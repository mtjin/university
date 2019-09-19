package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Movie.movieDB;

public class movie_delete extends JPanel {
	JTextField mvID;
	UI_Main ui;
	JButton ok, cancel;

	public movie_delete(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_delete.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화 아이디 필드
		mvID = new JTextField(20);
		mvID.setBounds(455, 325, 470, 55);
		mvID.setOpaque(false);
		mvID.setForeground(Color.WHITE);
		mvID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvID.setCaretColor(Color.white);
		

		// 삭제버튼 추가
		ok = new JButton("삭제");
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

		add(mvID);
		add(cancel);
		add(ok);
		add(lblNewLabel);
		mvID.addActionListener(new MyActionListener());
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "삭제":
				// 영화 아이디 예외처리
				if (mvID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화 아이디를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvID.getText()) == false) {
						JOptionPane.showMessageDialog(null, "영화 아이디는 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
				
				try {
					movieDB movieDB = new movieDB();
					boolean torf = movieDB.deleteMovie(mvID.getText());

					if(torf)
						JOptionPane.showMessageDialog(null, "영화가 삭제 되었습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "영화삭제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "영화삭제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.update_UI("movie_manage");
				break;
			case "취소":
				ui.update_UI("movie_manage");
				break;
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

}
