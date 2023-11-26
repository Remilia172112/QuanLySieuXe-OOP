package DanhSach;

import File.FileHandler;
import HangHoa.PhanTu;
import HangHoa.NhaCungCap;
import HangHoa.NhaCungCap;
// import HangHoa.NhaCungCap;
import HangHoa.NhaCungCap;

public class DanhSachNhaCungCap implements DanhSachChung{
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

            ncc.setMaNhaCC();(lArr[m++]);

            ncc.setTenNhaCC(lArr[m++]);

            ncc.setDiaChi(lArr[m++]);

            ncc.setSdt(Integer.parseInt(lArr[m++]));

            dsNhaCC[k++] = ncc;
        }

        return dsNhaCC;
    }

    public void setdsNhaCC( NhaCungCap[] dsNhaCC){ // ghi file
        NhaCungCap ncc;
        String tenFile = "./dssp.txt";
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
        FileHandler.resetFile("dssp.txt");
        System.out.print("Moi nhap so luong san pham: ");

        soLuong = Integer.parseInt(sc.nextLine());
        dsNhaCC = new NhaCungCap[soLuong];

        int stt, soLuongTemp=0, soLuongCurrent = soLuong;

        for(int i=0;i<soLuongCurrent;i++){
            dsNhaCC[i] = new NhaCungCap();
            stt = i+1;
            System.out.println("San pham thu "+stt+": ");
            dsNhaCC[i].nhap();
            soLuong = ++soLuongTemp;
            setdsNhaCC(dsNhaCC);
        }
    }

    @Override
    public void xuatDanhSach() {
        System.out.println();
        System.out.println("=== Danh sach san pham ===");
        System.out.printf("%-20s %-50s %-20s %-20s %-20s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        for(int i=0;i<soLuong;i++) {
            dsNhaCC[i].xuat();
        }
        System.out.println();
    }

    @Override
    public void themVaoDanhSach(PhanTu pt) {
        NhaCungCap[] dsNhaCCTemp = new NhaCungCap[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsNhaCCTemp[i] = dsNhaCC[i];
        dsNhaCCTemp[soLuong] = (NhaCungCap) pt;
        soLuong++;
        setdsNhaCC(dsNhaCC);(dsNhaCCTemp);
    }

    @Override
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong san pham can them vao danh sach: ");
        int sl = Integer.parseInt(sc.nextLine());
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new NhaCungCap();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    @Override
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim san pham can chinh sua: ");
        int viTri = timViTriPhanTu();

        NhaCungCap[] dssp = getdsNhaCC();

        if (viTri != -1) {
            dssp[viTri].suaThongTin();
            setdsNhaCC(dsNhaCC);(dssp);
        }
        else System.out.println("Khong tim thay san pham!");
    }

    @Override
    public void xoaPhanTu() {
        // Tìm sản phẩm trước
        System.out.println("Tim san pham can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            NhaCungCap[] dsNhaCCTemp = new NhaCungCap[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue;// bỏ phần tử
                dsNhaCCTemp[k++] = getdsNhaCC()[i];
            }

            soLuong--;
            setdsNhaCC(dsNhaCC);(dsNhaCCTemp);
        } else System.out.println("Khong tim thay san pham!");
    }

    @Override
    public PhanTu timPhanTu() { // tìm sản phẩm theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim san pham theo ten (1) hay theo khoa (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten san pham can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma san pham can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;

        NhaCungCap[] dsSanPhamTmp = getdsNhaCC();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSanPhamTmp[i].getMaNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return dsSanPhamTmp[i];
                if (loai == 2)
                    if (dsSanPhamTmp[i].getTenNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return dsSanPhamTmp[i];
            } else {
                if (loai == 1)
                    if (dsSanPhamTmp[i].getMaNhaCC().contains(giaTriCanTim))
                        return dsSanPhamTmp[i];
                if (loai == 2)
                    if (dsSanPhamTmp[i].getTenNhaCC().contains(giaTriCanTim))
                        return dsSanPhamTmp[i];
            }
        }
        return null;
    }

    @Override
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim san pham theo ten (1) hay theo khoa (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten san pham can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma san pham can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;

        NhaCungCap[] dsSanPhamTmp = getdsNhaCC();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSanPhamTmp[i].getMaNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSanPhamTmp[i].getTenNhaCC().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsSanPhamTmp[i].getMaNhaCC().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSanPhamTmp[i].getTenNhaCC().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriSanPham(String maSanPham) { // tìm vị trí sản phẩm với mã sản phẩm
        NhaCungCap[] dsSanPhamTmp = getdsNhaCC();
        for(int i=0;i<soLuong;i++) {
            if (dsSanPhamTmp[i].getTenNhaCC().equalsIgnoreCase(maSanPham))
                return i;
        }
        return -1;
    }

    @Override
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử cụ thể với mã sản phẩm
        NhaCungCap[] dssp = getdsNhaCC();
        for(int i=0;i<soLuong;i++) {
            if (dssp[i].getTenNhaCC().equalsIgnoreCase(thamSo))
                return dssp[i];
        }
        return null;
    }

    @Override
    public void thongKe() {
        int chon,n;
        dsNhaCC = getdsNhaCC();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In san pham co so luong lon hon n");
            System.out.println("2. In san pham co gia ban lon hon n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = Integer.parseInt(sc.nextLine());
                    for(int i=0;i<soLuong;i++) {
                        if(dsNhaCC[i].getSoLuong() > n){
                            dsNhaCC[i].xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap gia ban can tim: ");
                    n = Integer.parseInt(sc.nextLine());
                    for(int i=0;i<soLuong;i++) {
                        if(dsNhaCC[i].getPrice() > n){
                            dsNhaCC[i].xuat();
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
