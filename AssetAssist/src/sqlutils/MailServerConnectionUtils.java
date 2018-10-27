package sqlutils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServerConnectionUtils {

	public static void getMailServerSessoin(String userEmailAddress, String token, String timestamp)
			throws ClassNotFoundException, MessagingException {

		String host = "mail.jnjapps.com";
		String userName = "support@jnjapps.com";
		String password = "JnJ@Apps2016!";
		String from = "Do_Not_Reply@jnjapps.com";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getDefaultInstance(properties, auth);
		session.setDebug(true);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(from));

		// Set Subject: header field
		message.setSubject("Password Reset Link!", "UTF-8");
		message.setContent(
				"<p>Reset Password:%20<a href='http://localhost:8080/AssetAssist/passwordReset?token=" + token
						+ "&timestamp=" + timestamp.replace(" ", "%20") + "'" + "target='_top'>Send Mail</a></p>",
				"text/html");
		message.setSentDate(new Date());
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmailAddress, false));
		Transport.send(message);
	}
}
