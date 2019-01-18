package jdbc1212;

import java.sql.*;

public class PagingTest {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	//127.0.0.1 : ��PC����Ŵ
		String user = "java1113";
		String password = "1234";
		String driver = "oracle.jdbc.driver.OracleDriver";	//�־�߽��డ�� ojdbc6.jar
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("����Ŭ DB ���� ���� ����!!");
			
			//4) SQL�� �ۼ�
			//��) �й��� ������û ������ �� ������ �й������� ��ȸ�Ͻÿ�
			//		g1001 ȫ�浿 8
			//		g1005 ����ȭ 6 ..
			int start = 1;
			int end = 3;
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT rnum, hakno, uname, address, email, phone ");
			sql.append(" FROM(SELECT ROWNUM AS rnum, st.* ");
			sql.append(" 			FROM tb_student st ORDER BY hakno) ");
			sql.append(" WHERE rnum>=? AND rnum<=? ");
			sql.append(" ORDER BY rnum DESC ");
			/* t
			 	SELECT rnum, hakno, uname, address, phone, email
				FROM (SELECT ROWNUM AS rnum, hakno, uname, address, phone, email
        				FROM (SELECT hakno, uname, address, phone, email
                				FROM tb_student ORDER BY hakno
                				)aa
        					ORDER BY rnum DESC
        				)bb
				WHERE rnum>=1 AND rnum<=3;
			*/
			
			//5) SQL�� ��ȯ
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			//6) SQL�� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("�ڷ� ����");
				do {
					System.out.print(rs.getString("rnum")+" ");
					System.out.print(rs.getString("hakno")+" ");
					System.out.print(rs.getString("uname")+" ");
					System.out.print(rs.getString("address")+" ");
					System.out.print(rs.getString("phone")+" ");
					System.out.print(rs.getString("email")+" ");
					System.out.println();
				}while(rs.next());
			}else {
				System.out.println("�ڷ� ����");
			}//if end
			
		}catch (Exception e) {
			System.out.println("����Ŭ DB ���� ����!!"+ e);
		}finally {
			//�ڿ��ݳ�(��������)
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
