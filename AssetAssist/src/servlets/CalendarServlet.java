package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.UserAccount;
import sqlutils.MyUtils;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalendarServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();

		// Check User has logged on
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		// Not logged in
		if (loginedUser == null) {
			// Redirect to login page.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// int theme = loginedUser.getTheme();

		// Store info to the request attribute before forwarding.
		request.setAttribute("user", loginedUser);
		// request.setAttribute("theme", theme);

		// If the user has logged in, then forward to the page
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/calendar.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
