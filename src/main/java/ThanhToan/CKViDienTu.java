package ThanhToan;
import static ThanhToan.ThanhToan.sc;

public class CKViDienTu {
    private String tenVi;
    private String soDienThoaiLienKet;
    public CKViDienTu() {
    }

    public CKViDienTu(String tenVi, String soDienThoaiLienKet) {
        this.tenVi = tenVi;
        this.soDienThoaiLienKet = soDienThoaiLienKet;
    }
    
    public String getTenVi() {
        return tenVi;
    }

    public void setTenVi(String tenVi) {
        this.tenVi = tenVi;
    }

    public void setTenVi() {
        int chon = 0;
        do {
            System.out.println("1. ZaloPay");
            System.out.println("2. Momo");
            System.out.println("3. VNPay");
            System.out.print("Chon loai ví: ");
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    tenVi = "ZaloPay";                
                    break;
                case 2:
                    tenVi = "Momo";                
                    break; 
                case 3:
                    tenVi = "VNPay";                
                    break;    
                default:
                    chon = 0;
                    break;
            }
            if (chon == 0){
                System.out.println("Moi chon lai!");
            }
        } while(chon == 0);
    }

    public String getSoDienThoaiLienKet() {
        return soDienThoaiLienKet;
    }

    public void setSoDienThoaiLienKet(String soDienThoaiLienKet) {
        this.soDienThoaiLienKet = soDienThoaiLienKet;
    }

    public void setSoDienThoaiLienKet() {
        boolean check = true;
        do {
            System.out.print("Moi nhap so dien thoai lien ket cua vi: ");
            soDienThoaiLienKet = sc.nextLine();
            if(soDienThoaiLienKet.length() != 10){
                check = false;
                continue;
            }
            for(int i=0; i<soDienThoaiLienKet.length();i++) {
                if (!Character.isDigit(soDienThoaiLienKet.charAt(i))) {
                    check = false;
                    break;
                }
            }
            if (!check) System.out.println("Moi nhap lai!");
        } while(!check);
    }
    
    public void nhapThongTin(){
        setTenVi();
        setSoDienThoaiLienKet();
    }
    
    public void xuat() {
        System.out.println("Ten vi: "+tenVi);
        System.out.println("So dien thoai lien ket: "+soDienThoaiLienKet);
    }
}