package org.rsol.poc.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class RESTAwareAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	public RESTAwareAuthenticationFailureHandler() {
	}

	public RESTAwareAuthenticationFailureHandler(String defaultFailureUrl) {
		super(defaultFailureUrl);
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
			String err = exception.getMessage();
			response.getWriter().append(err);
		
		    System.out.println("---- setting reponse headers onAuthenticationFailure ...");
		 	response.setHeader("Access-Control-Allow-Origin", "*");
		 	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PATCH, PUT, DELETE");
		 	response.setHeader("Access-Control-Max-Age", "3600");
		 	response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cookie, myheader");
		 	response.setHeader("Access-Control-Allow-Credentials", "true");
		 	System.out.println("myheader:"+ request.getHeader("myheader"));
		 	
		super.onAuthenticationFailure(request, response, exception);
	}

}
