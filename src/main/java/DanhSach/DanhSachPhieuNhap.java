package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import SanPham.*;

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

            pn.setNgaynhap(lArr[m++]);

            pn.setMaNhaCC(lArr[m++]);

            pn.setMaNV(lArr[m++]);

            pn.setTongTien(Integer.parseInt(lArr[m++]));

            sl = Integer.parseInt(lArr[m++]);
            pn.setSoLoaiNhap(sl);

            dsx = new Xe[sl];
            for(int j=0;j<sl;j++) {
                sp = (Xe) ttdsx.layPhanTuVoi(lArr[m++]);
                sp.setSoLuong(Integer.parseInt(lArr[m++]));
                dsx[j] = sp;
            }
            pn.setDsXe(dsx);
            
            dsPhieuNhap[k++] = pn;
        }

        return dsPhieuNhap;
    }
    public void setdsXe( PhieuNhap[] dsPhieuNhap){ // ghi file
        PhieuNhap pn;
        String tenFile = "dspn.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i=0;i<soLuong;i++) {
            pn = (PhieuNhap) dsPhieuNhap[i];
            FileHandler.themPN(pn.getMaPhieuNhap(), pn.getNgaynhap(), pn.getMaNhaCC(), pn.getMaNV(), pn.getTongTien(), pn.getSoLoaiNhap(), pn.getDsXe());
        }

        this.dsPhieuNhap = (PhieuNhap[]) dsPhieuNhap;
    }

    public void themPhanTuVaoDanhSach(String username) {
        PhieuNhap pt;
        pt = new PhieuNhap();
        pt.nhap(username);
        themVaoDanhSach((PhanTu) pt);
        System.out.println("Tao phieu nhap thanh cong!!");
    }

    public int Tongtien() {
        int tong = 0;
        PhieuNhap[] dspn = getdsPhieuNhap();
        for (int i = 0; i < dspn.length; i++) {
            tong += dspn[i].getTongTien();
        }
        return tong;
    }
    
    public int Tongtientheoloai(String loaixe) {
        int tong = 0;
        PhieuNhap[] dspn = getdsPhieuNhap();
        for(int i = 0; i < dspn.length; i++) {
            Xe[] dsxpn = dspn[i].getDsXe();
            for (int j = 0; j < dsxpn.length; j++) {
                if(dsxpn[j].getLoaiXe().equals(loaixe)) tong += dsxpn[j].getPrice()*dsxpn[j].getSoLuong();
            }
        }
        return tong;   
    }

    public int Soxedaban() {
        int tong = 0;
        PhieuNhap[] dspn = getdsPhieuNhap();
        for (int i = 0; i < dspn.length; i++) {
            Xe[] dsxpn = dspn[i].getDsXe();
            for (int j = 0; j < dsxpn.length; j++) {
                tong += dsxpn[j].getSoLuong();
            }
        }
        return tong;
    }

    public void Soxedabantheoloai(String loaixe) {
        int tong = 0;
        DanhSachXe dstmp = new DanhSachXe();
        Xe[] dsx = dstmp.getdsXe();
        for (int i = 0; i < dsx.length; i++) dsx[i].setSoLuong(0);
        PhieuNhap[] dspn = getdsPhieuNhap();
        for (int i = 0; i < dspn.length; i++) {
            Xe[] dsxpn = dspn[i].getDsXe();
            for (int j = 0; j < dsxpn.length; j++) if(dsxpn[j].getLoaiXe().equals(loaixe)) {
                tong += dsxpn[j].getSoLuong();
                for(int k = 0; k < dsx.length; k++) if(dsx[k].getMaXe().equals(dsxpn[j].getMaXe())) {
                    dsx[k].setSoLuong(dsx[k].getSoLuong()+dsxpn[j].getSoLuong());
                    break;
                }
            }
        }
        System.out.println("So " + loaixe.toLowerCase() + " da nhap: " + tong);
        for (int i = 0; i < dsx.length; i++) if(dsx[i].getSoLuong() != 0) System.out.println(dsx[i].getMaXe() + ": " + dsx[i].getSoLuong());
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
        PhieuNhap[] dspn = getdsPhieuNhap();
        int count = 0;
        System.out.println("Ket qua tim kiem: ");
        for (int i = 0; i < dspn.length; i++) {
            if(KiemTra.sosanhngay(dspn[i].getNgaynhap(), ngtr) >= 0 && KiemTra.sosanhngay(ngsa, dspn[i].getNgaynhap()) >= 0 ) {
                dspn[i].xuat();
                count++;
            }
        }
        if(count == 0) System.out.println("Khong tim thay ket qua yeu cau!!!");
    }
    
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
            setdsXe(dsPhieuNhap);
        }
    }

    
    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co phieu nhap nao!!");
            return;
        }
        System.out.println("=== Danh sach phieu nhap ===");
        for(int i=0;i<soLuong;i++) {
            getdsPhieuNhap()[i].xuat();
        }
        System.out.println();
    }

    
    public void themVaoDanhSach(PhanTu pt) {
        PhieuNhap[] dsXeTemp = new PhieuNhap[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsXeTemp[i] = dsPhieuNhap[i];
        dsXeTemp[soLuong] = (PhieuNhap) pt;
        soLuong++;
        setdsXe(dsXeTemp);
    }

    
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

    
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim phieu nhap can chinh sua: ");
        int viTri = timViTriPhanTu();

        PhieuNhap[] dspn = getdsPhieuNhap();

        if (viTri != -1) {
            dspn[viTri].suaThongTin();
            setdsXe(dspn);
        }
        else System.out.println("Khong tim thay phieu nhap!");
    }

    
    public void xoaPhanTu() {
        // Tìm sản phẩm trước
        System.out.println("Tim phieu nhap can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            PhieuNhap[] dsXeTemp = new PhieuNhap[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue;// bỏ phần tử
                dsXeTemp[k++] = getdsPhieuNhap()[i];
            }

            soLuong--;
            setdsXe(dsXeTemp);
        } else System.out.println("Khong tim thay phieu nhap!");
    }

    
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


    
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử cụ thể với mã phieu nhap 
        PhieuNhap[] dspn = getdsPhieuNhap();
        for(int i=0;i<soLuong;i++) {
            if (dspn[i].getMaPhieuNhap().equalsIgnoreCase(thamSo))
                return dspn[i];
        }
        return null;
    }

    
    public void thongKe() { // thong kê theo ngày, theo nha cung cap 
        int chon;
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Thong ke tong");
            System.out.println("2. Thong ke theo loai xe");
            System.out.println("3. Thong ke theo khoang thoi gian");
            System.out.println("4. In phieu nhap nhap boi nhan vien ");
            System.out.println("5. In phieu nhap nhap vao ngay ");
            System.out.println("6. In phieu nhap nhap boi nha cung cap");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            PhieuNhap[] dspn = getdsPhieuNhap();
            String giatricanloc;
            boolean check;
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 1:
                    System.out.println("So phieu da nhap: " + soLuong);
                    System.out.println("So xe da nhap: " + Soxedaban());
                    System.out.println("Tong tien da nhap: " + Tongtien());
                    break;
                case 2:
                    System.out.println("Chon loai xe muon thong ke: ");
                    String tmplx = KiemTra.checkLoaixe();
                    Soxedabantheoloai(tmplx);
                    System.out.println("Tong tien da nhap: " + Tongtientheoloai(tmplx));
                    break;
                case 3:
                    Thongketheongay();
                    break;
                case 4:
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
                case 5:
                    System.out.print("Nhap thang can loc: ");
                    int tmp = KiemTra.checkMonth();
                    check = false;
                    for(int i=0;i<soLuong;i++){
                        if (Integer.parseInt((dspn[i].getNgaynhap().substring(3,2))) == tmp){
                            check = true;
                            break;
                        } 
                    }
                    if (!check) {
                        System.out.println("Ngay nay khong co phieu nhap.");
                        break;
                    } else{
                        for(int i=0;i<soLuong;i++) {
                            if (Integer.parseInt((dspn[i].getNgaynhap().substring(4,2))) == tmp)
                                dspn[i].xuat();
                        }
                    }
                    break;
                case 6:
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
                        System.out.println("Khong co phieu nhap co ma nha cung cap nay.");
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
