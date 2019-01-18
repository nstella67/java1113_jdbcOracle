package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateTest3 {

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
			//��) �ּҰ� ���� 'Seoul' ������� ���������� bonus�� �����ְ�
			//		���� 'Seoul' �ּҸ��� �ѱ� '����'�� �����Ͻÿ�
			String address = "Seoul";
			int bonus = 5;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE sungjuk ");
			sql.append(" SET addr = ? ");
			sql.append(" , kor=(kor+?) ");
			sql.append(" WHERE addr = ? ");
			
			//5) SQL�� ��ȯ
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "����");
			pstmt.setInt(2, bonus);
			pstmt.setString(3, "Seoul");
			
			//6) SQL�� ����
			int result = pstmt.executeUpdate();
			if(result==0) {
				System.out.println("���߰� ����");
			}else {
				System.out.println("���߰� ����");
			}//if end
			System.out.println(result);
			
		}catch (Exception e) {
			System.out.println("����Ŭ DB ���� ����!!"+ e);
		}

	}//main() end
}//class end
