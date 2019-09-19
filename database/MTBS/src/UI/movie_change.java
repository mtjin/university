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

import Movie.movie;
import Movie.movieDB;

public class movie_change extends JPanel {
	JTextField mvID, mvMovieTitle, mvDirector, mvActor, mvGrade, mvInfo;
	UI_Main ui;
	JButton ok, cancel;

	public movie_change(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_change.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화 아이디 필드
		mvID = new JTextField(20);
		mvID.setBounds(455, 182, 470, 55);
		mvID.setOpaque(false);
		mvID.setForeground(Color.WHITE);
		mvID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvID.setCaretColor(Color.white);

		// 영화 제목 필드
		mvMovieTitle = new JTextField(20);
		mvMovieTitle.setBounds(360, 255, 470, 55);
		mvMovieTitle.setOpaque(false);
		mvMovieTitle.setForeground(Color.WHITE);
		mvMovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvMovieTitle.setCaretColor(Color.white);

		// 감독 필드
		mvDirector = new JTextField(20);
		mvDirector.setOpaque(false);
		mvDirector.setBounds(360, 329, 470, 55);
		mvDirector.setForeground(Color.WHITE);
		mvDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvDirector.setCaretColor(Color.white);

		// 출연 필드
		mvActor = new JTextField(30);
		mvActor.setOpaque(false);
		mvActor.setBounds(360, 405, 470, 55);
		mvActor.setForeground(Color.WHITE);
		mvActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvActor.setCaretColor(Color.white);

		// 등급 필드
		mvGrade = new JTextField(10);
		mvGrade.setOpaque(false);
		mvGrade.setBounds(360, 479, 470, 55);
		mvGrade.setForeground(Color.WHITE);
		mvGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvGrade.setCaretColor(Color.white);

		// 주요정보 필드
		mvInfo = new JTextField(30);
		mvInfo.setOpaque(false);
		mvInfo.setBounds(360, 553, 470, 55);
		mvInfo.setForeground(Color.WHITE);
		mvInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvInfo.setCaretColor(Color.white);

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

		add(mvID);
		add(mvMovieTitle);
		add(mvDirector);
		add(mvActor);
		add(mvGrade);
		add(mvInfo);
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
				// 영화 아이디 예외처리
				if (mvID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화 아이디를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvID.getText()) == false) {
						JOptionPane.showMessageDialog(null, "영화 아이디는 숫자로 입력해주세요.", "입력 오류",
								JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 감독 예외처리
				if (isStringDouble(mvDirector.getText()) == true) {
					JOptionPane.showMessageDialog(null, "감독이름은 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 출연 예외처리
				if (isStringDouble(mvActor.getText()) == true) {
					JOptionPane.showMessageDialog(null, "출연자는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 등급 예외처리
				if (!mvGrade.getText().equals("")) {
					if (isStringDouble(mvGrade.getText()) == false) {
						JOptionPane.showMessageDialog(null, "등급은 숫자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					} else {
						if (mvGrade.getText().length() != 2) {
							JOptionPane.showMessageDialog(null, "등급은 2자리로 입력해주세요.", "입력 오류",
									JOptionPane.WARNING_MESSAGE);
							break;
						}
					}
				}

				// 주요 정보 예외처리
				if (isStringDouble(mvInfo.getText()) == true) {
					JOptionPane.showMessageDialog(null, "주요정보는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				try {
					movieDB movieDB = new movieDB();
					movie changeMovie = movieDB.getMovieDTO(mvID.getText());
					if (changeMovie.getMvMovieTitle() == null) {
						JOptionPane.showMessageDialog(null, "해당하는 영화 아이디가 없습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}
					if (mvMovieTitle.getText().equals("") && mvDirector.getText().equals("")
							&& mvActor.getText().equals("") && mvGrade.getText().equals("")
							&& mvInfo.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아무 수정 사항이 없습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
						ui.update_UI("movie_manage");
						break;
					}

					if (!mvMovieTitle.getText().equals(""))
						changeMovie.setMvMovieTitle(mvMovieTitle.getText());
					if (!mvDirector.getText().equals(""))
						changeMovie.setMvDirector(mvDirector.getText());
					if (!mvActor.getText().equals(""))
						changeMovie.setMvActor(mvActor.getText());
					if (!mvGrade.getText().equals(""))
						changeMovie.setMvGrade(mvGrade.getText());
					if (!mvInfo.getText().equals(""))
						changeMovie.setMvInfo(mvInfo.getText());

					boolean torf = movieDB.updateMovie(changeMovie);

					if (torf)
						JOptionPane.showMessageDialog(null, "영화를 수정하였습니다!", "메세지", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "영화수정을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "영화수정을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
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
