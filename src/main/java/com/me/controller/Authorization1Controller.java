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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.pojo.ClasList;
import com.me.pojo.User;

@Controller
@RequestMapping("/authorize/*")
public class Authorization1Controller {

	
	@RequestMapping(value = "/authorize/error.htm", method = RequestMethod.GET)
	protected ModelAndView addclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("errorpage");

		return mv;
	}
	
	@RequestMapping(value = "/authorize/ClasUpdateError.htm", method = RequestMethod.GET)
	protected ModelAndView errorclass(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("error-clasupdate");

		return mv;
	}
}

