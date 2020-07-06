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
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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

    String idRuangan, idFilm, jamtayang;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ObservableList<String> listRuangan = FXCollections.observableArrayList();
    ObservableList<String> listFilm = FXCollections.observableArrayList();
    SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
    SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
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
        try {

//            String query = "INSERT INTO jadwal (id_film,id_ruangan,jam_tayang) values(?,?,?)";
//            preparedStatement = con.prepareStatement(query);
////            preparedStatement.setString(1, username);
////                preparedStatement.setString(2, password);
//            resultSet = preparedStatement.executeQuery();
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
//        jamtayang = date.format();
        
        System.out.println(((TextField) tgl.getEditor()).getText());
        System.out.println("CLICK");
    }

}
