package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class StuConfig extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -1642080177885093066L;
	protected static final String ConfigAdd = null;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldIDcard;
	private StuBasicInfo sbi = StuForm.sbi;
	String name = "E:\\Picture\\default.jpg";

	/**
	 * Create the frame.
	 */

	public StuConfig(int activity) {
		setResizable(false);
		setTitle("\u6DFB\u52A0\u6216\u8005\u4FEE\u6539\u5B66\u751F\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(90, 133, 210, 21);
		contentPane.add(comboBox);

		JComboBox<String> comYear = new JComboBox<String>();
		comYear.setBounds(90, 102, 54, 21);
		contentPane.add(comYear);

		JComboBox<String> comDay = new JComboBox<String>();
		comDay.setBounds(232, 102, 47, 21);
		contentPane.add(comDay);

		JComboBox<String> comMonth = new JComboBox<String>();

		comMonth.setBounds(168, 102, 39, 21);
		contentPane.add(comMonth);
		for (int g = 1960; g <= 2016; g++) {
			comYear.addItem(String.valueOf(g));
		}
		for (int g = 1; g <= 12; g++) {
			comMonth.addItem(String.valueOf(g));
		}

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("yyyy-MM")
					.parse(comYear.getSelectedItem() + "-" + comMonth.getSelectedItem()));
			for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

				comDay.addItem(String.valueOf(i + 1));

			}
		} catch (ParseException e2) {
			e2.printStackTrace();
		} // 获取时间参数

		comMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {

				try {
					comDay.removeAllItems();
					cal.setTime(new SimpleDateFormat("yyyy-MM")
							.parse(comYear.getSelectedItem() + "-" + comMonth.getSelectedItem()));
					for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {

						comDay.addItem(String.valueOf(i + 1));

					}
					comDay.updateUI();
				} catch (ParseException e2) {
					e2.printStackTrace();
				} // 获取时间参数

			}
		});

		textFieldName = new JTextField();
		textFieldName.setBounds(90, 40, 210, 21);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldIDcard = new JTextField();
		textFieldIDcard.setBounds(90, 164, 210, 21);
		contentPane.add(textFieldIDcard);
		textFieldIDcard.setColumns(10);

		JLabel ConfigName = new JLabel("\u59D3\u540D");
		ConfigName.setBounds(26, 43, 54, 15);
		contentPane.add(ConfigName);

		JLabel ConfigSex = new JLabel("\u6027\u522B");
		ConfigSex.setBounds(26, 74, 54, 15);
		contentPane.add(ConfigSex);

		JLabel ConfigDept = new JLabel("\u9662\u7CFB");
		ConfigDept.setBounds(26, 136, 54, 15);
		contentPane.add(ConfigDept);

		JLabel ConfigIDcard = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		ConfigIDcard.setBounds(26, 167, 54, 15);
		contentPane.add(ConfigIDcard);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(90, 229, 313, 91);
		contentPane.add(textArea);

		JLabel ConfigBrief = new JLabel("\u7B80\u4ECB");
		ConfigBrief.setBounds(26, 229, 54, 15);
		contentPane.add(ConfigBrief);

		JComboBox<String> comboSex = new JComboBox<String>();
		comboSex.setBounds(90, 71, 210, 21);
		comboSex.addItem("男");
		comboSex.addItem("女");
		contentPane.add(comboSex);

		JButton ConfigAdd = new JButton("\u6DFB\u52A0");

		ConfigAdd.setBounds(38, 341, 93, 23);
		contentPane.add(ConfigAdd);

		JButton ConfigCancel = new JButton("\u53D6\u6D88");
		ConfigCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		ConfigCancel.setBounds(297, 341, 93, 23);
		contentPane.add(ConfigCancel);

		JLabel ConfigBirthday = new JLabel("\u751F\u65E5");
		ConfigBirthday.setBounds(26, 105, 54, 15);
		contentPane.add(ConfigBirthday);

		textFieldName.setText(sbi.Name);
		textFieldIDcard.setText(sbi.IDcard);
		textArea.setText(sbi.Brief);

		JButton ConfigAlter = new JButton("\u4FEE\u6539");
		ConfigAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "Update StuInfo set Sname = '" + textFieldName.getText() + "',Ssex = '"
						+ comboSex.getSelectedItem() + "',Sbirth = '" + comYear.getSelectedItem() + "年"
						+ comMonth.getSelectedItem() + "月" + comDay.getSelectedItem() + "日" + "',Sdept = '"
						+ comboBox.getSelectedItem() + "',IDcard = '" + textFieldIDcard.getText() + "',Sbrief = '"
						+ textArea.getText() + "',Sphoto = '" + name + "' where " + "ID = " + sbi.ID;

				try {
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					stat.execute(sql);
					JOptionPane.showMessageDialog(null, "修改成功");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		ConfigAlter.setBounds(168, 341, 93, 23);
		contentPane.add(ConfigAlter);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 201, 393, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel(
				"\u6CE8\u610F\uFF1A\u4E0B\u4E3A\u5FC5\u586B\u9879\uFF0C\u5B66\u53F7\u81EA\u52A8\u751F\u6210");
		lblNewLabel.setBounds(26, 10, 260, 15);
		contentPane.add(lblNewLabel);

		JButton btnUpload = new JButton("\u4E0A\u4F20\u5934\u50CF");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int choose = fc.showOpenDialog(null);
				if (choose == JFileChooser.APPROVE_OPTION) {
					name = fc.getSelectedFile().getAbsolutePath();
				}
				ImagePanel ip = null;
				try {
					ip = new ImagePanel(ImageIO.read(new FileInputStream(name)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ip.setSize(90, 120);
				ip.setLocation(314, 40);
				getContentPane().add(ip);
				ip.updateUI();
			}
		});
		btnUpload.setBounds(310, 163, 93, 23);
		contentPane.add(btnUpload);

		JLabel label_3 = new JLabel("\u5E74");
		label_3.setBounds(154, 102, 24, 21);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u6708");
		label_4.setBounds(217, 102, 24, 21);
		contentPane.add(label_4);

		JLabel label_2 = new JLabel("\u65E5");
		label_2.setBounds(289, 102, 20, 21);
		contentPane.add(label_2);

		ConfigAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String sql = "insert into StuInfo values('" + textFieldName.getText() + "','"
							+ comboSex.getSelectedItem() + "','" + comYear.getSelectedItem() + "年" +

							comMonth.getSelectedItem() + "月" + comDay.getSelectedItem() + "日" + "','"
							+ comboBox.getSelectedItem() + "','" + textFieldIDcard.getText() + "','" + name + "','"
							+ textArea.getText() + "')";

					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					stat.execute(sql);
					con.close();

					Connection con1 = SQLHelper.getConnection();
					Statement stat1 = con1.createStatement();
					ResultSet rs = null;
					String sql1 = "Select * from StuInfo where Sname = '" + textFieldName.getText() + "'";
					stat1.execute(sql1);
					rs = stat1.executeQuery(sql1);
					String s = "";
					while (rs.next()) {
						s = rs.getString(1);

					}
					JOptionPane.showMessageDialog(null, "添加成功，该账户现在可以以学生身份登录本系统，帐号为" + s + ",默认密码为123");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		if (activity == 1) {
			ConfigAdd.setEnabled(false);
		}
		if (activity == 2) {
			ConfigAlter.setEnabled(false);
		}

		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from Deptinfo");
			while (rs.next()) {
				comboBox.addItem(rs.getString("Dname"));
			}
			con.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textFieldIDcard.getDocument().addDocumentListener(new Swing_OnValueChanged());
	}

	class Swing_OnValueChanged implements DocumentListener { // 输出变化及结果
		public void changedUpdate(DocumentEvent e) {
		}

		public void insertUpdate(DocumentEvent e) { // 输出变化及结果
			try {
				Integer.parseInt(textFieldIDcard.getText());
			} catch (Exception e1) {
				for (int i = 0; i < textFieldIDcard.getText().length(); i++) {
					if(textFieldIDcard.getText().charAt(i)>='0'&&textFieldIDcard.getText().charAt(i)<='9')
						continue;
					if (textFieldIDcard.getText().charAt(i) != 'x' && textFieldIDcard.getText().charAt(i) != 'X') {
						JOptionPane.showMessageDialog(null, "身份证号不能含有除数字和X以外的其他字符！");
						break;
					}
				}
			}
		}
		public void removeUpdate(DocumentEvent e) { // 输出变化及结果
		}
	}
}
