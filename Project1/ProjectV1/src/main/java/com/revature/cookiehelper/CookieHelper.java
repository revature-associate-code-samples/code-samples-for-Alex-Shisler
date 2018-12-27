package com.revature.cookiehelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CookieHelper {

	public Cookie getCookie(String name, HttpServletRequest req) throws ServletException, IOException{
		HttpSession session = req.getSession(false);
		Cookie[] cookies = req.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			if (cookies[i].getName().equals(name)) 
				return cookies[i];
		} 
		return null;
	}// getCookie
}
