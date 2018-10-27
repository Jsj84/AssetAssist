package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Password;
import core.UserAccount;
import sqlutils.DBUtils;
import sqlutils.MyUtils;

@WebServlet(name = "newUser", urlPatterns = { "/newUser" })
public class newUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public newUserServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		request.setAttribute("user", user);
		request.setAttribute("timezones", UserObjectServlet.getTimeZones());
		request.setAttribute("page", "newUser.jsp");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userID = request.getParameter("userID");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone_number");
		String timeZone = request.getParameter("time_zone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		byte[] salt = Password.getNextSalt();
		byte[] pass = Password.hash(password.toCharArray(), salt);

		try {
			UserAccount user = new UserAccount(userID, pass, salt, firstName, lastName, timeZone, phone, email);
			Connection conn = MyUtils.getStoredConnection(request);
			DBUtils.insertUser(conn, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		request.setAttribute("user", user);
		request.setAttribute("page", "userInfoView.jsp");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

}
