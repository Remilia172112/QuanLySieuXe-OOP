package Nguoi;

import DanhSach.DanhSachNhanVien;
import KiemTra.KiemTra;

public class NhanVien extends Nguoi {
    private String manhanvien;
    private String ngayvaolam;
    private static int luongcoban = 4500000;
    private double hesoluong;
    private double luong;
    private double thuong;
    private char hang;
    private int soNgayNghiTrongThang;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String ngayVaoLam, int LuongCoBan, double heSoLuong, double luong, double thuong,
            char hang, int soNgayNghiTrongThang) {
        this.manhanvien = maNhanVien;
        this.ngayvaolam = ngayVaoLam;
        NhanVien.luongcoban = LuongCoBan;
        this.hesoluong = heSoLuong;
        this.luong = luong;
        this.thuong = thuong;
        this.hang = hang;
        this.soNgayNghiTrongThang = soNgayNghiTrongThang;
    }

    public NhanVien(String maNhanVien, String ngayVaoLam, int LuongCoBan, double heSoLuong, double luong, double thuong,
            char hang, int soNgayNghiTrongThang, String hoten, String ngaythangnamsinh, String CCCD, String gioitinh,
            String diachi, String sdt, String email) {
        super(hoten, ngaythangnamsinh, CCCD, gioitinh, diachi, sdt, email);
        this.manhanvien = maNhanVien;
        this.ngayvaolam = ngayVaoLam;
        NhanVien.luongcoban = LuongCoBan;
        this.hesoluong = heSoLuong;
        this.luong = luong;
        this.thuong = thuong;
        this.hang = hang;
        this.soNgayNghiTrongThang = soNgayNghiTrongThang;
    }

    public String getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(String a) {
        manhanvien = a;
    }

    public void setManhanvien() {
        System.out.print("Nhap ma nhan vien: ");
        DanhSachNhanVien ttds = new DanhSachNhanVien();
        boolean check = false;
        do {
            check = true;
            manhanvien = sc.nextLine();
            check = ttds.layPhanTuVoi(manhanvien) == null;
            if (!check) System.out.print("Ma nhan vien da ton tai, moi nhap lai: ");
            check = KiemTra.check_maso(manhanvien);
        }
        while (!check);
    }

    public String getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(String a) {
        ngayvaolam = a;
    }

    public void setNgayvaolam() {
        System.out.print("Nhap ngay vao lam: ");
        boolean check;
        int ngay = 1, thang = 1, nam = 1;
        // kiểm tra điều kiện ngày/tháng/năm
        do {
            check = true;
            System.out.print("Nhap ngay: ");
            ngay = KiemTra.checkNumber();;
            System.out.print("Nhap thang: ");
            thang = KiemTra.checkNumber();;
            System.out.print("Nhap nam: ");
            nam = KiemTra.checkNumber();;
            if (ngay <= 0 || ngay > 31) {
                check = false;
                System.out.println("Ngay khong hop le!");
            }
            if (thang <= 0 || thang > 12) {
                check = false;
                System.out.println("Thang khong hop le!");
            }
            if (nam <= 1920 || nam > 2022) {
                check = false;
                System.out.println("Nam khong hop le!");
            }
            if (nam % 400 == 0 || (nam % 4 == 0 && nam % 100 != 0)) { // năm nhuận
                if (thang == 2) {
                    if (ngay > 29) {
                        check = false;
                        System.out.println("Thang 2 nam da nhap chi co 29 ngay!");
                    }
                }
            } else { // không nhuận
                if (thang == 2) {
                    if (ngay > 28) {
                        check = false;
                        System.out.println("Thang 2 nam da nhap chi co 28 ngay!");
                    }
                }
            }
            switch (thang) { // các trường hợp còn lại
                case 4, 6, 9, 11:
                    if (ngay > 30) {
                        check = false;
                        System.out.println("Thang da nhap chi co 30 ngay!");
                    }
                    break;
            }
        } while (!check);
        this.ngayvaolam = ngay + "/" + thang + "/" + nam;
    }

    public double getHesoluong() {
        return hesoluong;
    }
    public void setHesoluong(double heSoLuong) {
        this.hesoluong = heSoLuong;
    }
    public void setHesoluong() {
        System.out.print("Nhap he so luong: ");
        boolean check = false;
        String ch;
        do {
            ch = sc.nextLine();
            check = KiemTra.isNumber(ch);
        } while (!check);
        hesoluong = Double.parseDouble(ch);
    }

    public double getLuong() {
        return luong;
    }
    public void setLuong() {
        double luong = 0;
        luong = luongcoban * hesoluong + thuong;
        this.luong = luong;
    }

    public double getThuong() {
        return thuong;
    }

    public void setThuong(double thuong) {
        this.thuong = thuong;
    }

    public char getHang() {
        return hang;
    }

    public void setHang(char hang) { // tính hạng và thưởng dựa theo ngày vào làm
        this.hang = hang;
        double thuong = 0;

        String ngayVaoLam = getNgayvaolam();
        String[] ngayThangNam = ngayVaoLam.split("/"); // tách thành mảng

        if (2023 - Integer.parseInt(ngayThangNam[2]) > 1) { // nếu số năm làm việc > 1
            if (hang == 'A')
                thuong = luongcoban * hesoluong * 0.05;
            else if (hang == 'B')
                thuong = luongcoban * hesoluong * 0.02;
        }
        setThuong(thuong);
    }

    public static int getLuongcoban() {
        return luongcoban;
    }

    public static void setLuongcoban(int LuongCoBan) {
        NhanVien.luongcoban = LuongCoBan;
    }

    public int getSongaynghitrongthang() {
        return soNgayNghiTrongThang;
    }

    public void setSongaynghitrongthang() {
        System.out.print("Nhap so ngay nghi trong thang: ");
        do {

            try {
                soNgayNghiTrongThang = KiemTra.checkNumber();;
            } catch (Exception e) {
                System.out.print("Vui long nhap mot so: ");
                continue;
            }
            if (soNgayNghiTrongThang < 0 || soNgayNghiTrongThang > 31)
                System.out.print("Moi nhap lai: ");
        } while (soNgayNghiTrongThang < 0 || soNgayNghiTrongThang > 31);

        if (soNgayNghiTrongThang < 2)
            setHang('A');
        else if (soNgayNghiTrongThang < 5)
            setHang('B');
        else
            setHang('C');

        setLuong();
    }

    public void setSongaynghitrongthang(int soNgayNghiTrongThang) {
        this.soNgayNghiTrongThang = soNgayNghiTrongThang;
        if (soNgayNghiTrongThang < 2)
            setHang('A');
        else if (soNgayNghiTrongThang < 5)
            setHang('B');
        else
            setHang('C');
        setLuong();
    }

    @Override
    public void nhap() {
        setManhanvien();
        setNgayvaolam();
        setHesoluong();
        setSongaynghitrongthang();
        super.nhap();
    }
    public void xuat() {
        System.out.printf("%-25s %-25s %-25s %-25s %-20s %-50s %-20s %-25s\n", "Ma nhan vien", "Ho ten", "Gioi tinh", "Ngay sinh", "CCCD", "Dia chi", "So dien thoai", "Email");
        System.out.printf("%-25s ", getManhanvien());
        super.xuat();
        System.out.printf("\n%-25s %-25s %-30s %-10s %-10s %-10s\n", "Ngay vao lam", "He so luong",
                "So ngay nghi trong thang", "Luong", "Thuong", "Hang");
        System.out.printf("%-25s %-25s %-30s %-10s %-10s %-10s\n", getNgayvaolam(), getHesoluong(),
                getSongaynghitrongthang(), getLuong(), getThuong(), getHang());
        System.out.println("***");
    }

    public static void xuat(NhanVien nv) {
        nv.xuat();
    }
    public void suaThongTin() {
        System.out.println("=== Sua thong tin nhan vien ===");
        System.out.println("1. Sua ho ten");
        System.out.println("2. Sua ngay thang nam sinh");
        System.out.println("3. Sua gioi tinh");
        System.out.println("4. Sua can cuoc cong dan");
        System.out.println("5. Sua dia chi");
        System.out.println("6. Sua so dien thoai");
        System.out.println("7. Sua email");
        System.out.println("8. Sua ngay vao lam");
        System.out.println("9. Sua he so luong");
        System.out.println("10. Sua so ngay nghi trong thang");
        System.out.println("0. Thoat");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
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
                case 8:
                    System.out.println("Thong tin hien tai: " + getNgayvaolam());
                    setNgayvaolam();
                    break;
                case 9:
                    System.out.println("Thong tin hien tai: " + getHesoluong());
                    System.out.print("Nhap noi dung: ");
                    setHesoluong();
                    break;
                case 10:
                    System.out.println("Thong tin hien tai: " + getSongaynghitrongthang());
                    setSongaynghitrongthang();
                    break;
                default:
                    chon = 0;
                    break;
            }
        } while (chon == 0);
    }
}
