package Model;

import java.sql.Date;

public class Receipt {
	
	String idReceipt;
	String customerName;
	String employeeName;
	double receiptPrice;
	Date buyDate;
	
	public Receipt() {
		
	}

	public Receipt(String idReceipt, String customerName, String employeeName, double receiptPrice, Date buyDate) {
		this.idReceipt = idReceipt;
		this.customerName = customerName;
		this.employeeName = employeeName;
		this.receiptPrice = receiptPrice;
		this.buyDate = buyDate;
	}

	public String getIdReceipt() {
		return idReceipt;
	}

	public void setIdReceipt(String idReceipt) {
		this.idReceipt = idReceipt;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getReceiptPrice() {
		return receiptPrice;
	}

	public void setReceiptPrice(double receiptPrice) {
		this.receiptPrice = receiptPrice;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
	public String print() {
		return "	Receipt ID: " + idReceipt + "\n"
				+"	Customer: " + customerName + "\n"
				+"	Employee: " + employeeName + "\n"
				+"	Price: " + receiptPrice + "\n"
				+"	Date: " + buyDate + "\n\n"
				+"		Thank you!";
	}
	
}
