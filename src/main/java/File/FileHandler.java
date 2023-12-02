package File;


import ThanhToan.ThanhToan;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import HangHoa.Xe;

public class FileHandler {
    private static Scanner fr;

    // Thêm nhân viên vào file dsnv.txt
    public static void themNv(String manv, String hoten, String ntns, String gioitinh, String cccd, String diachi, String sdt, String email, String ngayVaoLam, double heSoLuong, int soNgaynghitrongthang) {
        String tmp = manv+"#"+hoten+"#"+ntns+"#"+gioitinh+"#"+cccd+"#"+diachi+"#"+sdt+"#"+email+"#"+ngayVaoLam+"#"+heSoLuong+"#"+soNgaynghitrongthang;
        ghiFile(tmp, "dsnv.txt");
    }
    //Thêm sản phẩm vào file dssp.txt
    public static void themSP(String masp, String tensp, String thuongHieu, String noiSx, int sl, int gia) {
        String tmp = masp + "#" + tensp + "#" + thuongHieu + "#" + noiSx + "#" + sl + "#" + gia;
        ghiFile(tmp, "dssp.txt");
    }
    //them danh sach dong` xe vao` file dsdmsp.txt
    public static void themDmSP(String maDanhMuc, String tenDanhMuc, int soLuong, int baoHanh,String[] dsMaSanPham) {
        String tmp = maDanhMuc+"#"+tenDanhMuc+"#"+soLuong+"#"+baoHanh+"#";
        for(int i=0;i<dsMaSanPham.length;i++)
            if (i!=dsMaSanPham.length-1)
                tmp += dsMaSanPham[i]+"#";
            else tmp += dsMaSanPham[i];
        ghiFile(tmp, "dsdmsp.txt");
    }
    //Thêm nha cung cap vào file dsncc.txt
    public static void themNCC(String maNhaCC , String tenNhaCC , String diachi , int sdt) {
        String tmp = maNhaCC + "#" + tenNhaCC + "#" + diachi + "#" + sdt;
        ghiFile(tmp, "dsncc.txt");
    }

    //Thêm phieu nhap vào file dspn.txt
    public static void themPN(String maPhieuNhap , String maNhaCC , String maNV, String ngaynhap ,String maSanPham, int tongnhap) {
        String tmp = maPhieuNhap + "#" + maNhaCC + "#" + maNV + "#" + ngaynhap + "#" + maSanPham +"#" + tongnhap;
        ghiFile(tmp, "dspn.txt");
    }

