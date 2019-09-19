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

import Booking.book;
import Booking.bookDB;
import Payment.pay;
import Payment.payDB;

public class ticket_issue extends JPanel {
	JButton movie_manage, cinema_manage, VIP_manage, ticket_manage, re;
	UI_Main ui;
	JLabel sID;
	
	JLabel[] row;
	JLabel[] row2;
	JLabel[] row3;
	JButton[] approve;

	public ticket_issue(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/ticket_issue.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		
		bookDB bookDB = new bookDB();
		payDB payDB = new payDB();
		
		ArrayList bookList = bookDB.getbookList();
		ArrayList payList = payDB.getpayList();
		ArrayList<ArrayList> array = new ArrayList<ArrayList>();
		for(int i = 0; i < payList.size(); i++) {
			ArrayList<String> temparray = new ArrayList<String>();
			if(((String)((ArrayList)payList.get(i)).get(3)).equals("2")) {
				temparray.add((String)((ArrayList)bookList.get(i)).get(1));
				temparray.add((String)((ArrayList)bookList.get(i)).get(0));
				temparray.add((String)((ArrayList)payList.get(i)).get(2));
				array.add(temparray);
			}
		}

		System.out.println(array);
		
		
		
		String[][] a = new String[array.size()][3];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = new String();
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = (String) array.get(i).get(j);
			}
		}

		// row
		row = new JLabel[array.size()];
		row2 = new JLabel[array.size()];
		row3 = new JLabel[array.size()];
		approve = new JButton[array.size()];
		for (int i = 0; i < a.length; i++) {
			int j = 0;
			row[i] = new JLabel(a[i][j]);
			row[i].setBounds(110, 210 + (i * 40), 470, 55);
			row[i].setForeground(Color.WHITE);

			row2[i] = new JLabel(a[i][j + 1]);
			row2[i].setBounds(260, 210 + (i * 40), 470, 55);
			row2[i].setForeground(Color.WHITE);

			row3[i] = new JLabel(a[i][j + 2]);
			row3[i].setBounds(440, 210 + (i * 40), 470, 55);
			row3[i].setForeground(Color.WHITE);
			
			approve[i] = new JButton(a[i][j + 1] + "번 발행");
			approve[i].setContentAreaFilled(false);
			approve[i].setFocusPainted(false);
			approve[i].setForeground(Color.WHITE);
			approve[i].setBounds(690, 223 + (i * 36), 100, 30);
			approve[i].addActionListener(new MyActionListener() {
				public void actionPerformed(ActionEvent e) {
					book book = new book();
					pay pay = new pay();
					pay = payDB.getpay(e.getActionCommand().substring(0, 1));
					pay.setpayMethod("1");
					boolean torf = payDB.updatepay(pay);
					if(torf)
						JOptionPane.showMessageDialog(null, "완료 되었습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "실패 하였습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					ui.update_UI("Main_Menu_admin");
				}
			});
			add(row[i]);
			add(row2[i]);
			add(row3[i]);
			add(approve[i]);
		}

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
		
		// 돌아가기 버튼 추가
		re = new JButton("돌아가기");
		re.setBackground(new Color(114, 137, 218));
		re.setForeground(Color.WHITE);
		re.setBounds(307, 647, 350, 60);
		re.setBorderPainted(false);
		re.setFocusPainted(false);

		add(movie_manage);
		add(cinema_manage);
		add(VIP_manage);
		add(ticket_manage);
		add(re);
		add(lblNewLabel);
		movie_manage.addActionListener(new MyActionListener());
		cinema_manage.addActionListener(new MyActionListener());
		VIP_manage.addActionListener(new MyActionListener());
		ticket_manage.addActionListener(new MyActionListener());
		re.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
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
			case "돌아가기":
				ui.update_UI("Main_Menu_admin");
				break;
			}
		}
	}

}
