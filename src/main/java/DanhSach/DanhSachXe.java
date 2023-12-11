package DanhSach;

import File.FileHandler;
import KiemTra.KiemTra;
import SanPham.PhanTu;
import SanPham.Xe;
import SanPham.XeMuiTran;
import SanPham.XeTheThao;
import SanPham.XeDien;

public class DanhSachXe implements DanhSachChung {
    private int soLuong;
    private Xe[] dsXe;

    public DanhSachXe() {
        dsXe = getdsXe();
    }

    public int getsoLuong(){
        return soLuong;
    }

    public void setSoLuong(int soLuong){
        this.soLuong = soLuong;
    }

    public Xe[] getdsXe() { // đọc từ file
        String data = FileHandler.docFile("dsx.txt");
        String[] dArr = data.split("\n"); // tạo mảng từ file

        // nếu file rỗng
        if (dArr[0].length() == 0)
            setSoLuong(0);
        else
            setSoLuong(Integer.parseInt(dArr[0]));
        dsXe = new Xe[soLuong];
        Xe sp = new Xe();
        int k = 0, m;
        String [] lArr;
        for (int i = 1; i < dArr.length; i++) {
            lArr = dArr[i].split("#");
            m = 0;
            if(lArr[3].equals("Xe the thao")) {
                sp = new XeTheThao();
            }
            else if(lArr[3].equals("Xe mui tran")) {
                sp = new XeMuiTran();
            }
            else if(lArr[3].equals("Xe dien")) {
                sp = new XeDien();
            }
            sp.setMaXe(lArr[m++]);
    
            sp.setTenXe(lArr[m++]);
    
            sp.setThuongHieu(lArr[m++]);

            sp.setLoaiXe(lArr[m++]);
    
            sp.setNoiSanXuat(lArr[m++]);
    
            sp.setSoLuong(Integer.parseInt(lArr[m++]));
    
            sp.setPrice(Integer.parseInt(lArr[m++]));
            
            if(lArr[3].equals("Xe the thao")) {
                ((XeTheThao) sp).setHangxe(lArr[m++]);
            }
            else if(lArr[3].equals("Xe mui tran")) {
                ((XeMuiTran) sp).setLoaimui(lArr[m++]);
            }
            else if(lArr[3].equals("Xe dien")) {
                ((XeDien) sp).setDungluongpin(lArr[m++]);
            }
            dsXe[k++] = sp;
            

        }
        return dsXe;
    }
    public void setdsXe( Xe[] dsXe){ // ghi file
        Xe sp;
        String tenFile = "dsx.txt";
        FileHandler.resetFile(tenFile);
        FileHandler.ghiFile(soLuong+"", tenFile);

        for(int i=0;i<soLuong;i++) {
            sp = (Xe) dsXe[i];
            if(sp instanceof XeTheThao) FileHandler.themXe(sp.getMaXe(), sp.getTenXe(), sp.getThuongHieu(), sp.getLoaiXe(), sp.getNoiSanXuat(), sp.getSoLuong(), sp.getPrice(), ((XeTheThao) sp).getHangxe());
            else if(sp instanceof XeMuiTran) FileHandler.themXe(sp.getMaXe(), sp.getTenXe(), sp.getThuongHieu(), sp.getLoaiXe(), sp.getNoiSanXuat(), sp.getSoLuong(), sp.getPrice(), ((XeMuiTran) sp).getLoaimui());
            else if(sp instanceof XeDien) FileHandler.themXe(sp.getMaXe(), sp.getTenXe(), sp.getThuongHieu(), sp.getLoaiXe(), sp.getNoiSanXuat(), sp.getSoLuong(), sp.getPrice(), ((XeDien) sp).getDungluongpin());
        }   
        this.dsXe = (Xe[]) dsXe;
    }

    public int kiemtraChuoidonhang(String maXe) { // tìm vị trí sản phẩm với mã sản phẩm
        Xe[] dsXeTmp = getdsXe();
        for(int i=0;i<soLuong;i++) {
            if (maXe.contains(dsXeTmp[i].getMaXe()))
                return i;
        }
        return -1;
    }
    
