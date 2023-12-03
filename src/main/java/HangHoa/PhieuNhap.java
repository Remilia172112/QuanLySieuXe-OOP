package HangHoa;


import java.time.LocalDate;

import DanhSach.*;
import KiemTra.KiemTra;
//mỗi lần nhập 1 loại sp thoi 
public class PhieuNhap extends PhanTu {
    // Tạo tháng năm hiện tại
    LocalDate localDate = LocalDate.now();
    private int namhientai = localDate.getYear();
    private int thanghientai = localDate.getMonthValue();
    private int ngayhientai = localDate.getDayOfMonth();

    private String maPhieuNhap;
    private String maNhaCC;
    private String maNV;
    private String ngaynhap;
    private Xe[] dsmspNhap;
    private int SoluongNhap;
    private int tongTien = 0;

    public PhieuNhap(){
    }

    public PhieuNhap(String maPhieuNhap , String maNhaCC , String maNV , String ngaynhap, Xe[] dsmspNhap, int SoluongNhap, int tongTien){
        this.maPhieuNhap = maPhieuNhap;
        this.maNhaCC = maNhaCC;
        this.maNV = maNV;
        this.ngaynhap = ngaynhap;
        this.dsmspNhap = dsmspNhap;
        this.SoluongNhap = SoluongNhap;
        this.tongTien = tongTien;
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
            check = true;
            maPhieuNhap = sc.nextLine();
            check = (ttds.layPhanTuVoi(maPhieuNhap) == null); // kiểm tra mã xem đã tồn tại trong danh sách chưa
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
        System.out.println("Ban co muon xuat ra man hinh danh sach nha cung cap khong? (1 - in, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if(chon == 1) {
            dsncc.xuatDanhSach();
        }
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
        // sc.nextLine();
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
        System.out.print("Sua dung ngay thang nam hien tai de lam ngay lap don? (1/0): ");
        int chon = KiemTra.checkNumber();
        if(chon == 1) ngaynhap = ngayhientai + "/" + thanghientai + "/" + namhientai;
        else {
            boolean check;
            System.out.print("Nhap ngay lap don (dd/mm/yyyy): ");
            do
            {
                check = true;
                ngaynhap = sc.nextLine();
                check = KiemTra.isValidDate(ngaynhap);
                if(!check) System.out.print("Nhap sai dinh dang ngay!! Moi nhap lai: ");
            } while (!check);
        }
    }

    public Xe[] getDsSanPham() {
        return dsmspNhap;
    }

    public void setDsSanPham(Xe[] dsmspNhap) {
        this.dsmspNhap = dsmspNhap;
    }

    public void setDsSanPham() {
        // Khai báo
        DanhSachXe ttds = new DanhSachXe();
        DanhSachDongXe dsdx = new DanhSachDongXe();
        
        Xe[] dsspFile = ttds.getdsSanPham();
        Xe[] dssp = new Xe[SoluongNhap];
        
        Xe pt, timThay;
        int vtsp, stt, chon;
        // xem lại danh mục sản phẩm (tuỳ chọn)
        System.out.print("Ban co muon xem lai danh sach xe? (1 - in, 0 - khong): ");
        chon = KiemTra.checkNumber();
        if (chon==1) dsdx.xuatDanhSach();
        
        for(int i=0;i<SoluongNhap;i++) {
            stt=i+1;
            System.out.println("Nhap xe thu "+stt);
            
            do {
                System.out.print("Nhap ma xe: ");
                pt = (Xe) ttds.layPhanTuVoi(sc.nextLine());
                
                if (pt == null) 
                    System.out.println("Khong tim thay xe!");
                else {
                    dssp[i] = pt;
                    
                    // tìm sản phẩm trong danh sách sp với mã sản phẩm
                    timThay = (Xe) ttds.layPhanTuVoi(pt.getMaSanPham());
                    
                    pt.setSoLuong();
                    
                    // tăng số lượng sản phẩm trong danh sách vì đã thêm sản phẩm vào hoá đơn.
                    timThay.setSoLuong(timThay.getSoLuong()+pt.getSoLuong());
                    
                    // tìm vị trí sản phẩm đã nhập trong danh sách
                    vtsp = ttds.timViTriSanPham(pt.getMaSanPham());
                    
                    // cập nhật lại số lượng sản phẩm
                    dsspFile[vtsp] = timThay;
                    ttds.setdsSanPham(dsspFile);
                    
                    // cập nhật tổng tiền
                    tongTien += pt.getPrice() * pt.getSoLuong();
                }
            } while (pt == null);
        }
        
        dsmspNhap = dssp;
    }

    public int getSoluongNhap() {
        return SoluongNhap;
    }

    public void setSoluongNhap(int SoluongNhap) {
        this.SoluongNhap = SoluongNhap;
    }
    public void setSoluongNhap() {
        System.out.print("Nhap so xe can nhap: ");
        SoluongNhap = KiemTra.checkNumber();
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
    // Nhập version nhân viên
    public void nhap(String username){
        setMaPhieuNhap();
        setMaNhaCC();
        setMaNV(username);
        setNgaynhap();
        setSoluongNhap();
        setDsSanPham();
    }

    @Override
    public void nhap(){
        setMaPhieuNhap();
        setMaNhaCC();
        setMaNV();
        setNgaynhap();
        setSoluongNhap();
        setDsSanPham();
    }
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-20s  \n", maPhieuNhap, maNhaCC , maNV , ngaynhap);
        System.out.println("\nDanh sach san pham: \n");
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        for(int i = 0; i< dsmspNhap.length; i++) dsmspNhap[i].xuat();
        System.out.println("\n**************************");
    }
    public void suaThongTin() {
        System.out.println("=== Sua thong tin phieu nhap ===");
        System.out.println("1. Sua ma phieu nhap");
        System.out.println("2. Sua ma nha cung cap");
        System.out.println("3. Sua ma nhan vien lap phieu nhap");
        System.out.println("4. Sua ngay lap phieu nhap");
        System.out.println("5. Sua danh sach ma xe");
        System.out.println("6. Sua so luong xe nhap");
        System.out.println("0. Quay ve menu quan ly xe");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
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
                    System.out.println("Thong tin hien tai: ");
                    // xuất danh sách sản phẩm
                    Xe[] dsx = (Xe[]) getDsSanPham();
                    for(int i=0;i<dsx.length;i++)
                        dsx[i].xuat();
                    
                    System.out.println("Nhap moi danh sach san pham: ");
                    setSoluongNhap();
                    setDsSanPham();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: "+getSoluongNhap());
                    setSoluongNhap();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }
}