    //Thêm tài khoản vào file dskh.txt
    public static void themKH(String makh, String hoten, String ntns, String gioitinh, String cccd, String diachi, String sdt, String email, String[] dsmspDamua, int soDonHangDaThanhToan, int tongTienDaThanhToan, ThanhToan phThThanhToan) {
        String tmp = makh+"#"+hoten+"#"+ntns+"#"+gioitinh+"#"+cccd+"#"+diachi+"#"+sdt+"#"+email+"#"+dsmspDamua.length+"#";
        for(int i=0; i < dsmspDamua.length; i++) {
            tmp+=dsmspDamua[i] + "#";
        }
        tmp+=soDonHangDaThanhToan+"#"+tongTienDaThanhToan+"#";
        if (phThThanhToan != null) {
            tmp+=phThThanhToan.getPhuongThucThanhToan()+"#";
            // -># # # # # # # #
            // ghi phương thức ngân hàng
            if (phThThanhToan.getPTNganHang() != null) {
                tmp+=phThThanhToan.getPTNganHang().getSoTheTk()+"#"+phThThanhToan.getPTNganHang().getCVV()+"#";
            } else for(int i=0;i<2;i++) tmp+=" #";
            
            // ghi phương thức tín dụng
            if (phThThanhToan.getPTTinDung()!= null) {
                tmp+=phThThanhToan.getPTTinDung().getSoThe()+"#"
                +phThThanhToan.getPTTinDung().getLoaiThe()+"#"
                +phThThanhToan.getPTTinDung().getCVV()+"#";
            } else for(int i=0;i<3;i++) tmp+=" #";
            
            // ghi phương thức ví điện tử
            if (phThThanhToan.getPTViDienTu()!= null) {
                tmp+=phThThanhToan.getPTViDienTu().getSoDienThoaiLienKet()+"#"+phThThanhToan.getPTViDienTu().getTenVi();
            } else {
                tmp+=" # ";
            }
        } else {
            tmp+="TienMat#";
            for(int i=0;i<7;i++)
                if (i!=6) tmp+=" #";
                else tmp+=" ";
        }
        ghiFile(tmp, "dskh.txt");
    }
    //Thêm tài khoản vào file dstk.txt
    public static void themTK(String username, String password, String type) {
        String tmp = username + "#" + password + "#" + type;
        ghiFile(tmp, "dstk.txt");
    }
    // thêm hoá đơn vào file dshd.txt
    public static void themHd(int soHoaDon, int soLuongSanPham, String ngaylapdon, int tongTien, String manhanvien, String maKhachHang, String phThThanhToan, Xe[] dssp) {
        String tmp = soHoaDon+"#"+soLuongSanPham+"#"+ngaylapdon+"#"+tongTien+"#"+manhanvien+"#"+maKhachHang+"#"+phThThanhToan;    
        for(int i=0;i<dssp.length;i++)
        {
            tmp+="#" + dssp[i].getMaSanPham();
        }
        ghiFile(tmp, "dshd.txt");
    }
    //Tạo file
    public static void taoCacFile() {
        File[] f = new File[6];
        try {
            f[0] = new File("dssp.txt");
            f[1] = new File("dsnv.txt");
            f[2] = new File("dskh.txt");
            f[3] = new File("dstk.txt");
            f[4] = new File("dsdmsp.txt");
            f[5] = new File("dshd.txt");
            String tenFile = "";
            for (int i = 0; i < f.length; i++) {
                if (f[i].createNewFile()) {
                    switch (i) {
                        case 0:
                            tenFile = "dssp.txt";
                            ghiFile("15", tenFile);
                            //XE THE THAO
                            themSP("SPORT01", "McLaren P1", "McLaren ", "Italy", 10, 40000);
                            themSP("SPORT02", "Ferrari Roma", "Ferrari", "ITALY", 10, 60000);
                            themSP("SPORT03", "Ford GT", "Ford", "ITALY", 10, 50000);
                            themSP("SPORT04" ,"Tesla Roadster" ,"Tesla" ,"USA" , 20 ,  90000 );
                            themSP("SPORT05" ,"Lamborghini Huracan" ,"Lamborghini" ,"ITALY" , 10 ,  150000 );
                            //XE MUI TRAN
                            themSP("ROADSTER01", "BMW 430i", "BMW", "Duc", 5, 80000);
                            themSP("ROADSTER02", "Porsche 718 Boxster", "Porsche", "Italy", 5, 70000);
                            themSP("ROADSTER03", "Jaguar F-Type Convertible", "Jaguar", "Duc", 5, 120000);
                            themSP("ROADSTER04", "Mini Cooper Convertible", "Mini", "Anh", 5, 250000);
                            themSP("ROADSTER05", "Porsche 911 Targa", "Porsche", "Italy", 5, 500000);
                            //XE DIEN
                            themSP("VINFAST01", "VinFast VF e34" , "VINFAST" , "VIETNAM" , 2, 30000);
                            themSP("VINFAST02", "VinFast VF 8" , "VINFAST" , "VIETNAM" , 5, 50000);
                            themSP("VINFAST03", "VinFast VF 9" , "VINFAST" , "VIETNAM" , 3, 60000);
                            themSP("VINFAST04", "VinFast VF 5" , "VINFAST" , "VIETNAM" , 6, 90000);
                            themSP("VINFAST05", "VinFast VF 6" , "VINFAST" , "VIETNAM" , 4, 120000);

                            break;
                        case 1:
                            tenFile = "dsnv.txt";
                            ghiFile("4", tenFile);
                            themNv("NV1", "Tran Van A", "17/07/2004", "nam", "052204016288", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "tranvana@gmail.com", "17/07/2023", 0.5, 0);
                            themNv("NV2", "Tran Van B", "30/07/2000", "nu", "054524226300", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "tranvanb@gmail.com", "21/09/2023", 1.2, 0);
                            themNv("QL1", "Tran Thi C", "01/01/1950", "nam", "022201236288", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "quanly1@gmail.com", "12/09/2023", 0.4, 1);
                            themNv("QL2", "Tran Bui D", "02/02/2000", "nu", "054504012328", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "quanly2@gmail.com", "25/12/2023", 2, 1);
                            break;
                        case 2:
                            tenFile = "dskh.txt";
                            String[] rong = new String[0];
                            ghiFile("3", tenFile);
                            themKH("KH1", "Doan Van A", "20/12/1950", "nu", "320873941", "273 An Duong Vuong, P3, Q5, TP.HCM", "0894172635", "doanvana@gmail.com", rong, 0, 0, null);
                            themKH("KH2", "Nguyen Van B", "28/11/2002", "nam","320142913", "273 An Duong Vuong, P3, Q5, TP.HCM", "0913716241", "hahah@gmail.com", rong, 0, 0, null);
                            themKH("KH3", "Tran Van C", "10/10/1969", "nam", "320638711", "273 An Duong Vuong, P3, Q5, TP.HCM", "0907412663", "tranvanc@gmail.com", rong, 0, 0, null);
                            break;
                        case 3:
                            tenFile = "dstk.txt";
                            ghiFile("5", tenFile);
                            themTK("admin", "123", "admin");
                            themTK("NV1", "123", "nhan vien");
                            themTK("NV2", "123", "nhan vien");
                            themTK("QL1", "123", "quan ly");
                            themTK("QL2", "123", "quan ly");
                            break;
                        case 4:
                            tenFile = "dsdmsp.txt";
                            ghiFile("3", tenFile);
                            // 1
                            String[] dsMaSp = new String[]{"SPORT01","SPORT02","SPORT03","SPORT04", "SPORT05"};
                            themDmSP("SPORT", "Xe the thao", 5,12 , dsMaSp);
                            // 2
                            dsMaSp = new String[]{"ROADSTER01","ROADSTER02","ROADSTER03","ROADSTER04" , "ROADSTER05"};
                            themDmSP("ROADSTER", "Xe Mui tran", 5, 32,dsMaSp);
                            // 3
                            dsMaSp = new String[]{"VINFAST01", "VINFAST02", "VINFAST03", "VINFAST04", "VINFAST05"};
                            themDmSP("VINFAST" , "Xe dien oto" , 5,48, dsMaSp);
                            break;
                        case 5:
                            tenFile = "dshd.txt";
                            ghiFile("", tenFile);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Khong the tao file!");
        }
    }
    // Ghi file
    public static void ghiFile (String giaTri, String tenFile){
        try {
            File fo = new File(tenFile);
            fr = new Scanner(fo, "utf-8");
            String data = "";
            while (fr.hasNextLine())
                data += fr.nextLine() + "\n";
            FileWriter fw = new FileWriter(tenFile);
            fw.write(data + giaTri);
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the ghi file!");
        }
    }
    //Reset file theo tên
    public static void resetFile(String tenFile) {
        try {
            FileWriter fw = new FileWriter(tenFile);
            fw.write("");
            fw.close();
        } catch (Exception e) {
            System.out.println("Khong the reset file!");
        }
    }
    //Đọc file 
    public static String docFile (String tenFile){
            try {
                File fo = new File(tenFile);
                fr = new Scanner(fo, "utf-8");
                String data = "";
                while (fr.hasNextLine())
                    data += fr.nextLine() + "\n";
                return data;
            } catch (Exception e) {
                System.out.println("Khong the doc file!");
                return null;
            }
        }
}