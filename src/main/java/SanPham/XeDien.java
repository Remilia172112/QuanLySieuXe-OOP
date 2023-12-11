package SanPham;

import KiemTra.KiemTra;

public class XeDien extends Xe {
    private String dungluongpin;

    public XeDien() {
    }
    
    public XeDien(String maXe , String tenXe , String thuongHieu,  String noiSanXuat , int soLuong , int price, String dungluongpin) {
        super(maXe, tenXe, thuongHieu, "Xe dien", noiSanXuat, soLuong, price);
        this.dungluongpin = dungluongpin;
    }

    public String getDungluongpin() {
        return dungluongpin;
    }

    public void setDungluongpin(String dungluongpin) {
        this.dungluongpin = dungluongpin;
    }
    
    public void setDungluongpin() {
        System.out.print("Nhap so dung luong pin: ");
        int so = KiemTra.checkNumber();
        dungluongpin = so + " kWh";
    }
    @Override
    public String in() {
        String data = super.in() + "\t\tDung luong pin: " + getDungluongpin() + "\n\n";
        return data;
    }
    @Override
    public void nhap(){
        super.nhapXD();
        setDungluongpin();
    }
    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Dung luong pin: " + dungluongpin);
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin xe dien ===");
            System.out.println("1. Sua thong tin xe dien");
            System.out.println("2. Sua dung luong pin xe dien");
            System.out.println("0. Quay ve menu quan ly xe");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin xe dien!!");
                    break;
                case 1:
                    super.suaThongTin();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getDungluongpin());
                    setDungluongpin();
                    break;
                default:
                    System.out.println("Hay chon so co trong menu!!");
                    break;
            }
        } while(chon!=0);
    }
}
