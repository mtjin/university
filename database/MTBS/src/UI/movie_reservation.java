package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Booking.book;
import Booking.bookDB;
import Cinema.cinemaDB;
import Movie.movieDB;
import Payment.pay;
import Payment.payDB;
import People.member;
import People.memberDB;
import Seat.seatDB;
import Theater.TheaterDB;
import Theater.theater;

public class movie_reservation extends JPanel {
	JTextField usePoint;
	JButton movie_search, movie_reserv, logout, memberInfo;
	UI_Main ui;
	JButton ok, cancel, cancel_reservation, check_reservation;
	JComboBox movieCombo, seatCombo, cinemaCombo, dateCombo, timeCombo, typeCombo;
	member member;

	public movie_reservation(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 맴버 가져오기 //
		String mid = ui.getmember().getmID();
		memberDB memberDB = new memberDB();
		member = memberDB.getMemberDTO(mid);

		// 콤보 박스 ===============================================
		// 영화 선택 콤보 박스 생성 및 추가
		movieDB movieDB = new movieDB();
		Vector v = movieDB.getMovieList();

		// 콤보 박스 내 선택 가능 메뉴 선언 (영화)
		String[] temp = new String[v.size()];
		ArrayList<String> list = new ArrayList<String>();

		// 영화 중복 제거
		for (int i = 0; i < v.size(); i++) {
			int j = 0;
			for (j = 0; j < i; j++) {
				if (list.get(j).equals((String) ((Vector) v.get(i)).get(2)))
					break;
			}
			if (j == i) {
				list.add((String) ((Vector) v.get(i)).get(2));
			}
		}

		String[] movie = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			movie[i] = list.get(i);
		}

		movieCombo = new JComboBox();
		movieCombo.setBounds(220, 210, 200, 30);
		movieCombo.setOpaque(false);
		add(movieCombo);
		movieCombo.setModel(new DefaultComboBoxModel(movie));

