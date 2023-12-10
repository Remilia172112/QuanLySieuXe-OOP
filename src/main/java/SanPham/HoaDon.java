package SanPham;
import java.util.Arrays;

import DanhSach.*;
import Nguoi.*;
import KiemTra.KiemTra;
import ThanhToan.*;

public class HoaDon extends PhanTu {
    private String ngaylapdon;
    private int soHoaDon;
    private int soLoaiXe;
    private int tongTien = 0;
    private String phThThanhToan;
    private KhachHang khachHang;
    private String mnv;
    private Xe[] dsXe;

    public HoaDon() {
    }

    public HoaDon(int soHoaDon, int soLoaiXe, int tongTien,String ngaylapdon, String mnv,KhachHang khachHang, Xe[] dsXe) {
        this.soHoaDon = soHoaDon;
        this.soLoaiXe = soLoaiXe;
        this.tongTien = tongTien;
        this.ngaylapdon = ngaylapdon;
        this.mnv = mnv;
        this.khachHang = khachHang;
        this.dsXe = dsXe;
    }
    
    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setKhachHang() {
        DanhSachKhachHang  ttdss = new DanhSachKhachHang();
        PhanTu pt;
        String maKhachHang;
        int chon;
        do {
            System.out.print("Ban co muon xuat ra man hinh danh sach khach hang khong? (1 - in, 0 - khong): ");
            chon = KiemTra.checkNumber();
            chon = (chon==0) ? 0 : 1;
            if (chon == 1) ttdss.xuatDanhSach();
            System.out.print("Nhap ma khach hang: ");
            maKhachHang = sc.nextLine();
            pt = ttdss.layPhanTuVoi(maKhachHang);
            if (pt == null) {
                System.out.println("Khong tim thay khach hang!");
                System.out.print("Ban co muon them khach hang moi khong? (1 - them, 0 - khong)");
                chon = KiemTra.checkNumber();
                chon = (chon==0) ? 0 : 1;

                if (chon == 1) {
                    pt = new KhachHang();
                    pt.nhap();
                    ttdss.themVaoDanhSach(pt);
                    khachHang = (KhachHang) pt;
                }

            } else khachHang = (KhachHang) pt;
            
        } while(pt == null);
    }

    public Xe[] getDsXe() {
        return dsXe;
    }

    public void setDsXe(Xe[] dsXe) {
        this.dsXe = dsXe;
    }

