/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop.kasirr;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import models.jadwalModel;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author user
 */
public class KasirController implements Initializable {

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private TableColumn<jadwalModel, String> nama_film;
    @FXML
    private TableColumn<jadwalModel, String> ruangan;
    @FXML
    private TableColumn<jadwalModel, String> jamtayang;
    @FXML
    private TableColumn<jadwalModel, String> aksi;
    @FXML
    private TableView<jadwalModel> table_jadwal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        loadData();
        if (con == null) {
            System.out.println("Koneksi Gagal");
        } else {
            System.out.println("Koneksi Berhasil");
        }
    }

    public KasirController() {
        con = ConnectionUtil.conDB();
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        nama_film.setCellValueFactory(new PropertyValueFactory<>("nama"));
        ruangan.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        jamtayang.setCellValueFactory(new PropertyValueFactory<>("jamtayang"));
        aksi.setCellValueFactory(new PropertyValueFactory<>("aksi"));
//        edittableCols();
    }

    private void edittableCols() {
    }

    private void loadData() {

        ObservableList<jadwalModel> list = FXCollections.observableArrayList();
        String query = "SELECT\n"
                + "	jadwal.jam_tayang ,ruangan.id as idR,film.judul,ruangan.nama_ruangan\n"
                + "FROM\n"
                + "	jadwal\n"
                + "	JOIN film  ON jadwal.id_film = film.id\n"
                + "	JOIN ruangan ON jadwal.id_ruangan = ruangan.id";

        try {
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                list.add(new jadwalModel(
                        resultSet.getString("judul"), resultSet.getString("nama_ruangan"), resultSet.getString("jam_tayang"), new Button("Pilih"),resultSet.getString("idR")
                ));
            }
            table_jadwal.setItems(list);
        } catch (SQLException ex) {
//            Logger.getLogger(KasirController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
