package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import Nguoi.*;
import SanPham.PhanTu;

public class DanhSachTaiKhoan implements DanhSachChung {
    private int soLuong;
    private TaiKhoan[] dsTaiKhoan;
    public DanhSachTaiKhoan() {
        dsTaiKhoan = getDsTaiKhoan();
    }

    public DanhSachTaiKhoan(int soLuong, TaiKhoan[] dsTaiKhoan) {
        this.soLuong = soLuong;
        this.dsTaiKhoan = dsTaiKhoan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public TaiKhoan[] getDsTaiKhoan() { // đọc từ file
        String data = FileHandler.docFile("dstk.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file

        // nếu file rỗng
        if (dArr[0].length() == 0)
            setSoLuong(0);
        else
            setSoLuong(Integer.parseInt(dArr[0]));

        dsTaiKhoan = new TaiKhoan[soLuong];
        TaiKhoan tk;
        int k = 0, m;

        String[] lArr;

        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");
            m = 0;
            tk = new TaiKhoan();
            tk.setUsername(lArr[m++]);
            tk.setPassword(lArr[m++]);
            tk.setType(lArr[m++]);
            dsTaiKhoan[k++] = tk;
        }
        return dsTaiKhoan;
    }

    public void setDsTaiKhoan(PhanTu[] dsSanPham) { // ghi file
        TaiKhoan tk;
        String tenFile = "dstk.txt";

        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong + "", tenFile);

        for (int i = 0; i < soLuong; i++) {
            tk = (TaiKhoan) dsSanPham[i];
            FileHandler.themTK(tk.getUsername(), tk.getPassword(), tk.getType());
        }
        this.dsTaiKhoan = (TaiKhoan[]) dsTaiKhoan;
    }
    public PhanTu layPhanTuVoiPassword(String thamSo) {
        TaiKhoan[] dsnv = getDsTaiKhoan();
        for (int i = 0; i < soLuong; i++) {
            if (dsnv[i].getPassword() == thamSo)
                return dsnv[i];
        }
        return null;
    }
    public TaiKhoan checkLogin(String username, String password) {
        TaiKhoan check;
        check = (TaiKhoan) layPhanTuVoi(username);
        if(check == null) {
            System.out.println("Khong ton tai tai khoan");
            return null;
        }
        if(!check.getPassword().equals(password)) {
            System.out.println("Sai mat khau");
            return null;
        }
        return check;
    }

    public void themPhanTuVaoDanhSach() {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        if(dsnv.getSoLuong() == soLuong) {
            System.out.print("Tat ca nhan vien da co tai khoan");
            return;
        }    
        PhanTu pt;
        pt = new TaiKhoan();
        pt.nhap();
        themVaoDanhSach(pt);
    }

    public void themPhanTuVaoDanhSach(String username, String type) {  
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        if(dsnv.getSoLuong() == soLuong) {
            System.out.print("Tat ca nhan vien da co tai khoan");
            return;
        }
        TaiKhoan pt;
        pt = new TaiKhoan();
        pt.nhap(username, type);
        themVaoDanhSach((PhanTu) pt);
    }

