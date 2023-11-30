package HangHoa;

import java.io.IOException;

import DanhSach.*;
import KiemTra.KiemTra;
//mỗi lần nhập 1 loại sp thoi 
public class PhieuNhap extends PhanTu {
    private String maPhieuNhap;
    private String maNhaCC;
    private String maNV;
    private String ngaynhap;
    private String maSanPham;
    private int Tongnhap;


    public static void main(String[] args) throws IOException {
        PhieuNhap pn = new PhieuNhap();
        pn.menu();
        
    }
    public void menu() {
        boolean check = true;
        while (check) {
            System.out.println("");
            System.out.println("+------------------------------+");
            System.out.println("Menu: ");
            System.out.println("1. set ma phieu nhap ");
            System.out.println("2. set nha cung cap");
            System.out.println("3. set ma nhan vien ");
            System.out.println("4. set ma san pham ");
            System.out.println("5. set tong nhap ");
            System.out.println("6. set ngaynhap");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int chose = sc.nextInt();
            System.out.println("+------------------------------+");
            switch (chose) {
                case 1:
                    setMaPhieuNhap();
                    break;
                case 2:
                    setMaNhaCC();
                    break;
                case 3:
                    setMaNV();
                    break;
                case 4:
                    setMaSanPham();
                    break;
                case 5:
                    setMaSanPham();
                    break;
                case 6:
                    setNgaynhap();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh thanh cong!!!");
                    check = false;
                    return;
                default:
                    System.out.println("Nhap sai");
            }
        }
    }


    public PhieuNhap(){
    }

    public PhieuNhap(String maPhieuNhap , String maNhaCC , String maNV , String ngaynhap, String maSanPham, int Tongnhap){
        this.maPhieuNhap = maPhieuNhap;
        this.maNhaCC = maNhaCC;
        this.maNV = maNV;
        this.ngaynhap = ngaynhap;
        this.maSanPham = maSanPham;
        this.Tongnhap = Tongnhap;
    }


    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }
    public void setMaPhieuNhap() {
        System.out.print("Nhap ma phieu nhap: ");
        DanhSachPhieuNhap ttds = new DanhSachPhieuNhap();
        boolean check = false;
        do
        {
            maPhieuNhap = sc.nextLine();
            check = ttds.layPhanTuVoi(maPhieuNhap) == null; // kiểm tra mã xem đã tồn tại trong danh sách chưa
            if (!check) System.out.print("Ma phieu nhap da ton tai, moi nhap lai: ");
        } while (!check);
    }


    public String getMaNhaCC() {
        return maNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }
    public void setMaNhaCC() {
        DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
        dsncc.xuatDanhSach();
        System.out.print("Nhap ma nha cung cap: ");
        boolean check = true;
        do
        {
            maNhaCC = sc.nextLine();
            check = dsncc.layPhanTuVoi(maNhaCC) == null; // kiểm tra mã xem đã tồn tại trong danh sách chưa
            if (check) System.out.print("Ma nha cung cap ko ton tai, moi nhap lai: ");
        } while (check);
    }


    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public void setMaNV() {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        dsnv.xuatDanhSach();
        System.out.print("Nhap ma nhan vien lap phieu nhap: ");
        boolean check = true;
        sc.nextLine();
        do
        {
            
            maNV = sc.nextLine();
            check = (dsnv.layPhanTuVoi(maNV) == null); // kiểm tra mã xem đã tồn tại trong danh sách chưa
            if (check) System.out.print("Ma nhan vien ko ton tai, moi nhap lai: ");
        } while (check);
    }


    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }
    public void setNgaynhap() {
        System.out.print("Nhap ngay lap phieu nhap: ");
        boolean check = false;
        do
        {
            ngaynhap = sc.nextLine();
            check = KiemTra.check_date(ngaynhap) ; // kiểm tra ngày
        } while (!check);
    }

    public String getMaSanPham() {
        return maSanPham;
    }
    public void setMaSanPham() {
        System.out.print("Nhap ma san pham: ");
        DanhSachXe ttds = new DanhSachXe();
        boolean check = true;
        do
        {
            maSanPham = sc.nextLine();
            check = ttds.layPhanTuVoi(maSanPham) == null; // kiểm tra mã sản phẩm xem đã tồn tại trong danh sách chưa
            if (check) System.out.print("Ma san pham da ton tai, moi nhap lai: ");
        } while (check);
    }
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }


    public int getTongnhap() {
        return Tongnhap;
    }

    public void setTongnhap(int Tongnhap) {
        this.Tongnhap = Tongnhap;
    }
    public void setTongnhap() {
        System.out.print("Nhap so xe can nhap: ");
        Tongnhap = KiemTra.checkNumber();
    }


    @Override
    public void nhap(){
        setMaPhieuNhap();
        setMaNhaCC();
        setMaNV();
        setNgaynhap();
        setMaSanPham();
        setTongnhap();
    }
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s  \n",maPhieuNhap, maNhaCC , maNV , ngaynhap, maSanPham, Tongnhap );
    }
    public void suaThongTin() {
        System.out.println("=== Sua thong tin phieu nhap ===");
        System.out.println("1. Sua ma phieu nhap");
        System.out.println("2. Sua ma nha cung cap");
        System.out.println("3. Sua ma nhan vien lap phieu nhap");
        System.out.println("4. Sua ngay lap phieu nhap");
        System.out.println("5. Sua ma san pham can nhap");
        System.out.println("6. Sua tong loai xe nhap");
        System.out.println("0. Quay ve menu quan ly san pham");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 0:
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getMaPhieuNhap());
                    setMaPhieuNhap();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getMaNhaCC());
                    setMaNhaCC();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: "+getMaNV());
                    setMaNV();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getNgaynhap());
                    setNgaynhap();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: "+getMaSanPham());
                    setMaSanPham();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: "+getTongnhap());
                    setTongnhap();
                    break;

                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }
}
