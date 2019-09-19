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

import Cinema.cinema;
import Cinema.cinemaDB;

public class cinema_change extends JPanel {
	JTextField cinemaName, cinemaAddress, cinemaNum;
	UI_Main ui;
	JButton ok, cancel;

	public cinema_change(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/cinena_change.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화관 이름 필드
		cinemaName = new JTextField(20);
		cinemaName.setBounds(455, 182, 470, 55);
		cinemaName.setOpaque(false);
		cinemaName.setForeground(Color.WHITE);
		cinemaName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaName.setCaretColor(Color.white);

		// 영화관 주소 필드
		cinemaAddress = new JTextField(20);
		cinemaAddress.setBounds(360, 255, 470, 55);
		cinemaAddress.setOpaque(false);
		cinemaAddress.setForeground(Color.WHITE);
		cinemaAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaAddress.setCaretColor(Color.white);

		// 영화관 번호 필드
		cinemaNum = new JTextField(20);
		cinemaNum.setOpaque(false);
		cinemaNum.setBounds(360, 329, 470, 55);
		cinemaNum.setForeground(Color.WHITE);
		cinemaNum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		cinemaNum.setCaretColor(Color.white);

		// 수정버튼 추가
		ok = new JButton("수정");
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
		add(cinemaAddress);
		add(cinemaNum);
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
			case "수정":
				// 영화관 이름 예외처리
				if (cinemaName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화관 이름을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 주소 예외처리
				if (isStringDouble(cinemaAddress.getText()) == true) {
					JOptionPane.showMessageDialog(null, "주소는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 전화번호 예외처리
				if (!cinemaNum.getText().equals("")) {
					if (isStringDouble(cinemaNum.getText()) == true) {
						JOptionPane.showMessageDialog(null, "전화번호는 ***-****형식으로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (cinemaNum.getText().length() > 9) {
							JOptionPane.showMessageDialog(null, "전화번호는 9자리 이내로 입력해주세요.", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					cinemaDB cinemaDB = new cinemaDB();
					cinema changecinema = cinemaDB.getCinemaDTO(cinemaName.getText());
					if (cinemaName.getText() == null) {
						JOptionPane.showMessageDialog(null, "해당하는 영화관 이름이 없습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}
					if (cinemaName.getText().equals("") && cinemaAddress.getText().equals("")
							&& cinemaNum.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아무 수정 사항이 없습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}
					
					if (!cinemaName.getText().equals(""))
						changecinema.setcNAME(cinemaName.getText());
					if (!cinemaAddress.getText().equals(""))
						changecinema.setcAddress(cinemaAddress.getText());
					if (!cinemaNum.getText().equals(""))
						changecinema.setcPhoneNum(cinemaNum.getText());

					boolean torf = cinemaDB.updateCinema(changecinema);

					if (torf)
						JOptionPane.showMessageDialog(null, "영화를 수정하였습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "영화수정을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "영화수정을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
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
