import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AddUpdateDeleteProductNavigation extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String navigateto=request.getParameter("navigateto");
    
    
    if(navigateto.equals("addproduct"))
    {
    	response.sendRedirect("InsertProductServlet");
    }
    else if(navigateto.equals("deleteproduct"))
    {
    	response.sendRedirect("DeleteProductServlet");
    }
    else
    {
    	response.sendRedirect("UpdateProductServlet");
    }
    
    
    
  }
}