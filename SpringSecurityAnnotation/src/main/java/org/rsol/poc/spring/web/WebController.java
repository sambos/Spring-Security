package org.rsol.poc.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	 public ModelAndView welcomePage() {
	 
	 ModelAndView model = new ModelAndView();
	 model.addObject("title", "Spring Security with Annotation");
	 model.addObject("message", "Welcome Page !");
	 model.setViewName("unsecured");
	 return model;
	 
	 }
	 
	 @RequestMapping(value = "/protected**", method = RequestMethod.GET)
	 public ModelAndView protectedPage() {
	 ModelAndView model = new ModelAndView();
	 model.addObject("title", "Spring Security with Annotation");
	 model.addObject("message", "This is protected page - Only for Administrators !");
	 model.setViewName("protected");
	 return model;
	 
	 }
	 
	 @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
	 public ModelAndView superAdminPage() {
	 
	 ModelAndView model = new ModelAndView();
	 model.addObject("title", "Spring Security demo");
	 model.addObject("message", "This is confidential page - Need Super Admin Role !");
	 model.setViewName("protected");
	 
	 return model;
	 
	 }
	 
	 @RequestMapping(value = "/session**", method = RequestMethod.GET)
	 public ModelAndView userSessionPage() {
	 
	 ModelAndView model = new ModelAndView();
	 model.addObject("title", "Spring Security demo");
	 model.addObject("message", "This is confidential page - Need Super Admin Role !");
	 model.setViewName("session");
	 
	 return model;
	 
	 }	 
}
