package HangHoa;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
public class DongXe extends PhanTu {
    private String maDanhMuc;
    private String tenDanhMuc;
    private int soLuong;
    private String[] dsMaSanPham;
    private  int baoHanh ;
    public DongXe() {
    }
    public DongXe(String maDanhMuc, String tenDanhMuc, int soLuong, String[] dsMaSanPham ,int baoHanh) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.soLuong = soLuong;
        this.dsMaSanPham = dsMaSanPham;
        this.baoHanh = baoHanh;
    }
    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public void setMaDanhMuc() {
        System.out.print("Nhap ma danh muc: ");
        DanhSachXe ttds = new DanhSachXe();
        boolean check = false;
        do
        {
            maDanhMuc = sc.nextLine();
            // kiểm tra xem mã danh mục đã tồn tại hay chưa
            check = ttds.layPhanTuVoi(maDanhMuc) == null;
            if (!check) System.out.print("Ma danh muc da ton tai, moi nhap lai: ");
        } while (!check);
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public void setTenDanhMuc() {
        System.out.print("Nhap ten danh muc: ");
        maDanhMuc = sc.nextLine();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong() {
        System.out.print("Nhap so luong: ");
        boolean check;
        do {
            check = true;
            try {
                soLuong = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui long nhap mot so!");
                check = false;
            }
        } while(!check);
    }

    public int getBaoHanh() {
        return baoHanh;
    }
    public void setBaoHanh(){
        System.out.print("Nhap thoi han bao hanh: ");
        boolean check;
        do {
            check = true;
            try {
                baoHanh = KiemTra.checkNumber();
            } catch (Exception e) {
                System.out.println("Vui long nhap mot so!");
                check = false;
            }
        } while(!check);
    }
    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
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
        System.out.println("Nhap day cac ma san pham, phan cach boi dau cham phay (;):");

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
                    System.out.println("Khong tim thay ma san pham tai vi tri thu "+i);
                    break;
                }
            if (!check) System.out.println("Day cac ma san pham khong hop le, hay nhap lai!");
        } while (!check);
        setDsMaSanPham(dsMaSpArr);
    }

    public void nhapDsMaSanPham() {
        DanhSachXe ttds = new DanhSachXe();
        String[] dsMaSp = new String[soLuong];

        Xe pt;
        for(int i=0;i<soLuong;i++) {

            System.out.println("Them ma san pham thu "+i);
            do {
                pt = (Xe) ttds.timPhanTu();

                if (pt == null) System.out.println("Khong tim thay san pham!");
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
            System.out.print("Nhap ma san pham: ");
            maSP = sc.nextLine();

            pt = (Xe) ttds.layPhanTuVoi(maSP); // tìm sản phẩm

            if (pt == null)  // nếu không tìm thấy
                System.out.println("Khong ton tai ma san pham!");
            else dsMaSP[soLuong] = pt.getMaSanPham();

        } while (pt == null);
        setDsMaSanPham(dsMaSP);
    }

    public void themKMaSPVaoDs() {
        System.out.print("Nhap so ma san pham can them vao danh sach: ");
        int k;
        boolean check;
        do {
            check = true;
            try {
                k = Integer.parseInt(sc.nextLine());
                for(int i=0;i<k;i++)
                    themMaSPVaoDs();
            } catch (Exception e) {
                check = false;
                System.out.println("Vui long nhap mot so!");
            }
        } while (!check);
    }

    public void xoaMaSPKhoiDs() {
        String[] dsMaSP = new String[soLuong-1];

        System.out.print("Nhap ma san pham can xoa khoi danh sach: ");
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
        } else System.out.println("Khong tim thay ma san pham!");
    }
    @Override
    public void nhap(){
        setMaDanhMuc();
        setTenDanhMuc();
        setSoLuong();
        setBaoHanh();
        nhapDsMaSanPham();
    }

    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-10s %10s \n", "Ma Dong xe", "Ten Dong xe", "So luong", " Thang bao hanh");
        System.out.printf("%-20s %-20s %-10s %10s \n", maDanhMuc, tenDanhMuc, soLuong ,baoHanh);

        System.out.println("Danh sach san pham cung danh muc: ");
        System.out.printf("%-20s %-30s %-20s %-20s %-20s %-20s \n","Ma san pham", "Ten san pham", "Thuong hieu", "Noi san xuat", "So luong", "Gia");
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
        System.out.println("=== Sua thong tin danh muc san pham ===");
        System.out.println("1. Sua ma danh muc");
        System.out.println("2. Sua ten danh muc");
        System.out.println("3. Them ma san pham vao danh sach ma san pham");
        System.out.println("4. Xoa ma san pham khoi danh sach ma san pham");
        System.out.println("5. Nhap moi danh sach ma san pham");
        System.out.println("6. Them ma san pham vao danh sach ma san pham");
        System.out.println("7. Xoa ma san pham vao danh sach ma san pham");
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
