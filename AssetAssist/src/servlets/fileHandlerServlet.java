package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import core.UserAccount;
import sqlutils.DBUtils;
import sqlutils.MyUtils;

/**
 * Servlet implementation class fileHandlerServlet
 */
@WebServlet("/fileHandler")
@MultipartConfig
public class fileHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public fileHandlerServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InputStream inputStream = null;

		Part filePart = request.getPart("filename");
		if (filePart != null) {

			inputStream = filePart.getInputStream();

		}
		HttpSession session = request.getSession();
		Connection conn = MyUtils.getStoredConnection(request);
		UserAccount user = MyUtils.getLoginedUser(session);

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[(int) filePart.getSize()];
		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
			buffer.flush();
			buffer.close();
		}

		byte[] byteArray = buffer.toByteArray();
		if (inputStream != null) {
			try {
				user.setProfilePic(byteArray);
				DBUtils.insertProfilePic(conn, user, byteArray);
				filePart.delete();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}