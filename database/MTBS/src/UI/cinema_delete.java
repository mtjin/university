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

import Cinema.cinemaDB;

public class cinema_delete extends JPanel {
	JTextField cinemaName;
	UI_Main ui;
	JButton ok, cancel;

	public cinema_delete(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cinema_delete.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화관 이름 필드
		cinemaName = new JTextField(20);
		cinemaName.setBounds(455, 325, 470, 55);
		cinemaName.setOpaque(false);
		cinemaName.setForeground(Color.WHITE);
		cinemaName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaName.setCaretColor(Color.white);

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

		add(cinemaName);
		add(cancel);
		add(ok);
		add(lblNewLabel);
		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "삭제":
				// 영화 아이디 예외처리
				if (cinemaName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화관 이름을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(cinemaName.getText()) != false) {
						JOptionPane.showMessageDialog(null, "영화관 이름은 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
				
				try {
				cinemaDB cinemaDB = new cinemaDB();
				boolean torf = cinemaDB.deleteCinema(cinemaName.getText());

				if(torf)
					JOptionPane.showMessageDialog(null, "영화관이 삭제 되었습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "영화관 삭제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "영화관 삭제를 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
				System.out.println(e1.toString());
			}
				ui.update_UI("cinema_manage");
				break;
			case "취소":
				ui.update_UI("cinema_manage");
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
