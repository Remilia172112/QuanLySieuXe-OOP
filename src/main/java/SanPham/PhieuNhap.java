package SanPham;

import java.util.Arrays;

import DanhSach.*;
import KiemTra.KiemTra;

public class PhieuNhap extends PhanTu {
    private int maPhieuNhap;
    private String maNhaCC;
    private String maNV;
    private String ngaynhap;
    private Xe[] dsmspNhap;
    private int soLoaiNhap;
    private int tongTien = 0;

    public PhieuNhap(){
    }

    public PhieuNhap(int maPhieuNhap , String maNhaCC , String maNV , String ngaynhap, Xe[] dsmspNhap, int soLoaiNhap, int tongTien){
        this.maPhieuNhap = maPhieuNhap;
        this.maNhaCC = maNhaCC;
        this.maNV = maNV;
        this.ngaynhap = ngaynhap;
        this.dsmspNhap = dsmspNhap;
        this.soLoaiNhap = soLoaiNhap;
        this.tongTien = tongTien;
    }


    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }
    public void setMaPhieuNhap() {
        DanhSachPhieuNhap ttds = new DanhSachPhieuNhap();
        PhieuNhap[] dspn = ttds.getdsPhieuNhap();
        if(dspn.length != 0) maPhieuNhap = dspn[ttds.getsoLuong()-1].getMaPhieuNhap() + 1;
        else maPhieuNhap = 1;
        System.out.println("So phieu nhap: " + maPhieuNhap);
        // System.out.print("Nhap ma phieu nhap: ");
        // boolean check = false;
        // do
        // {
        //     check = true;
        //     maPhieuNhap = sc.nextLine();
        //     check = (ttds.layPhanTuVoi(maPhieuNhap) == null); // kiểm tra mã xem đã tồn tại trong danh sách chưa
        //     if (!check) System.out.print("Ma phieu nhap da ton tai, moi nhap lai: ");
        // } while (!check);
    }


    public String getMaNhaCC() {
        return maNhaCC;
    }

    public void setMaNhaCC(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }
    public void setMaNhaCC() {
        DanhSachNhaCungCap dsncc = new DanhSachNhaCungCap();
        System.out.print("Ban co muon xuat ra man hinh danh sach nha cung cap khong? (1 - in, 0 - khong): ");
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
        System.out.print("Sua dung ngay thang nam hien tai de lam ngay lap don? (1 - co, 0 - khong): ");
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

    public Xe[] getDsXe() {
        return dsmspNhap;
    }

    public void setDsXe(Xe[] dsmspNhap) {
        this.dsmspNhap = dsmspNhap;
    }

    public void resetDsXe() {
        // Khai báo
        DanhSachXe ttds = new DanhSachXe();
        
        Xe[] dsspFile = ttds.getdsXe();
        
        Xe timThay;
        if (dsmspNhap.length > 0) { // nếu danh sách sản phẩm > 0
            int viTriCanChinhSua;
            for(Xe x: dsmspNhap) // ứng với từng phần tử
            {
                // tìm sản phẩm trong danh sách với mã sản phẩm
                timThay = (Xe) ttds.layPhanTuVoi(x.getMaXe());
                
                // giảm số lượng sản phẩm trong danh sách vì xoá sản phẩm khỏi hoá đơn
                timThay.setSoLuong(timThay.getSoLuong()-x.getSoLuong());
                
                // tìm vị trí sản phẩm cần chỉnh sửa trong danh sách
                viTriCanChinhSua = ttds.timViTriXe(x.getMaXe());
                dsspFile[viTriCanChinhSua] = timThay;
                // cập nhật lại danh sách
                ttds.setdsXe(dsspFile);
            }
        }
    }
    
    public void setDsXe() {
        // Khai báo
        DanhSachXe ttds = new DanhSachXe();
        DanhSachDongXe dsdx = new DanhSachDongXe();
        
        Xe[] dsspFile = ttds.getdsXe();
        Xe[] dssp = new Xe[soLoaiNhap];
        String[] madachon = new String[0];
        
        Xe pt, timThay;
        int vtsp, stt, chon;
        // xem lại danh mục sản phẩm (tuỳ chọn)
        System.out.print("Ban co muon xem lai danh sach xe? (1 - in, 0 - khong): ");
        chon = KiemTra.checkNumber();
        if (chon==1) dsdx.xuatDanhSach();
        
        for(int i=0;i<soLoaiNhap;i++) {
            stt=i+1;
            System.out.println("Nhap xe thu "+stt);
            
            do {
                System.out.print("Nhap ma xe: ");
                pt = (Xe) ttds.layPhanTuVoi(sc.nextLine());
                
                if (pt == null) 
                    System.out.println("Khong tim thay xe!");
                else {
                    dssp[i] = pt;

                    boolean check = false;
                    for(int k=0; k < madachon.length; k++) {
                        if(madachon[k].equals(pt.getMaXe())) {
                            System.out.println("Xe da co trong phieu nhap, vui long chon xe khac!!!");
                            pt=null;
                            check = true;
                            break;
                        }
                    }
                    if(check) continue;

                    madachon = Arrays.copyOf(madachon, madachon.length+1);
                    madachon[i] = pt.getMaXe();
                    
                    // tìm sản phẩm trong danh sách sp với mã sản phẩm
                    timThay = (Xe) ttds.layPhanTuVoi(pt.getMaXe());
                    
                    pt.setSoLuong();
                    
                    // tăng số lượng sản phẩm trong danh sách vì đã thêm sản phẩm vào hoá đơn.
                    timThay.setSoLuong(timThay.getSoLuong()+pt.getSoLuong());
                    
                    // tìm vị trí sản phẩm đã nhập trong danh sách
                    vtsp = ttds.timViTriXe(pt.getMaXe());
                    
                    // cập nhật lại số lượng sản phẩm
                    dsspFile[vtsp] = timThay;
                    
                    // cập nhật tổng tiền
                    tongTien += pt.getPrice() * pt.getSoLuong();
                }
            } while (pt == null);
        }
        // Cập nhật lên file
        ttds.setdsXe(dsspFile);
        // Nếu là chỉnh sửa danh sách sản phẩm
        if (dsmspNhap != null) {
            if (dsmspNhap.length > 0) { // nếu danh sách sản phẩm > 0
                int viTriCanChinhSua;
                for(Xe x: dsmspNhap) // ứng với từng phần tử
                {
                    // tìm sản phẩm trong danh sách với mã sản phẩm
                    timThay = (Xe) ttds.layPhanTuVoi(x.getMaXe());
                    
                    // giảm số lượng sản phẩm trong danh sách vì xoá sản phẩm khỏi hoá đơn
                    timThay.setSoLuong(timThay.getSoLuong()-x.getSoLuong());
                    
                    // tìm vị trí sản phẩm cần chỉnh sửa trong danh sách
                    viTriCanChinhSua = ttds.timViTriXe(x.getMaXe());
                    dsspFile[viTriCanChinhSua] = timThay;

                    // cập nhật lại danh sách
                    ttds.setdsXe(dsspFile);
                }
            }
        }
        dsmspNhap = dssp;
    }

    public int getSoLoaiNhap() {
        return soLoaiNhap;
    }

    public void setSoLoaiNhap(int soLoaiNhap) {
        this.soLoaiNhap = soLoaiNhap;
    }
    public void setSoLoaiNhap() {
        System.out.print("Nhap so loai xe can nhap: ");
        boolean check = false;
        do {
            soLoaiNhap = KiemTra.checkNumber();
            check = soLoaiNhap > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
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
        setSoLoaiNhap();
        setTongTien(0);
        setDsXe();
    }
    @Override
    public void nhap(){
        setMaPhieuNhap();
        setMaNhaCC();
        setMaNV();
        setNgaynhap();
        setSoLoaiNhap();
        setTongTien(0);
        setDsXe();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-20s \n","So phieu nhap", "Ma nha cung cap", "Ma nha vien", "Ngay nhap");
        System.out.printf("%-20s %-20s %-20s %-20s  \n", maPhieuNhap, maNhaCC , maNV , ngaynhap);
        System.out.println("\nDanh sach san pham: \n");
        System.out.printf("%-20s %-25s %-20s %-20s %-20s %-15s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Loai xe", "Noi san xuat", "So luong", "Gia");
        for(int i = 0; i< dsmspNhap.length; i++) dsmspNhap[i].xuat();
        System.out.println("\n**************************");
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin phieu nhap ===");
            System.out.println("1. Sua ma phieu nhap");
            System.out.println("2. Sua ma nha cung cap");
            System.out.println("3. Sua ma nhan vien lap phieu nhap");
            System.out.println("4. Sua ngay lap phieu nhap");
            System.out.println("5. Sua danh sach ma xe");
            System.out.println("0. Quay ve menu quan ly xe");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin phieu nhap!!");
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
                    Xe[] dsx = (Xe[]) getDsXe();
                    for(int i=0;i<dsx.length;i++)
                        dsx[i].xuat();
                    
                    System.out.println("Nhap moi danh sach xe: ");
                    setSoLoaiNhap();
                    setDsXe();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }
}
