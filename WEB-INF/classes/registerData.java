import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.Serializable;

public class registerData extends HttpServlet {
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
		
	// Displaying the header as it is
	
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
				String fname1=(String)session.getAttribute("fname");
	
	if (fname1 == null)
	{
	out.println("<li class=''><a href='Registration'>Register</a></li>");
	out.println("<li class='' ><a href='Login'>Sign in</a></li>");
	}
	  else if (fname1.equals("StoreManager")){
		  out.println("<li class=''><a href='Registration'>Register Customer</a></li>");
		  out.println("<li class=''><a href='#'>Hello  "+fname1+"</a></li>");
		  
		  
	  } else
	{
		out.println("<li class=''><a href='#'>Hello  "+fname1+"</a></li>");
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
			
	// Store data into text file
	
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String ctype= request.getParameter("ctype");
		
		
		
			Input i1 = new Input(ctype,username,password);
			
			
		
		
				try {
					FileOutputStream f = new FileOutputStream(new File("C:/data_from_forms/FormData.txt"),true);
					ObjectOutputStream o = new ObjectOutputStream(f);
					o.writeObject(i1);
					o.close();
					f.close();
					out.println("<h3>Registration Successful as a "+ctype+"!!!!!!</h3><br><br>");
					}
					
				catch(Exception e)
				{
					
				}
				
				
				out.println("<a href='Login'>Click here to login ");
		
		
		out.println("</body>");
		out.println("</html>");
		
	  }
	 
}
		