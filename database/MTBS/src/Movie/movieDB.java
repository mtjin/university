package Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Movie.movie;

public class movieDB {
	public movieDB() {

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

	// 한 영화 정보 가져오기
	public movie getMovieDTO(String ID) {

		movie movie = new movie();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select * from movie where ID=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ID);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				movie.setMvID(rs.getString("ID"));
				movie.setMvTheaterID(rs.getString("TheaterID"));
				movie.setMvMovieTitle(rs.getString("MovieTitle"));
				movie.setMvDirector(rs.getString("Director"));
				movie.setMvActor(rs.getString("Actor"));
				movie.setMvGrade(rs.getString("Grade"));
				movie.setMvInfo(rs.getString("Info"));
				movie.setMvAccumulateNum(rs.getString("AccumulateNum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movie;
	}

	// 영화리스트 출력
	public Vector getMovieList() {

		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {
			con = getConn();
			String sql = "select ID,TheaterID,MovieTitle,Director,Actor,Grade,Info,sum(AccumulateNum) as AccumulateNum from movie group by MovieTitle order by AccumulateNum desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String mvID = rs.getString("ID");
				String mvTheaterID = rs.getString("TheaterID");
				String mvMovieTitle = rs.getString("MovieTitle");
				String mvDirector = rs.getString("Director");
				String mvActor = rs.getString("Actor");
				String mvGrade = rs.getString("Grade");
				String mvInfo = rs.getString("Info");
				String mvAccumulateNum = rs.getString("AccumulateNum");

				Vector row = new Vector();
				row.add(mvID);
				row.add(mvTheaterID);
				row.add(mvMovieTitle);
				row.add(mvDirector);
				row.add(mvActor);
				row.add(mvGrade);
				row.add(mvInfo);
				row.add(mvAccumulateNum);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 영화 등록
	public boolean insertMovie(movie movie) {
		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령

		try {
			con = getConn();
			String sql = "insert into movie(ID,TheaterID, MovieTitle, Director, Actor, Grade, Info, AccumulateNum) values(?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, movie.getMvID());
			ps.setString(2,  movie.getMvTheaterID());
			ps.setString(3, movie.getMvMovieTitle());
			ps.setString(4, movie.getMvDirector());
			ps.setString(5, movie.getMvActor());
			ps.setString(6, movie.getMvGrade());
			ps.setString(7, movie.getMvInfo());
			ps.setString(8, movie.getMvAccumulateNum());
			int r = ps.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("영화 추가 성공");
				return true;
			} else {
				System.out.println("영화 추가 실패");
				return false;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	

	// 영화 정보 수정
	public boolean updateMovie(movie movie) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConn();
			String sql = "update movie set ID=?, theaterID=?, MovieTitle=?, Director=?, Actor=?, Grade=?, Info=?, AccumulateNum=?"
					+ "where ID=?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, movie.getMvID());
			ps.setString(2, movie.getMvTheaterID());
			ps.setString(3, movie.getMvMovieTitle());
			ps.setString(4, movie.getMvDirector());
			ps.setString(5, movie.getMvActor());
			ps.setString(6, movie.getMvGrade());
			ps.setString(7, movie.getMvInfo());
			ps.setString(8, movie.getMvAccumulateNum());
			ps.setString(9, movie.getMvID());
			
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
	public boolean deleteMovie(String id) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConn();
			String sql = "delete from movie where ID=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			//ps.setString(2, pwd);
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
			String sql = "select * from movie order by MovieTitle asc";
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
