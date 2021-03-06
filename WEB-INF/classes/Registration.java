import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class Registration extends HttpServlet {
  public void doGet(HttpServletRequest request,
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
			out.println("<li  class='start selected'><a id='home' href='Home'>Home</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=smartwatches'>Smart Watches</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=speakers'>Speakers</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=headphones'>Headphones</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=phones'>Phones</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=laptops'>Laptops</a></li>");
			out.println("<li  class=''><a href='ProductServlet?param1=externalstorages'>External Storage</a></li>");
			out.println("<li class='' ><a href='Login'>Sign in</a></li>");
			
				//HttpSession session = request.getSession();
				//String fname1=(String)session.getAttribute("fname");
	
	/*if (fname1 == null)
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
	}*/

	
	out.println("<li class='' ><a href='ViewOrders'>View Orders</a></li>");
		
			
			
			out.println("<div align='right'>");
			out.println("<form action='ViewCart'>");
			out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
			
	// Below Code performs registration
	
	out.println("<h3>Please fill below form to register:</h3>");
	
	out.println("<form action='RegisterUserServlet'>");
	out.println("<table>");
	out.println("<tr>");
	out.println("<td>");
	out.println("Customer Type :");
	out.println("<input type='radio' name='type' value='Customer' required/> Customer");
	out.println("<input type='radio' name='type' value='Salesman' required/> Salesman");
	out.println("<input type='radio' name='type' value='StoreManager' required/> Store Manager");
	out.println("</td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("<td>");
	out.println("First Name :");
	out.println("<input type='text' placeholder ='First name'  name='fname' required/></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("<td>");
	out.println("Last Name  :");
	out.println("<input type='text' placeholder='Last name'  name='lname' required/></td>");
	out.println("</tr>");
	out.println("<tr>");
	
	out.println("<tr>");
	out.println("<td>");
	out.println("User ID  &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:");
	out.println("<input type='text' name='uid' required/></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("<td>");
	out.println("Email Id &nbsp&nbsp&nbsp&nbsp&nbsp :");
	out.println("<input type='text' name='emailid' required/></td>");
	out.println("</tr>");
	
	out.println("<tr>");
	out.println("<td>");
	out.println("Password &nbsp&nbsp:");
	out.println("<input type='password' name='password' required/></td>");
	out.println("</tr>");
	out.println("<tr><td ><input type='submit' value='Register'></td></tr>");
	out.println("</table>");
	out.println("</form>");
	
	
	out.println("</body>");
out.println("</html>");
		
  }
}
