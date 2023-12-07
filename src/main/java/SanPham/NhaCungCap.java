package SanPham;

import DanhSach.DanhSachNhaCungCap;
import KiemTra.KiemTra;

public class NhaCungCap extends PhanTu {
    private String maNhaCC ;
    private String tenNhaCC ;
    private String diachi ;
    private String sdt ;

    public NhaCungCap(){
    }

    public NhaCungCap(String maNhaCC , String tenNhaCC , String diachi , String sdt){
        this.maNhaCC = maNhaCC ;
        this.tenNhaCC = tenNhaCC;
        this.diachi = diachi ;
        this.sdt = sdt;
    }
    public void setMaNhaCC(String a) {
        maNhaCC = a;
    }
    
    public void setMaNhaCC() {
        System.out.print("Nhap ma nha cung cap: ");
        DanhSachNhaCungCap ttds = new DanhSachNhaCungCap();
        boolean check = false;
        do
        {
            maNhaCC = sc.nextLine();
            check = ttds.layPhanTuVoi(maNhaCC) == null; // kiểm tra mã nha cung cap xem đã tồn tại trong danh sách chưa
            if (!check) System.out.print("Ma nha cung cap da ton tai, moi nhap lai: ");
        } while (!check);
    }
    public void setTenNhaCC() {
        System.out.print("Nhap ten nha cung cap: ");
        tenNhaCC = sc.nextLine();
    }
    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }

    public void setDiaChi(String diachi) {
        this.diachi = diachi;
    }
    public void setDiaChi() {
        System.out.print("Nhap dia chi nha cung cap: ");
        diachi = sc.nextLine();
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public void setSdt() {
        System.out.print("Nhap so dien thoai nha cung cap: ");
        boolean check = false;
        do {
            sdt = sc.nextLine();
            check = KiemTra.check_sdt(sdt);
        } while (!check);
    }

    public String getMaNhaCC() {
        return maNhaCC;
    }
    public String getTenNhaCC() {
        return tenNhaCC;
    }
    public String getDiaChi() {
        return diachi;
    }
    public String getSdt() {
        return sdt;
    }

    @Override
    public void nhap(){
        setMaNhaCC();
        setTenNhaCC();
        setDiaChi();
        setSdt();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-45s %-20s  \n",getMaNhaCC(),getTenNhaCC(),getDiaChi(),getSdt());

    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin nha cung cap ===");
            System.out.println("1. Sua ma nha cung cap");
            System.out.println("2. Sua ten nha cung cap");
            System.out.println("3. Sua dia chi nha cung cap");
            System.out.println("4. Sua so dien thoai nha cung cap");
            System.out.println("0. Quay ve menu quan ly xe");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin nha cung cap!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getMaNhaCC());
                    setMaNhaCC();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getTenNhaCC());
                    setTenNhaCC();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: "+getDiaChi());
                    setDiaChi();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getSdt());
                    setSdt();
                    break;
                
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }
}
