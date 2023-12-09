package SanPham;

import KiemTra.KiemTra;

public class XeTheThao extends Xe {
    private String hangxe;

    public XeTheThao() {
    }
    
    public XeTheThao(String maXe , String tenXe , String thuongHieu , String noiSanXuat , int soLuong , int price, String hangxe) {
        super(maXe, tenXe, thuongHieu, "Xe the thao", noiSanXuat, soLuong, price);
        this.hangxe = hangxe;
    }

    public String getHangxe() {
        return hangxe;
    }

    public void setHangxe(String hangxe) {
        this.hangxe = hangxe;
    }
    
    public void setHangxe() {
        System.out.print("Nhap hang xe: ");
        boolean check = false;
        do {
            check = true;
            hangxe = sc.nextLine();
            hangxe = hangxe.toUpperCase();
            check = KiemTra.checkHangxe(hangxe);
        } while(!check);
    }

    @Override
    public void nhap(){
        super.nhapXTT();
        setHangxe();
    }
    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Hang xe: " + hangxe);
    }
    @Override
    public void suaThongTin() {
        int chon;
        do {
            System.out.println("=== Sua thong tin xe the thao ===");
            System.out.println("1. Sua thong tin xe the thao");
            System.out.println("2. Sua hang xe xe the thao");
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
                    System.out.println("Thong tin hien tai: "+getHangxe());
                    setHangxe();
                    break;
                default:
                    System.out.println("Hay chon so co trong menu!!");
                    break;
            }
        } while(chon!=0);
    }
}
