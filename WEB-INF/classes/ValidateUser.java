import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class ValidateUser extends HttpServlet {
  public void doGet(HttpServletRequest request,
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
	
	
    String uid=request.getParameter("uid");
    String password = request.getParameter("password");
    String type = request.getParameter("type");
	
	//String fname=MySQLDataStoreUtilities.getUserName(uid);
	
	boolean flag =MySQLDataStoreUtilities.validateUser(uid,password,type);
	if(flag==true)
	{
		HttpSession hs=request.getSession();
		hs.setAttribute("fname",MySQLDataStoreUtilities.getUserName(uid));
		hs.setAttribute("type",MySQLDataStoreUtilities.getUserType(uid));
		out.println("<h1>Login Successful..!!</h1><br><br> <a href='Home'> Click here to jump to Home page ");
	}
	else
	{
		out.println("<h2>Oops..Incorrect Username/Password..Please verify the credentials..!<br><br>");
		out.println("<a href='Login'><h2>Click here to Login again");
	}
	
    
/* boolean userflag = false;
    for(String id : RegisterUserServlet.hm_user.keySet())
    {
    	
    	if(id.equals(uid))
    	{
    		userflag=true;
    	}
    }
    if(RegisterUserServlet.hm_user.size()==0)
    {
    	out.println("Oops..Incorrect Username/Password..Please verify the credentials..!<br><br>");
		out.println("<a href='Login'><h3>Click here to Login again");
    	
		//response.sendRedirect("Login");
    }
    else if(userflag==false)
    {
		out.println("<h2>Oops..Incorrect Username/Password..Please verify the credentials..!<br><br>");
		out.println("<a href='Login'><h2>Click here to Login again");
    	//response.sendRedirect("Login");
    }
    else if( (RegisterUserServlet.hm_user.get(uid).getPassword().equals(password))  && (RegisterUserServlet.hm_user.get(uid).getType().equals(type)) )
    {
    	HttpSession hs=request.getSession();
    	hs.setAttribute("fname", RegisterUserServlet.hm_user.get(uid).getFname());
    	hs.setAttribute("type", RegisterUserServlet.hm_user.get(uid).getType());
    	response.sendRedirect("Home");
    }
    else
    {
    	out.println("Oops..Incorrect Username/Password..Please verify the credentials..!<br><br>");
		out.println("<a href='Login'><h3>Click here to Login again");
		//response.sendRedirect("Login");
    }
    */
    
  }
}