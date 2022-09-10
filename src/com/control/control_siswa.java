package com.control;
 import com.view.tampilan;
 import java.sql.SQLException;
 import java.util.logging.Level;
 import java.util.logging.Logger;

public interface control_siswa {
    public void simpan(tampilan siswa) throws SQLException;
    public void ubah(tampilan siswa) throws SQLException;
    public void hapus(tampilan siswa) throws SQLException;
    public void Tampil(tampilan siswa) throws SQLException;
    public void Baru(tampilan siswa) throws SQLException;
    public void KLikTabel(tampilan siswa) throws SQLException;

}
