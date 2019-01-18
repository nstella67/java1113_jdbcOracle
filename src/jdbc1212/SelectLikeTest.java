package jdbc1212;

import java.sql.*;

public class SelectLikeTest {

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
			//��) �̸��� '��' ���ڰ� �ִ� ����� �̸������� ��ȸ�Ͻÿ�
			String col = "uname";
			String keyword = "��";
			//WHERE uname like '%��%'
			String where = " WHERE " + col + " LIKE '%" + keyword + "%' ";
			
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT sno, uname, kor, eng, mat, aver, addr, wdate ");
			sql.append(" FROM sungjuk ");
			sql.append(where);
			sql.append(" ORDER BY uname DESC ");
			
			//5) SQL�� ��ȯ
			pstmt = con.prepareStatement(sql.toString());
			
			//6) SQL�� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("�ڷ� ����");
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
