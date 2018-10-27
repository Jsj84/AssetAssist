package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class HPAPIServlet
 */
@WebServlet(name = "hpapi", urlPatterns = { "/hpapi" })
public class HPAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HPAPIServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JsonObject hpReturn = null;
		String errorString = null;
		String startDate = null;
		String endDate = null;
		String status = null;
		boolean hasError = false;

		String serialNumber = request.getParameter("serialnumber");
		String model = request.getParameter("modelnumber");
		if (serialNumber != null && model != null) {
			try {
				hpReturn = getWarrantyStatus(serialNumber, model);
				status = hpReturn.get("status").getAsString();

				if (status.contains("Not")) {
					errorString = "We were unable to retrieve your products warrenty information";
					hasError = true;

				} else {
					startDate = hpReturn.get("startDate").getAsString();
					endDate = hpReturn.get("endDate").getAsString();

				}

			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
				hasError = true;
			}
		}
		Map<String, String> map = new HashMap<>();

		if (hasError) {

			map.put("errorString2", errorString);

			// RequestDispatcher dispatcher = request.getServletContext()
			// .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
			// dispatcher.forward(request, response);

		} else {

			map.put("status", status);
			map.put("startDate", startDate);
			map.put("endDate", endDate);

		}
		String json = new Gson().toJson(map);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public String getHPToken() throws Exception {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost postRequest = new HttpPost("https://css.api.hp.com/oauth/v1/token");
		StringEntity input = new StringEntity(
				"apiKey=GUAkG356dNhXXhsYGq8A2UjgVYoGG6SO&apiSecret=xiaC07eqOV6KjJRzXFALb6TNjW4WX2BD&grantType=client_credentials&scope=warranty");
		input.setContentType("application/x-www-form-urlencoded");
		postRequest.setHeader("accept", "application/json");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String token = "", output;
		while ((output = br.readLine()) != null) {
			token += output;
		}
		httpClient.close();
		System.out.println("Token is: " + token);
		JsonObject jsonObject = new JsonParser().parse(token).getAsJsonObject();
		token = jsonObject.get("access_token").getAsString();
		return token;
	}

	public JsonObject getWarrantyStatus(String sn, String pn) throws Exception {

		Map<String, String> map = new HashMap<>();
		map.put("sn", sn);
		map.put("pn", pn);

		Gson json = new Gson();
		String jsonStr = json.toJson(map).toString();

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost("https://css.api.hp.com/productWarranty/v1/queries");

		StringEntity input = new StringEntity("[" + jsonStr + "]");
		input.setContentType("application/json");
		postRequest.setHeader("accept", "application/json");
		postRequest.setHeader("Authorization", "Bearer " + getHPToken());
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		String details = "", output;
		while ((output = br.readLine()) != null) {
			details += output;
		}
		httpClient.close();
		details = details.replace("[", "");
		details = details.replace("]", "");
		System.out.println("Output for warrente status is: " + details);
		JsonObject jsonObject = new JsonParser().parse(details).getAsJsonObject();

		return jsonObject;
	}
}