    public void changePasswordds(String username) {
        TaiKhoan[] dsnv = getDsTaiKhoan();
        int vitri = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsnv[i].getUsername().equals(username)) {
                vitri = i;
                break;
            }
        }
        if(vitri != -1) dsnv[vitri].changePassword(username);
        setDsTaiKhoan(dsnv);
    }

    
    public void nhapDanhSach(){
        FileHandler.resetFile("dstk.txt");
        System.out.println("Nhap so luong tai khoan: ");

        soLuong = KiemTra.checkNumber();
        dsTaiKhoan = new TaiKhoan[soLuong];

        int stt, soLuongTemp = 0, soLuongCurrent = soLuong;
        for (int i = 0; i < soLuongCurrent; i++) {
            dsTaiKhoan[i] = new TaiKhoan();
            stt = i + 1;

            System.out.println("** Nhan vien thu " + stt + " **");
            dsTaiKhoan[i].nhap();

            soLuong = ++soLuongTemp;
            setDsTaiKhoan(dsTaiKhoan);
        }
    }
    public void xuatDanhSach(){
        if(soLuong == 0) {
            System.out.println("Chua co tai khoan nao!!");
            return;
        }
        System.out.println("=== Danh sach tai khoan nhan vien ===");
        for (int i = 0; i < soLuong; i++) {
            getDsTaiKhoan()[i].xuat();
        }
        System.out.println();
    }
    public void themVaoDanhSach(PhanTu pt){
        TaiKhoan[] dsTaiKhoanTmp = new TaiKhoan[soLuong + 1];
        for (int i = 0; i < soLuong; i++)
            dsTaiKhoanTmp[i] = getDsTaiKhoan()[i];
        dsTaiKhoanTmp[soLuong] = (TaiKhoan) pt;
        soLuong++;
        setDsTaiKhoan(dsTaiKhoanTmp);
    }
    public void themKPhanTuVaoDanhSach() {
        DanhSachNhanVien dsnv = new DanhSachNhanVien();
        if(dsnv.getSoLuong() == soLuong) {
            System.out.println("Tat ca nhan vien da co tai khoan!!");
            return;
        }
        System.out.print("Nhap so luong tai khoan can them vao danh sach: ");
        int sl;
        boolean check = false;
        do {
            sl = KiemTra.checkNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
        PhanTu pt;
        for (int i = 0; i < sl; i++) {
            pt = new TaiKhoan();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }
    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim tai khoan can chinh sua: ");
        int viTri = timViTriPhanTu();

        TaiKhoan[] dsnv = getDsTaiKhoan();
        // tìm thấy
        if (viTri != -1) {
            dsnv[viTri].suaThongTin();
            setDsTaiKhoan(dsnv);
        } else
            System.out.println("Khong tim thay!");
    }
    public void chinhSuaThongTinPhanTu(String username, String type) {
        TaiKhoan[] dsnv = getDsTaiKhoan();
        int viTri = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsnv[i].getUsername().equals(username)) {
                viTri = i;
                break;
            }
        }
        // tìm thấy
        if (viTri != -1) {
            dsnv[viTri].setUsername(username);
            dsnv[viTri].setType(type);
            setDsTaiKhoan(dsnv);
        }
    }
    public void xoaPhanTu(String mnv) {
        TaiKhoan[] dstktmp = getDsTaiKhoan();
        int viTri = -1;
        for (int i = 0; i < dstktmp.length; i++) if(dstktmp[i].getUsername().equals(mnv)) {
            viTri = i;
            break;
        }
        // Nếu tìm thấy
        if (viTri != -1) {
            TaiKhoan[] dsTaiKhoanTmp = new TaiKhoan[soLuong - 1];

            for (int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri)
                    continue; // bỏ phần tử
                dsTaiKhoanTmp[k++] = getDsTaiKhoan()[i];
            }
            soLuong--;
            setDsTaiKhoan(dsTaiKhoanTmp);
        }
    }
    public void xoaPhanTu() {
        // Tìm tài khoản trước
        System.out.println("Tim tai khoan can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            TaiKhoan[] dsTaiKhoanTmp = new TaiKhoan[soLuong - 1];

            for (int i = 0, k = 0; i < soLuong; i++) {
                if (i == viTri)
                    continue; // bỏ phần tử
                dsTaiKhoanTmp[k++] = getDsTaiKhoan()[i];
            }

            soLuong--;
            setDsTaiKhoan(dsTaiKhoanTmp);
            System.out.println("Xoa thanh cong!!!");
        } else
            System.out.println("Khong tim thay tai khoan!");
    }
    public PhanTu timPhanTu() { 
        int loai;
        System.out.print("Tim tai khoan theo tai khoan (1) hay theo mat khau (2), vui long chon: ");

        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten tai khoan can tim: ");
        if (loai == 2)
            System.out.print("Nhap mat khau tai khoan can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        TaiKhoan[] dsTaiKhoanTmp = getDsTaiKhoan();

        for (int i = 0; i < soLuong; i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsTaiKhoanTmp[i].getUsername().equalsIgnoreCase(giaTriCanTim))
                        return dsTaiKhoanTmp[i];
                if (loai == 2)
                    if (dsTaiKhoanTmp[i].getPassword().equals(giaTriCanTim))
                        return dsTaiKhoanTmp[i];
            } else {
                if (loai == 1)
                    if (dsTaiKhoanTmp[i].getUsername().contains(giaTriCanTim))
                        return dsTaiKhoanTmp[i];
                if (loai == 2)
                    if (dsTaiKhoanTmp[i].getPassword().equals(giaTriCanTim))
                        return dsTaiKhoanTmp[i];
            }
        }
        return null;
    }
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim tai khoan theo tai khoan (1) hay theo mat khau (2), vui long chon: ");

        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten tai khoan can tim: ");
        if (loai == 2)
            System.out.print("Nhap mat khau tai khoan can tim: ");

        String giaTriCanTim = sc.nextLine();

        int chon;
        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");

        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        TaiKhoan[] dsTaiKhoanTmp = getDsTaiKhoan();

        for (int i = 0; i < soLuong; i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsTaiKhoanTmp[i].getUsername().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsTaiKhoanTmp[i].getPassword().equals(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsTaiKhoanTmp[i].getUsername().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsTaiKhoanTmp[i].getPassword().equals(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }
    public PhanTu layPhanTuVoi(String thamSo) {
        TaiKhoan[] dsnv = getDsTaiKhoan();
        for (int i = 0; i < soLuong; i++) {
            if (dsnv[i].getUsername().equals(thamSo)) return dsnv[i];
        }
        return null;
    }
    public void thongKe() {
        int chon;
        dsTaiKhoan = getDsTaiKhoan();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. Loc tai khoan theo loai tai khoan");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    String type;
                    System.out.print("Nhap loai can loc: ");
                    String find = sc.nextLine();
                    if(!find.equals("nhan vien") && !find.equals("quan ly")) {
                        System.out.println("Nhap sai loai tai khoan!!");
                        break;
                    }
                    for (int i = 0; i < soLuong; i++) {
                        type = dsTaiKhoan[i].getType();
                        if (find.equalsIgnoreCase(type)) {
                            dsTaiKhoan[i].xuat();
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
