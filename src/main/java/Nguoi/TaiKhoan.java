package Nguoi;

public class TaiKhoan {
    private String username, password;

    public TaiKhoan() {
        username = null;
        password = null;
    }

    public TaiKhoan(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TaiKhoan(TaiKhoan a) {
        username = a.username;
        password = a.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return true;
    }

    public String toString() {
        return username + "-" + password;
    }
}
