import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteProductMessage extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	 response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession();
   
	String fname=(String)session.getAttribute("fname");
	String type = (String)session.getAttribute("type");
	
	 // Display the Header section	
	
    out.println("<html>");
		out.println("<head>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' content='no-cache'/>");
			out.println("<title>Smart Portables</title>");
			out.println("<link rel='shortcut icon' href='cart/icon.jpg'/>");
			out.println("<link rel='stylesheet' href='styles.css' type='text/css' />");
		out.println("</head>");
		out.println("<body>");
			out.println("<div id='container'>");
			out.println("<header>");
			out.println("<h1><a href='Home'>Smart <span> Portables</span></a></h1>");
			
			if(fname!=null)
			{
				out.println("<h3 align='right' color:'black'>Welcome, "+fname+"</h3>");
				out.println("<div align='right'>");
				out.println("<a href='SignOut'>Sign out</a>");
				out.println("</div>");
			}
			
			
			out.println("</header>");
			out.println("<nav>");
			out.println("<ul>");
			out.println("<li  class='start selected'><a id='home' href='Home'>Home</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=smartwatches'>Smart Watches</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=speakers'>Speakers</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=headphones'>Headphones</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=phones'>Phones</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=laptops'>Laptops</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=externalstorages'>External Storage</a></li>");
			//out.println("<li  class=''><a href='ProductServlet?param1=accessories'>Accessories</a></li>");
			
	
	if (fname == null)
			{
			out.println("<li class=''><a href='Registration'>Register</a></li>");
			out.println("<li class='' ><a href='Login'>Sign in</a></li>");
			} 
			out.println("<li class='' ><a href='ViewOrdersServlet'>View Orders</a></li>");
			
			
				//Data analytics links only accessible to StoreManager
				if(fname!=null && type.equals("StoreManager"))
			{
				out.println("<li class='' ><a href='DataAnalyticsServlet'>Data Analytics</a></li>");
				out.println("<li class='' ><a href='InsertProductServlet'>Add New Product</a></li>");
				
			}
			
			out.println("<div align='right'>");
			out.println("<form action='CartServlet'>");
			out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
			
	//Displaying the Content section
			
	out.println("<img class='header-image' src='images/combined.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");

	String producttodelete = request.getParameter("producttodelete");
	
	//delete from  mysql
	MySQLDataStoreUtilities.deleteProduct(producttodelete);
	
	//delete from hashmap
	String todeleteacc=null;
	for(String i: SmartPortablesSerializedDataStore.hm_accessories.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeleteacc=i;
		}
	}
	if(todeleteacc!=null)
	{
		SmartPortablesSerializedDataStore.hm_accessories.remove(todeleteacc);
	}
	
	
	String todeletees=null;
	for(String i: SmartPortablesSerializedDataStore.hm_externalstorages.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeletees=i;
		}
	}
	if(todeletees!=null)
	{
		SmartPortablesSerializedDataStore.hm_externalstorages.remove(todeletees);
	}
	
	
	String todeletehp=null;
	for(String i: SmartPortablesSerializedDataStore.hm_headphones.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeletehp=i;
		}
	}
	if(todeletehp!=null)
	{
		SmartPortablesSerializedDataStore.hm_headphones.remove(todeletehp);
	}
	
	
	String todeletelap=null;
	for(String i: SmartPortablesSerializedDataStore.hm_laptops.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeletelap=i;
		}
	}
	if(todeletelap!=null)
	{
		SmartPortablesSerializedDataStore.hm_laptops.remove(todeletelap);
	}
	
	
	String todeleteph=null;
	for(String i: SmartPortablesSerializedDataStore.hm_phones.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeleteph=i;
		}
	}
	if(todeleteph!=null)
	{
		SmartPortablesSerializedDataStore.hm_phones.remove(todeleteph);
	}
	
	
	String todeletesw=null;
	for(String i: SmartPortablesSerializedDataStore.hm_smartwatches.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeletesw=i;
		}
	}
	if(todeletesw!=null)
	{
		System.out.println("removed from smart watches");
		SmartPortablesSerializedDataStore.hm_smartwatches.remove(todeletesw);
	}
	
	
	
	String todeletesp=null;
	for(String i: SmartPortablesSerializedDataStore.hm_speakers.keySet())
	{
		if(i.equals(producttodelete))
		{
			todeletesp=i;
		}
	}
	if(todeletesp!=null)
	{
		SmartPortablesSerializedDataStore.hm_speakers.remove(todeletesp);
	}
	
	
	String todeleteprod=null;
	for(Product p: SmartPortablesSerializedDataStore.products)
	{
		if(p.getName().equals(producttodelete))
		{
			todeleteprod=p.getName();
		}
	}
	if(todeleteprod!=null)
	{
		System.out.println("removed from products");
		SmartPortablesSerializedDataStore.products.remove(todeleteprod);
	}
	
	
	
	String todeleteaccess=null;
	for(Accessory a: SmartPortablesSerializedDataStore.accessories)
	{
		if(a.getName().equals(producttodelete))
		{
			todeleteaccess=a.getName();
		}
	}
	if(todeleteaccess!=null)
	{
		SmartPortablesSerializedDataStore.accessories.remove(todeleteaccess);
	}
	
	
	
	
	out.println("<h3>Product Deleted Successfully:</h3>");
	out.println("<a href='DeleteProductServlet'> Click here to delete more products ");
	
	
	out.println("</section>");
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	
	// Left Navigation 
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
	
	//Footer
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
