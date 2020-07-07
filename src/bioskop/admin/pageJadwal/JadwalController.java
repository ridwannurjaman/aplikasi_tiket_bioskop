/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop.admin.pageJadwal;

import bioskop.kasirr.RuanganController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.JadwalModelAdmin;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author user
 */
public class JadwalController implements Initializable {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Button btntambah;
    @FXML
    private TableView<JadwalModelAdmin> tab_jadwal;
    @FXML
    private TableColumn<JadwalModelAdmin,String> col_film;
    @FXML
    private TableColumn<JadwalModelAdmin,String> col_ruangan;
    @FXML
    private TableColumn<JadwalModelAdmin,String> col_jamtayang;
    @FXML
    private TableColumn<JadwalModelAdmin,String> col_del;
    @FXML
    private TableColumn<JadwalModelAdmin,String> col_edit;
    @FXML
    private Button btnrefresh;

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
    public JadwalController() {
        con = ConnectionUtil.conDB();
    }
    private void loadData() {

        ObservableList<JadwalModelAdmin> list = FXCollections.observableArrayList();
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

                list.add(new JadwalModelAdmin(
                        resultSet.getString("judul"), resultSet.getString("nama_ruangan"), resultSet.getString("jam_tayang"), new Button("DELETE"),new Button("EDIT")
                ));
            }
            tab_jadwal.setItems(list);
        } catch (SQLException ex) {
//            Logger.getLogger(KasirController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initTable() {
        initCols();
    }
    
    private void initCols() {
        col_film.setCellValueFactory(new PropertyValueFactory<>("nama"));
        col_ruangan.setCellValueFactory(new PropertyValueFactory<>("ruangan"));
        col_jamtayang.setCellValueFactory(new PropertyValueFactory<>("jamtayang"));
        col_del.setCellValueFactory(new PropertyValueFactory<>("delete"));
        col_edit.setCellValueFactory(new PropertyValueFactory<>("edit"));
//        edittableCols();
    }
    
    

    @FXML
    private void tambahdata(MouseEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addData.fxml"));
                loader.load();
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void refreshData(ActionEvent event) {
        loadData();
    }
    
}
