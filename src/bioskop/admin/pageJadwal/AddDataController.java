/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop.admin.pageJadwal;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.JadwalModelAdmin;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddDataController implements Initializable {

    String idRuangan, idFilm, jamtayang, idkursi;
    int idjadwal;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ObservableList<String> listRuangan = FXCollections.observableArrayList();
    ObservableList<String> listFilm = FXCollections.observableArrayList();
    SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
    SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
//    
    @FXML
    private ComboBox<String> cmbfilm;
    @FXML
    private ComboBox<String> cmbruangan;
    @FXML
    private DatePicker tgl;
    @FXML
    private JFXTimePicker jam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        if (con == null) {
            System.out.println("Koneksi Gagal");
        } else {
            System.out.println("Koneksi Berhasil");
            loadDataFilm();
            loadDataRuangan();

        }

    }

    public AddDataController() {
        con = ConnectionUtil.conDB();
    }

    private void loadDataFilm() {
        try {

            String query = "Select * from film";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listFilm.add(resultSet.getString("judul"));
            }
            cmbfilm.getItems().addAll(listFilm);
        } catch (Exception e) {

        }

    }

    private void loadDataRuangan() {
        try {

            String query = "Select * from ruangan";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listRuangan.add(resultSet.getString("nama_ruangan"));
            }
            cmbruangan.getItems().addAll(listRuangan);
        } catch (Exception e) {

        }

    }

    private String getIDRuangan(String nama) {
        try {

            String query = "Select * from ruangan where nama_ruangan='" + cmbruangan.getSelectionModel().getSelectedItem().toString() + "'";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idRuangan = resultSet.getString("id");
            }
            System.out.println("ID RUANGAN " + idRuangan);
        } catch (Exception e) {

        }
        return idRuangan;
    }

    private String getIDFilm(String nama) {
        try {

            String query = "Select * from film where judul='" + cmbfilm.getSelectionModel().getSelectedItem().toString() + "'";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idFilm = resultSet.getString("id");
            }
            System.out.println("ID film " + idFilm);
        } catch (Exception e) {

        }
        return idFilm;
    }
   
    

    private void save() {
        String tanggal = ((TextField) tgl.getEditor()).getText();
        String jampicker = ((TextField) jam.getEditor()).getText();
        try {
            String date1 = date.format(date2.parse(tanggal));
            String jam1 = date24Format.format(date12Format.parse(jampicker));
            System.out.println(date1);
            System.out.println(jam1);
            jamtayang = date1 + " " + jam1;
            System.out.println(jamtayang);

            String query = "INSERT INTO jadwal (id_film,id_ruangan,jam_tayang) VALUES(?,?,?);";
            preparedStatement = con.prepareStatement(query,preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, Integer.parseInt(idFilm));
            preparedStatement.setInt(2, Integer.parseInt(idRuangan));
            preparedStatement.setString(3, jamtayang);
            preparedStatement.executeUpdate();
//            idjadwal = preparedStatement.executeUpdate(query, preparedStatement.RETURN_GENERATED_KEYS);
           resultSet = preparedStatement.getGeneratedKeys();
           if(resultSet.next()){
               idjadwal = Integer.parseInt(resultSet.getString(1));
               System.out.println("ID JADWAL :"+idjadwal);
           }
        } catch (Exception e) {
            System.out.println(e);
        }
        savedDetailJadwal();
    }

    private void savedDetailJadwal() {
        try {
            
            String query = "Select * from kursi where id_ruangan='" + idRuangan + "'";
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String query1 = "INSERT INTO detail_ruangan (id_ruangan,id_kursi,id_jadwal,status) VALUES(?,?,?,?);";
                preparedStatement = con.prepareStatement(query1);
                preparedStatement.setInt(1, Integer.parseInt(resultSet.getString("id_ruangan")));
                preparedStatement.setInt(2, Integer.parseInt(resultSet.getString("id")));
                preparedStatement.setInt(3, idjadwal);
                preparedStatement.setString(4, "kosong");
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void getvalueRuangan(ActionEvent event) {
        System.out.println(cmbruangan.getSelectionModel().getSelectedItem().toString());
        getIDRuangan(cmbruangan.getSelectionModel().getSelectedItem().toString());

    }

    @FXML
    private void getvalueFilm(ActionEvent event) {
        System.out.println(cmbfilm.getSelectionModel().getSelectedItem().toString());
        getIDFilm(cmbfilm.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void tambahdata(ActionEvent event) {
        save();
    }

}
