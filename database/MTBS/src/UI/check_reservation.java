package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Booking.bookDB;
import People.member;

public class check_reservation extends JPanel {
   JButton re;
   JLabel bookNo, mID, theaterID;
   UI_Main ui;

   public check_reservation(UI_Main ui) {
      this.ui = ui;
      // 레이아웃 설정
      setLayout(null);

      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon("Resource/check_reservation.png"));
      lblNewLabel.setBounds(0, 0, 1024, 768);
      
      ArrayList allBook = new ArrayList();
      bookDB bookDB = new bookDB();
      member member = ui.getmember();
      String mID = member.getmID();
      allBook = bookDB.getCheckReservationList(mID);  //멤머바이디를 매개변수로 보내 자신의 예약현황만 확인

      
      /*ArrayList allBook = new ArrayList();
      bookDB bookDB = new bookDB();
      allBook = bookDB.getCheckReservationList();*/
      
      String[][] a = new String[allBook.size()][4];// 6
      for (int i = 0; i < a.length; i++) {
         for (int j = 0; j < a[i].length; j++) {
            a[i][j] = new String();
         }
      }

      for (int i = 0; i < a.length; i++) {
         for (int j = 0; j < a[i].length; j++) {
            a[i][j] = (String) ((ArrayList) allBook.get(i)).get(j);
         }
      }

      // 예약
      JLabel[] row = new JLabel[allBook.size()];
      JLabel[] row2 = new JLabel[allBook.size()];
      JLabel[] row3 = new JLabel[allBook.size()];
      JLabel[] row4 = new JLabel[allBook.size()];
      for (int i = 0; i < a.length; i++) {
         int j = 0;
         row[i] = new JLabel(a[i][j]);
         row[i].setBounds(130, 257 + (i * 41), 100, 20);
         row[i].setForeground(Color.WHITE);

         row2[i] = new JLabel(a[i][j + 1]);
         row2[i].setBounds(260, 257 + (i * 41), 100, 20);
         row2[i].setForeground(Color.WHITE);

         row3[i] = new JLabel(a[i][j + 2]);
         row3[i].setBounds(390, 257 + (i * 41), 130, 20);
         row3[i].setForeground(Color.WHITE);
         
         row4[i] = new JLabel(a[i][j + 3]);
         row4[i].setBounds(580, 257 + (i * 41), 100, 20);
         row4[i].setForeground(Color.WHITE);
      
         
         add(row[i]);
         add(row2[i]);
         add(row3[i]);
         add(row4[i]);
         
      }

      // 돌아가기 버튼 추가
      re = new JButton("돌아가기");
      re.setBackground(new Color(114, 137, 218));
      re.setForeground(Color.WHITE);
      re.setBounds(307, 647, 350, 60);
      re.setBorderPainted(false);
      re.setFocusPainted(false);
      add(re);
      add(lblNewLabel);

      re.addActionListener(new MyActionListener());
   }

   class MyActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         switch (e.getActionCommand()) {

         case "돌아가기":
            ui.update_UI("Main_Menu");
         }
      }
   }

}