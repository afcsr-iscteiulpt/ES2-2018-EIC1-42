package email;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EmailFAQ {
	
	final String ood1mail = "ood1.0chief@gmail.com";
    final String password = "OOD1.0admin";
    
    private Properties props = new Properties();
    
    private String UserMail;
    private String FAQSubject;
    private String MessageFAQ;
    
    public EmailFAQ(String usermail, String faqSubj, String messagefaq) {
    	
		props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    UserMail = usermail;
	    FAQSubject = faqSubj;
	    MessageFAQ = messagefaq;
    	
	    Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(ood1mail, password);
                  }
                });
        
        try {
        	
        	InternetAddress From = new InternetAddress(ood1mail);
        	InternetAddress[] CClist = InternetAddress.parse(UserMail);
        	

            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(ood1mail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("FAQ Mail: " + FAQSubject +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            
            Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(MessageFAQ, "text/html");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
            
            Transport.send(message);

			JOptionPane.showMessageDialog(null, "Your question was successfully sent.");


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
