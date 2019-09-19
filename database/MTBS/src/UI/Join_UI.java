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

public class Join_UI extends JPanel {
	JTextField name, id, PW, address, PN, dob;
	UI_Main ui;
	JButton ok, cancel;

	public Join_UI(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/join.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 아이디 필드
		id = new JTextField(20);
		id.setBounds(360, 198, 470, 55);
		id.setOpaque(false);
		id.setForeground(Color.WHITE);
		id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		id.setCaretColor(Color.white);

		// 비밀번호 필드
		PW = new JTextField(20);
		PW.setBounds(360, 275, 470, 55);
		PW.setOpaque(false);
		PW.setForeground(Color.WHITE);
		PW.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		PW.setCaretColor(Color.white);

		// 이름 필드
		name = new JTextField(20);
		name.setOpaque(false);
		name.setBounds(360, 349, 470, 55);
		name.setForeground(Color.WHITE);
		name.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		name.setCaretColor(Color.white);

		// 생년월일 필드
		dob = new JTextField(10);
		dob.setOpaque(false);
		dob.setBounds(360, 425, 470, 55);
		dob.setForeground(Color.WHITE);
		dob.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		dob.setCaretColor(Color.white);

		// 주소 필드
		address = new JTextField(40);
		address.setOpaque(false);
		address.setBounds(360, 499, 470, 55);
		address.setForeground(Color.WHITE);
		address.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		address.setCaretColor(Color.white);

		// 전화번호 필드
		PN = new JTextField(12);
		PN.setOpaque(false);
		PN.setBounds(360, 573, 470, 55);
		PN.setForeground(Color.WHITE);
		PN.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		PN.setCaretColor(Color.white);

		// 저장버튼 추가
		ok = new JButton("저장");
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
		add(id);
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
			case "저장":
				// 아이디 예외처리
				if (id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(id.getText()) != false) {
						JOptionPane.showMessageDialog(null, "아이디는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 비밀번호 예외처리
				if (PW.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 이름 예외처리
				if (name.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(name.getText()) == true) {
						JOptionPane.showMessageDialog(null, "이름은 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 생년월일 예외처리
				if (dob.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
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
				if (address.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "주소를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(address.getText()) == true) {
						JOptionPane.showMessageDialog(null, "주소는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 전화번호 예외처리
				if (PN.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
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
					member new_member = new member();
					new_member.setmID(id.getText());
					new_member.setmPW(PW.getText());
					new_member.setmName(name.getText());
					new_member.setmDOB(dob.getText());
					new_member.setmAddress(address.getText());
					new_member.setmPN(PN.getText());
					new_member.setmticket("0");
					new_member.setMpoint("0");

					memberDB memberDB = new memberDB();
					memberDB.getConn();
					boolean torf = memberDB.insertMember(new_member);
					if (torf)
						JOptionPane.showMessageDialog(null, "회원가입을 축하드립니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "회원가입을 실패 하였습니다.", "메세지", JOptionPane.WARNING_MESSAGE);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "회원가입을 실패 하였습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					System.out.println(e1.toString());
				}
				ui.update_UI("Login");
				break;
			case "취소":
				ui.update_UI("Login");
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
