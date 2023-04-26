package com.example.webshopsport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Billdetail;

@Repository
public interface BillDetailRepository extends JpaRepository<Billdetail, Integer> {
	
	@Query("SELECT bd FROM Billdetail bd WHERE bd.bill.billId=?1 AND bd.bill.billStatus=?2")
	public List<Billdetail> getDetailBillByBillId(int billid, int status);
	
	@Query("SELECT bd FROM Billdetail bd WHERE bd.bill.billId=?1 ")
	public List<Billdetail> getDetailBillByBillIdOnly(int billid);
	
	@Query("SELECT bd FROM Billdetail bd WHERE bd.product.productId=?1 AND bd.bill.billId=?2")
	public Billdetail findProductByIdProductAndIdBill(int idProduct, int idBill);
	
	@Query("SELECT bd FROM Billdetail bd WHERE bd.product.productId=?1  AND bd.billdetailSize=?2 AND bd.bill.billId=?3")
	public Billdetail findByProductIdAndProductSizeAndBillId(int idProduct,String billDetailSize, int idBill);
	
	@Transactional
	@Modifying
	@Query("UPDATE Billdetail bd SET bd.billdetailQuantity = ?1 WHERE bd.billdetailId = ?2 AND bd.product.productId=?3")
	public int updateQuantity(int quantity, int detailBillId, int productId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Billdetail bd WHERE bd.product.productId=?1 AND bd.bill.billId=?2")
	public int deleteProduct(int productId, int billId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Billdetail bd WHERE bd.billdetailId=?1")
	public int deleteBillDetail(int billDetailId);

}
