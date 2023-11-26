package File;

import HangHoa.SanPham;
import ThanhToan.ThanhToan;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private static Scanner fr;

     // Thêm nhân viên vào file dsnv.txt
    public static void themNv(int manv, String hoten, String cccd, String diachi, String sdt, String ngayVaoLam, double heSoLuong, int soNgaynghitrongthang) {
        String tmp = manv+"#"+hoten+"#"+cccd+"#"+diachi+"#"+sdt+"#"+ngayVaoLam+"#"+heSoLuong+"#"+soNgaynghitrongthang;
        ghiFile(tmp, "dsnv.txt");
    }
    //Thêm sản phẩm vào file dssp.txt
    public static void themSP(String masp, String tensp, String thuongHieu, String noiSx, int sl, int gia) {
        String tmp = masp + "#" + tensp + "#" + thuongHieu + "#" + noiSx + "#" + sl + "#" + gia;
        ghiFile(tmp, "dssp.txt");
    }

    //Thêm nha cung cap vào file dsncc.txt
    public static void themNCC(String maNhaCC , String tenNhaCC , String diachi , int sdt) {
        String tmp = maNhaCC + "#" + tenNhaCC + "#" + diachi + "#" + sdt;
        ghiFile(tmp, "dsncc.txt");
    }




    //Tạo file
    public static void taoCacFile() {
        File[] f = new File[6];
        try {
            f[0] = new File("dssp.txt");
            String tenFile = "";
            for (int i = 0; i < f.length; i++) {
                if (f[i].createNewFile()) {
                    switch (i) {
                        case 0:
                            tenFile = "dssp.txt";
                            ghiFile("10", tenFile);
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
                            break;
                        case 1:
                            tenFile = "dsnv.txt";
                            ghiFile("4", tenFile);
                            themNv(1, "Tran Van A", "052204016288", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "17/07/2023", 0.5, 0);
                            themNv(2, "Tran Van B", "054524226300", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "21/09/2023", 1.2, 0);
                            themNv(3, "Tran Thi C", "022201236288", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "12/09/2023", 0.4, 1);
                            themNv(4, "Tran Bui D", "054504012328", "273 An Duong Vuong, P3, Q5, TP.HCM", "0938412413", "25/12/2023", 2, 1);
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
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