package com.example.webshopsport.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.webshopsport.entities.Bill;
import com.example.webshopsport.entities.Billdetail;
import com.example.webshopsport.model.BillModel;
import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.service.IBillService;

@RestController
@CrossOrigin
public class BillController {

	@Autowired
	IBillService billService;
	@GetMapping("/admin/gettotalmoney")
	public ResponseEntity<ResponseObject> getTotalMoney() {
		int result = billService.getTotalMoney();
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Get Total Money Success", result));
	}
	
	@GetMapping("/admin/gettotalorder")
	public ResponseEntity<ResponseObject> getTotalOrder() {
		int result = billService.getTotalOrder();
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Get Total Order Success", result));
	}
	
	@GetMapping("/admin/getstatistical/{year}")
	public ResponseEntity<ResponseObject> getStatisticalByYear(@PathVariable(name = "year") int year) {
		List<Object> listStatistical = billService.StatisticalByYear(year);
		if (listStatistical != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Get List Statistical Success", listStatistical));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject(200, "Get List Statistical Failed", listStatistical));
	}

	@GetMapping("/admin/getallbill")
	public ResponseEntity<ResponseObject> getAllBill() {
		List<Bill> billList = billService.getAllBill();
		if (billList != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Get List Bill Success", billList));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Don't Bill To Get", null));
	}

	@PostMapping("/api/createbill")
	public ResponseEntity<ResponseObject> createBill(@RequestBody Map<String, String> map) {
		int accountId = Integer.parseInt(map.get("accountId"));
		int productId = Integer.parseInt(map.get("productId"));
		String productSize = map.get("productSize");
		int price = Integer.parseInt(map.get("price"));
		int quantity = Integer.parseInt(map.get("quantity"));
		Billdetail billCreate = billService.craeteBill2(accountId, productId, productSize, price, quantity);
		System.out.println("request: "+quantity);
		System.out.println("res: "+billCreate.getBilldetailQuantity());
		if (billCreate != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Create Bill Success", billCreate));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject(200, "Create Bill Failed, Quantity Is Not Enough", null));
	}

	@GetMapping("/api/getdetailbillbybillid/{billId}")
	public ResponseEntity<ResponseObject> getDetailBillByBillId(@PathVariable int billId) {
		
		
		ArrayList<Billdetail> billDetail = billService.getDetailBillByBillId(billId);
		if (billDetail != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Get List Bill Success", billDetail));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Don't Bill To Get", null));
	}

	@GetMapping("/api/getdetailbillbyaccount/{accountId}/{billStatus}")
	public ResponseEntity<ResponseObject> getDetailBillByAccount(@PathVariable int accountId,
			@PathVariable int billStatus) {
		ArrayList<Billdetail> listBillDetail = billService.getDetailBillByAccount(accountId, billStatus);
		if (listBillDetail != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Get List Bill Success", listBillDetail));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Don't Bill To Get", null));
	}
	
	@GetMapping("/api/getallorderbyacount/{accountId}/{billStatus}")
	public ResponseEntity<ResponseObject> getAllBillByAccount(@PathVariable int accountId,
			@PathVariable int billStatus) {
		ArrayList<Bill> listBillDetail = billService.getAllBillByAccount(accountId, billStatus);
		if (listBillDetail != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Get List Bill Success", listBillDetail));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "No Bill To Get", null));
	}

//	@GetMapping("/api/getdetailbillbybillid/{billid}/{status}")
//	public ResponseEntity<ResponseObject> getDetailBillByBillId(
//			@PathVariable("billid") int billid, @PathVariable("status") int status) {
//		ArrayList<Billdetail> billDetail = billService.getDetailBillByBillId(billid, status);
//		if(billDetail != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(
//					new ResponseObject(200, "Get List Bill Success", billDetail));
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(
//				new ResponseObject(200, "Don't Bill To Get", null));
//	}

	@PostMapping("/api/deleteproductfrombill")
	public ResponseEntity<ResponseObject> deleteProduct(@RequestBody Map<String, Integer> map) {
		int billDetailId = map.get("billdetailId");
		ArrayList<Billdetail> listresult = billService.deleteBillDetail(billDetailId);
		
		if (listresult != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Delete Produt Success", listresult));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject(200, "Don't Find Product To Delete", listresult));
	}

//	@PostMapping("/api/deleteproductfrombill/{billdetailid}/{productid}")
//	public ResponseEntity<ResponseObject> deleteProduct(
//			@PathVariable("billdetailid") int billdetailid, 
//			@PathVariable("productid") int productid) {
//		ArrayList<Billdetail> listresult = billService.deleteProduct(productid, billdetailid);
//		if(listresult != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(
//					new ResponseObject(200, "Delete Produt Success", listresult));
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(
//				new ResponseObject(200, "Don't Find Product To Delete", listresult));
//	}

	@PostMapping("/api/checkout")
	public ResponseEntity<ResponseObject> CheckoutBill(@RequestBody BillModel billModel) throws ParseException {
		BillModel bill = billService.checkoutBill(billModel);
		if (bill != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Checkout Success", bill));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Checkout Failed", null));
	}

//	@PostMapping("/api/cancelbill")
//	public ResponseEntity<ResponseObject> cancelBill() {
//		
//	}

}
