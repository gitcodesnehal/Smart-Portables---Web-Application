import java.io.*;
import java.util.*;
public class Cart{

	public static ArrayList<Object> cartlist = new ArrayList<Object>();
	
	public Cart()
	{
		
	}
	
	public ArrayList<Object> getCart(){
		return cartlist;
	}
	
	public void setCart(ArrayList<Object> cartlist){
		this.cartlist=cartlist;
	}
	
}
