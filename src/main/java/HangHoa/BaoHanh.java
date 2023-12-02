package HangHoa;

import java.time.LocalDate;
import java.util.Scanner ;
import KiemTra.*;

import DanhSach.DanhSachBaoHanh;

public class BaoHanh extends PhanTu {
    private int soBaoHanh ;
    private int soThangBaoHanh ;
    
    public static Scanner sc = new Scanner(System.in);

    LocalDate localDate = LocalDate.now();
    int ngayhientai = localDate.getDayOfMonth();
    int thanghientai = localDate.getMonthValue();;
    int namhientai = localDate.getYear();

    public BaoHanh() {
    }

    public BaoHanh(int soBaoHanh, int soThangBaoHanh) {
        this.soBaoHanh = soBaoHanh;
        this.soThangBaoHanh = soThangBaoHanh ;
    }
    
    public int getSoBaoHanh() {
        return soBaoHanh;
    }


    public void setSoBaoHanh(int soBaoHanh) {
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

    public void setSoThangBaoHanh() {
        System.out.print("Nhap so thang duoc bao hanh : ");
        soThangBaoHanh = KiemTra.checkNumber();

    }



    @Override
    public void nhap() {
        setSoBaoHanh();
        setSoThangBaoHanh();
        /* 
         * setDongXeBaoHanh();
         * setKhachHang();
         * 
         */
        
         
    }

    @Override
    public void xuat() {

    }

    @Override
    public void suaThongTin(){
    }

}
