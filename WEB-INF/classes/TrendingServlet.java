import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TrendingServlet extends HttpServlet {
	SmartPortableUser u=null;
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
				String fname=(String)session.getAttribute("fname");
				String type = (String)session.getAttribute("type");
	
	
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
			//out.println("<li  class=''><a id='smartwatches' href='ProductServlet?param1=smartwatches'>Smart Watches</a></li>");
			//out.println("<li  class=''><a href='ProductServlet?param1=speakers'>Speakers</a></li>");
			//out.println("<li  class=''><a href='ProductServlet?param1=headphones'>Headphones</a></li>");
			//out.println("<li class=''><a href='ProductServlet?param1=phones'>Phones</a></li>");
			//out.println("<li  class=''><a href='ProductServlet?param1=laptops'>Laptops</a></li>");
			//out.println("<li  class=''><a href='ProductServlet?param1=externalstorages'>External Storage</a></li>");
				if(fname!=null && type.equals("StoreManager"))
			{
				out.println("<li class='' ><a href='TrendingServlet'>Trending</a></li>");
				out.println("<li class='' ><a href='StoreManagerReportChoice'>Inventory Report</a></li>");
				out.println("<li class='' ><a href='StoreManagerReportChoiceSales'>Sales Report</a></li>");
				out.println("<li class='' ><a href='DataAnalyticsServlet'>Data Analytics</a></li>");
				out.println("<li class='' ><a href='InsertProductServlet'>Add New Product</a></li>");
				
			}

				
			
				
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

	u=MySQLDataStoreUtilities.getUserDetails(fname);
		
	
	//get Top five most liked products
	
	HashMap<String,Integer> top5likedmap=MongoDBDataStoreUtilities.getTopFiveLiked();
	if(top5likedmap.size()!=0)
	{
		out.println("<h3> Top five most liked products: </h3>");
		out.println("<br>");
		out.println("<table style='width:50%'>");
		out.println("<tr>");
		out.println("<th>Product Name</th>");
		out.println("<th>Product Rating</th>");
		out.println("</tr>");
		
		for(String p : top5likedmap.keySet())
		{
			out.println("<tr>");
			out.println("<td>"+p+"</td>");
			out.println("<td>"+top5likedmap.get(p)+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
	
	HashMap<Integer,Integer> top5zipcodemap=MongoDBDataStoreUtilities.getTopFiveZipcodes();
	if(top5zipcodemap.size()!=0)
	{
		out.println("<h3> Top five zip-codes where maximum number of products sold: </h3>");
		out.println("<br>");
		out.println("<table style='width:50%'>");
		out.println("<tr>");
		out.println("<th>Zip Code</th>");
		out.println("<th>Count</th>");
		out.println("</tr>");
		
		for(int p : top5zipcodemap.keySet())
		{
			out.println("<tr>");
			out.println("<td>"+p+"</td>");
			out.println("<td>"+top5zipcodemap.get(p)+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
	
	/*HashMap<String,Integer> top5mostsoldmap = MongoDBDataStoreUtilities.getTopFiveMostSold();
	if(top5mostsoldmap.size()!=0)
	{
		out.println("<h3> Top five most sold products regardless of the rating: </h3>");
		out.println("<br>");
		out.println("<table style='width:50%'>");
		out.println("<tr>");
		out.println("<th>Product Name</th>");
		out.println("<th>Count</th>");
		out.println("</tr>");
		
		for(String p : top5mostsoldmap.keySet())
		{
			out.println("<tr>");
			out.println("<td>"+p+"</td>");
			out.println("<td>"+top5mostsoldmap.get(p)+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}*/
	
	HashMap<String,Integer> top5mostsoldmap = MongoDBDataStoreUtilities.getTopFiveMostSold();
	if(top5mostsoldmap.size()!=0)
	{
		out.println("<h3> Top five most sold products regardless of the rating: </h3>");
		out.println("<br>");
		out.println("<table style='width:50%'>");
		out.println("<tr>");
		out.println("<th>Product Name</th>");
		out.println("<th>Count</th>");
		out.println("</tr>");
		
		for(String p : top5mostsoldmap.keySet())
		{
			out.println("<tr>");
			out.println("<td>"+p+"</td>");
			out.println("<td>"+top5mostsoldmap.get(p)+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
	}
	
	
	out.println("</section>");
	out.println("<aside class='sidebar'>");
	out.println("<ul>");	
	out.println("<li>");
	out.println("<h4>Products</h4>");
	out.println("<ul>");
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