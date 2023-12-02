package DanhSach;

import File.FileHandler;
import HangHoa.*;
import Nguoi.*;

public class DanhSachBaoHanh {
    private int soLuong;
    private BaoHanh[] dsBaoHanh;



    @Override
    public PhanTu layPhanTuVoi(String thamSo) {
        BaoHanh[] dsBaoHanhTmp = getdsBaoHanh();
        
        for(int i=0;i<soLuong;i++) {
            if (dsBaoHanhTmp[i].getSoBaoHanh() == Integer.parseInt(thamSo))
                return dsBaoHanhTmp[i];
        }
        return null;
    }
}
