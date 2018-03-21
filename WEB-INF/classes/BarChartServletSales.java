
import java.io.IOException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 

public class BarChartServletSales extends HttpServlet {
    private static final long serialVersionUID = 1L;
 

    public BarChartServletSales() {
        super();
    }
 

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<ProductSalesBarChartData.KeyValueSales> barDataListSales = ProductSalesBarChartData.getBarDataListSales();
        request.setAttribute("barDataListSales", barDataListSales);
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher("/barchartsales.jsp");
        requestDispatcher.forward(request, response);
    }
 
    
 
}