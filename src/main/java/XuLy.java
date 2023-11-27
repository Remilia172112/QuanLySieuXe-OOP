import DanhSach.*;
import HangHoa.PhanTu;
import Nguoi.TaiKhoan;
import KiemTra.KiemTra;

import java.util.Scanner;
public class XuLy {
    private static Scanner sc = new Scanner(System.in);

    public static void xuLyTrungTam() {
        int chon;
        boolean check_break = false;
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
                case 1:
                    System.out.print("Tai khoan:");
                    String user = sc.nextLine();
                    System.out.print("Mat khau: ");
                    String pass = sc.nextLine();
                    TaiKhoan tk = dstk.checkLogin(user, pass);
                    if(tk != null) {
                        System.out.println("Dang nhap thanh cong!!!");
                        if(tk.getType().equals("nhan vien")) {
                            MenuNV();
                            check_break = true;
                        }
                        if(tk.getType().equals("quan ly")) {
                            MenuQL();
                            check_break = true;
                        }
                    }
                    break;
                default:
                    chon = 0;
                    System.out.println("Cam on ban da su dung chuong trinh");
                    break;
                }
            if(check_break) break;
        } while (chon!=0);
    }
    public static void MenuNV() {
        DanhSachXe dsdmsp = new DanhSachXe();
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Sieu Xe Version Nhan vien *****");
        do {
            System.out.println("==============================");
            System.out.println("1. Tao hoa don moi");
            System.out.println("2. Them khach hang moi");
            System.out.println("3. Them nha cung cap moi");
            System.out.println("4. In danh sach xe");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
        chon = Integer.parseInt(sc.nextLine());

        switch (chon) {
            case 1:
                //dshd.themKPhanTuVaoDanhSach();
                break;
            case 2:
                //dskh.themKPhanTuVaoDanhSach();
                break;
            case 3:
                //dsnv.themKPhanTuVaoDanhSach();
                break;
            case 4:
                dsdmsp.xuatDanhSach();
                break;
            case 5:
                break;
            default:
                chon = 0;
                System.out.println("Cam on ban da su dung chuong trinh");
                break;
            }
            if (chon == 5) break;
        }while (chon!=0);
    }
    public static void MenuQL() {
        DanhSachXe dsdmsp = new DanhSachXe();
        int chon;
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Sieu Xe Version Quan ly *****");
        do {
            System.out.println("==============================");
            System.out.println("1. Tao hoa don moi");
            System.out.println("2. Them khach hang moi");
            System.out.println("3. Them nhan vien moi");
            System.out.println("4. Them nha cung cap moi");
            System.out.println("5. In danh sach xe");
            System.out.println("6. Di toi menu quan ly");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("==============================");
            System.out.print("Moi chon: ");
        chon = Integer.parseInt(sc.nextLine());

        switch (chon) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                quanLy();
                break;
            default:
                chon = 0;
                System.out.println("Cam on ban da su dung chuong trinh");
                break;
            }
            if (chon == 5) break;
        }while (chon!=0);
    }
    public static void quanLy() {
        System.out.println("***** Chuong Trinh Quan Ly Cua Hang Van Phong Pham *****");
        System.out.println("==============================");
        System.out.println("1. Quan ly danh sach xe");
        System.out.println("2. Quan ly danh sach nhan vien");
        System.out.println("3. Quan ly danh sach khach hang");
        System.out.println("4. Quan ly danh sach tai khoan");
        System.out.println("5. Quan ly danh sach phieu nhap");
        System.out.println("6. Quan ly danh sach hoa don");
        System.out.println("0. Quay lai menu trung tam");
        System.out.println("==============================");
        System.out.print("Moi chon: ");
        int chon;
        chon = Integer.parseInt(sc.nextLine());
        switch (chon) {
            case 1:
                quanLyDSX();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                quanLyDSTK();
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                xuLyTrungTam();
                break;
        }
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
        inMenu("Xe");
        int chon;
        do {
            chon = Integer.parseInt(sc.nextLine());
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
                    if (chon==0) quanLy();
                    chon = 0;
                    break;
            }
            if (chon!=0)inMenu("san pham");
        } while(chon!=0);
    }
    public static void quanLyDSTK() {
        DanhSachTaiKhoan ttds = new DanhSachTaiKhoan();
        inMenu("Tai Khoan");
        int chon;
        do {
            chon = Integer.parseInt(sc.nextLine());
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
                    if (chon==0) quanLy();
                    chon = 0;
                    break;
            }
            if (chon!=0) inMenu("Tai Khoan");
        } while(chon!=0);
    }
}
