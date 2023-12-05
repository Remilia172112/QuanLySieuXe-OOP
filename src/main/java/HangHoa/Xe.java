package HangHoa;

import DanhSach.DanhSachDongXe;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
public class Xe extends PhanTu {
    private String maSanPham ;
    private String tenSanPham ;
    private String thuongHieu ;
    private  String noiSanXuat ;
    private int soLuong ;
    private int price ;
    public Xe(){
    }

    public Xe(String maSanPham , String tenSanPham , String thuongHieu , String noiSanXuat , int soLuong , int price){
        this.maSanPham = maSanPham ;
        this.tenSanPham = tenSanPham ;
        this.thuongHieu = thuongHieu ;
        this.noiSanXuat = noiSanXuat ;
        this.soLuong = soLuong ;
        this.price = price ;
    }

    public String getMaSanPham() {
        return maSanPham;
    }
    public void setMaSanPham() {
        DanhSachXe ttds = new DanhSachXe();
        DanhSachDongXe dsdx = new DanhSachDongXe();
        System.out.println("Ban co muon xuat ra man hinh danh sach dong xe khong? (1 - in, !1 - khong)");
        int chon = KiemTra.checkNumber();
        if (chon == 1) dsdx.xuatDanhSachDongxe();
        System.out.print("Nhap ma xe: ");
        boolean check = false;
        do
        {
            maSanPham = sc.nextLine();
            check = dsdx.Checkmadongxe(maSanPham) == true;
            if(!check) System.out.print("Ma xe phai trung 1 phan voi ma dong xe!!\n Moi nhap lai: ");
            if(check) {
                check = ttds.layPhanTuVoi(maSanPham) == null; // kiểm tra mã sản phẩm xem đã tồn tại trong danh sách chưa
                if (!check) System.out.print("Ma xe da ton tai, moi nhap lai: ");
            }
        } while (!check);
    }
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham() {
        System.out.print("Nhap ten xe: ");
        tenSanPham = sc.nextLine();
    }
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }
    public void setThuongHieu() {
        System.out.print("Nhap ten thuong hieu: ");
        thuongHieu = sc.nextLine();
    }
    public void setNoiSanXuat(String noiSanXuat) {
        this.noiSanXuat = noiSanXuat;
    }
    public void setNoiSanXuat() {
        System.out.print("Nhap noi san xuat: ");
        noiSanXuat = sc.nextLine();
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    public void setSoLuong() {
        System.out.print("Nhap so luong: ");
        soLuong = KiemTra.checkNumber();
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setPrice() {
        System.out.print("Vui long nhap gia: ");
        price = KiemTra.checkNumber();
    }
    public String getThuongHieu() {
        return thuongHieu;
    }

    public String getNoiSanXuat() {
        return noiSanXuat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getPrice() {
        return price;
    }
    @Override
    public void nhap(){
        setMaSanPham();
        setTenSanPham();
        setThuongHieu();
        setNoiSanXuat();
        setSoLuong();
        setPrice();
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-25s %-20s %-20s %-15s %-20s \n",maSanPham,tenSanPham,thuongHieu,noiSanXuat,soLuong,price);
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin xe ===");
            System.out.println("1. Sua ma xe");
            System.out.println("2. Sua ten xe");
            System.out.println("3. Sua thuong hieu");
            System.out.println("4. Sua noi san xuat");
            System.out.println("5. Sua so luong");
            System.out.println("6. Sua gia");
            System.out.println("0. Quay ve menu quan ly xe");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 0:
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getMaSanPham());
                    setMaSanPham();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getTenSanPham());
                    setTenSanPham();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: "+getThuongHieu());
                    setThuongHieu();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getNoiSanXuat());
                    setNoiSanXuat();
                    break;
                case 5:
                    System.out.println("Thong tin hien tai: "+getSoLuong());
                    setSoLuong();
                    break;
                case 6:
                    System.out.println("Thong tin hien tai: "+getPrice());
                    setPrice();
                    break;
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }
}
