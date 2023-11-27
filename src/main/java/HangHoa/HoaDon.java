package HangHoa;
import java.util.Scanner ;
import DanhSach.*;
import Nguoi.*;
import ThanhToan.*;

public class HoaDon extends PhanTu {
   private int soHoaDon;
   private int soLuongSanPham;
   private int tongTien = 0;
   private String phThThanhToan;
   private KhachHang khachHang;
   private SanPham[] dsSanPham;
   public static Scanner sc = new Scanner(System.in);
    public HoaDon() {
    }

    public HoaDon(int soHoaDon, int soLuongSanPham, KhachHang khachHang, SanPham[] dsSanPham) {
        this.soHoaDon = soHoaDon;
        this.soLuongSanPham = soLuongSanPham;
        this.khachHang = khachHang;
        this.dsSanPham = dsSanPham;
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
        return dsSanPham;
    }

    public void setDsSanPham(SanPham[] dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public void setDsSanPham() {
        // Khai báo
        DanhSachXe ttds = new DanhSachXe();
        DanhSachKhachHang dskh = new DanhSachKhachHang();
        
        SanPham[] dsspFile = ttds.getdsSanPham();
        SanPham[] dssp = new SanPham[soLuongSanPham];
        
        SanPham pt, timThay;
        int slcl, vtsp, stt;
        
        for(int i=0;i<soLuongSanPham;i++) {
            stt=i+1;
            System.out.println("Them san pham thu "+stt);
            
            do {
                System.out.print("Nhap ma san pham:");
                pt = (SanPham) ttds.layPhanTuVoi(sc.nextLine());
                
                if (pt == null) 
                    System.out.println("Khong tim thay san pham!");
                else {
                    dssp[i] = pt;
                    
                    if (pt.getSoLuong() == 0) { // nếu sản phẩm đã hết hàng
                        System.out.println("San pham da het hang, vui long chon san pham khac!");
                        pt=null;
                        continue;
                    }
                    
                    // tìm sản phẩm trong danh sách sp với mã sản phẩm
                    timThay = (SanPham) ttds.layPhanTuVoi(pt.getMaSanPham());
                    
                    do {
                        pt.setSoLuong();
                        
                        // tính toán số lượng sản phẩm còn lại
                        slcl = timThay.getSoLuong()-pt.getSoLuong();
                        
                        // nếu vượt quá số lượng sản phẩm hiện có
                        if (slcl < 0) System.out.println("So luong san pham khong du! San pham hien tai con: " + timThay.getSoLuong());
                    } while (slcl < 0);
                    
                    // giảm số lượng sản phẩm trong danh sách vì đã thêm sản phẩm vào hoá đơn.
                    timThay.setSoLuong(timThay.getSoLuong()-pt.getSoLuong());
                    
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
        // Tìm khách hàng trong danh sách
        KhachHang[] dsKhTemp = dskh.getDsKhachHang();
        int vtkh = dskh.timViTriKhachHang(khachHang.getMaKhachHang());
        
        // lấy thuộc tính tổng tiền đã thanh toán và số đơn hàng đã thanh toán
        int tienTam = dsKhTemp[vtkh].getTongTienDaThanhToan();
        int dhDaThanhToan = dsKhTemp[vtkh].getSoDonHangDaThanhToan();
        
        tienTam += tongTien; // cộng số tiền của cả hoá đơn đã nhập
        
        // Nếu là chỉnh sửa danh sách sản phẩm
        if (dsSanPham != null) {
            if (dsSanPham.length > 0) { // nếu danh sách sản phẩm > 0
                int tongTienTraLai = 0;
                int viTriCanChinhSua;
                for(SanPham x: dsSanPham) // ứng với từng phần tử
                {
                    // tìm sản phẩm trong danh sách với mã sản phẩm
                    timThay = (SanPham) ttds.layPhanTuVoi(x.getMaSanPham());
                    
                    // tăng số lượng sản phẩm trong danh sách vì xoá sản phẩm khỏi hoá đơn
                    timThay.setSoLuong(timThay.getSoLuong()+x.getSoLuong());
                    
                    // tìm vị trí sản phẩm cần chỉnh sửa trong danh sách
                    viTriCanChinhSua = ttds.timViTriSanPham(x.getMaSanPham());
                    dsSanPham[viTriCanChinhSua] = timThay;
                    
                    // cập nhật lại danh sách
                    ttds.setdsSanPham(dsSanPham);
                    
                    // tìm tổng tiền cần trả lại cho khách
                    tongTienTraLai += x.getPrice() * x.getSoLuong();
                }
                
                tienTam -= tongTienTraLai;
            }
        } else dhDaThanhToan++; // nếu đơn hàng mới hoàn toàn
        
        // lưu lại
        dsKhTemp[vtkh].setTongTienDaThanhToan(tienTam);
        dsKhTemp[vtkh].setSoDonHangDaThanhToan(dhDaThanhToan);
        dskh.setDsKhachHang(dsKhTemp);
        
        dsSanPham = dssp;
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
        
            int maKH = getKhachHang().getMaKhachHang();
            int vtkh = dskh.timViTriKhachHang(maKH);
            
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
    
    @Override
    public void nhap(){
        setSoHoaDon();
        setSoLuongSanPham();
        setKhachHang();
        setDsSanPham();
        setPhThThanhToan();
    }

    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-20s %-25s %-25s %-30s\n", "So hoa don", "So luong san pham", "Tong tien", "Ten khach hang", "Ten thu ngan", "Phuong thuc thanh toan");
        System.out.printf("%-20s %-20s %-20s %-25s %-25s %-30s \n", soHoaDon, soLuongSanPham, tongTien, khachHang.getHoten(), phThThanhToan);
        System.out.println("Danh sach san pham: ");
        System.out.printf("%-20s %-50s %-20s %-20s %-20s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        for(int i=0;i<dsSanPham.length;i++)
            dsSanPham[i].xuat();
        System.out.println("**************************");
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
                    SanPham[] dssp = (SanPham[]) getDsSanPham();
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
