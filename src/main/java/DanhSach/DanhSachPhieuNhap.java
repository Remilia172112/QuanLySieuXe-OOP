package DanhSach;

import File.FileHandler;
import HangHoa.*;
import KiemTra.KiemTra;

public class DanhSachPhieuNhap implements DanhSachChung {
    private int soLuong;
    private PhieuNhap[] dsPhieuNhap;

    public DanhSachPhieuNhap() {
        dsPhieuNhap = getdsPhieuNhap();
    }

    public int getsoLuong(){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public PhieuNhap[] getdsPhieuNhap() { // đọc từ file
        String data = FileHandler.docFile("./dspn.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file

        // nếu file rỗng
        if (dArr[0].length() == 0)
            setSoLuong(0);
        else
            setSoLuong(Integer.parseInt(dArr[0]));
        dsPhieuNhap = new PhieuNhap[soLuong];
        PhieuNhap pn;
        Xe sp;
        Xe[] dsx;
        int k = 0, m, sl;
        String []lArr;
        DanhSachXe ttdsx = new DanhSachXe();

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");

            m = 0;

            pn = new PhieuNhap();

            pn.setMaPhieuNhap(lArr[m++]);

            pn.setMaNhaCC(lArr[m++]);

            pn.setMaNV(lArr[m++]);

            pn.setNgaynhap(lArr[m++]);

            sl = Integer.parseInt(lArr[m++]);
            pn.setSoluongNhap(sl);

            dsx = new Xe[sl];
            for(int j=0;j<sl;j++) {
                sp = (Xe) ttdsx.layPhanTuVoi(lArr[m++]);
                sp.setSoLuong(Integer.parseInt(lArr[m++]));
                dsx[j] = sp;
            }
            


            dsPhieuNhap[k++] = pn;
        }

        return dsPhieuNhap;
    }
    public void setdsSanPham( PhieuNhap[] dsPhieuNhap){ // ghi file
        PhieuNhap pn;
        String tenFile = "dspn.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i=0;i<soLuong;i++) {
            pn = (PhieuNhap) dsPhieuNhap[i];
            FileHandler.themPN(pn.getMaPhieuNhap() , pn.getMaNhaCC() , pn.getMaNV(), pn.getNgaynhap() , pn.getSoluongNhap(), pn.getDsSanPham());
        }

        this.dsPhieuNhap = (PhieuNhap[]) dsPhieuNhap;
    }

    public void themPhanTuVaoDanhSach(String username) {
        PhieuNhap pt;
        pt = new PhieuNhap();
        pt.nhap(username);
        themVaoDanhSach((PhanTu) pt);
    }

    @Override
    public void nhapDanhSach() {
        FileHandler.resetFile("dspn.txt");
        System.out.print("Moi nhap so luong phieu nhap can nhap: ");

        soLuong = KiemTra.checkNumber();
        dsPhieuNhap = new PhieuNhap[soLuong];

        int stt, soLuongTemp=0, soLuongCurrent = soLuong;

        for(int i=0;i<soLuongCurrent;i++){
            dsPhieuNhap[i] = new PhieuNhap();
            stt = i+1;
            System.out.println("Phieu nhap thu "+stt+": ");
            dsPhieuNhap[i].nhap();
            soLuong = ++soLuongTemp;
            setdsSanPham(dsPhieuNhap);
        }
    }

    @Override
    public void xuatDanhSach() {
        System.out.println();
        System.out.println("=== Danh sach phieu nhap ===");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s  \n","Ma phieu nhap", "Ma nha cung cap", "Ma nha vien", "Ngay nhap", "ma sanpham","Tong nhap");
        for(int i=0;i<soLuong;i++) {
            getdsPhieuNhap()[i].xuat();
            // dsPhieuNhap[i].xuat();
        }
        System.out.println();
    }

    @Override
    public void themVaoDanhSach(PhanTu pt) {
        PhieuNhap[] dsSanPhamTemp = new PhieuNhap[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsSanPhamTemp[i] = dsPhieuNhap[i];
        dsSanPhamTemp[soLuong] = (PhieuNhap) pt;
        soLuong++;
        setdsSanPham(dsSanPhamTemp);
    }

    @Override
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong phieu nhap can them vao danh sach: ");
        int sl = KiemTra.checkNumber();
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new PhieuNhap();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    @Override
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim phieu nhap can chinh sua: ");
        int viTri = timViTriPhanTu();

        PhieuNhap[] dspn = getdsPhieuNhap();

        if (viTri != -1) {
            dspn[viTri].suaThongTin();
            setdsSanPham(dspn);
        }
        else System.out.println("Khong tim thay phieu nhap!");
    }

