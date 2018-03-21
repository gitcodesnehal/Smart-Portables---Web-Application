import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

public class StoreReviewServlet extends HttpServlet implements java.io.Serializable {

 public static HashMap<String,SmartPortableUser> hm_user = new HashMap<String,SmartPortableUser>();
  public static HashMap<String,String> map1	 = new HashMap<String,String>();
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
	
	String product_name=request.getParameter("product_name");
	String product_type=request.getParameter("product_type");
	
	String userid=request.getParameter("userid");
	int userage=Integer.parseInt(request.getParameter("userage"));
	String gender=request.getParameter("gender");
	String useroccupation=request.getParameter("useroccupation");
	int rate=Integer.parseInt(request.getParameter("rate"));
	String reviewdate=request.getParameter("reviewdate");
	String reviewtext=request.getParameter("reviewtext");
	
	String product_model_name=null;
	String product_category=null;
	double product_price=0.0;
	String retailer_name=null;
	int retailer_zip=0;
	String retailer_city=null;
	String retailer_state=null;
	String product_on_sale=null;
	String manufacturer_name=null;
	String manufacturer_rebates=null;
	
	HashMap<String, Product> map = new HashMap<String,Product>();
	HashMap<String, Accessory> map1 = new HashMap<String,Accessory>();
	 
	 if(product_type.equalsIgnoreCase("SmartWatches")){
			map=SmartPortablesSerializedDataStore.hm_smartwatches;
		}
		else if(product_type.equalsIgnoreCase("Speakers")){
			map=SmartPortablesSerializedDataStore.hm_speakers;
		}
		else if(product_type.equalsIgnoreCase("Headphones")){
			map=SmartPortablesSerializedDataStore.hm_headphones;
		}
		else if(product_type.equalsIgnoreCase("Phones")){
			map=SmartPortablesSerializedDataStore.hm_phones;
		}
		else if(product_type.equalsIgnoreCase("Laptops")){
			map=SmartPortablesSerializedDataStore.hm_laptops;
		}
		else if(product_type.equalsIgnoreCase("ExternalStorages")){
			map=SmartPortablesSerializedDataStore.hm_externalstorages;
		}
		else if(product_type.equalsIgnoreCase("Accessories")){
			map1=SmartPortablesSerializedDataStore.hm_accessories;
			//System.out.println("accessory selected");
		}
	 
	 for(String key: map.keySet())
	 {
		 if(map.get(key).getName().equals(product_name))
		 {
			product_model_name=map.get(key).getName();
			product_category=map.get(key).getId();
			product_price=map.get(key).getPrice();
			retailer_name=map.get(key).getRetailer();
			retailer_zip=map.get(key).getRetailerzipcode();
			retailer_city=map.get(key).getRetailercity();
			retailer_state=map.get(key).getRetailerstate();
			if(map.get(key).getDiscount()==0.0)
			{
				product_on_sale="No";
			}
			else
			{
				product_on_sale="Yes";
			}
			
			manufacturer_name=map.get(key).getManufacturer();
			if(map.get(key).getRebates()==0.0)
			{
				manufacturer_rebates="No";
			}
			else
			{
				manufacturer_rebates="Yes";
			}
			 
		 }
	}
	 
	 
	 for(String key: map1.keySet())
	 {
		
		 if(map1.get(key).getName().equals(product_name))
		 {
			product_model_name=map1.get(key).getName();
			product_category="Accessory";
			product_price=map1.get(key).getPrice();
			retailer_name=map1.get(key).getRetailer();
			retailer_zip=map1.get(key).getRetailerzipcode();
			retailer_city=map1.get(key).getRetailercity();
			retailer_state=map1.get(key).getRetailerstate();
			if(map1.get(key).getDiscount()==0.0)
			{
				product_on_sale="No";
			}
			else
			{
				product_on_sale="Yes";
			}
			
			manufacturer_name=map1.get(key).getManufacturer();
			if(map1.get(key).getRebates()==0.0)
			{
				manufacturer_rebates="No";
			}
			else
			{
				manufacturer_rebates="Yes";
			}
			 
		 }
	}
	
	MongoDBDataStoreUtilities.insertReview(product_model_name,product_category,product_price,retailer_name,retailer_zip,retailer_city,retailer_state,product_on_sale,manufacturer_name,manufacturer_rebates,userid,userage,gender,useroccupation,rate,reviewdate,reviewtext);
	response.sendRedirect("StoreReviewMessage");
	
	}
	
}
	