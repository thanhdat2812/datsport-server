package com.example.webshopsport.serviceImp;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Account;
import com.example.webshopsport.entities.Bill;
import com.example.webshopsport.entities.Billdetail;
import com.example.webshopsport.entities.Product;
import com.example.webshopsport.model.BillModel;
import com.example.webshopsport.repository.AccountRepository;
import com.example.webshopsport.repository.BillDetailRepository;
import com.example.webshopsport.repository.BillRepository;
import com.example.webshopsport.repository.ProductRepository;
import com.example.webshopsport.service.IBillService;

@Service
public class BillService implements IBillService {

	@Autowired
	BillRepository billRepository;

	@Autowired
	BillDetailRepository billdetailRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ProductRepository productRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int getTotalMoney() {
		int result = billRepository.getTotalMoney();
		return result;
	}

	@Override
	public int getTotalOrder() {
		int result = billRepository.getTotalOrder();
		return result;
	}

	@Override
	public ArrayList<Bill> getAllBill() {
		ArrayList<Bill> listBill = (ArrayList<Bill>) billRepository.getBill();
		
		return listBill;
	}

	@Override
	public ArrayList<Billdetail> getDetailBillByAccount(int accountId, int status) {
		Bill bill = billRepository.checkBillByAccountIdAndStatusId(accountId, status);
		if (bill == null) {
			return null;
		}
		ArrayList<Billdetail> billDetail = (ArrayList<Billdetail>) billdetailRepository
				.getDetailBillByBillId(bill.getBillId(), status);
		return billDetail;
	}
	@Override
	public ArrayList<Bill> getAllBillByAccount(int accountId, int status) {
		final int GET_ALL_STATUS=-1;
		
		ArrayList<Bill> bills;
		if(status!=GET_ALL_STATUS)
			bills = (ArrayList<Bill>) billRepository.getAllBillByAccountIdAndStatus(accountId, status);
		else 
			bills=(ArrayList<Bill>) billRepository.getAllBillByAccountId(accountId);
		return bills;
	}
	

	@Override
	@Transactional
	public Billdetail craeteBill2(int idAccount, int productId, String productSize, int price, int quantity) {
		Bill bill = billRepository.checkBillByAccountId(idAccount);
		Account acc = accountRepository.findAccountById(idAccount);
		Product product = productRepository.findProductById(productId);
		Billdetail billDetail = new Billdetail();
		if (quantity > product.getProductQuantity()) {
			return null;
		}
		if (bill != null) {
			Billdetail checkProduct = billdetailRepository.findByProductIdAndProductSizeAndBillId(productId,
					productSize, bill.getBillId());
			if (checkProduct != null) {
//				int newQuantity = checkProduct.getBilldetailQuantity() + quantity;
//				int newQuantity = quantity;
//				billdetailRepository.updateQuantity(newQuantity, checkProduct.getBilldetailId(), productId);
				
				// productRepository.updateProductQuantity(product.getProductQuantity() -
				// quantity, product.getProductId());
				checkProduct.setBilldetailQuantity(quantity);
				
//				Optional<Billdetail> billdetailData = billdetailRepository.findById(checkProduct.getBilldetailId());
				entityManager.merge(checkProduct);
		        entityManager.flush();
				return checkProduct;
			} else {
				billDetail.setBill(bill);
			}
		} else {
			Bill createBill = new Bill();
			Bill newBill = new Bill();
			createBill.setAccount(acc);
			billRepository.save(createBill);
			newBill = billRepository.checkBillByAccountId(idAccount);
			billDetail.setBill(newBill);
		}
		billDetail.setProduct(product);
		billDetail.setBilldetailSize(productSize);
		billDetail.setBilldetailPrice(price);
		billDetail.setBilldetailQuantity(quantity);
		Billdetail result = billdetailRepository.save(billDetail);
		// int newQuantity = product.getProductQuantity() - quantity;
		// productRepository.updateProductQuantity(newQuantity, product.getProductId());

		return result;
	}

