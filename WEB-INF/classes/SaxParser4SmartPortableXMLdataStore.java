import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.Serializable;
import java.util.*;


public class SaxParser4SmartPortableXMLdataStore extends DefaultHandler {
     Product product;
    Accessory accessory;
    static List<Product> products;
    static List<Accessory> accessories;
    String productXmlFileName;
    String elementValueRead;
    String id;
    
    public SaxParser4SmartPortableXMLdataStore(String productXmlFileName) {
        this.productXmlFileName = productXmlFileName;
        products = new ArrayList<Product>();
        accessories = new ArrayList<Accessory>();
        parseDocument();
        //prettyPrint();
        storeProductSQL();
    }


    private void parseDocument() {
    	//System.out.println("pardedoc");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
        	//System.out.println(productXmlFileName);
            SAXParser parser = factory.newSAXParser();	
            parser.parse(productXmlFileName,this);
        } catch (ParserConfigurationException e) {
            //System.out.println("ParserConfig error");
        } catch (SAXException e) {
            //System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            //System.out.println(e.getMessage());
        }
    }

    private void storeProductSQL()
    {
    	MySQLDataStoreUtilities.getConnection();
    	for (Product p: products)
    	{
			Random rand = new Random();
			int availquantity = rand.nextInt(100);
    		MySQLDataStoreUtilities.insertProducts(p.getName(), p.getPrice(), p.getImage() ,p.getRetailer(),p.getRetailercity(),p.getRetailerzipcode(),p.getRetailerstate(),p.getManufacturer(),p.getCondition(),p.getDiscount(),p.getRebates(),"Product",availquantity);

    		
    	}
    	
    	for(Accessory a: accessories)
    	{
    		Random rand = new Random();
			int availquantity = rand.nextInt(100);
			MySQLDataStoreUtilities.insertProducts(a.getName(), a.getPrice(), a.getImage(),a.getRetailer(),a.getRetailercity(),a.getRetailerzipcode(),a.getRetailerstate(),a.getManufacturer(),a.getCondition(),a.getDiscount(),a.getRebates(),"Accessory",availquantity);

    		
    	}
    }

    private void prettyPrint() {
    	//System.out.println("prettuprint");
        for (Product product: products) {
        System.out.println("\t\t image: " + product.image);
        System.out.println("\t\t id: " + product.id);
        System.out.println("\t\t name: " + product.name);
        System.out.println("\t\t condition: " + product.condition);
        System.out.println("\t\t price: " + product.price);	
		System.out.println("\t\t retailer: " + product.retailer);
		System.out.println("\t\t manufacturer: " + product.manufacturer);
		System.out.println("\t\t discount: " + product.discount);
		for (Accessory accessory: product.getAccessories()) 
		{
			System.out.println("\t\t accessory: " + accessory.getName());
		}
        }
        for(Accessory accessory: accessories)
        {
        	System.out.println("\t\t a_image: " + accessory.image);
            System.out.println("\t\t a_name: " + accessory.name);
            System.out.println("\t\t a_condition: " + accessory.condition);
            System.out.println("\t\t a_price: " + accessory.price);	
    		System.out.println("\t\t a_retailer: " + accessory.retailer);
    		System.out.println("\t\t a_manufacturer: " + accessory.manufacturer);
    		System.out.println("\t\t a_discount: " + accessory.discount);	
        }
    }





    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
    	//System.out.println("startele");
        if (elementName.equalsIgnoreCase("productcategory")) {
        	id=attributes.getValue("id");
        }
        if(elementName.equalsIgnoreCase("product")) {
        	product = new Product();
        	if(attributes.getValue("id")!=null)
        		id=attributes.getValue("id");
        	product.setId(id);
        }
        if(elementName.equalsIgnoreCase("a_accessory")) {
        	accessory = new Accessory();
        }
        if(elementName.equalsIgnoreCase("a_name")) {
        	accessory.setName(attributes.getValue("a_name"));
        }
        if(elementName.equalsIgnoreCase("a_price")) {
        	//System.out.println(attributes.getValue("a_price"));
        	//accessory.setPrice(Double.parseDouble(attributes.getValue("a_price")));
        }
        if(elementName.equalsIgnoreCase("a_condition")) {
        	accessory.setCondition(attributes.getValue("a_condition"));
        }
        if(elementName.equalsIgnoreCase("a_retailer")) {
        	//accessory.setRetailer(attributes.getValue("a_retailer"));
        }
        if(elementName.equalsIgnoreCase("a_retailercity")) {
        	//accessory.setRetailer(attributes.getValue("a_retailer"));
        }
        if(elementName.equalsIgnoreCase("a_retailerzipcode")) {
        	//accessory.setRetailer(attributes.getValue("a_retailer"));
        }
        if(elementName.equalsIgnoreCase("a_retailerstate")) {
        	//accessory.setRetailer(attributes.getValue("a_retailer"));
        }
        if(elementName.equalsIgnoreCase("a_manufacturer")) {
        	//accessory.setRetailer(attributes.getValue("a_manufacturer"));
        }
        if(elementName.equalsIgnoreCase("a_discount")) {
        	//accessory.setDiscount(Double.parseDouble(attributes.getValue("a_discount")));
        }
        if(elementName.equalsIgnoreCase("a_rebates")) {
        	//accessory.setDiscount(Double.parseDouble(attributes.getValue("a_rebates")));
        }
        
        if(elementName.equalsIgnoreCase("name")) {
        	//System.out.println(attributes.getValue("name"));
        	product.setName(attributes.getValue("name"));
        }
        if(elementName.equalsIgnoreCase("price")) {
        	//product.setPrice(Double.parseDouble(attributes.getValue("price")));
        }
        if(elementName.equalsIgnoreCase("condition")) {
        	product.setCondition(attributes.getValue("condition"));
        }
        if(elementName.equalsIgnoreCase("retailer")) {
        	//product.setRetailer(attributes.getValue("retailer"));
        }
        if(elementName.equalsIgnoreCase("retailercity")) {
        	//product.setRetailer(attributes.getValue("retailer"));
        }
        if(elementName.equalsIgnoreCase("retailerzipcode")) {
        	//product.setRetailer(attributes.getValue("retailer"));
        }
        if(elementName.equalsIgnoreCase("retailerstate")) {
        	//accessory.setRetailer(attributes.getValue("a_retailer"));
        }
        if(elementName.equalsIgnoreCase("manufacturer")) {
        	//product.setRetailer(attributes.getValue("manufacturer"));
        }
        if(elementName.equalsIgnoreCase("discount")) {
        	//product.setDiscount(Double.parseDouble(attributes.getValue("discount")));
        }
        if(elementName.equalsIgnoreCase("rebates")) {
        	//product.setDiscount(Double.parseDouble(attributes.getValue("rebates")));
        }
        if(elementName.equalsIgnoreCase("accessory")){
        	String acc_name=attributes.getValue("accessory");
        	/*for(Accessory accessory: accessories)
        	{
        		System.out.println(attributes.getValue("accessory"));
        		if(accessory.getName().equals(acc_name))
        		{
        			System.out.println("fdf"+product.getName());
        			System.out.println(accessory.getName());
        			product.accessories.add(accessory);
        		}
        	}*/
        }
        

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
    	//System.out.println("r4dr");
        if (element.equals("product")) {
            products.add(product);
	    return;
        }
        if (element.equals("productcategory")) {
	    return;
        }
        if (element.equals("a_accessory")) {
        	accessories.add(accessory);
    	    return;
        }
        if (element.equals("a_accessories")) {
    	    return;
        }
        if (element.equalsIgnoreCase("a_image")) {
            accessory.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("a_name")) {
            accessory.setName(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("a_price")){
            accessory.setPrice(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("a_discount")){
            accessory.setDiscount(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("a_rebates")){
            accessory.setRebates(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("a_condition")){
            accessory.setCondition(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("a_retailer")){
            accessory.setRetailer(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("a_retailercity")){
            accessory.setRetailercity(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("a_retailerzipcode")){
            accessory.setRetailerzipcode(Integer.parseInt(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("a_retailerstate")){
            accessory.setRetailerstate(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("a_manufacturer")){
            accessory.setManufacturer(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            product.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("id")) {
            product.setId(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            product.setName(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("accessory")){
        	String acc_name=elementValueRead;
        	for(Accessory accessory: accessories)
        	{
        		if(accessory.getName().equals(acc_name))
        		{
        			product.accessories.add(accessory);
        		}
        	}	
	    return;
        }
        if(element.equalsIgnoreCase("price")){
        	//System.out.println(elementValueRead);
            product.setPrice(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("discount")){
        	//System.out.println(elementValueRead);
            product.setDiscount(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("rebates")){
        	//System.out.println(elementValueRead);
            product.setRebates(Double.parseDouble(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("condition")){
            product.setCondition(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("retailer")){
            product.setRetailer(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("retailercity")){
            product.setRetailercity(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("retailerzipcode")){
            product.setRetailerzipcode(Integer.parseInt(elementValueRead));
	    return;
        }
        if(element.equalsIgnoreCase("retailerstate")){
            product.setRetailerstate(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("manufacturer")){
            product.setManufacturer(elementValueRead);
	    return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

   /* public static void main(String[] args) {
        new SaxParser4SmartPortableXMLdataStore("ProductCatalog1.xml");

    }*/

}
