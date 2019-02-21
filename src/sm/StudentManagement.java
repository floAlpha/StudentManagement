package sm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

public class StudentManagement {

	JFrame Login;
	private JTextField textID;
	private JPasswordField textPwd;
	public static int identity;
	final public static StuBasicInfo stu = new StuBasicInfo();

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagement login = new StudentManagement();
					login.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudentManagement() {
		initialize();
		try {
			SQLHelper.getConnection();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "连接数据库失败!");
		}

	}

	private void initialize() {
		Login = new JFrame();
		Login.setForeground(SystemColor.window);
		Login.setResizable(false);
		Login.setTitle("\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF\u767B\u5F55");
		Login.setBounds(0, -29, 400, 320);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		Login.setLocationRelativeTo(null);
		textID = new JTextField();
		textID.setBounds(130, 87, 184, 21);
		Login.getContentPane().add(textID);
		textID.setColumns(10);

		JLabel UserPwd = new JLabel("\u5BC6\u7801");
		UserPwd.setFont(new Font("华文宋体", Font.PLAIN, 14));
		UserPwd.setBounds(73, 140, 47, 17);
		Login.getContentPane().add(UserPwd);

		JLabel UserID = new JLabel("\u5E10\u53F7");
		UserID.setFont(new Font("华文宋体", Font.PLAIN, 14));
		UserID.setBounds(73, 87, 47, 21);
		Login.getContentPane().add(UserID);

		textPwd = new JPasswordField();
		textPwd.setBounds(130, 138, 184, 21);
		Login.getContentPane().add(textPwd);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 183, 184, 21);
		comboBox.addItem("学生");
		comboBox.addItem("教师");
		comboBox.addItem("管理员");
		Login.getContentPane().add(comboBox);

		JButton AllLogin = new JButton("\u767B\u5F55");
		AllLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("管理员")) {
					try {
						boolean b = false;
						Connection con = SQLHelper.getConnection();
						Statement stat = con.createStatement();
						ResultSet rs = null;
						String s1 = "";
						rs = stat.executeQuery("select * from AdminLogin ");
						while (rs.next()) {
							b = true;
							s1 = rs.getString(1);
							if (s1.equals(textID.getText())) {
								String s2 = rs.getString(2);
								if (s2.equals(String.valueOf(textPwd.getPassword()))) {
									JOptionPane.showMessageDialog(null, "登录成功");
									identity = 1;
									{
										ResultSet rs1 = stat.executeQuery("select * from AdminInfo where ID = " + s1);
										while (rs1.next()) {
											{
												stu.ID = rs1.getString(1);
												stu.Name = rs1.getString(2);
											}
										}
									}
									MainForm mf = new MainForm();
									mf.show();
									Thread th = new Thread(mf);
									th.start();
									Login.dispose();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "密码错误");
									break;
								}
							}
							b = false;
						}
						if (!b) {
							JOptionPane.showMessageDialog(null, "没有找到用户");
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

				if (comboBox.getSelectedItem().equals("教师")) {
					try {
						boolean b = false;
						Connection con = SQLHelper.getConnection();
						Statement stat = con.createStatement();
						ResultSet rs = null;
						String s1 = "";
						rs = stat.executeQuery("select * from TeacherLogin");
						while (rs.next()) {
							b = true;
							s1 = rs.getString("ID");
							if (s1.equals(textID.getText())) {
								String s2 = rs.getString(2);
								if (s2.equals(String.valueOf(textPwd.getPassword()))) {
									JOptionPane.showMessageDialog(null, "登录成功");
									{
										ResultSet rs1 = stat.executeQuery("select * from TeacherInfo where ID = " + s1);
										while (rs1.next()) {
											stu.ID = rs1.getString(1);
											stu.Name = rs1.getString(2);
										}
									}
									identity = 2;
									MainForm mf = new MainForm();
									mf.show();
									Thread th = new Thread(mf);
									th.start();
									Login.dispose();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "密码错误");
									break;
								}
							}
							b = false;
						}
						if (!b) {
							JOptionPane.showMessageDialog(null, "没有找到用户");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (comboBox.getSelectedItem().equals("学生")) {
					try {
						boolean b = false;
						Connection con = SQLHelper.getConnection();
						Statement stat = con.createStatement();
						ResultSet rs = null;
						String s1 = "";
						rs = stat.executeQuery("select * from StuLogin");
						while (rs.next()) {
							b = true;
							s1 = rs.getString("ID");
							if (s1.equals(textID.getText())) {
								String s2 = rs.getString(2);
								if (s2.equals(String.valueOf(textPwd.getPassword()))) {
									JOptionPane.showMessageDialog(null, "登录成功");
									{
										ResultSet rs1 = stat.executeQuery("select * from StuInfo where ID = " + s1);
										while (rs1.next()) {
											stu.ID = rs1.getString(1);
											stu.Name = rs1.getString(2);
											stu.Sex = rs1.getString(3);
											stu.Birthday = rs1.getString(4);
											stu.Department = rs1.getString(5);
											stu.IDcard = rs1.getString(6);
											stu.Photo = rs1.getString(7);
											stu.Brief = rs1.getString(8);
										}
									}
									identity = 0;
									MainForm mf = new MainForm();
									mf.show();
									Thread th = new Thread(mf);
									th.start();
									Login.dispose();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "密码错误");
									break;
								}
							}
							b = false;
						}
						if (!b) {
							JOptionPane.showMessageDialog(null, "没有找到用户");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		AllLogin.setBounds(73, 226, 93, 30);
		Login.getContentPane().add(AllLogin);

		JButton Cancel = new JButton("\u9000\u51FA");
		Cancel.setBounds(234, 226, 93, 30);
		Login.getContentPane().add(Cancel);

		JLabel label = new JLabel("\u767B\u5F55\u8EAB\u4EFD");
		label.setBounds(73, 186, 54, 15);
		Login.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF");
		label_1.setForeground(Color.GREEN);
		label_1.setEnabled(false);
		label_1.setFont(new Font("华文彩云", Font.PLAIN, 30));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(54, 10, 286, 56);
		Login.getContentPane().add(label_1);
		textID.getDocument().addDocumentListener(new Swing_OnValueChanged());

	}

	class Swing_OnValueChanged implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
		}

		public void insertUpdate(DocumentEvent e) {
			try {
				Integer.parseInt(textID.getText());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "帐号不能含有除数字以外的其他字符！");
			}
		}

		public void removeUpdate(DocumentEvent e) {
		}
	}
}
