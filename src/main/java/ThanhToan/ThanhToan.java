package ThanhToan;

import java.util.Scanner;

public class ThanhToan {
    private String phuongThucThanhToan;

    public static Scanner sc = new Scanner(System.in);
    private CKNganHang PTNganHang = null;
    private CKTinDung PTTinDung = null;
    private CKViDienTu PTViDienTu = null;

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public CKNganHang getPTNganHang() {
        return PTNganHang;
    }

    public CKTinDung getPTTinDung() {
        return PTTinDung;
    }

    public CKViDienTu getPTViDienTu() {
        return PTViDienTu;
    }

    public void setPTNganHang(CKNganHang PTNganHang) {
        this.PTNganHang = PTNganHang;
    }

    public void setPTTinDung(CKTinDung PTTinDung) {
        this.PTTinDung = PTTinDung;
    }

    public void setPTViDienTu(CKViDienTu PTViDienTu) {
        this.PTViDienTu = PTViDienTu;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
    
    public void chonPhThThanhToan(){
        System.out.println("Moi chon phuong thuc thanh toan: ");
        System.out.println("1. Tien mat");
        System.out.println("2. Tai khoan ngan hang");
        System.out.println("3. Tai khoan the tin dung");
        System.out.println("4. Vi dien tu");
        System.out.print("Chon: ");
        int chon;
        do{
            chon = Integer.parseInt(sc.nextLine());
            switch (chon) {
                case 1:
                    phuongThucThanhToan = "TienMat";
                    break;
                case 2:
                    phuongThucThanhToan = "CK_NganHang";
                    break;
                case 3:
                     phuongThucThanhToan = "CK_TinDung";
                    break;
                case 4:
                     phuongThucThanhToan = "CK_ViDienTu";
                    break;
                default:
                    chon = 0;
                    break;
            }
            if(chon == 0){
                System.out.println("Vui long chon lai phuong thuc thanh toan!");
            }
        }while (chon == 0);

    }

    public void LienKet() {
        if(phuongThucThanhToan.equals("CK_NganHang")) {
            PTNganHang = new CKNganHang();
            PTNganHang.nhapThongTin();
        } 
        else if (phuongThucThanhToan.equals("CK_TinDung")){
            PTTinDung = new CKTinDung();
            PTTinDung.nhapThongTin();
        }
        else if (phuongThucThanhToan.equals("CK_ViDienTu")){
            PTViDienTu = new CKViDienTu();
            PTViDienTu.nhapThongTin();
        }
    }
    
    public void xuat() {
        if (PTNganHang != null || PTTinDung != null || PTViDienTu != null)
            System.out.println("*** Cac Phuong Thuc Da Lien Ket ***");
        if (PTNganHang != null) {
            System.out.println("Phuong thuc ngan hang: ");
            PTNganHang.xuat();
        }
        if (PTTinDung != null) {
            System.out.println("Phuong thuc tin dung: ");
            PTTinDung.xuat();
        }
        if (PTViDienTu != null) {
            System.out.println("Phuong thuc vi dien tu: ");
            PTViDienTu.xuat();
        }
    }
}