package email;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

public class EmailSender {
	
	final String ood1mail = "ood1.0chief@gmail.com";
    final String password = "OOD1.0admin";
    
    private Properties props = new Properties();
    
    private String UserMail;
    private String ProblemName;
    private String Adminmail;
    private String XMLPath;
    
	
	public EmailSender(String usermail, String problemName, String adminmail, String XMLpath) throws AddressException, MessagingException {
		
		props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    UserMail=usermail;
	    ProblemName=problemName;
	    Adminmail=adminmail;
	    XMLPath=XMLpath;
		
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(ood1mail, password);
                  }
                });
        
        try {
        	
        	InternetAddress From = new InternetAddress(ood1mail);
        	InternetAddress[] CClist = InternetAddress.parse(Adminmail);

            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(usermail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("Otimiza��o em curso: " + problemName +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            String Message= "Muito obrigado por usar esta plataforma de otimiza��o. Ser� informado por email sobre o progresso de otimiza��o,"
            		+ " quando o processo de otimiza��o tiver atingido 25%, 50%, 75% do total do tempo estimado, e tamb�m quando o processo tiver"
            		+ " terminado, com sucesso ou devido � ocorr�ncia de erros.";
            
            Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(XMLpath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(problemName + "-XML");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
            
            

            Transport.send(message);

            System.out.println("Message Sent .");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void EmailCheck(int percentage) {
		
		Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(ood1mail, password);
                  }
                });
        
        try {
        	
        	InternetAddress From = new InternetAddress(ood1mail);
        	InternetAddress[] CClist = InternetAddress.parse(Adminmail);

            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(UserMail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("Actualiza��o Estado Otimiza��o " + percentage +"% : " + ProblemName +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            String Message= "Muito obrigado por usar esta plataforma de otimiza��o. Informamos-lhe que o seu "
            		+ "problema est� ainda em fase de processamento. Neste momento complet�mos cerca de " + percentage +"% "
            		+ "do processo de otimiza��o. Ser� informado futuramente das pr�ximas fases.";
            
            Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
            
            Transport.send(message);

            System.out.println("Message Sent.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void EmailFinish(String finishXML) {
		
		Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(ood1mail, password);
                  }
                });
        
        try {
        	
        	InternetAddress From = new InternetAddress(ood1mail);
        	InternetAddress[] CClist = InternetAddress.parse(Adminmail);

            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(UserMail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("Processo de Otimiza��o finalizado: " + ProblemName +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            String Message= "Muito obrigado por usar esta plataforma de otimiza��o. "
            		+"Informamos que o processo de otimiza��o acabou. Poder� ver uma detalhada an�lise "
            		+"do processo de otimiza��o do problema assim como a sua configura��o inicial. "
            		+"Agradecemos muito por depositar a sua confian�a na nossa plataforma.";
            
            Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource configFile = new FileDataSource(XMLPath);
			messageBodyPart.setDataHandler(new DataHandler(configFile));
			messageBodyPart.setFileName(ProblemName + "-XML");
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			DataSource finishFile = new FileDataSource(XMLPath);
			messageBodyPart.setDataHandler(new DataHandler(finishFile));
			messageBodyPart.setFileName(ProblemName + "-FinishStats");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
            
            Transport.send(message);

            System.out.println("Message Sent .");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
	}
	

	public static void main(String[] args) throws AddressException, MessagingException {
		//Testing function
		System.out.println("1");
		EmailSender A= new EmailSender("albertosilveiramos@gmail.com", "EmailTesting","bfcca@iscte-iul.pt" , "File.xml");
		System.out.println("2");
		A.EmailCheck(50);
		System.out.println("3");
		A.EmailFinish("File.xml");
    }

}
