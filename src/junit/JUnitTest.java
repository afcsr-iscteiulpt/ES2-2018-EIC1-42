package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import General.Administrador;
import General.Problem;
import General.Variable;
import email.EmailFAQ;
import email.EmailSender;
import validationMethods.IntrevalExclusion;

class JUnitTest {

	Administrador admin	= new Administrador("config.xml");


	@Test
	public void testEmailFAQ() {
		EmailFAQ faq = new EmailFAQ(admin.getEmail(), admin.getPassword(),
				"asdo@aisdha.com", "Problem", "Teste JUnit");
		assertNotNull(faq.getOod1mail());
		assertNotNull(faq.getPassword());
		assertNotNull(faq.getUserMail());
		assertNotNull(faq.getFAQSubject());
		assertNotNull(faq.getMessageFAQ());
	}

	@Test
	public void testEmailSender() {
		try {
			EmailSender faq = new EmailSender(admin.getEmail(), admin.getPassword(), "asdo@aisdha.com", "Problem", admin.getEmail(), "config.xml");
			assertNotNull(faq.getOod1mail());
			assertNotNull(faq.getPassword());
			assertNotNull(faq.getUserMail());
			assertNotNull(faq.getProblemName());
			assertNotNull(faq.getAdminmail());
			assertNotNull(faq.getXMLPath());
			faq.EmailCheck(10);
			faq.EmailFinish("config.xml");
		} catch (MessagingException e) {
		}
	}

	@Test
	public void testAdmin() {
		assertNotNull(admin.getName());
		assertNotNull(admin.getEmail());
		assertNotNull(admin.getPassword());
		assertNotNull(admin.getExperimentDir());
		assertNotNull(admin.getProblemsDir());
		assertNotNull(admin.getGraphicsDir());		
	}

	@Test
	public void testProblem() {
		ArrayList<Variable> variables = new ArrayList<>();
		variables.add(new Variable("name", "type", "value"));
		ArrayList<String> algorithms = new ArrayList<>();
		algorithms.add("NSGAII");
		ArrayList<String> objectivesArray = new ArrayList<>();
		Problem problem = new Problem("name", "Description", "useremail@gmail.com", variables, algorithms, 1, "/teste/", objectivesArray);
		problem.setType("Integer");
		assertNotNull(problem.getName());
		assertNotNull(problem.getDescription());
		assertNotNull(problem.getEmail());
		assertNotNull(problem.getVariablesArray());
		assertNotNull(problem.getAlgorithms());
		assertNotNull(problem.getNumberOfDays());
		assertNotNull(problem.getType());
		assertNotNull(problem.getPath());
	}

	@Test
	public void testVariable() {
		ArrayList<Integer> restrictionsInt = new ArrayList<>();
		Variable varInt = new Variable("varInt", "Integer", 0, 1, restrictionsInt);
		assertNotNull(varInt.getName());
		assertNotNull(varInt.getType());
		assertNotNull(varInt.getMinI());
		assertNotNull(varInt.getMaxI());
		assertEquals(varInt.getMinD(), 0.0);
		assertEquals(varInt.getMaxD(), 0.0);
		assertNull(varInt.getValue());
		assertNotNull(varInt.getMIN());
		assertNotNull(varInt.getMAX());
		assertNotNull(varInt.getRestrictionsInt());
		assertNull(varInt.getRestrictionsDouble());

		ArrayList<Double> restrictionsDouble = new ArrayList<>();
		Variable varDouble = new Variable("varDouble", "Double", 0, 1, restrictionsDouble);
		assertNotNull(varDouble.getName());
		assertNotNull(varDouble.getType());
		assertEquals(varDouble.getMinI(), 0);
		assertEquals(varDouble.getMaxI(), 0);
		assertNotNull(varDouble.getMinD());
		assertNotNull(varDouble.getMaxD());
		assertNull(varDouble.getValue());
		assertNotNull(varDouble.getMIN());
		assertNotNull(varDouble.getMAX());
		assertNull(varDouble.getRestrictionsInt());
		assertNotNull(varDouble.getRestrictionsDouble());

		Variable varBinary = new Variable("varBinaryy", "Binary", "1010");
		assertNotNull(varBinary.getName());
		assertNotNull(varBinary.getType());
		assertEquals(varBinary.getMinI(), 0);
		assertEquals(varBinary.getMaxI(), 0);
		assertEquals(varBinary.getMinD(), 0.0);
		assertEquals(varBinary.getMaxD(), 0.0);
		assertNotNull(varBinary.getValue());
		assertNull(varBinary.getMIN());
		assertNull(varBinary.getMAX());
		assertNull(varBinary.getRestrictionsInt());
		assertNull(varBinary.getRestrictionsDouble());
	}
	
	@Test
	public void testIntervalExclusion() {
		IntrevalExclusion exclutor = new IntrevalExclusion();
		ArrayList<Double> numbers = exclutor.getNumbers("{0.0, 1.0, 2.0}");
		assertTrue(numbers.get(0).equals(0.0));
		assertTrue(numbers.get(1).equals(1.0));
		assertTrue(numbers.get(2).equals(2.0));
		assertFalse(exclutor.isValid("{0.0, 1.0, 2.0"));
		assertFalse(exclutor.isValid("{0.0, 1.0 2.0}"));
	}

}
