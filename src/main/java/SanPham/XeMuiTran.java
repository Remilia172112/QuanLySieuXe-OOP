package SanPham;

import KiemTra.KiemTra;

public class XeMuiTran extends Xe {
    private String loaimui;

    public XeMuiTran() {
    }
    
    public XeMuiTran(String maXe , String tenXe , String thuongHieu , String noiSanXuat , int soLuong , int price, String loaimui) {
        super(maXe, tenXe, thuongHieu, "Xe mui tran", noiSanXuat, soLuong, price);
        this.loaimui = loaimui;
    }

    public String getLoaimui() {
        return loaimui;
    }

    public void setLoaimui(String loaimui) {
        this.loaimui = loaimui;
    }
    
    public void setLoaimui() {
        System.out.print("Nhap loai mui: ");
        boolean check = false;
        do {
            check = true;
            loaimui = sc.nextLine();
            check = KiemTra.checkLoaimui(loaimui);
        } while(!check);
    }
    @Override
    public String in() {
        String data = super.in() + "\t\tLoai mui: " + getLoaimui() + "\n\n";
        return data;
    }
    @Override
    public void nhap(){
        super.nhapXMT();
        setLoaimui();
    }
    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Loai mui: " + loaimui);
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin xe mui tran ===");
            System.out.println("1. Sua thong tin xe mui tran");
            System.out.println("2. Sua loai mui xe mui tran");
            System.out.println("0. Quay ve menu quan ly xe");
            System.out.println("===============================");
            System.out.print("Nhap lua chon: ");
            chon = KiemTra.checkNumber();;
            switch (chon) {
                case 0:
                    System.out.println("Thoat sua thong tin xe dien!!");
                    break;
                case 1:
                    super.suaThongTin();
                    break;
                case 2:
                    System.out.println("Thong tin hien tai: "+getLoaimui());
                    setLoaimui();
                    break;
                default:
                    System.out.println("Hay chon so co trong menu!!");
                    break;
            }
        } while(chon!=0);
    }
}
