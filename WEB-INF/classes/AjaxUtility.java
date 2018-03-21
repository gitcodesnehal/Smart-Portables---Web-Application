import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AjaxUtility {
	
	static Connection conn = null;
	public static void getConnection()
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");			
		}
		
		catch(Exception e)
		{
		
		}	
		
	}
	
	public static HashMap<String,Product> getData() throws SQLException
	{
		HashMap<String,Product> hm = new HashMap<String,Product>();
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from products";
		ResultSet rs=stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{
			Product p = new Product(rs.getString("productname"),rs.getString("productname"),rs.getDouble("price"),rs.getString("image"),rs.getString("retailer"),rs.getString("retailercity"),rs.getInt("retailerzipcode"),rs.getString("retailerstate"),rs.getString("manufacturer"),rs.getString("cond"),rs.getDouble("discount"),rs.getDouble("rebates"),new ArrayList<Accessory>());
			//System.out.println(p.toString());
			hm.put(rs.getString("productname"), p);
		}
		return hm;
	}
	
	public StringBuffer readdata(String searchId) throws SQLException
	{
		StringBuffer sb = new StringBuffer();
		HashMap<String,Product> data;
		data=getData();
		Iterator it = data.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pi =(Map.Entry)it.next();
			Product p=(Product)pi.getValue();
			if(p.getName().toLowerCase().startsWith(searchId))
			{
				
				sb.append("<product>");
				sb.append("<id>"+p.getId()+"</id>");
				sb.append("<productName>"+p.getName()+"</productName>");
				sb.append("</product>");
			}
			
		}
		System.out.println(sb);
		return sb;
	}

}
