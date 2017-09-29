package Frame;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dto.Department;

public class DepartmentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textDeptno;
	private JTextField textName;
	private JTextField textFloor;
	private JButton btnCancel;
	private JButton btnAdd;
	private JLabel lblDeptName;
	private JLabel lblFloor;
	private JLabel lblDeptno;
	private MainFrame mainframe;

	public JTextField getTextDeptno() {
		return textDeptno;
	}

	public void setMainframe(MainFrame mainframe) {
		this.mainframe = mainframe;
	}

	public DepartmentFrame() {
		setTitle("\uBD80\uC11C\uAD00\uB9AC");
		setBounds(100, 100, 293, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		setLocationRelativeTo(null);

		lblDeptno = new JLabel("\uBD80\uC11C\uBC88\uD638");
		lblDeptno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDeptno);

		textDeptno = new JTextField();
		textDeptno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textDeptno);
		textDeptno.setColumns(10);

		lblDeptName = new JLabel("\uBD80\uC11C\uBA85");
		lblDeptName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDeptName);

		textName = new JTextField();
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textName);
		textName.setColumns(10);

		lblFloor = new JLabel("\uC704\uCE58");
		lblFloor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFloor);

		textFloor = new JTextField();
		textFloor.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(textFloor);
		textFloor.setColumns(10);

		btnAdd = new JButton("\uCD94\uAC00");
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("수정")) {
					DepartmentDao.getInstance().updateDepartment(getModyDepartment());
					mainframe.setTable();
					clearText();
				} else if (e.getActionCommand().equals("추가")) {
					DepartmentDao.getInstance().insertDepartment(getModyDepartment());
					mainframe.setTable();
					clearText();
				} else if (e.getActionCommand().equals("삭제")) {
					int deptNo = Integer.parseInt(textDeptno.getText());
					Department dept = new Department(deptNo);
					DepartmentDao.getInstance().deleteDepartment(dept);
					mainframe.setTable();
					clearText();
				} else if (e.getActionCommand().equals("검색")) {
					int deptNo = Integer.parseInt(textDeptno.getText());
					Department dept = new Department(deptNo);
					Object[] dept2 = DepartmentDao.getInstance().selectDepartmentByNo(dept).toArray();
					mainframe.setTable(dept2);
					clearText();

				}
			}
		});

		btnCancel = new JButton("\uCDE8\uC18C");
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

	public void setText(String text) {
		if (text.equals("추가")) {
			btnAdd.setText("추가");
			clearText();
			textDeptno.setEditable(false);
			textName.setEditable(true);
			textFloor.setEditable(true);
		} else if (text.equals("수정")) {
			clearText();
			btnAdd.setText("수정");
			textDeptno.setEditable(false);
			textName.setEditable(true);
			textFloor.setEditable(true);
		} else if (text.equals("삭제")) {
			btnAdd.setText("삭제");
			clearText();
			textDeptno.setEditable(true);
			textName.setEditable(false);
			textFloor.setEditable(false);
		} else if (text.equals("검색")) {
			btnAdd.setText("검색");
			clearText();
			textDeptno.setEditable(true);
			textName.setEditable(false);
			textFloor.setEditable(false);
		}

	}

	public Department getModyDepartment() {
		int deptNo = Integer.parseInt(textDeptno.getText());
		String deptName = textName.getText();
		int floor = Integer.parseInt(textFloor.getText());
		return new Department(deptNo, deptName, floor);
	}

	public void setModyText(Department d) {
		textDeptno.setText(String.valueOf(d.getDeptNO()));
		textName.setText(d.getDeptName());
		textFloor.setText(String.valueOf(d.getFloor()));
	}

	public void clearText() {
		for (Component c : contentPane.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
	}

}
