package Nguoi;

import DanhSach.DanhSachNhanVien;
import DanhSach.DanhSachTaiKhoan;
import KiemTra.KiemTra;
import SanPham.PhanTu;
public class TaiKhoan extends PhanTu {
    private String username, password, type;

    public TaiKhoan() {
        username = null;
        password = null;
        type = null;
    }

    public TaiKhoan(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername() {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
        dsnv.xuatDanhSach();
        System.out.print("Nhap ma so nhan vien muon tao tai khoan: ");
        boolean check = false;
        do {
            check = true;
            username = sc.nextLine();
            check = KiemTra.check_maso(username);
            if(check) {
                check = dstk.layPhanTuVoi(username) == null;
                if (!check) System.out.print("Nhan vien da co tai khoan!! Hay nhap lai: ");
            }
            if(check) {
                check = dsnv.layPhanTuVoi(username) != null;
                if (!check) {
                    System.out.print("Ma nhan vien khong ton tai!! Ban co muon them nhan vien moi (1/0): ");
                    int chon = KiemTra.checkNumber();
                    if(chon == 1) dsnv.themPhanTuVaoDanhSach();
                }
            }
        }
        while (!check);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword() {
        System.out.print("Nhap mat khau: ");
        password = sc.nextLine();
    }

    public void changePassword(String username) {
        System.out.print("Nhap mat khau cu: ");
        var password_old = sc.nextLine();
        if(!password_old.equals(password)) {
            System.out.print("Nhap sai mat khau cu!!!");
            return;
        }
        System.out.print("Nhap mat khau moi: ");
        var password_new = sc.nextLine();
        System.out.print("Nhap lai mat khau: ");
        var password_new_again = sc.nextLine();
        if(!password_new.equals(password_new_again)) {
            System.out.print("Nhap khong trung nhau!!!");
            return;
        }
        System.out.print("Thay doi mat khau thanh cong!!!");
        password = password_new;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType() {
        System.out.print("Nhap loai tai khoan (nhan vien/quan ly): ");
        boolean check = false;
        do {
            check = true;
            type = sc.nextLine();
            if(type.equals("quan ly") || type.equals("nhan vien")) check = true;
            else check = false;
        }
        while (!check);
    }

    public String toString() {
        return username + "-" + type;
    }
    
    public void nhap(String username, String type) {
        setUsername(username);
        setPassword();
        setType(type);
    }

    @Override
    public void nhap() {
        setUsername();
        setPassword();
        setType();
    }
    @Override
    public void xuat() {
        System.out.printf("%-25s %-25s %-25s \n", "Ten tai khoan", "Mat khau", "Loai tai khoan");
        System.out.printf("%-25s %-25s %-25s \n", username, password, type);
        System.out.println("****************************");
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin nhan vien ===");
            System.out.println("1. Sua tai khoan");
            System.out.println("2. Sua mat khau");
            System.out.println("3. Sua loai tai khoan");
            System.out.println("0. Thoat");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin!!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: " + getUsername());
                    setUsername();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: " + getPassword());
                    setPassword();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: " + getType());
                    setType();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu");
                    break;
            }
        } while (chon != 0);
    }
}
