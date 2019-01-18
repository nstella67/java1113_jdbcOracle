package jdbc1210;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateTest3 {

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
			//문) 주소가 영문 'Seoul' 대상으로 국어점수에 bonus를 더해주고
			//		영문 'Seoul' 주소명을 한글 '서울'로 수정하시오
			String address = "Seoul";
			int bonus = 5;
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE sungjuk ");
			sql.append(" SET addr = ? ");
			sql.append(" , kor=(kor+?) ");
			sql.append(" WHERE addr = ? ");
			
			//5) SQL문 변환
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "서울");
			pstmt.setInt(2, bonus);
			pstmt.setString(3, "Seoul");
			
			//6) SQL문 실행
			int result = pstmt.executeUpdate();
			if(result==0) {
				System.out.println("행추가 실패");
			}else {
				System.out.println("행추가 성공");
			}//if end
			System.out.println(result);
			
		}catch (Exception e) {
			System.out.println("오라클 DB 연결 실패!!"+ e);
		}

	}//main() end
}//class end
