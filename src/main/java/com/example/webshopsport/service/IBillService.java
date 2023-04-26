package com.example.webshopsport.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.webshopsport.entities.Bill;
import com.example.webshopsport.entities.Billdetail;
import com.example.webshopsport.model.BillModel;

public interface IBillService {
	
	public int getTotalMoney();
	public int getTotalOrder();
	public BillModel checkoutBill(BillModel billModel) throws ParseException;
	public ArrayList<Billdetail> getDetailBillByBillId(int billid);
	public ArrayList<Billdetail> getDetailBillByAccount(int accountId, int status);
	public ArrayList<Bill> getAllBillByAccount(int accountId, int status);
	public ArrayList<Bill> getAllBill();
	public Billdetail craeteBill2(int idAccount, int productId,String productSize, int price, int quantity);
	public ArrayList<Billdetail> deleteProduct(int productId, int billId);
	public List<Object> StatisticalByYear(int year);
	public ArrayList<Billdetail> deleteBillDetail(int billDetailId);


}