    public void setDsXe() {
        // Khai báo
        DanhSachXe ttds = new DanhSachXe();
        DanhSachKhachHang dskh = new DanhSachKhachHang();

        // Tìm khách hàng trong danh sách
        KhachHang[] dsKhTemp = dskh.getDsKhachHang();
        int vtkh = dskh.timViTriKhachHang(khachHang.getMaKhachHang());

        // Lấy mảng sản phẩm đã mua
        String[] dsspDamua = dsKhTemp[vtkh].getDsmspDamua();
        int slspDamua = dsspDamua.length;
        if(slspDamua >= 99) {
            System.out.print("He thong da qua tai!!!");
            return;
        }
        
        Xe[] dsspFile = ttds.getdsXe();
        Xe[] dssp = new Xe[soLoaiXe];
        String[] madachon = new String[0];
        
        Xe pt, timThay;
        int slcl, vtsp, chon;
        // xem lại danh mục sản phẩm (tuỳ chọn)
        System.out.print("Ban co muon xem lai danh sach xe? (1 - xem, 0 - khong): ");
        chon = Integer.parseInt(sc.nextLine());
        
        if (chon==1) ttds.xuatDanhSach();
        
        for(int i=0;i<soLoaiXe;i++) {
            System.out.println("Them xe thu "+(i+1)+":");
            
            do {
                System.out.print("Nhap ma xe: ");
                pt = (Xe) ttds.layPhanTuVoi(sc.nextLine());
                
                if (pt == null) 
                    System.out.println("Khong tim thay xe!!!");
                else {
                    dssp[i] = pt;
                    
                    if (pt.getSoLuong() == 0) { // nếu sản phẩm đã hết hàng
                        System.out.println("Xe da het hang, vui long chon xe khac!!!");
                        pt=null;
                        continue;
                    }

                    boolean check = false;
                    for(int k=0; k < madachon.length; k++) {
                        if(madachon[k].equals(pt.getMaXe())) {
                            System.out.println("Xe da co trong hoa don, vui long chon xe khac!!!");
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
                    
                    do {

                        pt.setSoLuong();

                        // tính toán số lượng sản phẩm còn lại
                        slcl = timThay.getSoLuong()-pt.getSoLuong();
                        
                        // nếu vượt quá số lượng sản phẩm hiện có
                        if (slcl < 0) System.out.println("So luong xe khong du! Xe hien tai con: " + timThay.getSoLuong());
                    } while (slcl < 0);
                    
                    // Lưu mã sản phẩm riêng của người mua vào
                    for(int j = 0; j < pt.getSoLuong(); j++) {
                        dsspDamua = Arrays.copyOf(dsspDamua, dsspDamua.length+1);
                        String tmp;
                        if(slspDamua < 9) tmp = pt.getMaXe() + dsKhTemp[vtkh].getMaKhachHang() + thanghientai + namhientai + "0" + ++slspDamua;
                        else tmp = pt.getMaXe() + dsKhTemp[vtkh].getMaKhachHang() + thanghientai + namhientai + ++slspDamua;
                        dsspDamua[dsspDamua.length-1] = tmp;
                    }
                    // giảm số lượng sản phẩm trong danh sách vì đã thêm sản phẩm vào hoá đơn.
                    timThay.setSoLuong(timThay.getSoLuong()-pt.getSoLuong());
                    
                    // tìm vị trí sản phẩm đã nhập trong danh sách
                    vtsp = ttds.timViTriXe(timThay.getMaXe());
                    
                    // cập nhật lại số lượng sản phẩm
                    dsspFile[vtsp] = timThay;
                    
                    // cập nhật tổng tiền
                    tongTien += pt.getPrice() * pt.getSoLuong();
                }
            } while (pt == null);
        }
        // Cập nhật lên file
        ttds.setdsXe(dsspFile);
        
        // lấy thuộc tính tổng tiền đã thanh toán và số đơn hàng đã thanh toán
        int tienTam = dsKhTemp[vtkh].getTongTienDaThanhToan();
        int dhDaThanhToan = dsKhTemp[vtkh].getSoDonHangDaThanhToan();
        
        tienTam += tongTien; // cộng số tiền của cả hoá đơn đã nhập
        
        // Nếu là chỉnh sửa danh sách sản phẩm
        if (dsXe != null) {
            if (dsXe.length > 0) { // nếu danh sách sản phẩm > 0
                int tongTienTraLai = 0;
                int viTriCanChinhSua;
                for(Xe x: dsXe) // ứng với từng phần tử
                {
                    // tìm sản phẩm trong danh sách với mã sản phẩm
                    timThay = (Xe) ttds.layPhanTuVoi(x.getMaXe());
                    
                    // tăng số lượng sản phẩm trong danh sách vì xoá sản phẩm khỏi hoá đơn
                    timThay.setSoLuong(timThay.getSoLuong()+x.getSoLuong());
                    
                    // tìm vị trí sản phẩm cần chỉnh sửa trong danh sách
                    viTriCanChinhSua = ttds.timViTriXe(x.getMaXe());
                    dsspFile[viTriCanChinhSua] = timThay;
                    
                    // cập nhật lại danh sách
                    ttds.setdsXe(dsspFile);
                    
                    // tìm tổng tiền cần trả lại cho khách
                    tongTienTraLai += x.getPrice() * x.getSoLuong();
                }
                
                tienTam -= tongTienTraLai;
            }
        } else dhDaThanhToan++; // nếu đơn hàng mới hoàn toàn
        
        // lưu lại
        dsKhTemp[vtkh].setDsmspDamua(dsspDamua);
        dsKhTemp[vtkh].setTongTienDaThanhToan(tienTam);
        dsKhTemp[vtkh].setSoDonHangDaThanhToan(dhDaThanhToan);
        dskh.setDsKhachHang(dsKhTemp);
        
        dsXe = dssp;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public void setSoHoaDon() {
        System.out.print("Nhap so hoa don: ");
        DanhSachHoaDon ttds = new DanhSachHoaDon();
        
        boolean check;
        do
        {
            check = true;
            soHoaDon = KiemTra.checkNumber();
            check = ttds.layPhanTuVoi(soHoaDon+"") == null;
            if (!check) System.out.print("So hoa don da ton tai, moi nhap lai: ");
        } while (!check);
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public int getSoLoaiXe() {
        return soLoaiXe;
    }

    public void setSoLoaiXe(int soLoaiXe) {
        this.soLoaiXe = soLoaiXe;
    }

    public void setSoLoaiXe() {
        System.out.print("Nhap so loai xe can mua: ");
        soLoaiXe = KiemTra.checkNumber();
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getPhThThanhToan() {
        return phThThanhToan;
    }

    public void setPhThThanhToan(String phThThanhToan) {
        this.phThThanhToan = phThThanhToan;
    }

    public void setNgaylapdon(String ngaylapdon) {
        this.ngaylapdon = ngaylapdon;
    }

    public String getNgaylapdon() {
        return ngaylapdon;
    }
    
    public void setNgaylapdon() {
        System.out.print("Sua dung ngay thang nam hien tai de lam ngay lap don? (1 - co, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if(chon == 1) ngaylapdon = ngayhientai + "/" + thanghientai + "/" + namhientai;
        else {
            boolean check;
            System.out.print("Nhap ngay lap don (dd/mm/yyyy): ");
            do
            {
                check = true;
                ngaylapdon = sc.nextLine();
                check = KiemTra.check_date(ngaylapdon);
                if(check) {
                    check = KiemTra.CheckDate(ngaylapdon);
                    if(!check) System.out.print("Moi nhap lai: ");
                }
            } while (!check);
        }
    }

    public void setPhThThanhToan() {
        System.out.println("Moi chon phuong thuc thanh toan cho hoa don nay: ");
        System.out.println("1. Tien mat");
        System.out.println("2. Tai khoan ngan hang");
        System.out.println("3. Tai khoan the tin dung");
        System.out.println("4. Vi dien tu");
        System.out.print("Chon: ");
        int chon, chontl = 0;
        // các biến lựa chọn
        KhachHang[] dsKhachHang;

        do{
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    phThThanhToan = "TienMat";
                    break;
                case 2:
                    phThThanhToan = "CK_NganHang";
                    break;
                case 3:
                    phThThanhToan = "CK_TinDung";
                    break;
                case 4:
                    phThThanhToan = "CK_ViDienTu";
                    break;
                default:
                    chon = 0;
                    break;
            }
            
            if(chon == 0)
            { // nếu người dùng nhập số khác các số trên
                System.out.print("Moi chon lai: ");
                continue;
            }
            
            
            if (chon == 1) break; // ph thức tiền mặt
            
            // kiểm tra
            DanhSachKhachHang dskh = new DanhSachKhachHang();
        
            String maKH = getKhachHang().getMaKhachHang();
            int vtkh = dskh.timViTriKhachHang(maKH+"");
            
            dsKhachHang = dskh.getDsKhachHang();

            ThanhToan pttt = dsKhachHang[vtkh].getPhThThanhToan();
            
            switch (chon) { // nếu chọn đúng phương thức
                case 2:
                    if (pttt.getPTNganHang() == null) chontl = 1;
                    break;
                case 3:
                    if (pttt.getPTTinDung()== null) chontl = 1;
                    break;
                case 4:
                    if (pttt.getPTViDienTu()== null) chontl = 1;
                    break;
                default:
                    chontl = 1;
                    break;
            }
            
            if (chontl == 1) {
                System.out.println("Khach hang chua thiet lap phuong thuc thanh toan nay!");

                System.out.print("Ban co muon thiet lap phuong thuc thanh toan cho khach hang nay khong? (1 - co, 0 - khong): ");
                chontl = KiemTra.checkNumber();
                chontl = (chontl == 1) ? 1 : 0;

                if (chontl == 1) { // nếu chọn thiết lập
                    System.out.println("Moi chon phuong thuc thanh toan cho khach hang nay: ");
                    System.out.println("1. Tai khoan ngan hang");
                    System.out.println("2. Tai khoan the tin dung");
                    System.out.println("3. Vi dien tu");
                    System.out.print("Chon: ");
                    chon = KiemTra.checkNumber();
                    switch (chon) {
                        case 1:
                            CKNganHang cknh = new CKNganHang();
                            cknh.nhapThongTin();
                            dsKhachHang[vtkh].getPhThThanhToan().setPTNganHang(cknh);
                            break;
                        case 2:
                            CKTinDung cktd = new CKTinDung();
                            cktd.nhapThongTin();
                            dsKhachHang[vtkh].getPhThThanhToan().setPTTinDung(cktd);
                            break;
                        case 3:
                            CKViDienTu ckvdt = new CKViDienTu();
                            ckvdt.nhapThongTin();
                            dsKhachHang[vtkh].getPhThThanhToan().setPTViDienTu(ckvdt);
                            break;
                        default:
                            System.out.print("Moi chon phuong thuc thanh toan khac: ");
                            chontl = 0;
                            break;
                    }
                    dskh.setDsKhachHang(dsKhachHang);
                } else System.out.print("Moi chon phuong thuc thanh toan khac: ");
            }
        }while (chontl == 0);
    }
    
    public String getMnv() {
        return mnv;
    }

    public void setMnv(String mnv) {
        this.mnv = mnv;
    }

    public void setMnv() {
        System.out.print("Nhap ma nhan vien quan li don hang: ");
        DanhSachNhanVien ttds = new DanhSachNhanVien();
        boolean check = false;
        do {
            check = true;
            mnv = sc.nextLine();
            check = ttds.layPhanTuVoi(mnv) != null;
            if (!check) System.out.print("Ma nhan vien khong ton tai, moi nhap lai: ");
            if(check) check = KiemTra.check_maso(mnv);
        }
        while (!check);
    }
    // Nhập version đã đăng nhập
    public void nhap(String username){
        setSoHoaDon();
        setSoLoaiXe();
        setNgaylapdon();
        setMnv(username);
        setKhachHang();
        setTongTien(0);
        setDsXe();
        setPhThThanhToan();
    }
    @Override
    public void nhap(){
        setSoHoaDon();
        setSoLoaiXe();
        setNgaylapdon();
        setMnv();
        setKhachHang();
        setTongTien(0);
        setDsXe();
        setPhThThanhToan();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-25s %-30s\n", "So hoa don", "So luong xe", "Tong tien", "Ten khach hang", "Phuong thuc thanh toan");
        System.out.printf("%-20s %-20s %-20s %-25s %-30s \n", soHoaDon, soLoaiXe, tongTien, khachHang.getHoten(), phThThanhToan);
        System.out.println("\nDanh sach xe: \n");
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n","Ma xe", "Ten xe", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        for(int i = 0; i< dsXe.length; i++)
        dsXe[i].xuat();
        System.out.println("\n**************************");
    }
    @Override
    public void suaThongTin() {
        System.out.println("=== Sua thong tin hoa don ===");
        System.out.println("1. Sua so hoa don");
        System.out.println("2. Sua ma khach hang");
        System.out.println("3. Sua ma nhan vien lap hoa don");
        System.out.println("4. Sua ngay lap hoa don");
        System.out.println("5. Sua danh sach xe");
        System.out.println("6. Sua phuong thuc thanh toan");
        System.out.println("0. Quay ve menu quan ly xe");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin hoa don!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getSoHoaDon());
                    setSoHoaDon();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getKhachHang().getMaKhachHang());
                    setKhachHang();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: "+getMnv());
                    setMnv();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getNgaylapdon());
                    setNgaylapdon();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: ");
                    // xuất danh sách sản phẩm
                    Xe[] dsx = (Xe[]) getDsXe();
                    for(int i=0;i<dsx.length;i++)
                        dsx[i].xuat();
                    
                    System.out.println("Nhap moi danh sach xe: ");
                    setSoLoaiXe();
                    setDsXe();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: "+getPhThThanhToan());
                    setPhThThanhToan();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }
}
