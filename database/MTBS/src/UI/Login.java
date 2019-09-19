package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import People.Admin;
import People.member;
import People.memberDB;

public class Login extends JPanel {
	JTextField loginTextField;
	JPasswordField passwordField;
	BufferedImage img = null;
	JButton bt, join;
	UI_Main ui;

	public Login(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/login.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 아이디 필드
		loginTextField = new JTextField(10);
		loginTextField.setBounds(435, 260, 400, 60);
		loginTextField.setOpaque(false);
		loginTextField.setForeground(Color.WHITE);
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		loginTextField.setCaretColor(Color.white);
		// 패스워드 필드
		passwordField = new JPasswordField(10);
		passwordField.setBounds(435, 415, 400, 60);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.WHITE);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		passwordField.setCaretColor(Color.white);

		// 로그인버튼 추가
		bt = new JButton("로그인");
		bt.setBackground(new Color(114, 137, 218));
		bt.setForeground(Color.WHITE);
		bt.setBounds(435, 500, 400, 60);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);

		// 가입버튼 추가
		join = new JButton(" ");
		join.setBounds(435, 590, 150, 25);
		join.setContentAreaFilled(false);
		join.setBorderPainted(false);
		join.setFocusPainted(false);

		add(loginTextField);
		add(passwordField);
		add(bt);
		add(join);
		add(lblNewLabel);
		join.addActionListener(new MyActionListener());
		bt.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "로그인":
				Admin admin = new Admin();
				// admin입력했을 때
				if (loginTextField.getText().equals(admin.getID())) {
					if (String.valueOf(passwordField.getPassword()).equals(admin.getPW())) {
						ui.update_UI("Main_Menu_admin");
						break;
					}
				}
				// 회원이 입력했을 때
				else {
					try {
						memberDB memberDB = new memberDB();
						member member = memberDB.getMemberDTO(loginTextField.getText());
						String pw = member.getmPW();
						if (pw.equals(passwordField.getText())) {
							ui.setmember(member);
							ui.update_UI("Main_Menu");
							break;
						}
					} catch (Exception e1) {
						System.out.println(e1.toString());
						JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						break;
					}
					JOptionPane.showMessageDialog(null, "아이디, 패스워드를 확인 해주세요.", "메세지", JOptionPane.WARNING_MESSAGE);
				}
				break;
			case " ":
				ui.update_UI("Join_UI");
				break;
			}
		}
	}
}