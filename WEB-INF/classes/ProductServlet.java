import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ProductServlet extends HttpServlet {
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
	

SmartPortablesSerializedDataStore obj1=(SmartPortablesSerializedDataStore) request.getAttribute("obj1");
	
	String product_type=request.getParameter("param1");
	System.out.println(product_type);
	HashMap<String, Product> map = new HashMap<String,Product>();
	HashMap<String, Accessory> map1 = new HashMap<String,Accessory>();
    
	
	if(product_type.equalsIgnoreCase("SmartWatches")){
		map=obj1.hm_smartwatches;
		out.println("<h1> SMART WATCHES </h1>");
	}
	else if(product_type.equalsIgnoreCase("Speakers")){
		map=obj1.hm_speakers;
		out.println("<h1> SPEAKERS </h1>");
	}
	else if(product_type.equalsIgnoreCase("Headphones")){
		map=obj1.hm_headphones;
		out.println("<h1> HEADPHONES </h1>");
	}
	else if(product_type.equalsIgnoreCase("Phones")){
		map=obj1.hm_phones;
		out.println("<h1> PHONES </h1>");
	}
	else if(product_type.equalsIgnoreCase("Laptops")){
		map=obj1.hm_laptops;
		out.println("<h1> LAPTOPS </h1>");
	}
	else if(product_type.equalsIgnoreCase("ExternalStorages")){
		map=obj1.hm_externalstorages;
		System.out.println(map.size());
		out.println("<h1> EXTERNAL STORAGES </h1>");
	}
	else if(product_type.equalsIgnoreCase("Accessories")){
		map1=obj1.hm_accessories;
		System.out.println(map1.size());
		out.println("<h1> ACCESSORIES </h1>");
	}
	
	//DISPLAYING PRODUCTS AND THEIR ACCESSORIES IN CAROUSEL
	for(String key:map.keySet())
	{
		out.println("<div id='container'>");
		out.println("<h3> Name: "+map.get(key).getName()+"</h3>");
		out.println("<img src =images/"+map.get(key).getImage()+" width='25%' height='25%'>");
		out.println("<h5> Price: "+map.get(key).getPrice()+"</h5>");
		out.println("<h5> Retailer: "+map.get(key).getRetailer()+"</h5>");
		out.println("<h5> Condition: "+map.get(key).getCondition()+"</h5>");
		out.println("<h5> Discount: "+map.get(key).getDiscount()+"</h5>");
		if(fname!=null)
		{
		out.println("<form id='prodaddtocart' action='AddToCartServlet'>");
		out.println("<input type='hidden' name='pagename' value="+product_type+"></input>");
		out.println("<button type='submit' name='button' value="+map.get(key).getName()+">Add to cart</button>");
		out.println("</form>");
		out.println("<br>");
		
		out.println("<form id='prodwritereview' action='WriteReviewServlet'>");
		out.println("<input type='hidden' name='product_type' value="+product_type+"></input>");
		out.println("<button type='submit' name='button' value="+map.get(key).getName()+">Write Review</button>");
		out.println("</form>");
		out.println("<br>");
		
		out.println("<form id='prodviewreview' action='ViewReviewServlet'>");
		out.println("<input type='hidden' name='product_name' value="+map.get(key).getName()+"></input>");
		out.println("<button type='submit' name='button' value="+map.get(key).getName()+">View Review</button>");
		out.println("</form>");
		}
		
		ArrayList<Accessory> acclist = map.get(key).getAccessories();
		if(acclist.size()!=0)
			out.println("<h2>Accessories</h2>");
		
		
		//CAROUSEL
		if(acclist.size()!=0){
			out.println("<div align='center'>");
		out.println("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
		 
			out.println("<ol class='carousel-indicators'>");
			out.println("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
			out.println("<li data-target='#myCarousel' data-slide-to='1'></li>");
			out.println("<li data-target='#myCarousel' data-slide-to='2'></li>");
			out.println("</ol>");
			
		out.println("<div class='carousel-inner'>");
			
			out.println("<div class='item active'>");
			out.println("<img src =images/"+acclist.get(0).getImage()+" width='20%' height='20%' align='center'>");
			out.println("<h5 align='center'> Name: "+acclist.get(0).getName()+"</h5>");
			out.println("<h5 align='center'> Price: $"+acclist.get(0).getPrice()+"</h5>");
			if(fname!=null)
			{
			out.println("<form id='accaddtocart' action='AddToCartServlet'>");
			out.println("<input type='hidden' name='pagename' value="+product_type+"></input>");
			out.println("<p align='center'>");
			out.println("<button type='submit' name='button' value="+acclist.get(0).getName()+" >Add to cart</button>");
			out.println("</p>");
			out.println("</form>");
			}
			out.println("</div>");
			
			
			for(int i=1;i<acclist.size();i++)
			{
				out.println("<div class='item'>");
				out.println("<img src =images/"+acclist.get(i).getImage()+" width='20%' height='20%' align='center'>");
				out.println("<h5 align='center'> Name: "+acclist.get(i).getName()+"</h5>");
				out.println("<h5 align='center'> Price: $"+acclist.get(i).getPrice()+"</h5>");
				if(fname!=null)
				{
				out.println("<form id='accaddtocart' action='AddToCartServlet'>");
				out.println("<input type='hidden' name='pagename' value="+product_type+"></input>");
				out.println("<p align='center'>");
				out.println("<button type='submit' name='button' value="+acclist.get(i).getName()+" align='center'>Add to cart</button>");
				out.println("</p>");
				out.println("</form>");
				}
				out.println("</div>");
			}
			
		out.println("</div>");	
		
		out.println("<a class='left carousel-control' href='#myCarousel' data-slide='prev'>");
		out.println("<span class='glyphicon glyphicon-chevron-left'></span>");
		out.println("<span class='sr-only'>Previous</span>");
		out.println("</a>");
		out.println("<a class='right carousel-control' href='#myCarousel' data-slide='next'>");
		out.println("<span class='glyphicon glyphicon-chevron-right'></span>");
		out.println("<span class='sr-only'>Next</span>");
		out.println("</a>");
		out.println("</div>");
		}
		out.println("</div>");
	}
	
	
	//DISPLAYING JUST ACCESSORIES
	for(String key:map1.keySet())
	{
		out.println("<div id='container'>");
		out.println("<h3> Name: "+map1.get(key).getName()+"</h3>");
		out.println("<img src =images/"+map1.get(key).getImage()+" width='25%' height='25%'>");
		out.println("<h5> Price: "+map1.get(key).getPrice()+"</h5>");
		out.println("<h5> Retailer: "+map1.get(key).getRetailer()+"</h5>");
		out.println("<h5> Condition: "+map1.get(key).getCondition()+"</h5>");
		out.println("<h5> Discount: "+map1.get(key).getDiscount()+"</h5>");
		if(fname!=null)
		{
		out.println("<form id='prodaddtocart' action='AddToCartServlet'>");
		out.println("<input type='hidden' name='pagename' value="+product_type+"></input>");
		out.println("<button type='submit' name='button' value="+map1.get(key).getName()+">Add to cart</button>");
		out.println("</form>");
		out.println("</br>");
		out.println("<form id='accwritereview' action='WriteReviewServlet'>");
		out.println("<input type='hidden' name='product_type' value="+product_type+"></input>");
		out.println("<button type='submit' name='button' value="+map1.get(key).getName()+">Write Review</button>");
		out.println("</form>");
		out.println("</br>");
		out.println("<form id='prodviewreview' action='ViewReviewServlet'>");
		out.println("<input type='hidden' name='product_name' value="+map1.get(key).getName()+"></input>");
		out.println("<button type='submit' name='button' value="+map1.get(key).getName()+">View Review</button>");
		out.println("</form>");
		}
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
