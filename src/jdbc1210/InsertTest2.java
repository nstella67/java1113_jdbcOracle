package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTest2 {

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
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO sungjuk(sno, uname, kor, eng, mat, addr, wdate)");
			sql.append(" VALUES((SELECT NVL(MAX(sno), 0)+1 FROM sungjuk), ?, ?, ?, ?, ?, sysdate)");
			
			
			//5) SQL문 변환
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			// ? → 순서와 자료형에 주의
			pstmt.setString(1, "박항서");	//1 → 첫번째?, uname
			pstmt.setInt(2, 100);				//2 → 두번째?, kor
			pstmt.setInt(3, 100);				//3 → 세번째?, eng
			pstmt.setInt(4, 100);				//4 → 네번째?, mat
			pstmt.setString(5, "베트남");	//5 → 다섯번째?, addr	
			
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
