package jdbc1212;

import java.sql.*;

public class SelectLikeTest {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	//127.0.0.1 : 내PC가리킴
		String user = "java1113";
		String password = "1234";
		String driver = "oracle.jdbc.driver.OracleDriver";	//있어야실행가능 ojdbc6.jar
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 DB 서버 연결 성공!!");
			
			//4) SQL문 작성
			//문) 이름에 '나' 글자가 있는 명단을 이름순으로 조회하시오
			String col = "uname";
			String keyword = "나";
			//WHERE uname like '%나%'
			String where = " WHERE " + col + " LIKE '%" + keyword + "%' ";
			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT sno, uname, kor, eng, mat, aver, addr, wdate ");
			sql.append(" FROM sungjuk ");
			sql.append(where);
			sql.append(" ORDER BY uname DESC ");
			
			//5) SQL문 변환
			pstmt = con.prepareStatement(sql.toString());
			
			//6) SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("자료 있음");
				do {
					System.out.print(rs.getInt("sno")+" ");
					System.out.print(rs.getString("uname")+" ");
					System.out.print(rs.getInt("kor")+" ");
					System.out.print(rs.getInt("eng")+" ");
					System.out.print(rs.getInt("mat")+" ");
					System.out.print(rs.getInt("aver")+" ");
					System.out.print(rs.getString("addr")+" ");
					System.out.print(rs.getString("wdate")+" ");
					System.out.println();
				}while(rs.next());
			}else {
				System.out.println("자료 없음");
			}//if end
			
		}catch (Exception e) {
			System.out.println("오라클 DB 연결 실패!!"+ e);
		}finally {
			//자원반납(순서주의)
			try {
				if(rs!=null) {rs.close();}
			}catch (Exception e) {}
			
			try {
				if(pstmt!=null) {pstmt.close();}
			}catch (Exception e) {}
			
			try {
				if(con!=null) {con.close();}
			}catch (Exception e) {}
			
		}

	}//main() end
}//class end
