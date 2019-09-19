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
import Theater.TheaterDB;
import Theater.theater;

public class cinema_add extends JPanel {
   JTextField cNAME, cAddress, cPhoneNum;
   UI_Main ui;
   JButton ok, cancel;

   public cinema_add(UI_Main ui) {
      this.ui = ui;
      // 레이아웃 설정
      setLayout(null);

      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon("Resource/cinema_add.png"));
      lblNewLabel.setBounds(0, 0, 1024, 768);

      // 이름필드
      cNAME = new JTextField(20);
      cNAME.setBounds(360, 198, 470, 55);
      cNAME.setOpaque(false);
      cNAME.setForeground(Color.WHITE);
      cNAME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      cNAME.setCaretColor(Color.white);

      // 주소필드
      cAddress = new JTextField(20);
      cAddress.setBounds(360, 275, 470, 55);
      cAddress.setOpaque(false);
      cAddress.setForeground(Color.WHITE);
      cAddress.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      cAddress.setCaretColor(Color.white);

      // 전화번호필드
      cPhoneNum = new JTextField(20);
      cPhoneNum.setOpaque(false);
      cPhoneNum.setBounds(360, 349, 470, 55);
      cPhoneNum.setForeground(Color.WHITE);
      cPhoneNum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      cPhoneNum.setCaretColor(Color.white);

      
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

      add(cNAME);
      add(cAddress);
      add(cPhoneNum);
      add(cancel);
      add(ok);
      add(lblNewLabel);
      cNAME.addActionListener(new MyActionListener());
      cAddress.addActionListener(new MyActionListener());
      cPhoneNum.addActionListener(new MyActionListener());
      ok.addActionListener(new MyActionListener());
      cancel.addActionListener(new MyActionListener());
   }

   class MyActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         switch (e.getActionCommand()) {
         case "저장":
            
            if (cNAME.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "영화관 이름을 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
               break;
            } else {
               if (isStringDouble(cNAME.getText()) != false) {
                  JOptionPane.showMessageDialog(null, "영화관 이름을 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                  break;
               }
            }

            
            if (cAddress.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "영화관 주소를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
               break;
            }else {
               if (isStringDouble(cAddress.getText()) != false) {
                  JOptionPane.showMessageDialog(null, "영화관 주소는 글자로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                  break;
               }
            }

            
            if (cPhoneNum.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "영화관 전화번호를 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
               break;
            } else {
               if (isStringDouble(cPhoneNum.getText()) == true) {
                  JOptionPane.showMessageDialog(null, "전화번호는 ***-****형식으로 입력해주세요.", "입력 오류", JOptionPane.WARNING_MESSAGE);
                  break;
               }
            }

            
            
            try {
               cinema new_cinema = new cinema();
               new_cinema.setcNAME(cNAME.getText());
               new_cinema.setcAddress(cAddress.getText());
               new_cinema.setcPhoneNum(cPhoneNum.getText());

               cinemaDB cinemaDB = new cinemaDB();
               boolean torf = cinemaDB.insertCinema(new_cinema);
               
               if(torf) {
                   JOptionPane.showMessageDialog(null, "영화관이 등록 되었습니다, 상영관을 등록합니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
                   ui.setcinema(new_cinema);
                   ui.update_UI("theater_add");
               }
               else {
                   JOptionPane.showMessageDialog(null, "영화관등록을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
                   ui.update_UI("cinema_manage");
               }
                  
            } catch (Exception e1) {
               JOptionPane.showMessageDialog(null, "영화관등록을 실패 했습니다.", "메세지", JOptionPane.WARNING_MESSAGE);
               System.out.println(e1.toString());
            }
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