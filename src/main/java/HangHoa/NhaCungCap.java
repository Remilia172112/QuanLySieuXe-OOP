package HangHoa;

import DanhSach.DanhSachNhaCungCap;
// import DanhSach.DanhSachXe;

public class NhaCungCap extends PhanTu {
    private String maNhaCC ;
    private String tenNhaCC ;
    private String diachi ;
    private int sdt ;
    public NhaCungCap(){
    }

    public NhaCungCap(String maNhaCC , String tenNhaCC , String diachi , int sdt){
        this.maNhaCC = maNhaCC ;
        this.tenNhaCC = tenNhaCC;
        this.diachi = diachi ;
        this.sdt = sdt;
    }

    
    public void setMaNhaCC() {
        System.out.print("Nhap ma nha cung cap: ");
        DanhSachNhaCungCap ttds = new DanhSachNhaCungCap();
        boolean check = false;
        do
        {
            maNhaCC = sc.nextLine();
            check = ttds.layPhanTuVoi(maNhaCC) == null; // kiểm tra mã nha cung cap xem đã tồn tại trong danh sách chưa
            if (!check) System.out.print("Ma nha cung cap da ton tai, moi nhap lai: ");
        } while (!check);
    }
    public void setMaSanPham(String maNhaCC) {
        this.maNhaCC = maNhaCC;
    }


    public void setTenNhaCC() {
        System.out.print("Nhap ten nha cung cap: ");
        tenNhaCC = sc.nextLine();
    }
    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }

    public void setDiaChi(String diachi) {
        this.diachi = diachi;
    }
    public void setDiaChi() {
        System.out.print("Nhap dia chi nha cung cap: ");
        diachi = sc.nextLine();
    }
  
    public void setSdt(int sdt) {
        this.sdt = sdt;
    }
    public void setSdt() {
        System.out.print("Nhap so dien thoai cua nha cung cap: ");
        boolean check;
        do {
            check = true;
            try {
                sdt = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                check = false;
                System.out.print("Vui long nhap so dien thoai dung: ");
                //them ktra sdt 
            }
        } while (!check);
    }

    public String getMaNhaCC() {
        return maNhaCC;
    }
    public String getTenNhaCC() {
        return tenNhaCC;
    }
    public String getDiaChi() {
        return diachi;
    }
    public int getSdt() {
        return sdt;
    }

    @Override
    public void nhap(){
        setMaNhaCC();
        setTenNhaCC();
        setDiaChi();
        setSdt();
    }

    @Override
    public void xuat() {
        System.out.printf("%-20s %-20s %-50s %-20s  \n",maNhaCC,tenNhaCC,diachi,sdt);
    }

    @Override
    public void suaThongTin() {
        System.out.println("=== Sua thong tin nha cung cap ===");
        System.out.println("1. Sua ma nha cung cap");
        System.out.println("2. Sua ten nha cung cap");
        System.out.println("3. Sua dia chi nha cung cap");
        System.out.println("4. Sua so dien thoai nha cung cap");
        System.out.println("0. Quay ve menu quan ly san pham");
        System.out.println("===============================");
        int chon;
        do {
            System.out.print("Nhap lua chon: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 0:
                    break;
                case 1:
                    System.out.println("Thong tin hien tai: "+getMaNhaCC());
                    setMaNhaCC();;
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getTenNhaCC());
                    setTenNhaCC();
                    break;
                case 3:
                    System.out.println("Thong tin hien tai: "+getDiaChi());
                    setDiaChi();
                    break;
                case 4:
                    System.out.println("Thong tin hien tai: "+getSdt());
                    setSdt();
                    break;
                
                default:
                    System.out.println("Hay chon lai!");
                    break;
            }
        } while(chon!=0);
    }


}
