package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTest1 {

	public static void main(String[] args) {
		try {
			//1) ����Ŭ DB ���� ���� ����
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	//127.0.0.1 : ��PC����Ŵ
			String user = "java1113";
			String password = "1234";
			String driver = "oracle.jdbc.driver.OracleDriver";	//�־�߽��డ�� ojdbc6.jar
			/* driver ����
				jdbcOracle �� ��Ŭ�� �� Build Path �� Configure Build Path �� Libraries
				Add External JARs... �� D:\java1113\setup\ojdbc6.jar
			*/
			
			//2) ����̹� �ε�
			Class.forName(driver);
			
			//3) �����ͺ��̽� ���� ����
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("����Ŭ DB ���� ���� ����!!");
			
			//4) SQL�� �ۼ�
			/* ���1)
			String sql = "";
			sql += " INSERT INTO sungjuk(sno, uname, kor, eng, mat, addr, wdate)";
			sql += " VALUES (sungjuk_seq.nextval, '������', 99, 88, 77, 'Jeju', sysdate)";
			*/
			
			// ��� 2) StringBuilder �̿�
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO sungjuk(sno, uname, kor, eng, mat, addr, wdate)");
			sql.append(" VALUES((SELECT NVL(MAX(sno), 0)+1 FROM sungjuk)");
			sql.append(" , 'QUEEN', 55, 66, 44, 'Suwon', sysdate) ");
			
			
			//5) SQL�� ��ȯ
			//PreparedStatement pstmt = con.prepareStatement(sql);	//���1)
			PreparedStatement pstmt = con.prepareStatement(sql.toString());	//���2)
			
			//6) SQL�� ����
			int result = pstmt.executeUpdate();
			System.out.println(result);		//1  :  1�� �߰�
			
		}catch (Exception e) {
			System.out.println("����Ŭ DB ���� ����!!"+ e);
		}

	}//main() end
}//class end
