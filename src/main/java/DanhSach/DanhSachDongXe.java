package DanhSach;
import File.FileHandler;
import HangHoa.DongXe;
import HangHoa.PhanTu;
import KiemTra.KiemTra;

public class DanhSachDongXe implements DanhSachChung{
    private int soLuong;
    private DongXe[] dsDanhMucSP = getDsDanhMucSP();

    public DanhSachDongXe() {
        dsDanhMucSP = getDsDanhMucSP();
    }
    public DanhSachDongXe(int soLuong, DongXe[] dsDanhMucSP) {
        this.soLuong = soLuong;
        this.dsDanhMucSP = dsDanhMucSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public DongXe[] getDsDanhMucSP() { // đọc từ file
        String data = FileHandler.docFile("dsdmsp.txt");
        // chia thành các phần tử mảng
        String[] dArr = data.split("\n");
        // trường hợp file rỗng
        if (dArr[0].length() == 0) setSoLuong(0);
        else setSoLuong(Integer.parseInt(dArr[0]));

        dsDanhMucSP = new DongXe[soLuong];
        DongXe dmsp;
        int k = 0, m, sldmsp;
        String [] dsMaSP;

        String[] lArr;
        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");

            m = 0;

            dmsp = new DongXe();

            dmsp.setMaDanhMuc(lArr[m++]);
            dmsp.setTenDanhMuc(lArr[m++]);

            sldmsp = Integer.parseInt(lArr[m++]);
            dmsp.setSoLuong(sldmsp);

            dsMaSP = new String[sldmsp];

            for(int j=0;j<sldmsp;j++)
                dsMaSP[j] = lArr[m++];

            dmsp.setDsMaSanPham(dsMaSP);

            dsDanhMucSP[k++] = dmsp;
        }
        return dsDanhMucSP;
    }
    public void setDsDanhMucSP(PhanTu[] dsDanhMucSP) { // ghi vào file
        DongXe dmsp;
        String tenFile = "dsdmsp.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"\n", tenFile);
        for(int i=0;i<soLuong;i++) {
            dmsp = (DongXe) dsDanhMucSP[i];
            FileHandler.themDmSP(dmsp.getMaDanhMuc(), dmsp.getTenDanhMuc(), dmsp.getSoLuong(), dmsp.getDsMaSanPham());
        }
        this.dsDanhMucSP = (DongXe[]) dsDanhMucSP;
    }
    @Override
    public void nhapDanhSach() {
        System.out.println("Nhap so luong danh muc: ");
        soLuong = Integer.parseInt(sc.nextLine());

        dsDanhMucSP = new DongXe[soLuong];


        int stt, soLuongTemp=0, soLuongCurrent = soLuong;
        for (int i = 0; i < soLuongCurrent; i++){
            dsDanhMucSP[i] = new DongXe();
            stt = i+1;
            System.out.println("** Danh muc san pham thu "+stt+" **");

            dsDanhMucSP[i].nhap();
            soLuong = ++soLuongTemp;
            // mỗi lần đọc phần tử từ mảng sẽ ghi trực tiếp vào file kèm số lượng phần tử đã đọc
            setDsDanhMucSP(dsDanhMucSP);
        }
    }

    @Override
    public void xuatDanhSach() {
        System.out.println("=== Danh sach danh muc san pham ===");
        for (int i = 0; i < soLuong; i++){
            dsDanhMucSP[i].xuat();
        }
        System.out.println();
    }

    @Override
    public void themVaoDanhSach(PhanTu pt) {
        DongXe[] dsDm = new DongXe[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsDm[i] = getDsDanhMucSP()[i];
        dsDm[soLuong] = (DongXe) pt;
        soLuong++;
        setDsDanhMucSP(dsDm);
    }

    @Override
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong danh muc san pham can them vao danh sach: ");
        int sl = Integer.parseInt(sc.nextLine());
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new DongXe();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    @Override
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim danh muc san pham can chinh sua: ");

        int viTri = timViTriPhanTu();

        DongXe[] dsDmSp = getDsDanhMucSP();

        if (viTri != -1) {
            dsDmSp[viTri].suaThongTin();
            setDsDanhMucSP(dsDmSp);
        } else System.out.println("Khong tim thay!");
    }

    @Override
    public void xoaPhanTu() {
        System.out.println("Tim danh muc san pham can xoa: ");

        int viTri = timViTriPhanTu();

        // Nếu tìm thấy
        if (viTri != -1) {
            DongXe[] dsDm = new DongXe[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue; // bỏ phần tử
                dsDm[k++] = getDsDanhMucSP()[i];
            }

            soLuong--;
            setDsDanhMucSP(dsDm);
        } else System.out.println("Khong tim thay danh muc san pham!");
    }

    @Override
    public PhanTu timPhanTu() { // tìm danh mục sản phẩm dựa theo tên hoặc khoá (tương đối || tuyệt đối)

        int loai;
        System.out.print("Tim danh muc san pham theo ten (1) hay theo ma (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten danh muc san pham can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma danh muc san pham can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;
        DongXe[] dsDm = getDsDanhMucSP();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác

                if (loai == 1)
                    if (dsDm[i].getTenDanhMuc().equalsIgnoreCase(giaTriCanTim))
                        return dsDm[i];
                if (loai == 2)
                    if (dsDm[i].getMaDanhMuc().equalsIgnoreCase(giaTriCanTim))
                        return dsDm[i];

            } else {

                if (loai == 1)
                    if (dsDm[i].getTenDanhMuc().contains(giaTriCanTim))
                        return dsDm[i];
                if (loai == 2)
                    if (dsDm[i].getMaDanhMuc().contains(giaTriCanTim))
                        return dsDm[i];

            }
        }
        return null;
    }

    @Override
    public int timViTriPhanTu() { // trả về vị trí phần tử trong mảng
        int loai;
        System.out.print("Tim danh muc san pham theo ten (1) hay theo ma (2), vui long chon: ");
        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;
        if (loai == 1)
            System.out.print("Nhap ten danh muc san pham can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma danh muc san pham can tim: ");
        String giaTriCanTim = sc.nextLine();
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;
        DongXe[] dsDm = getDsDanhMucSP();
        for(int i=0;i<soLuong;i++) {
            if (chon == 1) {
                if (loai == 1)
                    if (dsDm[i].getTenDanhMuc().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsDm[i].getMaDanhMuc().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsDm[i].getTenDanhMuc().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsDm[i].getMaDanhMuc().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    @Override
    public PhanTu layPhanTuVoi(String thamSo) {
        DongXe[] dsDm = getDsDanhMucSP();
        for(int i=0;i<soLuong;i++) {
            if (dsDm[i].getMaDanhMuc().equalsIgnoreCase(thamSo))
                return dsDm[i];
        }
        return null;
    }

    @Override
    public void thongKe() {
        int chon, n;
        dsDanhMucSP = getDsDanhMucSP();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc danh muc san pham co so luong >= n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");

            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = Integer.parseInt(sc.nextLine());
                    for (DongXe dmSP: dsDanhMucSP) {
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
