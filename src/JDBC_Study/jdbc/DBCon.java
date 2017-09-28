package JDBC_Study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private String user = "root";
	private String password = "rootroot";
	private String url = "jdbc:mysql://localhost/mysql_study";
	private Connection conn;
	// DB ������ �ð��� ���� �ɸ��� �۾��̱� ������ ������ �����ϸ� ���α׷��� ���� �ð��� ������.
	// �׷��� ������ DB ��ü�� ���������� �������� ���ϵ��� Ŭ������ �ʵ忡�� DB�� �����ϰ� �����ڸ� private���� ��.
	// �׸��� �ش� �ʵ带 static���� �ϰ�, �ش� �ʵ忡 ���� get, set�� ����
	// �̷��� ����� �̱��� �����̶� �Ѵ�.
	// �̱��� ����: �ش� Ŭ������ �ν��Ͻ��� �ϳ��� ���������, ��𼭵��� �ش� �ν��Ͻ��� ������ �� �ֵ��� �ϱ� ���� ����
	private static final DBCon instance = new DBCon();

	public static DBCon getInstance() {
		return instance;
	}

	public Connection getConn() {
		return conn;
	}

	private DBCon() {
		try {
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		}
	}

	public void connCloser() {
		jdbcUtil.close(conn);
	}

}
