package com.revature.controllerv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.Login;
import com.revature.user.Employee;

public class FrontController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7963624447234248456L;
	private RequestHelperV1 rh = new RequestHelperV1();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String switchString = req.getParameter("request");
		if (switchString != null) {
			System.out.println("Processing");
			rh.process(req, res);

		} else {
			System.out.println("Awaiting orders");
			super.doGet(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);

	}



	
}
