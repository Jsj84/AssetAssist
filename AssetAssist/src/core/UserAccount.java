package core;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class UserAccount {

	private String userName;
	private byte[] password;
	private byte[] salt;
	private String token;
	private String timeStamp;
	private String firstName;
	private String lastName;
	private String time_zone;
	private String phone_number;
	private String theme;
	private String email;
	private byte[] profilePic;
	private String pic;

	public UserAccount() {

	}

	public UserAccount(String user, byte[] pass, byte[] salt, String firstName, String lastName, String timeZone,
			String phoneNumber, String email) {
		this.userName = user;
		this.password = pass;
		this.salt = salt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.time_zone = timeZone;
		this.phone_number = phoneNumber;
		this.email = email;
	}

	public UserAccount(String user, byte[] pass, byte[] salt, String firstName) {
		this.userName = user;
		this.password = pass;
		this.salt = salt;
		this.firstName = firstName;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String string) {
		this.theme = string;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public byte[] getSalt() {
		return salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
		this.pic = Base64.encode(profilePic).toString();
	}

	public String getPic() {
		return pic;
	}
}