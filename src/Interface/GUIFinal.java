package Interface;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipFile;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import General.SharedClass;
import General.Variable;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class GUIFinal extends JFrame {

	private SharedClass shared;
	private JPanel contentPane;
	private JTextField TFName;
	private JTextField TFDays;
	private JTextArea TADescription;
	private JTextArea TAVariables;
	private JTextArea TAAlgorit;
	private JTextField TFBrowse;
	private JButton ButtonBrowse;

	public GUIFinal(SharedClass shared) {
		this.shared = shared;
		// teste

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(100);
		contentPane.add(progressBar);
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		JLabel LabelProblemReview = new JLabel("Problem Review");
		LabelProblemReview.setBounds(32, 0, 265, 28);
		LabelProblemReview.setForeground(new Color(255, 255, 255));
		LabelProblemReview.setFont(new Font("Avenir Next", Font.BOLD, 21));
		contentPane.add(LabelProblemReview);

		JLabel LabelName = new JLabel("Name:");
		LabelName.setBounds(34, 34, 58, 20);
		LabelName.setForeground(new Color(255, 255, 255));
		LabelName.setFont(new Font("Avenir Next", Font.BOLD, 17));
		contentPane.add(LabelName);

		TFName = new JTextField();
		TFName.setBounds(34, 53, 183, 28);
		contentPane.add(TFName);
		TFName.setColumns(10);
		TFName.setEnabled(false);
		TFName.setText(shared.getProblem().getName());

		JLabel LabelDescription = new JLabel("Description:");
		LabelDescription.setBounds(34, 83, 116, 20);
		LabelDescription.setForeground(new Color(255, 255, 255));
		LabelDescription.setFont(new Font("Avenir Next", Font.BOLD, 17));
		contentPane.add(LabelDescription);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 104, 633, 65);
		contentPane.add(scrollPane);

		TADescription = new JTextArea();
		TADescription.setEnabled(false);
		scrollPane.setViewportView(TADescription);
		TADescription.setText(shared.getProblem().getDescription());

		JLabel LabelVariables = new JLabel("Variables:");
		LabelVariables.setBounds(34, 181, 90, 20);
		LabelVariables.setForeground(new Color(255, 255, 255));
		LabelVariables.setFont(new Font("Avenir Next", Font.BOLD, 17));
		contentPane.add(LabelVariables);

		JLabel LabelAlgori = new JLabel("Algorithms chosen:");
		LabelAlgori.setBounds(343, 181, 212, 20);
		LabelAlgori.setForeground(new Color(255, 255, 255));
		LabelAlgori.setFont(new Font("Avenir Next", Font.BOLD, 17));
		contentPane.add(LabelAlgori);

		JSeparator separator = new JSeparator();
		separator.setBounds(34, 22, 633, 12);
		separator.setForeground(new Color(47, 79, 79));
		contentPane.add(separator);

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setBounds(34, 401, 53, 35);
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 3);
			}
		});
		contentPane.add(BotaoBack);

		TFDays = new JTextField();
		TFDays.setBounds(287, 53, 58, 28);
		TFDays.setColumns(10);
		TFDays.setEnabled(false);
		contentPane.add(TFDays);

		JLabel LabelNeedsToBeSolved = new JLabel("Needs to be solved in:");
		LabelNeedsToBeSolved.setBounds(287, 34, 212, 20);
		LabelNeedsToBeSolved.setForeground(new Color(255, 255, 255));
		LabelNeedsToBeSolved.setFont(new Font("Avenir Next", Font.BOLD, 17));
		contentPane.add(LabelNeedsToBeSolved);

		JLabel LabelDays = new JLabel("days");
		LabelDays.setBounds(343, 58, 58, 20);
		LabelDays.setForeground(new Color(255, 255, 255));
		LabelDays.setFont(new Font("Avenir Next", Font.BOLD, 14));
		contentPane.add(LabelDays);

		JButton ButtonSave = new JButton("Save & Send");
		ButtonSave.setBounds(507, 384, 166, 52);
		ButtonSave.setForeground(new Color(0, 128, 128));
		ButtonSave.setFont(new Font("Avenir Next", Font.BOLD, 20));
		contentPane.add(ButtonSave);
		ButtonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.writeXmlFile(shared.getProblem());

			}
		});

		JScrollPane SPVariables = new JScrollPane();
		SPVariables.setBounds(34, 200, 212, 111);
		contentPane.add(SPVariables);

		TAVariables = new JTextArea();
		TAVariables.setEnabled(false);
		SPVariables.setViewportView(TAVariables);

		JScrollPane SPAlgori = new JScrollPane();
		SPAlgori.setBounds(343, 200, 212, 111);
		contentPane.add(SPAlgori);

		TAAlgorit = new JTextArea();
		TAAlgorit.setEnabled(false);
		SPAlgori.setViewportView(TAAlgorit);

		JLabel LabelInformative = new JLabel("Select a evaluative .jar file:");
		LabelInformative.setFont(new Font("Avenir Next", Font.BOLD, 14));
		LabelInformative.setForeground(new Color(255, 255, 255));
		LabelInformative.setBounds(34, 323, 278, 16);
		contentPane.add(LabelInformative);

		ButtonBrowse = new JButton("Browse");
		ButtonBrowse.setForeground(new Color(0, 128, 128));
		ButtonBrowse.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		ButtonBrowse.setBounds(32, 341, 90, 29);
		contentPane.add(ButtonBrowse);
		ButtonBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		TFBrowse = new JTextField();
		TFBrowse.setForeground(new Color(47, 79, 79));
		TFBrowse.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		TFBrowse.setBounds(123, 341, 376, 26);
		TFBrowse.setColumns(10);
		if (shared.getVerifyLoad() == true) {
			TFBrowse.setText(shared.getProblem().getPath());
		}
		contentPane.add(TFBrowse);

		JLabel LabelLogo = new JLabel();
		LabelLogo.setBounds(0, 0, 700, 478);
		LabelLogo.setIcon(imageIcon);
		contentPane.add(LabelLogo);
	} 
	

	public void search() {
		JFileChooser fileChooser = new JFileChooser();
		StringBuilder sb = new StringBuilder();
		File file = new File("");
		;

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}
		try {
			if (isJarFile(file)) {
				TFBrowse.setText(file.getPath());
				shared.getProblem().setPath(file.getPath());
			} else {
				JOptionPane.showMessageDialog(null, "The file must be .jar");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean isJarFile(File file) throws IOException {
		if (!isZipFile(file)) {
			return false;
		}
		ZipFile zip = new ZipFile(file);
		boolean manifest = zip.getEntry("META-INF/MANIFEST.MF") != null;
		zip.close();
		return manifest;
	}

	public static boolean isZipFile(File file) throws IOException {
		if (file.isDirectory()) {
			return false;
		}
		if (!file.canRead()) {
			throw new IOException("Cannot read file " + file.getAbsolutePath());
		}
		if (file.length() < 4) {
			return false;
		}
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		int test = in.readInt();
		in.close();
		return test == 0x504b0304;
	}

	public JTextField getTFBrowse() {
		return TFBrowse;
	}

	public void setTFBrowse(String path) {
		TFBrowse.setText(path);
	}

	public JTextField getTFName() {
		return TFName;
	}

	public void setTFName(JTextField tFName) {
		TFName = tFName;
	}

	public JTextField getTFDays() {
		return TFDays;
	}

	public void setTFDays(JTextField tFDays) {
		TFDays = tFDays;
	}

	public JTextArea getTADescription() {
		return TADescription;
	}

	public void setTADescription(JTextArea tADescription) {
		TADescription = tADescription;
	}

	public JTextArea getTAVariables() {
		return TAVariables;
	}

	public void setTAVariables(JTextArea tAVariables) {
		TAVariables = tAVariables;
	}

	public JTextArea getTAAlgorit() {
		return TAAlgorit;
	}

	public void setTAAlgorit(JTextArea tAAlgorit) {
		TAAlgorit = tAAlgorit;
	}

	public JPanel getContentPane() {
		return contentPane;
	}
}
