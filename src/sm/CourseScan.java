package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CourseScan extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = 56543429010376630L;
	private JPanel contentPane;
	private JTextField textField;
	static private CourseInfo ci[];
	private int i = 0;
	private int total = 0;
	private int p = 0;
	public static String s = null;

	/**
	 * Create the frame.
	 */
	public CourseScan() {
		setTitle("\u8BFE\u7A0B\u4FE1\u606F\u6D4F\u89C8");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancel = new JButton("返回");
		btnCancel.setBounds(316, 316, 93, 23);
		contentPane.add(btnCancel);
		
		JLabel lbltype = new JLabel((String) null);
		lbltype.setBounds(109, 129, 269, 15);
		contentPane.add(lbltype);
		
		JLabel label_9 = new JLabel("\u8BFE\u7A0B\u7C7B\u578B");
		label_9.setBounds(23, 129, 54, 15);
		contentPane.add(label_9);
		
		JLabel lblsemester = new JLabel((String) null);
		lblsemester.setBounds(109, 154, 269, 15);
		contentPane.add(lblsemester);
		
		JLabel label_11 = new JLabel("\u5B66\u671F");
		label_11.setBounds(23, 154, 54, 15);
		contentPane.add(label_11);
	

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 53, 414, 2);
		contentPane.add(separator);

		JLabel label = new JLabel("\u6A21\u7CCA\u641C\u7D22\u8BFE\u7A0B\u4FE1\u606F");
		label.setFont(new Font("楷体", Font.BOLD, 14));
		label.setBounds(23, 22, 131, 21);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(172, 22, 156, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				s = textField.getText();
				CourseQuery cq = new CourseQuery(s);
				cq.show();
			}
		});
		btnSearch.setBounds(341, 21, 83, 23);
		contentPane.add(btnSearch);

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D");
		label_1.setBounds(23, 79, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u53F7");
		label_2.setBounds(23, 104, 54, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u5B66\u5206");
		label_3.setBounds(23, 179, 54, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u4E0A\u8BFE\u65F6\u95F4");
		label_4.setBounds(23, 204, 54, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\u6559\u5E08");
		label_5.setBounds(23, 229, 54, 15);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("\u9650\u9009\u4EBA\u6570");
		label_6.setBounds(23, 254, 54, 15);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("\u5DF2\u9009\u4EBA\u6570");
		label_7.setBounds(23, 279, 54, 15);
		contentPane.add(label_7);

		JLabel lblcname = new JLabel("");
		lblcname.setBounds(109, 79, 269, 15);
		contentPane.add(lblcname);

		JLabel lblcno = new JLabel("");
		lblcno.setBounds(109, 104, 269, 15);
		contentPane.add(lblcno);

		JLabel lblcredit = new JLabel("");
		lblcredit.setBounds(109, 179, 269, 15);
		contentPane.add(lblcredit);

		JLabel lblctime = new JLabel("");
		lblctime.setBounds(109, 204, 269, 15);
		contentPane.add(lblctime);

		JLabel lblcteacher = new JLabel("");
		lblcteacher.setBounds(109, 229, 269, 15);
		contentPane.add(lblcteacher);

		JLabel lblccapacity = new JLabel("");
		lblccapacity.setBounds(109, 254, 269, 15);
		contentPane.add(lblccapacity);

		JLabel lblcselect = new JLabel("");
		lblcselect.setBounds(109, 279, 269, 15);
		contentPane.add(lblcselect);

		JButton btnUp = new JButton("\u4E0A\u4E00\u6761");

		btnUp.setBounds(34, 316, 93, 23);
		contentPane.add(btnUp);

		JButton btnDown = new JButton("\u4E0B\u4E00\u6761");

		btnDown.setBounds(176, 316, 93, 23);
		contentPane.add(btnDown);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		ci = new CourseInfo[200];
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();

			try {
				ResultSet rs = stat.executeQuery("select * from CourseInfo");

				while (rs.next()) {

					ci[i] = new CourseInfo();
					ci[i].cno = rs.getString("Cno");
					ci[i].cname = rs.getString("Cname");
					ci[i].ctype = rs.getString("CType");
					ci[i].SchoolYear = rs.getString("Csemester");
					ci[i].credit = rs.getString("Ccredit");
					ci[i].cteacher = rs.getString("Cteacher");
					ci[i].ctime = rs.getString("Ctime");
					ci[i].ccapacity = rs.getString("Ccapacity");
					ci[i].cselect = rs.getString("Cselect");
					i++;

				}
				total = i;

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "没有找到课程信息");
			}

		} catch (Exception e) {

		}
		lblcno.setText(ci[p].cno);
		lblcname.setText(ci[p].cname);
		lbltype.setText(ci[p].ctype);
		lblsemester.setText(ci[p].SchoolYear);
		lblcredit.setText(ci[p].credit);
		lblcteacher.setText(ci[p].cteacher);
		lblctime.setText(ci[p].ctime);
		lblccapacity.setText(ci[p].ccapacity);
		lblcselect.setText(ci[p].cselect);

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (p > 0) {
					p--;
					lblcno.setText(ci[p].cno);
					lblcname.setText(ci[p].cname);
					lbltype.setText(ci[p].ctype);
					lblsemester.setText(ci[p].SchoolYear);
					lblcredit.setText(ci[p].credit);
					lblcteacher.setText(ci[p].cteacher);
					lblctime.setText(ci[p].ctime);
					lblccapacity.setText(ci[p].ccapacity);
					lblcselect.setText(ci[p].cselect);
				} else
					JOptionPane.showMessageDialog(null, "已经是第一条信息了");
			}
		});

		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (p < total - 1) {
					p++;
					lblcno.setText(ci[p].cno);
					lblcname.setText(ci[p].cname);
					lbltype.setText(ci[p].ctype);
					lblsemester.setText(ci[p].SchoolYear);
					lblcredit.setText(ci[p].credit);
					lblcteacher.setText(ci[p].cteacher);
					lblctime.setText(ci[p].ctime);
					lblccapacity.setText(ci[p].ccapacity);
					lblcselect.setText(ci[p].cselect);
				} else
					JOptionPane.showMessageDialog(null, "已经是最后一条信息了");

			}
		});


	}
}
