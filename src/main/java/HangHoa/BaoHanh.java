package HangHoa;

import java.time.LocalDate;
import java.util.Scanner ;

import DanhSach.DanhSachBaoHanh;

public class BaoHanh extends {
    private String soBaoHanh ;
    private int soThangBaoHanh ;
    
    public static Scanner sc = new Scanner(System.in);

    LocalDay localDate = LocalDate.now();
    int ngayhientai = localDate.getDayOfMonth();
    int thanghientai = localDate.getMonthValue();;
    int namhientai = localDate.getYear();

    public BaoHanh() {
    }

    
    public String getSoBaoHanh() {
        return soBaoHanh;
    }


    public void setSoBaoHanh(String soBaoHanh) {
        this.soBaoHanh = soBaoHanh;
    }

    public void setSoBaoHanh() {
        System.out.print("Nhap so bao hanh: ");
        DanhSachBaoHanh ttds = new DanhSachBaoHanh();
        
        boolean check;
        do
        {
            check = true;
            try {
                soBaoHanh = Integer.parseInt(sc.nextLine());
                check = ttds.layPhanTuVoi(soBaoHanh+"") == null;
                if (!check) System.out.print("So bao hanh da ton tai, moi nhap lai: ");
            } catch (Exception e) {
                check = false;
                System.out.print("Vui long nhap mot so: ");
            }
        } while (!check);
    }


    public int getSoThangBaoHanh() {
        return soThangBaoHanh;
    }


    public void setSoThangBaoHanh(int soThangBaoHanh) {
        this.soThangBaoHanh = soThangBaoHanh;
    }



    @Override
    public void nhap() {
        setMaBaoHanh();
        /* 
         * setSoThangBaoHanh();
         * setDongXeBaoHanh();
         * setKhachHang();
         * 
         */
        
         
    }

    @Override
    public void xuat() {

    }

    @Override
    public void suaThongTing(){
    }

}
