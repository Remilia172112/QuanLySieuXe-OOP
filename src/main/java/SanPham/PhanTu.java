package SanPham;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class PhanTu {
    public static Scanner sc  =new Scanner(System.in);
    // Tạo tháng năm hiện tại
    LocalDate localDate = LocalDate.now();
    int namhientai = localDate.getYear();
    int thanghientai = localDate.getMonthValue();
    int ngayhientai = localDate.getDayOfMonth();
    
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