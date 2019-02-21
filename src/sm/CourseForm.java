package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CourseForm extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = 1087670180773680397L;
	/**
	 * 
	 */

	private JPanel contentPane;
	private JTextField textField;
	public static CourseInfo ci = new CourseInfo();

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public CourseForm(String activity) {
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u8BFE\u7A0B\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(33, 87, 68, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(33, 112, 68, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u5B66\u5206");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(33, 187, 68, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u4E0A\u8BFE\u65F6\u95F4");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(33, 212, 68, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u6559\u5E08");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(33, 237, 68, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\u9650\u9009\u4EBA\u6570");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(33, 262, 68, 15);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("\u5DF2\u9009\u4EBA\u6570");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(33, 287, 68, 15);
		contentPane.add(label_6);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 70, 424, 2);
		contentPane.add(separator);

		textField = new JTextField();
		textField.setBounds(193, 34, 142, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel(
				"\u8F93\u5165\u8BFE\u7A0B\u53F7\u6216\u8BFE\u7A0B\u540D\u67E5\u627E\u8BFE\u7A0B");
		lblNewLabel.setBounds(10, 37, 190, 15);
		contentPane.add(lblNewLabel);

		JButton btnSearch = new JButton("\u641C\u7D22");

		btnSearch.setBounds(351, 33, 83, 23);
		contentPane.add(btnSearch);

		JLabel lblCno = new JLabel("");
		lblCno.setFont(new Font("楷体", Font.PLAIN, 14));
		lblCno.setBounds(111, 87, 287, 15);
		contentPane.add(lblCno);

		JLabel lblCname = new JLabel("");
		lblCname.setFont(new Font("楷体", Font.PLAIN, 14));
		lblCname.setBounds(111, 112, 287, 15);
		contentPane.add(lblCname);

		JLabel lblCredit = new JLabel("");
		lblCredit.setFont(new Font("楷体", Font.PLAIN, 14));
		lblCredit.setBounds(111, 187, 287, 15);
		contentPane.add(lblCredit);

		JLabel lblTime = new JLabel("");
		lblTime.setFont(new Font("楷体", Font.PLAIN, 14));
		lblTime.setBounds(111, 212, 287, 15);
		contentPane.add(lblTime);

		JLabel lblTeacher = new JLabel("");
		lblTeacher.setFont(new Font("楷体", Font.PLAIN, 14));
		lblTeacher.setBounds(111, 237, 287, 15);
		contentPane.add(lblTeacher);

		JLabel lblCapacity = new JLabel("");
		lblCapacity.setFont(new Font("楷体", Font.PLAIN, 14));
		lblCapacity.setBounds(111, 262, 287, 15);
		contentPane.add(lblCapacity);

		JLabel lblSelect = new JLabel("");
		lblSelect.setFont(new Font("楷体", Font.PLAIN, 14));
		lblSelect.setBounds(111, 287, 287, 15);
		contentPane.add(lblSelect);

		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CourseConfig cc = new CourseConfig("添加");
				cc.show();
			}
		});
		btnAdd.setBounds(33, 337, 93, 23);
		contentPane.add(btnAdd);

		JButton btnAlter = new JButton("\u4FEE\u6539");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseConfig cc = new CourseConfig("修改");
				cc.show();
			}
		});
		btnAlter.setBounds(181, 337, 93, 23);
		contentPane.add(btnAlter);

		JButton btnDelete = new JButton("\u5220\u9664");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();

					if (JOptionPane.showConfirmDialog(null, "确定要删除该课程信息吗", "警告", JOptionPane.YES_NO_OPTION) == 0) {
						ResultSet resultSet = stat.executeQuery("select * from sc where cno = " + lblCno.getText());
						String grade = "";
						boolean b = true;
						if (resultSet.next()) {
							grade = resultSet.getString("grade");
							if (grade == null)
								b = false;
						}
						if (b) {
							stat.execute("delete from Courseinfo where cno  =" + lblCno.getText());
							JOptionPane.showMessageDialog(null, "删除成功");
						}else{
							JOptionPane.showMessageDialog(null, "删除失败，该课程已结课，已有成绩和记录");
						}
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "删除失败，该课程已结课，已有成绩和记录");
					e1.printStackTrace();
				}

			}
		});
		btnDelete.setBounds(320, 337, 93, 23);
		contentPane.add(btnDelete);

		JLabel label_7 = new JLabel("\u8BFE\u7A0B\u7C7B\u578B");
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(33, 137, 68, 15);
		contentPane.add(label_7);

		JLabel lbltype = new JLabel("");
		lbltype.setFont(new Font("楷体", Font.PLAIN, 14));
		lbltype.setBounds(111, 137, 287, 15);
		contentPane.add(lbltype);

		JLabel label_8 = new JLabel("\u5B66\u671F");
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(33, 162, 68, 15);
		contentPane.add(label_8);

		JLabel lblsemester = new JLabel("");
		lblsemester.setFont(new Font("楷体", Font.PLAIN, 14));
		lblsemester.setBounds(111, 162, 287, 15);
		contentPane.add(lblsemester);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					boolean b = false;
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					try {
						Integer.parseInt(textField.getText());
						ResultSet rs = stat.executeQuery("select * from courseinfo where Cno =" + textField.getText());
						while (rs.next()) {

							lblCno.setText(ci.cno = rs.getString("Cno"));
							lblCname.setText(ci.cname = rs.getString("Cname"));
							lbltype.setText(ci.ctype = rs.getString("CType"));
							lblsemester.setText(ci.SchoolYear = rs.getString("CSemester"));
							lblCredit.setText(ci.credit = rs.getString("Ccredit"));
							lblTime.setText(ci.ctime = rs.getString("Ctime"));
							lblTeacher.setText(ci.cteacher = rs.getString("Cteacher"));
							lblCapacity.setText(ci.ccapacity = rs.getString("Ccapacity"));
							lblSelect.setText(ci.cselect = rs.getString("Cselect"));
							b = true;
						}
						if (!b)
							JOptionPane.showMessageDialog(null, "没有找到课程");

					} catch (Exception ex) {
						ResultSet rs = stat
								.executeQuery("select * from courseinfo where Cname = '" + textField.getText() + "'");
						while (rs.next()) {

							lblCno.setText(ci.cno = rs.getString("Cno"));
							lblCname.setText(ci.cname = rs.getString("Cname"));
							lbltype.setText(ci.ctype = rs.getString("CType"));
							lblsemester.setText(ci.SchoolYear = rs.getString("CSemester"));
							lblCredit.setText(ci.credit = rs.getString("Ccredit"));
							lblTime.setText(ci.ctime = rs.getString("Ctime"));
							lblTeacher.setText(ci.cteacher = rs.getString("Cteacher"));
							lblCapacity.setText(ci.ccapacity = rs.getString("Ccapacity"));
							lblSelect.setText(ci.cselect = rs.getString("Cselect"));
							b = true;
						}
						if (!b)
							JOptionPane.showMessageDialog(null, "没有找到课程");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		if (activity.equals("添加")) {
			btnAlter.setEnabled(false);
		}
		if (activity.equals("修改")) {
			btnAdd.setEnabled(false);
		}
	}
}
