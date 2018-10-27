package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

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

@WebServlet(urlPatterns = { "/createProduct" })
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateProductServlet() {
		super();
	}

	// Show product creation page.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserAccount user = null;
		HttpSession session = request.getSession();

		if (session != null) {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				user = DBUtils.findUser(conn, MyUtils.getLoginedUser(session).getUserName());
				// Store info in request attribute, before forward to views
				request.setAttribute("user", user);
				request.setAttribute("page", "createProductView.jsp");

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount loginedUser = MyUtils.getLoginedUser(session);

		if (loginedUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		}

		else {

			String asset_id = request.getParameter("code");
			String name = request.getParameter("name");
			String priceStr = request.getParameter("price");
			String catagory = request.getParameter("catagory");
			String location = request.getParameter("location");
			String date = request.getParameter("date");
			String serialNumber = request.getParameter("serialnumber");
			String model = request.getParameter("modelnumber");
			String depreciationVal = request.getParameter("yearsDep");

			DecimalFormat df = new DecimalFormat();
			DecimalFormatSymbols sfs = new DecimalFormatSymbols();
			sfs.setDecimalSeparator(',');
			df.setDecimalFormatSymbols(sfs);

			String errorString = null;

			try {
				Connection conn = MyUtils.getStoredConnection(request);
				double price = 0;
				if (priceStr != "") {
					price = df.parse(priceStr).doubleValue();
				}

				Product product = new Product(asset_id, name, catagory, price, serialNumber, model, location, date,
						Integer.parseInt(depreciationVal));
				DBUtils.insertProduct(conn, product);

			} catch (SQLException | ParseException e) {
				errorString = e.getMessage();
				e.printStackTrace();
			}

			// If error, forward to Edit page.
			if (errorString != null) {

				// Store information to request attribute, before forward to views.
				request.setAttribute("errorString", errorString);
				request.setAttribute("user", loginedUser);
				request.setAttribute("page", "createProductView.jsp");

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(request, response);
			}

			else {
				request.setAttribute("user", loginedUser);
				request.setAttribute("page", "productListView.jsp");

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/index.jsp");
				dispatcher.forward(request, response);

			}
		}
	}

}
