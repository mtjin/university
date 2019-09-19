package Payment;

public class pay {
   private String payNo;
   private String bookNo;
   private String price;
   private String payMethod; // 1 = 인터넷결제 및 결제완료, 2 = 현장결제(결제 후 1로 변경)

   public String getpayNo() {
      return payNo;
   }
   public void setpayNo(String payNo) {
      this.payNo = payNo;
   }
   public String getbookNo() {
      return bookNo;
   }
   public void setbookNo(String bookNo) {
      this.bookNo = bookNo;
   }
   public String getprice() {
      return price;
   }
   public void setprice(String price) {
      this.price = price;
   }
   public String getpayMethod() {
      return payMethod;
   }
   public void setpayMethod(String payMethod) {
      this.payMethod = payMethod;
   }
}