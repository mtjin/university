package Cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;



public class cinemaDB {
	public cinemaDB() {
		
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

	// 영화관 정보 가져오기
	public cinema getCinemaDTO(String NAME) {

		cinema cinema = new cinema();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from cinema where NAME=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, NAME);

			rs = ps.executeQuery();

			if (rs.next()) {
				cinema.setcNAME(rs.getString("NAME"));
				cinema.setcAddress(rs.getString("Address"));
				cinema.setcPhoneNum(rs.getString("PhoneNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cinema;
	}

	// 영화관 리스트 출력
	public Vector getCinemaList() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from cinema order by NAME asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String cName = rs.getString("NAME");
				String cAddress = rs.getString("Address");
				String cPhoneNum = rs.getString("PhoneNum");

				Vector row = new Vector();
				row.add(cName);
				row.add(cAddress);
				row.add(cPhoneNum);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 영화관 등록
	public boolean insertCinema(cinema cinema) {
		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령

		try {
			con = getConn();
			String sql = "insert into cinema(NAME, Address, PhoneNum) values(?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, cinema.getcNAME());
			ps.setString(2, cinema.getcAddress());
			ps.setString(3, cinema.getcPhoneNum());
			
			int r = ps.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("영화관 추가 성공");
				return true;
			} else {
				System.out.println("영화관 추가 실패");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 영화관 정보 수정
	public boolean updateCinema(cinema cinema) {
		System.out.println("dto=" + cinema.toString());
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update cinema set NAME=?, Address=?, PhoneNum=?"
					+ "where NAME=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, cinema.getcNAME());
			ps.setString(2, cinema.getcAddress());
			ps.setString(3, cinema.getcPhoneNum());
			ps.setString(4, cinema.getcNAME());
			

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

	// 영화관 삭제
	public boolean deleteCinema(String name) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from cinema where Name=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			int r = ps.executeUpdate(); // 실행 -> 삭제

			if (r > 0)
				return true;
			else return false;

		} catch (Exception e) {
			System.out.println(e + "-> 오류발생");
		}
		return true;
	}

	/** DB데이터 다시 불러오기 */
	public void userSelectAll(DefaultTableModel model) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select * from cinema order by NAME asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel에 있는 데이터 지우기
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10) };

				model.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
