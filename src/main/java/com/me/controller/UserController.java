package com.me.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.DAO.GymDAO;
import com.me.DAO.ClassesDAO;
import com.me.DAO.UserDAO;
import com.me.exception.UserException;
import com.me.pojo.Gym;
import com.me.pojo.ClasList;
import com.me.pojo.User;
import com.me.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@Autowired
	@Qualifier("gymDao")
	GymDAO gymDao;
	
	@Autowired
	@Qualifier("classDao")
	ClassesDAO classDao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "home";
	}
	
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				mv.setViewName("error");
				return mv;
			}
			session.setAttribute("user", u);
			String role = u.getRole().trim();
			
			if (role.equals("customer")){
			System.out.println("Entered here");
				mv.addObject("classes", classDao.list());
				mv.setViewName("customer-home");
				return mv ;
			}
			
			else if (role.equals("host")){
				mv.addObject("classes", classDao.listGym(u.getGym()));
				mv.setViewName("host-home");
				return mv;
			}
			
			else if(role.equals("admin")){
//				System.out.println("The email addrees of Admin : " +u.getFirstName()+" is "+u.getEmail().getEmailAddress());
				mv.addObject("gyms", gymDao.list());
				mv.setViewName("admin-home");
				return mv;	
				}
			
			else
				return null;
			
			

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			mv.setViewName("error");
			return mv;
		}

	}
	
	@RequestMapping(value = "/user/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");
		ModelAndView mv = new ModelAndView();
		mv.addObject("gyms", gymDao.list());
		mv.addObject("user",new User());
		mv.setViewName("register-user");
//		return new ModelAndView("register-user", "user", new User());
		return mv;

	}
	
	@RequestMapping(value = "/user/customer.htm", method = RequestMethod.GET)
	protected ModelAndView customerHome() throws Exception {
		System.out.print("Customer Home");
		ModelAndView mv = new ModelAndView();
		mv.addObject("classes", classDao.list());
		mv.setViewName("customer-home");
//		return new ModelAndView("register-user", "user", new User());
		return mv;

	}
	
	@RequestMapping(value = "/user/host.htm", method = RequestMethod.GET)
	protected ModelAndView hostHome(HttpServletRequest request) throws Exception {
		System.out.print("Host Home");
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		mv.addObject("classes", classDao.listGym(u.getGym()));
		mv.setViewName("host-home");
		return mv;
//		return new ModelAndView("register-user", "user", new User());
		

	}
	
	@RequestMapping(value = "/user/admin.htm", method = RequestMethod.GET)
	protected ModelAndView adminHome() throws Exception {
		System.out.print("Admin Home");
		ModelAndView mv = new ModelAndView();
		mv.addObject("gyms", gymDao.list());
		mv.setViewName("admin-home");
//		return new ModelAndView("register-user", "user", new User());
		return mv;

	}
	
	@RequestMapping(value = "/user/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
//			return new ModelAndView("register-user", "user", user);
			ModelAndView mv = new ModelAndView();
			mv.addObject("gyms", gymDao.list());
			mv.addObject("user", user);
			mv.setViewName("register-user");
			return mv;
		}

		try {

			System.out.print("registerNewUser");
			String role = request.getParameter("myRole");
			user.setRole(role);
			String gym = request.getParameter("myGym");
			System.out.println("The Gym is :" +gym);
			
			Gym c = gymDao.get(gym);
			
			System.out.println("The gym ID : " +c.getGymId());
			user.setGym(c);
			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			
			return new ModelAndView("user-home", "user", u);
			
	
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

}
