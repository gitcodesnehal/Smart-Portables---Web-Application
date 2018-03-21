

import java.util.*;

public class Product implements java.io.Serializable{
	 String id;
	 String name;
	 double price;
	 String image;
	 String retailer;
	 String retailercity;
	 int retailerzipcode;
	 String retailerstate;
	 String manufacturer;
	 String condition;
	 double discount;
	 double rebates;
	 //int availquantity;
	 ArrayList<Accessory> accessories = new ArrayList<Accessory>();
	
	public Product(String id, String name, double price, String image, String retailer,String retailercity, int retailerzipcode,String retailerstate,String manufacturer, String condition,double discount,double rebates, ArrayList<Accessory> accessories){
		this.id=id;
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.retailercity=retailercity;
		this.retailerzipcode=retailerzipcode;
		this.retailerstate=retailerstate;
		this.manufacturer=manufacturer;
		this.condition=condition;
		this.discount = discount;
		this.rebates=rebates;
		//this.availquantity=availquantity;
		this.setAccessories(accessories);
	}
	
	
	
	public String getRetailerstate() {
		return retailerstate;
	}



	public void setRetailerstate(String retailerstate) {
		this.retailerstate = retailerstate;
	}



	public String getRetailercity() {
		return retailercity;
	}



	public void setRetailercity(String retailercity) {
		this.retailercity = retailercity;
	}



	public int getRetailerzipcode() {
		return retailerzipcode;
	}



	public void setRetailerzipcode(int retailerzipcode) {
		this.retailerzipcode = retailerzipcode;
	}



	public String getManufacturer() {
		return manufacturer;
	}



	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}



	public double getRebates() {
		return rebates;
	}

	public void setRebates(double rebates) {
		this.rebates = rebates;
	}

	public Product(){
		
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id=id;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories(ArrayList<Accessory> accessories) {
		this.accessories = accessories;
	}

	public ArrayList<Accessory> getAccessories() {
		return accessories;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}