	@Override
	public ArrayList<Billdetail> deleteBillDetail(int billDetailId) {
		Optional<Billdetail> checkBillDetail = billdetailRepository.findById(billDetailId);
		if (checkBillDetail == null)
			return null;
		Billdetail billDetail = checkBillDetail.get();
		Product product = productRepository.findProductById(billDetail.getProduct().getProductId());
		int quantity = billDetail.getBilldetailQuantity();
		int newQuantity = quantity + product.getProductQuantity();
		billdetailRepository.deleteBillDetail(billDetailId);
		ArrayList<Billdetail> list = (ArrayList<Billdetail>) billdetailRepository
				.getDetailBillByBillId(billDetail.getBill().getBillId(), 0);
		return list;

	}

	@Override
	public ArrayList<Billdetail> deleteProduct(int productId, int billId) {
		Billdetail checkBillDetail = billdetailRepository.findProductByIdProductAndIdBill(productId, billId);
		Product product = productRepository.findProductById(productId);

		if (checkBillDetail != null) {
			int quantity = checkBillDetail.getBilldetailQuantity();
			int newQuantity = quantity + product.getProductQuantity();
			billdetailRepository.deleteProduct(productId, billId);
			// productRepository.updateProductQuantity(newQuantity, product.getProductId());
			ArrayList<Billdetail> list = (ArrayList<Billdetail>) billdetailRepository.getDetailBillByBillId(billId, 0);
			return list;

		}
		return null;
	}

	@Override
	public ArrayList<Billdetail> getDetailBillByBillId(int billid) {
		return (ArrayList<Billdetail>) billdetailRepository.getDetailBillByBillIdOnly(billid);
	}

	@Override
	public BillModel checkoutBill(BillModel billModel) throws ParseException {
		Optional<Bill> checkBill = billRepository.findById(billModel.getBill_id());
		if (checkBill.isPresent()) {
			Bill bill = convertToEntity(billModel);
			bill.setBillId(checkBill.get().getBillId());
			billRepository.save(bill);
			ArrayList<Billdetail> listBillDetail = (ArrayList<Billdetail>) billdetailRepository
					.getDetailBillByBillId(bill.getBillId(), 1);
			for (Billdetail billdetail : listBillDetail) {
				productRepository.updateProductQuantity(
						billdetail.getProduct().getProductQuantity() - billdetail.getBilldetailQuantity(),
						billdetail.getProduct().getProductId());
			}
			return convertToDTO(bill);
		}
		return null;

	}

	@Override
	public List<Object> StatisticalByYear(int year) {
		List<Object> listStatistical = billRepository.StatisticalByYear(year);
		return listStatistical;
	}

	public Bill convertToEntity(BillModel billModel) throws ParseException {
//		DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Bill bill = new Bill();
		Account account = accountRepository.findAccountById(billModel.getUser_id());
		bill.setBillId(billModel.getBill_id());
		bill.setAccount(account);
		bill.setBillTotal(billModel.getBill_total());
		bill.setBillPayment(billModel.getBill_payment());
		bill.setBillAddressShip(billModel.getBill_address_ship());
		bill.setBillDate(billModel.getBill_date());
		bill.setBillStatus(billModel.getBill_status());
		return bill;
	}

	public BillModel convertToDTO(Bill bill) {
		BillModel billModel = new BillModel();
		billModel.setBill_id(bill.getBillId());
		billModel.setUser_id(bill.getAccount().getUserId());
		billModel.setBill_total(bill.getBillTotal());
		billModel.setBill_payment(bill.getBillPayment());
		billModel.setBill_address_ship(bill.getBillAddressShip());
		billModel.setBill_date(bill.getBillDate());
		billModel.setBill_status(bill.getBillStatus());
		return billModel;
	}

	
}
