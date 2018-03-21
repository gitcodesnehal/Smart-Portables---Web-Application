import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Orders {
	
	int cno;
	SmartPortableUser spu;
	ArrayList<Object> cartlist;
	double totalamount;
	String address;
	long creditcardnumber;
	String orderdate;
	String deliverydate;
	
	public Orders(int cno, SmartPortableUser spu,ArrayList<Object> cartlist, double totalamount,String address, long creditcardnumber)
	{
		this.cno=cno;
		this.spu=spu;
		this.cartlist=cartlist;
		this.totalamount=totalamount;
		this.address=address;
		this.creditcardnumber=creditcardnumber;
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		this.orderdate= date.toString();
		cal.add(Calendar.DATE, 14);
		this.deliverydate=(cal.getTime()).toString();
		
	}
	
	
	public double getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public long getCreditcardnumber() {
		return creditcardnumber;
	}


	public void setCreditcardnumber(long creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
	}


	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public SmartPortableUser getSpu() {
		return spu;
	}

	public void setSpu(SmartPortableUser spu) {
		this.spu = spu;
	}

	public ArrayList<Object> getCartlist() {
		return cartlist;
	}

	public void setCartlist(ArrayList<Object> cartlist) {
		this.cartlist = cartlist;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}	

}
