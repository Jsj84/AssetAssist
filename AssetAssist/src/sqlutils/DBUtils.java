package sqlutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import core.Product;
import core.UserAccount;

public class DBUtils {

	public static UserAccount findUser(Connection conn, String userName) throws Exception {

		String sql = "Select a.User_Name, a.Password, a.salt, a.firstName, a.Token, a.Timestamp, a.theme_type, a.lastName, a.time_zone, a.phone_number, a.email_address, a.profile_picture from USER_ACCOUNT a where a.User_Name = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			byte[] password = rs.getBytes("Password");
			byte[] salt = rs.getBytes("salt");
			String token = rs.getString("Token");
			String timeStamp = rs.getString("Timestamp");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String time_zone = rs.getString("time_zone");
			String phoneNumber = rs.getString("phone_number");
			String theme = rs.getString("theme_type") == null ? theme = "cosmo" : rs.getString("theme_type");
			String email = rs.getString("email_address");
			byte[] profilePic = rs.getBytes("profile_picture") == null ? profilePic = "avatar.png".getBytes()
					: rs.getBytes("profile_picture");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setSalt(salt);
			user.setToken(token);
			user.setTimeStamp(timeStamp);
			user.setFirstName(firstName);
			user.setTheme(theme);
			user.setLastName(lastName);
			user.setTime_zone(time_zone);
			user.setPhone_number(phoneNumber);
			user.setEmail(email);
			user.setProfilePic(profilePic);
			return user;
		}
		return null;
	}

	public static List<UserAccount> queryUser(Connection conn) throws Exception {

		List<UserAccount> users = new ArrayList<>();

		String sql = "Select a.User_Name, a.Password, a.salt, a.firstName, a.Token, a.Timestamp, a.theme_type, a.lastName, a.time_zone, a.phone_number, a.email_address, a.profile_picture from USER_ACCOUNT a";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String userName = rs.getString("User_Name");
			byte[] password = rs.getBytes("Password");
			byte[] salt = rs.getBytes("salt");
			String token = rs.getString("Token");
			String timeStamp = rs.getString("Timestamp");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String time_zone = rs.getString("time_zone");
			String theme = rs.getString("theme_type") == null ? theme = "cosmo" : rs.getString("theme_type");
			String phoneNumber = rs.getString("phone_number");
			String email = rs.getString("email_address");
			byte[] profilePic = rs.getBytes("profile_picture") == null ? profilePic = "avatar.png".getBytes()
					: rs.getBytes("profile_picture");
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setSalt(salt);
			user.setToken(token);
			user.setTimeStamp(timeStamp);
			user.setFirstName(firstName);
			user.setTheme(theme);
			user.setLastName(lastName);
			user.setTime_zone(time_zone);
			user.setPhone_number(phoneNumber);
			user.setEmail(email);
			user.setProfilePic(profilePic);
			users.add(user);
		}
		return users;
	}

	public static UserAccount findEmail(Connection conn, String firstName) throws SQLException {

		String sql = "Select a.firstName from USER_ACCOUNT a where a.firstName =? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, firstName);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			UserAccount user = new UserAccount();
			user.setFirstName(firstName);
			return user;
		}
		return null;
	}

	public static void updateToken(Connection conn, String token, String timestamp, String userID) throws SQLException {
		String sql = "Update USER_ACCOUNT set Token =?, timestamp =? where User_Name =? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, token);
		pstm.setString(2, timestamp);
		pstm.setString(3, userID);
		pstm.executeUpdate();
	}

	public static void updatePassword(Connection conn, String token, byte[] pass, byte[] salt) throws Exception {
		String sql = "Update USER_ACCOUNT set Password =?, salt =? where Token =? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setBytes(1, pass);
		pstm.setBytes(2, salt);
		pstm.setString(3, token);
		pstm.executeUpdate();
	}

	public static void insertUser(Connection conn, UserAccount account) throws Exception {
		String sql = "Insert into USER_ACCOUNT(User_Name, Password, salt, firstName, lastName, time_zone, phone_number, email_address) values (?,?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, account.getUserName());
		pstm.setBytes(2, account.getPassword());
		pstm.setBytes(3, account.getSalt());
		pstm.setString(4, account.getFirstName());
		pstm.setString(5, account.getLastName());
		pstm.setString(6, account.getTime_zone());
		pstm.setString(7, account.getPhone_number());
		pstm.setString(8, account.getEmail());
		pstm.executeUpdate();
	}

	public static void updateUser(Connection conn, UserAccount account) throws Exception {
		String sql = "Update USER_ACCOUNT set firstName=?, lastName=?, phone_number=?, time_zone=?, email_address=? where User_Name =?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, account.getFirstName());
		pstm.setString(2, account.getLastName());
		pstm.setString(3, account.getPhone_number());
		pstm.setString(4, account.getTime_zone());
		pstm.setString(5, account.getEmail());
		pstm.setString(6, account.getUserName());
		pstm.executeUpdate();
	}

	public static void inserTheme(Connection conn, UserAccount account, String theme) throws SQLException {
		String sql = "Update USER_ACCOUNT set theme_type =? where User_Name =? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, theme);
		pstm.setString(2, account.getUserName());
		pstm.executeUpdate();
	}

	public static void insertProfilePic(Connection conn, UserAccount account, byte[] profilePic) throws SQLException {
		String sql = "Update USER_ACCOUNT set profile_picture =? where User_Name =? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setBytes(1, profilePic);
		pstm.setString(2, account.getUserName());
		pstm.executeUpdate();
	}

	public static void deleteUser(Connection conn, String userName) throws SQLException {
		String sql = "Delete from USER_ACCOUNT where User_Name =? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.executeUpdate();
	}

	public static List<Product> queryProduct(Connection conn) throws SQLException, ParseException {
		String sql = "Select a.Code, a.Name, a.Price, a.Catagory, a.Location, a.Serial_Number, a.model, a.Date_Purchased from PRODUCT a ";

		PreparedStatement pstm = conn.prepareStatement(sql);

		ResultSet rs = pstm.executeQuery();
		List<Product> list = new ArrayList<>();
		while (rs.next()) {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			double price = rs.getFloat("Price");
			String catagory = rs.getString("Catagory");
			String location = rs.getString("Location");
			String serialnumber = rs.getString("Serial_Number");
			String model = rs.getString("model");
			String datePurchased = rs.getString("Date_Purchased");
			Product product = new Product();
			product.setCode(code);
			product.setName(name);
			product.setPrice(price);
			product.setCatagory(catagory);
			product.setLocation(location);
			product.setSerialNumber(serialnumber);
			product.setModel(model);
			product.setDatePurchased(datePurchased);
			list.add(product);
		}
		return list;
	}

	public static Product findProduct(Connection conn, String code) throws SQLException {
		String sql = "Select a.Code, a.Name, a.Price, a.Catagory, a.Location, a.Date_Purchased , a.Serial_Number a.model from PRODUCT a where a.Code = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String name = rs.getString("Name");
			String catagory = rs.getString("Catagory");
			double price = rs.getFloat("Price");
			String serialNumber = rs.getString("Serial_Number");
			String model = rs.getString("model");
			String location = rs.getString("Location");
			String date = rs.getString("Date_Purchased");
			int depreciationVal = rs.getInt("depreciationValue");
			Product product = new Product(code, name, catagory, price, serialNumber, model, location, date,
					depreciationVal);
			return product;
		}
		return null;
	}

	public static void updateProduct(Connection conn, Product product) throws SQLException {
		String sql = "Update PRODUCT set Name =?, Price=? where Code=? ";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getName());
		pstm.setDouble(2, product.getPrice());
		pstm.setString(3, product.getCode());
		pstm.executeUpdate();
	}

	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String sql = "Insert into PRODUCT(Code,Name,Price,Catagory,Location,Date_Purchased, Serial_number, model) values (?,?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, product.getCode());
		pstm.setString(2, product.getName());
		pstm.setDouble(3, product.getPrice());
		pstm.setString(4, product.getCatagory());
		pstm.setString(5, product.getLocation());
		pstm.setString(6, product.getDatePurchased());
		pstm.setString(7, product.getSerialNumber());
		pstm.setString(8, product.getModel());
		pstm.executeUpdate();
	}

	public static void deleteProduct(Connection conn, String code) throws SQLException {
		String sql = "Delete From PRODUCT where Code= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, code);
		pstm.executeUpdate();
	}
}
