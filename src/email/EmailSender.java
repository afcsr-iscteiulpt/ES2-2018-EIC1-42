package email;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import General.Administrador;
import General.SharedClass;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

public class EmailSender extends Thread {

	final String ood1mail;
	final String password;

	private Properties props = new Properties();

	private String UserMail;
	private String ProblemName;
	private String Adminmail;
	private String XMLPath;
	private SharedClass shared;

	private int estimado = 70000;

	@Override
	public void run() {
		while (!interrupted()) {
			for (int i = 25; i < 100; i += 25) {
				try {
					sleep(estimado / 4);
					shared.getGUIFinal().getProgressBar().setValue(i);
					shared.getGUIFinal().revalidate();
					shared.getGUIFinal().repaint();
					emailCheck(i);
				} catch (InterruptedException e) {
				}
			}
			try {
				sleep(estimado / 4);
				shared.getGUIFinal().getProgressBar().setValue(100);
				shared.getGUIFinal().revalidate();
				shared.getGUIFinal().repaint();
				emailFinish();
				interrupt();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * 
	 * Classe de envio de email para o cliente sobre o progresso da otimiza��o
	 * 
	 * @param shared
	 * @param ood1mail
	 * @param password
	 * @param usermail
	 * @param problemName
	 * @param adminmail
	 * @param XMLpath
	 * @throws AddressException
	 * @throws MessagingException
	 */

	public EmailSender(SharedClass shared, String ood1mail, String password, String usermail, String problemName,
			String adminmail, String XMLpath) throws AddressException, MessagingException {
		this.ood1mail = ood1mail;
		this.password = password;
		this.shared = shared;

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		UserMail = usermail;
		ProblemName = problemName;
		Adminmail = adminmail;
		XMLPath = XMLpath;

		/*
		 * Cria��o da sess�o e respetiva autentica��o
		 */
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ood1mail, password);
			}
		});

		try {

			InternetAddress From = new InternetAddress(ood1mail);
			InternetAddress[] CClist = InternetAddress.parse(Adminmail);

			/*
			 * Cria��o da mensagem a ser enviada para o utilizador sobre o inicio do
			 * processo de otimiza��o
			 */
			Message message = new MimeMessage(session);
			message.setFrom(From);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usermail));
			for (int i = 0; i < CClist.length; i++)
				message.addRecipient(Message.RecipientType.CC, CClist[i]);
			LocalDateTime now = LocalDateTime.now();
			message.setSubject("Otimização em curso: " + shared.getProblem().getName() + " " + now.getYear() + "-"
					+ now.getMonth() + "-" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute());

			String Message = "Muito obrigado por usar esta plataforma de otimização. Será informado por email sobre o progresso de otimização,"
					+ " quando o processo de otimização tiver atingido 25%, 50%, 75% do total do tempo estimado, e também quando o processo tiver"
					+ " terminado, com sucesso ou devido à ocorrência de erros.";

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);

			/*
			 * Associa��o do ficheiro XML (do progresso feito) ao email a ser enviado
			 * para o utilizador
			 */
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(XMLpath + problemName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(shared.getProblem().getName() + "-XML");

			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Message Sent .");

		} catch (MessagingException e) {

		}
	}

	/**
	 * 
	 * Envio da percentagem do progresso da otimiza��o
	 * 
	 * @param percentage
	 */

	public void emailCheck(int percentage) {

		/*
		 * Cria��o da sess�o e respetiva autentica��o
		 */
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ood1mail, password);
			}
		});

		try {

			InternetAddress From = new InternetAddress(ood1mail);
			InternetAddress[] CClist = InternetAddress.parse(Adminmail);

			/*
			 * Cria��o da mensagem a ser enviada para o utilizador sobre a evolu��o
			 * do processo de otimiza��o
			 */
			Message message = new MimeMessage(session);
			message.setFrom(From);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(UserMail));
			for (int i = 0; i < CClist.length; i++)
				message.addRecipient(Message.RecipientType.CC, CClist[i]);
			LocalDateTime now = LocalDateTime.now();
			message.setSubject("Actualização Estado Otimização " + percentage + "% : "
					+ shared.getProblem().getName() + " " + now.getYear() + "-" + now.getMonth() + "-"
					+ now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute());

			String Message = "Muito obrigado por usar esta plataforma de otimização. Informamos-lhe que o seu "
					+ "problema está ainda em fase de processamento. Neste momento completámos cerca de " + percentage
					+ "% " + "do processo de otimização. Será informado futuramente das próximas fases.";

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

	/**
	 * Envio do email final da otimiza��o para o cliente
	 */

	public void emailFinish() {

		/*
		 * Cria��o da sess�o e respetiva autentica��o
		 */
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ood1mail, password);
			}
		});

		try {

			InternetAddress From = new InternetAddress(ood1mail);
			InternetAddress[] CClist = InternetAddress.parse(Adminmail);

			/*
			 * Cria��o da mensagem a ser enviada para o utilizador sobre o fim do
			 * processo de otimiza��o
			 */
			Message message = new MimeMessage(session);
			message.setFrom(From);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(UserMail));
			for (int i = 0; i < CClist.length; i++)
				message.addRecipient(Message.RecipientType.CC, CClist[i]);
			LocalDateTime now = LocalDateTime.now();
			message.setSubject(
					"Processo de Otimização finalizado: " + shared.getProblem().getName() + " " + now.getYear() + "-"
							+ now.getMonth() + "-" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute());
			String Message = "Muito obrigado por usar esta plataforma de otimização. "
					+ "Informamos que o processo de otimização acabou. Poderá ver uma detalhada análise "
					+ "do processo de otimização do problema assim como a sua configuração inicial. "
					+ "Agradecemos muito por depositar a sua confiança na nossa plataforma.";

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(Message, "text/html");
			multipart.addBodyPart(messageBodyPart);

			/*
			 * Associa��o do ficheiro XML (do progresso feito) ao email a ser enviado
			 * para o utilizador
			 */
			messageBodyPart = new MimeBodyPart();
			DataSource configFile = new FileDataSource(shared.getXMLFinalName());
			messageBodyPart.setDataHandler(new DataHandler(configFile));
			messageBodyPart.setFileName(shared.getProblem().getName() + "-XML");
			multipart.addBodyPart(messageBodyPart);

			/*
			 * Associa��o do ficheiro FinishStats ao email a ser enviado para o
			 * utilizador
			 */

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

}
