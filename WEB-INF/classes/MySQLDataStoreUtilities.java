import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class MySQLDataStoreUtilities
{
	static Connection conn = null;
	static int count=0;
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

	
	public static void insertUser(String uid,String password,String type,String fname,String lname,String emailid) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(uid,password,type,fname,lname,emailid)"+ "VALUES(?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1,uid);
			pst.setString(2,password);
			pst.setString(3,type);
			pst.setString(4,fname);
			pst.setString(5,lname);
			pst.setString(6,emailid);
			
			pst.execute();
			
			conn.commit();
			pst.close();
			conn.close();
						
		}
		
		catch(Exception e)
		{
		}
	}
	
	public static boolean validateUser(String uid,String password,String type)
	{
		boolean flag=false;
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
		
			Statement stmt = conn.createStatement();
			String validateUserInfo = "SELECT uid,password,type FROM Registration;";
			ResultSet rs=stmt.executeQuery(validateUserInfo);
			while(rs.next())
			{
				if(uid.equals(rs.getString("uid")) && password.equals(rs.getString("password")) && type.equals(rs.getString("type")))
					flag=true;
				//else
				//	  flag=false;
			}
			conn.commit();
			rs.close();
			stmt.close();
			conn.close();
			}
			catch(Exception e)
			{
			}
		return flag;	

	}
	
	
		public static void insertOrder(int oid, String fname, double totalamount, String address, long creditcardnumber,String orderdate,String deliverydate) 
		{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String insertIntoCustomerOrdersQuery = "INSERT INTO CustomerOrders(oid,fname,totalamount,address,creditcardnumber,orderdate,deliverydate)"+ "VALUES(?,?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrdersQuery);
			pst.setInt(1,oid);
			pst.setString(2,fname);
			pst.setDouble(3, totalamount);
			pst.setString(4,address);
			pst.setLong(5, creditcardnumber);
			pst.setString(6,orderdate);
			pst.setString(7,deliverydate);
			
			pst.execute();
			
			//conn.commit();
			pst.close();
			conn.close();
						
		}
		
		catch(Exception e)
		{
			
		}
		}
		
		public static void deleteOrder(int oid)
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String deleteFromCustomerOrdersQuery = "DELETE FROM customerorders WHERE oid=?;";
			
			PreparedStatement pst = conn.prepareStatement(deleteFromCustomerOrdersQuery);
			pst.setInt(1,oid);
			pst.execute();
			
			pst.close();
			conn.close();
			
			}
			catch(Exception e)
			{
			}
		}
		
		public static void insertProductOrder(String productname,int oid)
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String insertIntoProductOrderQuery = "INSERT INTO ProductOrder(productname,oid)"+ "VALUES(?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertIntoProductOrderQuery);
			pst.setString(1,productname);
			pst.setInt(2,oid);			
			pst.execute();
			pst.close();
			conn.close();
			}
			catch(Exception e)
			{
			}
		}
		
	public static void insertNewProduct(String productname, double price,String image,String retailer,String retailercity,int retailerzipcode,String retailerstate, String manufacturer, String cond,double discount,double rebates,String prodtype)
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			Statement stmt = conn.createStatement();
			String checkifempty = "SELECT * FROM products;";
			ResultSet rs=stmt.executeQuery(checkifempty);
				
			
			String insertIntoProductsQuery = "INSERT INTO products(productname,price,image,retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,prodtype)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertIntoProductsQuery);
			pst.setString(1,productname);
			pst.setDouble(2, price);
			pst.setString(3, image);
			pst.setString(4,retailer);
			pst.setString(5,retailercity);
			pst.setInt(6,retailerzipcode);
			pst.setString(7,retailerstate);
			pst.setString(8,manufacturer);
			pst.setString(9, cond);
			pst.setDouble(10, discount);
			pst.setDouble(11, rebates);
			pst.setString(12, prodtype);
			
			pst.execute();
			pst.close();
			/*conn.commit();
			conn.close();*/
						
		}
		
		catch(Exception e)
		{
			
		}
		
	}

		public static void updateProduct(String productname, double price, double discount, double rebates) throws SQLException 
		{
			getConnection();
			String updateproduct = "update products set price=?,discount=?,rebates=? where productname=?;";
			PreparedStatement pst = conn.prepareStatement(updateproduct);
			pst.setDouble(1,price);	
			pst.setDouble(2,discount);
			pst.setDouble(3,rebates);
			pst.setString(4,productname);
			pst.execute();				
			//conn.commit();
			pst.close();
			conn.close();

			
			
		}
			
	
	public static String getUserName(String uid)
	{
		String fname1=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String getUser = "SELECT * FROM Registration WHERE uid='"+uid+"';";
			
			PreparedStatement pst = conn.prepareStatement(getUser);
			//pst.setString(1,uid);
			
			ResultSet rs=pst.executeQuery(getUser);
			while(rs.next())
			{
				
				 fname1=rs.getString("fname");
				
			}
			
			conn.commit();
			rs.close();
			pst.close();
			conn.close();			
			}
		catch(Exception e)
		{
		}
		return fname1;		
	}
	
		public static String getUserType(String uid)
		{
			String type1=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String getUser = "SELECT * FROM Registration WHERE uid='"+uid+"';";
			
			PreparedStatement pst = conn.prepareStatement(getUser);
			//pst.setString(1,uid);
			
			ResultSet rs=pst.executeQuery(getUser);
			while(rs.next())
			{
				
				 type1=rs.getString("type");
				
			}
			
			conn.commit();
			rs.close();
			pst.close();
			conn.close();			
			}
		catch(Exception e)
		{
		}
		return type1;		
		}
		
		
		//get userdetails given fname
		public static SmartPortableUser getUserDetails(String fname)
		{
		SmartPortableUser spu=null;
		String uid1 = null, password1 = null,type1 = null,fname1 = null,lname1 = null,emailid1=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String getUser = "SELECT * FROM registration WHERE fname='"+fname+"';";
			
			PreparedStatement pst = conn.prepareStatement(getUser);
			//pst.setString(1,uid);
			
			ResultSet rs=pst.executeQuery(getUser);
			while(rs.next())
			{
				 uid1=rs.getString("uid");
				 password1=rs.getString("password");
				 type1=rs.getString("type");
				 fname1=rs.getString("fname");
				 lname1=rs.getString("lname");
				 emailid1=rs.getString("emailid");
				 	
			}
			spu = new SmartPortableUser(fname1,lname1,uid1,emailid1,password1,type1);
			
			conn.commit();
			rs.close();
			pst.close();
			conn.close();			
			}
		catch(Exception e)
		{
		}
		return spu;		
		}
		
		
		public static ArrayList<Orders> getOrderDetails(String fname)
		{
		ArrayList<Orders> orders = new ArrayList<Orders>();
		//String uid1 = null, password1 = null,type1 = null,fname1 = null,lname1 = null,emailid1=null;
		int oid1; 
		String fname1=null;
		double totalamount1;
		String address1=null;
		long creditcardnumber1; 
		String orderdate1=null; 
		String deliverydate1=null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
			String getOrders = "SELECT * FROM CustomerOrders WHERE fname='"+fname+"';";
			
			PreparedStatement pst = conn.prepareStatement(getOrders);
			//pst.setString(1,uid);
			
			ResultSet rs=pst.executeQuery(getOrders);
			while(rs.next())
			{
				 oid1=rs.getInt("oid");
				 fname1=rs.getString("fname");
				 totalamount1=rs.getDouble("totalamount");
				 address1=rs.getString("address");
				 creditcardnumber1=rs.getLong("creditcardnumber");
				 orderdate1=rs.getString("orderdate");
				 deliverydate1=rs.getString("deliverydate");
				 SmartPortableUser spu1 = getUserDetails(fname1);
				 Orders o = new Orders(oid1, spu1, new ArrayList<Object>(),totalamount1,address1,creditcardnumber1);
				 o.setOrderdate(orderdate1);
				 o.setDeliverydate(deliverydate1);
				 orders.add(o);
				 	
			}
			//spu = new SmartPortableUser(fname1,lname1,uid1,emailid1,password1,type1);
			
			conn.commit();
			rs.close();
			pst.close();
			conn.close();			
			}
		catch(Exception e)
		{
			
		}
		return orders;		
		}
		
		public static void insertProducts(String productname, double price, String image, String retailer,String retailercity,int retailerzipcode,String retailerstate, String manufacturer, String cond,double discount,double rebates,String prodtype, int availquantity)
	{
		
		try
		{
			/*Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartportables","root","root");*/
			Statement stmt = conn.createStatement();
			String checkifempty = "SELECT * FROM products;";
			ResultSet rs=stmt.executeQuery(checkifempty);
			if(rs.next())
			{
				count++;
			}
			if(count <70)
			{		
			
			String insertIntoProductsQuery = "INSERT INTO products(productname,price,image,retailer,retailercity,retailerzipcode,retailerstate,manufacturer,cond,discount,rebates,prodtype,availquantity)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement pst = conn.prepareStatement(insertIntoProductsQuery);
			pst.setString(1,productname);
			pst.setDouble(2, price);
			pst.setString(3, image);
			pst.setString(4,retailer);
			pst.setString(5,retailercity);
			pst.setInt(6,retailerzipcode);
			pst.setString(7,retailerstate);
			pst.setString(8,manufacturer);
			pst.setString(9, cond);
			pst.setDouble(10, discount);
			pst.setDouble(11, rebates);
			pst.setString(12, prodtype);
			pst.setInt(13, availquantity);
			
			pst.execute();
			pst.close();
			/*conn.commit();
			conn.close();*/
			}
			
						
		}
		
		catch(Exception e)
		{
			//System.out.println(e);
		}
		
	}
	
		public static void deleteProduct(String productname) {
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
				String deleteproduct = "DELETE FROM products where productname=?;";
				
				PreparedStatement pst = conn.prepareStatement(deleteproduct);
				pst.setString(1,productname);				
				pst.execute();				
				//conn.commit();
				pst.close();
				conn.close();
							
			}
			
			catch(Exception e)
			{
				
			}
			
		}
	
	
	public static String getTypeFromProdName(String productname)
	{
		//ArrayList<String> productnameslist = new ArrayList<String>();
		String prodtype=null;
		getConnection();
		PreparedStatement  pst;
		try {
			String gettypefromprodname = "SELECT prodtype FROM products where productname=?;";
			pst = conn.prepareStatement(gettypefromprodname);
			pst.setString(1, productname);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				prodtype = rs.getString("prodtype");
				//System.out.println(price);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prodtype;
	}

	public static Product getProductObjectByName(String searchId) throws SQLException
	{
		Product p=null;
		getConnection();
		PreparedStatement  pst;
		String getproduct="select * from products where productname=?;";
		pst=conn.prepareStatement(getproduct);
		pst.setString(1, searchId);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			p = new Product(rs.getString("productname"),rs.getString("productname"),rs.getDouble("price"),rs.getString("image"),rs.getString("retailer"),rs.getString("retailercity"),rs.getInt("retailerzipcode"),rs.getString("retailerstate"),rs.getString("manufacturer"),rs.getString("cond"),rs.getDouble("discount"),rs.getDouble("rebates"),new ArrayList<Accessory>());
		}
		pst.close();
		conn.close();
		return p;
	}
	
	
	public static HashMap<String,Product> getData() throws SQLException
	{
		HashMap<String,Product> productmap = new HashMap<String,Product>();
		Product p=null;
		getConnection();
		PreparedStatement  pst;
		String getproducts="select * from products;";
		pst=conn.prepareStatement(getproducts);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			p = new Product(rs.getString("productname"),rs.getString("productname"),rs.getDouble("price"),rs.getString("image"),rs.getString("retailer"),rs.getString("retailercity"),rs.getInt("retailerzipcode"),rs.getString("retailerstate"),rs.getString("manufacturer"),rs.getString("cond"),rs.getDouble("discount"),rs.getDouble("rebates"),new ArrayList<Accessory>());
			productmap.put(rs.getString("productname"), p);
		}
		pst.close();
		conn.close();		
		return productmap;
	}

	

		public static HashMap<String,Integer> getTopFiveMostSold()
			{
			HashMap<String,Integer> top5mostsoldmap = new HashMap<String,Integer>();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
				//String deleteFromCustomerOrdersQuery = "DELETE FROM CustomerOrders where oid=?;";
				String gettopfivequery = "SELECT productname,count(oid) as count_oid FROM ProductOrder group by productname limit 5;";
				
				PreparedStatement pst = conn.prepareStatement(gettopfivequery);
				//pst.setString(1,uid);
				
				ResultSet rs=pst.executeQuery(gettopfivequery);
				while(rs.next())
				{
					 top5mostsoldmap.put(rs.getString("productname"), rs.getInt("count_oid"));
					 	
				}
				
				conn.close();
							
			}
			
			catch(Exception e)
			{
			}
			
			
			return top5mostsoldmap;
			}
		
		public static ArrayList<String> getAllProductNames()
		{
		ArrayList<String> productnameslist = new ArrayList<String>();
		getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String productnamesquery = "SELECT productname FROM products;";
			ResultSet rs=stmt.executeQuery(productnamesquery);
			while(rs.next())
			{
				productnameslist.add(rs.getString("productname"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
		}

		return productnameslist;
	}

	public static double getPriceFromProdName(String productname)
	{
		//ArrayList<String> productnameslist = new ArrayList<String>();
		double price=0;
		getConnection();
		PreparedStatement  pst;
		try {
			String getpricefromprodname = "SELECT price FROM products where productname=?;";
			pst = conn.prepareStatement(getpricefromprodname);
			pst.setString(1, productname);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				price = rs.getDouble("price");
				
			}
			rs.close();
			pst.close();
			conn.close();
		} 
		catch (Exception e)
		{
		}

		return price;
	}
	
	public static double getDiscountFromProdName(String productname)
	{
		//ArrayList<String> productnameslist = new ArrayList<String>();
		double discount=0;
		getConnection();
		PreparedStatement  pst;
		try {
			String getdiscountfromprodname = "SELECT discount FROM products where productname=?;";
			pst = conn.prepareStatement(getdiscountfromprodname);
			pst.setString(1, productname);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				discount = rs.getDouble("discount");
				
			}
			rs.close();
			pst.close();
			conn.close();
		} 
		catch (Exception e)
		{
		}

		return discount;
	}
	
	public static double getRebateFromProdName(String productname)
	{
		//ArrayList<String> productnameslist = new ArrayList<String>();
		double rebate=0;
		getConnection();
		PreparedStatement  pst;
		try {
			String getdiscountfromprodname = "SELECT rebates FROM products where productname=?;";
			pst = conn.prepareStatement(getdiscountfromprodname);
			pst.setString(1, productname);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				rebate = rs.getDouble("rebates");
				
			}
			rs.close();
			pst.close();
			conn.close();
		} 
		catch (Exception e)
		{
		}

		return rebate;
	}
	
	
	public static int getQuantityFromProdName(String productname)
	{
		//ArrayList<String> productnameslist = new ArrayList<String>();
		int availquantity=0;
		getConnection();
		PreparedStatement  pst;
		try {
			String getavailquantityfromprodname = "SELECT availquantity FROM products where productname=?;";
			pst = conn.prepareStatement(getavailquantityfromprodname);
			pst.setString(1, productname);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				availquantity = rs.getInt("availquantity");
				
			}
			rs.close();
			pst.close();
			conn.close();
		} 
		catch (Exception e) 
		{
		}

		return availquantity;
	}
	
	public static ArrayList<String> getAllProductOnSale()
	{
		ArrayList<String> productsonsale = new ArrayList<String>();
		getConnection();
		PreparedStatement  pst;
		try {
			String getproductsonsale = "SELECT productname FROM products WHERE discount<>0.0;";
			pst = conn.prepareStatement(getproductsonsale);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				productsonsale.add(rs.getString("productname"));
			}
			rs.close();
			pst.close();
			conn.close();
		} 
		catch (Exception e) 
		{
		
		}

		return productsonsale;
	}
	
	public static ArrayList<String> getAllProductWithRebates()
	{
		ArrayList<String> productswithrebates = new ArrayList<String>();
		getConnection();
		PreparedStatement  pst;
		try {
			String getproductsonsale = "SELECT productname FROM products where rebates<>0.0;";
			pst = conn.prepareStatement(getproductsonsale);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				productswithrebates.add(rs.getString("productname"));
			}
			rs.close();
			pst.close();
			conn.close();
		} 
		catch (Exception e)
		{
		
		}

		return productswithrebates;
	}
	
	public static HashMap<String,Integer> getNoOfItemsSold()
	{
		
		HashMap<String,Integer> noofitemssoldmap= new HashMap<String,Integer>();
		getConnection();
		PreparedStatement  pst;
		try {
			String getavailquantityfromprodname = "select productname,count(productname) as noofitemssold from ProductOrder group by productname;";
			pst = conn.prepareStatement(getavailquantityfromprodname);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				noofitemssoldmap.put(rs.getString("productname"), rs.getInt("noofitemssold"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch (Exception e) 
		{
		}

		return noofitemssoldmap;
	}
	
	public static TreeMap<String,Integer> getDailyTransaction()
	{
		
		TreeMap<String,Integer> getdailytransaction= new TreeMap<String,Integer>();
		getConnection();
		PreparedStatement  pst;
		try {
			String result = "select concat(substr(orderdate,4,8),substr(orderdate,24,27)) as Txn_day,sum(totalamount) as Total_sales from customerorders group by substr(orderdate,4,8) order by Txn_day asc;";
			pst = conn.prepareStatement(result);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				getdailytransaction.put(rs.getString("Txn_day"), rs.getInt("Total_sales"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch (Exception e) 
		{
		}

		return getdailytransaction;
	}
	
	/* public static int getNumberOfProducts()
	{
		int count=0;
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","Internetsp@92");
				Statement stmt = conn.createStatement();
				String numberOfProducts = "SELECT count(*) FROM products;";
				ResultSet rs = stmt.executeQuery(numberOfProducts);
				
				while(rs.next())
				{
					count++;
				}
				stmt.close();
			}
			catch(Exception e)
			{
			}
		return count;		
	}	*/
	
			
	}
	
	

