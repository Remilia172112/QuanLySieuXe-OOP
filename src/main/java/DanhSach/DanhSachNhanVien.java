package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.NhanVien;
import SanPham.PhanTu;

public class DanhSachNhanVien implements DanhSachChung {
    private int soLuong;
    private NhanVien[] dsNhanVien; 

    public DanhSachNhanVien() {
        dsNhanVien = getDsNhanVien();
    }

    public DanhSachNhanVien(int soLuong, NhanVien[] dsNhanVien) {
        this.soLuong = soLuong;
        this.dsNhanVien = dsNhanVien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public NhanVien[] getDsNhanVien() { // đọc từ file
        String data = FileHandler.docFile("dsnv.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file

        // nếu file rỗng
        if (dArr[0].length() == 0)
            setSoLuong(0);
        else
            setSoLuong(Integer.parseInt(dArr[0]));

        dsNhanVien = new NhanVien[soLuong];
        NhanVien nv;
        int k = 0, m;
        String[] lArr;
        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");
            m = 0;
            nv = new NhanVien();
            nv.setManhanvien(lArr[m++]);
            nv.setHoten(lArr[m++]);
            nv.setChucvu(lArr[m++]);
            nv.setNgaythangnamsinh(lArr[m++]);
            nv.setGioitinh(lArr[m++]);            
            nv.setCCCD(lArr[m++]);
            nv.setDiachi(lArr[m++]);
            nv.setSdt(lArr[m++]);
            nv.setEmail(lArr[m++]);
            nv.setNgayvaolam(lArr[m++]);
            nv.setHesoluong(Double.parseDouble(lArr[m++]));
            nv.setSongaynghitrongthang(Integer.parseInt(lArr[m++]));
            dsNhanVien[k++] = nv;
        }
        return dsNhanVien;
    }

    public void setDsNhanVien(PhanTu[] dsSanPham) { // ghi file
        NhanVien nv;
        String tenFile = "dsnv.txt";

        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong + "", tenFile);

        for (int i = 0; i < soLuong; i++) {
            nv = (NhanVien) dsSanPham[i];
            FileHandler.themNv(nv.getManhanvien(), nv.getHoten(), nv.getChucvu(), nv.getNgaythangnamsinh(), nv.getGioitinh(), nv.getCCCD(), nv.getDiachi(), nv.getSdt(), nv.getEmail(),
                    nv.getNgayvaolam(), nv.getHesoluong(), nv.getSongaynghitrongthang());
        }
        this.dsNhanVien = (NhanVien[]) dsNhanVien;
    }

    public void themPhanTuVaoDanhSach() {    
        PhanTu pt;
        pt = new NhanVien();
        pt.nhap();
        themVaoDanhSach(pt);
    }

