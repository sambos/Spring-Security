package org.rsol.poc.spring.web;

import net.sf.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
 
	//@RequestMapping(value = "/welcome**", method = RequestMethod.GET)
	@RequestMapping("/welcome")
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");
 
		return model; 
	}

	
	
	@RequestMapping(value = "/test-service**", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody ResponseEntity<JSONObject> testService() {
 
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		System.out.println("logged in user name: "+ user.getUsername());
		String name = "some name";
		Key k = new Key();
		k.setKey(name);
		JSONObject json = new JSONObject();
		json.put("key", k);
		
		return new ResponseEntity<JSONObject>(json, HttpStatus.OK); 
	} 
	
	public class Key{
		public Key(){}
		private String key;
		public String getKey(){return key;}
		public void setKey(String key){this.key = key; }
	}
}