import DanhSach.*;
import HangHoa.PhanTu;
import Nguoi.TaiKhoan;
import KiemTra.KiemTra;

import java.util.Scanner;
public class XuLy {
    private static Scanner sc = new Scanner(System.in);

    public static void xuLyTrungTam() {
        int chon;
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
         System.out.println("***** Chao mung ban den voi chuong trinh quan ly cua hang sieu xe *****");
        do {
            System.out.println("==============================");
            System.out.println("1. Dang Nhap");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    System.out.print("Tai khoan: ");
                    String user = sc.nextLine();
                    System.out.print("Mat khau : ");
                    String pass = sc.nextLine();
                    TaiKhoan tk = dstk.checkLogin(user, pass);
                    if(tk != null) {
                        System.out.println("Dang nhap thanh cong!!!");
                        if(tk.getType().equals("nhan vien")) {
                            MenuNV();
                            chon = 0;
                        }
                        if(tk.getType().equals("quan ly")) {
                            MenuQL();
                            chon = 0;
                        }
                    }
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu");
                    break;
                }
        } while (chon!=0);
    }
    public static void MenuNV() {
        DanhSachXe dsx = new DanhSachXe();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachHoaDon dshd = new DanhSachHoaDon() ;
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Sieu Xe Version Nhan vien *****");
        do {
            System.out.println("==============================");
            System.out.println("1. Tao hoa don moi");
            System.out.println("2. In danh sach hoa don");
            System.out.println("3. Tao phieu nhap moi");
            System.out.println("4. In danh sach phieu nhap");
            System.out.println("5. Them khach hang moi");
            System.out.println("6. In danh sach khach hang");
            System.out.println("7. In danh sach xe");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
        chon = KiemTra.checkNumber();

        switch (chon) {
            case 0:
                System.out.println("Cam on ban da su dung chuong trinh");
                break;
            case 1:
                dshd.themKPhanTuVaoDanhSach();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                dskh.themKPhanTuVaoDanhSach();
                break;
            case 6:
                dskh.xuatDanhSach();
                break;
            case 7:
                dsx.xuatDanhSach();
                break;
            default:
                System.out.println("Hay nhap so co trong menu");
                break;
            }
        }while (chon!=0);
    }
    public static void MenuQL() {
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Sieu Xe Version Quan ly *****");
        do {
            System.out.println("==============================");
            System.out.println("1. Quan ly danh sach xe");
            System.out.println("2. Quan ly danh sach nha cung cap");
            System.out.println("3. Quan ly danh sach nhan vien");
            System.out.println("4. Quan ly danh sach khach hang");
            System.out.println("5. Quan ly danh sach tai khoan");
            System.out.println("6. Quan ly danh sach phieu nhap");
            System.out.println("7. Quan ly danh sach hoa don");
            System.out.println("2. Quan ly danh sach nhan vien");
            System.out.println("3. Quan ly danh sach khach hang");
            System.out.println("4. Quan ly danh sach tai khoan");
            System.out.println("5. Quan ly danh sach phieu nhap");
            System.out.println("6. Quan ly danh sach hoa don");
            System.out.println("7. Quan ly danh sach Dong Xe");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    quanLyDSX();
                    chon = 0;
                    break;
                case 2:
                    break;
                case 3:
                    quanLyDSNV();
                    chon = 0;
                    break;
                case 4:
                    quanLyDSKH();
                    chon = 0;
                    break;
                case 5:
                    quanLyDSTK();
                    chon = 0;
                    break;
                case 6:
                    break;
                case 7:
                    quanLyDSDanhMucSP();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu");
                    break;
            }
        } while (chon!=0);
    }
    private static void inMenu(String ten) {
        System.out.println("==============================");
        System.out.println("*** QUAN LY DANH SACH "+ten.toUpperCase()+" ***");
        System.out.println("1. Nhap moi danh sach");
        System.out.println("2. Xuat danh sach");
        System.out.println("3. Them "+ten+" vao danh sach");
        System.out.println("4. Chinh sua thong tin "+ten);
        System.out.println("5. Xoa "+ten);
        System.out.println("6. Tim "+ten);
        System.out.println("7. Thong ke");
        System.out.println("8. Tong so luong "+ten);
        System.out.println("0. Quay lai menu quan ly");
        System.out.println("==============================");
        System.out.print("Moi chon: ");
    }
    public static void quanLyDSX() {
        DanhSachXe ttds = new DanhSachXe();
        inMenu("xe");
        int chon;
        do {
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    ttds.nhapDanhSach();
                    break;
                case 2:
                    ttds.xuatDanhSach();
                    break;
                case 3:
                    ttds.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    ttds.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    ttds.xoaPhanTu();
                    break;
                case 6:
                    PhanTu pt = ttds.timPhanTu();
                    if (pt != null) {
                        System.out.println("** Thong tin tim thay **");
                        pt.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    ttds.thongKe();
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("xe");
        } while(chon!=0);
    }
    public static void quanLyDSNV() {
        DanhSachNhanVien ttds = new DanhSachNhanVien();
        inMenu("nhan vien");
        int chon;
        do {
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    ttds.nhapDanhSach();
                    break;
                case 2:
                    ttds.xuatDanhSach();
                    break;
                case 3:
                    ttds.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    ttds.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    ttds.xoaPhanTu();
                    break;
                case 6:
                    PhanTu pt = ttds.timPhanTu();
                    if (pt != null) {
                        System.out.println("** Thong tin tim thay **");
                        pt.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    ttds.thongKe();
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("nhan vien");
        } while(chon!=0);
    }
    public static void quanLyDSKH() {
        DanhSachKhachHang ttds = new DanhSachKhachHang();
        inMenu("khach hang");
        int chon;
        do {
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    ttds.nhapDanhSach();
                    break;
                case 2:
                    ttds.xuatDanhSach();
                    break;
                case 3:
                    ttds.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    ttds.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    ttds.xoaPhanTu();
                    break;
                case 6:
                    PhanTu pt = ttds.timPhanTu();
                    if (pt != null) {
                        System.out.println("** Thong tin tim thay **");
                        pt.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    ttds.thongKe();
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("khach hang");
        } while(chon!=0);
    }
    public static void quanLyDSTK() {
        DanhSachTaiKhoan ttds = new DanhSachTaiKhoan();
        inMenu("tai khoan");
        int chon;
        do {
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    ttds.nhapDanhSach();
                    break;
                case 2:
                    ttds.xuatDanhSach();
                    break;
                case 3:
                    ttds.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    ttds.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    ttds.xoaPhanTu();
                    break;
                case 6:
                    PhanTu pt = ttds.timPhanTu();
                    if (pt != null) {
                        System.out.println("** Thong tin tim thay **");
                        pt.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    ttds.thongKe();
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("tai khoan");
        } while(chon!=0);
    }
    public static void quanLyDSDanhMucSP() {
        DanhSachDongXe ttds = new DanhSachDongXe();
        DanhSachXe dssp = new DanhSachXe();
        inMenu("danh muc san pham");
        int chon;
        do {
            chon = Integer.parseInt(sc.nextLine());
            if (dssp.getsoLuong() == 0) {
                System.out.println("Vui long nhap danh sach san pham truoc khi quan ly danh muc!");
                chon = 0;
            }
            switch (chon) {
                case 1:
                    ttds.nhapDanhSach();
                    break;
                case 2:
                    ttds.xuatDanhSach();
                    break;
                case 3:
                    ttds.themKPhanTuVaoDanhSach();
                    break;
                case 4:
                    ttds.chinhSuaThongTinPhanTu();
                    break;
                case 5:
                    ttds.xoaPhanTu();
                    break;
                case 6:
                    PhanTu pt = ttds.timPhanTu();
                    if (pt != null) {
                        System.out.println("** Thong tin tim thay **");
                        pt.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 7:
                    ttds.thongKe();
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0)inMenu("danh muc san pham");
        } while(chon!=0);
    }
}
