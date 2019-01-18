package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteTest {

	public static void main(String[] args) {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	//127.0.0.1 : ��PC����Ŵ
			String user = "java1113";
			String password = "1234";
			String driver = "oracle.jdbc.driver.OracleDriver";	//�־�߽��డ�� ojdbc6.jar
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("����Ŭ DB ���� ���� ����!!");
			
			//4) SQL�� �ۼ�
			//sno>=25 �����ϱ�
			int sno = 25;
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM sungjuk ");
			sql.append(" WHERE sno>=? ");

			//5) SQL�� ��ȯ
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, 25);
			
			//6) SQL�� ����
			int result = pstmt.executeUpdate();
			if(result==0) {
				System.out.println("����� ����");
			}else {
				System.out.println("����� ����");
			}//if end
			System.out.println("����� : " + result +"��");
			
		}catch (Exception e) {
			System.out.println("����Ŭ DB ���� ����!!"+ e);
		}

	}

}
