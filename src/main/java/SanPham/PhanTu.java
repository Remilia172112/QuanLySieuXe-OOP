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
    
    abstract public void nhap();
    abstract public void xuat();
    abstract public void suaThongTin();
}
