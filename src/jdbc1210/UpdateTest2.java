package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateTest2 {

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
			//sno=24의 데이터 수정하기
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
			
			//5) SQL문 변환
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "차범근");	//1
			pstmt.setInt(2, 99);			//2
			pstmt.setInt(3, 77);			//3
			pstmt.setInt(4, 88);			//4
			pstmt.setString(5, "독일");		//5
			pstmt.setInt(6, sno);			//6
			
			//6) SQL문 실행
			int result = pstmt.executeUpdate();
			if(result==0) {
				System.out.println("행추가 실패");
			}else {
				System.out.println("행추가 성공");
			}//if end
			
		}catch (Exception e) {
			System.out.println("오라클 DB 연결 실패!!"+ e);
		}

	}//main() end
}//class end
