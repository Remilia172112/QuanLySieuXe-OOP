package Nguoi;
import java.time.LocalDate;

import DanhSach.DanhSachKhachHang;
import DanhSach.DanhSachXe;
import HangHoa.Xe;
import KiemTra.KiemTra;
import ThanhToan.ThanhToan;

public class KhachHang extends Nguoi {
    private int soDonHangDaThanhToan;
    private int tongTienDaThanhToan;
    private String[] dsmspDamua;
    private String maKhachHang;
    private ThanhToan phThThanhToan = null;

    public KhachHang() {
    }

    public KhachHang(int soDonHangDaThanhToan, int tongTienDaThanhToan, String maKhachHang, ThanhToan phThThanhToan) {
        this.soDonHangDaThanhToan = soDonHangDaThanhToan;
        this.tongTienDaThanhToan = tongTienDaThanhToan;
        this.maKhachHang = maKhachHang;
        this.phThThanhToan = phThThanhToan;
    }

    public KhachHang(int soDonHangDaThanhToan, int tongTienDaThanhToan, String maKhachHang, ThanhToan phThThanhToan, String hoten, String ngaythangnamsinh, String CCCD, String gioitinh,
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

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    
    public void setMaKhachHang() {
        System.out.print("Nhap ma khach hang: ");
        DanhSachKhachHang ttds = new DanhSachKhachHang();
        boolean check = false;
        do
        {
            maKhachHang = KiemTra.checkMaso();
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
        System.out.println("Nhap day cac ma xe da mua, phan cach boi dau cham phay (;):");

        String dsMaSP;

        String[] dsMaSpArr;

        DanhSachXe ttds = new DanhSachXe(); // tạo đối tượng DanhSachSanPham

        boolean check;
        do {
            check = true;
            dsMaSP = sc.nextLine(); // đọc từ input
            dsMaSpArr = dsMaSP.split(";"); // tách thành mảng bởi dấu ;

            for(int i=0;i<dsMaSpArr.length;i++) { // ứng với từng phần tử mảng
                if (ttds.kiemtraChuoidonhang(dsMaSpArr[i]) == -1){ // nếu không tìm thấy
                    check = false;
                    System.out.println("Khong tim thay ma xe tai vi tri thu " + i);
                    break;
                }
                String dateyearbuy = dsMaSpArr[i].substring(dsMaSpArr[i].length() - 7, 6);
                if(!KiemTra.isValidMonthYear(dateyearbuy)) {
                    check = false;
                    System.out.println("Thang nam khong hop le tai vi tri thu " + i);
                    break;
                }
                LocalDate localDate = LocalDate.now();
                int namhientai = localDate.getYear();
                int thanghientai = localDate.getMonthValue();
                if(Integer.parseInt(dateyearbuy.substring(dateyearbuy.length() - 4)) > namhientai) {
                    check = false;
                    System.out.println("Nam mua xe lon hon nam hien tai tai vi tri thu " + i);
                    break;
                }
                if(Integer.parseInt(dateyearbuy.substring(0, 2)) > thanghientai) {
                    check = false;
                    System.out.println("Thang mua xe lon hon thang hien tai tai vi tri thu " + i);
                    break;
                }
            }
            if (!check) System.out.println("Day cac ma xe khong hop le, hay nhap lai!");
        } while (!check);
        setDsmspDamua(dsMaSpArr);
    }

    @Override
    public void nhap() {
        setMaKhachHang();
        super.nhap();
        setPhThThanhToan();
        System.out.println("Cap nhat danh sach da mua cua khach hang? (1/0): ");
        int chon = Integer.parseInt(sc.nextLine());
        if(chon == 1) {
            System.out.println("Ma xe da mua bao gom: ma xe + ma khach hang + thang nam mua xe");
            setDsmspDamua();
        }
        else {
            String[] tmp = new String[0];
            setDsmspDamua(tmp);
        }
    }
    public void xuat() {
        System.out.println("Ma nhan vien: " + getMaKhachHang());        
        super.xuat();
        if (phThThanhToan != null) phThThanhToan.xuat();
        System.out.println();
        if(dsmspDamua.length != 0) {
            System.out.println("****************************");
            System.out.println("Danh sach xe da mua: ");
            System.out.printf("%-20s %-30s %-20s %-20s %-20s \n","Ma xe khach hang", "Ten xe", "Thuong hieu", "Noi san xuat", "Gia");
            Xe pt;
            DanhSachXe ttds = new DanhSachXe();

            for(int i=0;i<dsmspDamua.length;i++) {

                pt = (Xe) ttds.layPhanTuVoi(dsmspDamua[i].substring(0, dsmspDamua[i].length()-6-maKhachHang.length()));

                if (pt != null) System.out.printf("%-20s %-30s %-20s %-20s %-20s \n", dsmspDamua[i], pt.getTenSanPham(), pt.getThuongHieu(), pt.getNoiSanXuat(), pt.getPrice());
            }
        }
        System.out.println("****************************");
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
