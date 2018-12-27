package com.revature.controllerv1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.Login;
import com.revature.delegate.EmployeeDelegate;
import com.revature.delegate.LoginDelegate;
import com.revature.user.Employee;

public class RequestHelperV1 {
	private LoginDelegate ld = new LoginDelegate();
	private EmployeeDelegate ed = new EmployeeDelegate();

	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String switchString = req.getParameter("request");
		System.out.println("Request: " + switchString);
		switch (switchString) {
		default:
			System.out.println("Bad Request.");
			break;
		case "login":
			System.out.println("Login attempt");
			ld.login(req, resp);
			switchString = "";
			break;
		case "redirectLogin":
			System.out.println("Redirect");
			ld.getPage(req, resp);
			break;
		case "retrieveEmp":
			System.out.println("retrieveEmp");
			ed.retrieveEmpInfo(req, resp);
			switchString = "";
			break;
		case "retrieveReqs":
			System.out.println("Retrieve Requests");
			ed.getReimbursementList(req, resp);
			break;
		case "requestMoney":
			System.out.println("Asking for money");
			ed.addReimbursement(req, resp);
			break;
		case "updateInfo":
			System.out.println("Updating Info");
			ed.updateEmp(req, resp);
			break;
		case "empList":
			System.out.println("Get ALL the employees");
			ed.getAllEmployees(req, resp);
			break;
		case "approvReq":
			System.out.println("Approvin' eh?");
			ed.approveRequests(req, resp);
			break;
		case "logout":
			System.out.println("Logging out");
			ld.logout(req, resp);
			break;
		} // request switch

	}// process
}
