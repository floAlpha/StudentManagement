package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

public class StuQuery extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -2971886114545751927L;
	private JPanel contentPane;
	StuBasicInfo sbi[] = new StuBasicInfo[100];
	int total = 0;
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public StuQuery(String s) {
		setTitle("\u5B66\u751F\u641C\u7D22\u7ED3\u679C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton toExcel = new JButton("\u5BFC\u51FA\u5230Excel");
		toExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				WritableWorkbook wb = null;
				File file = new File("D://Student.xls");
				OutputStream os = null;
				try {
					if (!file.exists())
						file.createNewFile();	
					os = new FileOutputStream(file);
					wb = Workbook.createWorkbook(os);
					WritableSheet sheet = wb.createSheet("sheet", 0);
					Label[] label = new Label[7];
					label[0] = new Label(0, 0, "学号");
					label[1] = new Label(1, 0, "姓名");
					label[2] = new Label(2, 0, "性别");
					label[3] = new Label(3, 0, "生日");
					label[4] = new Label(4, 0, "院系");
					label[5] = new Label(5, 0, "身份证");
					label[6] = new Label(6, 0, "个人简介");
					sheet.addCell(label[0]);
					sheet.addCell(label[1]);
					sheet.addCell(label[2]);
					sheet.addCell(label[3]);
					sheet.addCell(label[4]);
					sheet.addCell(label[5]);
					sheet.addCell(label[6]);
					for (int i = 0; i < total; i++) {
						label[0] = new Label(0, i+1, sbi[i].ID);
						label[1] = new Label(1, i+1, sbi[i].Name);
						label[2] = new Label(2, i+1, sbi[i].Sex);
						label[3] = new Label(3, i+1, sbi[i].Birthday);
						label[4] = new Label(4, i+1, sbi[i].Department);
						label[5] = new Label(5, i+1, sbi[i].IDcard);
						label[6] = new Label(6, i+1, sbi[i].Brief);
						sheet.addCell(label[0]);
						sheet.addCell(label[1]);
						sheet.addCell(label[2]);
						sheet.addCell(label[3]);
						sheet.addCell(label[4]);
						sheet.addCell(label[5]);
						sheet.addCell(label[6]);
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
					Runtime.getRuntime().exec("explorer.exe D:\\Student.xls");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		toExcel.setBounds(123, 454, 108, 23);
		contentPane.add(toExcel);

		JButton btnCancel = new JButton("\u8FD4\u56DE");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(487, 454, 108, 23);
		contentPane.add(btnCancel);

		for (int j = 0; j < sbi.length; j++) {
			sbi[j] = new StuBasicInfo();
		}

		Vector<String> v0 = new Vector<String>();
		Vector<Vector<String>> v = new Vector<Vector<String>>();
		v0.add("学号");
		v0.add("姓名");
		v0.add("性别");
		v0.add("出生日期");
		v0.add("院系");
		v0.add("身份证号");
		v0.add("个人简介");
		v.add(v0);
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			try {
				Integer.parseInt(s);
				try {
					ResultSet rs0 = null;
					rs0 = stat.executeQuery("select * from StuInfo where ID like '%" + s
							+ "%' union select * from StuInfo where Sname like '%" + s + "%'"
							+ " union select * from StuInfo where IDcard like '%" + s + "%'");

					while (rs0.next()) {
						Vector<String> vtmp = new Vector<String>();
						sbi[total].ID = rs0.getString("ID");
						sbi[total].Name = rs0.getString("Sname");
						sbi[total].Sex = rs0.getString("Ssex");
						sbi[total].Birthday = rs0.getString("Sbirth");
						sbi[total].Department = rs0.getString("Sdept");
						sbi[total].IDcard = rs0.getString("IDcard");
						sbi[total].Brief = rs0.getString("Sbrief");
						vtmp.add(sbi[total].ID);
						vtmp.add(sbi[total].Name);
						vtmp.add(sbi[total].Sex);
						vtmp.add(sbi[total].Birthday);
						vtmp.add(sbi[total].Department);
						vtmp.add(sbi[total].IDcard);
						vtmp.add(sbi[total].Brief);
						v.add(vtmp);
						total++;
					}

				} catch (Exception e1) {}

			} catch (Exception ex) {

				ResultSet rs0 = stat.executeQuery("select * from StuInfo where Sname like '%" + s
						+ "%' union select * from StuInfo where Sdept like '%" + s + "%'"
						+ " union select * from StuInfo where Ssex = '" + s + "'"
						+ " union select * from StuInfo where IDcard like '%" + s + "%'"
						+ " union select * from StuInfo where Sbirth like '%" + s + "%'"
						+ " union select * from StuInfo where Sbrief like '%" + s + "%'");
				while (rs0.next()) {
					Vector<String> vtmp = new Vector<String>();
					sbi[total].ID = rs0.getString("ID");
					sbi[total].Name = rs0.getString("Sname");
					sbi[total].Sex = rs0.getString("Ssex");
					sbi[total].Birthday = rs0.getString("Sbirth");
					sbi[total].Department = rs0.getString("Sdept");
					sbi[total].IDcard = rs0.getString("IDcard");
					sbi[total].Brief = rs0.getString("Sbrief");
					vtmp.add(sbi[total].ID);
					vtmp.add(sbi[total].Name);
					vtmp.add(sbi[total].Sex);
					vtmp.add(sbi[total].Birthday);
					vtmp.add(sbi[total].Department);
					vtmp.add(sbi[total].IDcard);
					vtmp.add(sbi[total].Brief);
					v.add(vtmp);
					total++;
				}

			}
			// table_1 = new JTable(v,v0);

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		table_1 = new JTable(v, v0);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setCellSelectionEnabled(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setBounds(22, 25, 675, 408);
		table_1.setEnabled(false);
		contentPane.add(table_1);

	}
}
