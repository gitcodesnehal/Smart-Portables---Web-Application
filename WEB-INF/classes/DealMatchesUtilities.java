import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class DealMatchesUtilities extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession();
   
	String fname=(String)session.getAttribute("fname");
	String type = (String)session.getAttribute("type");
    
    HashMap<String,Product> selectedproducts = new HashMap<String,Product>();
    HashMap<String,Product> productmap = new HashMap<String,Product>();
    	//out.println("<div id='content'>");
    	//out.println("<div class='post'>");
    	//out.println("<h2 class='title'>");
    	//out.println("<a href='#'>Deal Macthes</a></h2>");
    	//out.println("<div class='entry'>");
    	//out.println("<br><br>");
    	//out.println("<h2>The world trusts us to deliver SPEEDY service </h2>");
    	//out.println("<br><br>");
    	//out.println("<h2>We beat our competitors in all aspects. Price-Match Guaranteed </h2>");
    	String line=null;
		
    	try {
			productmap = MySQLDataStoreUtilities.getData();
		} 
		catch (SQLException e) 
		{
			
		}
    	for(Map.Entry<String, Product> entry : productmap.entrySet())
    	{
    		if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
    		{
    			BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\apache-tomcat-7.0.34\\webapps\\Assignment_5\\DealMatches.txt")));
    			line=reader.readLine();
    			if(line==null)
    			{
    				out.print("<h2 align='center'>No Offers Found</h2>");
    				break;
    			}
    			else
    			{
    				
    				do
    				{
    					if(line.contains(entry.getKey().replace('_', ' ')))
    					{
    						
    						out.print("<h3>"+line+"</h2>");
    						out.print("<br>");
    						selectedproducts.put(entry.getKey(), entry.getValue());
    					}
    				}
    				while((line=reader.readLine())!=null);
    			}

    		}
    	}
		out.println("<hr>");
    	
    	//displaying selectedproducts on Home page
    	for(Map.Entry<String, Product> entry: selectedproducts.entrySet())
    	{
    		out.println("<table>");
			out.println("<tr>");
			out.println("<h3>"+entry.getKey()+"</h3>");
			out.println("</tr>");
			out.println("<tr>");
    		out.println("<h2>$"+entry.getValue().getPrice()+"</h2>");
			out.println("</tr>");
			out.println("<tr>");
    		out.println("<img src =images/"+entry.getValue().getImage()+" width='25%' height='25%'>");
			out.println("</tr>");
    		out.println("<br>");
			out.println("<br>");
    		
    		if(fname!=null)
			{
			out.println("<form id='prodaddtocart' action='AddToCartServlet'>");
    		out.println("<input type='hidden' name='pagename' value='home'></input>");
			out.println("<tr>");
    		out.println("<button type='submit' name='button' value="+entry.getKey()+">Add to cart</button>");
			out.println("</form>");
			out.println("<br>");
			out.println("<br>");
			out.println("</tr>");
    		
    		
    		out.println("<form id='prodwritereview' action='WriteReviewServlet'>");
			
			String product_type=null;
			
		if(SmartPortablesSerializedDataStore.hm_accessories.containsKey(entry.getKey()))
       	{
       		product_type="Accessories";
       	}
       	else if(SmartPortablesSerializedDataStore.hm_smartwatches.containsKey(entry.getKey()))
       	{
       		product_type="SmartWatches";
       	}
       	else if(SmartPortablesSerializedDataStore.hm_speakers.containsKey(entry.getKey()))
       	{
       		product_type="Speakers";
       	}
       	else if(SmartPortablesSerializedDataStore.hm_headphones.containsKey(entry.getKey()))
       	{
       		product_type="Headphones";
       	}
       	else if(SmartPortablesSerializedDataStore.hm_phones.containsKey(entry.getKey()))
       	{
       		product_type="Phones";
       	}
       	else if(SmartPortablesSerializedDataStore.hm_laptops.containsKey(entry.getKey()))
       	{
       		product_type="Laptops";
       	}
       	else if(SmartPortablesSerializedDataStore.hm_externalstorages.containsKey(entry.getKey()))
       	{
       		product_type="ExternalStorages";
       	}
			
    		out.println("<input type='hidden' name='product_type' value="+product_type+"></input>");
			out.println("<tr>");
    		out.println("<button type='submit' name='button' value="+entry.getKey()+">Write Review</button>");
    		out.println("</form>");
			out.println("<br>");
			out.println("<br>");
			out.println("</tr>");
			}
    		
    		
    		out.println("<form id='prodviewreview' action='ViewReviewServlet'>");
    		out.println("<input type='hidden' name='product_name' value="+entry.getKey()+"></input>");
			out.println("<tr>");
    		out.println("<button type='submit' name='button' value="+entry.getKey()+">View Review</button>");
    		out.println("</form>");
			out.println("<br>");
			out.println("<br>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<hr>");
    	}
   
    
  }
}