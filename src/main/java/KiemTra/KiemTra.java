package KiemTra;

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
	
	public static boolean check_maso(String maso) {
		String reg = "^[A-Za-z0-9]{2}\\d{8}$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(maso);
		if (!matcher.matches()) {
			System.out.print("Ma so khong dung dinh dang!!! Moi nhap lai: ");
			return false;
		}
		return true;
	}

	public static boolean check_diachi(String text) {
		String reg = "[^\\s*]$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(text);
		if (!matcher.matches()) {
			System.out.print("Chuoi khong duoc bo trong!!! Moi nhap lai: ");
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
			System.out.println(name);
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
}
