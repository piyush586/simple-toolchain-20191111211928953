package com.revolve;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;

public class QueryHelper {

	public List<RawMaterial> getRawMaterials(){
List<RawMaterial> list = new ArrayList<>();
		
		RawMaterial rm = new RawMaterial("Nimonic 105 alloy", 0.0, 1);
		list.add(rm);
		rm = new RawMaterial("Nimonic 80a aluminium alloy", 0.0, 1);
		list.add(rm);
		rm = new RawMaterial("Nickel", 0.0, 1);
		list.add(rm);
		rm = new RawMaterial("Copper", 0.0, 1);
		list.add(rm);
		rm = new RawMaterial("Titanium", 0.0, 1);
		list.add(rm);
		
		
		IamAuthenticator authenticator = new IamAuthenticator("5Yxi-cf9gS-RQ9cxGoYuy10qtf8Sfn1LqTFXNyxlMtZy");
        Discovery discovery = new Discovery("2019-04-30", authenticator);
        String environmentId = "system";
        String collectionId = "news-en";
       
        //loop through raw materials
        for (int j = 0; j < list.size(); j++) {
			
        QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
        queryBuilder.query("enriched_text.concepts.text:"+list.get(j).getName());
        
        queryBuilder.filter("enriched_text.entities.sentiment.score>=0.5");
        
        QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute().getResult();
//        System.out.println(queryResponse.toString());
        Gson g = new Gson();
        JsonObject obj = g.fromJson(queryResponse.toString(),JsonObject.class);
        JsonArray arr =  obj.getAsJsonArray("results");
//        System.out.println(arr.toString());
            Double avg = 0.0;
        
            
            for (int i=0; i< arr.size();i++
             ) {
                    JsonObject o1 = arr.get(i).getAsJsonObject();
                    avg +=o1.getAsJsonObject("enriched_text").getAsJsonObject("sentiment").getAsJsonObject("document").get("score").getAsDouble();
//                    System.out.println(o1.getAsJsonObject("enriched_text").getAsJsonObject("sentiment").getAsJsonObject("document").get("score").getAsDouble());
        	}
        
        list.get(j).setHike(avg/arr.size());
			
        }
        return list;
	}
	
	public List<RawMaterial> getComponents(){
		List<RawMaterial> list = new ArrayList<>();
				
				RawMaterial rm = new RawMaterial("Turbine Blade", 0.0, 1);
				list.add(rm);
				rm = new RawMaterial("Fuel manifold", 0.0, 1);
				list.add(rm);
				rm = new RawMaterial("Modulating air system valve", 0.0, 1);
				list.add(rm);
				rm = new RawMaterial("Fuel atomization nozzels", 0.0, 1);
				list.add(rm);
				rm = new RawMaterial("Main oil Pump", 0.0, 1);
				list.add(rm);
				
				
				IamAuthenticator authenticator = new IamAuthenticator("5Yxi-cf9gS-RQ9cxGoYuy10qtf8Sfn1LqTFXNyxlMtZy");
		        Discovery discovery = new Discovery("2019-04-30", authenticator);
		        String environmentId = "system";
		        String collectionId = "news-en";
		       
		        //loop through raw materials
		        for (int j = 0; j < list.size(); j++) {
					
		        QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
		        queryBuilder.query("enriched_text.concepts.text:"+list.get(j).getName());
		        
		        queryBuilder.filter("enriched_text.entities.sentiment.score>=0.5");
		        
		        QueryResponse queryResponse = discovery.query(queryBuilder.build()).execute().getResult();
//		        System.out.println(queryResponse.toString());
		        Gson g = new Gson();
		        JsonObject obj = g.fromJson(queryResponse.toString(),JsonObject.class);
		        JsonArray arr =  obj.getAsJsonArray("results");
//		        System.out.println(arr.toString());
		            Double avg = 0.0;
		        
		            
		            for (int i=0; i< arr.size();i++
		             ) {
		                    JsonObject o1 = arr.get(i).getAsJsonObject();
		                    avg +=o1.getAsJsonObject("enriched_text").getAsJsonObject("sentiment").getAsJsonObject("document").get("score").getAsDouble();
//		                    System.out.println(o1.getAsJsonObject("enriched_text").getAsJsonObject("sentiment").getAsJsonObject("document").get("score").getAsDouble());
		        	}
		        
		        list.get(j).setHike(avg/arr.size());
					
		        }
		        return list;
			}
	
}
