package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JSeparator;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GradeUpload extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = 3417127535345046944L;
	private JPanel contentPane;
	public static StuBasicInfo sbu = StudentManagement.stu;
	CourseInfo ci[] = new CourseInfo[20];
	static int total = 0;
	int p = 0;
	private JTable table;
	private JScrollPane jsp;

	/**
	 * Create the frame.
	 */
	public GradeUpload() {
		setTitle("\u6559\u5E08\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u6559\u5E08\u59D3\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(24, 32, 70, 26);
		contentPane.add(label);

		JLabel lblName = new JLabel("New label");
		lblName.setFont(new Font("宋体", Font.PLAIN, 14));
		lblName.setBounds(133, 32, 70, 26);
		contentPane.add(lblName);

		JList<String> list = new JList<String>();

		list.setBounds(24, 90, 179, 161);
		contentPane.add(list);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 274, 564, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D");
		lblNewLabel.setBounds(267, 91, 54, 15);
		contentPane.add(lblNewLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 64, 564, 2);
		contentPane.add(separator_1);

		JLabel lbl = new JLabel("\u8BFE\u7A0B\u53F7");
		lbl.setBounds(267, 116, 54, 15);
		contentPane.add(lbl);

		JLabel lclcno = new JLabel("\u4E0A\u8BFE\u65F6\u95F4");
		lclcno.setBounds(267, 141, 54, 15);
		contentPane.add(lclcno);

		JLabel lblcredit = new JLabel("\u5B66\u5206");
		lblcredit.setBounds(267, 163, 54, 15);
		contentPane.add(lblcredit);

		JLabel lblteacher = new JLabel("\u4EFB\u8BFE\u6559\u5E08");
		lblteacher.setBounds(267, 188, 54, 15);
		contentPane.add(lblteacher);

		JLabel lblcapacity = new JLabel("\u9650\u9009\u4EBA\u6570");
		lblcapacity.setBounds(267, 213, 54, 15);
		contentPane.add(lblcapacity);

		JLabel lblselect = new JLabel("\u5DF2\u9009\u4EBA\u6570");
		lblselect.setBounds(267, 238, 54, 15);
		contentPane.add(lblselect);

		Vector<String> v = new Vector<String>();

		int i = 0;
		try {
			Connection con = SQLHelper.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Courseinfo where CTeacher = '" + sbu.Name + "'");
			while (rs.next()) {

				ci[i] = new CourseInfo();
				ci[i].cno = rs.getString("Cno");
				ci[i].cname = rs.getString("Cname");
				ci[i].credit = rs.getString("Ccredit");
				ci[i].ctime = rs.getString("Ctime");
				ci[i].ccapacity = rs.getString("Ccapacity");
				ci[i].cteacher = rs.getString("Cteacher");
				ci[i].cselect = rs.getString("Cselect");

				v.add(rs.getString("Cname"));
				i++;
			}
			list.setListData(v);
			total = i;
		} catch (Exception e) {

		}
		lblName.setText(sbu.Name);

		JLabel cno = new JLabel("");
		cno.setBounds(362, 91, 179, 15);
		contentPane.add(cno);

		JLabel cname = new JLabel("");
		cname.setBounds(362, 116, 179, 15);
		contentPane.add(cname);

		JLabel time = new JLabel("");
		time.setBounds(362, 141, 179, 15);
		contentPane.add(time);

		JLabel credit = new JLabel("");
		credit.setBounds(362, 163, 179, 15);
		contentPane.add(credit);

		JLabel teacher = new JLabel("");
		teacher.setBounds(362, 188, 179, 15);
		contentPane.add(teacher);

		JLabel capacity = new JLabel("");
		capacity.setBounds(362, 213, 179, 15);
		contentPane.add(capacity);

		JLabel select = new JLabel("");
		select.setBounds(362, 238, 179, 15);
		contentPane.add(select);

		Vector<Vector<String>> col = new Vector<Vector<String>>();
		Vector<String> v0 = new Vector<String>();

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String s = list.getSelectedValue();
				for (int i = 0; i < total; i++) {
					if (s.equals(ci[i].cname)) {
						cno.setText(ci[i].cno);
						cname.setText(ci[i].cname);
						time.setText(ci[i].ctime);
						capacity.setText(ci[i].ccapacity);
						select.setText(ci[i].cselect);
						teacher.setText(ci[i].cteacher);
						credit.setText(ci[i].credit);

					}
				}
				try {
					String sql = "select * from sc where cname = '" + s + "'";

					p = 0;
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					ResultSet rs = stat.executeQuery(sql);
					v0.clear();
					col.clear();
					v0.add("学号");
					v0.add("姓名");
					v0.add("课程名");
					v0.add("成绩");
					//col.add(v0);

					while (rs.next()) {
						Vector<String> rows = new Vector<String>();
						rows.add(rs.getString("ID"));
						rows.add(rs.getString("Sname"));
						rows.add(rs.getString("Cname"));
						rows.add(rs.getString("grade"));
						col.add(rows);
						p++;
					}
					table = new JTable(col, v0);
					//table.setBounds(24, 293, 537, 161);
					jsp = new JScrollPane(table);
					jsp.setBounds(24, 293, 537, 161);
					contentPane.add(jsp);
					jsp.updateUI();
					//contentPane.add(table);
					//table.updateUI();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		JButton UpLoad = new JButton("\u63D0\u4EA4");
		UpLoad.setBounds(59, 477, 93, 23);
		contentPane.add(UpLoad);

		JButton check = new JButton("\u68C0\u67E5");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = true;
				String s[] = new String[100];
				for (int i = 0; i < 100; ++i) {
					s[i] = new String();
				}
				for (int i = 0; i < p ; i++) {
					table.setValueAt(table.getModel().getValueAt(i, 3), i, 3);
					s[i] = new String(table.getModel().getValueAt(i, 3).toString());

					if (Integer.parseInt(s[i]) < 0 || Integer.parseInt(s[i]) > 100) {
						JOptionPane.showMessageDialog(null, "学号" + table.getModel().getValueAt(i, 0) + "成绩非法");
						b = false;
						break;
					}
				}
				if (b)
					JOptionPane.showMessageDialog(null, "检查完成，可以提交成绩");
			}
		});
		check.setBounds(251, 477, 93, 23);
		contentPane.add(check);

		JButton btnCancel = new JButton("\u8FD4\u56DE");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(448, 477, 93, 23);
		contentPane.add(btnCancel);

		UpLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s[] = new String[100];
				for (int i = 0; i < 100; ++i) {
					s[i] = new String();
				}
				for (int i = 0; i < p; i++) {
					table.setValueAt(table.getModel().getValueAt(i, 3), i, 3);
					s[i] = new String(table.getModel().getValueAt(i, 3).toString());

					if (Integer.parseInt(s[i]) < 0 || Integer.parseInt(s[i]) > 100) {
						JOptionPane.showMessageDialog(null, "非法数据");
						break;
					}
				}

				try {
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					for (int i = 0; i < p ; i++) {
						stat.execute("Update SC Set grade = " + s[i] + " where ID = "
								+ (table.getModel().getValueAt(i, 0).toString()) + " and Cname ='"
								+ table.getModel().getValueAt(i, 2).toString() + "'");
						table.updateUI();
					}
					JOptionPane.showMessageDialog(null, "成绩上传成功");
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "成绩上传失败");
					ex.printStackTrace();
				}

			}
		});

	}
}