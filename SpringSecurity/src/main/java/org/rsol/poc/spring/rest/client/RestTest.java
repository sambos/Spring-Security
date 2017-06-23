package org.rsol.poc.spring.rest.client;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

public final class RestTest {
	  public static void main(String[] args) {
	    RestTemplate rest = new RestTemplate();

/*	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	        @Override
	        public boolean verify(String s, SSLSession sslsession) {
	            return true;
	        }
	    });*/

	    // setting up a trust store with JCA is a whole other issue
	    // this assumes you can only log in via SSL
	    // you could turn that off, but not on a production site!
/*	    System.setProperty("javax.net.ssl.trustStore", "/path/to/cacerts");
	    System.setProperty("javax.net.ssl.trustStorePassword", "somepassword");*/

	    String jsessionid = rest.execute("http://localhost:8080/spring-session/j_spring_security_check", HttpMethod.POST,
	            new RequestCallback() {
	                @Override
	                public void doWithRequest(ClientHttpRequest request) throws IOException {
	                 request.getBody().write("j_username=admin&j_password=admin".getBytes());
	                }
	            }, new ResponseExtractor<String>() {
	                @Override
	                public String extractData(ClientHttpResponse response) throws IOException {
	                    List<String> cookies = response.getHeaders().get("Cookie");

	                    // assuming only one cookie with jsessionid as the only value
	                    if (cookies == null) {
	                        cookies = response.getHeaders().get("Set-Cookie");
	                    }

	                    String cookie = cookies.get(cookies.size() - 1);

	                    int start = cookie.indexOf('=');
	                    int end = cookie.indexOf(';');

	                    return cookie.substring(start + 1, end);
	                }
	            });
	    System.out.println(jsessionid);
	    
	    

	   String key = rest.getForObject("http://localhost:8080/spring-session/test-service;jsessionid=" + jsessionid, String.class);
	   System.out.println(key);
	   ResponseEntity response = rest.getForEntity("http://localhost:8080/spring-session/test-service;jsessionid=" + jsessionid, String.class);
	   System.out.println(response.getStatusCode());
	   
	  }
	  
}