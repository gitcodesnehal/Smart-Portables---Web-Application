import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
public class ProductSalesBarChartData {
 
    private static final List<KeyValueSales> barDataListSales;
    //static ArrayList<String> allproductsname = MySQLDataStoreUtilities.getAllProductNames();
    static HashMap<String,Integer> noofitemssoldmap= MySQLDataStoreUtilities.getNoOfItemsSold();
    static {
    	
    	barDataListSales = new ArrayList<ProductSalesBarChartData.KeyValueSales>();
    	for(String s: noofitemssoldmap.keySet())
    	{
    		double price = MySQLDataStoreUtilities.getPriceFromProdName(s);
    		double totalsales = price*noofitemssoldmap.get(s);
    		barDataListSales.add(new KeyValueSales(s,totalsales));
    	}
    	/*barDataList.add(new KeyValue("Russia", 100));
    	barDataList.add(new KeyValue("Canada", 80));
    	barDataList.add(new KeyValue("USA", 75));
    	barDataList.add(new KeyValue("China", 10));
    	barDataList.add(new KeyValue("Brazil", 50));
    	barDataList.add(new KeyValue("Australia", 77));
    	barDataList.add(new KeyValue("India", 32));*/
    }
 
    public static List<KeyValueSales> getBarDataListSales() {
    	//System.out.println(barDataList.size());
        return barDataListSales;
    }
 
    public static class KeyValueSales {
        String key;
        double value;
 
        public KeyValueSales(String key, double value) {
            super();
            this.key = key;
            this.value = value;
        }
 
        public String getKey() {
            return key;
        }
 
        public void setKey(String key) {
            this.key = key;
        }
 
        public double getValue() {
            return value;
        }
 
        public void setValue(int value) {
            this.value = value;
        }
 
    }
 
}
