
package com.model;
import com.control.control_siswa;
import com.koneksi.koneksi;
import com.view.tampilan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class model_siswa implements control_siswa{
    String jk;

    @Override
    public void simpan(tampilan siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()) {
        jk = "Laki-laki";
    } else {
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "Insert into siswa VALUES(?,?,?,?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.setString(2, siswa.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) siswa.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
            prepare.close();
            Baru(siswa);
        } catch (Exception e) {
            System.out.println (e);
        } finally {
            Tampil(siswa);
            siswa.setLebarKolom();
        }
    }
    
    @Override
    public void ubah(tampilan siswa) throws SQLException {
    if (siswa.rbLaki.isSelected()) {
        jk = "Laki-laki";
    } else {
            jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?, "
                    + "jurusan=? WHERE NIS=?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.setString(2, siswa.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, (String) siswa.cbJurusan.getSelectedItem());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah.");
            prepare.close();
            Baru(siswa);
        } catch (Exception e) {
            System.out.println (e);
        } finally {
            Tampil(siswa);
            siswa.setLebarKolom();
            Baru(siswa);
        }
    }

    @Override
    public void hapus(tampilan siswa) throws SQLException {
        try {
            Connection con = koneksi.getcon();
            String sql = "DELETE FROM siswa WHERE NIS =?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNIS.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
            prepare.close();
            Baru(siswa);
        } catch (Exception e) {
            System.out.println (e);
        } finally {
            Tampil(siswa);
            siswa.setLebarKolom();
            Baru(siswa);
        }
    }

    @Override
    public void Tampil(tampilan siswa) throws SQLException {
        siswa.tblmodel.getDataVector() .removeAllElements();
        siswa.tblmodel.fireTableDataChanged();
        try{
          Connection con = koneksi.getcon();
          Statement stt = con.createStatement();
          String sql = "SELECT * FROM siswa order BY NIS ASC";
          ResultSet res = stt.executeQuery(sql);
          while (res.next()){
          Object[] ob = new Object[8];
          ob[0] = res.getString(1);
          ob[1] = res.getString(2);
          ob[2] = res.getString(3);
          ob[3] = res.getString(4);
          siswa.tblmodel.addRow(ob);
          }
        } catch (SQLException e){
            System.out.println(e);
            }
    }       

    @Override
    public void Baru(tampilan siswa) throws SQLException {
        siswa.txtNIS.setText("");
        siswa.txtNama.setText("");
        siswa.rbLaki.setSelected(true);
        siswa.cbJurusan.setSelectedIndex(0);
    }

    @Override
    public void KLikTabel(tampilan siswa) throws SQLException {
        try {
            int pilih = siswa.tabel.getSelectedRow();
            if (pilih == -1){
                return;
        }
        siswa.txtNIS.setText(siswa.tblmodel.getValueAt(pilih, 0).toString());
        siswa.txtNama.setText(siswa.tblmodel.getValueAt(pilih, 1).toString());
        siswa.cbJurusan.setSelectedItem(siswa.tblmodel.getValueAt(pilih, 3).toString());
        jk = String.valueOf(siswa.tblmodel.getValueAt(pilih, 2));
    } catch (Exception e) {
    }
    if (siswa.rbLaki.getText().equals(jk)) {
        siswa.rbLaki.setSelected(true);
    } else {
        siswa.rbPerempuan.setSelected(true);
    }
  }
}
