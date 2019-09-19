package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Booking.book;
import Booking.bookDB;
import Movie.movie;
import Movie.movieDB;
import Payment.pay;
import Payment.payDB;
import People.member;
import People.memberDB;
import UI.movie_reservation.MyActionListener;

public class cancel_reservation extends JPanel {
	UI_Main ui;
	JTextField bookNO;
	JButton ok, re;

	public cancel_reservation(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cancel_reservation.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		bookNO = new JTextField(20);
		bookNO.setBounds(380, 355, 200, 30);
		bookNO.setOpaque(false);
		bookNO.setForeground(Color.WHITE);
		bookNO.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
		bookNO.setCaretColor(Color.BLACK);

		ok = new JButton("예약 취소");
		ok.setBackground(new Color(114, 137, 218));
		ok.setForeground(Color.WHITE);
		ok.setBounds(105, 647, 350, 60);
		ok.setBorderPainted(false);
		ok.setFocusPainted(false);

		// 취소버튼 추가
		re = new JButton("돌아가기");
		re.setBackground(new Color(114, 137, 218));
		re.setForeground(Color.WHITE);
		re.setBounds(510, 647, 350, 60);
		re.setBorderPainted(false);
		re.setFocusPainted(false);

		add(bookNO);
		add(ok);
		add(re);
		add(lblNewLabel);

		ok.addActionListener(new MyActionListener());
		re.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		boolean isCancel = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {

			case "예약 취소":
				// 포인트 사용 예외처리
				if (!bookNO.getText().isEmpty()) {
					if (isStringDouble(bookNO.getText()) == false) {
						JOptionPane.showMessageDialog(null, "예약번호는 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						try {
							bookDB bookDB = new bookDB();
							member member = ui.getmember();
							
							memberDB memberDB = new memberDB();
							payDB payDB= new payDB();
							String mID = member.getmID();
							
							book tmpBook = bookDB.getBook2(member.getmID());
							pay tmpPay = payDB.getpay2(bookNO.getText());
							int payMoney = Integer.valueOf(tmpPay.getprice());
							
							isCancel = bookDB.deleteBook(bookNO.getText(), mID);
							
							if (isCancel == true) {
								//티켓가격을 10000원인 경우는 포인트를 이용안한거이므로 포인트를 도로 채워줄 필요가없음
								if(payMoney == 10000) {
									memberDB.updateCancelMember(member, 0);
								}
								//원래가격 10000 - 구매티켓 가격 = 포인트 , 이 포인트를 복구시켜준다.
								else if(payMoney < 10000) {
									memberDB.updateCancelMember(member, 10000 - payMoney);
								}
								JOptionPane.showMessageDialog(null, "예매취소가 완료되었습니다!", "메세지",
										JOptionPane.INFORMATION_MESSAGE);
							} else if (isCancel == false) {
								JOptionPane.showMessageDialog(null, "예매취소를 실패 했습니다.(예약하지 않은 영화입니다!)", "메세지",
										JOptionPane.WARNING_MESSAGE);
							}
						} catch (Exception e10) {
							JOptionPane.showMessageDialog(null, "예매취소을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "예매취소를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
				}
			case "돌아가기":
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