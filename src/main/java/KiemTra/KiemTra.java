package KiemTra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class KiemTra {
	static Scanner sc = new Scanner(System.in);

	public static boolean check_sdt(String phone) {
		String reg = "^0\\d{9}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(phone);
		if (!matcher.matches()) {
			System.out.print("So dien thoai khong dung dinh dang!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}
	
	public static String checkMaso() {
        boolean check = false;
		String str;
		do {
			str = sc.nextLine();
			check = true;
			if(!check_maso(str)) {
				check = false;
			}
		} while (!check);
		return str;
	}
	
	public static boolean check_maso(String maso) {
		String reg = "^[A-Za-z]{2,}.*\\d{2,}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(maso);
		if (!matcher.matches()) {
			System.out.print("Ma so khong dung dinh dang!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}
	public static boolean checkMSKH(String input) {
        String regex = "^KH\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
	public static boolean checkMSNV(String input) {
        String regex = "^NV\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
	public static boolean checkMSQL(String input) {
        String regex = "^QL\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
	public static boolean check_diachi(String text)
	{
		if(text == null || text == "")
		{
			System.out.println("Khong duoc bo trong!!!");
			return false;
		}
		return true;
	}

	public static boolean check_date(String date) {
		String reg = "^(0[1-9]|[1-2][0-9]|3[01])/(0[1-9]|1[0-2])/[1-2]\\d{3}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			System.out.print("Dinh dang dung la dd/mm/yyyy!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}

	public static boolean check_name(String name) {
		String reg = "^[^\\d]+(\\s[^\\d]+)+$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			System.out.print("Nhap sai dinh dang ten!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}


	public static boolean check_sex(String sex) {
		String reg = "nam|nu$";
		Pattern pattern = Pattern.compile(reg,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sex);
		if (!matcher.matches() || sex == "") {
			System.out.print("Gioi tinh phai la nam/nu!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}

	public static boolean check_mail(String mail) {
		String reg = "^[A-Za-z0-9]{5,32}@[a-zA-Z]{2,10}(\\.[a-zA-Z]{2,10})+$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(mail);
		if (!matcher.matches() || mail == "") {
			System.out.print("Khong nhap dung dinh dang email!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}

	public static boolean check_cccd(String cccd)
	{
		String reg = "^\\d{12}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(cccd);
		if(!matcher.matches() || cccd == "")
		{
			System.out.print("So can cuoc cong dan khong dung!!! Moi nhap lai:");
			return false;
		}
		return true;
	}
	public static int checkMonth() {
        boolean check = false;
		String str;
		do {
			str = sc.nextLine();
			check = true;
			if(!isNumber(str)&&Integer.parseInt(str)>=1&&Integer.parseInt(str)<=12) {
				check = false;
				System.out.print("Nhap sai so!!! Moi nhap lai: ");
			}

		} while (!check);
		return Integer.parseInt(str);
	}
	public static int checkNumber() {
        boolean check = false;
		String str;
		do {
			str = sc.nextLine();
			check = true;
			if(!isNumber(str)) {
				check = false;
				System.out.print("Nhap sai so!!! Moi nhap lai: ");
			}
		} while (!check);
		return Integer.parseInt(str);
	}
	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static boolean isValidMonthYear(String input) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng MMYYYY
        String regex = "^(0[1-9]|1[0-2])(20\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
	public static boolean isValidDate(String dateString) {
        // Định dạng cho chuỗi ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            // Chuyển đổi chuỗi thành đối tượng LocalDate
            LocalDate inputDate = LocalDate.parse(dateString, formatter);

            // Lấy ngày tháng năm hiện tại
            LocalDate currentDate = LocalDate.now();

            // Kiểm tra xem ngày tháng năm có hợp lệ và nhỏ hơn hoặc bằng ngày hiện tại không
            return !inputDate.isAfter(currentDate);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi khi chuyển đổi
            return false;
        }
	}

	public static boolean CheckDate(String date) {
        boolean check = true;
		String dateStr[] = date.split("/");
        int ngay = Integer.parseInt(dateStr[0]), thang = Integer.parseInt(dateStr[1]), nam = Integer.parseInt(dateStr[2]);
        if (ngay <= 0 | ngay > 31) {
            check = false;
            System.out.println("Ngay khong hop le!");
        }
        if (thang <= 0 || thang > 12) {
            check = false;
            System.out.println("Thang khong hop le!");
        }
        if (nam <= 1920 || nam > 2100) {
            check = false;
            System.out.println("Nam khong hop le!");
        }
        if (nam % 400 == 0 || (nam % 4 == 0 && nam % 100 != 0)) { // năm nhuận
            if (thang == 2) {
                if (ngay > 29) {
                    check = false;
                    System.out.println("Thang 2 nam da nhap chi co 29 ngay!");
                }
            }
        } else { // không nhuận
            if (thang == 2) {
                if (ngay > 28) {
                    check = false;
                    System.out.println("Thang 2 nam da nhap chi co 28 ngay!");
                }
            }
        }
        switch (thang) { // các trường hợp còn lại
            case 4, 6, 9, 11:
                if (ngay > 30) {
                    check = false;
                    System.out.println("Thang da nhap chi co 30 ngay!");
                }
                break;
        }
		return check;
	}
	public static String checkLoaixe() {
		boolean check = false;
		int choose;
		System.out.println("==============================");
		System.out.println("1. Xe the thao");
		System.out.println("2. Xe mui tran");
		System.out.println("3. Xe dien");
		System.out.println("==============================");
		do {
			System.out.print("Moi chon: ");
			choose = Integer.parseInt(sc.nextLine());
			check = true;
			switch (choose) {
				case 1:
					return "Xe the thao";
				case 2:
					return "Xe mui tran";
				case 3:
					return "Xe dien";
				default:
					System.out.println("Hay chon so co trong menu!!!");
					break;
			}
		} while (!check);
		return "";
	}
	public static boolean checkLoaimui(String text)
	{
		if(text.equals("Mem") || text.equals("Tu dong") ||text.equals("Tron") || text.equals("Vuot") || text.equals("Goc canh") || text.equals("Rong") || text.equals("Cat dut") || text.equals("Chia") || text.equals("Doi xung") || text.equals("Co dien"))
		{
			return true;
		}
		else{
			System.out.print("Khong nhap dung Loai mui (Mem, Tu dong, Tron, Vuot, Goc canh, Rong, Cat dut, Chia, Doi xung, Co dien)!! Moi nhap lai: ");
			return false;
		}
	}
	public static boolean checkHangxe(String text)
	{
		String reg = "^(?i)[abcds]$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(text);
		if (!matcher.matches() || text == "") {
			System.out.print("Khong nhap dung hang xe (S,A,B,C,D)!! Moi nhap lai: ");
			return false;
		}
		return true;
	}
	public static boolean checkMaxeTT(String input) {
        String regex = "^SPORT\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
	public static boolean checkMaxeMT(String input) {
        String regex = "^ROADSTER\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
	public static boolean checkMaxeD(String input) {
        String regex = "^VINFAST\\d{2,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
	public static int sosanhngay(String date1, String date2) {
        String[] parts1 = date1.split("/");
        String[] parts2 = date2.split("/");

        int day1 = Integer.parseInt(parts1[0]);
        int month1 = Integer.parseInt(parts1[1]);
        int year1 = Integer.parseInt(parts1[2]);

        int day2 = Integer.parseInt(parts2[0]);
        int month2 = Integer.parseInt(parts2[1]);
        int year2 = Integer.parseInt(parts2[2]);

        if (year1 != year2) {
            return Integer.compare(year1, year2);
        } else if (month1 != month2) {
            return Integer.compare(month1, month2);
        } else {
            return Integer.compare(day1, day2);
        }
    }
}
