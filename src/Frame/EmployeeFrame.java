package Frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import JDBC_Study.dto.Department;
import JDBC_Study.dto.Employee;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class EmployeeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textEmpno;
	private JTextField textEmpname;
	private JTextField textTitle;
	private JButton btnAdd;
	private JButton btnCancel;
	private JLabel lblTitle;
	private JSpinner spinnerSalary;
	private JLabel lblSalary;
	private JLabel lblDno;
	private JLabel lblManager;
	private JLabel lblEmpname;
	private JLabel lblEmpno;
	private JComboBox boxManager;
	private JComboBox boxDno;

	public void setBoxManager(JComboBox boxManager) {
		this.boxManager = boxManager;
	}

	public EmployeeFrame() {
		setTitle("\uC0AC\uC6D0\uAD00\uB9AC");
		setBounds(100, 100, 450, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		lblEmpno = new JLabel("\uC0AC\uC6D0\uBC88\uD638");
		lblEmpno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEmpno);

		textEmpno = new JTextField();
		contentPane.add(textEmpno);
		textEmpno.setColumns(10);

		lblEmpname = new JLabel("\uC0AC\uC6D0\uBA85");
		lblEmpname.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEmpname);

		textEmpname = new JTextField();
		contentPane.add(textEmpname);
		textEmpname.setColumns(10);

		lblManager = new JLabel("\uAD00\uB9AC\uC790");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblManager);

		boxManager = new JComboBox();
		contentPane.add(boxManager);

		lblDno = new JLabel("\uBD80\uC11C");
		lblDno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDno);

		boxDno = new JComboBox();
		contentPane.add(boxDno);

		lblSalary = new JLabel("\uAE09\uC5EC");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSalary);

		spinnerSalary = new JSpinner();
		contentPane.add(spinnerSalary);

		lblTitle = new JLabel("\uC9C1\uCC45");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);

		textTitle = new JTextField();
		textTitle.setColumns(10);
		contentPane.add(textTitle);

		btnAdd = new JButton("\uCD94\uAC00");
		contentPane.add(btnAdd);

		btnCancel = new JButton("\uCDE8\uC18C");
		contentPane.add(btnCancel);
		setLocationRelativeTo(null);
	}

	public void setText(String text, Object[][] objects) {
		if (text.equals("추가")) {
			btnAdd.setText("추가");
			clearText();

		} else if (text.equals("수정")) {
			System.out.println("확인");

		} else if (text.equals("삭제")) {

		} else if (text.equals("검색")) {

		}

	}

	public void setModyText(Employee e) {
		textEmpno.setText(String.valueOf(e.getEmpNo()));
		textEmpname.setText(e.getEmpName());
		// boxManager.s
	}

	public void clearText() {
		for (Component c : contentPane.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
	}

}
