import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

public class ValidateLogin extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
		  
	response.setContentType("text/html;charset=UTF-8");
    
			String username= request.getParameter("username");
			String password=request.getParameter("password");
			String ctype=request.getParameter("ctype");
			
			File obj=new File("C:/data_from_forms/Data.txt");
			FileReader reader=new FileReader(obj);
			BufferedReader in=new BufferedReader(reader);  
			String aks[],temp1,temp2,temp3;
			int i=0;
			String line=in.readLine();
			while(line!=null)
			{
				aks=line.split(" ");
				while(aks[i+2]!=null)
				{
				temp1=aks[i];
				temp2=aks[i+1];
				temp3=aks[i+2];
				if(temp1.equals(username) && temp2.equals(password) && temp3.equals(ctype))
				{
				response.sendRedirect("Home");
				}
			line=in.readLine();
				}
		}
		
	//String errormsg="Username,Password  and Customer Type do not match. Please re-enter";
    //request.setAttribute("errormsg",errormsg);
    //RequestDispatcher obj1=request.getRequestDispatcher("Login");
    //obj1.forward(request,response);
	response.sendRedirect("Login");
	
	
	  }
}