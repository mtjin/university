package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main_Menu extends JPanel {
	JButton movie_search, movie_reserv, logout, memberInfo;
	UI_Main ui;

	public Main_Menu(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/main_menu.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

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

		// 회원탈퇴 버튼 추가
		memberInfo = new JButton("회원관리");
		memberInfo.setContentAreaFilled(false);
		memberInfo.setFocusPainted(false);
		memberInfo.setForeground(Color.WHITE);
		memberInfo.setBounds(770, 23, 200, 55);

		add(movie_search);
		add(movie_reserv);
		add(logout);
		add(memberInfo);
		add(lblNewLabel);
		movie_search.addActionListener(new MyActionListener());
		movie_reserv.addActionListener(new MyActionListener());
		logout.addActionListener(new MyActionListener());
		memberInfo.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
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
			}
		}
	}
}
