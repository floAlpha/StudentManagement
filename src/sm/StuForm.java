package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StuForm extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = 3701393107314219193L;
	private JPanel contentPane;
	private JTextField textField;
	public static StuBasicInfo sbi = new StuBasicInfo();

	/**
	 * Create the frame.
	 */
	public StuForm() {
		setResizable(false);
		setType(Type.POPUP);

		setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setToolTipText("");
		textField.setBounds(224, 31, 183, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(21, 59, 403, 6);
		contentPane.add(separator);

		JButton btnStuAdd = new JButton("\u6DFB\u52A0");
		btnStuAdd.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				StuConfig sc = new StuConfig(2);
				sc.show();
			}
		});
		btnStuAdd.setBounds(29, 313, 69, 23);
		contentPane.add(btnStuAdd);

		JButton btnStuUpdate = new JButton("\u4FEE\u6539");
		btnStuUpdate.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				StuConfig sc = new StuConfig(1);
				sc.show();
			}
		});
		btnStuUpdate.setBounds(129, 313, 69, 23);
		contentPane.add(btnStuUpdate);

		JButton btnStuDelete = new JButton("\u5220\u9664");
		btnStuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确定要删除该学生信息吗,此操作将一并删除该学生登陆本系统的帐号以及选课记录！", "警告",
						JOptionPane.YES_NO_OPTION) == 0) {
					String sql0 = "delete from StuLogin where ID = " + sbi.ID;
					String sql1 = "delete from StuInfo where ID = " + sbi.ID;
					String sql2 = "delete from Sc where ID = " + sbi.ID;
					try {
						Connection con = SQLHelper.getConnection();
						Statement stat = con.createStatement();
						stat.execute(sql0);
						stat.execute(sql1);
						stat.execute(sql2);
						JOptionPane.showMessageDialog(null, "删除成功");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnStuDelete.setBounds(237, 313, 69, 23);
		contentPane.add(btnStuDelete);

		JButton btnSearch = new JButton("\u8F93\u5165\u5B66\u53F7\u6216\u59D3\u540D\u67E5\u8BE2\u5B66\u751F");

		btnSearch.setBounds(26, 30, 172, 23);
		contentPane.add(btnSearch);

		JLabel StuID = new JLabel("\u5B66\u53F7");
		StuID.setBounds(31, 75, 50, 15);
		contentPane.add(StuID);

		JLabel StuName = new JLabel("\u59D3\u540D");
		StuName.setBounds(31, 100, 50, 15);
		contentPane.add(StuName);

		JLabel StuSex = new JLabel("\u6027\u522B");
		StuSex.setBounds(31, 125, 50, 15);
		contentPane.add(StuSex);

		JLabel StuBirthday = new JLabel("\u751F\u65E5");
		StuBirthday.setBounds(31, 150, 50, 15);
		contentPane.add(StuBirthday);

		JLabel StuIDcard = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		StuIDcard.setBounds(29, 175, 60, 15);
		contentPane.add(StuIDcard);

		JLabel StuBrief = new JLabel("\u7B80\u4ECB");
		StuBrief.setBounds(31, 225, 50, 15);
		contentPane.add(StuBrief);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(80, 225, 327, 62);
		contentPane.add(textArea);

		JButton btnStuCancel = new JButton("\u53D6\u6D88");
		btnStuCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnStuCancel.setBounds(338, 313, 69, 23);
		contentPane.add(btnStuCancel);

		JLabel lblID = new JLabel("");
		lblID.setBounds(99, 75, 153, 15);
		contentPane.add(lblID);

		JLabel lblName = new JLabel("");
		lblName.setBounds(99, 100, 153, 15);
		contentPane.add(lblName);

		JLabel lblSex = new JLabel("");
		lblSex.setBounds(99, 125, 153, 15);
		contentPane.add(lblSex);

		JLabel lblBirthday = new JLabel("");
		lblBirthday.setBounds(99, 150, 153, 15);
		contentPane.add(lblBirthday);

		JLabel lblIDcard = new JLabel("");
		lblIDcard.setBounds(99, 175, 153, 15);
		contentPane.add(lblIDcard);

		JLabel label = new JLabel("\u9662\u7CFB");
		label.setBounds(31, 200, 50, 15);
		contentPane.add(label);

		JLabel lblDept = new JLabel("");
		lblDept.setBounds(99, 200, 153, 15);
		contentPane.add(lblDept);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImagePanel ip = null;
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					ResultSet rs = null;
					String s = textField.getText();
					boolean b = false;
					try {
						Integer.parseInt(s);

						try {
							rs = null;
							rs = stat.executeQuery("select * from StuInfo where ID = " + s);

							if (rs.next()) {
								sbi.ID = rs.getString("ID");
								sbi.Name = rs.getString("Sname");
								sbi.Sex = rs.getString("Ssex");
								sbi.Department = rs.getString("Sdept");
								sbi.Birthday = rs.getString("Sbirth");
								sbi.Photo = rs.getString("Sphoto");
								sbi.IDcard = rs.getString("IDcard");
								sbi.Brief = rs.getString("Sbrief");
								b = true;
							} else
								JOptionPane.showMessageDialog(null, "没有找到该学生");

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "没有找到该学生");
						}
						lblID.setText(sbi.ID);
						lblName.setText(sbi.Name);
						lblSex.setText(sbi.Sex);
						lblBirthday.setText(sbi.Birthday);
						lblDept.setText(sbi.Department);
						lblIDcard.setText(sbi.IDcard);
						textArea.setText(sbi.Brief);
						// JOptionPane.showMessageDialog(null, sbi.Brief);
						if (sbi.Photo != null) {
							try {
								ip = new ImagePanel(ImageIO.read(new FileInputStream(sbi.Photo)));
								ip.setSize(90, 120);
								ip.setLocation(285, 80);
								getContentPane().add(ip);
								b = false;
								ip.updateUI();
							} catch (FileNotFoundException e3) {

								e3.printStackTrace();
							} catch (IOException e3) {

							}
						} else if (b) {
							ip = new ImagePanel(ImageIO.read(new FileInputStream("E:\\Picture\\default.jpg")));
							ip.setSize(90, 120);
							ip.setLocation(285, 80);
							getContentPane().add(ip);
							ip.updateUI();

						}

					} catch (Exception ex) {
						try {
							rs = null;
							rs = stat.executeQuery("select * from StuInfo where Sname = '" + s + "'");
							// JOptionPane.showMessageDialog(null,sbi.Photo );
							if (rs.next()) {
								sbi.ID = rs.getString("ID");
								sbi.Name = rs.getString("Sname");
								sbi.Sex = rs.getString("Ssex");
								sbi.Department = rs.getString("Sdept");
								sbi.Birthday = rs.getString("Sbirth");
								sbi.Photo = rs.getString("Sphoto");
								sbi.IDcard = rs.getString("IDcard");
								sbi.Brief = rs.getString("Sbrief");
								b = true;
							} else
								JOptionPane.showMessageDialog(null, "没有找到该学生");

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "没有找到该学生");
						}
						lblID.setText(sbi.ID);
						lblName.setText(sbi.Name);
						lblSex.setText(sbi.Sex);
						lblBirthday.setText(sbi.Birthday);
						lblDept.setText(sbi.Department);
						lblIDcard.setText(sbi.IDcard);
						textArea.setText(sbi.Brief);
						if (sbi.Photo != null) {
							try {
								ip = new ImagePanel(ImageIO.read(new FileInputStream(sbi.Photo)));
								ip.setSize(90, 120);
								ip.setLocation(285, 80);
								getContentPane().add(ip);
								ip.updateUI();
								b = false;
							} catch (FileNotFoundException e3) {
								e3.printStackTrace();
							} catch (IOException e3) {}

						} else if (b) {
							try {
								ip = new ImagePanel(ImageIO.read(new FileInputStream("E:\\Picture\\default.jpg")));
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							ip.setSize(90, 120);
							ip.setLocation(285, 80);
							getContentPane().add(ip);
							ip.updateUI();
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}

			}
		});

		textArea.setEditable(false);
	}
}
