import java.util.ArrayList;
import java.util.List;
 
public class ProductQuantityBarChartData {
 
    private static final List<KeyValue> barDataList;
    static ArrayList<String> allproductsname = MySQLDataStoreUtilities.getAllProductNames();
    static {
    	
    	barDataList = new ArrayList<ProductQuantityBarChartData.KeyValue>();
    	for(String s: allproductsname)
    	{
    		int quant = MySQLDataStoreUtilities.getQuantityFromProdName(s);
    		barDataList.add(new KeyValue(s,quant));
    	}
    	/*barDataList.add(new KeyValue("Russia", 100));
    	barDataList.add(new KeyValue("Canada", 80));
    	barDataList.add(new KeyValue("USA", 75));
    	barDataList.add(new KeyValue("China", 10));
    	barDataList.add(new KeyValue("Brazil", 50));
    	barDataList.add(new KeyValue("Australia", 77));
    	barDataList.add(new KeyValue("India", 32));*/
    }
 
    public static List<KeyValue> getBarDataList() {
        return barDataList;
    }
 
    public static class KeyValue {
        String key;
        int value;
 
        public KeyValue(String key, int value) {
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
 
        public int getValue() {
            return value;
        }
 
        public void setValue(int value) {
            this.value = value;
        }
 
    }
 
}
