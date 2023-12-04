package com.ksh.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindController {
	
    @GetMapping(value="/apitest")
    public String callapihttp(){
        JSONObject jsonResponse = new JSONObject();
        JSONArray mediaPaths = new JSONArray();
        
    	StringBuffer result = new StringBuffer();
        try{
        	// ---------- path ---- 나오는 값
            String urlstr = "https://beautyinfo0.cafe24.com/api/v1/d/styles";
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
 
            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
            String json = br.readLine();
    		
    		JSONParser parser = new JSONParser();
    		JSONObject obj = (JSONObject)parser.parse(json);
    		JSONArray content = (JSONArray)obj.get("content");
    		
    		for(int i=0;i<content.size();i++){
    			JSONObject tmp = (JSONObject)content.get(i);
    			JSONArray medias = (JSONArray)tmp.get("medias");
    				for(int j=0; j<medias.size(); j++) {
    					JSONObject medias1 = (JSONObject)medias.get(j);
    					String path = (String)medias1.get("path");
    					// System.out.println(medias1);
    					mediaPaths.add(path);
    				}
    		}
             urlconnection.disconnect();
             
        }catch(Exception e){
            e.printStackTrace();
        }
        jsonResponse.put("media_paths", mediaPaths);
        return jsonResponse.toJSONString();
    }
}
