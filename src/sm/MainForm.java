package sm;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

public class MainForm extends JFrame implements Runnable {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -8412201860809260075L;
	StuBasicInfo sbi = StudentManagement.stu;

	@SuppressWarnings("deprecation")
	public MainForm() {
		setResizable(false);

		setTitle("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu System = new JMenu("\u7CFB\u7EDF\u7BA1\u7406");
		System.setHorizontalAlignment(SwingConstants.TRAILING);
		menuBar.add(System);

		JMenuItem Login = new JMenuItem("\u7CFB\u7EDF\u767B\u5F55");

		System.add(Login);

		JMenuItem Pwd_Update = new JMenuItem("\u5BC6\u7801\u4FEE\u6539");

		System.add(Pwd_Update);

		JMenuItem Logout = new JMenuItem("\u6CE8\u9500");

		System.add(Logout);

		JMenuItem Exit = new JMenuItem("\u9000\u51FA");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"确定退出吗", "提示",JOptionPane.YES_NO_OPTION)==0)
				dispose();
			}
		});
		System.add(Exit);

		JMenu StuManage = new JMenu("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(StuManage);

		JMenuItem StuAdd = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u6DFB\u52A0");

		StuManage.add(StuAdd);

		JMenuItem StuUpdate = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u4FEE\u6539");
		StuManage.add(StuUpdate);

		JMenuItem StuDelete = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u5220\u9664");
		StuManage.add(StuDelete);

		JMenu CourseManage = new JMenu("\u8BFE\u7A0B\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(CourseManage);

		JMenuItem CourseAdd = new JMenuItem("\u8BFE\u7A0B\u4FE1\u606F\u6DFB\u52A0");

		JLabel label_2 = new JLabel("\u5F53\u524D\u767B\u5F55\u8EAB\u4EFD");
		label_2.setBounds(175, 286, 85, 15);
		getContentPane().add(label_2);

		JLabel identity = new JLabel("");
		identity.setBounds(270, 286, 104, 15);
		getContentPane().add(identity);
		CourseAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CourseConfig cc = new CourseConfig("添加");
				cc.show();
			}
		});
		CourseManage.add(CourseAdd);

		JMenuItem CourseUpdate = new JMenuItem("\u8BFE\u7A0B\u4FE1\u606F\u4FEE\u6539");
		CourseUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseForm cf = new CourseForm("修改");
				cf.show();
			}
		});
		CourseManage.add(CourseUpdate);

		JMenuItem CourseDelete = new JMenuItem("\u8BFE\u7A0B\u4FE1\u606F\u5220\u9664");
		CourseDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CourseForm cf = new CourseForm("修改");
				cf.show();
			}
		});
		CourseManage.add(CourseDelete);

		JMenu SCManage = new JMenu("\u5B66\u751F\u9009\u8BFE");
		menuBar.add(SCManage);

		JMenuItem SC = new JMenuItem("\u5B66\u751F\u9009\u8BFE");
		SC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SCManage sc = new SCManage();
				sc.show();
			}
		});
		SCManage.add(SC);

		JMenu GradeManage = new JMenu("\u6210\u7EE9\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(GradeManage);

		JMenuItem GradeUpload = new JMenuItem("\u5B66\u751F\u6210\u7EE9\u5F55\u5165");
		GradeUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GradeUpload gu = new GradeUpload();
				gu.show();
			}
		});
		GradeManage.add(GradeUpload);

		JMenu DeptManage = new JMenu("\u9662\u7CFB\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(DeptManage);

		JMenuItem DeptAdd = new JMenuItem("\u6DFB\u52A0\u9662\u7CFB");
		DeptAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeptForm df = new DeptForm();
				df.show();
			}
		});
		DeptManage.add(DeptAdd);

		JMenuItem DeptUpdate = new JMenuItem("\u9662\u7CFB\u4FE1\u606F\u66F4\u65B0");
		DeptUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptEdit se = new DeptEdit();
				se.show();
			}
		});
		DeptManage.add(DeptUpdate);

		JMenuItem DeptDelete = new JMenuItem("\u5220\u9664\u9662\u7CFB");
		DeptDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptEdit se = new DeptEdit();
				se.show();
			}
		});
		DeptManage.add(DeptDelete);

		JMenu DataScan = new JMenu("\u6570\u636E\u67E5\u8BE2");
		menuBar.add(DataScan);

		JMenuItem StuScan = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u6D4F\u89C8");
		StuScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuInfoScan sis = new StuInfoScan();
				sis.show();
			}
		});
		DataScan.add(StuScan);

		JMenuItem CourseScan = new JMenuItem("\u8BFE\u7A0B\u4FE1\u606F\u6D4F\u89C8");
		CourseScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CourseScan cs = new CourseScan();
				cs.show();
			}
		});
		DataScan.add(CourseScan);

		JMenuItem StuGradeManage = new JMenuItem("\u5B66\u751F\u6210\u7EE9\u67E5\u8BE2");
		StuGradeManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GradeScan gs = new GradeScan();
				gs.show();
			}
		});
		DataScan.add(StuGradeManage);
		getContentPane().setLayout(null);

		JLabel label_5 = new JLabel("\u7CFB\u7EDF\u65F6\u95F4\uFF1A");
		label_5.setBounds(135, 311, 65, 15);
		getContentPane().add(label_5);

		StuAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuConfig sc = new StuConfig(2);
				sc.show();
			}
		});
		StuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuForm sf = new StuForm();
				sf.show();

			}
		});

		StuUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuForm sf = new StuForm();
				sf.show();

			}
		});

		Logout.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				StudentManagement sm = new StudentManagement();
				sm.identity = 0;
				sm.Login.setVisible(true);
				dispose();

			}
		});
		Login.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				StudentManagement sm = new StudentManagement();
				sm.identity = 0;
				sm.Login.setVisible(true);
				dispose();
			}
		});
		Pwd_Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pwd_Update pu = new Pwd_Update();
				pu.show();
			}
		});

		if (StudentManagement.identity == 2) {
			StuManage.setEnabled(false);
			CourseManage.setEnabled(false);
			//GradeManage.setEnabled(false);
			DeptManage.setEnabled(false);
			SCManage.setEnabled(false);
			JLabel lblNewLabel = new JLabel("\u7528\u6237\uFF1A");
			lblNewLabel.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			lblNewLabel.setBounds(30, 27, 54, 15);
			getContentPane().add(lblNewLabel);

			JLabel label = new JLabel("ID：");
			label.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label.setBounds(30, 52, 54, 15);
			getContentPane().add(label);

			JLabel name = new JLabel("");
			name.setBounds(100, 27, 183, 15);
			getContentPane().add(name);

			JLabel ID = new JLabel("");
			ID.setBounds(100, 52, 183, 15);
			getContentPane().add(ID);

			try {
				Connection con = SQLHelper.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery("select * from TeacherInfo where ID = " + sbi.ID);
				while (rs.next()) {
					ID.setText(rs.getString("ID"));
					name.setText(rs.getString("Tname"));
					identity.setText("    教师");
				}
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (StudentManagement.identity == 0) {
			StuManage.setVisible(false);
			CourseManage.setVisible(false);
			GradeManage.setVisible(false);
			DeptManage.setVisible(false);
			StuScan.setVisible(false);
			
			JLabel lblNewLabel = new JLabel("\u7528\u6237\uFF1A");
			lblNewLabel.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			lblNewLabel.setBounds(30, 27, 54, 15);
			getContentPane().add(lblNewLabel);

			JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
			label.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label.setBounds(30, 52, 54, 15);
			getContentPane().add(label);

			JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
			label_1.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label_1.setBounds(30, 77, 54, 15);
			getContentPane().add(label_1);

			JLabel lbli = new JLabel("\u751F\u65E5\uFF1A");
			lbli.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			lbli.setBounds(30, 102, 54, 15);
			getContentPane().add(lbli);

			JLabel label_3 = new JLabel("\u9662\u7CFB\uFF1A");
			label_3.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label_3.setBounds(30, 127, 54, 15);
			getContentPane().add(label_3);

			JLabel label_4 = new JLabel("个人简介");
			label_4.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label_4.setBounds(30, 187, 80, 15);
			getContentPane().add(label_4);

			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("楷体", Font.CENTER_BASELINE, 15));
			textArea.setBounds(120, 187, 376, 75);
			textArea.setEditable(false);
			getContentPane().add(textArea);

			JLabel name = new JLabel("");
			name.setBounds(100, 27, 183, 15);
			getContentPane().add(name);

			JLabel ID = new JLabel("");
			ID.setBounds(100, 52, 183, 15);
			getContentPane().add(ID);

			JLabel sex = new JLabel("");
			sex.setBounds(100, 77, 183, 15);
			getContentPane().add(sex);

			JLabel birthday = new JLabel("");
			birthday.setBounds(100, 102, 183, 15);
			getContentPane().add(birthday);

			JLabel dept = new JLabel("");
			dept.setBounds(100, 127, 183, 15);
			getContentPane().add(dept);

			JLabel IDcard = new JLabel("");
			IDcard.setBounds(100, 152, 183, 15);
			getContentPane().add(IDcard);

			JLabel label_6 = new JLabel("\u8EAB\u4EFD\u8BC1\uFF1A");
			label_6.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label_6.setBounds(30, 152, 65, 15);
			getContentPane().add(label_6);

			try {
				Connection con = SQLHelper.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery("select * from StuInfo where ID = " + sbi.ID);
				while (rs.next()) {
					ID.setText(rs.getString("ID"));
					name.setText(rs.getString("Sname"));
					sex.setText(rs.getString("Ssex"));
					IDcard.setText(rs.getString("IDcard"));
					dept.setText(rs.getString("Sdept"));
					birthday.setText(rs.getString("Sbirth"));
					textArea.setText(rs.getString("Sbrief"));
					identity.setText("    学生");
					ImagePanel ip = null;
					try {
						ip = new ImagePanel(ImageIO.read(new FileInputStream(rs.getString("Sphoto"))));
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					ip.setBounds(421, 32, 90, 125);
					getContentPane().add(ip);
				}
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		if (StudentManagement.identity == 1) {
			SCManage.setVisible(false);
			JLabel lblNewLabel = new JLabel("\u7528\u6237\uFF1A");
			lblNewLabel.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			lblNewLabel.setBounds(30, 27, 54, 15);
			getContentPane().add(lblNewLabel);

			JLabel label = new JLabel("ID：");
			label.setFont(new Font("楷体", Font.CENTER_BASELINE, 14));
			label.setBounds(30, 52, 54, 15);
			getContentPane().add(label);

			JLabel name = new JLabel("");
			name.setBounds(100, 27, 183, 15);
			getContentPane().add(name);

			JLabel ID = new JLabel("");
			ID.setBounds(100, 52, 183, 15);
			getContentPane().add(ID);

			try {
				Connection con = SQLHelper.getConnection();
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery("select * from AdminInfo where ID = " + sbi.ID);
				while (rs.next()) {
					ID.setText(rs.getString("ID"));
					name.setText(rs.getString("Aname"));
					identity.setText("    系统管理员");
				}
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		int ONE_SECOND = 1000;
		String DEFAULT_FORMAT = "YYYY年MM月dd日 EEE HH:mm:ss";

		JLabel displayArea = new JLabel("");
		displayArea.setBounds(217, 311, 200, 15);
		getContentPane().add(displayArea);
		while (true) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_FORMAT);
			displayArea.setText(dateFormatter.format(Calendar.getInstance().getTime()));
			try {
				Thread.sleep(ONE_SECOND);
			} catch (Exception e) {
				displayArea.setText("Error!!!");
			}
		}

	}
}
