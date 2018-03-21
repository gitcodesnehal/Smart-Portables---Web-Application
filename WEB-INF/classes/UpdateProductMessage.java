import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateProductMessage extends HttpServlet {
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

	
	String producttoupdate = request.getParameter("producttoupdate");
	//System.out.println(producttoupdate);
	double price = Double.parseDouble(request.getParameter("price"));
	double discount = Double.parseDouble(request.getParameter("discount"));
	double rebates = Double.parseDouble(request.getParameter("rebates"));
	
	//upate in mysql
	try {
		MySQLDataStoreUtilities.updateProduct(producttoupdate,price,discount,rebates);
	} catch (SQLException e)
	{
		e.printStackTrace();
	}
	
	//update in  hashmap
	String toupdateacc=null;
	for(String i: SmartPortablesSerializedDataStore.hm_accessories.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdateacc=i;
		}
	}
	if(toupdateacc!=null)
	{
		SmartPortablesSerializedDataStore.hm_accessories.get(toupdateacc).setPrice(price);
		SmartPortablesSerializedDataStore.hm_accessories.get(toupdateacc).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_accessories.get(toupdateacc).setRebates(rebates);
	}
	
	
	String toupdatees=null;
	for(String i: SmartPortablesSerializedDataStore.hm_externalstorages.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdatees=i;
		}
	}
	if(toupdatees!=null)
	{
		SmartPortablesSerializedDataStore.hm_externalstorages.get(toupdatees).setPrice(price);
		SmartPortablesSerializedDataStore.hm_externalstorages.get(toupdatees).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_externalstorages.get(toupdatees).setRebates(rebates);
	}
	
	
	String toupdatehp=null;
	for(String i: SmartPortablesSerializedDataStore.hm_headphones.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdatehp=i;
		}
	}
	if(toupdatehp!=null)
	{
		SmartPortablesSerializedDataStore.hm_headphones.get(toupdatehp).setPrice(price);
		SmartPortablesSerializedDataStore.hm_headphones.get(toupdatehp).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_headphones.get(toupdatehp).setRebates(rebates);
	}
	
	
	String toupdatelap=null;
	for(String i: SmartPortablesSerializedDataStore.hm_laptops.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdatelap=i;
		}
	}
	if(toupdatelap!=null)
	{
		SmartPortablesSerializedDataStore.hm_laptops.get(toupdatelap).setPrice(price);
		SmartPortablesSerializedDataStore.hm_laptops.get(toupdatelap).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_laptops.get(toupdatelap).setRebates(rebates);
	}
	
	
	String toupdateph=null;
	for(String i: SmartPortablesSerializedDataStore.hm_phones.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdateph=i;
		}
	}
	if(toupdateph!=null)
	{
		SmartPortablesSerializedDataStore.hm_phones.get(toupdateph).setPrice(price);
		SmartPortablesSerializedDataStore.hm_phones.get(toupdateph).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_phones.get(toupdateph).setRebates(rebates);
	}
	
	
	String toupdatesw=null;
	for(String i: SmartPortablesSerializedDataStore.hm_smartwatches.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdatesw=i;
		}
	}
	if(toupdatesw!=null)
	{
		SmartPortablesSerializedDataStore.hm_smartwatches.get(toupdatesw).setPrice(price);
		SmartPortablesSerializedDataStore.hm_smartwatches.get(toupdatesw).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_smartwatches.get(toupdatesw).setRebates(rebates);
	}
	
	
	
	String toupdatesp=null;
	for(String i: SmartPortablesSerializedDataStore.hm_speakers.keySet())
	{
		if(i.equals(producttoupdate))
		{
			toupdatesp=i;
		}
	}
	if(toupdatesp!=null)
	{
		SmartPortablesSerializedDataStore.hm_speakers.get(toupdatesp).setPrice(price);
		SmartPortablesSerializedDataStore.hm_speakers.get(toupdatesp).setDiscount(discount);
		SmartPortablesSerializedDataStore.hm_speakers.get(toupdatesp).setRebates(rebates);
	}
	
	
	String toupdateprod=null;
	for(Product p: SmartPortablesSerializedDataStore.products)
	{
		if(p.getName().equals(producttoupdate))
		{
			p.setPrice(price);
			p.setDiscount(discount);
			p.setRebates(rebates);
		}
	}
	
	
	
	
	String toupdateaccess=null;
	for(Accessory a: SmartPortablesSerializedDataStore.accessories)
	{
		if(a.getName().equals(producttoupdate))
		{
			a.setPrice(price);
			a.setDiscount(discount);
			a.setRebates(rebates);
		}
	}
	
	
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