package ThanhToan;
import static ThanhToan.ThanhToan.sc;

public class CKNganHang {
<<<<<<< HEAD
    private String soTheTk;
    private int CVV;

    public CKNganHang(){

    }

    public CKNganHang(String soTheTk, int CVV) {
        this.soTheTk = soTheTk;
        this.CVV = CVV;
    }

    public String getSoTheTk() {
        return soTheTk;
    }

    public void setSoTheTk(String soTheTk) {
        this.soTheTk = soTheTk;
    }

    public void setSoTheTk() {
        boolean check;
        do {
            check = true;
            System.out.print("Nhap so the/tai khoan: ");
            soTheTk = sc.nextLine();
            for(int i = 0; i < soTheTk.length(); i++){
                if(!Character.isDigit(soTheTk.charAt(i))) { // nếu có chứa ký tự
                    check = false;
                    break;
                }
            }
            if (!check) {
                System.out.println("So the khong hop le!");
            }
        } while(!check);
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public void setCVV() {
        boolean check;
        do {
            check = true;
            System.out.print("Nhap so CVV/CVC: ");
            String CVVTemp = sc.nextLine();
            
            if(CVVTemp.length() == 3){
                
                for(int i = 0; i < CVVTemp.length(); i++){
                    if( !Character.isDigit(CVVTemp.charAt(i)) ) {
                        check = false;
                        System.out.println("So CVV/CVC khong hop le!");
                        break;
                    }
                }
                CVV = Integer.parseInt(CVVTemp);
                
            } else {
                System.out.println("So CVV/CVC khong hop le!");
                check = false;
            }
        } while(!check);
    }
     
    public void nhapThongTin(){
        setSoTheTk();
        setCVV();        
    }
    
    public void xuat() {
        System.out.println("So the/tai khoan: "+soTheTk);
        System.out.println("CVV: "+CVV);
=======
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
>>>>>>> c95ef4bf3dac230142a94acfacb6de77b07f67c6
    }
}
