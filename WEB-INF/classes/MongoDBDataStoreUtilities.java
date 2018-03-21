import java.io.*;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDataStoreUtilities {

	public static DBCollection myReviews;
	
	public static void getConnection()
	{
		
			MongoClient mongo = new MongoClient("localhost",27017);
			DB db = mongo.getDB("CustomerReviews");
			myReviews = db.getCollection("myReviews");
		
	}
	
	public static void insertReview(String product_model_name,String product_category,double product_price,String retailer_name,int retailer_zip,String retailer_city,String retailer_state,String product_on_sale,String manufacturer_name,String manufacturer_rebates,String userid,int userage,String gender,String useroccupation,int rate,String reviewdate,String reviewtext)
	{
		MongoDBDataStoreUtilities.getConnection();
		BasicDBObject doc = new BasicDBObject("title","myReviews").append("product_model_name", product_model_name).append("product_category", product_category).append("product_price", product_price).append("retailer_name", retailer_name).append("retailer_zip", retailer_zip).append("retailer_city", retailer_city).append("retailer_state", retailer_state).append("product_on_sale", product_on_sale).append("manufacturer_name", manufacturer_name).append("manufacturer_rebates", manufacturer_rebates).append("userid", userid).append("userage", userage).append("gender", gender).append("useroccupation", useroccupation).append("rate", rate).append("reviewdate", reviewdate).append("reviewtext", reviewtext);
		myReviews.insert(doc);
	}
	
	public static HashMap<String,Integer> getTopFiveLiked()
	{
		HashMap<String,Integer> top5likedmap= new HashMap<String,Integer>();
		String product_model_name=null;
		int rate=0;
		
		MongoDBDataStoreUtilities.getConnection();		
		BasicDBObject query = new BasicDBObject();
		int returnLimit=5;
		DBObject sort = new BasicDBObject();
		sort.put("rate", -1);
		DBCursor dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
		while(dbCursor.hasNext())
		{
			BasicDBObject obj = (BasicDBObject) dbCursor.next();
			product_model_name=obj.getString("product_model_name");
					//dbCursor.next().get().toString();
			rate=obj.getInt("rate");
					//dbCursor.next().get("rate").toString();
			top5likedmap.put(product_model_name,rate);
		}
				
		return top5likedmap;
		
	}

	public static HashMap<Integer,Integer> getTopFiveZipcodes()
	{
		HashMap<Integer,Integer> top5zipcodemap=new HashMap<Integer,Integer>();
		int zipcode=0;
		int zipcount=0;
		DBObject groupFields = new BasicDBObject("_id",0);
		groupFields.put("_id", "$retailer_zip");
		groupFields.put("count", new BasicDBObject("$sum",1));
		DBObject group = new BasicDBObject("$group",groupFields);
		
		
		DBObject projectFields = new BasicDBObject("_id",0);
		projectFields.put("retailer_zip", "$_id");
		projectFields.put("Review Count", "$count");
		DBObject project = new BasicDBObject("$project",projectFields);
		
		DBObject sort = new BasicDBObject();
		sort.put("rate", -1);
		
		DBObject limit = new BasicDBObject();
		DBObject orderby = new BasicDBObject();
		
		orderby=new BasicDBObject("$sort",sort);
		
		limit=new BasicDBObject("$limit",5);
		AggregationOutput aggregate=myReviews.aggregate(group,project,orderby,limit);
		
		for(DBObject result:aggregate.results())
		{
			BasicDBObject bobj = (BasicDBObject)result;
			zipcode=bobj.getInt("retailer_zip");
			zipcount=bobj.getInt("Review Count");
			top5zipcodemap.put(zipcode, zipcount);
					
		}
	
		return top5zipcodemap;
	}
	
	public static HashMap<String,Integer> getTopFiveMostSold()
	{
		HashMap<String,Integer> top5mostsoldmap = new HashMap<String,Integer>();
		String prodname=null;
		int prodcount=0;
		DBObject groupFields = new BasicDBObject("_id",0);
		groupFields.put("_id", "$product_model_name");
		groupFields.put("count", new BasicDBObject("$sum",1));
		DBObject group = new BasicDBObject("$group",groupFields);
		
		//System.out.println("count is"+group.);
		
		DBObject projectFields = new BasicDBObject("_id",0);
		projectFields.put("product_model_name", "$_id");
		projectFields.put("Product Count", "$count");
		DBObject project = new BasicDBObject("$project",projectFields);
		
		//DBObject count = new BasicDBObject();
		//sort.put("count", -1);
		
		DBObject limit = new BasicDBObject();
		//DBObject orderby = new BasicDBObject();
		
		//orderby=new BasicDBObject("$count",count);
		
		limit=new BasicDBObject("$limit",5);
		AggregationOutput aggregate=myReviews.aggregate(group,project,limit);
		
		
		for(DBObject result:aggregate.results())
		{
			BasicDBObject bobj = (BasicDBObject)result;
			prodname=bobj.getString("product_model_name");
			prodcount=bobj.getInt("Product Count");
			top5mostsoldmap.put(prodname, prodcount);
					
		}
		
		return top5mostsoldmap;
	}
	
	
	
}