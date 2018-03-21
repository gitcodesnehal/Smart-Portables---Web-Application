import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DataAnalyticsServlet extends HttpServlet {
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
			
	//out.println("<img class='header-image' src='images/combined.jpg' width = '100%' height = '100%' alt='Index Page Image' />");
	out.println("<div id='body'>");
	out.println("<section id='content'>");
	
	out.println("<h1> Data Analytics on Review</h1>");
	out.println("<form id='datanalytics' action='ViewDataAnalytics'");
	out.println("<table>");
	
	//PRODUCT NAME 
	out.println("<tr>");
	out.println("<td>");
	out.println("<input type='checkbox' name='querycheckbox' value='productname'/> Select");
	out.println("</td>");
	out.println("<td>");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("Product name");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	
	ArrayList<String> productnameslist = MySQLDataStoreUtilities.getAllProductNames();
	
	out.println("<select name='productnamedropdown'>");
	out.println("<option value="+""+">"+""+"</option>");
	for(String productname: productnameslist)
	{
		out.println("<option value="+productname+">"+productname+"</option>");
	}
	out.println("</select>");
	out.println("</td>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("</tr");
	
	//PRODUCT PRICE
	out.println("<tr>");
	out.println("<td>");
	out.println("<input type='checkbox' name='querycheckbox' value='productprice'/> Select");
	out.println("</td>");
	out.println("<td>");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("Product price");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("</td>");
	out.println("<td>");
	out.println("<input type='text' name='price' />");
	out.println("</td>");
	out.println("<td>");
	out.println("<input type='radio' name='compareprice' value='priceequals'/> Equals");
	out.println("<input type='radio' name='compareprice' value='pricegreaterthan'/> Greater Than");
	out.println("<input type='radio' name='compareprice' value='pricelessthan'/> Less Than");
	out.println("</td>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("</tr");
	
	
	
	//REVIEW RATE
	out.println("<tr>");
	out.println("<td>");
	out.println("<input type='checkbox' name='querycheckbox' value='reviewrating'> Select");
	out.println("<td>");
	out.println("<td>");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("Review Rating");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("<select name='reviewratedropdown'>");
	out.println("<option value='1'>"+1+"</option>");
	out.println("<option value='2'>"+2+"</option>");
	out.println("<option value='3'>"+3+"</option>");
	out.println("<option value='4'>"+4+"</option>");
	out.println("<option value='5'>"+5+"</option>");
	out.println("</select>");
	out.println("</td>");
	out.println("<td>");
	out.println("<input type='radio' name='comparerating' value='rateequals'/> Equals");
	out.println("<input type='radio' name='comparerating' value='rategreaterthan'/> Greater Than");
	out.println("</td>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("</tr");
	
	
	
	
	//RETAILER CITY
	out.println("<tr>");
	out.println("<td>");
	out.println("<input type='checkbox' name='querycheckbox' value='retailercity'> Select");
	out.println("</td>");
	out.println("<td>");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("Retailer City");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("</td>");
	out.println("<td>");
	out.println("<input type='text' name='city' />");
	out.println("</td>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("</tr");
	
	//GROUP BY
	out.println("<tr>");
	out.println("<td>");
	out.println("<input type='checkbox' name='querycheckbox' value='groupby'> Group by");
	out.println("</td>");
	out.println("<td>");
	out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
	out.println("<select name='groupbydropdown'>");
	out.println("<option value='city'>"+"city"+"</option>");
	out.println("<option value='product_name'>"+"product_name"+"</option>");
	out.println("<option value='zipcode'>"+"zipcode"+"</option>");
	out.println("</select>");
	out.println("</td>");
	out.println("<td>");
	out.println("<input type='radio' name='countordetail' value='wantcount'/> Count");
	out.println("<input type='radio' name='countordetail' value='wantdetail'/> Detail");
	out.println("</td>");
	out.println("<br>");
	out.println("<br>");
	out.println("<br>");
	out.println("</tr>");
	
	
	out.println("</table>");
	out.println("<input type='submit' value='Find Data'>");
	
	out.println("</form>");
	
	
	
	
	
	
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
