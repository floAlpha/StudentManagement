package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class GradeScan extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = 1409375758632107796L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	StuBasicInfo stu = StudentManagement.stu;

	/**
	 * Create the frame.
	 */
	public GradeScan() {
		setTitle("\u641C\u7D22\u5B66\u751F\u6210\u7EE9");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 529, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Vector<String> v0 = new Vector<String>();
		Vector<Vector<String>> v = new Vector<Vector<String>>();

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 493, 2);
		contentPane.add(separator);

		JLabel label = new JLabel("\u8F93\u5165\u59D3\u540D\u6216\u8005\u8BFE\u7A0B\u540D\u641C\u7D22\u6210\u7EE9");
		label.setBounds(20, 23, 173, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(190, 20, 195, 21);
		contentPane.add(textField);
		textField.setColumns(10);


		JButton btnSearch = new JButton("\u641C\u7D22");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.clear();
				v0.clear();
				v0.add("学号");
				v0.add("姓名");
				v0.add("课程名");
				v0.add("成绩");
				v.add(v0);
				try {
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();

					ResultSet rs = stat.executeQuery("Select * from SC where Sname = '" + textField.getText()
							+ "' union " + "select * from SC where Cname = '" + textField.getText() + "'");
					while (rs.next()) {
						Vector<String> vrow = new Vector<String>();
						vrow.add(rs.getString("ID"));
						vrow.add(rs.getString("Sname"));
						vrow.add(rs.getString("Cname"));
						vrow.add(rs.getString("grade"));
						v.add(vrow);
					}
					table = new JTable(v, v0);
					table.setBounds(20, 73, 472, 288);

					contentPane.add(table);
					table.updateUI();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		v.clear();
		v0.clear();
		v0.add("学号");
		v0.add("姓名");
		v0.add("课程名");
		v0.add("成绩");
		v.add(v0);

		btnSearch.setBounds(395, 19, 97, 23);
		contentPane.add(btnSearch);
		table = new JTable(v, v0);
		table.setBounds(20, 73, 472, 288);
		contentPane.add(table);
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			if (Integer.parseInt(stu.ID) > 100) {
				ResultSet rs = stat.executeQuery("Select * from SC where Sname = '" + stu.Name + "'");
				while (rs.next()) {
					Vector<String> vrow = new Vector<String>();
					vrow.add(rs.getString("ID"));
					vrow.add(rs.getString("Sname"));
					vrow.add(rs.getString("Cname"));
					vrow.add(rs.getString("grade"));
					v.add(vrow);
				}
				table = new JTable(v, v0);
				table.setBounds(20, 73, 472, 288);

				contentPane.add(table);
				table.updateUI();
				JLabel labelname = new JLabel(stu.Name);
				labelname.setBounds(33, 23, 79, 15);
				contentPane.add(labelname);
				label.setVisible(false);
				textField.setVisible(false);
				btnSearch.setVisible(false);
				
			}
		} catch (Exception e) {

		}

	}
}