    public void nhapDanhSach() {
        FileHandler.resetFile("dsx.txt");
        System.out.print("Moi nhap so luong xe: ");

        soLuong = KiemTra.checkNumber();
        dsXe = new Xe[soLuong];

        int soLuongTemp=0, soLuongCurrent = soLuong;
        String tmp;

        for(int i=0;i<soLuongCurrent;i++){
            System.out.println("Them xe thu " + (i+1) + ": ");
            System.out.println("Chon loai xe: ");
            tmp = KiemTra.checkLoaixe();
            if(tmp.equals("Xe the thao")) {
                dsXe[i] = new XeTheThao();
                dsXe[i].nhap();
            }
            else if(tmp.equals("Xe mui tran")) {
                dsXe[i] = new XeMuiTran();
                dsXe[i].nhap();
            }
            else if(tmp.equals("Xe dien")) {
                dsXe[i] = new XeDien();
                dsXe[i].nhap();
            }
            soLuong = ++soLuongTemp;
            setdsXe(dsXe);
        }
        DanhSachDongXe dsdx = new DanhSachDongXe();
        dsdx.resetDsdx();
    }

    
    public void xuatDanhSach() {
        if(soLuong == 0) {
            System.out.println("Chua co xe nao!!");
            return;
        }
        System.out.println("=== Danh sach xe ===");
        System.out.printf("%-20s %-25s %-20s %-20s %-20s %-15s %-20s \n","Ma xe", "Ten xe", "Thuong hieu", "Loai xe", "Noi san xuat", "So luong", "Gia");
        for(int i=0;i<soLuong;i++) {
            dsXe[i].xuat();
        }
        System.out.println();
    }

    
    public void themVaoDanhSach(PhanTu pt) {
        Xe[] dsXeTemp = new Xe[soLuong+1];
        for(int i=0;i<soLuong;i++){
            dsXeTemp[i] = getdsXe()[i];
        }
        dsXeTemp[soLuong] = (Xe) pt;
        soLuong++;
        setdsXe(dsXeTemp);
    }

    
    public void themKPhanTuVaoDanhSach() {
        System.out.print("Nhap so luong xe can them vao danh sach: ");
        int sl;
        boolean check = false;
        do {
            sl = KiemTra.checkNumber();
            check = sl > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
        PhanTu pt;
        String tmp;
        for(int i=0;i<sl;i++)
        {
            System.out.println("Them xe thu " + (i+1) + ": ");
            System.out.println("Chon loai xe: ");
            tmp = KiemTra.checkLoaixe();
            if(tmp.equals("Xe the thao")) {
                pt = new XeTheThao();
                pt.nhap();
                themVaoDanhSach(pt);
            }
            else if(tmp.equals("Xe mui tran")) {
                pt = new XeMuiTran();
                pt.nhap();
                themVaoDanhSach(pt);
            }
            else if(tmp.equals("Xe dien")) {
                pt = new XeDien();
                pt.nhap();
                themVaoDanhSach(pt);
            }
        }
        DanhSachDongXe dsdx = new DanhSachDongXe();
        dsdx.resetDsdx();
    }
    
    public void themPhanTuVaoDanhSach() {
        PhanTu pt;
        String tmp;
        System.out.println("Chon loai xe: ");
        tmp = KiemTra.checkLoaixe();
        if(tmp.equals("Xe the thao")) {
            pt = new XeTheThao();
            pt.nhap();
            themVaoDanhSach(pt);
        }
        else if(tmp.equals("Xe mui tran")) {
            pt = new XeMuiTran();
            pt.nhap();
            themVaoDanhSach(pt);
        }
        else if(tmp.equals("Xe dien")) {
            pt = new XeDien();
            pt.nhap();
            themVaoDanhSach(pt);
        }
    }

    public void chinhSuaThongTinPhanTu() {
        System.out.println("Tim xe can chinh sua: ");
        int viTri = timViTriPhanTu();
        Xe[] dsx = getdsXe();
        if (viTri != -1) {
            dsx[viTri].suaThongTin();
            setdsXe(dsx);
        }
        else System.out.println("Khong tim thay xe!");
    }

    
    public void xoaPhanTu() {
        // Tìm sản phẩm trước
        System.out.println("Tim xe can xoa: ");
        int viTri = timViTriPhanTu();
        // Nếu tìm thấy
        if (viTri != -1) {
            Xe[] dsXeTemp = new Xe[soLuong-1];
            // Xóa xe ra khỏi danh sách xe
            for(int i=0, k=0;i<soLuong;i++) {
                if (i==viTri) continue;// bỏ phần tử
                dsXeTemp[k++] = getdsXe()[i];
            }
            DanhSachDongXe dsdx = new DanhSachDongXe();
            dsdx.xoaPhanTuMaXe(getdsXe()[viTri].getMaXe(), getdsXe()[viTri].getLoaiXe());
            soLuong--;
            setdsXe(dsXeTemp);
            System.out.println("Xoa thanh cong!!!");
        } else System.out.println("Khong tim thay xe!");
    }
    
    public PhanTu timPhanTu() { // tìm sản phẩm theo tên hoặc khoá (tương đối || tuyệt đối)
        int loai;
        System.out.print("Tim xe theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;

        if (loai == 1)
            System.out.print("Nhap ten xe can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma xe can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        Xe[] dsXeTmp = getdsXe();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsXeTmp[i].getTenXe().equalsIgnoreCase(giaTriCanTim))
                        return dsXeTmp[i];
                if (loai == 2)
                    if (dsXeTmp[i].getMaXe().equalsIgnoreCase(giaTriCanTim))
                        return dsXeTmp[i];
            } else {
                if (loai == 1)
                    if (dsXeTmp[i].getTenXe().contains(giaTriCanTim))
                        return dsXeTmp[i];
                if (loai == 2)
                    if (dsXeTmp[i].getMaXe().contains(giaTriCanTim))
                        return dsXeTmp[i];
            }
        }
        return null;
    }

    
    public int timViTriPhanTu() {
        int loai;
        System.out.print("Tim xe theo ten (1) hay theo ma (2), vui long chon: ");

        loai = KiemTra.checkNumber();
        loai = (loai != 2) ? 1 : 2;
        if (loai == 1)
            System.out.print("Nhap ten xe can tim: ");
        if (loai == 2)
            System.out.print("Nhap ma xe can tim: ");

        String giaTriCanTim = sc.nextLine();
        int chon;

        System.out.print("Ban can tim chinh xac (1) hay tim tuong doi (2), vui long chon: ");
        chon = KiemTra.checkNumber();
        chon = (chon != 2) ? 1 : 2;

        Xe[] dsXeTmp = getdsXe();

        for(int i=0;i<soLuong;i++) {
            if (chon == 1) { // tìm chính xác
                if (loai == 1)
                    if (dsXeTmp[i].getTenXe().equalsIgnoreCase(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsXeTmp[i].getMaXe().equalsIgnoreCase(giaTriCanTim))
                        return i;
            } else {
                if (loai == 1)
                    if (dsXeTmp[i].getTenXe().contains(giaTriCanTim))
                        return i;
                if (loai == 2)
                    if (dsXeTmp[i].getMaXe().contains(giaTriCanTim))
                        return i;
            }
        }
        return -1;
    }

    public int timViTriXe(String maXe) { // tìm vị trí sản phẩm với mã sản phẩm
        Xe[] dsXeTmp = getdsXe();
        for(int i=0;i<soLuong;i++) {
            if (dsXeTmp[i].getMaXe().equalsIgnoreCase(maXe))
                return i;
        }
        return -1;
    }

    
    public PhanTu layPhanTuVoi(String thamSo) { // tìm phần tử cụ thể với mã sản phẩm
        Xe[] dsx = getdsXe();
        for(int i=0;i<soLuong;i++) {
            if (dsx[i].getMaXe().equalsIgnoreCase(thamSo))
                return dsx[i];
        }
        return null;
    }

    
    public void thongKe() {
        int chon,n;
        dsXe = getdsXe();
        do {
            System.out.println("=== Thong ke ===");
            System.out.println("1. In xe co so luong lon hon n");
            System.out.println("2. In xe co gia ban lon hon n");
            System.out.println("0. Quay lai menu truoc");
            System.out.print("Moi chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 1:
                    System.out.print("Nhap so luong can tim: ");
                    n = KiemTra.checkNumber();
                    for(int i=0;i<soLuong;i++) {
                        if(dsXe[i].getSoLuong() > n){
                            dsXe[i].xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.print("Nhap gia ban can tim: ");
                    n = KiemTra.checkNumber();
                    for(int i=0;i<soLuong;i++) {
                        if(dsXe[i].getPrice() > n){
                            dsXe[i].xuat();
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
