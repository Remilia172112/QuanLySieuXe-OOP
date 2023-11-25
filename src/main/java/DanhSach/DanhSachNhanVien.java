package DanhSach;

import File.FileHandler;
import HangHoa.PhanTu;
import Nguoi.NhanVien;

public class DanhSachNhanVien implements DanhSachChung {
    private int soLuong;
    private NhanVien[] dsNhanVien = getDsNhanVien();

    public DanhSachNhanVien() {

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

            nv.setMaNhanVien(Integer.parseInt(lArr[m++]));

            nv.setHoTen(lArr[m++]);

            nv.setCmt(lArr[m++]);

            nv.setDiaChi(lArr[m++]);

            nv.setSoDienThoai(lArr[m++]);

            nv.setNgayvaolam(lArr[m++]);

            nv.setHesoluong(Double.parseDouble(lArr[m++]));

            nv.setSoNgayNghiTrongThang(Integer.parseInt(lArr[m++]));

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
            FileHandler.themNv(nv.getMaNhanVien(), nv.getHoTen(), nv.getCmt(), nv.getDiaChi(), nv.getSoDienThoai(),
                    nv.getNgayvaolam(), nv.getHesoluong(), nv.getSoNgayNghiTrongThang());
        }
        this.dsNhanVien = (NhanVien[]) dsNhanVien;
    }

    @Override
    public void nhapDanhSach() {
        FileHandler.resetFile("dsnv.txt");
        System.out.println("Nhap so luong nhan vien: ");

        soLuong = Integer.parseInt(sc.nextLine());
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

    @Override
    public void xuatDanhSach() {
        System.out.println("=== Danh sach nhan vien ===");
        for (int i = 0; i < soLuong; i++) {
            getDsNhanVien()[i].xuat();
        }
        System.out.println();
    }

    @Override
    public void themVaoDanhSach(PhanTu pt) {
        // tạo mảng tạm
        NhanVien[] dsNhanVienTmp = new NhanVien[soLuong + 1];
        for (int i = 0; i < soLuong; i++)
            dsNhanVienTmp[i] = dsNhanVien[i];
        dsNhanVienTmp[soLuong] = (NhanVien) pt;
        soLuong++;
        setDsNhanVien(dsNhanVienTmp);
    }

    @Override
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong nhan vien can them vao danh sach: ");
        int sl = Integer.parseInt(sc.nextLine());
        PhanTu pt;
        for (int i = 0; i < sl; i++) {
            pt = new NhanVien();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    @Override
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

    @Override
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

            soLuong--;
            setDsNhanVien(dsNhanVienTmp);
        } else
            System.out.println("Khong tim thay nhan vien!");
    }

    @Override
    public PhanTu timPhanTu() { // tìm nhân viên theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim nhan vien theo ten (1) hay theo ma (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten nhan vien can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma nhan vien can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;

        NhanVien[] dsNhanVienTmp = getDsNhanVien();

        for (int i = 0; i < soLuong; i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoTen().equalsIgnoreCase(giaTriCanTim))
                        return dsNhanVienTmp[i];
                if (loai == 2)
                    if (dsNhanVienTmp[i].getMaNhanVien() == Integer.parseInt(giaTriCanTim))
                        return dsNhanVienTmp[i];
            } else {
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoTen().contains(giaTriCanTim))
                        return dsNhanVienTmp[i];
                if (loai == 2)
                    if (dsNhanVienTmp[i].getMaNhanVien() == Integer.parseInt(giaTriCanTim))
                        return dsNhanVienTmp[i];
            }
        }
        return null;
    }

    @Override
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim nhan vien theo ten (1) hay theo ma (2), vui long chon: ");

        loai = Integer.parseInt(sc.nextLine());
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten nhan vien can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma nhan vien can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = Integer.parseInt(sc.nextLine());
        chon = (chon != 2) ? 1 : 2;

        NhanVien[] dsNhanVienTmp = getDsNhanVien();

        for (int i = 0; i < soLuong; i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoTen().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsNhanVienTmp[i].getMaNhanVien() == Integer.parseInt(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsNhanVienTmp[i].getHoTen().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsNhanVienTmp[i].getMaNhanVien() == Integer.parseInt(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    @Override
    public PhanTu layPhanTuVoi(String thamSo) {
        NhanVien[] dsnv = getDsNhanVien();
        for (int i = 0; i < soLuong; i++) {
            if (dsnv[i].getMaNhanVien() == Integer.parseInt(thamSo))
                return dsnv[i];
        }
        return null;
    }

    @Override
    public void thongKe() {
        int chon;

        dsNhanVien = getDsNhanVien();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc nhan vien theo nam vao lam");
            System.out.println("2. Loc nhan vien theo hang");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = Integer.parseInt(sc.nextLine());
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
