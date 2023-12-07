package Nguoi;

import KiemTra.KiemTra;
import SanPham.PhanTu;

import java.time.LocalDate;

public class Nguoi extends PhanTu {
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
        String save[];
        save = ngaythangnamsinh.split("/");
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
        System.out.print("Nhap ho ten: ");
        boolean check = false;
        do {
            check = true;
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
        System.out.print("Nhap ngay/thang/nam sinh: ");
        boolean check = false;
        do {
            check = true;
            ngaythangnamsinh = sc.nextLine();
            check = KiemTra.check_date(ngaythangnamsinh);
            if(check) {
                check = KiemTra.CheckDate(ngaythangnamsinh);
                if(!check) System.out.print("Moi nhap lai: ");
            }
        } while (!check);
        xulytuoinamsinh(ngaythangnamsinh);
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh() { 
        System.out.print("Nhap gioi tinh (nam/nu): ");
        boolean check = false;
        do {
            gioitinh = sc.nextLine();
            check = KiemTra.check_sex(gioitinh);
        } while (!check);
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD() {
        System.out.print("Nhap so CCCD: ");
        boolean check = false;
        do {
            check = true;
            CCCD = sc.nextLine();
            check = KiemTra.check_cccd(CCCD);
        } while (!check);
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi() {
        System.out.print("Nhap dia chi: ");
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
        System.out.print("Nhap email: ");
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
        System.out.print("Nhap so dien thoai: ");
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
        setHoten();
        setGioitinh();
        setNgaythangnamsinh();
        setCCCD();
        setDiachi();
        setSdt();
        setEmail();
    }
    @Override
    public void xuat() {
        System.out.printf("%-25s %-15s %-15s %-15s\n", "Ho ten", "Gioi tinh", "Ngay sinh", "CCCD");
        System.out.printf("%-25s %-15s %-15s %-15s\n", getHoten(), getGioitinh(),getNgaythangnamsinh(), getCCCD());
        System.out.println();
        System.out.printf("%-40s %-15s %-15s\n", "Dia chi", "So dien thoai", "Email");
        System.out.printf("%-40s %-15s %-15s", getDiachi(), getSdt(), getEmail());
        System.out.println();
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin ca nhan ===");
            System.out.println("1. Sua ho ten");
            System.out.println("2. Sua ngay thang nam sinh");
            System.out.println("3. Sua gioi tinh");
            System.out.println("4. Sua can cuoc cong dan");
            System.out.println("5. Sua dia chi");
            System.out.println("6. Sua so dien thoai");
            System.out.println("7. Sua email");
            System.out.println("0. Thoat");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin ca nhan!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: " + getHoten());
                    setHoten();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: " + getNgaythangnamsinh());
                    setNgaythangnamsinh();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: " + getGioitinh());
                    setGioitinh();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: " + getCCCD());
                    setCCCD();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: " + getDiachi());
                    setDiachi();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: " + getSdt());
                    setSdt();
                    break;
                case 7:
                    System.out.println("Thong tin hien tai: " + getEmail());
                    setEmail();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu");
                    break;
            }
        } while (chon != 0);
    }
}
