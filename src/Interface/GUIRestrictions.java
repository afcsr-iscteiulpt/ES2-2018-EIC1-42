package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import General.SharedClass;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class GUIRestrictions extends JFrame {

	private SharedClass shared;
	private JPanel contentPane;
	private JTextField TFName;
	private JTextField TFRestrictions;
	private DefaultListModel<String> model;
	private JList<String> list;
	private JCheckBox checkBox;
	private JLabel LabelName;
	private JLabel LabelInterval;

	public GUIRestrictions(SharedClass shared) {
		this.shared = shared;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(34, 448, 633, 20);
		progressBar.setValue(60);
		contentPane.add(progressBar);

		JButton BotaoBack = new JButton("◀");
		BotaoBack.setBounds(34, 401, 53, 35);
		BotaoBack.setForeground(new Color(0, 128, 128));
		BotaoBack.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 1);
			}
		});
		contentPane.add(BotaoBack);

		JButton BotaoNext = new JButton("▶");
		BotaoNext.setBounds(614, 401, 53, 35);
		BotaoNext.setForeground(new Color(0, 128, 128));
		BotaoNext.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		BotaoNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shared.setExistingPanel(shared.getNPArray(), 3);

			}
		});
		contentPane.add(BotaoNext);

		TFName = new JTextField();
		TFName.setEnabled(false);
		TFName.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		TFName.setBounds(349, 129, 221, 35);
		contentPane.add(TFName);
		TFName.setColumns(10);

		LabelName = new JLabel("Variable selected:");
		LabelName.setForeground(new Color(47, 79, 79));
		LabelName.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		LabelName.setBounds(352, 111, 154, 16);
		contentPane.add(LabelName);

		LabelInterval = new JLabel("Remove values from interval:");
		LabelInterval.setForeground(new Color(47, 79, 79));
		LabelInterval.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		LabelInterval.setBounds(352, 183, 267, 16);
		contentPane.add(LabelInterval);

		TFRestrictions = new JTextField();
		TFRestrictions.setEnabled(false);
		TFRestrictions.setFont(new Font("Avenir Next", Font.PLAIN, 9));
		TFRestrictions.setColumns(10);
		TFRestrictions.setBounds(349, 198, 318, 35);
		contentPane.add(TFRestrictions);

		checkBox = new JCheckBox("Set as objective");
		checkBox.setForeground(new Color(47, 79, 79));
		checkBox.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		checkBox.setBounds(349, 244, 210, 23);

		contentPane.add(checkBox);

		Border border = BorderFactory.createLineBorder(new Color(0, 128, 128));
		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setForeground(new Color(47, 79, 79));
		list.setBounds(36, 101, 285, 276);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				validateCheckBox();
				TFName.setText(list.getSelectedValue());
				convertIntervalToString(list.getSelectedValue());
			}
		});
		list.setBorder(border);
		contentPane.add(list);

		JButton ButtonSet = new JButton("Set restriction");
		ButtonSet.setForeground(new Color(0, 128, 128));
		ButtonSet.setBounds(349, 289, 130, 29);
		ButtonSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateChanges();

			}
		});
		contentPane.add(ButtonSet);

		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("GenericPage.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));

		JLabel LabelRestrictions = new JLabel("Apply your restrictions");
		LabelRestrictions.setForeground(Color.WHITE);
		LabelRestrictions.setFont(new Font("Avenir Next", Font.BOLD, 29));
		LabelRestrictions.setBounds(34, 24, 445, 49);
		contentPane.add(LabelRestrictions);
		JLabel LabelLogo = new JLabel();
		LabelLogo.setBounds(0, 0, 700, 478);
		LabelLogo.setIcon(imageIcon);
		contentPane.add(LabelLogo);

	}

	public void convertIntervalToString(String var) {
		boolean b = false;
		String intervalString = "[ ";
		for (int i = 0; i < shared.getProblem().getVariablesArray().size(); i++) {
			if (shared.getProblem().getVariablesArray().get(i).getName().equals(var)) {
				if (shared.getProblem().getVariablesArray().get(i).getType().equals("Integer")) {
					int min = shared.getProblem().getVariablesArray().get(i).getMinI();
					int max = shared.getProblem().getVariablesArray().get(i).getMaxI();
					for (int j = min; j <= max; j++) {
						if (j != max) {
							intervalString += j + " , ";
						} else {
							intervalString += j + " ]";
						}
					}
				}
				if (shared.getProblem().getVariablesArray().get(i).getType().equals("Double")) {
					double min = shared.getProblem().getVariablesArray().get(i).getMinD();
					double max = shared.getProblem().getVariablesArray().get(i).getMaxD();
					for (double j = min; j <= max; j++) {
						if (j != max) {
							intervalString += j + " , ";
						} else {
							intervalString += j + " ]";
						}
					}
				}
			}
		}
		TFRestrictions.setText(intervalString);

	}

	public void validateChanges() {
		String name = TFName.getText();
		if (checkBox.isSelected()) {
			for (int i = 0; i < shared.getProblem().getVariablesArray().size(); i++) {
				if (shared.getProblem().getVariablesArray().get(i).getName().equals(name)) {
					shared.getProblem().getVariablesArray().get(i).setObjective(true);
				}
			}
		}
	}

	public void validateCheckBox() {
		String name = TFName.getText();
		for (int i = 0; i < shared.getProblem().getVariablesArray().size(); i++) {
			if (shared.getProblem().getVariablesArray().get(i).getName().equals(name)) {
				checkBox.setSelected(shared.getProblem().getVariablesArray().get(i).isObjective());
			}
		}
	}

	public void it_is_a_Binary() {
		LabelName.setVisible(false);
		TFName.setVisible(false);
		LabelInterval.setVisible(false);
		TFRestrictions.setVisible(false);
		
	}

	public DefaultListModel<String> getModel() {
		return model;
	}

	public JPanel getContentPane() {
		return contentPane;
	}
}
