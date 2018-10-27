package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);
		request.setAttribute("user", user);
		request.setAttribute("page", "userObjectView.jsp");

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);

		// Not logged in
		if (user == null) {
			// Redirect to login page.
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		Connection conn = MyUtils.getStoredConnection(request);

		List<Product> p = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();

		try {
			p = DBUtils.queryProduct(conn);

			for (int i = 0; i < p.size(); i++) {
				if (!map.containsKey(p.get(i).getCatagory())) {
					map.put(p.get(i).getCatagory(), 1);

				} else {
					int temp = map.get(p.get(i).getCatagory()) + 1;
					map.replace(p.get(i).getCatagory(), temp);

				}
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.setAttribute("database", p.size());
		request.setAttribute("productList", p);
		request.setAttribute("map", map);
		request.setAttribute("page", "homeView.jsp");

		// If the user has logged in, then forward to the page
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
	}
}
