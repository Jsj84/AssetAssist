package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

@WebServlet(urlPatterns = { "/editProduct" })
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditProductServlet() {
		super();
	}

	// Show product edit page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = MyUtils.getStoredConnection(request);

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		String code = request.getParameter("asset_id");

		Product product = null;

		String errorString = null;

		try {
			product = DBUtils.findProduct(conn, code);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (errorString != null && product == null) {
			response.sendRedirect(request.getServletPath() + "/productList");
			return;
		}

		// Store errorString in request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);
		request.setAttribute("user", user);
		request.setAttribute("page", "editPrdoductView.jsp");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);

	}

	// After the user modifies the product information, and click Submit.
	// This method will be executed.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = MyUtils.getStoredConnection(request);

		String asset_id = request.getParameter("asset_id");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		float price = 0;

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		try {
			price = Float.parseFloat(priceStr);
		} catch (Exception e) {
		}
		Product product = new Product(asset_id, name, price);

		String errorString = null;

		try {
			DBUtils.updateProduct(conn, product);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// If error, forward to Edit page.
		if (errorString != null) {
			request.setAttribute("page", "editProductView.jsp");
			request.setAttribute("errorString", errorString);
		}

		else {
			request.setAttribute("errorString", errorString);
			request.setAttribute("page", "productListView.jsp");
		}

		request.setAttribute("product", product);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

}