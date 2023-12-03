package DanhSach;

import File.FileHandler;
import HangHoa.PhanTu;
import KiemTra.KiemTra;
import HangHoa.NhaCungCap;

public class DanhSachNhaCungCap implements DanhSachChung {
    private int soLuong;
    private NhaCungCap[] dsNhaCC;

    public DanhSachNhaCungCap() {
        dsNhaCC = getdsNhaCC();
    }

    public int getSoLuong(){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public NhaCungCap[] getdsNhaCC() { // đọc từ file
        String data = FileHandler.docFile("./dsncc.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file

        // nếu file rỗng
        if (dArr[0].length() == 0)
            setSoLuong(0);
        else
            setSoLuong(Integer.parseInt(dArr[0]));
        dsNhaCC = new NhaCungCap[soLuong];
        NhaCungCap ncc;
        int k = 0, m;
        String []lArr;

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");

            m = 0;

            ncc = new NhaCungCap();

            ncc.setMaNhaCC(lArr[m++]);

            ncc.setTenNhaCC(lArr[m++]);

            ncc.setDiaChi(lArr[m++]);

            ncc.setSdt(lArr[m++]);

            dsNhaCC[k++] = ncc;
        }

        return dsNhaCC;
    }

    public void setdsNhaCC( NhaCungCap[] dsNhaCC){ // ghi file
        NhaCungCap ncc;
        String tenFile = "dsncc.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i=0;i<soLuong;i++) {
            ncc = (NhaCungCap) dsNhaCC[i];
            FileHandler.themNCC(ncc.getMaNhaCC(), ncc.getTenNhaCC(), ncc.getDiaChi(), ncc.getSdt());
        }
        this.dsNhaCC = (NhaCungCap[]) dsNhaCC;
    }



    @Override
    public void nhapDanhSach() {
        FileHandler.resetFile("dsncc.txt");
        System.out.print("Moi nhap so luong nha cung cap: ");

        soLuong = Integer.parseInt(sc.nextLine());
        dsNhaCC = new NhaCungCap[soLuong];

        int stt, soLuongTemp=0, soLuongCurrent = soLuong;

        for(int i=0;i<soLuongCurrent;i++){
            dsNhaCC[i] = new NhaCungCap();
            stt = i+1;
            System.out.println("Nha cung cap thu "+stt+": ");
            dsNhaCC[i].nhap();
            soLuong = ++soLuongTemp;
            setdsNhaCC(dsNhaCC);
        }
    }
    public void xuatDanhSach() {
        System.out.println();
        System.out.println("=== Danh sach nha cung cap ===");
        System.out.printf("%-20s %-20s %-50s %-20s \n","Ma nha cung cap", "Ten nha cung cap", "Dia chi", "So dien thoai");
        for(int i=0;i<soLuong;i++) {
            // dsNhaCC[i].xuat();
            getdsNhaCC()[i].xuat();
        }
        System.out.println();
    }
    public void themVaoDanhSach(PhanTu pt) {
        NhaCungCap[] dsNhaCCTemp = new NhaCungCap[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsNhaCCTemp[i] = dsNhaCC[i];
        dsNhaCCTemp[soLuong] = (NhaCungCap) pt;
        soLuong++;
        setdsNhaCC(dsNhaCCTemp);
    }
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong nha cung cap can them vao danh sach: ");
        int sl = Integer.parseInt(sc.nextLine());
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new NhaCungCap();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim nha cung cap can chinh sua: ");
        int viTri = timViTriPhanTu();

        NhaCungCap[] dssp = getdsNhaCC();

        if (viTri != -1) {
            dssp[viTri].suaThongTin();
            setdsNhaCC(dsNhaCC);
        }
        else System.out.println("Khong tim thay nha cung cap!");
    }
    public void xoaPhanTu() {
        // Tìm sản phẩm trước
        System.out.println("Tim nha cung cap can xoa !");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            NhaCungCap[] dsNhaCCTemp = new NhaCungCap[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue;// bỏ phần tử
                dsNhaCCTemp[k++] = getdsNhaCC()[i];
            }

            soLuong--;
            setdsNhaCC(dsNhaCCTemp);
        } else System.out.println("Khong tim thay nha cung cap!");
    }
    public PhanTu timPhanTu() { // tìm theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim nha cc theo ten (1) hay theo khoa (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten nha cc can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma nha cc can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Tim chinh xac (1)/ tim tuong doi (2), vui long chon: ");
        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;

        NhaCungCap[] dsSanPhamTmp = getdsNhaCC();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return dsSanPhamTmp[i];
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return dsSanPhamTmp[i];
            } else {
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenNhaCC().contains(giaTriCanTim))
                        return dsSanPhamTmp[i];
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaNhaCC().contains(giaTriCanTim))
                        return dsSanPhamTmp[i];
            }
        }
        return null;
    }
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim nha cung cap theo ten (1) hay theo khoa (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten nha cung cap can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma nha cung can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;

        NhaCungCap[] dsSanPhamTmp = getdsNhaCC();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsSanPhamTmp[i].getMaNhaCC().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaNhaCC().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }
    public int timViTriSanPham(String maSanPham) { // tìm vị trí với mã ncc
        NhaCungCap[] dsSanPhamTmp = getdsNhaCC();
        for(int i=0;i<soLuong;i++) {
            if (dsSanPhamTmp[i].getTenNhaCC().equalsIgnoreCase(maSanPham))
                return i;
        }
        return -1;
    }
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử cụ thể với mã ncc
        NhaCungCap[] dssp = getdsNhaCC();
        for(int i=0;i<soLuong;i++) {
            if (dssp[i].getMaNhaCC().equalsIgnoreCase(thamSo))
                return dssp[i];
        }
        return null;
    }
    public void thongKe() {
        int chon;
        dsNhaCC = getdsNhaCC();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In Nha cung cap theo ma nha cung cap ");
            System.out.println("2. In Nha cung cap theo so dien thoai");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            NhaCungCap[] dspn = getdsNhaCC();
            String giatricanloc;
            boolean check;
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 1:
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
                        System.out.println("Ma nhan vien ko ton tai.");
                        break;
                    } else{
                        for(int i=0;i<soLuong;i++) {
                            if (dspn[i].getMaNhaCC().equalsIgnoreCase(giatricanloc))
                                dspn[i].xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap so dien thoai cap can loc: ");
                    giatricanloc = sc.nextLine();
                    check = false;
                    for(int i=0;i<soLuong;i++){
                        if ((dspn[i].getSdt().equalsIgnoreCase(giatricanloc))){
                            check = true;
                            break;
                        } 
                    }
                    if (!check) {
                        System.out.println("Ngay nay khong co phieu nhap.");
                        break;
                    } else{
                        for(int i=0;i<soLuong;i++) {
                            if (dspn[i].getSdt().equalsIgnoreCase(giatricanloc))
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
