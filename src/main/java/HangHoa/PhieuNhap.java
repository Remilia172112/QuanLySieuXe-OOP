package HangHoa;

import DanhSach.*;
import KiemTra.KiemTra;
// import Nguoi.NhanVien;

public class PhieuNhap extends PhanTu {
    private String maPhieuNhap;
    private String maNhaCC;
    private String maNV;
    private String ngaynhap;
    private int tongnhap;
    public PhieuNhap(){
    }
    
    public PhieuNhap(String maPhieuNhap , String maNhaCC , String maNV , String ngaynhap, int tongnhap){
        this.maPhieuNhap = maPhieuNhap;
        this.maNhaCC = maNhaCC;
        this.maNV = maNV;
        this.ngaynhap = ngaynhap;
        this.tongnhap = tongnhap; 
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
        DanhSachPhieuNhap ttds = new DanhSachPhieuNhap();
        boolean check = false;
        do
        {
            maNhaCC = sc.nextLine();
            check = ttds.layPhanTuVoi(maNhaCC) == null; // kiểm tra mã xem đã tồn tại trong danh sách chưa
            if (check) System.out.print("Ma nha cung cap ko ton tai, moi nhap lai: ");
        } while (!check);
    }
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public void setMaNV() {
        DanhSachNhanVien dsncc = new DanhSachNhanVien();       
        dsncc.xuatDanhSach();
        System.out.print("Nhap ma nhan vien lap phieu nhap: ");
        DanhSachPhieuNhap ttds = new DanhSachPhieuNhap();
        boolean check = false;
        do
        {
            maNV = sc.nextLine();
            check = ttds.layPhanTuVoi(maNV) == null; // kiểm tra mã xem đã tồn tại trong danh sách chưa
            if (check) System.out.print("Ma nhan vien ko ton tai, moi nhap lai: ");
        } while (!check);
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
            check = KiemTra.check_date(ngaynhap) ; // kiểm tra ngay
        } while (!check);
    }


    public int getTongnhap() {
        return tongnhap;
    }

    public void setTongnhap(int tongnhap) {
        this.tongnhap = tongnhap;
    }
    public void setTongnhap() {
        System.out.print("Nhap so loai xe can nhap: ");
        DanhSachPhieuNhap ttds = new DanhSachPhieuNhap();
        boolean check = false;
        do
        {
            maNV = sc.nextLine();
            check = ttds.layPhanTuVoi(maNV) == null; // kiểm tra mã xem đã tồn tại trong danh sách chưa
            if (check) System.out.print("Ma nhan vien ko ton tai, moi nhap lai: ");
        } while (!check);
    }


    @Override
    public void nhap(){
        setMaPhieuNhap();
        setMaNhaCC();
        setMaNV();
        setNgaynhap();
        setTongnhap();
    }
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s  \n",maPhieuNhap, maNhaCC , maNV , ngaynhap, tongnhap );
    }
    public void suaThongTin() {
        System.out.println("=== Sua thong tin phieu nhap ===");
        System.out.println("1. Sua ma phieu nhap");
        System.out.println("2. Sua ma nha cung cap");
        System.out.println("3. Sua ma nhan vien lap phieu nhap");
        System.out.println("4. Sua ngay lap phieu nhap");
        System.out.println("5. Sua tong loai xe nhap");
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
