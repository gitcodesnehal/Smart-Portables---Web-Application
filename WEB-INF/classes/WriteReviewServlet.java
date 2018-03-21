import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class WriteReviewServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession();
				String fname=(String)session.getAttribute("fname");
	
	
    out.println("<html>");
		out.println("<head>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' content='no-cache'/>");
			out.println("<title>Smart Portables</title>");
			out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
			out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'> ");
			out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
			out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>");
			out.println("<script src='script.js'></script>");
			
			
			
		out.println("</head>");
		out.println("<body>");
			out.println("<div id='container'>");
			out.println("<header>");
			out.println("<h1><a href='/'>Smart <span> Portables</span></a></h1>");
			
				if(fname!=null)
			{
				out.println("<h3 align='right' color:'black'>Hello ,"+fname+"</h3>");
				out.println("<div align='right'>");
				out.println("<a href='SignOut'>Sign out</a>");
				out.println("</div>");
			}
			
			
			
			out.println("</header>");
			out.println("<nav>");
			out.println("<ul>");
			out.println("<li  class='start selected'><a id='home' href='Home'>Home</a></li>");
			out.println("<li  class=''><a id='smartwatches' href='ProductServlet?param1=smartwatches'>Smart Watches</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=speakers'>Speakers</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=headphones'>Headphones</a></li>");
			out.println("<li class=''><a href='ProductServlet?param1=phones'>Phones</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=laptops'>Laptops</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=externalstorages'>External Storage</a></li>");
			
				
					if (fname == null)
			{
			out.println("<li class=''><a href='Registration'>Register</a></li>");
			out.println("<li class='' ><a href='Login'>Sign in</a></li>");
			} 
			out.println("<li class='' ><a href='ViewOrdersServlet'>View Orders</a></li>");
			out.println("<li class='' ><a href='TrendingServlet'>Trending</a></li>");
		
			
			
			out.println("<div align='right'>");
			out.println("<form action='CartServlet'>");
			out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
			
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	
 String product_name=request.getParameter("button");
	 String product_type=request.getParameter("product_type");
	 
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
		}
	 
		 
	 //FOR PRODUCTS
	 for(String key: map.keySet())
	 {
		 if(map.get(key).getName().equals(product_name))
		 {
			 
			out.println("<table style='width:75%'>");
			out.println("<tr>");
			out.println("<th>Product Model Name</th>");
			out.println("<td>"+map.get(key).getName()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Product Category</th>");
			out.println("<td>"+map.get(key).getId()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Product Price</th>");
			out.println("<td>"+map.get(key).getPrice()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer Name</th>");
			out.println("<td>"+map.get(key).getRetailer()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer Zip</th>");
			out.println("<td>"+map.get(key).getRetailerzipcode()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer City</th>");
			out.println("<td>"+map.get(key).getRetailercity()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer State</th>");
			out.println("<td>"+map.get(key).getRetailerstate()+"</th>");
			out.println("</tr>");
			if(map.get(key).getDiscount()==0.0)
			{
			out.println("<tr>");
			out.println("<th>Product On Sale</th>");
			out.println("<td>Yes</th>");
			out.println("</tr>");
			}
			else
			{
			out.println("<tr>");
			out.println("<th>Product On Sale</th>");
			out.println("<td>No</th>");
			out.println("</tr>");	
			}
			out.println("<tr>");
			out.println("<th>Manufacturer Name</th>");
			out.println("<td>"+map.get(key).getManufacturer()+"</th>");
			out.println("</tr>");
			if(map.get(key).getRebates()==0.0)
			{
			out.println("<tr>");
			out.println("<th>Manufacturer Rebate</th>");
			out.println("<td>Yes</th>");
			out.println("</tr>");
			}
			else
			{
			out.println("<tr>");
			out.println("<th>Manufacturer Rebate</th>");
			out.println("<td>No</th>");
			out.println("</tr>");	
			}
			out.println("</table>");
			
			
		 }
	 }
	
	 //FOR ACCESSORIES
	 for(String key: map1.keySet())
	 {
		 if(map1.get(key).getName().equals(product_name))
		 {
			 //System.out.println(map1.get(key).getName());
			out.println("<table style='width:75%'>");
			out.println("<tr>");
			out.println("<th>Product Model Name</th>");
			out.println("<td>"+map1.get(key).getName()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Product Category</th>");
			out.println("<td>Accessory</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Product Price</th>");
			out.println("<td>"+map1.get(key).getPrice()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer Name</th>");
			out.println("<td>"+map1.get(key).getRetailer()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer Zip</th>");
			out.println("<td>"+map1.get(key).getRetailerzipcode()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer City</th>");
			out.println("<td>"+map1.get(key).getRetailercity()+"</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th>Retailer State</th>");
			out.println("<td>"+map1.get(key).getRetailerstate()+"</th>");
			out.println("</tr>");
			if(map1.get(key).getDiscount()==0.0)
			{
			out.println("<tr>");
			out.println("<th>Product On Sale</th>");
			out.println("<td>Yes</th>");
			out.println("</tr>");
			}
			else
			{
			out.println("<tr>");
			out.println("<th>Product On Sale</th>");
			out.println("<td>No</th>");
			out.println("</tr>");	
			}
			out.println("<tr>");
			out.println("<th>Manufacturer Name</th>");
			out.println("<td>"+map1.get(key).getManufacturer()+"</th>");
			out.println("</tr>");
			if(map1.get(key).getRebates()==0.0)
			{
			out.println("<tr>");
			out.println("<th>Manufacturer Rebate</th>");
			out.println("<td>Yes</th>");
			out.println("</tr>");
			}
			else
			{
			out.println("<tr>");
			out.println("<th>Manufacturer Rebate</th>");
			out.println("<td>No</th>");
			out.println("</tr>");	
			}
			out.println("</table>");
			
			
		 }
	 }
	
		out.println("<h2>Enter Product Review</h2>");
		out.println("</br>");
		out.println("<div style='background-color:#f2f2f2; width:50%'>");
		out.println("<form id='customerinfo' action='StoreReviewServlet'>");
		out.println("<h3>   UserId: </h3><input type='text' name='userid' style='width:50%'></input>");
		out.println("</br>");
		out.println("<h3>   UserAge: </h3><input type='text' name='userage' style='width:50%'></input>");
		out.println("</br>");
		out.println(" <h3> Gender: </h3>");
		out.println("<input type='radio' name='gender' value='female' required/> Female");
		out.println("<input type='radio' name='gender' value='male' required/> Male");
		out.println("</br>");
		out.println("<h3>   UserOccupation: </h3><input type='text' name='useroccupation' style='width:50%'></input>");
		out.println(" <h3> Rating : </h3>");
		out.println("<select name='rate'>");
		out.println("<option  value='1'>1</option>");
		out.println("<option  value='2'>2</option>");
		out.println("<option value='3'>3</option>");
		out.println("<option value='4'>4</option>");
		out.println("<option  value='5'>5</option>");
		out.println("</select>");
		out.println("</br>");
		LocalDate localDate = LocalDate.now();
		String date=(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate)).toString();
		out.println(" <h3> Review Date:  </h3>");
		out.println("<input type='text' name='reviewdate' value="+date+" style='width:50%'></input>");
		out.println("<h3>   Review: </h3><input type='text' name='reviewtext' style='width:50%'></input>");
		out.println("</br>");
		out.println("</br>");
		out.println("<input type='hidden' name='product_name' value="+product_name+"></input>");
		out.println("<input type='hidden' name='product_type' value="+product_type+"></input>");
		out.println("<input type='submit' value='Submit' style='background-color:#4CAF50; color:white; width:25%'></input>");
		out.println("</br>");
		out.println("</form>");
		out.println("</br>");
		out.println("</div>");	
			
	out.println("</section>");
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li><a href='TrendingServlet'>Trending</a></li>");
	out.println("<li><a href='ProductServlet?param1=smartwatches'>Smart Watches</a></li>");
	out.println("<li><a href='ProductServlet?param1=speakers'>Speakers</a></li>");
	out.println("<li><a href='ProductServlet?param1=headphones'>Headphones</a></li>");
	out.println("<li><a href='ProductServlet?param1=phones'>Phones</a></li>");
	out.println("<li><a href='ProductServlet?param1=laptops'>Laptop</a></li>");
	out.println("<li><a href='ProductServlet?param1=externalstorages'>External Storage</a></li>");
	out.println("<li><a href='ProductServlet?param1=accessories'>Accessories</a></li>");
	out.println("</ul>");
	out.println("</li>");	
	out.println("<li>");
	out.println("<h4>About us</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<p style='margin: 0;'>This is a sample website created to demonstrate a standard enterprise web page.</p>");
	out.println(" </li>");
	out.println("</ul>");
	out.println("</li>");	
	out.println("<li>");
	out.println("<h4>Search site</h4>");
	out.println("<ul>");
	out.println("<li class='text'>");
	out.println("<form method='get' class='searchform' action='#'>");
	out.println("<p>");
	out.println("<input type='text' size='25' value='' name='s' class='s' />");
	out.println("</p>");	
	out.println("</form></li></ul></li>");	     	
	out.println("<li>");	
	out.println("<h4>Helpful Links</h4>");	
	out.println("<ul>");	
	out.println("<li><a href='http://www.cnn.com/' title='premium templates'>See what's happening around you</a></li>");	
	out.println("<li><a href='https://travel.usnews.com/rankings/worlds-best-vacations/' title='web hosting'>Get some ideas on your next trip?</a></li>");	
	out.println("</ul></li></ul></aside>");	
	out.println("<div class='clear'></div>");
	out.println("</div>");	
	out.println("<footer>");	
	out.println("<div class='footer-content'>");	
	out.println("<div class='clear'></div>");	
	out.println("</div>");	
	out.println("<div class='footer-bottom'>");	
	out.println("<p>Smart Portables - Enterprise Web Application </p>");	
	out.println("</div>");	
	out.println("</footer>");	
	out.println("</div>");	
			
	out.println("</body>");
	out.println("</html>");
	
	  }
}
