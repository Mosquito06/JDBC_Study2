package Frame;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import JDBC_Study.dao.DepartmentDao;
import JDBC_Study.dto.Department;

public class MainFrame extends JFrame {
	public static final String[] COL_NAMES = { "부서번호", "부서명", "위치" };

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JPanel panelDepartment;
	private int i;
	private DepartmentFrame Optionframe = new DepartmentFrame();
		
	public MainFrame() {
		Optionframe.setMainframe(this);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelWest = new JPanel();
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new GridLayout(4, 4, 0, 0));

		JButton btnAdd = new JButton("\uCD94\uAC00");
		panelWest.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Optionframe == null) {
					Optionframe = new DepartmentFrame();
					Optionframe.setVisible(true);
				} else {
					Optionframe.setVisible(true);
				}
				Optionframe.setText("추가");
				Optionframe.getTextDeptno().setText(String.valueOf(i+1));				
				

			}
		});

		JButton btnList = new JButton("\uBAA9\uB85D");
		panelWest.add(btnList);
		btnList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				panelDepartment.removeAll();
				DepartmentDao dao = DepartmentDao.getInstance();

				model = new DefaultTableModel(getdate(dao.selectDepartmentByAll()), MainFrame.COL_NAMES);
				table = new JTable(model);

				JPopupMenu popUp = new JPopupMenu();
				JMenuItem menuItem = new JMenuItem("수정");
				menuItem.addActionListener(new ActionListener() {
				
				
					@Override
					public void actionPerformed(ActionEvent e) {
						if (Optionframe == null) {
							Optionframe = new DepartmentFrame();
							
							Optionframe.setVisible(true);
						} else {
							Optionframe.setVisible(true);
						}
						Optionframe.setText("수정");
						
						int deptNo = (int) table.getValueAt(table.getSelectedRow(), 0);
						String deptName = (String) table.getValueAt(table.getSelectedRow(), 1);
						int floor = (int) table.getValueAt(table.getSelectedRow(), 2);
						Optionframe.setModyText(new Department(deptNo, deptName, floor));
						

					}
				});

				popUp.add(menuItem);
				table.setComponentPopupMenu(popUp);
				;

				panelDepartment.add(table);
				panelDepartment.revalidate();

			}

		});

		JButton btnDel = new JButton("\uC0AD\uC81C");
		panelWest.add(btnDel);

		JButton btnSeach = new JButton("\uAC80\uC0C9");
		panelWest.add(btnSeach);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);

		panelDepartment = new JPanel();
		tabbedPane.addTab("\uBD80\uC11C", null, panelDepartment, null);
		panelDepartment.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelEmployee = new JPanel();
		tabbedPane.addTab("\uC0AC\uC6D0", null, panelEmployee, null);
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

	public void modyMethod(Department dept) {
		DepartmentDao.getInstance().updateDepartment(dept);
		panelDepartment.removeAll();
		DepartmentDao dao = DepartmentDao.getInstance();

		model = new DefaultTableModel(getdate(dao.selectDepartmentByAll()), MainFrame.COL_NAMES);
		table = new JTable(model);

	}

	public void setTable() {
		panelDepartment.removeAll();
		DepartmentDao dao = DepartmentDao.getInstance();

		model = new DefaultTableModel(getdate(dao.selectDepartmentByAll()), MainFrame.COL_NAMES);
		table = new JTable(model);
		panelDepartment.add(table);
		panelDepartment.revalidate();
	}

}
