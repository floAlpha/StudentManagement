package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DeptForm extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -9034819094735238605L;
	private JPanel contentPane;
	private JTextField nameadd;
	private JTextField presidentadd;
	private JTextField stusumadd;

	/**
	 * Create the frame.
	 */
	public DeptForm() {
		setTitle("\u6DFB\u52A0\u9662\u7CFB");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 357, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_3 = new JLabel("\u9662    \u7CFB");
		label_3.setBounds(26, 33, 54, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\u9662    \u957F");
		label_4.setBounds(26, 58, 54, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\u5B66\u751F\u4EBA\u6570");
		label_5.setBounds(26, 83, 54, 15);
		contentPane.add(label_5);

		nameadd = new JTextField();
		nameadd.setColumns(10);
		nameadd.setBounds(107, 30, 201, 21);
		contentPane.add(nameadd);

		presidentadd = new JTextField();
		presidentadd.setColumns(10);
		presidentadd.setBounds(107, 55, 201, 21);
		contentPane.add(presidentadd);

		stusumadd = new JTextField();
		stusumadd.setEditable(false);
		stusumadd.setColumns(10);
		stusumadd.setBounds(107, 80, 201, 21);
		contentPane.add(stusumadd);

		JButton DeptAdd = new JButton("\u6DFB\u52A0\u9662\u7CFB");
		DeptAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = SQLHelper.getConnection();
					Statement stat = con.createStatement();
					stat.execute("insert into DeptInfo values('" + nameadd.getText() + "','" + presidentadd.getText()
							+ "'," + stusumadd.getText() + ")");
					JOptionPane.showMessageDialog(null, "添加成功！");

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "数据格式错误！");
					e2.printStackTrace();
				}

			}
		});
		DeptAdd.setBounds(26, 158, 93, 23);
		contentPane.add(DeptAdd);

		JButton Cancel = new JButton("\u8FD4\u56DE");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Cancel.setBounds(215, 158, 93, 23);
		contentPane.add(Cancel);

	}
}
