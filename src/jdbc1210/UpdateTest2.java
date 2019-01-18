package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateTest2 {

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
			//sno=24�� ������ �����ϱ�
			int sno=27;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE sungjuk ");
			sql.append(" SET uname = ? ");
			sql.append(" , kor = ? ");
			sql.append(" , eng = ? ");
			sql.append(" , mat = ? ");
			sql.append(" , addr = ? ");
			sql.append(" , wdate = sysdate ");
			sql.append(" WHERE sno = ? ");
			
			//5) SQL�� ��ȯ
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "������");	//1
			pstmt.setInt(2, 99);			//2
			pstmt.setInt(3, 77);			//3
			pstmt.setInt(4, 88);			//4
			pstmt.setString(5, "����");		//5
			pstmt.setInt(6, sno);			//6
			
			//6) SQL�� ����
			int result = pstmt.executeUpdate();
			if(result==0) {
				System.out.println("���߰� ����");
			}else {
				System.out.println("���߰� ����");
			}//if end
			
		}catch (Exception e) {
			System.out.println("����Ŭ DB ���� ����!!"+ e);
		}

	}//main() end
}//class end
