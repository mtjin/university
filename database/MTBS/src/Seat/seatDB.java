package Seat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Movie.movie;

public class seatDB {
   public seatDB() {

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

   // 한 좌석 정보 가져오기
   public Seat getSeatDTO(String ID) {

      Seat seat = new Seat();

      Connection con = null; // 연결
      PreparedStatement ps = null; // 명령
      ResultSet rs = null; // 결과

      try {
         con = getConn();
         String sql = "select * from seat where sSeatNum=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         
         rs = ps.executeQuery();
         if (rs.next()) {
            seat.setsSeatNum(rs.getString("sSeatNum"));
            seat.setsTheaterID(rs.getString("sTheaterID"));
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return seat;
   }

   // 좌석 리스트 출력
   public Vector getSeatList(String movieName) {

      Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가

      Connection con = null; // 연결
      PreparedStatement ps = null; // 명령
      ResultSet rs = null; // 결과

      try {
         con = getConn();
         String sql = "select * from seat where sTheaterID in (select TheaterID from theater where MovieTitle=?)";
         ps = con.prepareStatement(sql);
         ps.setString(1, movieName);
         rs = ps.executeQuery();

         while (rs.next()) {
            String sSeatNum = rs.getString("sSeatNum");
            String sTheaterID = rs.getString("sTheaterID");
           

            Vector row = new Vector();
            row.add(sSeatNum);
            row.add(sTheaterID);
           
            data.add(row);
         } // while
      } catch (Exception e) {
         e.printStackTrace();
      }
      return data;
   }

   // 좌석 등록
   public boolean insertSeat(Seat seat, int snum) {
      Connection con = null; // 연결
      PreparedStatement ps = null; // 명령

      try {
         con = getConn();
         String sql = "insert into seat(sSeatNum, sTheaterID) values(?,?)";

         int r = 0;
         for(int i = 0; i < snum; i++) {
             ps = con.prepareStatement(sql);
             ps.setString(1, String.valueOf(seat.getsTheaterID()+(i+1)));
             ps.setString(2, seat.getsTheaterID());
            
             r = ps.executeUpdate(); // 실행 -> 저장
             
         }

         if (r > 0) {
             System.out.println("좌석 추가 성공");
             return true;
          } else {
             System.out.println("좌석 추가 실패");
             return false;
          }
         
      
      } catch (Exception e) {
         e.printStackTrace();
      }

      return false;
   }
}
