import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class RemoveFromCartServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    String obj=request.getParameter("object");
    
    
    for ( int i = 0;  i < Cart.cartlist.size(); i++){
        Object o = Cart.cartlist.get(i);
        if((o.toString()).equals(obj))
        {
        	Cart.cartlist.remove(i);
        }
    }
    
    response.sendRedirect("CartServlet");
        
  }
}