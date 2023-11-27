package Nguoi;

import HangHoa.PhanTu;
import KiemTra.KiemTra;

import java.time.LocalDate;

public abstract class Nguoi extends PhanTu {
    private String hoten;
    // Tuổi xử lý sau
    private int tuoi;
    private int namsinh;
    private String ngaythangnamsinh;
    private String CCCD;
    private String gioitinh;
    private String diachi;
    private String sdt;
    private String email;
    // Lấy ngày tháng hiện tại
    LocalDate localDate = LocalDate.now();
    int namhientai = localDate.getYear();
    int thanghientai = localDate.getMonthValue();
    int ngayhientai = localDate.getDayOfMonth();

    public Nguoi() {
    }

    public Nguoi(String hoten, String ngaythangnamsinh, String CCCD, String gioitinh, String diachi, String sdt,
            String email) {
        this.hoten = hoten;
        this.CCCD = CCCD;
        this.ngaythangnamsinh = ngaythangnamsinh;
        xulytuoinamsinh(ngaythangnamsinh);
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
    }

    public void xulytuoinamsinh(String ngaythangnamsinh) {
        String save[] = ngaythangnamsinh.split("/");
        namsinh = Integer.parseInt(save[2]);
        if (Integer.parseInt(save[0]) >= ngayhientai && Integer.parseInt(save[1]) == thanghientai)
            tuoi = namhientai - namsinh;
        else
            tuoi = namhientai - namsinh - 1;
        if (Integer.parseInt(save[1]) < thanghientai)
            tuoi = namhientai - namsinh;
    }

    public String getHoten() {
        return hoten;
    }
    
    public void setHoten() {
        boolean check = false;
        do {
            hoten = sc.nextLine();
            check = KiemTra.check_name(hoten);
        } while (!check);
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi() {
        tuoi = KiemTra.checkNumber();
    }
    public String getNgaythangnamsinh() {
        return ngaythangnamsinh;
    }
    public void setNgaythangnamsinh(String ngaythangnamsinh) {
        this.ngaythangnamsinh = ngaythangnamsinh;
        xulytuoinamsinh(ngaythangnamsinh);
    }

    public void setNgaythangnamsinh() {
        boolean check = false;
        do {
            ngaythangnamsinh = sc.nextLine();
            check = KiemTra.check_date(ngaythangnamsinh);
        } while (!check);
        xulytuoinamsinh(ngaythangnamsinh);
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh() {
        boolean check = false;
        do {
            gioitinh = sc.nextLine();
            check = KiemTra.check_sex(gioitinh);
        } while (!check);
    }

    public String getCCCD() {
        return CCCD;
    }

    public String setCCCD() {
        return CCCD;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi() {
        boolean check = false;
        do {
            diachi = sc.nextLine();
            check = KiemTra.check_diachi(diachi);
        } while (!check);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        boolean check = false;
        do {
            email = sc.nextLine();
            check = KiemTra.check_mail(email);
        } while (!check);
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt() {
        boolean check = false;
        do {
            sdt = sc.nextLine();
            check = KiemTra.check_sdt(sdt);
        } while (!check);
    }
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setCCCD(String cCCD) {
        CCCD = cCCD;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public void nhap() {
        System.out.println("Nhap ho ten: ");
        setHoten();
        System.out.println("Nhap ngay/thang/nam sinh: ");
        setNgaythangnamsinh();
        System.out.println("Nhap so CCCD: ");
        setCCCD();
        System.out.println("Nhap gioi tinh (nam/nu): ");
        setGioitinh();
        System.out.println("Nhap dia chi: ");
        setDiachi();
        System.out.println("Nhap so dien thoai: ");
        setDiachi();
        System.out.println("Nhap email: ");
        setEmail();
    }

    public void xuat() {
        System.out.printf("%-25s %-25s %-25s %-20s %-50s %-20s %-25s", getHoten(), getGioitinh(),getNgaythangnamsinh(), getCCCD(), getDiachi(), getSdt(), getEmail());
    }

    public abstract void suaThongTin();
}
