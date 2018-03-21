import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class StoreNewProduct extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String productname=request.getParameter("productname");
    double price = Double.parseDouble(request.getParameter("price"));
    String retailer=request.getParameter("retailer");
    String retailercity=request.getParameter("retailercity");
    int retailerzipcode=Integer.parseInt(request.getParameter("retailerzipcode"));
    String retailerstate=request.getParameter("retailerstate");
    String manufacturer=request.getParameter("manufacturer");
    String cond=request.getParameter("cond");
    double discount=Double.parseDouble(request.getParameter("discount"));
    double rebates=Double.parseDouble(request.getParameter("rebates"));
    String prodtype=request.getParameter("prodtype");
    if(prodtype.equals("Product"))
    {
    	String prodcat = request.getParameter("prodcat");
    	if(prodcat.equals("smartwatches"))
    	{
    		Product p =new Product(prodcat,productname,price,"watch_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,new ArrayList<Accessory>());
    		SmartPortablesSerializedDataStore.hm_smartwatches.put(productname, p);
    		MySQLDataStoreUtilities.insertNewProduct(productname,  price, "watch_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
    	    
    	}
    	else if(prodcat.equals("speakers"))
    	{
    		Product p =new Product(prodcat,productname,price,"speaker_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,new ArrayList<Accessory>());
    		SmartPortablesSerializedDataStore.hm_speakers.put(productname, p);
    		MySQLDataStoreUtilities.insertNewProduct(productname,  price, "speaker_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
    	}
    	else if(prodcat.equals("headphones"))
    	{
    		Product p =new Product(prodcat,productname,price,"headphones_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,new ArrayList<Accessory>());
    		SmartPortablesSerializedDataStore.hm_headphones.put(productname, p);
    		MySQLDataStoreUtilities.insertNewProduct(productname,  price, "headphones_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
    	    
    	}
    	else if(prodcat.equals("phones"))
    	{
    		Product p =new Product(prodcat,productname,price,"phone_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,new ArrayList<Accessory>());
    		SmartPortablesSerializedDataStore.hm_phones.put(productname, p);
    		MySQLDataStoreUtilities.insertNewProduct(productname,  price, "phone_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
    	    
    	}
    	else if(prodcat.equals("laptops"))
    	{
    		Product p =new Product(prodcat,productname,price,"laptop_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,new ArrayList<Accessory>());
    		SmartPortablesSerializedDataStore.hm_laptops.put(productname, p);
    		MySQLDataStoreUtilities.insertNewProduct(productname,  price, "laptop_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
    	    
    	}
    	else if(prodcat.equals("externalstorages"))
    	{
    		Product p =new Product(prodcat,productname,price,"flashdrive_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,new ArrayList<Accessory>());
    		SmartPortablesSerializedDataStore.hm_externalstorages.put(productname, p);
    		MySQLDataStoreUtilities.insertNewProduct(productname,  price, "flashdrive_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
    	    
    	}
    }
    else
    {
    	Accessory a = new Accessory(productname,price,"acc_1.jpg",retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates);
    	SmartPortablesSerializedDataStore.hm_accessories.put(productname,a);
    	MySQLDataStoreUtilities.insertNewProduct(productname,  price, "acc_1.jpg", retailer, retailercity, retailerzipcode, retailerstate,  manufacturer,  cond, discount, rebates, prodtype);
	    
    }
    
    response.sendRedirect("StoreNewProductMessage");
    
        
    
    
  }
}