package core;

import java.text.ParseException;

public class Product {

	private String code;
	private String name;
	private String catagory;
	private String location;
	private String datePurchased;
	private String serialNumber;
	private String model;
	private double price;
	private int depreciationVal;

	public Product() {

	}

	public Product(String code, String name) {
		this.code = code;
		this.name = name;

	}

	public Product(String code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public Product(String code, String name, String catagory, double price) {
		this.code = code;
		this.name = name;
		this.catagory = catagory;
		this.price = price;
	}

	public Product(String code, String name, double price, String catagory, String location) {
		this.code = code;
		this.name = name;
		this.catagory = catagory;
		this.location = location;
		this.price = price;
	}

	public Product(String code, String name, String catagory, double price, String serialnumber, String model,
			String location, String datePurchased, int depreciationVal) {
		this.code = code;
		this.name = name;
		this.catagory = catagory;
		this.price = price;
		this.serialNumber = serialnumber;
		this.model = model;
		this.location = location;
		this.datePurchased = datePurchased;
		this.depreciationVal = depreciationVal;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(String datePurchased) throws ParseException {
		this.datePurchased = datePurchased;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDepreciationVal() {
		return depreciationVal;
	}

	public void setDepreciationVal(int depreciationVal) {
		this.depreciationVal = depreciationVal;
	}
}
