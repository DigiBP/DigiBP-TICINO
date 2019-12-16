package ch.fhnw.digibp.config;

import javax.annotation.PostConstruct;
import com.sun.mail.smtp.SMTPTransport;

import org.springframework.beans.factory.annotation.Autowired;

import ch.fhnw.digibp.config.MessageSender;
import ch.fhnw.digibp.config.yaml.MailProperties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MessageSender {
    @Autowired
    private MailProperties mailProperties;

    protected Message msg;
    protected Session session;

    @PostConstruct
    private void setUp()
    {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        session = Session.getInstance(prop, null);
        msg = new MimeMessage(session);
    }

    protected void sendMessage( String recipientEmail ) throws MessagingException
    {
        // from
        msg.setFrom(new InternetAddress(mailProperties.getSender()));

        // to 
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        msg.setSentDate(new Date());

        // Get SMTPTransport
        SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
        // connect
        t.connect(mailProperties.getServer(), mailProperties.getUsername(), mailProperties.getPassword());
        
        // send
        t.sendMessage(msg, msg.getAllRecipients());

        System.out.println("Response: " + t.getLastServerResponse());

        t.close();
    }
}