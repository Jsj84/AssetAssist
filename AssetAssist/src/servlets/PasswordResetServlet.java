package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Password;
import sqlutils.DBUtils;
import sqlutils.MyUtils;

@WebServlet("/passwordReset")
public class PasswordResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String token;

	public PasswordResetServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/password_resetView.jsp");

		token = request.getParameter("token");
		String timestampStr = request.getParameter("timestamp");
		Timestamp timestamp = Timestamp.valueOf(timestampStr);

		String errorType = null;
		String errorString = null;
		boolean hasError = false;

		Date now = new Date();
		System.out.println("Time stamp is: " + toDate(timestamp));
		System.out.println("now is : " + now);

		if (now.getTime() > toDate(timestamp).getTime()) {
			hasError = true;
			errorType = "Danger";
			errorString = "Your token has expired, please wait while you're redirected";
			System.out.println("PW expired");

		} else {
			System.out.println("PW is good");
			dispatcher.forward(request, response);
		}
		if (hasError) {

			request.setAttribute("errorType", errorType);
			request.setAttribute("errorString", errorString);
			request.setAttribute("showAlert",
					" window.setTimeout(function() {\n" + "	    $(\".alert\").fadeTo(500, 0).slideUp(500, function(){\n"
							+ "	        $(this).remove(); \n" + "	    });\n" + "	}, 4000);");
			response.addHeader("REFRESH", "5;URL=" + request.getContextPath() + "/login");
			dispatcher.forward(request, response);
		}
	}

	public static Date toDate(Timestamp timestamp) {
		long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
		long millsecsAdded10Min = milliseconds + 600000;
		return new Date(millsecsAdded10Min);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String password = request.getParameter("password");

		// boolean hasError = false;
		// String errorString = null;

		// if (hasError) {
		//
		// RequestDispatcher dispatcher = this.getServletContext()
		// .getRequestDispatcher("/WEB-INF/views/password_resetView.jsp");
		// dispatcher.forward(request, response);
		// } else {
		Connection conn = MyUtils.getStoredConnection(request);
		try {
			System.out.println("Token is: " + token);
			byte[] salt = Password.getNextSalt();
			byte[] pass = Password.hash(password.toCharArray(), salt);

			DBUtils.updatePassword(conn, token, pass, salt);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}
	// }

}
