package org.rsol.poc.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class RESTAwareAuthenticationSuccessHandler 
extends SimpleUrlAuthenticationSuccessHandler {

private RequestCache requestCache = new HttpSessionRequestCache();

@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
Authentication authentication) throws ServletException, IOException {
  SavedRequest savedRequest = requestCache.getRequest(request, response);

  System.out.println("---- setting reponse headers...");
 	response.setHeader("Access-Control-Allow-Origin", "*");
 	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PATCH, PUT, DELETE");
 	response.setHeader("Access-Control-Max-Age", "3600");
 	response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cookie, myheader");
 	response.setHeader("Access-Control-Allow-Credentials", "true");
 	//response.setHeader("Access-Control-Allow-Headers",request.getHeader("Access-Control-Request-Headers"));
 	
 	
 	String jsessionid = response.getHeader("Set-Cookie");
 	if(jsessionid != null){
 	   jsessionid = jsessionid.substring(jsessionid.indexOf('=')+1, jsessionid.indexOf(';'));
 	}else{
 		jsessionid =  request.getSession().getId();
 	}
 	response.getWriter().append(jsessionid);
 	
 	System.out.println("sessionId:" + request.getSession().getId());
 	System.out.println("jsessionId:" + jsessionid);
 	
/* 	Cookie[] cookies = request.getCookies();
 	System.out.println("cookies length:" + cookies.length);
    for (int i = 0; i < cookies.length; i++){
        Cookie cookie = cookies[i];
        
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(cookie.getValue( ), " ");
        
        if(tokens.countTokens() == 1){
       	 System.out.println(tokens.nextToken());

        }

        } */


 	
  if (savedRequest == null) {
      clearAuthenticationAttributes(request);
      return;
  }
  String targetUrlParam = getTargetUrlParameter();
  if (isAlwaysUseDefaultTargetUrl() || 
    (targetUrlParam != null && 
    StringUtils.hasText(request.getParameter(targetUrlParam)))) {
      requestCache.removeRequest(request, response);
      clearAuthenticationAttributes(request);
      return;
  }
  
 
  clearAuthenticationAttributes(request);
}

public void setRequestCache(RequestCache requestCache) {
  this.requestCache = requestCache;
}
}
