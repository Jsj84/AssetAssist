package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

@WebServlet("/userObject")
public class UserObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserObjectServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String theme = request.getParameter("theme");

		if (theme != null) {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				DBUtils.inserTheme(conn, user, theme);
				user.setTheme(theme);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("user", user);
		request.setAttribute("timezones", getTimeZones());
		request.setAttribute("themes", getThemes());
		request.setAttribute("page", "userObjectView.jsp");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone_number");
		String timeZone = request.getParameter("time_zone");
		String theme = request.getParameter("Theme_Type");
		String email = request.getParameter("email");

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhone_number(phone);
		user.setTime_zone(timeZone);
		user.setTheme(theme);
		user.setEmail(email);

		Connection conn = MyUtils.getStoredConnection(request);
		try {
			DBUtils.updateUser(conn, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.setAttribute("timezones", getTimeZones());
		request.setAttribute("page", "userObjectView.jsp");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	public static String[] getThemes() {

		String[] themes = { "cerulean", "cosmo", "cyborg", "darkly", "flatly", "journal", "litera", "lumen", "lux",
				"materia", "minty", "pulse", "sandstone", "simplex", "sketchy", "slate", "solar", "spacelab",
				"superhero", "united", "yeti" };

		return themes;

	}

	public static List<String> getTimeZones() {

		List<String> timezones = new ArrayList<>();

		String[] ids = TimeZone.getAvailableIDs();

		for (String id : ids) {
			timezones.add(displayTimeZone(TimeZone.getTimeZone(id)));
		}
		return timezones;
	}

	private static String displayTimeZone(TimeZone tz) {

		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);
		// avoid -4:-30 issue
		minutes = Math.abs(minutes);

		String result = "";
		if (hours > 0) {
			result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
		} else {
			result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
		}

		return result;

	}

}
