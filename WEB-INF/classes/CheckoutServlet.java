import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class CheckoutServlet extends HttpServlet {
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
			out.println("<li class='' ><a href='TrendingServlet'>Trending</a></li>");
			
				
					if (fname == null)
			{
			out.println("<li class=''><a href='Registration'>Register</a></li>");
			out.println("<li class='' ><a href='Login'>Sign in</a></li>");
			} 
			out.println("<li class='' ><a href='ViewOrdersServlet'>View Orders</a></li>");
		
			
			
			out.println("<div align='right'>");
			out.println("<form action='CartServlet'>");
			out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
			
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	//request.getParameter("button");
	//System.out.println("ewfewrferfer");
	
	double totalamount=0;
	
	if(Cart.cartlist.size()!=0)
	{
		
		out.println("<table style='width:50%'>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Price</th>");
		out.println("</tr>");
		for(Object objincart: Cart.cartlist)
		{
			 
			out.println("<tr>");
			if(objincart instanceof Product)
			{
				double discountedprice =((((Product)objincart).getPrice()*((Product)objincart).getDiscount())/100);
				discountedprice = ((Product)objincart).getPrice()-discountedprice;
				totalamount=totalamount+discountedprice;
				out.println("<td>"+((Product)objincart).getName()+"</td>");
				out.println("<td>"+discountedprice+"</td>");
				out.println("<td>");
				out.println("</td>");
				
			}
			else if(objincart instanceof Accessory)
			{
				double discountedprice =((((Accessory)objincart).getPrice()*((Accessory)objincart).getDiscount())/100);
				discountedprice = ((Accessory)objincart).getPrice()-discountedprice;
				totalamount=totalamount+discountedprice;
				out.println("<td>"+((Accessory)objincart).getName()+"</td>");
				out.println("<td>"+discountedprice+"</td>");
				out.println("<td>");
				out.println("</td>");
			}
			out.println("</tr>");
		}
		out.println("<td colspan='2'>Total: $"+totalamount+"</td>");
		//out.println();
		out.println("</table>");
		
		//session.setAttribute("cartlist", Cart.cartlist);
		 
		out.println("</br>");
		out.println("<h2>Enter Your Details</h2>");
		out.println("</br>");
		out.println("<div style='background-color:#f2f2f2; width:50%'>");
		out.println("<form id='customerinfo' action='CreateOrder'>");
		out.println("<h3>   Name: </h3><input type='text' name='fullname' style='width:50%'></input>");
		out.println("</br>");
		out.println("<input type='hidden' name='totalamount' value="+totalamount+"></input>");
		out.println("</br>");
		out.println("<input type='hidden' name='prevpage' value="+"checkoutservlet"+"></input>");
		out.println("</br>");
		out.println("<h3>   Address: </h3><input type='textarea' name='address' style='width:50%'></input>");
		out.println("</br>");
		out.println("<h3>   Credit Card #: </h3><input type='text' name='creditcardnumber' style='width:50%'></input>");
		out.println("</br>");
		out.println("</br>");
		out.println("<input type='submit' value='Pay' style='background-color:#4CAF50; color:white; width:25%'></input>");
		out.println("</br>");
		out.println("</form>");
		out.println("</br>");
		out.println("</div>");	
	}
		
	
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
	 
	 
	 public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
   
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
	
	
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
			out.println("<h1><a href='/'>Smart <span> Portables</span></a></h1>");
			out.println("</header>");
			out.println("<nav>");
			out.println("<ul>");
			out.println("<li class='start selected'><a href='Home'>Home</a></li>");
			out.println("<li class=''><a href='SmartWatches'>Smart Watches</a></li>");
			out.println("<li class=''><a href='Speakers'>Speakers</a></li>");
			out.println("<li class=''><a href='Headphones'>Headphones</a></li>");
			out.println("<li class=''><a href='Phones'>Phones</a></li>");
			out.println("<li class=''><a href='Laptops'>Laptops</a></li>");
			out.println("<li class=''><a href='ExternalStorage'>External Storage</a></li>");
			
				HttpSession session = request.getSession();
				String fname=(String)session.getAttribute("fname");
	
	if (fname == null)
	{
	out.println("<li class=''><a href='Registration'>Register</a></li>");
	out.println("<li class='' ><a href='Login'>Sign in</a></li>");
	}
	  else if (fname.equals("StoreManager")){
		  out.println("<li class=''><a href='Registration'>Register Customer</a></li>");
		  out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");
		  
		  
	  } else
	{
		out.println("<li class=''><a href='#'>Hello  "+fname+"</a></li>");
		out.println("<li class='' ><a href='SignOut'>Sign Out</a></li>");
	}

	
	out.println("<li class='' ><a href='ViewOrders'>View Orders</a></li>");
		
			
			
			out.println("<div align='right'>");
			out.println("<form action='ViewCart'>");
			out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
			
	out.println("<img class='header-image' src='images/combined.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	/*out.println("<article>");
	out.println("<h2>Welcome to Smart Portables</h2>");
	out.println("<p>Smart Portables offer variety of electronics gadgets</p>");
	out.println("<p>Shop at the best market rate</p>");
	out.println("</article>");
	out.println("<article class='expanded'>");
	out.println("<h2>Products Available</h2>");
	out.println("<p>Smart Watches</p>");
	out.println("<p>Speakers</p>");
	out.println("<p>Headphones</p>");
	out.println("<h2>Accessories Available</h2>");
	out.println("<p>Mobile Cover</p>");
	out.println("<p>Laptop Bag</p>");
	out.println("<p>Take two Interactive</p>");
	out.println("</article>");*/
	out.println("</section>");
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
	out.println("<li><a href='SmartWatches'>Smart Watches</a></li>");
	out.println("<li><a href='Speakers'>Speakers</a></li>");
	out.println("<li><a href='Headphones'>Headphones</a></li>");
	out.println("<li><a href='Headphones'>Headphones</a></li>");
	out.println("<li><a href='Phones'>Phones</a></li>");
	out.println("<li><a href='Laptop'>Laptop</a></li>");
	out.println("<li><a href='ExternalStorage'>External Storage</a></li>");
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
	out.println("<li><a href='http://www.w3schools.com/html/default.asp' title='premium templates'>Learn HTML here</a></li>");	
	out.println("<li><a href='http://www.w3schools.com/css/default.asp' title='web hosting'>Learn CSS here</a></li>");	
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
