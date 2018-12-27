package com.revature.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Login;
import com.revature.cookiehelper.CookieHelper;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.LoginDao;
import com.revature.dao.LoginOracle;
import com.revature.user.Employee;

/*
 * Programmer: Alex Shisler
 * LoginDelegate
 */
public class LoginDelegate {
	public LoginDao ld = new LoginOracle();
	public EmployeeDAO ed = new EmployeeDAOImpl();
	private Employee emp;
	private Login login;
	private CookieHelper cooks = new CookieHelper();

	public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String data = getBody(req);
		System.out.println(data);
		login = new ObjectMapper().readValue(data, Login.class);

		login = ld.login(login.getUsername(), login.getPassword());
		if (login == null) {
			System.out.println("Login failed!");
			resp.resetBuffer();
			resp.setStatus(401);
			// resp.sendRedirect("EmployeeLoginPage.html");
		} else {
			emp = ed.getEmployee(login.getUsername());
			System.out.println("Logged in!");
			System.out.println(emp.toString());
			HttpSession session = req.getSession();
			session.setAttribute(login.getUsername(), login);
			session.setAttribute(login.getPassword(), login);
			// Cookie logCookie = new Cookie("username", login.getUsername());
			// Cookie logCookie2 = new Cookie("password", login.getPassword());
			ObjectMapper mapper = new ObjectMapper();

			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			// System.out.println(req.getCookies());
			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

			mapper.writeValue(resp.getOutputStream(), emp);
			// req.getRequestDispatcher("EmployeePage.html");
		} // logged in

	}

	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie email = cooks.getCookie("email", req);
		Cookie username = cooks.getCookie("username", req);
		resp.addCookie(email);
		resp.addCookie(username);
		if (emp == null) {
			System.out.println("Redirect to login, failure");
			resp.sendRedirect("EmployeeLoginPage.html");
		} else {
			resp.setStatus(301);
			System.out.println("Redirect to EP");
			if(emp.getTitle().equals("Manager")){
				resp.sendRedirect("ManagerPage.html");
				return;
				}//if manager
			else {
				resp.sendRedirect("EmployeePage.html");
				return;
				}//not a manager.
			}//triumph
		
		}//getPage

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie email = cooks.getCookie("email", req);
		Cookie username = cooks.getCookie("username", req);
		email.setMaxAge(0);
		username.setMaxAge(0);
		resp.addCookie(email);
		resp.addCookie(username);
		req.getSession().invalidate();
		resp.setStatus(301);
		resp.sendRedirect("EmployeeLoginPage.html");
		return;
	}

	private String getBody(HttpServletRequest req) throws ServletException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader;
		try {
			reader = req.getReader();

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			} // while
			String data = buffer.toString();
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // catch
		return null;

	}// getBody

}