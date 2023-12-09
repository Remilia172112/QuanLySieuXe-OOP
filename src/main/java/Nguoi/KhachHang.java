package Nguoi;
import java.time.LocalDate;

import DanhSach.DanhSachKhachHang;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
import SanPham.Xe;
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
            maKhachHang = sc.nextLine();
            check = KiemTra.checkMSKH(maKhachHang);
            if(!check) System.out.print("Nhap sai ma khach hang (KH), moi nhap lai: ");
            if(check) {
                check = ttds.layPhanTuVoi(maKhachHang+"") == null;
                if (!check) System.out.print("Ma khach hang da ton tai, moi nhap lai: ");
            }
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
        System.out.print("Ban co muon xuat ra man hinh danh sach xe khong? (1 - in, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if (chon == 1) {
            DanhSachXe ttds = new DanhSachXe();
            ttds.xuatDanhSach();
        }
        System.out.println("Nhap day cac ma xe da mua, phan cach boi dau cham phay (;): ");

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

    public void xoaMaSPKhoiDs() {
        String[] dsMaSP = new String[dsmspDamua.length-1];

        System.out.print("Nhap ma xe khach hang can xoa khoi danh sach: ");
        String giaTriCanXoa = sc.nextLine();

        boolean check = false;
        for(int i=0;i<dsmspDamua.length;i++)
        {
            check = dsmspDamua[i].equalsIgnoreCase(giaTriCanXoa);
            if (check) break; // Nếu tìm thấy
        }

        if (check) {
            // thủ tục copy từ mảng danh sách cũ
            for(int i=0, k=0;i<dsmspDamua.length;i++)
            {
                if (dsmspDamua[i].equalsIgnoreCase(giaTriCanXoa)) continue; // bỏ qua phần tử
                dsMaSP[k++] = dsmspDamua[i];
            }
            setDsmspDamua(dsMaSP);
        } else System.out.println("Khong tim thay ma xe khach hang!");
    }

    public void themMaSPVaoDs() {
        // thêm mã sản phẩm vào danh sách
        String[] dsMaSP = new String[dsmspDamua.length+1]; // tạo mảng tạm

        // thủ tục copy từ mảng danh sách cũ
        for(int i=0;i<dsmspDamua.length;i++)
            dsMaSP[i] = dsmspDamua[i];

        String maSP;
        DanhSachXe ttds = new DanhSachXe();
        boolean check = false;
        System.out.println("Ma xe da mua bao gom: ma xe + ma khach hang + thang nam mua xe + thu tu mua");
        System.out.print("Ban co muon xuat ra man hinh danh sach xe khong? (1 - in, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if (chon == 1) {
            ttds.xuatDanhSach();
        }
        do {
            System.out.print("Nhap ma xe khach hang: ");
            maSP = sc.nextLine();
            if (ttds.kiemtraChuoidonhang(maSP) == -1){ // nếu không tìm thấy
                check = false;
                System.out.println("Khong tim thay ma xe");
                continue;
            }
            String dateyearbuy = maSP.substring(maSP.length() - 7, 6);
            if(!KiemTra.isValidMonthYear(dateyearbuy)) {
                check = false;
                System.out.println("Thang nam khong hop le");
                continue;                
            }
            LocalDate localDate = LocalDate.now();
            int namhientai = localDate.getYear();
            int thanghientai = localDate.getMonthValue();
            if(Integer.parseInt(dateyearbuy.substring(dateyearbuy.length() - 4)) > namhientai) {
                check = false;
                System.out.println("Nam mua xe lon hon nam hien tai");
                continue;
            }
            if(Integer.parseInt(dateyearbuy.substring(0, 2)) > thanghientai) {
                check = false;
                System.out.println("Thang mua xe lon hon thang hien tai");
                continue;
            }
        } while (!check);
        setDsmspDamua(dsMaSP);
    }

    public void themKMaSPVaoDs() {
        System.out.print("Nhap so ma xe khach hang can them vao danh sach: ");
        int k;
        k = KiemTra.checkNumber();
        for(int i=0;i<k;i++)
            themMaSPVaoDs();
    }
    

    @Override
    public void nhap() {
        setMaKhachHang();
        super.nhap();
        setPhThThanhToan();
        System.out.println("Cap nhat danh sach da mua cua khach hang? (1/0): ");
        int chon = KiemTra.checkNumber();
        if(chon == 1) {
            System.out.println("Ma xe da mua bao gom: ma xe + ma khach hang + thang nam mua xe");
            setDsmspDamua();
        }
        else {
            String[] tmp = new String[0];
            setDsmspDamua(tmp);
        }
    }
    @Override
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

                pt = (Xe) ttds.layPhanTuVoi(dsmspDamua[i].substring(0, dsmspDamua[i].length()-7-maKhachHang.length()));

                if (pt != null) System.out.printf("%-20s %-30s %-20s %-20s %-20s \n", dsmspDamua[i], pt.getTenXe(), pt.getThuongHieu(), pt.getNoiSanXuat(), pt.getPrice());
            }
        }
        System.out.println("****************************");
    }    
    public static void xuat(KhachHang kh) {
        kh.xuat();
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin khach hang ===");
            System.out.println("1. Sua thong tin ca nhan");
            System.out.println("2. Sua phuong thuc thanh toan");
            System.out.println("3. Them ma xe khach hang vao danh sach ma xe khach hang");
            System.out.println("4. Xoa ma xe khach hang khoi danh sach ma xe khach hang");
            System.out.println("5. Nhap moi danh sach ma xe khach hang");
            System.out.println("6. Them ma xe khach hang vao danh sach ma xe khach hang");
            System.out.println("7. Xoa ma xe khach hang khoi danh sach ma xe khach hang");
            System.out.println("0. Thoat");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin khach hang!!");
                    break;
                case 1:
                    super.suaThongTin();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getPhThThanhToan().getPhuongThucThanhToan());
                    setPhThThanhToan();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsmspDamua())
                        System.out.print(x + " ");

                    themKMaSPVaoDs();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsmspDamua())
                        System.out.print(x + " ");

                    xoaMaSPKhoiDs();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsmspDamua())
                        System.out.print(x + " ");

                    setDsmspDamua();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: ");
                    for (String x : getDsmspDamua())
                        System.out.print(x + " ");
                    themKMaSPVaoDs();
                    break;
                case 7:
                    System.out.println("Thong tin hien tai: ");

                    for (String x : getDsmspDamua())
                        System.out.print(x + " ");

                    xoaMaSPKhoiDs();
                    break;
                default:
                    System.out.println("Hay nhap so co trong menu");
                    break;
            }
        } while(chon != 0);
    }
}
