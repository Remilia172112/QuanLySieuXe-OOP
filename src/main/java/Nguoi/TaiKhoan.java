package Nguoi;

import DanhSach.DanhSachNhanVien;
import HangHoa.PhanTu;
import KiemTra.KiemTra;
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
        System.out.print("Nhap tai khoan: ");
        DanhSachNhanVien ttds = new DanhSachNhanVien();
        boolean check = false;
        do {
            check = true;
            username = sc.nextLine();
            check = ttds.layPhanTuVoi(username) == null;
            if (!check) System.out.print("Ma nhan vien da ton tai, moi nhap lai: ");
            check = KiemTra.check_maso(username);
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
        boolean check = false;
        do {
            check = true;
            password = sc.nextLine();
            check = KiemTra.check_maso(password);
        }
        while (!check);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType() {
        System.out.print("Nhap loai tai khoan (admin/quan ly/nhan vien): ");
        boolean check = false;
        do {
            check = true;
            type = sc.nextLine();
            if(type == "admin" || type == "quan ly" || type == "nhan vien") check = true;
            else check = false;
        }
        while (!check);
    }

    public String toString() {
        return username + "-" + type;
    }
    
    @Override
    public void nhap() {
        setUsername();
        setPassword();
        setType();
    }

    public void xuat() {
        System.out.printf("%s-25 %s-25 %-25s\n", "Tai khoan", "Mat khau", "Loai tai khoan");
        System.out.printf("%s-25 %s-25 %-25s", getUsername(), getPassword(), getType());
    }

    public void suaThongTin() {
        System.out.println("=== Sua thong tin nhan vien ===");
        System.out.println("1. Sua tai khoan");
        System.out.println("2. Sua mat khau");
        System.out.println("3. Sua loai tai khoan");
        System.out.println("0. Thoat");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
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
                    chon = 0;
                    System.out.println("Thoat sua thong tin!!!");
                    break;
            }

        } while (chon != 0);
    }
}
