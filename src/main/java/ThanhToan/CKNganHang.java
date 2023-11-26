package ThanhToan;
import static ThanhToan.ThanhToan.sc;

public class CKNganHang {
    private String soTheTaiKhoan ;
    private int CVV ;
    
    public CKNganHang() {

    }

    public CKNganHang(String soTheTaiKhoan, int CVV) {
        this.soTheTaiKhoan = soTheTaiKhoan;
        this.CVV = CVV;
    }

    public String getsoTheTaiKhoan() {
        return soTheTaiKhoan;
    }
    
    public void setsoTheTaiKhoan(String soTheTaiKhoan) {
        this.soTheTaiKhoan = soTheTaiKhoan;
    }

    public void setsoTheTaiKhoan () {
        boolean check ;
        do {
            check = true;
            System.out.print("Nhap so the tai khoan : ");
            String STK = sc.nextLine();
            for(int i = 0 ; i < STK.length() ; i++) {
                if (!Character.isDigit(STK.charAt(i))) {
                    check = false ;
                    break ;
                }
            }
            if(!check) {
                System.out.println("Vui long nhap lai so the !");
            } else {
                soTheTaiKhoan = STK ;
            }
        } while (!check);
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
    public void setCVV() {
        boolean check ;
        do {
            check =true ;
            System.out.print("Nhap so CVV/CVC : ");
            String tempCVV = sc.nextLine();
            if(tempCVV.length() == 3) {
                for(int i = 0 ; i < 3 ; i++) {
                    if(!Character.isDigit(tempCVV.charAt(i))) {
                        check = false ;
                        System.out.println("Ma CVV/CVC khong hop le !");
                        break ;
                    }
                }
            } else {
                System.out.println("Ma CVV/CVC khong hop le ! Ma nay chi co 3 so !");
                check = false ;
            }
            if (check) {
                CVV = Integer.parseInt(tempCVV);
            }
        } while(!check);
    }
    public void nhapThongTin() {
        setsoTheTaiKhoan();
        setCVV();
    }
    
    public void xuat() {
        System.out.println("So the tai khoan : " + soTheTaiKhoan);
        System.out.println("Ma so CVV : " + CVV);
    }
}
