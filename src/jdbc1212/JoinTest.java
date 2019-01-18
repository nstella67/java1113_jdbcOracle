package jdbc1212;

import java.sql.*;

public class JoinTest {

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
			
			
			//5) SQL�� ��ȯ
			pstmt = con.prepareStatement(sql.toString());
			
			//6) SQL�� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("�ڷ� ����");
				do {
					System.out.print(rs.getString("hakno")+" ");
					System.out.print(rs.getInt("hap")+" ");
					System.out.print(rs.getString("uname")+" ");
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
