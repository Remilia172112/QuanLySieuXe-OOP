package Nguoi;
import DanhSach.DanhSachKhachHang;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
import ThanhToan.ThanhToan;

public class KhachHang extends Nguoi {
    private int soDonHangDaThanhToan;
    private int tongTienDaThanhToan;
    private int maKhachHang;
    private String[] dsmspDamua;
    private ThanhToan phThThanhToan = null;

    public KhachHang() {
    }

    public KhachHang(int soDonHangDaThanhToan, int tongTienDaThanhToan, int maKhachHang, ThanhToan phThThanhToan) {
        this.soDonHangDaThanhToan = soDonHangDaThanhToan;
        this.tongTienDaThanhToan = tongTienDaThanhToan;
        this.maKhachHang = maKhachHang;
        this.phThThanhToan = phThThanhToan;
    }

    public KhachHang(int soDonHangDaThanhToan, int tongTienDaThanhToan, int maKhachHang, ThanhToan phThThanhToan, String hoten, String ngaythangnamsinh, String CCCD, String gioitinh,
    String diachi, String sdt, String email) {
        super(hoten, ngaythangnamsinh, CCCD, gioitinh, diachi, sdt, email);
        this.soDonHangDaThanhToan = soDonHangDaThanhToan;
        this.tongTienDaThanhToan = tongTienDaThanhToan;
        this.maKhachHang = maKhachHang;
        this.phThThanhToan = phThThanhToan;
    }

    public int getSoDonHangDaThanhToan() {
        return soDonHangDaThanhToan;
    }

    public void setSoDonHangDaThanhToan(int soDonHangDaThanhToan) {
        this.soDonHangDaThanhToan = soDonHangDaThanhToan;
    }

    public int getTongTienDaThanhToan() {
        return tongTienDaThanhToan;
    }

    public void setTongTienDaThanhToan(int tongTienDaThanhToan) {
        this.tongTienDaThanhToan = tongTienDaThanhToan;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    
    public void setMaKhachHang() {
        System.out.print("Nhap ma khach hang: ");
        DanhSachKhachHang ttds = new DanhSachKhachHang();
        boolean check = false;
        do
        {
            maKhachHang = KiemTra.checkNumber();
            check = ttds.layPhanTuVoi(maKhachHang+"") == null;
            if (!check) System.out.print("Ma khach hang da ton tai, moi nhap lai: ");
        } while (!check);
    }

    public ThanhToan getPhThThanhToan() {
        return phThThanhToan;
    }

    public void setPhThThanhToan(ThanhToan phThThanhToan) {
        this.phThThanhToan = phThThanhToan;
    }

    public void setPhThThanhToan() {
        int chon;
        System.out.print("Nhap 1 neu muon nhap phuong thuc thanh toan cho khach hang nay: ");
        chon = KiemTra.checkNumber();
        if (chon == 1) {
            phThThanhToan = new ThanhToan();
            phThThanhToan.chonPhThThanhToan();
            phThThanhToan.LienKet();
        }
    }

    public String[] getDsmspDamua() {
        return dsmspDamua;
    }

    public void setDsmspDamua(String[] dsmspDamua) {
        this.dsmspDamua = dsmspDamua;
    }

    public void setDsmspDamua() {
        System.out.println("Nhap day cac ma san pham da mua, phan cach boi dau cham phay (;):");

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
        setDsmspDamua(dsMaSpArr);
    }

    @Override
    public void nhap() {
        setMaKhachHang();
        super.nhap();
        setPhThThanhToan();
    }
    public void xuat() {
        System.out.printf("%-20s %-25s %-15s %-15s %-15s %-40s %-15s %-15s\n", "Ma khach hang", "Ho ten", "Gioi tinh", "Ngay sinh", "CCCD", "Dia chi", "So dien thoai", "Email");
        System.out.printf("%-20s ", getMaKhachHang());
        super.xuat();
        System.out.println();
        if (phThThanhToan != null) phThThanhToan.xuat();
        System.out.println("---");
    }    
    public static void xuat(KhachHang kh) {
        kh.xuat();
    }
    public void suaThongTin() {
        System.out.println("=== Sua thong tin nhan vien ===");
        System.out.println("1. Sua ho ten");
        System.out.println("2. Sua ngay thang nam sinh");
        System.out.println("3. Sua gioi tinh");
        System.out.println("4. Sua can cuoc cong dan");
        System.out.println("5. Sua dia chi");
        System.out.println("6. Sua so dien thoai");
        System.out.println("7. Sua email");
        System.out.println("8. Sua phuong thuc thanh toan");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: " + getHoten());
                    setHoten();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: " + getNgaythangnamsinh());
                    setNgaythangnamsinh();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: " + getGioitinh());
                    setGioitinh();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: " + getCCCD());
                    setCCCD();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: " + getDiachi());
                    setDiachi();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: " + getSdt());
                    setSdt();
                    break;
                case 7:
                    System.out.println("Thong tin hien tai: " + getEmail());
                    setEmail();
                    break;
                case 8:
                    System.out.println("Thong tin hien tai: "+getPhThThanhToan().getPhuongThucThanhToan());
                    setPhThThanhToan();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu");
                    break;
            }
        } while(chon != 0);
    }
}
