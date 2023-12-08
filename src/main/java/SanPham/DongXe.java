package SanPham;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
public class DongXe extends PhanTu {
    private String maDongxe;
    private String tenDongxe;
    private int soLuong = 0;
    private String[] dsMaXe;
    private  int thangbaoHanh ;

    public DongXe() {
    }
    
    public DongXe(String maDongxe, String tenDongxe, int soLuong, String[] dsMaXe ,int thangbaoHanh) {
        this.maDongxe = maDongxe;
        this.tenDongxe = tenDongxe;
        this.soLuong = soLuong;
        this.dsMaXe = dsMaXe;
        this.thangbaoHanh = thangbaoHanh;
    }
    public String getMaDongXe() {
        return maDongxe;
    }

    public void setMaDongXe(String maDongxe) {
        this.maDongxe = maDongxe;
    }

    public void setMaDongXe() {
        System.out.print("Nhap ma dong xe: ");
        DanhSachXe dsxe = new DanhSachXe();
        boolean check = false;
        do{
            maDongxe = sc.nextLine();
            //kiem tra ma danh dong xe co tonn` tai hay chua //
            check = dsxe.layPhanTuVoi(maDongxe) == null ;
            if (!check)
                System.out.println("Ma danh muc da ton tai! \nMoi ban nhap lai: ");
        }while(!check) ;

    }

    public String getTenDongXe() {
        return tenDongxe;
    }

    public void setTenDongXe(String tenDongxe) {
        this.tenDongxe = tenDongxe;
    }

    public void setTenDongXe() {
        System.out.print("Nhap ten dong xe: ");
        tenDongxe = sc.nextLine();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong() {
        DanhSachXe dsx = new DanhSachXe();
        Xe[] dsxtmp = dsx.getdsXe();
        soLuong = 0;
        for(int i = 0; i < dsxtmp.length; i++) {
            if(dsxtmp[i].getLoaiXe().equals(tenDongxe)) {
                soLuong++;
            }
        }
    }

    public int getThangbaoHanh() {
        return thangbaoHanh;
    }
    public void setThangbaohanh(){
        System.out.print("Nhap thoi han bao hanh theo thang: ");
        thangbaoHanh = KiemTra.checkNumber();
    }
    public void setThangbaoHanh(int baoHanh) {
        this.thangbaoHanh = baoHanh;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String[] getDsMaXe() {
        return dsMaXe;
    }

    public void setDsMaXe(String[] dsMaXe) {
        this.dsMaXe = dsMaXe;
    }

    public void setDsMaXe() {
        DanhSachXe dsx = new DanhSachXe();
        Xe[] dsxtmp = dsx.getdsXe();
        String[] dsMaSpArr = new String[soLuong];
        int m =  0;
        for(int i = 0; i < dsxtmp.length; i++) {
            if(dsxtmp[i].getLoaiXe().equals(tenDongxe)) {
                dsMaSpArr[m++] = dsxtmp[i].getMaXe();
            }
        }
        setDsMaXe(dsMaSpArr);
    }

    public void nhapDsMaXe() {
        DanhSachXe ttds = new DanhSachXe();
        String[] dsMaSp = new String[soLuong];

        Xe pt;
        
        for(int i=0;i<soLuong;i++) {

            System.out.println("Them ma xe thu "+(i+1)+":");
            do {
                pt = (Xe) ttds.timPhanTu();

                if (pt == null) {
                    System.out.println("Khong tim thay xe!");
                    System.out.println("Ban co muon them xe moi? (1 - co, 0 - khong): ");
                    int chon = KiemTra.checkNumber();
                    if (chon == 1) ttds.themKPhanTuVaoDanhSach();

                }
                else if(!pt.getLoaiXe().equals(tenDongxe)) {
                    System.out.println("Loai xe cua xe khong phu hop voi dong xe!");
                }
                else dsMaSp[i] = pt.getMaXe();

            } while (pt == null || !pt.getLoaiXe().equals(tenDongxe));
        }
        dsMaXe = dsMaSp;
    }

    public void themMaSPVaoDs() {
        // thêm mã sản phẩm vào danh sách
        String[] dsMaSP = new String[soLuong+1]; // tạo mảng tạm
        // thủ tục copy từ mảng danh sách cũ
        for(int i=0;i<soLuong;i++)
            dsMaSP[i] = dsMaXe[i];
        DanhSachXe ttds = new DanhSachXe();
        ttds.themPhanTuVaoDanhSach();
        dsMaSP[soLuong] = ttds.getdsXe()[ttds.getsoLuong()-1].getMaXe();
        soLuong++;
        setDsMaXe(dsMaSP);
    }

    public void themMaSPVaoDs(String maxe) {
        // thêm mã sản phẩm vào danh sách
        String[] dsMaSP = new String[soLuong+1]; // tạo mảng tạm
        // thủ tục copy từ mảng danh sách cũ
        for(int i=0;i<soLuong;i++) dsMaSP[i] = dsMaXe[i];
        dsMaSP[soLuong] = maxe;
        soLuong++;
        setDsMaXe(dsMaSP);
    }

    public void themKMaSPVaoDs() {
        System.out.print("Nhap so xe can them vao danh sach: ");
        int k;
        k = KiemTra.checkNumber();
        for(int i=0;i<k;i++)
            themMaSPVaoDs();
    }

    public void xoaMaSPKhoiDs() {
        DanhSachXe dsx = new DanhSachXe();
        dsx.xoaPhanTu();
        soLuong--;
        setDsMaXe();
    }

    public void xuatDongxe() {
        System.out.printf("%-20s %-20s %-10s %10s \n", "Ma Dong xe", "Ten Dong xe", "So luong", "Thang bao hanh");
        System.out.printf("%-20s %-20s %-10s %10s \n", maDongxe, tenDongxe, soLuong ,thangbaoHanh);
        System.out.println("****************************");
    }

    @Override
    public void nhap(){
        setMaDongXe();
        setTenDongXe();
        setThangbaohanh();
        setSoLuong();
        setDsMaXe();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-10s %10s \n", "Ma Dong xe", "Ten Dong xe", "So luong", "Thang bao hanh");
        System.out.printf("%-20s %-20s %-10s %10s \n", maDongxe, tenDongxe, soLuong ,thangbaoHanh);

        System.out.println("Danh sach xe cung dong xe: ");
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n","Ma xe", "Ten xe", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        PhanTu pt;
        DanhSachXe ttds = new DanhSachXe();

        for(int i=0;i<dsMaXe.length;i++) {

            pt = ttds.layPhanTuVoi(dsMaXe[i]);

            if (pt != null) pt.xuat(); // tìm thấy
        }
        System.out.println("****************************");
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin dong xe ===");
            System.out.println("1. Tao ma xe moi vao danh sach ma xe");
            System.out.println("2. Xoa ma xe khoi danh sach ma xe");
            System.out.println("3. Nhap moi danh sach ma xe");
            System.out.println("0. Thoat");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin dong xe!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: ");
                    for (String x : getDsMaXe())
                        System.out.print(x + " ");
                    themKMaSPVaoDs();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsMaXe())
                        System.out.print(x + " ");

                    xoaMaSPKhoiDs();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsMaXe())
                        System.out.print(x + " ");

                    setDsMaXe();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while (chon != 0);
    }

}
