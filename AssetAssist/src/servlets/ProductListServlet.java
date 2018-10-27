package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Product;
import core.UserAccount;
import sqlutils.DBUtils;
import sqlutils.MyUtils;

@WebServlet(urlPatterns = { "/productList" })
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductListServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Check User has logged on
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser != null) {

			Connection conn = MyUtils.getStoredConnection(request);

			String errorString = null;
			List<Product> list = null;
			try {
				list = DBUtils.queryProduct(conn);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

			// Store info in request attribute, before forward to views
			request.setAttribute("user", loginedUser);
			request.setAttribute("errorString", errorString);
			request.setAttribute("productList", list);
			request.setAttribute("page", "productListView.jsp");

			// Forward to /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount loginedUser = null;

		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			loginedUser = MyUtils.getLoginedUser(session);
		}

		String h = request.getParameter("assetCode");
		List<Product> list = null;

		if (h != null) {

			Connection conn = MyUtils.getStoredConnection(request);
			try {
				DBUtils.deleteProduct(conn, h);
				list = DBUtils.queryProduct(conn);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}

			if (loginedUser != null) {
				request.setAttribute("user", loginedUser);
				request.setAttribute("productList", list);
				request.setAttribute("page", "productListView.jsp");

				// Forward to /WEB-INF/views/productListView.jsp
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}
	}
}