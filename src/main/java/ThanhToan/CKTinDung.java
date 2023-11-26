package ThanhToan;
import static ThanhToan.ThanhToan.sc;

public class CKTinDung {
   private String soThe;
   private int CVV;
   private String loaiThe;
   
   public CKTinDung(){
       
   }

    public CKTinDung(String soThe, int CVV, String loaiThe) {
        this.soThe = soThe;
        this.CVV = CVV;
        this.loaiThe = loaiThe;
    }

    public String getSoThe() {
        return soThe;
    }

    public void setSoThe(String soThe) {
        this.soThe = soThe;
        setLoaiThe();
    }

    public void setSoThe() {
        boolean check;
        do {
            check = true;
            System.out.print("Nhap so the: ");
            soThe = sc.nextLine();
            if(soThe.length() == 16){
                for(int i = 0; i < soThe.length(); i++){
                    if( !Character.isDigit(soThe.charAt(i)) ) {
                        check = false;
                        break;
                    }
                }
            } else check = false;
        } while(!check);
        setLoaiThe();
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

    public String getLoaiThe() {
        return loaiThe;
    }
    
    //kiểm tra loại thẻ từ số thẻ (bằng những kí tự đâù)

    public void setLoaiThe() {
        loaiThe = "";
        if (soThe.charAt(0) == '5')
            loaiThe = "Master Card";
        else if (soThe.charAt(0) == '4')
            loaiThe = "Visa";
        else 
            loaiThe = "Unknown";
    }
    
    public void nhapThongTin(){
        setSoThe();
        setCVV();
    }
   
    public void xuat() {
        System.out.println("So the/tai khoan: "+soThe);
        System.out.println("Loai the: "+loaiThe);
        System.out.println("CVV: "+CVV);
    }
}