    public void chinhSuaThongTinPhanTu(String mnv) {
        int viTri = -1;
        for(int i = 0; i < soLuong; i++)
        {
            if(dsNhanVien[i].getManhanvien().equals(mnv)) {
                viTri = i;
                break;
            }
        }
        NhanVien[] dsnv = getDsNhanVien();
        // tìm thấy
        if (viTri != -1) {
            dsnv[viTri].suaThongTin();
            setDsNhanVien(dsnv);
        } else
            System.out.println("Khong tim thay!");
    }


    
    public void nhapDanhSach() {
        FileHandler.resetFile("dsnv.txt");
        System.out.println("Nhap so luong nhan vien: ");

        soLuong = KiemTra.checkNumber();
        dsNhanVien = new NhanVien[soLuong];

        int stt, soLuongTemp = 0, soLuongCurrent = soLuong;
        for (int i = 0; i < soLuongCurrent; i++) {
            dsNhanVien[i] = new NhanVien();
            stt = i + 1;

            System.out.println("** Nhan vien thu " + stt + " **");
            dsNhanVien[i].nhap();

            soLuong = ++soLuongTemp;
            setDsNhanVien(dsNhanVien);
        }
    }
    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co nhan vien nao!!");
            return;
        }
        System.out.println("=== Danh sach nhan vien ===");
        for (int i = 0; i < soLuong; i++) {
            getDsNhanVien()[i].xuat();
        }
        System.out.println();
    }
    public void themVaoDanhSach(PhanTu pt) {
        // tạo mảng tạm
        NhanVien[] dsNhanVienTmp = new NhanVien[soLuong + 1];
        for (int i = 0; i < soLuong; i++)
            dsNhanVienTmp[i] = getDsNhanVien()[i];
        dsNhanVienTmp[soLuong] = (NhanVien) pt;
        soLuong++;
        setDsNhanVien(dsNhanVienTmp);
    }
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong nhan vien can them vao danh sach: ");
        int sl;
        boolean check = false;
        do {
            sl = KiemTra.checkNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
        PhanTu pt;
        for (int i = 0; i < sl; i++) {
            pt = new NhanVien();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim nhan vien can chinh sua: ");
        int viTri = timViTriPhanTu();

        NhanVien[] dsnv = getDsNhanVien();
        // tìm thấy
        if (viTri != -1) {
            dsnv[viTri].suaThongTin();
            setDsNhanVien(dsnv);
        } else
            System.out.println("Khong tim thay!");
    }
    public void xoaPhanTu() {
        // Tìm nhân viên trước
        System.out.println("Tim nhan vien can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            NhanVien[] dsNhanVienTmp = new NhanVien[soLuong - 1];

            for (int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri)
                    continue; // bỏ phần tử
                dsNhanVienTmp[k++] = getDsNhanVien()[i];
            }
            DanhSachTaiKhoan dstk = new DanhSachTaiKhoan();
            dstk.xoaPhanTu(getDsNhanVien()[viTri].getManhanvien());
            soLuong--;
            setDsNhanVien(dsNhanVienTmp);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay nhan vien!");
    }
    public PhanTu timPhanTu() { // tìm nhân viên theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim nhan vien theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten nhan vien can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma nhan vien can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        NhanVien[] dsNhanVienTmp = getDsNhanVien();

        for (int i = 0; i < soLuong; i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return dsNhanVienTmp[i];
                if (loai == 2)
                    if (dsNhanVienTmp[i].getManhanvien().equals(giaTriCanTim))
                        return dsNhanVienTmp[i];
            } else {
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoten().contains(giaTriCanTim))
                        return dsNhanVienTmp[i];
                if (loai == 2)
                    if (dsNhanVienTmp[i].getManhanvien().equals(giaTriCanTim))
                        return dsNhanVienTmp[i];
            }
        }
        return null;
    }
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim nhan vien theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten nhan vien can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma nhan vien can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        NhanVien[] dsNhanVienTmp = getDsNhanVien();

        for (int i = 0; i < soLuong; i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoten().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsNhanVienTmp[i].getManhanvien().equals(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoten().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsNhanVienTmp[i].getManhanvien().equals(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }
    public PhanTu layPhanTuVoi(String thamSo) {
        NhanVien[] dsnv = getDsNhanVien();
        for (int i = 0; i < soLuong; i++) {
            if (dsnv[i].getManhanvien().equalsIgnoreCase(thamSo) )
                return dsnv[i];
        }
        return null;
    }
    public void thongKe() {
        int chon;

        dsNhanVien = getDsNhanVien();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc nhan vien theo nam vao lam");
            System.out.println("2. Loc nhan vien theo hang");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    String[] year;
                    System.out.print("Nhap nam can loc: ");
                    String find = sc.nextLine();
                    for (int i = 0; i < soLuong; i++) {
                        year = dsNhanVien[i].getNgayvaolam().split("/");
                        for (String w : year) {
                            if (w.equals(find)) {
                                dsNhanVien[i].xuat();
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap hang can loc: ");
                    char r = sc.nextLine().charAt(0);
                    for (int i = 0; i < soLuong; i++) {
                        if (getDsNhanVien()[i].getHang() == r) {
                            getDsNhanVien()[i].xuat();
                        }
                    }
                    break;

                default:
                    chon = 0;
                    break;
            }
        } while (chon != 0);
    }
}
