package trainBooking.controller;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trainBooking.model.logic.Command;
import trainBooking.model.logic.CommandFactory;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class ServletController
 */
//@WebServlet("/")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {}
	
	@Override
	public void destroy() {
		// delete all resources
		
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		System.out.println(/*redirect to doPost userPAth /registration*/);
		String url = "/WEB-INF/view/";
		
		CommandFactory commands = CommandFactory.getInstance();
		Command command = null;
		
		if (userPath.equals("/login")) {
			url += "login.jsp";
		} else if (userPath.equals("/registration")) {
			url += "registration.jsp";
		} else if (userPath.equals("/logout")) {
			command = commands.getCommand("logout");
			command.execute(request, response);
			url += "login.jsp";
		} else if (userPath.equals("/create-train")) {
			System.out.println("/*redirect to doPost userPAth /registration*/");
			url += "create-train.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		System.out.println("doPost userPath " + userPath);
		String url = "/WEB-INF/view/";

		HttpSession session = request.getSession();
		CommandFactory commands = CommandFactory.getInstance();
		Command command = null;
		
		if (userPath.equals("/check-login")) {
			
			// Get pressed button.
			String buttonUser = request.getParameter("userLogin");
			String buttonAdmin = request.getParameter("adminLogin");

			// User login button was pressed.
			if (buttonUser != null) {

				command = commands.getCommand("user_login");
				command.execute(request, response);
				
				// Check whether the user was found.
				if (session.getAttribute("user") == null) {
					url += "login.jsp";
				} else {
					url += "user-panel.jsp";
				}
			} else if (buttonAdmin != null) {
				
				// Admin login button was pressed.
				command = commands.getCommand("admin_login");
				command.execute(request, response);
				
				if (session.getAttribute("admin") == null) {
					url += "login.jsp";
				} else {
					url += "admin-panel.jsp";
				}
			}
		} else if (userPath.equals("/check-registration")) {
			
			// Before processing data, check that the passwords match.
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("passwordConfirm");
			
			if (!password.equals(passwordConfirm)) {
				request.setAttribute("passwordNotMatch", true);				
				url += "registration.jsp";
			} else {
				
				// Passwords match, forward request to the command.
				request.removeAttribute("passwordNotMatch");
				
				command = commands.getCommand("create_user");
				command.execute(request, response);
				
				if (request.getAttribute("userNotCreated") != null) {
					url += "registration.jsp";
				} else {
					request.removeAttribute("userNotCreated");
					url += "registration-success.jsp";
				}
			}
		} else if (userPath.equals("/create-train")) {
			System.out.println("5");
			String create = request.getParameter("create");
			if (create != null) {
				command = commands.getCommand("create_train");
				command.execute(request, response);

				// Check whether the user was found.
				if (session.getAttribute("train") == null) {
					url += "admin-panel.jsp";
				} else {
					url += "admin-panel.jsp";
				}
				System.out.println("fuck1");
			} else {
				System.out.println("fuck2");
			}

			System.out.println("fuck3");
		} else if (userPath.equals("/check-train")) {
			System.out.println("6");
			String create = request.getParameter("create");
			System.out.println("fuck4");
			url += "admin-panel.jsp";
//			if (create != null) {
//				command = commands.getCommand("create_train");
//				command.execute(request, response);
//
//				// Check whether the user was found.
//				if (session.getAttribute("train") == null) {
//					url += "admin-panel.jsp";
//				} else {
//					url += "admin-panel.jsp";
//				}
//				System.out.println("fuck1");
//			} else {
//				System.out.println("fuck2");
//			}
//
//			System.out.println("fuck3");
		} else if (userPath.equals("/admin-panel")) {
			System.out.println(7);
			// Update users.
			command = commands.getCommand("update_users");
			command.execute(request, response);
			url += "admin-panel.jsp";
		} else if (userPath.equals("/find-train")) {
			// Find train that satisfies conditions.
			command = commands.getCommand("find_train");
			command.execute(request, response);
			url += "trains.jsp";
		} else if (userPath.equals("/create-invoice")) {
			// Prepare new invoice
			command = commands.getCommand("prepare_invoice");
			command.execute(request, response);
			url += "accept-invoice.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

}
