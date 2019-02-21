package sm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Pwd_Update extends JFrame {

	private JPanel contentPane;
	private JPasswordField Pwd0;
	private JPasswordField Pwd1;
	private JPasswordField Pwd2;
	private StuBasicInfo stu = StudentManagement.stu;

	/**
	 * Create the frame.
	 */
	public Pwd_Update() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 349, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPwd0 = new JLabel("\u539F\u5BC6\u7801");
		lblPwd0.setBounds(46, 39, 43, 15);
		contentPane.add(lblPwd0);

		JLabel lblPwd1 = new JLabel("\u65B0\u5BC6\u7801");
		lblPwd1.setBounds(46, 79, 43, 15);
		contentPane.add(lblPwd1);

		JLabel lblPwd2 = new JLabel("\u65B0\u5BC6\u7801");
		lblPwd2.setBounds(46, 121, 43, 15);
		contentPane.add(lblPwd2);

		Pwd0 = new JPasswordField();
		Pwd0.setBounds(112, 36, 168, 21);
		contentPane.add(Pwd0);

		Pwd1 = new JPasswordField();
		Pwd1.setBounds(112, 76, 168, 21);
		contentPane.add(Pwd1);

		Pwd2 = new JPasswordField();
		Pwd2.setBounds(112, 118, 168, 21);
		contentPane.add(Pwd2);

		JButton UpdateConfirm = new JButton("\u786E\u8BA4");
		UpdateConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					ResultSet rs = null;
					String s0 = "";
					try {
						rs = stat.executeQuery("select * from AdminLogin where ID = " + stu.ID);
						if (rs.next()) {
							s0 = rs.getString(2);

							if (s0.equals(String.valueOf(Pwd0.getPassword()))) {
								if (String.valueOf(Pwd1.getPassword()).equals(String.valueOf(Pwd2.getPassword()))) {
									stat.execute("Update AdminLogin Set UserPwd = '"
											+ String.valueOf(Pwd2.getPassword()) + "'" + "where ID = " + stu.ID);
									JOptionPane.showMessageDialog(null, "密码修改成功");
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "新密码不一致");
									Pwd1.setText("");
									Pwd2.setText("");
								}
							} else
								JOptionPane.showMessageDialog(null, "原密码错误");
							Pwd0.setText("");
							Pwd1.setText("");
							Pwd2.setText("");
						}

						else {
							rs = stat.executeQuery("select * from StuLogin where ID = " + stu.ID);
							int i = 2;
							if (rs.next()) {
								s0 = rs.getString(i);
								if (s0.equals(String.valueOf(Pwd0.getPassword()))) {
									if (String.valueOf(Pwd1.getPassword()).equals(String.valueOf(Pwd2.getPassword()))) {
										stat.execute("Update StuLogin Set UserPwd = '"
												+ String.valueOf(Pwd2.getPassword()) + "'" + "where ID = " + stu.ID);
										JOptionPane.showMessageDialog(null, "密码修改成功");
										dispose();
									} else {
										JOptionPane.showMessageDialog(null, "新密码不一致");
										Pwd1.setText("");
										Pwd2.setText("");
									}
								} else
									JOptionPane.showMessageDialog(null, "原密码错误");
								Pwd0.setText("");
								Pwd1.setText("");
								Pwd2.setText("");
							}

						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "密码修改失败");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		UpdateConfirm.setBounds(44, 162, 93, 23);
		contentPane.add(UpdateConfirm);

		JButton UpdateCancel = new JButton("\u53D6\u6D88");
		UpdateCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		UpdateCancel.setBounds(191, 162, 93, 23);
		contentPane.add(UpdateCancel);

	}
}
