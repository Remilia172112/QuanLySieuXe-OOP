package ThanhToan;
import static ThanhToan.ThanhToan.sc;

public class CKNganHang {
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
    }
}
