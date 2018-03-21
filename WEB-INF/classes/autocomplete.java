import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;


public class autocomplete extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String action = request.getParameter("action");
    String searchId = request.getParameter("searchId");
   // System.out.println("action is: "+action);
    //System.out.println("searchid is: "+searchId);
    	StringBuffer sb = new StringBuffer();
    	boolean namesAdded=false;
    	if(action.equals("complete"))
    	{
    		if(!searchId.equals(""))
    		{
    			AjaxUtility a = new AjaxUtility();
    			try {
					sb=a.readdata(searchId);
				} 
				catch (SQLException e) {
					
					e.printStackTrace();
				}
    			if(sb!=null || !sb.equals(""))
    			{
    				namesAdded=true;
    			}
    			if(namesAdded)
    			{
    				//System.out.println("namesAdded");
    				response.setContentType("text/xml");
    				response.getWriter().write("<products>"+sb.toString()+"</products>");
    			}
    		}
    	}
    	else if(action.equals("lookup"))
    	{
    		request.setAttribute("action", action);
    		request.setAttribute("searchId", searchId);
    		request.getRequestDispatcher("LookupProduct").forward(request, response);
    		//response.sendRedirect("LookupProduct");
    	}
    //}
    
    
    
    
  }
}