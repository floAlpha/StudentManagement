package sm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.event.ActionEvent;

public class CourseQuery extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -3730331188005265744L;
	private JPanel contentPane;
	CourseInfo ci[] = new CourseInfo[100];
	int total = 0;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public CourseQuery(String s) {
		setTitle("\u8BFE\u7A0B\u641C\u7D22\u7ED3\u679C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btntoExcel = new JButton("\u5BFC\u51FA\u5230Excel");
		btntoExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WritableWorkbook wb = null;
				File file = new File("D://Course.xls");
				OutputStream os = null;
				try {
					if (!file.exists())
						file.createNewFile();
					os = new FileOutputStream(file);
					wb = Workbook.createWorkbook(os);
					WritableSheet sheet = wb.createSheet("sheet", 0);
					Label[] label = new Label[9];
					label[0] = new Label(0, 0, "课程号");
					label[1] = new Label(1, 0, "课程名");
					label[2] = new Label(2, 0, "课程类型");
					label[3] = new Label(3, 0, "学期");
					label[4] = new Label(4, 0, "学分");
					label[5] = new Label(5, 0, "上课时间");
					label[6] = new Label(6, 0, "教师");
					label[7] = new Label(7, 0, "限选人数");
					label[8] = new Label(8, 0, "已选人数");
					sheet.addCell(label[0]);
					sheet.addCell(label[1]);
					sheet.addCell(label[2]);
					sheet.addCell(label[3]);
					sheet.addCell(label[4]);
					sheet.addCell(label[5]);
					sheet.addCell(label[6]);
					sheet.addCell(label[7]);
					sheet.addCell(label[8]);
					for (int i = 0; i < total; i++) {
						label[0] = new Label(0, i+1, ci[i].cno);
						label[1] = new Label(1, i+1, ci[i].cname);
						label[2] = new Label(2, i+1, ci[i].ctype);
						label[3] = new Label(3, i+1, ci[i].SchoolYear);
						label[4] = new Label(4, i+1, ci[i].credit);
						label[5] = new Label(5, i+1, ci[i].ctime);
						label[6] = new Label(6, i+1, ci[i].cteacher);
						label[7] = new Label(7, i+1, ci[i].ccapacity);
						label[8] = new Label(8, i+1, ci[i].cselect);
						sheet.addCell(label[0]);
						sheet.addCell(label[1]);
						sheet.addCell(label[2]);
						sheet.addCell(label[3]);
						sheet.addCell(label[4]);
						sheet.addCell(label[5]);
						sheet.addCell(label[6]);
						sheet.addCell(label[7]);
						sheet.addCell(label[8]);
					}
					wb.write();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (RowsExceededException e1) {
					e1.printStackTrace();
				} catch (WriteException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (wb != null)
							wb.close();
						if (os != null)
							os.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (WriteException e1) {
						e1.printStackTrace();
					}
				}

				try {
					Runtime.getRuntime().exec("explorer.exe D:\\Course.xls");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btntoExcel.setBounds(65, 428, 110, 23);
		contentPane.add(btntoExcel);

		JButton btnCancel = new JButton("\u8FD4\u56DE");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(527, 428, 110, 23);
		contentPane.add(btnCancel);

		for (int j = 0; j < ci.length; j++) {
			ci[j] = new CourseInfo();
		}

		Vector<String> v0 = new Vector<String>();
		Vector<Vector<String>> v = new Vector<Vector<String>>();
		v0.add("课程号");
		v0.add("课程名");
		v0.add("课程类型");
		v0.add("学期");
		v0.add("学分");
		v0.add("上课时间");
		v0.add("教师");
		v0.add("限选人数");
		v0.add("已选人数");
		v.add(v0);
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			try {
				Integer.parseInt(s);
				try {
					ResultSet rs0 = stat.executeQuery("select * from CourseInfo where cno = " + s
							+ " union select * from CourseInfo where Ccredit = '" + s + "'"
							+ " union select * from CourseInfo where Cselect = '" + s + "'"
							+ " union select * from CourseInfo where Ccapacity = '" + s + "'");
					while (rs0.next()) {
						Vector<String> vtmp = new Vector<String>();
						ci[total].cno = rs0.getString("Cno");
						ci[total].cname = rs0.getString("Cname");
						ci[total].ctype = rs0.getString("CType");
						ci[total].SchoolYear = rs0.getString("Csemester");
						ci[total].credit = rs0.getString("Ccredit");
						ci[total].cteacher = rs0.getString("Cteacher");
						ci[total].ctime = rs0.getString("Ctime");
						ci[total].ccapacity = rs0.getString("Ccapacity");
						ci[total].cselect = rs0.getString("Cselect");
						vtmp.add(ci[total].cno);
						vtmp.add(ci[total].cname);
						vtmp.add(ci[total].ctype);
						vtmp.add(ci[total].SchoolYear);
						vtmp.add(ci[total].cteacher);
						vtmp.add(ci[total].ctime);
						vtmp.add(ci[total].cteacher);
						vtmp.add(ci[total].ccapacity);
						vtmp.add(ci[total].cselect);
						v.add(vtmp);
						total++;
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "没有找到结果");
				}

			} catch (Exception ex) {

				ResultSet rs0 = stat.executeQuery("select * from CourseInfo where Cname like '%" + s + "%'"
						+ " union select * from CourseInfo where Ctype like '%" + s + "%'"
						+ " union select * from CourseInfo where Csemester like '%" + s + "%'"
						+ " union select * from CourseInfo where Cteacher like '%" + s + "%'"
						+ " union select * from CourseInfo where Ctime like '%" + s + "%'");

				while (rs0.next()) {

					Vector<String> vtmp = new Vector<String>();
					ci[total].cno = rs0.getString("Cno");
					ci[total].cname = rs0.getString("Cname");
					ci[total].ctype = rs0.getString("CType");
					ci[total].SchoolYear = rs0.getString("Csemester");
					ci[total].credit = rs0.getString("Ccredit");
					ci[total].cteacher = rs0.getString("Cteacher");
					ci[total].ctime = rs0.getString("Ctime");
					ci[total].ccapacity = rs0.getString("Ccapacity");
					ci[total].cselect = rs0.getString("Cselect");
					vtmp.add(ci[total].cno);
					vtmp.add(ci[total].cname);
					vtmp.add(ci[total].ctype);
					vtmp.add(ci[total].SchoolYear);
					vtmp.add(ci[total].cteacher);
					vtmp.add(ci[total].ctime);
					vtmp.add(ci[total].cteacher);
					vtmp.add(ci[total].ccapacity);
					vtmp.add(ci[total].cselect);
					v.add(vtmp);
					total++;
				}

			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		table = new JTable(v, v0);
		table.setBounds(10, 10, 684, 390);
		table.setEnabled(false);
		contentPane.add(table);
	}
}
