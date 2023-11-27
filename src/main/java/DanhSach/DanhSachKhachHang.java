package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import HangHoa.PhanTu;
import Nguoi.KhachHang;
import ThanhToan.*;

public class DanhSachKhachHang implements DanhSachChung {
    private int soLuong;
    private KhachHang[] dsKhachHang;
    
    public DanhSachKhachHang(){
        dsKhachHang = getDsKhachHang();
    }
    
    public DanhSachKhachHang(int soLuong, KhachHang[] dsKhachHang){
        this.soLuong = soLuong;
        this.dsKhachHang = dsKhachHang;
    }
    
    public int getSoLuong(){
        return soLuong;
    }
    
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }
    
    public KhachHang[] getDsKhachHang(){ // đọc từ file
        String data = FileHandler.docFile("dskh.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file
        
        // nếu file rỗng
        if (dArr[0].length() == 0) setSoLuong(0);
        else setSoLuong(Integer.parseInt(dArr[0]));
        
        dsKhachHang = new KhachHang[soLuong];
        KhachHang kh;
        ThanhToan pttt; CKNganHang cknh; CKTinDung cktd; CKViDienTu ckvdt;
        
        String lArr[];
        int k = 0, m;
        
        for (int i = 1; i < dArr.length; i++) { // ứng với mỗi dòng
            lArr = dArr[i].split("#");
            m = 0;

            kh = new KhachHang();
            kh.setMaKhachHang(Integer.parseInt(lArr[m++]));
            kh.setHoten(lArr[m++]);
            kh.setNgaythangnamsinh(lArr[m++]);
            kh.setGioitinh(lArr[m++]);            
            kh.setCCCD(lArr[m++]);
            kh.setDiachi(lArr[m++]);
            kh.setSdt(lArr[m++]);
            kh.setEmail(lArr[m++]);
            kh.setSoDonHangDaThanhToan(Integer.parseInt(lArr[m++]));
            kh.setTongTienDaThanhToan(Integer.parseInt(lArr[m++]));
            // ghi phương thức thanh toán
            // !lArr[m].equals(" ") => nếu dòng khác " " (đã thiết lập phương thức thanh toán)
            pttt = new ThanhToan();
            pttt.setPhuongThucThanhToan(lArr[m++]);
            // phương thức ngân hàng
            if (!lArr[m].equals(" ")) {
                cknh = new CKNganHang();
                
                cknh.setSoTheTk(lArr[m++]);
                
                cknh.setCVV(Integer.parseInt(lArr[m++]));
                
                pttt.setPTNganHang(cknh);
            } else m+=2; // bỏ qua 2 dòng
            // phương thức tín dụng
            if (!lArr[m].equals(" ")) {
                cktd = new CKTinDung();
                
                cktd.setSoThe(lArr[m++]);
                
                cktd.setCVV(Integer.parseInt(lArr[m++]));
                
                pttt.setPTTinDung(cktd);
            } else m+=3;
            // phương thức ví điện tử
            if (!lArr[m].equals(" ")) {
                ckvdt = new CKViDienTu();
                
                ckvdt.setSoDienThoaiLienKet(lArr[m++]);
                
                ckvdt.setTenVi(lArr[m++]);
                
                pttt.setPTViDienTu(ckvdt);
            } else m+=2;
            kh.setPhThThanhToan(pttt);
            dsKhachHang[k++] = kh;
        }
        return dsKhachHang;
    }
    public void setDsKhachHang(PhanTu[] dsKhachhang){ // ghi file
        KhachHang kh;
        String tenFile = "dskh.txt";
        
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);
        
        for(int i=0;i<soLuong;i++) {
            kh = (KhachHang) dsKhachhang[i];
            FileHandler.themKH(kh.getMaKhachHang(), kh.getHoten(), kh.getNgaythangnamsinh(), kh.getGioitinh(), kh.getCCCD(), kh.getDiachi(), 
                    kh.getSdt(), kh.getEmail(), kh.getSoDonHangDaThanhToan(), kh.getTongTienDaThanhToan(), kh.getPhThThanhToan());
        }
        this.dsKhachHang = (KhachHang[]) dsKhachhang;
    }
    @Override
    public void nhapDanhSach(){
        System.out.print("Nhap so luong khach hang: ");
        
        soLuong = KiemTra.checkNumber();
        
        dsKhachHang = new KhachHang[soLuong];
        
        int stt, soLuongTemp=0, soLuongCurrent = soLuong;
        
        for (int i = 0; i < soLuongCurrent; i++){
            dsKhachHang[i] = new KhachHang();
            stt = i+1;
            System.out.println("** Khach hang thu "+stt+" **");
            
            dsKhachHang[i].nhap();
            soLuong = ++soLuongTemp;
            // mỗi lần đọc phần tử từ mảng sẽ ghi trực tiếp vào file kèm số lượng phần tử đã đọc
            setDsKhachHang(dsKhachHang);
            
        }
    }   
    public void xuatDanhSach(){
        System.out.println("=== Danh sach khach hang ===");
        for (int i = 0; i<soLuong; i++){
            getDsKhachHang()[i].xuat();
        }
        System.out.println();
    }      
    public void themVaoDanhSach(PhanTu pt){
        KhachHang[] dsKhachHangTmp = new KhachHang[soLuong+1];
        
        for(int i=0;i<soLuong;i++)
            dsKhachHangTmp[i] = getDsKhachHang()[i];
        
        dsKhachHangTmp[soLuong] = (KhachHang) pt;
        
        soLuong++;
        setDsKhachHang(dsKhachHangTmp);
    }
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong khach hang can them vao danh sach: ");
        int sl = KiemTra.checkNumber();;
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new KhachHang();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    } 
    public void chinhSuaThongTinPhanTu(){
        System.out.println("Tim nhan vien can chinh sua: ");
        int viTri = timViTriPhanTu();
        KhachHang[] dskh = getDsKhachHang();
        if (viTri != -1) {
            dskh[viTri].suaThongTin();
            setDsKhachHang(dskh);
        }
        else System.out.println("Khong tim thay!");
    }   
    public void xoaPhanTu(){
        // Tìm khách hàng trước
        System.out.println("Tim khach hang can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            KhachHang[] dsKhachHangTmp = new KhachHang[soLuong-1];
            
            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue; // bỏ phần tử
                dsKhachHangTmp[k++] = getDsKhachHang()[i];
            }
            
            soLuong--;
            setDsKhachHang(dsKhachHangTmp);
        } else System.out.println("Khong tim thay khach hang!");
    }
    public PhanTu timPhanTu(){ // tìm khách hàng theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim khach hang theo ten (1) hay theo ma (2), vui long chon: ");
        
        loai = KiemTra.checkNumber();;
        loai = (loai != 2) ? 1 : 2;
        
        if (loai == 1)
            System.out.print("Nhap ten khach hang can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma khach hang can tim: ");
        
        String giaTriCanTim = sc.nextLine();
        
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        
        chon = KiemTra.checkNumber();;
        chon = (chon != 2) ? 1 : 2;
        
        KhachHang[] dsKhachHangTmp = getDsKhachHang();
        
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang() == Integer.parseInt(giaTriCanTim))
                        return dsKhachHangTmp[i];
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().contains(giaTriCanTim))
                        return dsKhachHangTmp[i];
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang() == Integer.parseInt(giaTriCanTim))
                        return dsKhachHangTmp[i];
            }
        }
        return null;
    }
    public int timViTriPhanTu() { // trả về vị trí phần tử tìm được
        int loai;
        System.out.print("Tim khach hang theo ten (1) hay theo ma (2), vui long chon: ");
        
        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;
        
        if (loai == 1)
            System.out.print("Nhap ten khach hang can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma khach hang can tim: ");
        
        String giaTriCanTim = sc.nextLine();
        
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;
        
        KhachHang[] dsKhachHangTmp = getDsKhachHang();
        
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang() == Integer.parseInt(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsKhachHangTmp[i].getHoten().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsKhachHangTmp[i].getMaKhachHang() == Integer.parseInt(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }
    public int timViTriKhachHang(int maKhachHang) { // tìm vị trí sản phẩm với mã sản phẩm
        KhachHang[] dskh = getDsKhachHang();
        for(int i=0;i<soLuong;i++) {
            if (dskh[i].getMaKhachHang() == maKhachHang)
                return i;
        }
        return -1;
    }
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử với mã
        KhachHang[] dskh = getDsKhachHang();
        for(int i=0;i<soLuong;i++) {
            if (dskh[i].getMaKhachHang()== Integer.parseInt(thamSo))
                return dskh[i];
        }
        return null;
    }
    public void thongKe() {
        int chon, n;
        dsKhachHang = getDsKhachHang();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc khach hang voi so luong don hang da thanh toan >= n");
            System.out.println("2. Loc khach hang co tong tien da thanh toan >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.checkNumber();

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong don hang can tim: ");
                    n = KiemTra.checkNumber();
                    for (KhachHang khachHang: dsKhachHang) {
                        if (khachHang.getSoDonHangDaThanhToan() >= n) khachHang.xuat();
                    }
                    break;
                case 2:
                    System.out.print("Nhap so luong tong tien can tim: ");
                    n = KiemTra.checkNumber();
                    for (KhachHang khachHang: dsKhachHang) {
                        if (khachHang.getTongTienDaThanhToan() >= n) khachHang.xuat();
                    }
                    break;
            
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
