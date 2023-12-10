package DanhSach;
import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.*;
import SanPham.*;

public class DanhSachHoaDon implements DanhSachChung {
    private int soLuong;
    private HoaDon[] dsHoaDon;
    
    public DanhSachHoaDon() {
        dsHoaDon = getdsHoaDon();
    }
    
    public int getsoLuong(){
        return soLuong;
    }
    
    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }
    
    public HoaDon[] getdsHoaDon() { // đọc từ file
        String data = FileHandler.docFile("dshd.txt");
        // tách thành mảng các phần tử chuỗi
        String[] dArr = data.split("\n");
        
        // nếu file rỗng
        if (dArr[0].length() == 0) setSoLuong(0);
        else setSoLuong(Integer.parseInt(dArr[0]));
        
        dsHoaDon = new HoaDon[soLuong];
        HoaDon hd;
        Xe sp;
        Xe[] dsx;
        String[] lArr;
        int k = 0, m, slsp=0;
        // khởi tạo các đối tượng từ các class danh sách trung tâm
        DanhSachKhachHang ttdskh = new DanhSachKhachHang();
        DanhSachXe ttdsx = new DanhSachXe();
        
        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");

            hd = new HoaDon();
            
            m = 0;

            hd.setSoHoaDon(Integer.parseInt(lArr[m++]));
            
            hd.setNgaylapdon(lArr[m++]);

            hd.setMnv(lArr[m++]);
            
            hd.setKhachHang((KhachHang) ttdskh.layPhanTuVoi(lArr[m++]));
            
            hd.setPhThThanhToan(lArr[m++]);

            hd.setTongTien(Integer.parseInt(lArr[m++]));

            slsp = Integer.parseInt(lArr[m++]);
            
            hd.setSoLoaiXe(slsp);

            // đọc danh sách sản phẩm
            dsx = new Xe[slsp];
            for(int j=0;j<slsp;j++) {
                sp = (Xe) ttdsx.layPhanTuVoi(lArr[m++]);
                sp.setSoLuong(Integer.parseInt(lArr[m++]));
                dsx[j] = sp;
            }
            
            hd.setDsXe(dsx);
            dsHoaDon[k++] = hd;
        }
        return dsHoaDon;
    }
    
    public void setdsHoaDon(PhanTu[] dsHoaDon){ // ghi file
        HoaDon hd;
        String tenFile = "dshd.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);
        
        for(int i=0;i<soLuong;i++) {
            // đọc từng phần tử từ mảng dsHoaDon
            hd = (HoaDon) dsHoaDon[i];
            FileHandler.themHd(hd.getSoHoaDon(), hd.getNgaylapdon(), hd.getMnv(),hd.getKhachHang().getMaKhachHang(), hd.getPhThThanhToan(), hd.getTongTien(),hd.getSoLoaiXe(), hd.getDsXe());
        }
        this.dsHoaDon = (HoaDon[])dsHoaDon;
    }

    public void themPhanTuVaoDanhSach(String username) {
        HoaDon pt;
        pt = new HoaDon();
        pt.nhap(username);
        themVaoDanhSach((PhanTu) pt);
        System.out.println("Tao hoa don thanh cong!!");
    }

    public int Tongtien() {
        int tong = 0;
        HoaDon[] dshd = getdsHoaDon();
        for (int i = 0; i < dshd.length; i++) {
            tong += dshd[i].getTongTien();
        }
        return tong;
    }
    
    public int Tongtientheoloai(String loaixe) {
        int tong = 0;
        HoaDon[] dshd = getdsHoaDon();
        for(int i = 0; i < dshd.length; i++) {
            Xe[] dsxhd = dshd[i].getDsXe();
            for (int j = 0; j < dsxhd.length; j++) {
                if(dsxhd[j].getLoaiXe().equals(loaixe)) tong += dsxhd[j].getPrice()*dsxhd[j].getSoLuong();
            }
        }
        return tong;   
    }

    public int Soxedaban() {
        int tong = 0;
        HoaDon[] dshd = getdsHoaDon();
        for (int i = 0; i < dshd.length; i++) {
            Xe[] dsxhd = dshd[i].getDsXe();
            for (int j = 0; j < dsxhd.length; j++) {
                tong += dsxhd[j].getSoLuong();
            }
        }
        return tong;
    }

    public void Soxedabantheoloai(String loaixe) {
        int tong = 0;
        HoaDon[] dshd = getdsHoaDon();
        for (int i = 0; i < dshd.length; i++) {
            Xe[] dsxhd = dshd[i].getDsXe();
            for (int j = 0; j < dsxhd.length; j++) {
                if(dsxhd[j].getLoaiXe().equals(loaixe)) tong += dsxhd[j].getSoLuong();
            }
        }
        System.out.println("So " + loaixe.toLowerCase() + " da ban: " + tong);
        for (int i = 0; i < dshd.length; i++) {
            Xe[] dsxhd = dshd[i].getDsXe();
            for (int j = 0; j < dsxhd.length; j++) {
                if(dsxhd[j].getLoaiXe().equals(loaixe)) {
                    System.out.println(dsxhd[j].getMaXe() + ": " + dsxhd[j].getSoLuong());
                }
            }
        }
    }

    public void Thongketheongay() {
        System.out.println("Nhap ngay loc: ");
        System.out.print("Tu: ");
        String ngtr;
        boolean check = false;
        do {
            check = true;
            ngtr = sc.nextLine();
            check = KiemTra.check_date(ngtr);
            if(check) {
                check = KiemTra.CheckDate(ngtr);
                if(!check) System.out.print("Moi nhap lai: ");
            }
        } while (!check);
        System.out.print("Den: ");
        String ngsa;
        check = false;
        do {
            check = true;
            ngsa = sc.nextLine();
            check = KiemTra.check_date(ngsa);
            if(check) {
                check = KiemTra.CheckDate(ngsa);
                if(!check) System.out.print("Moi nhap lai: ");
            }
        } while (!check);
        HoaDon[] dshd = getdsHoaDon();
        int count = 0;
        System.out.println("Ket qua tim kiem: ");
        for (int i = 0; i < dshd.length; i++) {
            if(KiemTra.sosanhngay(dshd[i].getNgaylapdon(), ngtr) >= 0 && KiemTra.sosanhngay(ngsa, dshd[i].getNgaylapdon()) >= 0 ) {
                dshd[i].xuat();
                count++;
            }
        }
        if(count == 0) System.out.println("Khong tim thay ket qua yeu cau!!!");
    }

    public void nhapDanhSach() {
        System.out.println("Moi nhap so luong hoa don:");
        
        soLuong = KiemTra.checkNumber();
        
        dsHoaDon = new HoaDon[soLuong];
        
        int stt, soLuongTemp=0, soLuongCurrent = soLuong;;
        
        for(int i=0;i<soLuongCurrent;i++){
            dsHoaDon[i] = new HoaDon();
            stt = i+1;
            System.out.println("** Hoa don thu "+stt+" **");
            
            dsHoaDon[i].nhap();
            soLuong = ++soLuongTemp;
            setdsHoaDon(dsHoaDon);
        }
    }

    
    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co hoa don nao!!");
            return;
        }
        System.out.println("=== Danh sach hoa don ===");
        for(int i=0;i<soLuong;i++) {
            dsHoaDon[i].xuat();
        }
        System.out.println();
    }

    
    public void themVaoDanhSach(PhanTu pt) {
        HoaDon[] dsHoaDonTemp = new HoaDon[soLuong+1];
        
        for(int i=0;i<soLuong;i++)
            dsHoaDonTemp[i] = dsHoaDon[i];
        dsHoaDonTemp[soLuong] = (HoaDon) pt;
        
        soLuong++;
        setdsHoaDon(dsHoaDonTemp);
    }

    
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong hoa don can them vao danh sach: ");
        int sl = KiemTra.checkNumber();
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new HoaDon();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim hoa don can chinh sua: ");
        int viTri = timViTriPhanTu();
        
        HoaDon[] dsHd = getdsHoaDon();
        
        if (viTri != -1) {
            dsHd[viTri].suaThongTin();
            setdsHoaDon(dsHd);
        } else System.out.println("Khong tim thay!");
    }

    
    public void xoaPhanTu() {
        System.out.println("Tim hoa don can xoa: ");
        
        int viTri = timViTriPhanTu();
        
        if (viTri != -1) {
            
            HoaDon[] dsHoaDonTemp = new HoaDon[soLuong-1];
            
            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue; // bỏ phần tử
                dsHoaDonTemp[k++] = dsHoaDon[i];
            }
            
            soLuong--;
            setdsHoaDon(dsHoaDonTemp);
        } else System.out.println("Khong tim thay hoa don!");
    }

    
    public PhanTu timPhanTu() { // tìm hoá đơn theo tên hoặc khoá (tương đối || tuyệt đối)
        System.out.print("Nhap so hoa don can tim: ");
        int soHoaDonCanTim = KiemTra.checkNumber();
        
        HoaDon[] dsHoaDonTmp = getdsHoaDon();
        
        for(int i=0;i<soLuong;i++) {
            if (dsHoaDonTmp[i].getSoHoaDon() == soHoaDonCanTim)
                return dsHoaDonTmp[i];
        }
        
        return null;
    }
    
    
    public int timViTriPhanTu() {
        System.out.print("Nhap so hoa don can tim: ");
        int soHoaDonCanTim = KiemTra.checkNumber();
        
        HoaDon[] dsHoaDonTmp = getdsHoaDon();
        
        for(int i=0;i<soLuong;i++) {
            if (dsHoaDonTmp[i].getSoHoaDon() == soHoaDonCanTim)
                return i;
        }
        return -1;
    }
    
    
    public PhanTu layPhanTuVoi(String thamSo) {
        HoaDon[] dsHoaDonTmp = getdsHoaDon();
        
        for(int i=0;i<soLuong;i++) {
            if (dsHoaDonTmp[i].getSoHoaDon() == Integer.parseInt(thamSo))
                return dsHoaDonTmp[i];
        }
        return null;
    }

    
    public void thongKe() {
        int chon, n;
        String st;
        dsHoaDon = getdsHoaDon();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Thong ke tong");
            System.out.println("2. Thong ke theo loai xe");
            System.out.println("3. Thong ke theo khoang thoi gian");
            System.out.println("4. Loc hoa don co tong tien da thanh toan >= n");
            System.out.println("5. Loc khach hang co phuong thuc thanh toan xac dinh");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    System.out.println("So don da ban duoc: " + soLuong);
                    System.out.println("So xe da ban duoc: " + Soxedaban());
                    System.out.println("Tong tien da ban duoc: " + Tongtien());
                    break;
                case 2:
                    System.out.println("Chon loai xe muon thong ke: ");
                    String tmp = KiemTra.checkLoaixe();
                    Soxedabantheoloai(tmp);
                    System.out.println("Tong tien da ban duoc: " + Tongtientheoloai(tmp));
                    break;
                case 3:
                    Thongketheongay();
                    break;
                case 4:
                    System.out.print("Nhap so luong don hang can tim: ");
                    n = KiemTra.checkNumber();
                    for (HoaDon hoaDon: dsHoaDon) {
                        if (hoaDon.getTongTien() >= n) hoaDon.xuat();
                    }
                    break;
                case 5:
                    System.out.println("1. Tien mat");
                    System.out.println("2. Tai khoan ngan hang");
                    System.out.println("3. Tai khoan the tin dung");
                    System.out.println("4. Vi dien tu");
                    System.out.print("Chon phuong thuc thanh toan can tim: ");
                    n = KiemTra.checkNumber();
                    switch (n) {
                        case 2:
                            st = "CK_NganHang";
                            break;
                        case 3:
                            st = "CK_TinDung";
                            break;
                        case 4:
                            st = "CK_ViDienTu";
                            break;
                    
                        default:
                            st = "TienMat";
                            break;
                    }
                    for (HoaDon hoaDon: dsHoaDon) {
                        if (hoaDon.getPhThThanhToan().equals(st)) hoaDon.xuat();
                    }
                    break;
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
