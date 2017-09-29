package Frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dao.EmployeeDao;
import JDBC_Study.dto.Department;
import JDBC_Study.dto.Employee;

public class MainFrame extends JFrame {
	public static final Object[] DEPARTMENT_NAMES = { "부서번호", "부서명", "위치" };
	public static final Object[] EMPLOYEE_NAMES = { "사원번호", "사원명", "관리자", "부서", "급여", "직책", "퇴근시간" };

	private int i;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JPanel panelDepartment;
	private DepartmentFrame Departmentframe = new DepartmentFrame();
	private EmployeeFrame Employeeframe = new EmployeeFrame();
	private JTabbedPane tabbedPane;
	private JPanel panelEmployee;

	DepartmentDao Ddao = DepartmentDao.getInstance();
	EmployeeDao Edao = EmployeeDao.getInstance();

	public MainFrame() {
		Departmentframe.setMainframe(this);

		setTitle("\uD1B5\uD569 \uAD00\uB9AC \uC2DC\uC2A4\uD15C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 385);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int Confirm = JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(Confirm == JOptionPane.YES_OPTION){
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					setVisible(false);
				}
				
			}
		});
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new GridLayout(4, 4, 0, 0));

		JButton btnList = new JButton("\uC2DC\uC791");
		panelWest.add(btnList);
		btnList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					panelDepartment.removeAll();

					model = new DefaultTableModel(getdate(Ddao.selectDepartmentByAll()), MainFrame.DEPARTMENT_NAMES);
					table = new JTable(model);

					JPopupMenu popUp = new JPopupMenu();
					JMenuItem menuItem = new JMenuItem("수정");
					menuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (Departmentframe == null) {
								Departmentframe = new DepartmentFrame();

								Departmentframe.setVisible(true);
							} else {
								Departmentframe.setVisible(true);
							}
							Departmentframe.setText("수정");

							int deptNo = (int) table.getValueAt(table.getSelectedRow(), 0);
							String deptName = (String) table.getValueAt(table.getSelectedRow(), 1);
							int floor = (int) table.getValueAt(table.getSelectedRow(), 2);
							Departmentframe.setModyText(new Department(deptNo, deptName, floor));

						}
					});

					popUp.add(menuItem);
					table.setComponentPopupMenu(popUp);

					panelDepartment.add(table);
					panelDepartment.revalidate();
				} else {
					panelEmployee.removeAll();

					model = new DefaultTableModel(getdateE(Edao.selectEmployeeByAll()), MainFrame.EMPLOYEE_NAMES);
					table = new JTable(model);

					JPopupMenu popUp = new JPopupMenu();
					JMenuItem menuItem = new JMenuItem("수정");
					menuItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (Employeeframe == null) {
								Employeeframe = new EmployeeFrame();

								Employeeframe.setVisible(true);
							} else {
								Employeeframe.setVisible(true);
							}
							
							int empNo = (int) table.getValueAt(table.getSelectedRow(), 0);
							String empName = (String) table.getValueAt(table.getSelectedRow(), 1);
							String title = (String) table.getValueAt(table.getSelectedRow(), 2);
							index 3 == 관리자(콤보박스)
							int salary = (int) table.getValueAt(table.getSelectedRow(), 4);
							index 5 == 부서(콤보박스)
							
							
							
							
						}
						
					});

					popUp.add(menuItem);
					table.setComponentPopupMenu(popUp);

					panelEmployee.add(table);
					panelEmployee.revalidate();
				}

			}

		});

		JButton btnAdd = new JButton("\uCD94\uAC00");
		panelWest.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					if (Departmentframe == null) {
						Departmentframe = new DepartmentFrame();
						Departmentframe.setVisible(true);
					} else {
						Departmentframe.setVisible(true);
					}
					Departmentframe.setText("추가");
					Departmentframe.getTextDeptno().setText(String.valueOf(i + 1));
				} else {
					if (Employeeframe == null) {
						Employeeframe = new EmployeeFrame();

						Employeeframe.setVisible(true);
					} else {
						Employeeframe.setVisible(true);
					}
					Employeeframe.setText("추가");
				}

			}
		});

		JButton btnDel = new JButton("\uC0AD\uC81C");
		panelWest.add(btnDel);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					if (Departmentframe == null) {
						Departmentframe = new DepartmentFrame();

						Departmentframe.setVisible(true);
					} else {
						Departmentframe.setVisible(true);
					}
					Departmentframe.setText("삭제");
				} else {
					if (Employeeframe == null) {
						Employeeframe = new EmployeeFrame();

						Employeeframe.setVisible(true);
					} else {
						Employeeframe.setVisible(true);
					}
					Employeeframe.setText("삭제");
				}
			}
		});

		JButton btnSeach = new JButton("\uAC80\uC0C9");
		panelWest.add(btnSeach);
		btnSeach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					if (Departmentframe == null) {
						Departmentframe = new DepartmentFrame();

						Departmentframe.setVisible(true);
					} else {
						Departmentframe.setVisible(true);
					}
					Departmentframe.setText("검색");
				} else {
					if (Employeeframe == null) {
						Employeeframe = new EmployeeFrame();

						Employeeframe.setVisible(true);
					} else {
						Employeeframe.setVisible(true);
					}
					Employeeframe.setText("검색");
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);

		panelDepartment = new JPanel();
		tabbedPane.addTab("\uBD80\uC11C", null, panelDepartment, null);
		panelDepartment.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelDepartment.add(scrollPane_1);

		panelEmployee = new JPanel();
		tabbedPane.addTab("\uC0AC\uC6D0", null, panelEmployee, null);
		panelEmployee.setLayout(new GridLayout(1, 0, 0, 0));
		setLocationRelativeTo(null);
	}

	public Object[][] getdate(List<Department> lists) {
		Object[][] data = new Object[lists.size()][];
		i = 0;
		for (Department d : lists) {
			data[i] = d.toArray();
			i++;
		}
		return data;
	}

	public Object[][] getdateE(List<Employee> lists) {
		Object[][] data = new Object[lists.size()][];
		i = 0;
		for (Employee e : lists) {
			data[i] = e.toArray();
			i++;
		}
		return data;
	}
	
	public void getNameCombo(List<Employee> lists) {
		
		for (Employee e : lists) {
			
		}
		return ;
	}

	public void modyMethod(Department dept) {
		DepartmentDao.getInstance().updateDepartment(dept);
		panelDepartment.removeAll();
		DepartmentDao dao = DepartmentDao.getInstance();

		model = new DefaultTableModel(getdate(dao.selectDepartmentByAll()), MainFrame.DEPARTMENT_NAMES);
		table = new JTable(model);

	}

	public void setTable() {
		panelDepartment.removeAll();
		DepartmentDao dao = DepartmentDao.getInstance();

		model = new DefaultTableModel(getdate(dao.selectDepartmentByAll()), MainFrame.DEPARTMENT_NAMES);
		table = new JTable(model);
		panelDepartment.add(table);
		panelDepartment.revalidate();
	}

	public void setTable(Object[] dept2) {
		panelDepartment.removeAll();

		Object[][] data = new Object[1][];
		data[0] = dept2;

		model = new DefaultTableModel(data, MainFrame.DEPARTMENT_NAMES);
		table = new JTable(model);
		panelDepartment.add(table);
		panelDepartment.revalidate();
	}

}
