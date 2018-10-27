package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.UserAccount;
import sqlutils.DBUtils;
import sqlutils.MyUtils;

@WebServlet(name = "userInfo", urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Connection conn = MyUtils.getStoredConnection(request);

		// Check User has logged on
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		List<UserAccount> users = null;
		// UserAccount users = null;

		// Not logged in
		if (loginedUser == null) {
			// Redirect to login page.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		try {
			users = DBUtils.queryUser(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Store info to the request attribute before forwarding.
		request.setAttribute("users", users);
		request.setAttribute("user", loginedUser);
		request.setAttribute("page", "userInfoView.jsp");

		// If the user has logged in, then forward to the page
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<UserAccount> users = null;

		String userName = request.getParameter("userName");
		System.out.println("Returned param is: " + userName);

		HttpSession session = request.getSession();

		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		Connection conn = MyUtils.getStoredConnection(request);

		try {
			DBUtils.deleteUser(conn, userName);
			users = DBUtils.queryUser(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Store info to the request attribute before forwarding.
		request.setAttribute("users", users);
		request.setAttribute("user", loginedUser);
		request.setAttribute("page", "userInfoView.jsp");

		// If the user has logged in, then forward to the page
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);

	}

}
