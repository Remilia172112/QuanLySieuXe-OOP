package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import HangHoa.SanPham;
import HangHoa.PhanTu;

public class DanhSachXe implements DanhSachChung {
    private int soLuong;
    private SanPham[] dsSanPham;

    public DanhSachXe() {
        dsSanPham = getdsSanPham();
    }

    public int getsoLuong(){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public SanPham[] getdsSanPham() { // đọc từ file
        String data = FileHandler.docFile("./dssp.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file

        // nếu file rỗng
        if (dArr[0].length() == 0)
            setSoLuong(0);
        else
            setSoLuong(Integer.parseInt(dArr[0]));
        dsSanPham = new SanPham[soLuong];
        SanPham sp;
        int k = 0, m;
        String []lArr;

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");

            m = 0;

            sp = new SanPham();

            sp.setMaSanPham(lArr[m++]);

            sp.setTenSanPham(lArr[m++]);

            sp.setThuongHieu(lArr[m++]);

            sp.setNoiSanXuat(lArr[m++]);

            sp.setSoLuong(Integer.parseInt(lArr[m++]));

            sp.setPrice(Integer.parseInt(lArr[m++]));

            dsSanPham[k++] = sp;
        }

        return dsSanPham;
    }
    public void setdsSanPham( SanPham[] dsSanPham){ // ghi file
        SanPham sp;
        String tenFile = "./dssp.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i=0;i<soLuong;i++) {
            sp = (SanPham) dsSanPham[i];
            FileHandler.themSP(sp.getMaSanPham(), sp.getTenSanPham(), sp.getThuongHieu(), sp.getNoiSanXuat(),
                    sp.getSoLuong(), sp.getPrice());
        }

        this.dsSanPham = (SanPham[]) dsSanPham;
    }
    @Override
    public void nhapDanhSach() {
        FileHandler.resetFile("dssp.txt");
        System.out.print("Moi nhap so luong san pham: ");

        soLuong = KiemTra.checkNumber();;
        dsSanPham = new SanPham[soLuong];

        int stt, soLuongTemp=0, soLuongCurrent = soLuong;

        for(int i=0;i<soLuongCurrent;i++){
            dsSanPham[i] = new SanPham();
            stt = i+1;
            System.out.println("San pham thu "+stt+": ");
            dsSanPham[i].nhap();
            soLuong = ++soLuongTemp;
            setdsSanPham(dsSanPham);
        }
    }

    @Override
    public void xuatDanhSach() {
        System.out.println();
        System.out.println("=== Danh sach san pham ===");
        System.out.printf("%-20s %-30s %-20s %-20s %-20s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        for(int i=0;i<soLuong;i++) {
            dsSanPham[i].xuat();
        }
        System.out.println();
    }

    @Override
    public void themVaoDanhSach(PhanTu pt) {
        SanPham[] dsSanPhamTemp = new SanPham[soLuong+1];
        for(int i=0;i<soLuong;i++)
            dsSanPhamTemp[i] = dsSanPham[i];
        dsSanPhamTemp[soLuong] = (SanPham) pt;
        soLuong++;
        setdsSanPham(dsSanPhamTemp);
    }

    @Override
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong san pham can them vao danh sach: ");
        int sl = KiemTra.checkNumber();;
        PhanTu pt;
        for(int i=0;i<sl;i++)
        {
            pt = new SanPham();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    @Override
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim san pham can chinh sua: ");
        int viTri = timViTriPhanTu();

        SanPham[] dssp = getdsSanPham();

        if (viTri != -1) {
            dssp[viTri].suaThongTin();
            setdsSanPham(dssp);
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
            SanPham[] dsSanPhamTemp = new SanPham[soLuong-1];

            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue;// bỏ phần tử
                dsSanPhamTemp[k++] = getdsSanPham()[i];
            }

            soLuong--;
            setdsSanPham(dsSanPhamTemp);
        } else System.out.println("Khong tim thay san pham!");
    }

    @Override
    public PhanTu timPhanTu() { // tìm sản phẩm theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim san pham theo ten (1) hay theo khoa (2), vui long chon: ");

        loai = KiemTra.checkNumber();;
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten san pham can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma san pham can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();;
        chon = (chon != 2) ? 1 : 2;

        SanPham[] dsSanPhamTmp = getdsSanPham();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenSanPham().equalsIgnoreCase(giaTriCanTim))
                        return dsSanPhamTmp[i];
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaSanPham().equalsIgnoreCase(giaTriCanTim))
                        return dsSanPhamTmp[i];
            } else {
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenSanPham().contains(giaTriCanTim))
                        return dsSanPhamTmp[i];
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaSanPham().contains(giaTriCanTim))
                        return dsSanPhamTmp[i];
            }
        }
        return null;
    }

    @Override
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim san pham theo ten (1) hay theo khoa (2), vui long chon: ");

        loai = KiemTra.checkNumber();;
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten san pham can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma san pham can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();;
        chon = (chon != 2) ? 1 : 2;

        SanPham[] dsSanPhamTmp = getdsSanPham();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenSanPham().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaSanPham().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsSanPhamTmp[i].getTenSanPham().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsSanPhamTmp[i].getMaSanPham().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriSanPham(String maSanPham) { // tìm vị trí sản phẩm với mã sản phẩm
        SanPham[] dsSanPhamTmp = getdsSanPham();
        for(int i=0;i<soLuong;i++) {
            if (dsSanPhamTmp[i].getMaSanPham().equalsIgnoreCase(maSanPham))
                return i;
        }
        return -1;
    }

    @Override
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử cụ thể với mã sản phẩm
        SanPham[] dssp = getdsSanPham();
        for(int i=0;i<soLuong;i++) {
            if (dssp[i].getMaSanPham().equalsIgnoreCase(thamSo))
                return dssp[i];
        }
        return null;
    }

    @Override
    public void thongKe() {
        int chon,n;
        dsSanPham = getdsSanPham();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In san pham co so luong lon hon n");
            System.out.println("2. In san pham co gia ban lon hon n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = KiemTra.checkNumber();;
                    for(int i=0;i<soLuong;i++) {
                        if(dsSanPham[i].getSoLuong() > n){
                            dsSanPham[i].xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap gia ban can tim: ");
                    n = KiemTra.checkNumber();;
                    for(int i=0;i<soLuong;i++) {
                        if(dsSanPham[i].getPrice() > n){
                            dsSanPham[i].xuat();
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
