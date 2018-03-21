import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.util.*;

public class ViewDataAnalytics extends HttpServlet {
	SmartPortableUser u=null;
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
	
	
	String filters[]=request.getParameterValues("querycheckbox");
	String productnamedropdown = request.getParameter("productnamedropdown");
	int price=0;
	if(request.getParameter("price")!="")
	{
		 price = Integer.parseInt(request.getParameter("price"));
	}	
	String compareprice = request.getParameter("compareprice");
	int reviewratedropdown=0;
	if(request.getParameter("reviewratedropdown")!=null)
	{
		 reviewratedropdown = Integer.parseInt(request.getParameter("reviewratedropdown"));
	}
	String comparerating = request.getParameter("comparerating");
	String city = request.getParameter("city");
	String groupbydropdown = request.getParameter("groupbydropdown");
	String countordetail = request.getParameter("countordetail");
	
	boolean filterbyprodname=false;
	boolean filterbyprodprice=false;
	boolean filterbyrate=false;
	boolean filterbycity =false;
	boolean filterbygroupby = false;
	
	

	MongoDBDataStoreUtilities.getConnection();
	BasicDBObject query = new BasicDBObject();
	List<BasicDBObject> andquery = new ArrayList<BasicDBObject>();
	DBObject group = null;
	if(filters!=null)
	{
		for(int i=0;i<filters.length;i++)
		{
			System.out.println(filters[i]);
			switch(filters[i])
			{
			//PRODUCT NAME:
			case "productname":
				filterbyprodname=true;
				if(productnamedropdown.equals(""))
				{
					ArrayList<String> prodnamelist=MySQLDataStoreUtilities.getAllProductNames();
					for(String pn : prodnamelist)
						andquery.add(new BasicDBObject("product_model_name",pn));
				}
			
			//PRODUCT PRICE
			case "productprice":
				filterbyprodprice=true;
				if(compareprice==null)
				{
					
				}
				else if(compareprice.equals("priceequals"))
				{
					System.out.println("inside price equal");
					andquery.add(new BasicDBObject("product_price", price));
				}
				else if(compareprice.equals("pricegreaterthan"))
				{
					andquery.add(new BasicDBObject("product_price", new BasicDBObject("$gt",price)));
				}
				else
				{
					andquery.add(new BasicDBObject("product_price", new BasicDBObject("$lt",price)));
				}
				break;
			// REVIEW RATE
			case "reviewrating":
				filterbyrate=true;
				if(comparerating==null)
				{
					if(countordetail==null)
					{
						
					}
					else if(countordetail.equals("wantcount"))
					{
						andquery.add(new BasicDBObject("$group", "product_model_name"));
					}
				}
				else{
				if(comparerating.equals("rateequals"))
				{
					andquery.add(new BasicDBObject("rate", reviewratedropdown));
					
				}
				else
				{
					System.out.println("inside prate gt");
					andquery.add(new BasicDBObject("rate", new BasicDBObject("$gt",reviewratedropdown)));
				}}
				break;
			// RETAILER CITY
			case "retailercity":
				filterbycity=true;
				andquery.add(new BasicDBObject("retailer_city", city));
				break;
			
			//GROUP BY
			case "groupby":
				filterbygroupby=true;
				if(countordetail.equals("wantcount"))
				{
					DBObject groupfield = new BasicDBObject("_id",0);
					groupfield.put("_id", groupbydropdown);
					groupfield.put("count", new BasicDBObject("$sum",1));
					group = new BasicDBObject("$group",groupfield);
				}
				
			}
		}
	}
	

	//output of DA1
	if(filterbyprodname && filterbyrate && productnamedropdown.equals("") && comparerating==null)
	{
		query.put("$or", andquery);
		DBCursor dbCursor = MongoDBDataStoreUtilities.myReviews.find(query);
		out.println("<table>");
		out.println("<h2>Products and it's Ratings</h2>");
		while(dbCursor.hasNext())
		{
			
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			
			out.println("<tr>");
			out.println("<td>");
			out.println("Product Name");
			out.println("</td>");
			out.println("<td>");
			out.println(bobj.getString("product_model_name"));
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("Review Rate");
			out.println("</td>");
			out.println("<td>");
			out.println(bobj.getInt("rate"));
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td>");
			out.println("Review");
			out.println("</td>");
			out.println("<td>");
			out.println(bobj.getString("reviewtext"));
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("     ");
			out.println("</tr>");
			}
		out.println("</table>");
		
	}
	
	//output of DA 2,3,5
	else{
	query.put("$and", andquery);
	System.out.println(query.toString());
	
	DBCursor dbCursor = MongoDBDataStoreUtilities.myReviews.find(query);


	out.println("<table>");
	out.println("<h2>Reviews</h2>");
	while(dbCursor.hasNext())
	{
		System.out.println("inside while");
		BasicDBObject bobj = (BasicDBObject) dbCursor.next();
		
		out.println("<tr>");
		out.println("<td>");
		out.println("Product Name");
		out.println("</td>");
		out.println("<td>");
		out.println(bobj.getString("product_model_name"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Product Price");
		out.println("</td>");
		out.println("<td>");
		out.println(bobj.getString("product_price"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("City");
		out.println("</td>");
		out.println("<td>");
		out.println(bobj.getString("retailer_city"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Review Rate");
		out.println("</td>");
		out.println("<td>");
		out.println(bobj.getInt("rate"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Review Date");
		out.println("</td>");
		out.println("<td>");
		out.println(bobj.getString("reviewdate"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>");
		out.println("Review");
		out.println("</td>");
		out.println("<td>");
		out.println(bobj.getString("reviewtext"));
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("     ");
		out.println("</tr>");
		}
	out.println("</table>");

		
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
	}
