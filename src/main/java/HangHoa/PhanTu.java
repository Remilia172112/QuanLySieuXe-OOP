package HangHoa;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class PhanTu {
    public static Scanner sc  =new Scanner(System.in);
    // Tạo tháng năm hiện tại
    LocalDate localDate = LocalDate.now();
    int namhientai = localDate.getYear();
    int thanghientai = localDate.getMonthValue();
    int ngayhientai = localDate.getDayOfMonth();

    String ma;
    String ten;
    int sl;
    
    public String getMa() {
        return ma;
    }
    public void setMa(String ma) {
        this.ma = ma;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public int getSl() {
        return sl;
    }
    public void setSl(int sl) {
        this.sl = sl;
    }
    public void nhap() {
        System.out.printf("Noi dung xuat");
    }
    public void xuat() {
        System.out.printf("Noi dung nhap");
    }
    public void suaThongTin() {
        System.out.printf("Noi dung sua thong tin");
    }
}
