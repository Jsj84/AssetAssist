package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Password;
import core.Product;
import core.UserAccount;
import sqlutils.DBUtils;
import sqlutils.MyUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	List<Product> p = new ArrayList<>();
	Map<String, Integer> map = new HashMap<>();

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser != null) {
			try {
				Connection conn = MyUtils.getStoredConnection(request);
				p = DBUtils.queryProduct(conn);

				for (int i = 0; i < p.size(); i++) {
					if (!map.containsKey(p.get(i).getCatagory())) {
						map.put(p.get(i).getCatagory(), 1);
					} else {
						int temp = map.get(p.get(i).getCatagory()) + 1;
						map.replace(p.get(i).getCatagory(), temp);
					}
				}

				request.setAttribute("user", loginedUser);
				request.setAttribute("productList", p);
				request.setAttribute("database", p.size());
				request.setAttribute("map", map);
				request.setAttribute("page", "homeView.jsp");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(request, response);

			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}

		} else {

			String userName = MyUtils.getUserNameInCookie(request);
			if (userName != null) {
				request.setAttribute("userName", userName);
			}
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
	}

	// When the user enters userName & password, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userLogin = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "on".equals(rememberMeStr);

		UserAccount user = null;
		boolean hasError = false;
		String errorString = null;
		String errorType = null;

		if (userLogin == null || password == null || userLogin.length() == 0 || password.length() == 0) {
			hasError = true;
			errorType = "Warning";

			if (userLogin == null || userLogin.length() == 0) {
				errorString = "Your user name is required";
			} else if (password.length() == 0) {
				errorString = "A password is required";
			} else {
				errorString = "Either your username or password is incorrect";
			}
		} else {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				// Find the user in the DB.
				user = DBUtils.findUser(conn, userLogin);

				if (user == null) {
					hasError = true;
					errorType = "Warning";
					errorString = "Sorry, we don't have a record of your account";
				} else if (!Password.isExpectedPassword(password.toCharArray(), user.getSalt(), user.getPassword())) {
					hasError = true;
					errorType = "warning";
					errorString = "Incorrect Password";
				}

			} catch (Exception e) {
				hasError = true;
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError) {
			user = new UserAccount();
			user.setUserName(userLogin);
			// user.setPassword(Password.ge);

			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("errorType", errorType);
			request.setAttribute("showAlert",
					" window.setTimeout(function() {\n" + "	    $(\".alert\").fadeTo(500, 0).slideUp(500, function(){\n"
							+ "	        $(this).remove(); \n" + "	    });\n" + "	}, 4000);");

			request.setAttribute("user", user);

			// Forward to /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
		// If no error
		// Store user information in Session
		// And redirect to userInfo page.
		else {

			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);
			session.setMaxInactiveInterval(1800);

			// If user checked "Remember me".
			if (remember) {
				MyUtils.storeUserCookie(response, user);
			}
			// Else delete cookie.
			else {
				MyUtils.deleteUserCookie(response, user);
			}
			List<Product> p = new ArrayList<>();

			Connection conn = MyUtils.getStoredConnection(request);
			try {
				p = DBUtils.queryProduct(conn);
			} catch (SQLException | ParseException e) {
				System.out.println("Caught an error trying to load the product list ");
				e.printStackTrace();
			}

			request.setAttribute("user", user);
			request.setAttribute("productList", p);
			request.setAttribute("database", p.size());

			for (int i = 0; i < p.size(); i++) {
				if (!map.containsKey(p.get(i).getCatagory())) {
					map.put(p.get(i).getCatagory(), 1);
				} else {
					int temp = map.get(p.get(i).getCatagory()) + 1;
					map.replace(p.get(i).getCatagory(), temp);
				}
			}
			request.setAttribute("map", map);
			request.setAttribute("page", "homeView.jsp");
			session.setMaxInactiveInterval(1800);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			dispatcher.forward(request, response);
		}

	}
}