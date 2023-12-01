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
        int k = 0, m;
        String []lArr;

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");

            m = 0;

            pn = new PhieuNhap();

            pn.setMaPhieuNhap(lArr[m++]);

            pn.setMaNhaCC(lArr[m++]);

            pn.setMaNV(lArr[m++]);

            pn.setNgaynhap(lArr[m++]);

            pn.setTongnhap(Integer.parseInt(lArr[m++]));

            dsPhieuNhap[k++] = pn;
        }

        return dsPhieuNhap;
    }
    public void setdsSanPham( PhieuNhap[] dsPhieuNhap){ // ghi file
        PhieuNhap pn;
        String tenFile = "./dssp.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i=0;i<soLuong;i++) {
            pn = (PhieuNhap) dsPhieuNhap[i];
            FileHandler.themPN(pn.getMaPhieuNhap() , pn.getMaNhaCC() , pn.getMaNV(), pn.getNgaynhap() , pn.getTongnhap());
        }

        this.dsPhieuNhap = (PhieuNhap[]) dsPhieuNhap;
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
        System.out.printf("%-20s %-50s %-20s %-20s %-20s  \n","Ma phieu nhap", "Ma nha cung cap", "Ma nha vien", "Ngay nhap", "Tong nhap");
        for(int i=0;i<soLuong;i++) {
            dsPhieuNhap[i].xuat();
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
        // int loai;
        // System.out.print("Tim san pham theo ten (1) hay theo khoa (2), vui long chon: ");

        // loai = KiemTra.checkNumber();
        // loai = (loai != 2) ? 1 : 2;

        // if (loai == 1)
        //     System.out.print("Nhap ten san pham can tim: ");
        // if (loai == 2)
        //     System.out.print("Nhap ma san pham can tim: ");

        // String giaTriCanTim = sc.nextLine();
        // int chon;

        // System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        // chon = KiemTra.checkNumber();
        // chon = (chon != 2) ? 1 : 2;

        // PhieuNhap[] dsSanPhamTmp = getdsPhieuNhap();

        // for(int i=0;i<soLuong;i++) {
        //     if (chon == 1) { // tìm chính xác
        //         if (loai == 1)
        //             if (dsSanPhamTmp[i].getTenSanPham().equalsIgnoreCase(giaTriCanTim))
        //                 return dsSanPhamTmp[i];
        //         if (loai == 2)
        //             if (dsSanPhamTmp[i].getMaSanPham().equalsIgnoreCase(giaTriCanTim))
        //                 return dsSanPhamTmp[i];
        //     } else {
        //         if (loai == 1)
        //             if (dsSanPhamTmp[i].getTenSanPham().contains(giaTriCanTim))
        //                 return dsSanPhamTmp[i];
        //         if (loai == 2)
        //             if (dsSanPhamTmp[i].getMaSanPham().contains(giaTriCanTim))
        //                 return dsSanPhamTmp[i];
        //     }
        // }
        return null;
    }

    @Override
    public int timViTriPhanTu() {
        // int loai;
        // System.out.print("Tim phieu nhap theo ten (1) hay theo khoa (2), vui long chon: ");

        // loai = KiemTra.checkNumber();;
        // loai = (loai != 2) ? 1 : 2;
        // if (loai == 1)
        //     System.out.print("Nhap ten san pham can tim: ");
        // if (loai == 2)
        //     System.out.print("Nhap ma san pham can tim: ");
        // String giaTriCanTim = sc.nextLine();
        // int chon;
        // System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        // chon = KiemTra.checkNumber();;
        // chon = (chon != 2) ? 1 : 2;

        // PhieuNhap[] dsSanPhamTmp = getdsPhieuNhap();
        // for(int i=0;i<soLuong;i++) {
        //     if (chon == 1) { // tìm chính xác
        //         if (loai == 1)
        //             if (dsSanPhamTmp[i].getTenSanPham().equalsIgnoreCase(giaTriCanTim))
        //                 return i;
        //         if (loai == 2)
        //             if (dsSanPhamTmp[i].getMaSanPham().equalsIgnoreCase(giaTriCanTim))
        //                 return i;
        //     } else {
        //         if (loai == 1)
        //             if (dsSanPhamTmp[i].getTenSanPham().contains(giaTriCanTim))
        //                 return i;
        //         if (loai == 2)
        //             if (dsSanPhamTmp[i].getMaSanPham().contains(giaTriCanTim))
        //                 return i;
        //     }
        // }
        // return -1;


        System.out.print("Tim phieu nhap theo ma phieu nhap, hay nhap mapn: ");
        String giaTriCanTim = sc.nextLine();
        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();;
        chon = (chon != 2) ? 1 : 2;
        PhieuNhap[] dsSanPhamTmp = getdsPhieuNhap();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (dsSanPhamTmp[i].getMaPhieuNhap().equalsIgnoreCase(giaTriCanTim))
                    return i;
            } else {
                if (dsSanPhamTmp[i].getMaPhieuNhap().contains(giaTriCanTim))
                    return i;
            }
        }
        return -1;
    }

    public int timViTriSanPham(String maSanPham) { // tìm vị trí  với mã phieu nhap 
        // for(int i=0;i<soLuong;i++) {
        //     if (dsSanPhamTmp[i].getMaSanPham().equalsIgnoreCase(maSanPham))
        //         return i;
        // }
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
    //     int chon,n;
    //     dsPhieuNhap = getdsPhieuNhap();
    //     do {
    //         System.out.println("=== Thong ke ===");
    //         System.out.println("1. In san pham co so luong lon hon n");
    //         System.out.println("2. In san pham co gia ban lon hon n");
    //         System.out.println("0. Quay lai menu truoc");
    //         System.out.print("Moi chon: ");
    //         chon = KiemTra.checkNumber();;
    //         switch (chon) {
    //             case 1:
    //                 System.out.print("Nhap so luong can tim: ");
    //                 n = KiemTra.checkNumber();;
    //                 for(int i=0;i<soLuong;i++) {
    //                     if(dsPhieuNhap[i].getSoLuong() > n){
    //                         dsPhieuNhap[i].xuat();
    //                     }
    //                 }
    //                 break;
    //             case 2:
    //                 System.out.print("Nhap gia ban can tim: ");
    //                 n = KiemTra.checkNumber();;
    //                 for(int i=0;i<soLuong;i++) {
    //                     if(dsPhieuNhap[i].getPrice() > n){
    //                         dsPhieuNhap[i].xuat();
    //                     }
    //                 }
    //                 break;
    //             default:
    //                 chon=0;
    //                 break;
    //         }
    //     } while(chon!=0);
    }
}
