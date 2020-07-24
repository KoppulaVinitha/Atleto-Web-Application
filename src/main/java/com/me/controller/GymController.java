/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.me.controller;

/**
 *
 * @author koppu
 */

import java.util.List;

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
import com.me.exception.GymException;
import com.me.pojo.Gym;
import com.me.pojo.ClasList;
import com.me.pojo.User;
import com.me.validator.GymValidator;

@Controller
@RequestMapping("/gym/*")
public class GymController {

	@Autowired
	@Qualifier("gymDao")
	GymDAO gymDao;
	@Autowired
	@Qualifier("gymValidator")
	GymValidator validator;
	
	@Autowired
	@Qualifier("classDao")
	ClassesDAO classDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/gym/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("gym") Gym gym, BindingResult result) throws Exception {
		
		validator.validate(gym, result);
		if (result.hasErrors()) {
			return new ModelAndView("register-gym", "gym", gym);
		}

		try {

			System.out.print("registerNewGym");
			
	   	        Gym c = gymDao.register(gym);
			
			return new ModelAndView("gym-add", "gym", c);

		} catch (GymException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while registering gym");
		}
	}
	
	@RequestMapping(value = "/gym/register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-gym", "gym", new Gym());

	}
	
	@RequestMapping(value = "/gym/delete.htm", method = RequestMethod.POST)
	protected ModelAndView searchclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		int compId = Integer.parseInt(request.getParameter("compID"));
		Gym comp = gymDao.getById(compId);
		gymDao.delete(comp);
		
	 	mv.addObject("gyms", gymDao.list());
		mv.setViewName("admin-home");

		return mv;
	}
}
