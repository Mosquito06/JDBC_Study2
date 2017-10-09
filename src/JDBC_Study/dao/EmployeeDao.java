package JDBC_Study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import JDBC_Study.dto.Department;
import JDBC_Study.dto.Employee;
import JDBC_Study.jdbc.eDBCon;
import JDBC_Study.jdbc.jdbcUtil;

public class EmployeeDao {

	private static final EmployeeDao instance = new EmployeeDao();

	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;

	private EmployeeDao() {

	}

	public static EmployeeDao getInstance() {
		return instance;
	}

	public List<Employee> selectEmployeeByAll() {
		List<Employee> lists = new ArrayList<>();
		sql = "select empno, empname, title, manager, salary, dno, leaveoffice from employee";
		con = eDBCon.getInstance().getCon();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int empNo = rs.getInt("empNo");
				String empName = rs.getString("empName");
				String title = rs.getString("title");
				int manager = rs.getInt("manager");
				int salary = rs.getInt("salary");
				int dno = rs.getInt("dno");
				String leaveOffice = rs.getString("leaveOffice");
				lists.add(new Employee(empNo, empName, title, manager, salary, dno, leaveOffice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		return lists;
	}

	public Employee selectEmployeeByNo(Employee e) {
		Employee employee = null;
		sql = "select empno, empname, title, manager, salary, dno, leaveoffice from employee where empno = ?";
		try {
			pstmt = eDBCon.getInstance().getCon().prepareStatement(sql);
			pstmt.setInt(1, e.getEmpNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				employee = new Employee(rs.getInt("empno"), rs.getString("empname"), rs.getString("title"),
						rs.getInt("manager"), rs.getInt("salary"), rs.getInt("dno"), rs.getString("leaveoffice"));
				JOptionPane.showMessageDialog(null, "검색을 완료하였습니다");
			} else {
				JOptionPane.showMessageDialog(null, "검색하신 사원 존재하지 않습니다");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}

		return employee;
	}

	public void insertEmployee(Employee e) {
		sql = "insert into employee values(?,?,?,?,?,?,?)";
		con = eDBCon.getInstance().getCon();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, e.getEmpNo());
			pstmt.setString(2, e.getEmpName());
			pstmt.setString(3, e.getTitle());
			pstmt.setInt(4, e.getManager());
			pstmt.setInt(5, e.getSalary());
			pstmt.setInt(6, e.getDno());
			pstmt.setString(7, e.getLeaveOffice());

			int res = pstmt.executeUpdate();
			if (res > 0) {
				JOptionPane.showMessageDialog(null, "데이터를 추가하였습니다");
			} else {
				JOptionPane.showMessageDialog(null, "데이터를 추가에 실패하였습니다");
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "데이터를 추가에 실패하였습니다");
		} finally {
			jdbcUtil.close(pstmt);
		}
	}

	public void updateEmployee(Employee e) {
		sql = "update employee set empname = ?, title = ?, manager = ?, salary = ?, dno = ?, leaveoffice = ? where empno = ?";
		try {
			pstmt = eDBCon.getInstance().getCon().prepareStatement(sql);
			pstmt.setString(1, e.getEmpName());
			pstmt.setString(2, e.getTitle());
			pstmt.setInt(3, e.getManager());
			pstmt.setInt(4, e.getSalary());
			pstmt.setInt(5, e.getDno());
			pstmt.setString(6, e.getLeaveOffice());
			pstmt.setInt(7, e.getEmpNo());
			
			if (pstmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "수정하였습니다.");
			} else {
				JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}

	}

	public void deleteEmployee(Employee e) {
		sql = "delete from employee where empno = ?";
		try {
			pstmt = eDBCon.getInstance().getCon().prepareStatement(sql);
			pstmt.setInt(1, e.getEmpNo());

			if (pstmt.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다");
			} else {
				JOptionPane.showMessageDialog(null, "삭제 할 수 없습니다");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			jdbcUtil.close(pstmt);
		}

	}

	public DefaultComboBoxModel<String> getComboData(String text, Employee e) {
		DefaultComboBoxModel<String> Model = new DefaultComboBoxModel<>();
		if (text.equals("관리자")) {
			sql = "select empname, title, empno from employee where title != ?";
			con = eDBCon.getInstance().getCon();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, e.getTitle());
				rs = pstmt.executeQuery();

				while (rs.next()) {

					String empName = rs.getString("empName");
					String title = rs.getString("title");
					int empno = rs.getInt("empno");

					Model.addElement("[" + empno + ":" + title + "]" + empName);

				}
			} catch (SQLException SQL) {
				SQL.printStackTrace();
			} finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
			}
		} else if (text.equals("부서")) {
			sql = "select deptno, deptname from department";
			con = eDBCon.getInstance().getCon();
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int deptno = rs.getInt("deptno");
					String deptname = rs.getString("deptname");
					
					Model.addElement("[" + deptno + "]" + deptname);

				}
			} catch (SQLException SQL) {
				SQL.printStackTrace();
			} finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
			}
		} 
		return Model;

	}
	
	public DefaultComboBoxModel<String> setComboData(String text) {
		DefaultComboBoxModel<String> Model = new DefaultComboBoxModel<>();
		if (text.equals("관리자")) {
			sql = "select empname, title, empno from employee";
			con = eDBCon.getInstance().getCon();
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					String empName = rs.getString("empName");
					String title = rs.getString("title");
					int empno = rs.getInt("empno");

					Model.addElement("[" + empno + ":" + title + "]" + empName);

				}
			} catch (SQLException SQL) {
				SQL.printStackTrace();
			} finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
			}
		} else if (text.equals("부서")) {
			sql = "select deptno, deptname from department";
			con = eDBCon.getInstance().getCon();
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int deptno = rs.getInt("deptno");
					String deptname = rs.getString("deptname");
					
					Model.addElement("[" + deptno + "]" + deptname);

				}
			} catch (SQLException SQL) {
				SQL.printStackTrace();
			} finally {
				jdbcUtil.close(rs);
				jdbcUtil.close(pstmt);
			}
		} 
		return Model;

	}

}
