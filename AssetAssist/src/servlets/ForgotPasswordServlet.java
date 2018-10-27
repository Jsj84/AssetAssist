package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.UserAccount;
import sqlutils.DBUtils;
import sqlutils.MailServerConnectionUtils;
import sqlutils.MyUtils;

@WebServlet(name = "forgotPassword", urlPatterns = { "/forgotPassword" })
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/forgotPasswordView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean hasError = false;
		String errorString = null;
		String errorType = null;
		UserAccount user = null;

		String userEmailAddress = request.getParameter("emailAddress");
		Random rand = new Random();
		int n = rand.nextInt(900000000) + 1;
		String token = Integer.toString(n);
		Timestamp timestamp = new Timestamp(new Date().getTime());

		if (userEmailAddress.isEmpty()) {
			hasError = true;
			errorType = "Warning";
			errorString = "A Valid Email Address is Required";
		} else {
			Connection conn = MyUtils.getStoredConnection(request);

			try {
				user = DBUtils.findUser(conn, userEmailAddress);
				if (user == null) {
					hasError = true;
					errorType = "Warning";
					errorString = "Invalid Account";
				} else {
					DBUtils.updateToken(conn, token, timestamp.toString(), userEmailAddress);
					MailServerConnectionUtils.getMailServerSessoin(userEmailAddress, token, timestamp.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		if (hasError) {
			user = new UserAccount();
			user.setUserName(userEmailAddress);
			;

			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("errorType", errorType);
			request.setAttribute("user", user);
			request.setAttribute("showAlert",
					" window.setTimeout(function() {\n" + "	    $(\".alert\").fadeTo(500, 0).slideUp(500, function(){\n"
							+ "	        $(this).remove(); \n" + "	    });\n" + "	}, 4000);");

			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/forgotPasswordView.jsp");
			dispatcher.forward(request, response);
		} else {
			errorType = "Success";
			errorString = "Password Reset Email Has Been Sent";
			request.setAttribute("errorType", errorType);
			request.setAttribute("errorString", errorString);
			request.setAttribute("showAlert",
					" window.setTimeout(function() {\n" + "	    $(\".alert\").fadeTo(500, 0).slideUp(500, function(){\n"
							+ "	        $(this).remove(); \n" + "	    });\n" + "	}, 4000);");

			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/forgotPasswordView.jsp");
			dispatcher.forward(request, response);
		}
	}
}
