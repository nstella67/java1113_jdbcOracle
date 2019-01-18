package jdbc1212;

import java.sql.*;

public class JoinTest {

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
			//문) 학번별 수강신청 과목의 총 학점을 학번순으로 조회하시오
			//		g1001 홍길동 8
			//		g1005 무궁화 6 ..
			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT bb.hakno, bb.hap, st.uname "); 
			sql.append(" FROM (SELECT aa.hakno, sum(aa.ghakjum) as hap "); 
			sql.append(" 		FROM(SELECT su.hakno, su.gcode, gw.ghakjum");
			sql.append(" 				FROM tb_sugang su JOIN tb_gwamok gw ");
			sql.append(" 				ON su.gcode=gw.gcode ");
			sql.append("  		) aa ");
			sql.append(" 		GROUP BY aa.hakno ");
			sql.append(" 		) bb JOIN tb_student st ");
			sql.append(" ON bb.hakno=st.hakno ");
			sql.append(" ORDER BY bb.hakno ");
			
			
			//5) SQL문 변환
			pstmt = con.prepareStatement(sql.toString());
			
			//6) SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("자료 있음");
				do {
					System.out.print(rs.getString("hakno")+" ");
					System.out.print(rs.getInt("hap")+" ");
					System.out.print(rs.getString("uname")+" ");
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
