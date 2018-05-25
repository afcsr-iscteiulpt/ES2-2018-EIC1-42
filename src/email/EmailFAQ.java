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
	
	private String ood1mail;
    private String password;
    
    private Properties props = new Properties();
    
    private String UserMail;
    private String FAQSubject;
    private String MessageFAQ;
    
    public EmailFAQ(String ood1mail, String password, String usermail, String faqSubj, String messagefaq) {
    	this.ood1mail = ood1mail;
    	this.password = password;
    	
		props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    UserMail = usermail;
	    FAQSubject = faqSubj;
	    MessageFAQ = messagefaq;
    	
		/*
		 * Criação da sessão e respetiva autenticação
		 */
	    Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(ood1mail, password);
                  }
                });
        
        try {
        	
        	InternetAddress From = new InternetAddress(ood1mail);
        	InternetAddress[] CClist = InternetAddress.parse(UserMail);
        	
        	/*
        	 * Criação da mensagem a ser enviada para a equipa de desenvolvimento do projeto
        	 */
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

	public String getOod1mail() {
		return ood1mail;
	}

	public String getPassword() {
		return password;
	}

	public String getUserMail() {
		return UserMail;
	}

	public String getFAQSubject() {
		return FAQSubject;
	}

	public String getMessageFAQ() {
		return MessageFAQ;
	}

}
