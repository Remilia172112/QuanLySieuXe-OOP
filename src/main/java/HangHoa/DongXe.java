package HangHoa;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
public class DongXe extends PhanTu {
    private String maDanhMuc;
    private String tenDanhMuc;
    private int soLuong = 0;
    private String[] dsMaSanPham;
    private  int thangbaoHanh ;
    public DongXe() {
    }
    public DongXe(String maDanhMuc, String tenDanhMuc, int soLuong, String[] dsMaSanPham ,int thangbaoHanh) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.soLuong = soLuong;
        this.dsMaSanPham = dsMaSanPham;
        this.thangbaoHanh = thangbaoHanh;
    }
    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public void setMaDanhMuc() {
        System.out.print("Nhap ma dong xe: ");
        DanhSachXe ttds = new DanhSachXe();
        boolean check = false;
        do
        {
            maDanhMuc = sc.nextLine();
            // kiểm tra xem mã danh mục đã tồn tại hay chưa
            check = ttds.layPhanTuVoi(maDanhMuc) == null;
            if (!check) System.out.print("Ma dong xe da ton tai, moi nhap lai: ");
        } while (!check);
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public void setTenDanhMuc() {
        System.out.print("Nhap ten dong xe: ");
        maDanhMuc = sc.nextLine();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong() {
        System.out.print("Nhap so luong xe cua dong xe: ");
        soLuong = KiemTra.checkNumber();
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

    public String[] getDsMaSanPham() {
        return dsMaSanPham;
    }

    public void setDsMaSanPham(String[] dsMaSanPham) {
        this.dsMaSanPham = dsMaSanPham;
    }

    public void setDsMaSanPham() {
        System.out.println("Nhap day cac ma xe, phan cach boi dau cham phay (;):");

        String dsMaSP;

        String[] dsMaSpArr;

        DanhSachXe ttds = new DanhSachXe(); // tạo đối tượng DanhSachSanPham

        boolean check;
        do {
            check = true;
            dsMaSP = sc.nextLine(); // đọc từ input
            dsMaSpArr = dsMaSP.split(";"); // tách thành mảng bởi dấu ;

            for(int i=0;i<dsMaSpArr.length;i++) // ứng với từng phần tử mảng
                if (ttds.layPhanTuVoi(dsMaSpArr[i]) == null){ // nếu không tìm thấy
                    check = false;
                    System.out.println("Khong tim thay ma xe tai vi tri thu "+i);
                    break;
                }
            if (!check) System.out.println("Day cac ma xe khong hop le, hay nhap lai!");
        } while (!check);
        setDsMaSanPham(dsMaSpArr);
    }

    public void nhapDsMaSanPham() {
        DanhSachXe ttds = new DanhSachXe();
        String[] dsMaSp = new String[soLuong];

        Xe pt;
        
        for(int i=0;i<soLuong;i++) {

            System.out.println("Them ma xe thu "+(i+1));
            do {
                pt = (Xe) ttds.timPhanTu();

                if (pt == null) {
                    System.out.println("Khong tim thay xe!");
                    System.out.println("Ban co muon them xe moi? (1 - co, 0 - khong): ");
                    int chon = KiemTra.checkNumber();
                    if (chon == 1) ttds.themKPhanTuVaoDanhSach();

                }
                else dsMaSp[i] = pt.getMaSanPham();

            } while (pt == null);
        }
        dsMaSanPham = dsMaSp;
    }

    public void themMaSPVaoDs() {
        // thêm mã sản phẩm vào danh sách
        String[] dsMaSP = new String[soLuong+1]; // tạo mảng tạm

        // thủ tục copy từ mảng danh sách cũ
        for(int i=0;i<soLuong;i++)
            dsMaSP[i] = dsMaSanPham[i];

        Xe pt;
        String maSP;
        DanhSachXe ttds = new DanhSachXe();

        do {
            System.out.print("Nhap ma xe: ");
            maSP = sc.nextLine();

            pt = (Xe) ttds.layPhanTuVoi(maSP); // tìm sản phẩm

            if (pt == null)  // nếu không tìm thấy
                System.out.println("Khong ton tai ma xe!");
            else dsMaSP[soLuong] = pt.getMaSanPham();

        } while (pt == null);
        setDsMaSanPham(dsMaSP);
    }

    public void themKMaSPVaoDs() {
        System.out.print("Nhap so ma xe can them vao danh sach: ");
        int k;
        k = KiemTra.checkNumber();
        for(int i=0;i<k;i++)
            themMaSPVaoDs();
    }

    public void xoaMaSPKhoiDs() {
        String[] dsMaSP = new String[--soLuong];

        System.out.print("Nhap ma xe can xoa khoi danh sach: ");
        String giaTriCanXoa = sc.nextLine();

        boolean check = false;
        for(int i=0;i<soLuong;i++)
        {
            check = dsMaSanPham[i].equalsIgnoreCase(giaTriCanXoa);
            if (check) break; // Nếu tìm thấy
        }

        if (check) {
            // thủ tục copy từ mảng danh sách cũ
            for(int i=0, k=0;i<soLuong;i++)
            {
                if (dsMaSanPham[i].equalsIgnoreCase(giaTriCanXoa)) continue; // bỏ qua phần tử
                dsMaSP[k++] = dsMaSanPham[i];
            }
            setDsMaSanPham(dsMaSP);
        } else System.out.println("Khong tim thay ma xe!");
    }
    public void xuatDongxe() {
        System.out.printf("%-20s %-20s %-10s %10s \n", "Ma Dong xe", "Ten Dong xe", "So luong", "Thang bao hanh");
        System.out.printf("%-20s %-20s %-10s %10s \n", maDanhMuc, tenDanhMuc, soLuong ,thangbaoHanh);
        System.out.println("****************************");
    }

    @Override
    public void nhap(){
        setMaDanhMuc();
        setTenDanhMuc();
        setThangbaohanh();
        System.out.println("Ban co muon them xe da co vao dong xe moi tao khong? (1 - co, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if (chon == 1) {
            setSoLuong();
            nhapDsMaSanPham();
        }
        
    }

    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-10s %10s \n", "Ma Dong xe", "Ten Dong xe", "So luong", "Thang bao hanh");
        System.out.printf("%-20s %-20s %-10s %10s \n", maDanhMuc, tenDanhMuc, soLuong ,thangbaoHanh);

        System.out.println("Danh sach xe cung dong xe: ");
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n","Ma xe", "Ten xe", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
        PhanTu pt;
        DanhSachXe ttds = new DanhSachXe();

        for(int i=0;i<dsMaSanPham.length;i++) {

            pt = ttds.layPhanTuVoi(dsMaSanPham[i]);

            if (pt != null) pt.xuat(); // tìm thấy
        }
        System.out.println("****************************");
    }

    @Override
    public void suaThongTin() {
        System.out.println("=== Sua thong tin dong xe ===");
        System.out.println("1. Sua ma dong xe");
        System.out.println("2. Sua ten dong xe");
        System.out.println("3. Them ma xe vao danh sach ma xe");
        System.out.println("4. Xoa ma xe khoi danh sach ma xe");
        System.out.println("5. Nhap moi danh sach ma xe");
        System.out.println("6. Them ma xe vao danh sach ma xe");
        System.out.println("7. Xoa ma xe khoi danh sach ma xe");
        System.out.println("0. Thoat");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: " + getMaDanhMuc());
                    setMaDanhMuc();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: " + getTenDanhMuc());
                    setTenDanhMuc();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsMaSanPham())
                        System.out.print(x + " ");

                    themKMaSPVaoDs();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsMaSanPham())
                        System.out.print(x + " ");

                    xoaMaSPKhoiDs();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsMaSanPham())
                        System.out.print(x + " ");

                    setDsMaSanPham();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: ");
                    for (String x : getDsMaSanPham())
                        System.out.print(x + " ");
                    themKMaSPVaoDs();
                    break;
                case 7:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsMaSanPham())
                        System.out.print(x + " ");

                    xoaMaSPKhoiDs();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while (chon != 0);
    }

}
