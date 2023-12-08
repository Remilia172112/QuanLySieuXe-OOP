package DanhSach;

import java.time.LocalDate;
import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.KhachHang;
import SanPham.DongXe;
import SanPham.PhanTu;
public class DanhSachDongXe implements DanhSachChung { 
    private int soLuong;
    private DongXe[] dsDongXe = getDsDongXe();

    public DanhSachDongXe() {
        dsDongXe = getDsDongXe();
    }
    public DanhSachDongXe(int soLuong , DongXe[] dsDongXe)
    {
        this.soLuong = soLuong;
        this.dsDongXe = dsDongXe;
    }
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public DongXe[] getDsDongXe() { // đọc từ file
        String data = FileHandler.docFile("dsdx.txt");
        // chia thành các phần tử mảng
        String[] dArr = data.split("\n");
        // trường hợp file rỗng
        if (dArr[0].length() == 0) setSoLuong(0);
        else setSoLuong(Integer.parseInt(dArr[0]));

        dsDongXe = new DongXe[soLuong];
        DongXe dmsp;
        int k = 0, m, sldmsp, thangBaoHanh ;
        String [] dsMaSP;
        String[] lArr;
        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");
            m = 0;

            dmsp = new DongXe();

            dmsp.setMaDongXe(lArr[m++]);
            dmsp.setTenDongXe(lArr[m++]);
            thangBaoHanh = Integer.parseInt(lArr[m++]);
            dmsp.setThangbaoHanh(thangBaoHanh);
            sldmsp = Integer.parseInt(lArr[m++]);
            dmsp.setSoLuong(sldmsp);
            dsMaSP = new String[sldmsp];
            for(int j=0;j<sldmsp;j++)
                dsMaSP[j] = lArr[m++];
            dmsp.setDsMaXe(dsMaSP);
            dsDongXe[k++] = dmsp;
        }
        return dsDongXe;
    }
    public void setDsDX(PhanTu[] dsDongXe) { // ghi vào file
        DongXe dmsp;
        String tenFile = "dsdx.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"\n", tenFile);
        for(int i=0;i<soLuong;i++) {
            dmsp = (DongXe) dsDongXe[i];
            FileHandler.themDX(dmsp.getMaDongXe(), dmsp.getTenDongXe(), dmsp.getSoLuong(), dmsp.getThangbaoHanh(), dmsp.getDsMaXe());
        }
        this.dsDongXe = (DongXe[]) dsDongXe;
    }

    public boolean Checkmadongxe(String thamSo) {
        DongXe[] dsDm = getDsDongXe();
        for(int i=0;i<soLuong;i++) {
            if (thamSo.contains(dsDm[i].getMaDongXe()))
                return true;
        }
        return false;
    }
    public int Timthangbaohanh(String thamSo) {
        DongXe[] dsDm = getDsDongXe();
        for(int i=0;i<soLuong;i++) {
            if (thamSo.contains(dsDm[i].getMaDongXe()))
                return dsDm[i].getThangbaoHanh();
        }
        return -1;
    }
    public void Checkbaohanh() {
        DanhSachKhachHang  ttdss = new DanhSachKhachHang();
        KhachHang tmp;
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
                return;
            } else tmp = (KhachHang) pt;
            
        } while(pt == null);
        String[] dsmspdm = tmp.getDsmspDamua();
        if(dsmspdm.length != 0) {
            LocalDate localDate = LocalDate.now();
            int namhientai = localDate.getYear();
            int thanghientai = localDate.getMonthValue();
            for(String a:dsmspdm) {
                String dateyearbuy = a.substring(a.length() - 7, a.length()-1);
                int namdathang = Integer.parseInt(dateyearbuy.substring(2));
                int thangdaqua = thanghientai - Integer.parseInt(dateyearbuy.substring(0, 2));
                if(namdathang > namhientai) thangdaqua += (namdathang-namhientai)*12;
                int thangbaohang = Timthangbaohanh(a);
                if(thangbaohang > thangdaqua) System.out.printf("Ma xe khach hang: " + a + "con thoi han bao hanh");
                else System.out.printf("Ma xe khach hang: " + a + "het thoi han bao hanh");
            }
        }
        else {
            System.out.println("Khach hang " + tmp.getHoten() + " chua dat xe nao!!");
        }
    }
    public void Themxevaodongxe(String maxe) {
        DongXe[] dsdx = getDsDongXe();
        int vitri = -1;
        for(int i = 0; i < dsdx.length; i++) {
            if(maxe.contains(dsdx[i].getMaDongXe())) {
                vitri = i;
                break;
            }
        }
        dsdx[vitri].themMaSPVaoDs(maxe);
        setDsDX(dsdx);
    }
    public String getLoaixe(String maxe) {
        DongXe[] dsdx = getDsDongXe();
        int vitri = -1;
        for(int i = 0; i < dsdx.length; i++) {
            if(maxe.contains(dsdx[i].getMaDongXe())) {
                vitri = i;
                break;
            }
        }
        return dsdx[vitri].getTenDongXe();
    }
    public void resetDsdx() {
        DongXe[] dsdx = getDsDongXe();
        for(int i = 0; i < dsdx.length; i++) {
            dsdx[i].setSoLuong();
            dsdx[i].setDsMaXe();
        }
        setDsDX(dsdx);
    }
    public void nhapDanhSach() {
        System.out.println("Nhap so luong dong xe: ");
        soLuong = KiemTra.checkNumber();
        dsDongXe = new DongXe[soLuong];
        int stt, soLuongTemp=0, soLuongCurrent = soLuong;
        for (int i = 0; i < soLuongCurrent; i++){
            dsDongXe[i] = new DongXe();
            stt = i+1;
            System.out.println("** Dong xe thu "+stt+" **");

            dsDongXe[i].nhap();
            soLuong = ++soLuongTemp;
            // mỗi lần đọc phần tử từ mảng sẽ ghi trực tiếp vào file kèm số lượng phần tử đã đọc
            setDsDX(dsDongXe);
        }
    }
    // Chỉ xuất dòng xe
    public void xuatDanhSachDongxe() {
        System.out.println("=== Danh sach dong xe ===");
        for (int i = 0; i < soLuong; i++){
            dsDongXe[i].xuatDongxe();
        }
        System.out.println();
    }
    
    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co dong xe nao!!");
            return;
        }
        System.out.println("=== Danh sach dong xe ===");
        for (int i = 0; i < soLuong; i++){
            dsDongXe[i].xuat();
        }
        System.out.println();
    }

    public void themVaoDanhSach(PhanTu pt) {
        DongXe[] dsDm = new DongXe[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsDm[i] = getDsDongXe()[i];
        dsDm[soLuong] = (DongXe) pt;
        soLuong++;
        setDsDX(dsDm);
    }

    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong dong xe can them vao danh sach: \n");
        int sl = KiemTra.checkNumber();
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new DongXe();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim dong xe can chinh sua: ");

        int viTri = timViTriPhanTu();

        DongXe[] dsDmSp = getDsDongXe();

        if (viTri != -1) {
            dsDmSp[viTri].suaThongTin();
            setDsDX(dsDmSp);
        } else System.out.println("Khong tim thay!");
    }

    public void xoaPhanTu() {
        System.out.println("Tim dong xe can xoa: ");

        int viTri = timViTriPhanTu();

        // Nếu tìm thấy
        if (viTri != -1) {
            DongXe[] dsDm = new DongXe[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue; // bỏ phần tử
                dsDm[k++] = getDsDongXe()[i];
            }

            soLuong--;
            setDsDX(dsDm);
        } else System.out.println("Khong tim thay dong xe!");
    }

    public PhanTu timPhanTu() { // tìm danh mục sản phẩm dựa theo tên hoặc khoá (tương đối || tuyệt đối)

        int loai;
        System.out.print("Tim dong xe theo ten (1) hay theo ma (2), vui long chon: ");
        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten dong xe can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma dong xe can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;
        DongXe[] dsDm = getDsDongXe();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác

                if (loai == 1)
                    if (dsDm[i].getTenDongXe().equalsIgnoreCase(giaTriCanTim))
                        return dsDm[i];
                if (loai == 2)
                    if (dsDm[i].getMaDongXe().equalsIgnoreCase(giaTriCanTim))
                        return dsDm[i];

            } else {

                if (loai == 1)
                    if (dsDm[i].getTenDongXe().contains(giaTriCanTim))
                        return dsDm[i];
                if (loai == 2)
                    if (dsDm[i].getMaDongXe().contains(giaTriCanTim))
                        return dsDm[i];

            }
        }
        return null;
    }

    public int timViTriPhanTu() { // trả về vị trí phần tử trong mảng
        int loai;
        System.out.print("Tim dong xe theo ten (1) hay theo ma (2), vui long chon: ");
        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;
        if (loai == 1)
            System.out.print("Nhap ten dong xe can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma dong xe can tim: ");
        String giaTriCanTim = sc.nextLine();
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;
        DongXe[] dsDm = getDsDongXe();
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsDm[i].getTenDongXe().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsDm[i].getMaDongXe().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsDm[i].getTenDongXe().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsDm[i].getMaDongXe().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public PhanTu layPhanTuVoi(String thamSo) {
        DongXe[] dsDm = getDsDongXe();
        for(int i=0;i<soLuong;i++) {
            if (dsDm[i].getMaDongXe().equalsIgnoreCase(thamSo))
                return dsDm[i];
        }
        return null;
    }

    public void thongKe() {
        int chon, n;
        dsDongXe = getDsDongXe();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc dong xe co so luong >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = KiemTra.checkNumber();

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = KiemTra.checkNumber();
                    for (DongXe dmSP: dsDongXe) {
                        if (dmSP.getSoLuong() >= n) dmSP.xuat();
                    }
                    break;
                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
