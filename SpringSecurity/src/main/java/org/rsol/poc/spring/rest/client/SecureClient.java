package org.rsol.poc.spring.rest.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class SecureClient {

	private static String loginUrl = "/j_spring_security_check";
	private static String logoutUrl = "/logout";
	private static String serviceUrl = "/test-service";
	private static String SERVER_URL = "http://localhost:8080/spring-session";
	public SecureClient() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
        HttpClient httpClient = new DefaultHttpClient();
        
        HttpConnectionParams
                .setConnectionTimeout(httpClient.getParams(), 10000);
        
        String msg = "";
		msg = doAccess(httpClient, serviceUrl); //log(msg);		
        msg = doLogin(httpClient); //log(msg);
        msg = doAccess(httpClient, serviceUrl); //log(msg);
		
	}
	
	public static String doLogin(HttpClient httpClient) throws Exception{
		HttpPost authpost = new HttpPost(SERVER_URL + loginUrl);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("j_username", "admin"));
        nameValuePairs.add(new BasicNameValuePair("j_password", "admin"));
        authpost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        HttpResponse response = httpClient.execute(authpost);
        //System.out.println("response:"+response.getStatusLine().getStatusCode() + " : "+response.getStatusLine().getReasonPhrase());
        //log( response.toString());
        EntityUtils.consume(response.getEntity());
        return parseToken(response.getAllHeaders(), "set-Cookie");        
		
	}
	
	public static String doAccess(HttpClient httpClient, String url) throws Exception{
        HttpGet httpget = new HttpGet(SERVER_URL + url);
        //parseToken(httpget.getAllHeaders(),"");
        HttpResponse response = httpClient.execute(httpget);
        //System.out.println("response:"+response.getStatusLine().getStatusCode() + " : "+response.getStatusLine().getReasonPhrase());
        //log( response.toString());
        EntityUtils.consume(response.getEntity());
        
        return parseToken(response.getAllHeaders(), "set-Cookie");
		
	}	
	
	public static String parseToken(Header[] headers, String token){
		for(Header h : headers){
			log(h.getName() + " "+ h.getValue());
			if(token.equalsIgnoreCase(h.getName())){
				
			 return h.getValue();
			}
		}
		return null;
	}
	
	public static void log(String msg){
		System.out.println(msg);
	}
}
