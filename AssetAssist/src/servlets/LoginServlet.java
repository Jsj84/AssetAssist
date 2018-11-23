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
	static List<Product> p = new ArrayList<>();

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
		String userName = MyUtils.getUserNameInCookie(request);

		if (userName != null) {
			request.setAttribute("userName", userName);
			request.setAttribute("checked", "checked");
		}

		if (loginedUser != null) {
			try {
				Connection conn = MyUtils.getStoredConnection(request);
				p = DBUtils.queryProduct(conn);

				request.setAttribute("user", loginedUser);
				request.setAttribute("productList", p);
				request.setAttribute("database", p.size());
				request.setAttribute("map", listCount());
				request.setAttribute("page", "homeView.jsp");

			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
			session.setMaxInactiveInterval(5 * 60);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userLogin = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		UserAccount user = new UserAccount();
		boolean hasError = false;
		String errorString = null;
		String errorType = null;
		HttpSession session = request.getSession();

		String userName = MyUtils.getUserNameInCookie(request);

		if (userLogin != null || password != null) {

			Connection conn = MyUtils.getStoredConnection(request);

			try {
				// Find the user in the DB.
				user = DBUtils.findUser(conn, userLogin);

				if (rememberMeStr != null) {
					MyUtils.storeUserCookie(response, user);
					request.setAttribute("checked", "checked");
				}
				// Else delete cookie.
				else {
					MyUtils.deleteUserCookie(response, user);
				}

				if (user == null) {
					hasError = true;
					errorType = "danger";
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

			if (!hasError) {
				MyUtils.storeLoginedUser(session, user);
				try {
					p = DBUtils.queryProduct(conn);
					request.setAttribute("productList", p);
					request.setAttribute("database", p.size());
					request.setAttribute("map", listCount());
					request.setAttribute("page", "homeView.jsp");
					request.setAttribute("userName", userName);
					request.setAttribute("user", user);

					session.setMaxInactiveInterval(5 * 60);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
					dispatcher.forward(request, response);

				} catch (SQLException | ParseException e) {
					System.out.println("Caught an error trying to load the product list ");
					e.printStackTrace();
				}

			}
			if (hasError) {
				// Store information in request attribute, before forward.
				request.setAttribute("errorString", errorString);
				request.setAttribute("errorType", errorType);
				request.setAttribute("userName", userName);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginView.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	private static Map<String, Integer> listCount() {

		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < p.size(); i++) {
			if (!map.containsKey(p.get(i).getCatagory())) {
				map.put(p.get(i).getCatagory(), 1);
			} else {
				int temp = map.get(p.get(i).getCatagory()) + 1;
				map.replace(p.get(i).getCatagory(), temp);
			}
		}
		return map;
	}
}