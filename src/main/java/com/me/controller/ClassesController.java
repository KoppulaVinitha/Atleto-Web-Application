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

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.me.exception.ClassesException;
import com.me.pojo.Gym;
import com.me.pojo.ClasList;
import com.me.pojo.User;
import com.me.validator.ClassValidator;

@Controller
@RequestMapping("/classes/*")
public class ClassesController {

	@Autowired
	@Qualifier("classDao")
	ClassesDAO classDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("gymDao")
	GymDAO gymDao;

	@Autowired
	@Qualifier("classValidator")
	ClassValidator validator;
	
	@Autowired
    private JavaMailSender mailSender;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/classes/register.htm", method = RequestMethod.GET)
	protected ModelAndView addclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		mv.addObject("gymName", u.getGym().getGymName());
		mv.addObject("claslist", new ClasList());
		mv.setViewName("class-add");

		return mv;
	}

	@RequestMapping(value = "/classes/register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("claslist") ClasList claslist,
			BindingResult result) throws Exception {

		validator.validate(claslist, result);
		if (result.hasErrors()) {
			return new ModelAndView("class-add", "claslist", claslist);
		}

		try {
			ModelAndView mv = new ModelAndView();
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			System.out.print("Add New Clas");
			claslist.setGym(u.getGym());
			claslist.setPostedName(u.getFirstName());
			claslist.setPostedID(u.getUsername());
			ClasList clas = classDao.register(claslist);
			mv.addObject("classes", classDao.listGym(u.getGym()));
			mv.addObject("claslist", clas);
			mv.setViewName("host-home");
			return mv;

		} catch (ClassesException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding clas");
		}
	}

	@RequestMapping(value = "/classes/update.htm", method = RequestMethod.GET)
	protected ModelAndView updateclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("clasId"));
		ClasList clas = classDao.getClas(id);
		User u = (User) session.getAttribute("user");
		mv.addObject("gymName", u.getGym().getGymName());

		mv.addObject("clasList", clas);
		mv.setViewName("class-update");

		return mv;
	}

	@RequestMapping(value = "/classes/update.htm", method = RequestMethod.POST)
	protected ModelAndView updateclas(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("clasId"));
		ClasList clas = classDao.getClas(id);
		clas.setClasName(request.getParameter("clasName"));
		clas.setClasDesc(request.getParameter("clasDesc"));
	//	System.out.println("THE PRODUCT ID IS :" + id);
		classDao.update(clas);
		User u = (User) session.getAttribute("user");
		
		mv.addObject("clasList", clas);
		mv.setViewName("update-success");

		return mv;
	}





@RequestMapping(value = "/classes/modify.htm", method = RequestMethod.GET)
	protected ModelAndView modifyclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("clasId"));
		ClasList clas = classDao.getClas(id);
		System.out.println("THE PRODUCT ID IS :" + id);
		User u = (User) session.getAttribute("user");
		mv.addObject("gymName", u.getGym().getGymName());
		// mv.addObject("claslist", new ClasList());

		mv.addObject("clasList", clas);
		mv.setViewName("j-delete");

		return mv;
	}

	@RequestMapping(value = "/classes/modify.htm", method = RequestMethod.POST)
	protected ModelAndView modifyClas(HttpServletRequest request, @ModelAttribute("claslist") ClasList claslist,
			BindingResult result) throws Exception {

		// validator.validate(claslist, result);
		// if (result.hasErrors()) {
		// return new ModelAndView("class-add", "claslist", claslist);
		// }

		try {

                ModelAndView mv = new ModelAndView();
                HttpSession session = (HttpSession) request.getSession();
		int id = Integer.parseInt(request.getParameter("clasId"));
		ClasList clas = classDao.getClas(id);
		System.out.println("THE PRODUCT ID IS :" + id);
		classDao.deleteClas(clas);
		User u = (User) session.getAttribute("user");
	
		mv.addObject("clasList", clas);
		mv.setViewName("delete-success");



		return mv;


		} catch (ClassesException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding clas");
		}
	}

	@RequestMapping(value = "/classes/add.htm", method = RequestMethod.POST)
	protected ModelAndView addClas(HttpServletRequest request) throws Exception {

		try {
			boolean flag = false;
			ModelAndView mv = new ModelAndView();
			HttpSession session = (HttpSession) request.getSession();
			User u = (User) session.getAttribute("user");
			int clas_id = Integer.parseInt(request.getParameter("clasID"));
			for (ClasList clas1 : u.getClaslists()) {
				if (clas1.getClasID() == clas_id) {
					flag = true;
				}
			}
			System.out.print("Add New Clas");
			if (!flag) {
				ClasList clas = classDao.getClas(clas_id);
				// u.getClaslists().add(clas);
				userDao.update(u, clas);
				System.out.println("Entered the clas addition new para");
				clas.getUsers().add(u);
				classDao.update(clas);
//				String address = u.getEmail().getEmailAddress();
//				String subject = "Clas Joined Notification for " +clas.getClasName();
//				String message = "Dear "+u.getFirstName()+","+"\n"+"\nYou Applied for the class : "+clas.getClasName()+"\n"+"\nClas Desc : " +clas.getClasDesc() 
//				+"\non : " +new Date() +"\n"+"\n"+"\nThank you for using our online app Atleto"+"\n"+"\nRegards"+"\nAdmin";
//				SimpleMailMessage email = new SimpleMailMessage();
//		        email.setTo(address);
//		        email.setSubject(subject);
//		        email.setText(message);
//		        
//		        mailSender.send(email);
//		        
		        
				mv.addObject("classes", classDao.listGym(u.getGym()));
				mv.addObject("claslist", clas);
				mv.setViewName("add-success");
				flag = false;
				return mv;
			} else {
				System.out.println("Entered the other para where its already added");
				mv.addObject("classes", null);
				mv.setViewName("add-already");
				return mv;
			}
		} catch (ClassesException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while adding clas");
		}
	}

	@RequestMapping(value = "/classes/search.htm", method = RequestMethod.GET)
	protected ModelAndView searchclas(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		mv.setViewName("search-class");

		return mv;
	}

	@RequestMapping(value = "/classes/search.htm", method = RequestMethod.POST)
	protected ModelAndView searchclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		String name = request.getParameter("inputtext");
		String search = request.getParameter("searchkey");

		if (search.equalsIgnoreCase("clasname")) {
			List<ClasList> classes = classDao.listClasname(name);
			mv.addObject("classes", classes);
			if (classes.isEmpty())
				System.out.println("The returned list is empty");
			for (ClasList clas : classes) {
				System.out.println("The matching classes are : " + clas.getClasName());
			}
		} else if (search.equalsIgnoreCase("gym")) {
			Gym c = gymDao.get(name);

			if (c == null) {
				mv.setViewName("search-fail");
				return mv;
			}
			List<ClasList> classes = classDao.listGym(c);
			if (classes.isEmpty()) {
				mv.setViewName("search-failclasses");
				return mv;
			}
			mv.addObject("classes", classes);
		}

		mv.setViewName("search-success");

		return mv;
	}

	@RequestMapping(value = "/classes/applied.htm", method = RequestMethod.GET)
	protected ModelAndView appliedclas(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("classes-joined");

		return mv;
	}
	
	@RequestMapping(value = "/classes/viewallclasses.htm", method = RequestMethod.GET)
	protected ModelAndView viewAllClas(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("classes", classDao.list());
		mv.setViewName("viewallclasses");

		return mv;
	}
}
