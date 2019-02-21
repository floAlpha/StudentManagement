package sm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;

public class GradePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2619756758404310372L;
	public static StuBasicInfo sbu = StudentManagement.stu;
	CourseInfo ci[] = new CourseInfo[20];
	static int total = 0;
	int p = 0;
	public JTable table;
	/**
	 * Create the panel.
	 */
	public GradePanel(String Course) {

		String s = Course;

		try {
			String sql = "select * from sc where cname = '" + s + "'";
			Vector<String> v0 = new Vector<String>();
			p = 0;
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			v0.add("学号");
			v0.add("姓名");
			v0.add("课程名");
			v0.add("成绩");
			//JTable headtable = new JTable(null,v0);

			Vector<Vector<String>> col = new Vector<Vector<String>>();
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
			setLayout(new BorderLayout(0, 0));
			table = new JTable(col, v0);
			table.setEnabled(true);
			JScrollPane jsp = new JScrollPane(table);
			jsp.setBounds(24, 293, 537, 161);
			add(jsp);
			jsp.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
