package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CourseConfig extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = 5496239112257957845L;
	private JPanel contentPane;
	private JTextField textcno;
	private JTextField textcname;
	private JTextField textcredit;
	private JTextField texttime;
	private JTextField textteacher;
	private JTextField textcapacity;
	private JTextField textselect;
	CourseInfo ci = CourseForm.ci;

	/**
	 * Create the frame.
	 */
	public CourseConfig(String Activity) {
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u4FEE\u6539\u548C\u6DFB\u52A0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> type = new JComboBox<String>();
		type.addItem("专业选修");
		type.addItem("公共选修");
		type.setBounds(138, 108, 249, 21);
		contentPane.add(type);
		
		JComboBox<String> semester = new JComboBox<String>();
		semester.addItem("2016-2017学年上半学期");
		semester.addItem("2016-2017学年下半学期");
		semester.addItem("2015-2016学年上半学期");
		semester.addItem("2015-2016学年下半学期");
		semester.addItem("2014-2015学年上半学期");
		semester.addItem("2014-2015学年下半学期");
		semester.setBounds(138, 136, 249, 21);
		contentPane.add(semester);

		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u53F7");
		lblNewLabel.setFont(new Font("华文宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 60, 75, 15);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\u8BFE\u7A0B\u540D");
		label.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label.setBounds(40, 85, 75, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5B66\u5206");
		label_1.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_1.setBounds(40, 167, 75, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u4E0A\u8BFE\u65F6\u95F4");
		label_2.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_2.setBounds(40, 192, 75, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u6559\u5E08");
		label_3.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_3.setBounds(40, 217, 75, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u9650\u9009\u4EBA\u6570");
		label_4.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_4.setBounds(40, 242, 75, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\u5DF2\u9009\u4EBA\u6570");
		label_5.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_5.setBounds(40, 267, 75, 15);
		contentPane.add(label_5);

		textcno = new JTextField();
		textcno.setBounds(138, 57, 249, 21);
		contentPane.add(textcno);
		textcno.setColumns(10);

		textcname = new JTextField();
		textcname.setColumns(10);
		textcname.setBounds(138, 82, 249, 21);
		contentPane.add(textcname);

		textcredit = new JTextField();
		textcredit.setColumns(10);
		textcredit.setBounds(138, 164, 249, 21);
		contentPane.add(textcredit);

		texttime = new JTextField();
		texttime.setColumns(10);
		texttime.setBounds(138, 189, 249, 21);
		contentPane.add(texttime);

		textteacher = new JTextField();
		textteacher.setColumns(10);
		textteacher.setBounds(138, 214, 249, 21);
		contentPane.add(textteacher);

		textcapacity = new JTextField();
		textcapacity.setColumns(10);
		textcapacity.setBounds(138, 239, 249, 21);
		contentPane.add(textcapacity);

		textselect = new JTextField();
		textselect.setColumns(10);
		textselect.setBounds(138, 264, 249, 21);
		contentPane.add(textselect);

		JLabel lblNewLabel_1 = new JLabel("\u4EE5\u4E0B\u4FE1\u606F\u52A1\u5FC5\u586B\u5199\u5B8C\u6574");
		lblNewLabel_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(138, 10, 179, 21);
		contentPane.add(lblNewLabel_1);

		JButton btnAdd = new JButton("\u6DFB\u52A0");

		btnAdd.setBounds(40, 306, 93, 23);
		contentPane.add(btnAdd);

		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(294, 306, 93, 23);
		contentPane.add(btnCancel);

		JButton btnAlter = new JButton("\u4FEE\u6539");

		btnAlter.setBounds(166, 306, 93, 23);
		contentPane.add(btnAlter);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = SQLHelper.getConnection();
					Statement st = con.createStatement();
					String sql = "insert into CourseInfo values(" + textcno.getText() + ",'" + textcname.getText()
							+ "','" + type.getSelectedItem() + "','" + semester.getSelectedItem() + "','" + textcredit.getText()
							+ "','" + texttime.getText() + "','" + textteacher.getText() + "','"
							+ textcapacity.getText() + "'," + "0" + ")";
					st.execute(sql);
					JOptionPane.showMessageDialog(null, "添加成功");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请将信息填写完整");
				}

			}
		});

		textcname.setText(ci.cname);
		textcredit.setText(ci.credit);
		texttime.setText(ci.ctime);
		textteacher.setText(ci.cteacher);
		textcapacity.setText(ci.ccapacity);
		textselect.setText(ci.cselect);
		type.setSelectedItem(ci.ctype);
		semester.setSelectedItem(ci.SchoolYear);
		textselect.setEditable(false);

		JLabel label_6 = new JLabel("\u8BFE\u7A0B\u7C7B\u578B");
		label_6.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_6.setBounds(40, 111, 75, 15);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("\u5B66\u671F");
		label_7.setFont(new Font("华文宋体", Font.PLAIN, 14));
		label_7.setBounds(40, 139, 75, 15);
		contentPane.add(label_7);
		


		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = SQLHelper.getConnection();
					Statement st = con.createStatement();
					String sql = "Update CourseInfo set cno = " + ci.cno + ",cname = '" + textcname.getText()
							+ "',ctype = '" + type.getSelectedItem() + "',csemester = '" + semester.getSelectedItem()
							+ "',ccredit = " + textcredit.getText() + ",Ctime = '" + texttime.getText()
							+ "',Cteacher = '" + textteacher.getText() + "',Ccapacity = " + textcapacity.getText()
							+ ",Cselect = " + textselect.getText() + " where Cno = " + ci.cno;
					st.execute(sql);
					JOptionPane.showMessageDialog(null, "修改成功");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请将信息填写完整");
				}
			}
		});

		if (Activity.equals("修改")) {
			btnAdd.setEnabled(false);
			textcno.setText(ci.cno);
			textcno.setEditable(false);

		}
		if (Activity.equals("添加"))
			btnAlter.setEnabled(false);
	}
}
