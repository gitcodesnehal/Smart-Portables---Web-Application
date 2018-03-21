import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.Serializable;
import java.util.*;

public class CreateOrder extends HttpServlet {
	
  public static HashMap<String,Orders> hm_order = new HashMap<String,Orders>();
  SmartPortableUser u=null;
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    HttpSession session = request.getSession();
    String fname = session.getAttribute("fname").toString();
    double totalamount = Double.parseDouble(request.getParameter("totalamount"));
    String prevpage = request.getParameter("prevpage");
    request.setAttribute("prevpage", prevpage);
    String address = request.getParameter("address");
    long creditcardnumber = Long.parseLong(request.getParameter("creditcardnumber"));
    
    
      /*for(String key:RegisterUserServlet.hm_user.keySet())
    {
    	System.out.println(key);
    	if(RegisterUserServlet.hm_user.get(key).getFname().equals(fname))
    	{
    		System.out.println("isncd");
    		u = RegisterUserServlet.hm_user.get(key);
    	}
    }*/
    
    u=MySQLDataStoreUtilities.getUserDetails(fname);
    
    
    Random rand = new Random();
    int confirmno = rand.nextInt(10000);
    //Cart.cartlist=session.getAttribute("cartlist");
    Orders neworder = new Orders(confirmno,u,Cart.cartlist,totalamount,address,creditcardnumber);
    
    //get the product name from cartlist to insert in SQL db
    ArrayList<String> productnames = new ArrayList<String>();
    
    if(Cart.cartlist.size()!=0)
    {
    	for(Object objincart: Cart.cartlist)
    	{
    		if(objincart instanceof Product)
    		{
    			productnames.add(((Product)objincart).getName());
    		}
    		if(objincart instanceof Accessory)
    		{
    			productnames.add(((Accessory)objincart).getName());
    		}
    		
    	}
    }
    
    //insert product name and respective order id in Product_Order table in sql
    for(String productname : productnames)
    {
    	MySQLDataStoreUtilities.insertProductOrder(productname,confirmno);
    }
    
    
    
    //INSERT ORDER INTO MYSQL
    MySQLDataStoreUtilities.insertOrder(confirmno,fname,totalamount,address,creditcardnumber,neworder.getOrderdate(),neworder.getDeliverydate());
    
    hm_order.put(u.getUid(), neworder);
    //String type = request.getParameter("type");
    
    //delete current cart items that get ordered
    for(int i=0;i<Cart.cartlist.size();i++)
    {
    	Cart.cartlist.remove(i);
    }
    session.setAttribute("confirmno",confirmno);
    session.setAttribute("orderobj", neworder);
    response.sendRedirect("ViewOrdersServlet");
    
  }
  
  
}