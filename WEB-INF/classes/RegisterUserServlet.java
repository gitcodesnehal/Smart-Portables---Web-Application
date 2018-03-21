import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.Serializable;


public class RegisterUserServlet extends HttpServlet implements Serializable {
	
  public static HashMap<String,SmartPortableUser> hm_user = new HashMap<String,SmartPortableUser>();
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
			
				//HttpSession session = request.getSession();
				//String fname1=(String)session.getAttribute("fname");
	
	
	out.println("<li class=''><a href='Registration'>Register</a></li>");
	out.println("<li class='' ><a href='Login'>Sign in</a></li>");
	
	
		//out.println("<li class=''><a href='#'>Hello  "+fname1+"</a></li>");
		//out.println("<li class='' ><a href='SignOut'>Sign Out</a></li>");


	
	out.println("<li class='' ><a href='ViewOrders'>View Orders</a></li>");
		
			
			
			out.println("<div align='right'>");
			out.println("<form action='ViewCart'>");
			out.println("<button type='submit' style='background-color:transparent'><img src='images/cart.png' width = '60px' height = '63px'></button>");
			out.println("</form>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
	
    
    String type = request.getParameter("type");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    String uid = request.getParameter("uid");
    String emailid = request.getParameter("emailid");
    String password = request.getParameter("password");
    
    SmartPortableUser newuser = new SmartPortableUser(fname,lname,uid,emailid,password,type);
    //hm_user.put(uid, newuser);
	MySQLDataStoreUtilities.insertUser(uid,password,type,fname,lname,emailid); 
    
   	
  
    out.println("<html>");
    out.println("<h3>Successfully Registered as a "+type+" !!</h3>");
    out.println("</html>");
	out.println("<a href='Login'>Click Here to Login >");
    //response.sendRedirect("Home");

    
  }
  
  /* static void  writeUserDataStore(){

	    try{
	    File userDataStore=new File("C:/data_from_forms/UserDataStore");
	    FileOutputStream fos=new FileOutputStream(userDataStore);
	    ObjectOutputStream oos=new ObjectOutputStream(fos);

		
	        oos.writeObject(hm_user);
	        oos.flush();
	        oos.close();
	        fos.close();
		
	    }catch(Exception e){
			
		}

	}
  
  static void readUserDataStore() {

	   
	    try{
	        File userDataStore=new File("C:/data_from_forms/UserDataStore");
	        FileInputStream fis=new FileInputStream(userDataStore);
	        ObjectInputStream ois=new ObjectInputStream(fis);

	        HashMap<String,SmartPortableUser> mapInFile=(HashMap<String,SmartPortableUser>)ois.readObject();
	        ois.close();
	        fis.close();
	        
	    }catch(Exception e){
	    	
	    }

	} */
  
}