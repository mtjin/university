package People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class memberDB {
	public memberDB() {

	}

	// DB연결 메소드
	public Connection getConn() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 1. 드라이버 로딩
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/MTBS?serverTimezone=UTC&useSSL=false",
					"MTBS", "mtbs"); // 2. 드라이버 연결

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// 한사람의 회원 정보 가져오기
	public member getMemberDTO(String id) {

		member member = new member();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				member.setmID(rs.getString("ID"));
				member.setmPW(rs.getString("PW"));
				member.setmName(rs.getString("Name"));
				member.setmDOB(rs.getString("DOB"));
				member.setmAddress(rs.getString("Address"));
				member.setmPN(rs.getString("PN"));
				member.setmticket(rs.getString("ticket"));
				member.setMpoint(rs.getString("point"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	// 멤버리스트 출력
	public ArrayList getMemberList() {

		ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
		
		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from member order by name asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String mID = rs.getString("ID");
				String mPW = rs.getString("PW");
				String mName = rs.getString("Name");
				String mDOB = rs.getString("DOB");
				String mAddress = rs.getString("Address");
				String mPN = rs.getString("PN");
				String mticket = rs.getString("ticket");
				String mpoint = rs.getString("point");

				ArrayList<String> array = new ArrayList<String>();
				array.add(mID);
				array.add(mPW);
				array.add(mName);
				array.add(mDOB);
				array.add(mAddress);
				array.add(mPN);
				array.add(mticket);
				array.add(mpoint);

				data.add(array);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 회원 등록
	public boolean insertMember(member member) {
		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령

		try {
			con = getConn();
			String sql = "insert into member(ID,PW,Name,DOB,Address,PN,ticket,point) values(?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, member.getmID());
			ps.setString(2, member.getmPW());
			ps.setString(3, member.getmName());
			ps.setString(4, member.getmDOB());
			ps.setString(5, member.getmAddress());
			ps.setString(6, member.getmPN());
			ps.setString(7, member.getmticket());
			ps.setString(8, member.getMpoint());
			int r = ps.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("가입 성공");
				return true;
			} else {
				System.out.println("가입 실패");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 회원정보 수정
	public boolean updateMember(member member) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update member set ID=?, PW=?, Name=?, DOB=?, Address=?, PN=?, ticket=?, point=?"
					+ "where ID=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, member.getmID());
			ps.setString(2, member.getmPW());
			ps.setString(3, member.getmName());
			ps.setString(4, member.getmDOB());
			ps.setString(5, member.getmAddress());
			ps.setString(6, member.getmPN());
			ps.setString(7, member.getmticket());
			ps.setString(8, member.getMpoint());
			ps.setString(9, member.getmID());
			
			int r = ps.executeUpdate(); // 실행 -> 수정
			// 1~n: 성공 , 0 : 실패

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	//예약취소시 포인트, 누적구매 티켓수 수정
		public boolean updateCancelMember(member member , int recoveryPoint) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConn();
				String sql = "update member set  ticket=?, point = point + ?"
						+ "where ID=?";
				ps = con.prepareStatement(sql);

				
				ps.setString(1, String.valueOf(Integer.valueOf(member.getmticket()) - 1));
				ps.setString(2, String.valueOf(recoveryPoint));
				ps.setString(3, member.getmID());
				
				int r = ps.executeUpdate(); // 실행 -> 수정
				// 1~n: 성공 , 0 : 실패

				if (r > 0)
					return true;
				else return false;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		}
	
	// 회원 삭제
	public boolean deleteMember(String id, String pwd) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from member where ID=? and PW=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			int r = ps.executeUpdate(); // 실행 -> 삭제

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			System.out.println(e + "-> 오류발생");
		}
		return true;
	}
}
