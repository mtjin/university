package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Movie.movie;
import Movie.movieDB;
import Theater.TheaterDB;
import Theater.theater;

public class movie_info_UI extends JPanel {
	JTextField mvID, mvTheaterID, mvMovieTitle, mvDirector, mvActor, mvGrade, mvInfo;
	UI_Main ui;
	JButton ok, cancel;

	public movie_info_UI(UI_Main ui) {
		this.ui = ui;
		// 레이아웃 설정
		setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resource/movie_info.png"));
		lblNewLabel.setBounds(0, 0, 1024, 768);

		// 영화 아이디 필드
		mvID = new JTextField(20);
		mvID.setBounds(360, 176, 470, 55);
		mvID.setOpaque(false);
		mvID.setForeground(Color.WHITE);
		mvID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvID.setCaretColor(Color.white);

		// 상영관 아이디 필드
		mvTheaterID = new JTextField(20);
		mvTheaterID.setBounds(360, 234, 470, 55);
		mvTheaterID.setOpaque(false);
		mvTheaterID.setForeground(Color.WHITE);
		mvTheaterID.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvTheaterID.setCaretColor(Color.white);

		// 영화 제목 필드
		mvMovieTitle = new JTextField(20);
		mvMovieTitle.setBounds(360, 293, 470, 55);
		mvMovieTitle.setOpaque(false);
		mvMovieTitle.setForeground(Color.WHITE);
		mvMovieTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvMovieTitle.setCaretColor(Color.white);

		// 감독 필드
		mvDirector = new JTextField(20);
		mvDirector.setOpaque(false);
		mvDirector.setBounds(360, 352, 470, 55);
		mvDirector.setForeground(Color.WHITE);
		mvDirector.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvDirector.setCaretColor(Color.white);

		// 출연 필드
		mvActor = new JTextField(30);
		mvActor.setOpaque(false);
		mvActor.setBounds(360, 414, 470, 55);
		mvActor.setForeground(Color.WHITE);
		mvActor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvActor.setCaretColor(Color.white);

		// 등급 필드
		mvGrade = new JTextField(10);
		mvGrade.setOpaque(false);
		mvGrade.setBounds(360, 470, 470, 55);
		mvGrade.setForeground(Color.WHITE);
		mvGrade.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvGrade.setCaretColor(Color.white);

		// 주요정보 필드
		mvInfo = new JTextField(30);
		mvInfo.setOpaque(false);
		mvInfo.setBounds(360, 530, 470, 55);
		mvInfo.setForeground(Color.WHITE);
		mvInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		mvInfo.setCaretColor(Color.white);

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

		add(mvID);
		add(mvTheaterID);
		add(mvMovieTitle);
		add(mvDirector);
		add(mvActor);
		add(mvGrade);
		add(mvInfo);
		add(cancel);
		add(ok);
		add(lblNewLabel);

		mvID.addActionListener(new MyActionListener());
		mvTheaterID.addActionListener(new MyActionListener());
		mvMovieTitle.addActionListener(new MyActionListener());
		mvDirector.addActionListener(new MyActionListener());
		mvActor.addActionListener(new MyActionListener());
		mvGrade.addActionListener(new MyActionListener());
		mvInfo.addActionListener(new MyActionListener());

		ok.addActionListener(new MyActionListener());
		cancel.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "저장":
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

				// 상영관 아이디 예외처리
				if (mvTheaterID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "상영관 아이디를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 영화 제목 예외처리
				if (mvMovieTitle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "영화 제목을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				}

				// 감독 예외처리
				if (mvDirector.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "감독이름을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvDirector.getText()) == true) {
						JOptionPane.showMessageDialog(null, "감독이름은 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 출연 예외처리
				if (mvActor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "출연자를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvActor.getText()) == true) {
						JOptionPane.showMessageDialog(null, "출연자는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				// 등급 예외처리
				if (mvGrade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "등급을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
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
				if (mvInfo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "주요정보를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
					break;
				} else {
					if (isStringDouble(mvInfo.getText()) == true) {
						JOptionPane.showMessageDialog(null, "주요정보는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
						break;
					}
				}

				try {
					TheaterDB TheaterDB = new TheaterDB();
					theater theater = new theater();
					Vector theaterlist = TheaterDB.getTheaterList();

					boolean temp = false;
					for (int i = 0; i < theaterlist.size(); i++) {
						if (mvTheaterID.getText().equals(((Vector) (theaterlist.get(i))).get(0))) {
							theater.settTheaterID((String) ((Vector) theaterlist.get(i)).get(0));
							theater.settCinemaName((String) ((Vector) theaterlist.get(i)).get(1));
							theater.settSeatNum((String) ((Vector) theaterlist.get(i)).get(2));
							theater.settMovieTitle(mvMovieTitle.getText());
							theater.settStartTime((String) ((Vector) theaterlist.get(i)).get(4));
							TheaterDB.updateTheater(theater);
							temp = true;
							break;
						}
					}

					// 기존에 있는 상영관
					if (temp) {
						movie new_movie = new movie();
						new_movie.setMvID(mvID.getText());
						new_movie.setMvTheaterID(mvTheaterID.getText());
						new_movie.setMvMovieTitle(mvMovieTitle.getText());
						new_movie.setMvDirector(mvDirector.getText());
						new_movie.setMvActor(mvActor.getText());
						new_movie.setMvGrade(mvGrade.getText());
						new_movie.setMvInfo(mvInfo.getText());
						new_movie.setMvAccumulateNum("0");
						movieDB movieDB = new movieDB();

						boolean torf = movieDB.insertMovie(new_movie);
						if (torf)
							JOptionPane.showMessageDialog(null, "영화가 등록 되었습니다!", "메세지",
									JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "영화등록을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "일치하는 상영관이 없습니다. 영화관을 먼저 추가 해주세요.", "메세지",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "영화등록을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
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