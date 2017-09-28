package JDBC_Study.dto;

public class Department {
	private int deptNo;
	private String deptName;
	private int floor;

	public Department(int deptNO) {
		this.deptNo = deptNO;
	}

	public Department(int deptNO, String deptName, int floor) {
		this.deptNo = deptNO;
		this.deptName = deptName;
		this.floor = floor;
	}

	public int getDeptNO() {
		return deptNo;
	}

	public void setDeptNO(int deptNO) {
		this.deptNo = deptNO;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("부서번호 : %s, 부서명 : %s, 위치 : %s층]", deptNo, deptName, floor);
	}

	public Object[] toArray() {
		return new Object[]{deptNo, deptName, floor};
	}

}
