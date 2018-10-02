package login;

import java.sql.*;

public class LogonDBBean {
	private static LogonDBBean instance = new LogonDBBean();
	
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private LogonDBBean() {}
	
	private Connection getConnection() throws Exception{
		Connection conn = null;
		String jdbcUrl = "jdbc::mysql://localhost:3306/member";
		String dbId = "root";
		String dbPass = "thdud0917@";
		
		Class.forName("com.mysql.jdbc.Driver");
		conn =DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		return conn;
	}
	
	public void insertMember(LogonDataBean member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("insert into member values (?, ?, ?, ?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
			if(conn!=null)try {conn.close();}catch(SQLException ex) {}
		}
	}
	
	public int userCheck(String id, String passwd) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dppasswd = "";
		int x = -1;
		try {
			conn =getConnection();
			pstmt = conn.prepareStatement("select passwd from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dppasswd = rs.getString("passwd");
				if(dppasswd.equals(passwd))
					x = 1; //인증 성공
				else
					x = 0; //비밀번호 틀림
			}else
				x= -1;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();} catch(SQLException ex) {}
			if(pstmt != null)try {pstmt.close();} catch(SQLException ex){}
			if(conn != null) try {conn.close();}catch(SQLException ex) {}
		}
		return x;
		
	}
}

