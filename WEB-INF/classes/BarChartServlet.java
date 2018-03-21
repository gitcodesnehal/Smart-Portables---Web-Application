
import java.io.IOException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 

public class BarChartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 

    public BarChartServlet() {
        super();
    }
 

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<ProductQuantityBarChartData.KeyValue> barDataList = ProductQuantityBarChartData.getBarDataList();
        request.setAttribute("barDataList", barDataList);
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher("/barchart.jsp");
        requestDispatcher.forward(request, response);
    }
 
    
 
}