package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteTest {

	public static void main(String[] args) {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";	//127.0.0.1 : 내PC가리킴
			String user = "java1113";
			String password = "1234";
			String driver = "oracle.jdbc.driver.OracleDriver";	//있어야실행가능 ojdbc6.jar
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 서버 연결 성공!!");
			
			//4) SQL문 작성
			//sno>=25 삭제하기
			int sno = 25;
			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM sungjuk ");
			sql.append(" WHERE sno>=? ");

			//5) SQL문 변환
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, 25);
			
			//6) SQL문 실행
			int result = pstmt.executeUpdate();
			if(result==0) {
				System.out.println("행삭제 실패");
			}else {
				System.out.println("행삭제 성공");
			}//if end
			System.out.println("행삭제 : " + result +"줄");
			
		}catch (Exception e) {
			System.out.println("오라클 DB 연결 실패!!"+ e);
		}

	}

}
