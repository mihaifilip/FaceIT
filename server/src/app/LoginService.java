package app;


import org.parse4j.*;
import org.parse4j.ParseException;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.GetCallback;
import util.LoginMessage;
import util.SignInMessage;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by mihfilip on 14/10/2015.
 */
public class LoginService implements IService {

    public static final String name = LoginService.class.getName();
    public static final String SUCCESS = "SUCCESS";

    public static String handleLoginRequest(LoginMessage message) {

        final String[] callBackMessage = new String[1];

        Parse.initialize("oqyzHQVvY1Zz2oEmEpRbvRneyEo5QrNJuUr0hIFL", "UAKGvtTYDZKmYd0MQlhApviC7lt0dQlqgLE6rMQ2");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("email", message.getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null) {
                    if (users.size() == 0) {
                        callBackMessage[0] =  "Invalid email address " + message.getEmail();
                    } else {
                        String passwordHash = users.get(0).getString("password");
                        String computedHash = hashPassword(message.getPassword());
                        if (!passwordHash.equals(computedHash)) {
                            callBackMessage[0] = "Invalid password";
                        } else {
                            callBackMessage[0] = SUCCESS;
                        }
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

        return callBackMessage[0];
    }

    public static String handleSignInRequest(SignInMessage message) {
        if (message.getEmail() == null || message.getUsername() == null || message.getPassword() == null) {
            return "Invalid information provided";
        }
        //generate token
        String token = UUID.randomUUID().toString().substring(0,6);
        //hash password
        String passwordHash = hashPassword(message.getPassword());
        //store password and token into database
        //todo
        //send token to email address
        sendEmail(message.getEmail(), token);

        return "A token has been sent to your email address.";
    }

    private static String hashPassword(String password) {
        byte[] salt = "thisshouldnotbehere".getBytes();
        byte[] hash = null;
        Random random = new Random();
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 2048, 128);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Base64.Encoder enc = Base64.getEncoder();

        return enc.encodeToString(hash);
    }

    private static void sendEmail(String email, String token) {

        // Sender's email ID needs to be mentioned
        String from = "faceit.misys@gmail.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true"); // added this line
        properties.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, "misyshackathon");
                    }
                });

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("FaceIT registration");

            // Now set the actual message
            message.setText("Thank you for registering to FaceIT! \n \n On your next login attempt, please use the token below: \n" + token);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }
}
