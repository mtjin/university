package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main_Menu_admin extends JPanel {
	JButton movie_manage, cinema_manage, VIP_manage, ticket_manage;
	UI_Main ui;

	public Main_Menu_admin(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/main_menu.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화 관리 버튼 추가
		movie_manage = new JButton("영화 관리");
		movie_manage.setContentAreaFilled(false);
		movie_manage.setFocusPainted(false);
		movie_manage.setForeground(Color.WHITE);
		movie_manage.setBounds(20, 23, 200, 55);
		
		// 영화관 관리 버튼 추가
		cinema_manage = new JButton("영화관 관리");
		cinema_manage.setContentAreaFilled(false);
		cinema_manage.setFocusPainted(false);
		cinema_manage.setForeground(Color.WHITE);
		cinema_manage.setBounds(270, 23, 200, 55);

		// VIP고객 관리 버튼 추가
		VIP_manage = new JButton("VIP고객 관리");
		VIP_manage.setContentAreaFilled(false);
		VIP_manage.setFocusPainted(false);
		VIP_manage.setForeground(Color.WHITE);
		VIP_manage.setBounds(520, 23, 200, 55);
		
		// 영화 티켓 발행 버튼 추가
		ticket_manage = new JButton("영화 티켓 발행");
		ticket_manage.setContentAreaFilled(false);
		ticket_manage.setFocusPainted(false);
		ticket_manage.setForeground(Color.WHITE);
		ticket_manage.setBounds(770, 23, 200, 55);

		add(movie_manage);
		add(cinema_manage);
		add(VIP_manage);
		add(ticket_manage);
		add(lblNewLabel);
		movie_manage.addActionListener(new MyActionListener());
		cinema_manage.addActionListener(new MyActionListener());
		VIP_manage.addActionListener(new MyActionListener());
		ticket_manage.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "영화 관리":
				ui.update_UI("movie_manage");
				break;
			case "영화관 관리":
				ui.update_UI("cinema_manage");
				break;
			case "VIP고객 관리":
				ui.update_UI("VIP_manage");
				break;
			case "영화 티켓 발행":
				ui.update_UI("ticket_issue");
				break;
			}
		}
	}
}
