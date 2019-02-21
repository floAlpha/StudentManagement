package sm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;

public class StuInfoScan extends JFrame {

	/**
	 * create by chuan in 2016/12/02 Fri.
	 */
	private static final long serialVersionUID = -3845063493196542030L;
	private JPanel contentPane;
	public static StuBasicInfo sbi = StuForm.sbi;
	StuBasicInfo sb[];
	int p = 0;
	int i = 0;
	private JTextField textSearch;
	String s = "";
	ImagePanel ip = null;
	// public static String keyword = "";

	/**
	 * Create the frame.
	 */
	public StuInfoScan() {
		setTitle("\u5B66\u751F\u4FE1\u606F\u6D4F\u89C8");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(5, 5, 0, 299);
		contentPane.add(label);

		JLabel lblNewLabel = new JLabel("\u5B66    \u53F7");
		lblNewLabel.setBounds(25, 84, 54, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u59D3    \u540D");
		lblNewLabel_1.setBounds(25, 109, 54, 15);
		contentPane.add(lblNewLabel_1);

		JLabel label_1 = new JLabel("\u6027    \u522B");
		label_1.setBounds(25, 134, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u51FA\u751F\u5E74\u6708");
		label_2.setBounds(25, 159, 54, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u9662    \u7CFB");
		label_3.setBounds(25, 184, 54, 15);
		contentPane.add(label_3);

		JLabel label_5 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");
		label_5.setBounds(25, 209, 54, 15);
		contentPane.add(label_5);

		JLabel lblNewLabel_2 = new JLabel("\u4E2A\u4EBA\u7B80\u4ECB");
		lblNewLabel_2.setBounds(25, 254, 54, 15);
		contentPane.add(lblNewLabel_2);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(110, 250, 281, 71);
		contentPane.add(textArea);
		textArea.setText(sbi.Brief);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 72, 414, 2);
		contentPane.add(separator);

		JLabel label_4 = new JLabel("\u6A21\u7CCA\u641C\u7D22\u5B66\u751F");
		label_4.setFont(new Font("仿宋", Font.BOLD, 14));
		label_4.setBounds(25, 14, 101, 15);
		contentPane.add(label_4);

		textSearch = new JTextField();
		textSearch.setBounds(136, 11, 202, 21);
		contentPane.add(textSearch);
		textSearch.setColumns(10);

		JButton btnUp = new JButton("\u4E0A\u4E00\u6761");

		btnUp.setBounds(40, 331, 93, 23);
		contentPane.add(btnUp);

		JButton btnDown = new JButton("\u4E0B\u4E00\u6761");

		btnDown.setBounds(168, 331, 93, 23);
		contentPane.add(btnDown);

		JButton btnCancel = new JButton("\u8FD4\u56DE");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(298, 331, 93, 23);
		contentPane.add(btnCancel);

		JLabel lblID = new JLabel("");
		lblID.setBounds(108, 84, 153, 15);
		contentPane.add(lblID);

		JLabel lblName = new JLabel("");
		lblName.setBounds(108, 109, 153, 15);
		contentPane.add(lblName);

		JLabel lblSex = new JLabel("");
		lblSex.setBounds(108, 134, 153, 15);
		contentPane.add(lblSex);

		JLabel lblBirthday = new JLabel("");
		lblBirthday.setBounds(108, 159, 153, 15);
		contentPane.add(lblBirthday);

		JLabel lblDept = new JLabel("");
		lblDept.setBounds(108, 184, 153, 15);
		contentPane.add(lblDept);

		JLabel lblIDcard = new JLabel("");
		lblIDcard.setBounds(108, 209, 153, 15);
		contentPane.add(lblIDcard);
		try {
			Connection con = SQLHelper.getConnection();
			Statement stat = con.createStatement();

			sb = new StuBasicInfo[200];

			try {
				ResultSet rs = stat.executeQuery("select * from StuInfo");

				while (rs.next()) {
					sb[i] = new StuBasicInfo();
					if (!rs.getString("ID").equals(""))
						sb[i].ID = rs.getString("ID");
					if (!rs.getString("Sname").equals(""))
						sb[i].Name = rs.getString("Sname");
					if (!rs.getString("Ssex").equals(""))
						sb[i].Sex = rs.getString("Ssex");
					if (!rs.getString("Sbirth").equals(""))
						sb[i].Birthday = rs.getString("Sbirth");
					if (!rs.getString("Sdept").equals(""))
						sb[i].Department = rs.getString("Sdept");
					if (!rs.getString("IDCard").equals(""))
						sb[i].IDcard = rs.getString("IDcard");
					if (!rs.getString("Sbrief").equals(""))
						sb[i].Brief = rs.getString("Sbrief");
					if (!rs.getString("Sphoto").equals(""))
						sb[i].Photo = rs.getString("Sphoto");
					else {
						sb[i].Photo = "E:\\Picture\\default.jpg";
					}
					i++;

				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "没有找到学生信息");
			}

			lblID.setText(sb[p].ID);
			lblName.setText(sb[p].Name);
			lblSex.setText(sb[p].Sex);
			lblBirthday.setText(sb[p].Birthday);
			lblDept.setText(sb[p].Department);
			lblIDcard.setText(sb[p].IDcard);
			textArea.setText(sb[p].Brief);
			try {
				ip = new ImagePanel(ImageIO.read(new FileInputStream(sb[p].Photo)));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ip.setSize(90, 120);
			ip.setLocation(316, 84);
			getContentPane().add(ip);
			ip.updateUI();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		JButton btnSerach = new JButton("\u641C\u7D22");
		btnSerach.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				s = textSearch.getText();
				StuQuery sq = new StuQuery(s);
				sq.show();
			}
		});
		btnSerach.setBounds(348, 10, 76, 23);
		contentPane.add(btnSerach);

		JLabel label_6 = new JLabel(
				"\u6CE8\u610F\uFF1A\u8BF7\u5C3D\u91CF\u8F93\u5165\u5B8C\u6574\u4FE1\u606F\u641C\u7D22\uFF0C\u4EE5\u514D\u51FA\u73B0\u5927\u91CF\u65E0\u7528\u7ED3\u679C");
		label_6.setBounds(25, 47, 399, 15);
		contentPane.add(label_6);

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (p > 0) {
					p--;
					lblID.setText(sb[p].ID);
					lblName.setText(sb[p].Name);
					lblSex.setText(sb[p].Sex);
					lblBirthday.setText(sb[p].Birthday);
					lblDept.setText(sb[p].Department);
					lblIDcard.setText(sb[p].IDcard);
					textArea.setText(sb[p].Brief);
					try {
						ip = new ImagePanel(ImageIO.read(new FileInputStream(sb[p].Photo)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ip.setSize(90, 120);
					ip.setLocation(316, 84);
					getContentPane().add(ip);
					ip.updateUI();
				} else
					JOptionPane.showMessageDialog(null, "已经是第一条了");
			}
		});
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (p < i - 1) {
					p++;
					lblID.setText(sb[p].ID);
					lblName.setText(sb[p].Name);
					lblSex.setText(sb[p].Sex);
					lblBirthday.setText(sb[p].Birthday);
					lblDept.setText(sb[p].Department);
					lblIDcard.setText(sb[p].IDcard);
					textArea.setText(sb[p].Brief);

					try {
						ip = new ImagePanel(ImageIO.read(new FileInputStream(sb[p].Photo)));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					ip.setSize(90, 120);
					ip.setLocation(316, 84);
					getContentPane().add(ip);
					ip.updateUI();
				} else
					JOptionPane.showMessageDialog(null, "已经最后一条了");
			}
		});

	}
}
