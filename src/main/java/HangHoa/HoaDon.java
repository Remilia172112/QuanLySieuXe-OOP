package HangHoa;
import java.time.LocalDate;
import java.util.Scanner ;
import DanhSach.*;
import Nguoi.*;
import KiemTra.KiemTra;
import ThanhToan.*;

public class HoaDon extends PhanTu {
    // Tạo tháng năm hiện tại
    LocalDate localDate = LocalDate.now();
    private int namhientai = localDate.getYear();
    private int thanghientai = localDate.getMonthValue() + 1;
    private int ngayhientai = localDate.getDayOfMonth();

    private String ngaylapdon;
    private int soHoaDon;
    private int soLuongSanPham;
    private int tongTien = 0;
    private String phThThanhToan;
    private KhachHang khachHang;
    private String mnv;
    private Xe[] dsXe;
    public static Scanner sc = new Scanner(System.in);
    public HoaDon() {
    }

    public HoaDon(int soHoaDon, int soLuongSanPham, String ngaylapdon,KhachHang khachHang, Xe[] dsXe) {
        this.soHoaDon = soHoaDon;
        this.soLuongSanPham = soLuongSanPham;
        this.ngaylapdon = ngaylapdon;
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
            System.out.println("Ban co muon xuat ra man hinh danh sach khach hang khong? (1 - in, 0 - khong)");
            chon = Integer.parseInt(sc.nextLine());
            chon = (chon==0) ? 0 : 1;
            if (chon == 1) ttdss.xuatDanhSach();
            System.out.print("Nhap ma khach hang: ");
            maKhachHang = sc.nextLine();
            pt = ttdss.layPhanTuVoi(maKhachHang);
            if (pt == null) {
                System.out.println("Khong tim thay khach hang!");
                System.out.println("Ban co muon them khach hang moi khong? (1 - them, 0 - khong)");
                chon = Integer.parseInt(sc.nextLine());
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

    public PhanTu[] getDsSanPham() {
        return dsXe;
    }

    public void setDsSanPham(Xe[] dsXe) {
        this.dsXe = dsXe;
    }

    public void setDsSanPham() {
        // Khai báo
        DanhSachXe ttds = new DanhSachXe();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        
        Xe[] dsspFile = ttds.getdsSanPham();
        Xe[] dssp = new Xe[soLuongSanPham];

        // Tìm khách hàng trong danh sách
        KhachHang[] dsKhTemp = dskh.getDsKhachHang();
        int vtkh = dskh.timViTriKhachHang(khachHang.getMaKhachHang());

        // Lấy mảng sản phẩm đã mua
        int slspDamua = dsKhTemp[vtkh].getDsmspDamua().length;
        String[] dsmspDamua = new String[slspDamua+soLuongSanPham];
        System.arraycopy(dsKhTemp[vtkh].getDsmspDamua(), 0, dsmspDamua, 0, slspDamua);

        Xe pt, timThay;
        int vtsp, stt;
        System.out.println("Ban co muon xuat ra man hinh danh sach xe khong? (1 - in, 0 - khong)");
        int chon = Integer.parseInt(sc.nextLine());
        if (chon == 1) ttds.xuatDanhSach();
        for(int i=0;i<soLuongSanPham;i++) {
            stt=i+1;
            System.out.println("Them san pham thu "+stt);
            
            do {
                System.out.print("Nhap ma san pham:");
                pt = (Xe) ttds.layPhanTuVoi(sc.nextLine());
                
                if (pt == null) 
                    System.out.println("Khong tim thay san pham!");
                else {
                    dssp[i] = pt;
                    
                    if (pt.getSoLuong() == 0) { // nếu sản phẩm đã hết hàng
                        System.out.println("San pham da het hang, vui long chon san pham khac!");
                        pt=null;
                        continue;
                    }
                    else {
                        // Lưu mã sản phẩm riêng của người mua vào
                        String tmp = pt.getMaSanPham() + dsKhTemp[vtkh].getMaKhachHang() + thanghientai + namhientai;
                        dsmspDamua[slspDamua++] = tmp;

                        // tìm sản phẩm trong danh sách sp với mã sản phẩm
                        timThay = (Xe) ttds.layPhanTuVoi(pt.getMaSanPham());
                        
                        // giảm số lượng sản phẩm trong danh sách vì đã thêm sản phẩm vào hoá đơn.
                        timThay.setSoLuong(timThay.getSoLuong()-1);
                        
                        // tìm vị trí sản phẩm đã nhập trong danh sách
                        vtsp = ttds.timViTriSanPham(pt.getMaSanPham());
                        
                        // cập nhật lại số lượng sản phẩm
                        dsspFile[vtsp] = timThay;
                        ttds.setdsSanPham(dsspFile);
                        
                        // cập nhật tổng tiền
                        tongTien += pt.getPrice() * pt.getSoLuong();
                    }
                }
            } while (pt == null);
        }
        
        
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
                    timThay = (Xe) ttds.layPhanTuVoi(x.getMaSanPham());
                    
                    // tăng số lượng sản phẩm trong danh sách vì xoá sản phẩm khỏi hoá đơn
                    timThay.setSoLuong(timThay.getSoLuong()+x.getSoLuong());
                    
                    // tìm vị trí sản phẩm cần chỉnh sửa trong danh sách
                    viTriCanChinhSua = ttds.timViTriSanPham(x.getMaSanPham());
                    dsXe[viTriCanChinhSua] = timThay;
                    
                    // cập nhật lại danh sách
                    ttds.setdsSanPham(dsXe);
                    
                    // tìm tổng tiền cần trả lại cho khách
                    tongTienTraLai += x.getPrice() * x.getSoLuong();
                }
                
                tienTam -= tongTienTraLai;
            }
        } else dhDaThanhToan++; // nếu đơn hàng mới hoàn toàn
        // Lưu ngày lập đơn
        ngaylapdon = ngayhientai + "/" + thanghientai + "/" + namhientai;
        // lưu lại
        dsKhTemp[vtkh].setDsmspDamua(dsmspDamua);
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
            try {

                soHoaDon = Integer.parseInt(sc.nextLine());
                check = ttds.layPhanTuVoi(soHoaDon+"") == null;
                if (!check) System.out.print("So hoa don da ton tai, moi nhap lai: ");
            } catch (Exception e) {
                check = false;
                System.out.print("Vui long nhap mot so: ");
            }
        } while (!check);
    }

    public void setSoHoaDon(int soHoaDon) {
        this.soHoaDon = soHoaDon;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public void setSoLuongSanPham() {
        boolean check;
        do
        {
            check = true;
            try {
                System.out.print("Nhap so luong san pham: ");
                soLuongSanPham = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                check = false;
                System.out.print("Vui long nhap mot so: ");
            }
        } while (!check);
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
        boolean check;
        do
        {
            check = true;
            System.out.print("Nhap ngay lap don (dd/mm/yyyy): ");
            ngaylapdon = sc.nextLine();
            check = KiemTra.isValidDate(ngaylapdon);
            if(!check) System.out.print("Nhap sai dinh dang ngay!! Moi nhap lai: ");
        } while (!check);
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
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui long nhap mot so!");
                continue;
            }
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
                chontl = Integer.parseInt(sc.nextLine());
                chontl = (chontl == 1) ? 1 : 0;

                if (chontl == 1) { // nếu chọn thiết lập
                    System.out.println("Moi chon phuong thuc thanh toan cho khach hang nay: ");
                    System.out.println("1. Tai khoan ngan hang");
                    System.out.println("2. Tai khoan the tin dung");
                    System.out.println("3. Vi dien tu");
                    System.out.print("Chon: ");
                    chon = Integer.parseInt(sc.nextLine());
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
            check = ttds.layPhanTuVoi(mnv) == null;
            if (!check) System.out.print("Ma nhan vien da ton tai, moi nhap lai: ");
            check = KiemTra.check_maso(mnv);
        }
        while (!check);
    }

    public void nhap(String username){
        setSoHoaDon();
        setSoLuongSanPham();
        setNgaylapdon();
        setMnv(username);
        setKhachHang();
        setDsSanPham();
        setPhThThanhToan();
    }
    @Override
    public void nhap(){
        setSoHoaDon();
        setSoLuongSanPham();
        setNgaylapdon();
        setMnv();
        setKhachHang();
        setDsSanPham();
        setPhThThanhToan();
    }

    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-25s %-30s\n", "So hoa don", "So luong san pham", "Tong tien", "Ten khach hang", "Phuong thuc thanh toan");
        System.out.printf("%-20s %-20s %-20s %-25s %-30s \n", soHoaDon, soLuongSanPham, tongTien, khachHang.getHoten(), phThThanhToan);
        System.out.println("\nDanh sach san pham: \n");
        System.out.printf("%-20s %-30s %-20s %-20s %-20s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        for(int i = 0; i< dsXe.length; i++)
        dsXe[i].xuat();
        System.out.println("\n**************************");
    }

    @Override
    public void suaThongTin() {
        System.out.println("=== Sua thong tin hoa don ===");
        System.out.println("1. Sua so hoa don");
        System.out.println("2. Sua ma khach hang");
        System.out.println("3. Sua danh sach san pham");
        System.out.println("4. Sua phuong thuc thanh toan");
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
                    System.out.println("Thong tin hien tai: "+getSoHoaDon());
                    setSoHoaDon();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getKhachHang().getMaKhachHang());
                    setKhachHang();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: ");
                    // xuất danh sách sản phẩm
                    Xe[] dssp = (Xe[]) getDsSanPham();
                    for(int i=0;i<dssp.length;i++)
                        dssp[i].xuat();
                    
                    System.out.println("Nhap moi danh sach san pham: ");
                    setSoLuongSanPham();
                    setDsSanPham();
                    break;
                case 4:
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
