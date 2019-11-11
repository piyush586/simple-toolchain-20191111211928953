package com.revolve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.discovery.v1.Discovery;
import com.ibm.watson.discovery.v1.model.QueryOptions;
import com.ibm.watson.discovery.v1.model.QueryResponse;

/**
 * Servlet implementation class RawMaterialServlet
 */
@WebServlet("/RawMaterialServlet")
public class RawMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RawMaterialServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
		
		
		IamAuthenticator authenticator = new IamAuthenticator("_0RYRaqACfzuggYALV1xwWDPJUb07l1j5iOjN6JFE5hx");
        Discovery discovery = new Discovery("2019-04-30", authenticator);
        String environmentId = "system";
        String collectionId = "news-en";

        QueryOptions.Builder queryBuilder = new QueryOptions.Builder(environmentId, collectionId);
        queryBuilder.query("enriched_text.concepts.text:"+list.get(0).name);
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
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