    @Override
    public void xoaPhanTu() {
        // Tìm sản phẩm trước
        System.out.println("Tim phieu nhap can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            PhieuNhap[] dsSanPhamTemp = new PhieuNhap[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue;// bỏ phần tử
                dsSanPhamTemp[k++] = getdsPhieuNhap()[i];
            }

            soLuong--;
            setdsSanPham(dsSanPhamTemp);
        } else System.out.println("Khong tim thay san pham!");
    }

    @Override
    public PhanTu timPhanTu() { // tìm sản phẩm theo tên hoặc khoá (tương đối || tuyệt đối)
        System.out.print("Nhap ma phieu nhap can tim:");
        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        PhieuNhap[] dsNCCTmp = getdsPhieuNhap();
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (dsNCCTmp[i].getMaPhieuNhap().equalsIgnoreCase(giaTriCanTim))
                    return dsNCCTmp[i];
            } else {
                if (dsNCCTmp[i].getMaPhieuNhap().contains(giaTriCanTim))
                    return dsNCCTmp[i];
            }
        }
        return null;
    }

    @Override
    public int timViTriPhanTu() {
        System.out.print("Nhap ma phieu nhap can tim vi tri :");
        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        PhieuNhap[] dsNCCTmp = getdsPhieuNhap();
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (dsNCCTmp[i].getMaPhieuNhap().equalsIgnoreCase(giaTriCanTim))
                    return i;
            } else {
                if (dsNCCTmp[i].getMaPhieuNhap().contains(giaTriCanTim))
                    return i;
            }
        }
        return -1;
    }


    @Override
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử cụ thể với mã phieu nhap 
        PhieuNhap[] dspn = getdsPhieuNhap();
        for(int i=0;i<soLuong;i++) {
            if (dspn[i].getMaPhieuNhap().equalsIgnoreCase(thamSo))
                return dspn[i];
        }
        return null;
    }

    @Override
    public void thongKe() { // thong kê theo ngày, theo nha cung cap 
        int chon;
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In phieu nhap nhap boi nhan vien ");
            System.out.println("2. In phieu nhap nhap vao ngay ");
            System.out.println("3. In phieu nhap nhap boi nha cung cap");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            PhieuNhap[] dspn = getdsPhieuNhap();
            String giatricanloc;
            boolean check;
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 1:
                    System.out.print("Nhap ma nhan vien can loc: ");
                    giatricanloc = sc.nextLine();
                    check = false;
                    for(int i=0;i<soLuong;i++){
                        if ((dspn[i].getMaNV().equalsIgnoreCase(giatricanloc))){
                            check = true;
                            break;
                        } 
                    }
                    if (!check) {
                        System.out.println("Ma nhan vien ko ton tai.");
                        break;
                    } else{
                        for(int i=0;i<soLuong;i++) {
                            if (dspn[i].getMaNV().equalsIgnoreCase(giatricanloc))
                                dspn[i].xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap ngay can loc: ");
                    giatricanloc = sc.nextLine();
                    check = false;
                    for(int i=0;i<soLuong;i++){
                        if ((dspn[i].getNgaynhap().equalsIgnoreCase(giatricanloc))){
                            check = true;
                            break;
                        } 
                    }
                    if (!check) {
                        System.out.println("Ngay nay khong co phieu nhap.");
                        break;
                    } else{
                        for(int i=0;i<soLuong;i++) {
                            if (dspn[i].getNgaynhap().equalsIgnoreCase(giatricanloc))
                                dspn[i].xuat();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhap ma nha cung cap can loc: ");
                    giatricanloc = sc.nextLine();
                    check = false;
                    for(int i=0;i<soLuong;i++){
                        if ((dspn[i].getMaNhaCC().equalsIgnoreCase(giatricanloc))){
                            check = true;
                            break;
                        } 
                    }
                    if (!check) {
                        System.out.println("Ngay nay khong co phieu nhap.");
                        break;
                    } else{
                        for(int i=0;i<soLuong;i++) {
                            if (dspn[i].getMaNhaCC().equalsIgnoreCase(giatricanloc))
                                dspn[i].xuat();
                        }
                    }
                    break;

                
                default:
                    chon=0;
                    break;
            }
        } while(chon!=0);
    }
}
