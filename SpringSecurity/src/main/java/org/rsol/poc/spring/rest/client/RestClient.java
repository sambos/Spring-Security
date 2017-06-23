package org.rsol.poc.spring.rest.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String user = "j_username";
		String pwd = "j_password";
		String loginUrl = "http://localhost:8080/spring-session/j_spring_security_check";
		String logoutUrl = "http://localhost:8080/spring-session/logout";
		String serviceUrl = "http://localhost:8080/spring-session/test-service";
		
		Map<String,String> up = new HashMap<String,String>();
		up.put(user, "admin");
		up.put(pwd, "admin");
		RestTemplate rest =new RestTemplate();		

/*		URI redirectUrl = rest.postForLocation(loginUrl, up);
		System.out.println(redirectUrl);*/

		testCall();

	}

	public class Key{
		private String key;
		public String getKey(){return key;}
		public void setKey(String key){this.key = key; }
	}
	
	public static void login(){
		
	}
	
	public static void testCall(){
		String loginUrl = "/spring-session/j_spring_security_check";
		String logoutUrl = "/spring-session/logout";
		String serviceUrl = "/spring-session/test-service";
/*		String loginUrl = "http://localhost:8080/spring-session/j_spring_security_check";
		String logoutUrl = "http://localhost:8080/spring-session/logout";
		String serviceUrl = "http://localhost:8080/spring-session/test-service";*/		
		
		DefaultHttpClient client = new DefaultHttpClient();
		 try {
	            HttpHost httpHost = new HttpHost("localhost", 8080, "http");
	            client.getParams().setParameter(ClientPNames.DEFAULT_HOST, httpHost);

	            HttpGet securedResource = new HttpGet(serviceUrl);            
	            HttpResponse httpResponse = client.execute(securedResource);
	            HttpEntity responseEntity = httpResponse.getEntity();
	            String strResponse = EntityUtils.toString(responseEntity);
	            int statusCode = httpResponse.getStatusLine().getStatusCode();
	            EntityUtils.consume(responseEntity);

	            System.out.println("Http status code for Unauthenticated Request: " + statusCode);// Statue code should be 200
	            System.out.println("Response for Unauthenticated Request: \n" + strResponse); // Should be login page
	            System.out.println("================================================================\n");

	            HttpPost authpost = new HttpPost(loginUrl);
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("j_username", "admin"));
	            nameValuePairs.add(new BasicNameValuePair("j_password", "admin"));
	            authpost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	            httpResponse = client.execute(authpost);
	            responseEntity = httpResponse.getEntity();
	            strResponse = EntityUtils.toString(responseEntity);
	            statusCode = httpResponse.getStatusLine().getStatusCode();
	            EntityUtils.consume(responseEntity);

	            System.out.println("Http status code for Authenticattion Request: " + statusCode);// Status code should be 302
	            System.out.println("Response for Authenticattion Request: \n" + strResponse); // Should be blank string
	            System.out.println("================================================================\n");

	            httpResponse = client.execute(securedResource);
	            responseEntity = httpResponse.getEntity();
	            strResponse = EntityUtils.toString(responseEntity);
	            statusCode = httpResponse.getStatusLine().getStatusCode();
	            EntityUtils.consume(responseEntity);

	            System.out.println("Http status code for Authenticated Request: " + statusCode);// Status code should be 200
	            System.out.println("Response for Authenticated Request: \n" + strResponse);// Should be actual page
	            System.out.println("================================================================\n");
	     
		   }
	        catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
