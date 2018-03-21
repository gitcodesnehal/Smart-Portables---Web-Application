import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class EditOrderServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    int oid = Integer.parseInt(request.getParameter("oid"));
    MySQLDataStoreUtilities.deleteOrder(oid);
    response.sendRedirect("ViewOrdersServlet");
	 }
		 
}