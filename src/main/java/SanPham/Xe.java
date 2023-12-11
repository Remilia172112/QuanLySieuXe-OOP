package SanPham;

import DanhSach.DanhSachDongXe;
import DanhSach.DanhSachXe;
import KiemTra.KiemTra;
public class Xe extends PhanTu {
    private String maXe ;
    private String tenXe ;
    private String thuongHieu ;
    private String loaiXe;
    private String noiSanXuat ;
    private int soLuong ;
    private int price ;

    public Xe(){
    }

    public Xe(String maXe , String tenXe , String thuongHieu , String loaiXe, String noiSanXuat , int soLuong , int price){
        this.maXe = maXe ;
        this.tenXe = tenXe ;
        this.thuongHieu = thuongHieu ;
        this.loaiXe = loaiXe ;
        this.noiSanXuat = noiSanXuat ;
        this.soLuong = soLuong ;
        this.price = price ;
    }

    public String getMaXe() {
        return maXe;
    }
    public void setMaXe() {
        DanhSachXe ttds = new DanhSachXe();
        DanhSachDongXe dsdx = new DanhSachDongXe();
        System.out.print("Ban co muon xuat ra man hinh danh sach dong xe khong? (1 - in, 0 - khong): ");
        int chon = KiemTra.checkNumber();
        if (chon == 1) dsdx.xuatDanhSachDongxe();
        System.out.print("Nhap ma xe: ");
        boolean check = false;
        do
        {
            maXe = sc.nextLine();

            check = ttds.layPhanTuVoi(maXe) == null; // kiểm tra mã sản phẩm xem đã tồn tại trong danh sách chưa
            if (!check) System.out.print("Ma xe da ton tai, moi nhap lai: ");

            if(check) {
                check = dsdx.Checkmadongxe(maXe) == true;
                if(!check) System.out.print("Ma xe phai trung 1 phan voi ma dong xe!! Moi nhap lai: ");    
            }
        } while (!check);
    }
    // Check mã xe theo mã của dòng xe của từng loại xe
    public void setMaXeTT() {
        DanhSachXe ttds = new DanhSachXe();
        Xe[] dsx = ttds.getdsXe();
        int stt = 1;
        for(int i = 0; i < dsx.length; i++) {
            if(dsx[i].getLoaiXe().equals("Xe the thao")) {
                stt = Integer.parseInt(dsx[i].getMaXe().substring(5))+1;
            }
        }
        if(stt>9) maXe = "SPORT" + stt;
        else maXe = "SPORT0" + stt;
        System.out.println("Ma xe: " + maXe);
        // System.out.println("Ma xe chung cua loai xe the thao: SPORT");
        // System.out.print("Nhap ma xe: ");
        // boolean check = false;
        // do
        // {
        //     maXe = sc.nextLine();

        //     check = ttds.layPhanTuVoi(maXe) == null; // kiểm tra mã sản phẩm xem đã tồn tại trong danh sách chưa
        //     if (!check) System.out.print("Ma xe da ton tai, moi nhap lai: ");

        //     if(check) {
        //         check = KiemTra.checkMaxeTT(maXe);
        //         if(!check) System.out.print("Ma xe phai trung 1 phan voi ma dong xe!! Moi nhap lai: ");    
        //     }
        // } while (!check);
    }
    public void setMaXeMT() {
        DanhSachXe ttds = new DanhSachXe();
        Xe[] dsx = ttds.getdsXe();
        int stt = 1;
        for(int i = 0; i < dsx.length; i++) {
            if(dsx[i].getLoaiXe().equals("Xe mui tran")) {
                stt = Integer.parseInt(dsx[i].getMaXe().substring(8))+1;
            }
        }
        if(stt>9) maXe = "ROADSTER" + stt;
        else maXe = "ROADSTER0" + stt;
        System.out.println("Ma xe: " + maXe);
        // System.out.println("Ma xe chung cua loai xe mui tran: ROADSTER");
        // System.out.print("Nhap ma xe: ");
        // boolean check = false;
        // do
        // {
        //     maXe = sc.nextLine();

        //     check = ttds.layPhanTuVoi(maXe) == null; // kiểm tra mã sản phẩm xem đã tồn tại trong danh sách chưa
        //     if (!check) System.out.print("Ma xe da ton tai, moi nhap lai: ");

        //     if(check) {
        //         check = KiemTra.checkMaxeMT(maXe);
        //         if(!check) System.out.print("Ma xe phai trung 1 phan voi ma dong xe!! Moi nhap lai: ");    
        //     }
        // } while (!check);
    }
    public void setMaXeD() {
        DanhSachXe ttds = new DanhSachXe();
        Xe[] dsx = ttds.getdsXe();
        int stt = 1;
        for(int i = 0; i < dsx.length; i++) {
            if(dsx[i].getLoaiXe().equals("Xe dien")) {
                stt = Integer.parseInt(dsx[i].getMaXe().substring(7))+1;
            }
        }
        if(stt>9) maXe = "VINFAST" + stt;
        else maXe = "VINFAST0" + stt;
        System.out.println("Ma xe: " + maXe);
        // System.out.println("Ma xe chung cua loai xe dien: VINFAST");
        // System.out.print("Nhap ma xe: ");
        // boolean check = false;
        // do
        // {
        //     maXe = sc.nextLine();

        //     check = ttds.layPhanTuVoi(maXe) == null; // kiểm tra mã sản phẩm xem đã tồn tại trong danh sách chưa
        //     if (!check) System.out.print("Ma xe da ton tai, moi nhap lai: ");

        //     if(check) {
        //         check = KiemTra.checkMaxeD(maXe);
        //         if(!check) System.out.print("Ma xe phai trung 1 phan voi ma dong xe!! Moi nhap lai: ");    
        //     }
        // } while (!check);
    }
    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }
    public String getTenXe() {
        return tenXe;
    }
    // chưa kiểm tra
    public void setTenXe() {
        System.out.print("Nhap ten xe: ");
        tenXe = sc.nextLine();
    }
    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }
    // chưa kiểm tra
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
        boolean check = false;
        do {
            soLuong = KiemTra.checkNumber();
            check = soLuong > 0;
            if(!check) System.out.print("Nhap so lon hon 0!!! Moi nhap lai: ");
        } while(!check);
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
    
    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public void setLoaiXe() {
        DanhSachDongXe dsdx = new DanhSachDongXe();
        loaiXe = dsdx.getLoaixe(maXe);
    }

    @Override
    public void nhap(){
        setMaXe();
        setTenXe();
        setThuongHieu();
        setLoaiXe();
        setNoiSanXuat();
        setSoLuong();
        setPrice();
    }
    // Các phương thức nhập riêng cho từng loại xe
    public void nhapXTT(){
        setMaXeTT();
        setTenXe();
        setThuongHieu();
        setLoaiXe();
        setNoiSanXuat();
        setSoLuong();
        setPrice();
    }
    public void nhapXMT(){
        setMaXeMT();
        setTenXe();
        setThuongHieu();
        setLoaiXe();
        setNoiSanXuat();
        setSoLuong();
        setPrice();
    }
    public void nhapXD(){
        setMaXeD();
        setTenXe();
        setThuongHieu();
        setLoaiXe();
        setNoiSanXuat();
        setSoLuong();
        setPrice();
    }
    public String in() {
        String data = "Ma xe: " + getMaXe() + "\t\tTen xe: " + getTenXe() 
        + "\t\tThuong hieu: " + getThuongHieu() + "\t\tLoai xe: " + getLoaiXe() 
        + "\t\tNoi san xuat: " + getNoiSanXuat() + "\nSo luong: " + getSoLuong()
        + "\t\tGia: " + getPrice();
        return data;
    }
    @Override
    public void xuat() {
        System.out.printf("%-20s %-25s %-20s %-20s %-20s %-15s %-20s \n",maXe,tenXe,thuongHieu,loaiXe,noiSanXuat,soLuong,price);
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
            System.out.println("0. Quay ve menu truoc");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin xe!!");
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getMaXe());
                    setMaXe();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getTenXe());
                    setTenXe();
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
