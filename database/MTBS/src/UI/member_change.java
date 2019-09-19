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

import People.member;
import People.memberDB;

public class member_change extends JPanel {
	JButton ok, cancel;
	JTextField name, id, PW, address, PN, dob;
	UI_Main ui;

	public member_change(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/member_change.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 비밀번호 필드
		PW = new JTextField(20);
		PW.setBounds(360, 198, 470, 55);
		PW.setOpaque(false);
		PW.setForeground(Color.WHITE);
		PW.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		PW.setCaretColor(Color.white);

		// 이름 필드
		name = new JTextField(20);
		name.setOpaque(false);
		name.setBounds(360, 275, 470, 55);
		name.setForeground(Color.WHITE);
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.setCaretColor(Color.white);

		// 생년월일 필드
		dob = new JTextField(10);
		dob.setOpaque(false);
		dob.setBounds(360, 349, 470, 55);
		dob.setForeground(Color.WHITE);
		dob.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		dob.setCaretColor(Color.white);

		// 주소 필드
		address = new JTextField(40);
		address.setOpaque(false);
		address.setBounds(360, 425, 470, 55);
		address.setForeground(Color.WHITE);
		address.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		address.setCaretColor(Color.white);

		// 전화번호 필드
		PN = new JTextField(12);
		PN.setOpaque(false);
		PN.setBounds(360, 499, 470, 55);
		PN.setForeground(Color.WHITE);
		PN.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		PN.setCaretColor(Color.white);

		// 정보변경버튼 추가
		ok = new JButton("정보변경");
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

		add(name);
		add(address);
		add(PW);
		add(PN);
		add(dob);
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
			case "정보변경":
				// 이름 예외처리
				if (!name.getText().isEmpty()) {
					if (isStringDouble(name.getText()) == true) {
						JOptionPane.showMessageDialog(null, "이름은 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 생년월일 예외처리
				if (!dob.getText().isEmpty()) {
					if (isStringDouble(dob.getText()) == false) {
						JOptionPane.showMessageDialog(null, "생년월일은 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (dob.getText().length() != 6) {
							JOptionPane.showMessageDialog(null, "생년월일은 6자리로 입력해주세요.", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				// 주소 예외처리
				if (!address.getText().isEmpty()) {
					if (isStringDouble(address.getText()) == true) {
						JOptionPane.showMessageDialog(null, "주소는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 전화번호 예외처리
				if (!PN.getText().isEmpty()) {
					if (isStringDouble(PN.getText()) == false) {
						JOptionPane.showMessageDialog(null, "전화번호는 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (PN.getText().length() != 11) {
							JOptionPane.showMessageDialog(null, "전화번호는 11자리로 입력해주세요.", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				try {
					member member = ui.getmember();

					if (!PW.getText().equals(""))
						member.setmPW(PW.getText());
					if (!name.getText().equals(""))
						member.setmName(name.getText());
					if (!dob.getText().equals(""))
						member.setmDOB(dob.getText());
					if (!address.getText().equals(""))
						member.setmAddress(address.getText());
					if (!PN.getText().equals(""))
						member.setmPN(PN.getText());

					memberDB memberDB = new memberDB();
					boolean torf = memberDB.updateMember(member);

					if (torf)
						JOptionPane.showMessageDialog(null, "정보를 수정하였습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "정보수정을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "정보수정을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				break;
			case "취소":
				ui.update_UI("Main_Menu");
				break;
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
