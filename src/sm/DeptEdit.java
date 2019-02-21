package sm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeptEdit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7890772918666005016L;
	private JPanel contentPane;
	DeptInfo di[] = new DeptInfo[20];
	int p = 0;
	int total = 0;
	private JTextField name;
	private JTextField president;
	private JTextField stusum;

	/**
	 * Create the frame.
	 */
	public DeptEdit() {
		setTitle("\u9662\u7CFB\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u9662    \u7CFB");
		label.setBounds(24, 20, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u9662    \u957F");
		label_1.setBounds(24, 45, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u5B66\u751F\u4EBA\u6570");
		label_2.setBounds(24, 70, 54, 15);
		contentPane.add(label_2);

		JButton DeptDelete = new JButton("\u5220\u9664");
		DeptDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确定删除院系信息吗？该院学生及选课信息也将一并删除！", "警告",
						JOptionPane.YES_NO_OPTION) == 0) {
					try {
						Connection con = SQLHelper.getConnection();
						Statement stat = con.createStatement();
						stat.execute("delete from DeptInfo where Dname = '" + name.getText() + "'");
						stat.execute("delete from Stuinfo where Sdept = '" + name.getText() + "'");
						JOptionPane.showMessageDialog(null, "删除成功");
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null, "请输入正确的数据！");
						e2.printStackTrace();
					}
				}
			}
		});
		DeptDelete.setBounds(271, 141, 81, 23);
		contentPane.add(DeptDelete);
		for (int j = 0; j < di.length; j++)
			di[j] = new DeptInfo();

		name = new JTextField();
		name.setBounds(101, 17, 208, 21);
		contentPane.add(name);
		name.setColumns(10);

		president = new JTextField();
		president.setColumns(10);
		president.setBounds(101, 42, 208, 21);
		contentPane.add(president);

		stusum = new JTextField();
		stusum.setEnabled(false);
		stusum.setColumns(10);
		stusum.setBounds(101, 67, 208, 21);
		contentPane.add(stusum);

		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();
			ResultSet rs = null;
			rs = stat.executeQuery("select * from DeptInfo");
			while (rs.next()) {
				di[p] = new DeptInfo();
				di[p].Dname = rs.getString("Dname");
				di[p].President = rs.getString("Dpresident");
				di[p].StuSum = rs.getString("StuSum");
				p++;
			}
			total = p;
			p = 0;

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		name.setText(di[0].Dname);
		president.setText(di[0].President);

		JButton btnUp = new JButton("\u4E0A\u4E00\u6761");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (p > 0) {
					p--;
					name.setText(di[p].Dname);
					president.setText(di[p].President);
					stusum.setText(di[p].StuSum);
				} else
					JOptionPane.showMessageDialog(null, "已经是第一个院系了");
			}
		});
		btnUp.setBounds(10, 141, 77, 23);
		contentPane.add(btnUp);

		JButton btnDown = new JButton("\u4E0B\u4E00\u6761");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (p < total - 1) {
					p++;
					name.setText(di[p].Dname);
					president.setText(di[p].President);
					stusum.setText(di[p].StuSum);
				} else
					JOptionPane.showMessageDialog(null, "已经是最后一个院系了");
			}
		});
		btnDown.setBounds(97, 141, 77, 23);
		contentPane.add(btnDown);

		JButton DeptUpdate = new JButton("\u66F4\u65B0");
		DeptUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确定更新院系信息吗！", "提示", JOptionPane.YES_NO_OPTION) == 0) {
					try {

						Connection con = SQLHelper.getConnection();
						Statement stat = con.createStatement();
						stat.execute("Update DeptInfo set dname = '" + name.getText() + "',dpresident = '"
								+ president.getText() + "',stusum = " + stusum.getText() + " where Dname ='"
								+ di[p].Dname + "'");
						JOptionPane.showMessageDialog(null, "更新成功！");

					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		DeptUpdate.setBounds(184, 141, 77, 23);
		contentPane.add(DeptUpdate);
	}

}
