package sm;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.awt.BorderLayout;

public class SCoperate extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6362595247635300892L;
	/**
	 * Create the panel.
	 */
	StuBasicInfo stu = StudentManagement.stu;
	int total = 0;

	public SCoperate(String Semester, String Type) {

		CourseInfo ci[] = new CourseInfo[100];
		for (int r = 0; r < ci.length; r++)
			ci[r] = new CourseInfo();
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs1 = null;

			rs1 = stat.executeQuery(
					"select * from CourseInfo where Ctype = '" + Type + "' and Csemester = '" + Semester + "'");
			Vector<String> vt0 = new Vector<String>();
			vt0.add("课程号");
			vt0.add("课程名");
			vt0.add("学期");
			vt0.add("选修类型");
			vt0.add("学分");
			vt0.add("教师");
			vt0.add("上课时间");
			vt0.add("已选人数");
			vt0.add("限选人数");
			vt0.add("是否已选");
			Vector<Vector<String>> vt2 = new Vector<Vector<String>>();
			int i = 0;
			while (rs1.next()) {
				Vector<String> vt1 = new Vector<String>();
				ci[i].cno = rs1.getString("Cno");
				ci[i].cname = rs1.getString("Cname");
				ci[i].SchoolYear = rs1.getString("CSemester");
				ci[i].ctype = rs1.getString("CType");
				ci[i].credit = rs1.getString("Ccredit");
				ci[i].cteacher = rs1.getString("Cteacher");
				ci[i].ctime = rs1.getString("Ctime");
				ci[i].cselect = rs1.getString("Cselect");
				ci[i].ccapacity = rs1.getString("Ccapacity");
				vt1.add(ci[i].cno);
				vt1.add(ci[i].cname);
				vt1.add(ci[i].SchoolYear);
				vt1.add(ci[i].ctype);
				vt1.add(ci[i].credit);
				vt1.add(ci[i].cteacher);
				vt1.add(ci[i].ctime);
				vt1.add(ci[i].cselect);
				vt1.add(ci[i].ccapacity);
				vt1.add(null);
				vt2.add(vt1);
				i++;
			}

			total = i;

			final JTable tableall = new JTable(vt2, vt0);
			tableall.setLayout(null);
			
			TableColumn tc = tableall.getColumnModel().getColumn(9);
			tc.setCellEditor(new DefaultCellEditor(new JCheckBox()));
			tc.setCellRenderer(tableall.getDefaultRenderer(Boolean.class));

			for (int j = 0; j < total; j++) {
			try {
					
					
					Connection con1 = SQLHelper.getConnection();
					Statement stat1 = con1.createStatement();
					ResultSet rs = stat1.executeQuery("select * from sc where ID = " + stu.ID + " and " + "Cname ='"
							+ tableall.getModel().getValueAt(j, 1) + "'");
					if (rs.next()) {
						tableall.setValueAt(true, j, 9);
					}

				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
			setLayout(new BorderLayout(0, 0));

			JScrollPane spall = new JScrollPane(tableall);
			spall.setBounds(0, 0, 660, 300);
			add(spall);

			tableall.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					if (tableall.getSelectedColumn() == 9) {
						if ((boolean) tableall.getModel().getValueAt(tableall.getSelectedRow(),
								tableall.getSelectedColumn()) == true) {
							try {
								Connection con = SQLHelper.getConnection();
								Statement stat = con.createStatement();
								String strSQL1 = "select * from sc where ID =" + stu.ID + " and " + "Cname ='"
										+ tableall.getModel().getValueAt(tableall.getSelectedRow(), 1) + "'";

								ResultSet rs = stat.executeQuery(strSQL1);
								if (rs.next()) {
									JOptionPane.showMessageDialog(null, "请勿重复添加课程");
								} else {

									String strSQL2 = "insert into SC(ID,Cno,Sname,Cname) values(" + stu.ID + ","
											+ tableall.getModel().getValueAt(tableall.getSelectedRow(), 0) + ",'"
											+ stu.Name + "','"
											+ tableall.getModel().getValueAt(tableall.getSelectedRow(), 1) + "')";

									stat.execute(strSQL2);

									CourseInfo ci1[] = getResult(Semester, Type, ci);

									tableall.setValueAt(ci1[tableall.getSelectedRow()].cselect,
											tableall.getSelectedRow(), 7);

									JOptionPane.showMessageDialog(null, "添加课程成功");
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						} else if ((boolean) tableall.getModel().getValueAt(tableall.getSelectedRow(),
								tableall.getSelectedColumn()) == false) {
							try {
								Connection con = SQLHelper.getConnection();
								Statement stat = con.createStatement();
								String strSQL2 = "delete from sc where ID = " + stu.ID + " and Cname ='"
										+ tableall.getModel().getValueAt(tableall.getSelectedRow(), 1) + "'";

								stat.execute(strSQL2);
								ResultSet resultSet = stat.executeQuery("select * from sc where ID = "+stu.ID+" and Cname ='"+
								tableall.getModel().getValueAt(tableall.getSelectedRow(), 1)+"'");
								String grade="";
								if(resultSet.next()){
									grade = resultSet.getString("Grade");
								}
								if(grade == null){
								CourseInfo ci1[] = getResult(Semester, Type, ci);

								tableall.setValueAt(ci1[tableall.getSelectedRow()].cselect, tableall.getSelectedRow(),
										7);
								JOptionPane.showMessageDialog(null, "退课成功");
								}
								else{
									JOptionPane.showMessageDialog(null, "该门课程已结课，不可退课");
									tableall.setValueAt(true,tableall.getSelectedRow() , 9);
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private CourseInfo[] getResult(String year, String type, CourseInfo ci[]) {
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs1 = null;
			rs1 = stat.executeQuery(
					"select * from CourseInfo where Ctype = '" + type + "' and Csemester = '" + year + "'");
			int i = 0;
			while (rs1.next()) {
				ci[i].cno = rs1.getString("Cno");
				ci[i].cname = rs1.getString("Cname");
				ci[i].credit = rs1.getString("Ccredit");
				ci[i].cteacher = rs1.getString("Cteacher");
				ci[i].ctime = rs1.getString("Ctime");
				ci[i].cselect = rs1.getString("Cselect");
				ci[i].ccapacity = rs1.getString("Ccapacity");
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ci;
	}
}
