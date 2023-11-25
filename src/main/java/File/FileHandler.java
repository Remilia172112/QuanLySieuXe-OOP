package File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private static Scanner fr;

    //THEM SANPHAM
    public static void themSP(String masp, String tensp, String thuongHieu, String noiSx, int sl, int gia) {
        String tmp = masp + "#" + tensp + "#" + thuongHieu + "#" + noiSx + "#" + sl + "#" + gia;
        ghiFile(tmp, "dssp.txt");
    }

    //END THEMSANPHAM
    //TAO FILE
    public static void taoCacFile() throws IOException {
        File[] f = new File[1];
        try {
            f[0] = new File("./dssp.txt");
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
                            //ket thuc
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
        //END TAOFILE
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
        //RESET FILE
        public static void resetFile(String tenFile) {
            try {
                FileWriter fw = new FileWriter(tenFile);
                fw.write("");
                fw.close();
            } catch (Exception e) {
                System.out.println("Khong the reset file!");
            }
        }

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