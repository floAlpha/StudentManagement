package sm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SCManage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6735065604056316428L;

	private JPanel contentPane;

	StuBasicInfo stu = StudentManagement.stu;
	SCoperate sco;
	int total = 0;

	/**
	 * Create the frame.
	 */

	public SCManage() {
		setTitle("\u5B66\u751F\u9009\u8BFE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 730, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setFont(new Font("华文楷体", Font.BOLD, 16));
		lblNewLabel.setBounds(23, 21, 57, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		lblNewLabel_1.setFont(new Font("华文楷体", Font.BOLD, 16));
		lblNewLabel_1.setBounds(270, 21, 57, 30);
		contentPane.add(lblNewLabel_1);

		JLabel Stuname = new JLabel("");
		Stuname.setText(stu.Name);
		Stuname.setFont(new Font("华文楷体", Font.BOLD, 16));
		Stuname.setBounds(104, 21, 95, 30);
		contentPane.add(Stuname);

		JLabel StuID = new JLabel("");
		StuID.setText(stu.ID);
		StuID.setFont(new Font("华文楷体", Font.BOLD, 16));
		StuID.setBounds(351, 21, 95, 30);
		contentPane.add(StuID);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 73, 694, 2);
		contentPane.add(separator);

		JLabel label = new JLabel("\u5B66\u5E74");
		label.setBounds(23, 88, 41, 15);
		contentPane.add(label);

		JComboBox<String> Year = new JComboBox<String>();

		Year.addItem("2016-2017学年");
		Year.addItem("2015-2016学年");
		Year.addItem("2014-2015学年");
		Year.setBounds(57, 85, 123, 21);
		contentPane.add(Year);

		JLabel label_1 = new JLabel("\u5B66\u671F");
		label_1.setBounds(249, 88, 41, 15);
		contentPane.add(label_1);

		JComboBox<String> Semester = new JComboBox<String>();

		Semester.addItem("上半学期");
		Semester.addItem("下半学期");
		Semester.setBounds(286, 85, 123, 21);

		contentPane.add(Semester);

		JComboBox<String> Type = new JComboBox<String>();

		sco = new SCoperate((String) Year.getSelectedItem() + (String) Semester.getSelectedItem(),
				(String) Type.getSelectedItem());
		Type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				contentPane.remove(sco);
				sco = new SCoperate((String) Year.getSelectedItem() + (String) Semester.getSelectedItem(),
						(String) Type.getSelectedItem());
				sco.setBounds(30, 129, 660, 300);
				getContentPane().add(sco);
			}
		});

		
		Year.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.remove(sco);
				sco = new SCoperate((String) Year.getSelectedItem() + (String) Semester.getSelectedItem(),
						(String) Type.getSelectedItem());
				sco.setBounds(30, 129, 660, 300);
				getContentPane().add(sco);
			}
		});
		
		Semester.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				contentPane.remove(sco);
				sco = new SCoperate((String) Year.getSelectedItem() + (String) Semester.getSelectedItem(),
						(String) Type.getSelectedItem());
				sco.setBounds(30, 129, 660, 300);
				getContentPane().add(sco);
			}
		});
		Type.addItem("公共选修");
		Type.addItem("专业选修");
		Type.setBounds(567, 85, 123, 21);
		contentPane.add(Type);

		JLabel label_2 = new JLabel("\u9009\u4FEE\u7C7B\u578B");
		label_2.setBounds(487, 88, 54, 15);
		contentPane.add(label_2);

	}

}
