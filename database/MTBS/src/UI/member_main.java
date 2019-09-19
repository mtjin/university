package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import People.member;
import People.memberDB;

public class member_main extends JPanel {
	JButton dropout, change, re;
	UI_Main ui;

	public member_main(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/member_main.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 돌아가기 버튼 추가
		re = new JButton("돌아가기");
		re.setBackground(new Color(114, 137, 218));
		re.setForeground(Color.WHITE);
		re.setBounds(307, 647, 350, 60);
		re.setBorderPainted(false);
		re.setFocusPainted(false);

		// 정보변경 버튼 추가
		change = new JButton("정보변경");
		change.setBackground(new Color(114, 137, 218));
		change.setForeground(Color.WHITE);
		change.setBounds(105, 300, 350, 60);
		change.setBorderPainted(false);
		change.setFocusPainted(false);

		// 회원탈퇴 버튼 추가
		dropout = new JButton("회원탈퇴");
		dropout.setBackground(new Color(114, 137, 218));
		dropout.setForeground(Color.WHITE);
		dropout.setBounds(510, 300, 350, 60);
		dropout.setBorderPainted(false);
		dropout.setFocusPainted(false);

		add(dropout);
		add(change);
		add(re);
		add(lblNewLabel);
		dropout.addActionListener(new MyActionListener());
		change.addActionListener(new MyActionListener());
		re.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "정보변경":
				ui.update_UI("member_change");
				break;
			case "돌아가기":
				ui.update_UI("Main_Menu");
				break;
			case "회원탈퇴":
				try {
					int result = JOptionPane.showConfirmDialog(null, "탈퇴 하시겠습니까?", "메세지", JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						member member = ui.getmember();
						memberDB memberDB = new memberDB();
						memberDB.deleteMember(member.getmID(), member.getmPW());
						JOptionPane.showMessageDialog(null, "탈퇴 성공!", "메세지", JOptionPane.INFORMATION_MESSAGE);
						ui.update_UI("Login");
					}
					break;
				} catch (Exception e1) {
					System.out.println(e1.toString());
					JOptionPane.showMessageDialog(null, "탈퇴를 실패했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
		}
	}

}
