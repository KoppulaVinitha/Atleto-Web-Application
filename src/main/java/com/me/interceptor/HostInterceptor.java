/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.me.interceptor;

/**
 *
 * @author koppu
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.me.DAO.ClassesDAO;
import com.me.pojo.ClasList;
import com.me.pojo.User;

public class HostInterceptor extends HandlerInterceptorAdapter{

	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		System.out.println("the URL is " +request.getRequestURL());
		System.out.println("the URI is " +request.getRequestURI());
		ClassesDAO classDao = new ClassesDAO();

		
//		if(session.getAttribute("user") != null){
//			System.out.println("in interceptor");
//			return true;
//		}
		if (session.getAttribute("user") != null){
		User user = (User) session.getAttribute("user");
		
		if (user.getRole().equalsIgnoreCase("host") ){
			if (request.getRequestURI().equalsIgnoreCase("/controller/classes/update.htm")){
				int id = Integer.parseInt(request.getParameter("clasId"));
				ClasList clas = classDao.getClas(id);
				
				if (user.getUsername().equals(clas.getPostedID())){
					System.out.println("User Authorized to change the clas");
					return true;
				} else {
					response.sendRedirect(request.getContextPath() +"/authorize/ClasUpdateError.htm");
					return false;
				}
			}
			System.out.println("in Host interceptor");
			return true;
		}
		}
		System.out.println("not Host");
		response.sendRedirect(request.getContextPath() +"/authorize/error.htm");
		return false;
		
		
		
		
	}
	
}