		// 콤보 박스 년도 값 변경
		movieCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ev) {
				seatDB seatDB = new seatDB();

				String[] seat = null;
				Object movieName = ev.getItem();

				Vector v3 = seatDB.getSeatList(movieName.toString());
				// TODO Auto-generated method stub
				if (v3 != null) {
					seat = new String[v3.size()];

					for (int i = 0; i < v3.size(); i++) {
						seat[i] = ((String) ((Vector) v3.get(i)).get(0));
					}
				}
				System.out.println(v3);

				seatCombo.setModel(new DefaultComboBoxModel(seat));
				seatCombo.revalidate();
				seatCombo.repaint();
			}
		});
		// ===========================================================
		// 영화관 선택 콤보 박스 생성 및 추가
		cinemaDB cinemaDB = new cinemaDB();
		Vector v2 = cinemaDB.getCinemaList();

		String[] cinema = new String[v2.size()];

		for (int i = 0; i < v2.size(); i++) {
			cinema[i] = (String) ((Vector) v2.get(i)).get(0);
		}

		cinemaCombo = new JComboBox();
		cinemaCombo.setBounds(220, 280, 200, 30);
		cinemaCombo.setOpaque(false);
		add(cinemaCombo);
		cinemaCombo.setModel(new DefaultComboBoxModel(cinema));
		// ===========================================================
		// 좌석 선택 콤보 박스 생성 및 추가
		seatDB seatDB = new seatDB();
		String[] seat = null;
		Object movieName = movieCombo.getSelectedItem();

		Vector v3 = seatDB.getSeatList(movieName.toString());

		if (v3 != null) {
			seat = new String[v3.size()];

			for (int i = 0; i < v3.size(); i++) {
				seat[i] = ((String) ((Vector) v3.get(i)).get(0));
			}
		}

		// 영화 선택 콤보 박스 생성 및 추가
		seatCombo = new JComboBox();
		seatCombo.setBounds(220, 355, 200, 30);
		seatCombo.setOpaque(false);
		add(seatCombo);
		seatCombo.setModel(new DefaultComboBoxModel(seat));
		
		
		String[] time = new String[1];
		seatCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ev) {
				//고른좌석번호를 불러옴
				Object seatThNum = seatCombo.getSelectedItem();
				String seatNum_str = seatThNum.toString();
				
				//고른좌석번호의 맨앞자리를 파싱해서 상영관 아이디를 알아냄
				String seatTheaterID = seatNum_str.substring(0, 1);
				TheaterDB tmpTheaterDB = new TheaterDB();
				theater tmpTheater = tmpTheaterDB.getTheaterDTO(seatTheaterID);
				
				time[0] = tmpTheater.gettStartTime();

				timeCombo.setModel(new DefaultComboBoxModel(time));
				timeCombo.revalidate();
				timeCombo.repaint();
			}
		});
		// ===========================================================
		// 날짜 선택 콤보 박스 생성 및 추가
		// 콤보 박스 년도 값 가져오기
		String[] date = new String[30];

		for (int i = 0; i < 30; i++) {
			date[i] = String.valueOf(i + 1);
		}

		// 영화 선택 콤보 박스 생성 및 추가
		dateCombo = new JComboBox();
		dateCombo.setBounds(220, 430, 200, 30);
		dateCombo.setOpaque(false);
		add(dateCombo);
		dateCombo.setModel(new DefaultComboBoxModel(date));
		// ===========================================================
		// 시간 선택 콤보 박스 생성 및 추가
		// 콤보 박스 년도 값 가져오기
		
		
		/*//고른좌석번호를 불러옴
		Object seatThNum = seatCombo.getSelectedItem();
		String seatNum_str = seatThNum.toString();
		
		//고른좌석번호의 맨앞자리를 파싱해서 상영관 아이디를 알아냄
		String seatTheaterID = seatNum_str.substring(0, 1);
		TheaterDB tmpTheaterDB = new TheaterDB();
		theater tmpTheater = tmpTheaterDB.getTheaterDTO(seatTheaterID);
		
		time[0] = tmpTheater.gettStartTime();*/
		

		// 영화 선택 콤보 박스 생성 및 추가
		timeCombo = new JComboBox();
		timeCombo.setBounds(220, 505, 200, 30);
		timeCombo.setOpaque(false);
		add(timeCombo);
		timeCombo.setModel(new DefaultComboBoxModel(seat));
		
		
	
		
		// ===========================================================
		// 결제 유형 선택 콤보 박스 생성 및 추가
		// 콤보 박스 년도 값 가져오기
		String[] type = new String[2];

		type[0] = "인터넷 결제";
		type[1] = "현장 결제";

		// 영화 선택 콤보 박스 생성 및 추가
		typeCombo = new JComboBox();
		typeCombo.setBounds(580, 210, 200, 30);
		typeCombo.setOpaque(false);
		add(typeCombo);
		typeCombo.setModel(new DefaultComboBoxModel(type));
		// 콤보 박스 ====================================================
		// 포인트 현황
		JLabel Point = new JLabel(member.getMpoint());
		Point.setBounds(605, 280, 200, 30);
		Point.setForeground(Color.WHITE);
		add(Point);

		// 포인트 사용 필드
		usePoint = new JTextField(20);
		usePoint.setBounds(605, 355, 200, 30);
		usePoint.setOpaque(false);
		usePoint.setForeground(Color.WHITE);
		usePoint.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		usePoint.setCaretColor(Color.white);
		add(usePoint);

		// 영화 검색 버튼 추가
		movie_search = new JButton("영화 검색");
		movie_search.setContentAreaFilled(false);
		movie_search.setFocusPainted(false);
		movie_search.setForeground(Color.WHITE);
		movie_search.setBounds(20, 23, 200, 55);

		// 영화 예약 버튼 추가
		movie_reserv = new JButton("영화 예약");
		movie_reserv.setContentAreaFilled(false);
		movie_reserv.setFocusPainted(false);
		movie_reserv.setForeground(Color.WHITE);
		movie_reserv.setBounds(270, 23, 200, 55);

		// 로그아웃 버튼 추가
		logout = new JButton("로그아웃");
		logout.setContentAreaFilled(false);
		logout.setFocusPainted(false);
		logout.setForeground(Color.WHITE);
		logout.setBounds(520, 23, 200, 55);

		// 회원관리 버튼 추가
		memberInfo = new JButton("회원관리");
		memberInfo.setContentAreaFilled(false);
		memberInfo.setFocusPainted(false);
		memberInfo.setForeground(Color.WHITE);
		memberInfo.setBounds(770, 23, 200, 55);

		// 예약버튼 추가
		ok = new JButton("예약 및 결제");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(105, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		// 취소버튼 추가
		cancel = new JButton("취소");
		cancel.setBackground(new Color(114, 137, 218));
		cancel.setForeground(Color.WHITE);
		cancel.setBounds(510, 647, 350, 60);
		cancel.setBorderPainted(false);
		cancel.setFocusPainted(false);

		// 영화취소버튼 추가
		cancel_reservation = new JButton("예약취소");
		cancel_reservation.setBackground(new Color(114, 137, 218));
		cancel_reservation.setForeground(Color.WHITE);
		cancel_reservation.setBounds(510, 495, 150, 40);
		cancel_reservation.setBorderPainted(false);
		cancel_reservation.setFocusPainted(false);

		// 영화예약확인 버튼 추가
		check_reservation = new JButton("예약확인");
		check_reservation.setBackground(new Color(114, 137, 218));
		check_reservation.setForeground(Color.WHITE);
		check_reservation.setBounds(510, 425, 150, 40);
		check_reservation.setBorderPainted(false);
		check_reservation.setFocusPainted(false);

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(memberInfo);
		add(ok);
		add(cancel);
		add(cancel_reservation);
		add(check_reservation);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		memberInfo.addActionListener(new MyActionListener());
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
		cancel_reservation.addActionListener(new MyActionListener());
		check_reservation.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "예약확인":
				ui.update_UI("check_reservation");
				break;
			case "예약취소":
				ui.update_UI("cancel_reservation");
				break;
			case "영화 검색":
				ui.update_UI("movie_search");
				break;
			case "영화 예약":
				ui.update_UI("movie_reservation");
				break;
			case "로그아웃":
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
				ui.update_UI("Login");
				break;
			case "회원관리":
				ui.update_UI("member_Main");
			case "예약 및 결제":
				// 포인트 사용 예외처리
				if (!usePoint.getText().isEmpty()) {
					if (isStringDouble(usePoint.getText()) == false) {
						JOptionPane.showMessageDialog(null, "생년월일은 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (Integer.parseInt(usePoint.getText()) < 1000
								|| Integer.parseInt(member.getMpoint()) < 1000) {
							JOptionPane.showMessageDialog(null, "포인트는 1000점 이상 사용 가능합니다.", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					bookDB bookDB = new bookDB();
					payDB payDB = new payDB();
					book book = new book();
					pay pay = new pay();

					// 예약
					ArrayList array = bookDB.getbookList();
					book.setbookNo(String.valueOf(array.size() + 1));
					book.setmID(member.getmID());
					book.setTheaterID(((String) seatCombo.getSelectedItem()).substring(0, 1));
					boolean torf1 = bookDB.insertbook(book);

					// 결제
					ArrayList array2 = payDB.getpayList();
					pay.setpayNo(String.valueOf(array2.size() + 1));
					pay.setbookNo(String.valueOf(array.size() + 1));
					member.setmticket(String.valueOf(Integer.parseInt(member.getmticket()) + 1)); // 구매 횟수 +1
					memberDB memberDB = new memberDB();
					memberDB.updateMember(member);
					if (!usePoint.getText().isEmpty()) {
						pay.setprice(String.valueOf(10000 - Integer.parseInt(usePoint.getText())));
						member.setMpoint(String
								.valueOf(Integer.parseInt(member.getMpoint()) - Integer.parseInt(usePoint.getText()))); // 포인트
																														// 차감
						boolean torf3 = memberDB.updateMember(member);

						if (torf3)
							JOptionPane.showMessageDialog(null, "포인트가 차감되었습니다.", "메세지",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "포인트 차감을 실패하였습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					} else {
						pay.setprice("10000");
					}

					memberDB.updateMember(member);
					// 인터넷 결제 일 경우(바로 결제 및 예매 완료)
					if (typeCombo.getSelectedItem().equals("인터넷 결제")) {

						pay.setpayMethod("1");
						boolean torf2 = payDB.insertpay(pay);

						if (torf1 && torf2)
							JOptionPane.showMessageDialog(null, "인터넷 결제가 완료되었습니다!", "메세지",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "인터넷 결제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					}
					// 현장 결제 일 경우(관리자 승인)
					else {

						pay.setpayMethod("2");
						boolean torf2 = payDB.insertpay(pay);

						if (torf1 && torf2)
							JOptionPane.showMessageDialog(null, "현장 결제가 완료되었습니다!", "메세지",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "현장 결제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "예매 또는 결제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.setmember(member);
				ui.update_UI("Main_Menu");
				break;
			case "취소":
				ui.update_UI("Main_Menu");
			}
		}
	}

	public boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
