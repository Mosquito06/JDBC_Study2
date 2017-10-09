package Frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dao.EmployeeDao;
import JDBC_Study.dto.Department;
import JDBC_Study.dto.Employee;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
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
	private MainFrame mainframe;
	private JLabel lblLeave;
	private JTextField textLeave;

	public void setMainframe(MainFrame mainframe) {
		this.mainframe = mainframe;
	}

	public JComboBox getBoxManager() {
		return boxManager;
	}

	public JComboBox getBoxDno() {
		return boxDno;
	}

	public EmployeeFrame() {
		setTitle("\uC0AC\uC6D0\uAD00\uB9AC");
		setBounds(100, 100, 450, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		setLocationRelativeTo(null);

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

		lblTitle = new JLabel("\uC9C1\uCC45");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);

		textTitle = new JTextField();
		textTitle.setColumns(10);
		contentPane.add(textTitle);

		lblManager = new JLabel("\uAD00\uB9AC\uC790");
		lblManager.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblManager);

		boxManager = new JComboBox();
		contentPane.add(boxManager);

		lblSalary = new JLabel("\uAE09\uC5EC");
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSalary);

		spinnerSalary = new JSpinner();
		contentPane.add(spinnerSalary);

		lblDno = new JLabel("\uBD80\uC11C");
		lblDno.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDno);

		boxDno = new JComboBox();
		contentPane.add(boxDno);

		lblLeave = new JLabel("\uD1F4\uADFC\uC2DC\uAC04");
		lblLeave.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblLeave);

		textLeave = new JTextField();
		textLeave.setColumns(10);
		contentPane.add(textLeave);

		btnAdd = new JButton("\uCD94\uAC00");
		contentPane.add(btnAdd);

		btnCancel = new JButton("\uCDE8\uC18C");
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("추가")) {
					EmployeeDao.getInstance().insertEmployee(getModyEmployee());
					mainframe.setTableE();
					clearText();
				} else if (e.getActionCommand().equals("수정")) {
					EmployeeDao.getInstance().updateEmployee(getModyEmployee());
					mainframe.setTableE();
					clearText();
				} else if (e.getActionCommand().equals("삭제")) {
					int empNo = Integer.valueOf(textEmpno.getText());
					EmployeeDao.getInstance().deleteEmployee(new Employee(empNo));
					mainframe.setTableE();
					clearText();
				} else if (e.getActionCommand().equals("검색")) {
					int empNo = Integer.valueOf(textEmpno.getText());
					Employee emp = new Employee(empNo);
					Object[] emp2 = EmployeeDao.getInstance().selectEmployeeByNo(emp).toArray();
					mainframe.setTableE(emp2);
					clearText();
				}

			}

			
		});
	}

	public void setText(String text) {
		if (text.equals("추가")) {
			btnAdd.setText("추가");
			clearText();
			textEmpno.setEditable(true);
			textEmpname.setEditable(true);
			textTitle.setEditable(true);
			boxManager.setEnabled(true);
			spinnerSalary.setEnabled(true);
			boxDno.setEnabled(true);
			textTitle.setEnabled(true);
			textLeave.setEditable(true);
		} else if (text.equals("수정")) {
			btnAdd.setText("수정");
			clearText();
			textEmpno.setEditable(false);
			textEmpname.setEditable(true);
			textTitle.setEditable(true);
			boxManager.setEnabled(true);
			spinnerSalary.setEnabled(true);
			boxDno.setEnabled(true);
			textTitle.setEnabled(true);
			textLeave.setEditable(true);
		} else if (text.equals("삭제")) {
			btnAdd.setText("삭제");
			clearText();
			textEmpno.setEditable(true);
			textEmpname.setEditable(false);
			textTitle.setEnabled(false);
			boxManager.setEnabled(false);
			spinnerSalary.setEnabled(false);
			boxDno.setEnabled(false);
			textTitle.setEditable(false);
			textLeave.setEditable(false);
		} else if (text.equals("검색")) {
			btnAdd.setText("검색");
			clearText();
			textEmpno.setEditable(true);
			textEmpname.setEditable(false);
			textTitle.setEditable(false);
			boxManager.setEnabled(false);
			spinnerSalary.setEnabled(false);
			boxDno.setEnabled(false);
			textTitle.setEditable(false);
			textLeave.setEditable(false);

		}

	}

	private Employee getModyEmployee() {
		int empNo = Integer.valueOf(textEmpno.getText());
		String empName = textEmpname.getText();
		String title = textTitle.getText();

		String m1 = (String) boxManager.getSelectedItem();
		int manager = Integer.valueOf(m1.substring(1, 5));

		int salary = (int) spinnerSalary.getValue();

		String m2 = (String) boxDno.getSelectedItem();
		int dno = Integer.valueOf(m2.substring(1, 2));

		String leaveOffice = textLeave.getText();

		return new Employee(empNo, empName, title, manager, salary, dno, leaveOffice);
	}
	
	
	public void setModyText(Employee e) {
		textEmpno.setText(String.valueOf(e.getEmpNo()));
		textEmpname.setText(e.getEmpName());
		textTitle.setText(e.getTitle());

		boxManager.setModel(EmployeeDao.getInstance().getComboData("관리자", e));

		SpinnerNumberModel spModel = new SpinnerNumberModel(e.getSalary(), 0, 100000000, 100000);
		spinnerSalary.setModel(spModel);

		boxDno.setModel(EmployeeDao.getInstance().getComboData("부서", e));

		textLeave.setText(e.getLeaveOffice());
	}

	public void clearText() {
		for (Component c : contentPane.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			}
			if (c instanceof JComboBox) {
				((JComboBox) c).setModel(new DefaultComboBoxModel<>());
			}
			if (c instanceof JSpinner) {
				((JSpinner) c).setModel(new SpinnerNumberModel());
			}
		}
	}

}
