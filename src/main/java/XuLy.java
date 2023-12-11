import DanhSach.*;
import Nguoi.NhanVien;
import Nguoi.TaiKhoan;
import SanPham.PhanTu;
import KiemTra.KiemTra;

import java.util.Scanner;
public class XuLy {
    private static Scanner sc = new Scanner(System.in);
    public static String username;
    public static void xuLyTrungTam() {
        int chon;
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
        System.out.println("************************************************************\n" +
                            "***          Chao mung ban den voi chuong trinh          ***\n" +
                            "***              QUAN LI CUA HANG SIEU XE                ***\n"+
                            "************************************************************");
        do {
            System.out.println("============================================================");
            System.out.println("***   Moi ban dang nhap vao chuong trinh!!! Ban la...    ***");
            System.out.println("***  1. Nhan vien                                        ***");
            System.out.println("***  2. Quan ly                                          ***");
            System.out.println("***  0. Thoat chuong trinh                               ***");
            System.out.println("============================================================");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    System.out.print("Tai khoan: ");
                    String user1 = sc.nextLine();
                    System.out.print("Mat khau:  ");
                    String pass1 = sc.nextLine();
                    TaiKhoan tk1 = dstk.checkLogin(user1, pass1);
                    if(tk1 != null) {
                        if(tk1.getType().equals("nhan vien")) {
                            System.out.println("Dang nhap thanh cong!!!");
                            username = user1;
                            MenuNV();
                            chon = 0;
                        }
                        else {
                            System.out.println("Ban dang dang nhap voi vai tro la nhan vien!!! Vui long su dung tai khoan nhan vien.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Tai khoan: ");
                    String user = sc.nextLine();
                    System.out.print("Mat khau:  ");
                    String pass = sc.nextLine();
                    TaiKhoan tk = dstk.checkLogin(user, pass);
                    if(tk != null) {
                        if(tk.getType().equals("quan ly")) {
                            System.out.println("Dang nhap thanh cong!!!");
                            username = user;
                            MenuQL();
                            chon = 0;
                        }
                        else {
                            System.out.println("Ban dang dang nhap voi vai tro la quan ly!!! Vui long su dung tai khoan quan ly.");
                        }
                    }
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
                }
        } while (chon!=0);
    }
    public static void MenuNV() {
        DanhSachXe dsx = new DanhSachXe();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        DanhSachHoaDon dshd = new DanhSachHoaDon() ;
        DanhSachPhieuNhap dspn = new DanhSachPhieuNhap();
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
        DanhSachDongXe dsdx = new DanhSachDongXe();
        NhanVien nv = (NhanVien) dsnv.layPhanTuVoi(username);
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Sieu Xe Version Nhan vien *****");
        do {
            System.out.println("==============================");
            System.out.println("Xin chao " + nv.getHoten());
            System.out.println("1.  Xem thong tin ca nhan");
            System.out.println("2.  Sua thong tin ca nhan");
            System.out.println("3.  Tao hoa don moi");
            System.out.println("4.  In danh sach hoa don");
            System.out.println("5.  In file hoa don");
            System.out.println("6.  Tao phieu nhap moi");
            System.out.println("7.  In danh sach phieu nhap");
            System.out.println("8.  In file phieu nhap");
            System.out.println("9.  Them khach hang moi");
            System.out.println("10. In danh sach khach hang");
            System.out.println("11. In danh sach xe");
            System.out.println("12. Xem bao hanh voi ma khach hang");
            System.out.println("13. Thay doi mat khau tai khoan");
            System.out.println("0.  Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    nv.xuat();
                    break;
                case 2:
                    dsnv.chinhSuaThongTinPhanTu(username);
                    nv = (NhanVien) dsnv.layPhanTuVoi(username);
                    break;
                case 3:
                    dshd.themPhanTuVaoDanhSach(username);
                    break;
                case 4:
                    dshd.xuatDanhSach();
                    break;
                case 5:
                    dshd.inhoadon();
                    break;
                case 6:
                    dspn.themPhanTuVaoDanhSach(username);
                    break;
                case 7:
                    dspn.xuatDanhSach();
                    break;
                case 8:
                    dspn.inphieunhap();
                    break;
                case 9:
                    dskh.themKPhanTuVaoDanhSach();
                    break;
                case 10:
                    dskh.xuatDanhSach();
                    break;
                case 11:
                    dsx.xuatDanhSach();
                    break;
                case 12:
                    dsdx.Checkbaohanh();
                    break;
                case 13:
                    dstk.changePasswordds(username);
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
                    break;
                }
            }while (chon!=0);
    }
    public static void MenuQL() {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        NhanVien nv = (NhanVien) dsnv.layPhanTuVoi(username);
        DanhSachDongXe dsdx = new DanhSachDongXe();
        DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Sieu Xe Version Quan ly *****");
        do {
            System.out.println("==============================");
            System.out.println("Xin chao " + nv.getHoten());
            System.out.println("1.  Xem thong tin tai khoan");
            System.out.println("2.  Sua thong tin tai khoan");
            System.out.println("3.  Quan ly danh sach xe");
            System.out.println("4.  Quan ly danh sach dong xe");
            System.out.println("5.  Quan ly danh sach nha cung cap");
            System.out.println("6.  Quan ly danh sach nhan vien");
            System.out.println("7.  Quan ly danh sach khach hang");
            System.out.println("8.  Quan ly danh sach tai khoan");
            System.out.println("9.  Quan ly danh sach hoa don");
            System.out.println("10. Quan ly danh sach phieu nhap");
            System.out.println("11. Xem thoi han bao hanh voi ma khach hang");
            System.out.println("12. Thay doi mat khau tai khoan");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                case 1:
                    nv.xuat();
                    break;
                case 2:
                    dsnv.chinhSuaThongTinPhanTu(username);
                    nv = (NhanVien) dsnv.layPhanTuVoi(username);
                    break;
                case 3:
                    quanLyDSX();
                    chon = 0;
                    break;
                case 4:
                    quanLyDSDX();
                    break;
                case 5:
                    quanLyDSNCC();
                    break;
                case 6:
                    quanLyDSNV();
                    chon = 0;
                    break;
                case 7:
                    quanLyDSKH();
                    chon = 0;
                    break;
                case 8:
                    quanLyDSTK();
                    chon = 0;
                    break;
                case 9:
                    quanLyDSHD();
                    break;
                case 10:
                    quanLyDSPN();
                    break;
                case 11:
                    dsdx.Checkbaohanh();
                    break;
                case 12:
                    dstk.changePasswordds(username);
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu!!!");
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
    private static void inMenuDX(String ten) {
        System.out.println("==============================");
        System.out.println("*** QUAN LY DANH SACH "+ten.toUpperCase()+" ***");
        System.out.println("1. Xuat danh sach");
        System.out.println("2. Tim "+ten);
        System.out.println("3. Thong ke");
        System.out.println("4. Tong so luong "+ten);
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
                case 8:
                    System.out.println("So luong: " + ttds.getsoLuong());
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
                case 8:
                    System.out.println("So luong: " + ttds.getSoLuong());
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
                case 8:
                    System.out.println("So luong: " + ttds.getSoLuong());
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
                case 8:
                    System.out.println("So luong: " + ttds.getSoLuong());
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("tai khoan");
        } while(chon!=0);
    }
    public static void quanLyDSDX() {
        DanhSachDongXe ttds = new DanhSachDongXe();
        DanhSachXe dsx = new DanhSachXe();
        inMenuDX("dong xe");
        int chon;
        do {
            chon = KiemTra.checkNumber();
            if (dsx.getsoLuong() == 0 && dsx.getsoLuong() == 1)  {
                System.out.println("Vui long nhap danh sach xe truoc khi quan ly dong xe!");
                chon = 0;
            }
            switch (chon) {
                case 1:
                    ttds.xuatDanhSach();
                    break;
                case 2:
                    PhanTu pt = ttds.timPhanTu();
                    if (pt != null) {
                        System.out.println("** Thong tin tim thay **");
                        pt.xuat();
                    } else System.out.println("Khong tim thay!");
                    break;
                case 3:
                    ttds.thongKe();
                    break;
                case 4:
                    System.out.println("So luong: " + ttds.getSoLuong());
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0)inMenuDX("dong xe");
        } while(chon!=0);
    }
    public static void quanLyDSHD() {
        DanhSachHoaDon ttds = new DanhSachHoaDon();
        DanhSachXe dsx = new DanhSachXe();
        inMenu("hoa don");
        int chon;
        do {
            chon = KiemTra.checkNumber();
            // chỉ cho phép thao tác nếu đã có danh sách sản phẩm
            if (dsx.getsoLuong() == 0) {
                System.out.println("Vui long nhap danh sach xe truoc khi quan ly hoa don!");
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
                case 8:
                    System.out.println("So luong: " + ttds.getsoLuong());
                    break;
                default:
                    chon = 0;
                    if (chon==0) MenuQL();
                    break;
            }
            if (chon!=0)inMenu("hoa don");
        } while(chon!=0);
    }

    public static void quanLyDSPN() {
        DanhSachPhieuNhap  ttds = new DanhSachPhieuNhap();
        inMenu("phieu nhap");
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
                case 8:
                    System.out.println("So luong: " + ttds.getsoLuong());
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("phieu nhap");
        } while(chon!=0);
    }
    public static void quanLyDSNCC() {
        DanhSachNhaCungCap ttds = new DanhSachNhaCungCap();
        inMenu("nha cung cap ");
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
                case 8:
                    System.out.println("So luong: " + ttds.getSoLuong());
                    break;
                default:
                    if (chon==0) MenuQL();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("nha cung cap");
        } while(chon!=0);
    }
}
