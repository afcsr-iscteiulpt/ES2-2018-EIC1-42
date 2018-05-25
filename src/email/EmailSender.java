package email;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import General.Administrador;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

public class EmailSender {
	
	final String ood1mail;
    final String password;
    

	private Properties props = new Properties();
    
    private String UserMail;
    private String ProblemName;
    private String Adminmail;
    private String XMLPath;
//    private static Administrador administrador = new Administrador("config.xml");
	
	public EmailSender(String ood1mail, String password, String usermail, String problemName, String adminmail, String XMLpath) throws AddressException, MessagingException {
		this.ood1mail = ood1mail;
		this.password = password;
		
		props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    UserMail=usermail;
	    ProblemName=problemName;
	    Adminmail=adminmail;
	    XMLPath=XMLpath;
		
	    
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
        	InternetAddress[] CClist = InternetAddress.parse(Adminmail);

        	
        	/*
        	 * Criação da mensagem a ser enviada para o utilizador sobre o inicio do processo de otimização
        	 */
            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(usermail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("Otimizaï¿½ï¿½o em curso: " + problemName +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            String Message= "Muito obrigado por usar esta plataforma de otimizaï¿½ï¿½o. Serï¿½ informado por email sobre o progresso de otimizaï¿½ï¿½o,"
            		+ " quando o processo de otimizaï¿½ï¿½o tiver atingido 25%, 50%, 75% do total do tempo estimado, e tambï¿½m quando o processo tiver"
            		+ " terminado, com sucesso ou devido ï¿½ ocorrï¿½ncia de erros.";
            
            Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);

        	/*
        	 * Associação do ficheiro XML (do progresso feito) ao email a ser enviado para o utilizador
        	 */
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
        	InternetAddress[] CClist = InternetAddress.parse(Adminmail);

        	/*
        	 * Criação da mensagem a ser enviada para o utilizador sobre a evolução do processo de otimização
        	 */
            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(UserMail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("Actualizaï¿½ï¿½o Estado Otimizaï¿½ï¿½o " + percentage +"% : " + ProblemName +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            String Message= "Muito obrigado por usar esta plataforma de otimizaï¿½ï¿½o. Informamos-lhe que o seu "
            		+ "problema estï¿½ ainda em fase de processamento. Neste momento completï¿½mos cerca de " + percentage +"% "
            		+ "do processo de otimizaï¿½ï¿½o. Serï¿½ informado futuramente das prï¿½ximas fases.";
            
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
		
		/*
		 *  Criação da sessão e respetiva autenticação
		 */
		Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(ood1mail, password);
                  }
                });
        
        try {
        	
        	InternetAddress From = new InternetAddress(ood1mail);
        	InternetAddress[] CClist = InternetAddress.parse(Adminmail);

        	/*
        	 * Criação da mensagem a ser enviada para o utilizador sobre o fim do processo de otimização
        	 */
            Message message = new MimeMessage(session);
            message.setFrom(From);
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(UserMail));
            for(int i= 0; i<CClist.length;i++)
            	message.addRecipient(Message.RecipientType.CC,CClist[i]);
            LocalDateTime now = LocalDateTime.now();
            message.setSubject("Processo de Otimizaï¿½ï¿½o finalizado: " + ProblemName +" "+now.getYear()+"-"+now.getMonth()+"-"+now.getDayOfMonth()
            					+" "+ now.getHour()+":"+now.getMinute());
            
            String Message= "Muito obrigado por usar esta plataforma de otimizaï¿½ï¿½o. "
            		+"Informamos que o processo de otimizaï¿½ï¿½o acabou. Poderï¿½ ver uma detalhada anï¿½lise "
            		+"do processo de otimizaï¿½ï¿½o do problema assim como a sua configuraï¿½ï¿½o inicial. "
            		+"Agradecemos muito por depositar a sua confianï¿½a na nossa plataforma.";
            
            Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);
			
        	/*
        	 * Associação do ficheiro XML (do progresso feito)  ao email a ser enviado para o utilizador
        	 */
			messageBodyPart = new MimeBodyPart();
			DataSource configFile = new FileDataSource(XMLPath);
			messageBodyPart.setDataHandler(new DataHandler(configFile));
			messageBodyPart.setFileName(ProblemName + "-XML");
			multipart.addBodyPart(messageBodyPart);
			
        	/*
        	 * Associação do ficheiro FinishStats ao email a ser enviado para o utilizador
        	 */
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
	
	public String getOod1mail() {
		return ood1mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserMail() {
		return UserMail;
	}
	
	public String getProblemName() {
		return ProblemName;
	}
	
	public String getAdminmail() {
		return Adminmail;
	}
	
	public String getXMLPath() {
		return XMLPath;
	}

//	public static void main(String[] args) throws AddressException, MessagingException {
//		/*
//		 * Testing function
//		 */
//		
//		System.out.println("1");
//		EmailSender A= new EmailSender(administrador.getEmail(), administrador.getPassword() ,"albertosilveiramos@gmail.com", "EmailTesting","bfcca@iscte-iul.pt" , "File.xml");
//		System.out.println("2");
//		A.EmailCheck(50);
//		System.out.println("3");
//		A.EmailFinish("File.xml");
//    }

}
