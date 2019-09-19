package Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Booking.book;
import People.member;

public class payDB {
   public payDB(){
      
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
   
   // 한 결제 정보 가져오기
   public pay getpay(String string) {

      pay pay = new pay();

      Connection con = null; // 연결
      PreparedStatement ps = null; // 명령
      ResultSet rs = null; // 결과

      try {
         con = getConn();
         String sql = "select * from pay where payNo=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, string);

         rs = ps.executeQuery();

         if (rs.next()) {
            pay.setpayNo(rs.getString("payNo"));
            pay.setbookNo(rs.getString("bookNo"));
            pay.setprice(rs.getString("price"));
            pay.setpayMethod(rs.getString("payMethod"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return pay;
   }
   
// 한 결제 정보 가져오기
   public pay getpay2(String bookNO) {

      pay pay = new pay();

      Connection con = null; // 연결
      PreparedStatement ps = null; // 명령
      ResultSet rs = null; // 결과

      try {
         con = getConn();
         String sql = "select * from pay where bookNO=?";
         ps = con.prepareStatement(sql);
         ps.setString(1, bookNO);

         rs = ps.executeQuery();

         if (rs.next()) {
            pay.setpayNo(rs.getString("payNo"));
            pay.setbookNo(rs.getString("bookNo"));
            pay.setprice(rs.getString("price"));
            pay.setpayMethod(rs.getString("payMethod"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return pay;
   }
   
	// 결제 등록
	public boolean insertpay(pay pay) {
		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령

		try {
			con = getConn();
			String sql = "insert into pay(payNo,bookNo,price,payMethod) values(?,?,?,?)";

			ps = con.prepareStatement(sql);
			ps.setString(1, pay.getpayNo());
			ps.setString(2, pay.getbookNo());
			ps.setString(3, pay.getprice());
			ps.setString(4, pay.getpayMethod());
			int r = ps.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("성공");
				return true;
			} else {
				System.out.println("실패");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	// 결제정보 수정
		public boolean updatepay(pay pay) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = getConn();
				String sql = "update pay set payNo=?, bookNo=?, price=?, payMethod=?"
						+ "where payNo=?";
				ps = con.prepareStatement(sql);

				ps.setString(1, pay.getpayNo());
				ps.setString(2, pay.getbookNo());
				ps.setString(3, pay.getprice());
				ps.setString(4, pay.getpayMethod());
				ps.setString(5, pay.getpayNo());
				
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
	
	// 리스트 출력
		public ArrayList getpayList() {

			ArrayList<ArrayList> data = new ArrayList<ArrayList>(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가
			
			Connection con = null; // 연결
			PreparedStatement ps = null; // 명령
			ResultSet rs = null; // 결과

			try {
				con = getConn();
				String sql = "select * from pay";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					   String payNo = rs.getString("payNo");
					   String bookNo = rs.getString("bookNo");
					   String price = rs.getString("price");
					   String payMethod = rs.getString("payMethod");

					ArrayList<String> array = new ArrayList<String>();
					array.add(payNo);
					array.add(bookNo);
					array.add(price);
					array.add(payMethod);

					data.add(array);
				} // while
			} catch (Exception e) {
				e.printStackTrace();
			}
			return data;
		}
}