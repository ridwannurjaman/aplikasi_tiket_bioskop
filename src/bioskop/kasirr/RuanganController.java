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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.jadwalModel;
import models.ruanganModel;
import utils.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RuanganController implements Initializable {
    String film;
    String namaRuangan;
    String jamTayang;
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Label txtnamaRuangan;
    @FXML
    private Label txtflim;
    @FXML
    private Label txtcountKursi;
    @FXML
    private Button btnback;
    @FXML
    private Button btnnext;
    @FXML
    private TableView<ruanganModel> table_ruangan;
    @FXML
    private TableColumn<ruanganModel, String> colcheckbox;
    @FXML
    private TableColumn<ruanganModel, String> colkursi;
    @FXML
    private TableColumn<ruanganModel, String> colStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        
        if (con == null) {
            System.out.println("Koneksi Gagal");
        } else {
            System.out.println("Koneksi Berhasil");
        }
        
        
    }

    public RuanganController() {
        con = ConnectionUtil.conDB();
    }

    private void initTable() {
        initCols();
    }
    
    public void setTetx(String namaFilm,String namaruangan,String jadwal,String id){
        this.txtflim.setText(namaFilm);
        this.txtnamaRuangan.setText(namaruangan);
        loadData(jadwal,id);
    }
    
    public String getTextFilm(){
        return film;
    }
    
    public String getTextJam(){
        return jamTayang;
    }
    
    public String getTextRuangan(){
        return namaRuangan;
    }

    private void initCols() {
        colcheckbox.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        colkursi.setCellValueFactory(new PropertyValueFactory<>("kursi"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void edittableCols() {
    }

    private void loadData(String tayang,String id) {

        ObservableList<ruanganModel> list = FXCollections.observableArrayList();
        String query = "SELECT\n"
                + "	kursi.posisi_kursi,\n"
                + "	detail_ruangan.`status`,\n"
                + "	jadwal.jam_tayang,\n"
                + "	ruangan.id as ID_RUANGAN\n"
                + "FROM\n"
                + "	detail_ruangan\n"
                + "	JOIN kursi ON detail_ruangan.id_kursi = kursi.id\n"
                + "	JOIN ruangan on detail_ruangan.id_ruangan = ruangan.id\n"
                + "	JOIN jadwal on detail_ruangan.id_jadwal = jadwal.id\n"
                + "	WHERE ruangan.id='"+id+"' and jadwal.jam_tayang='"+tayang+"'";

        try {
            preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                list.add(new ruanganModel(
                        new CheckBox("Pilih Kursi"),resultSet.getString("posisi_kursi"),resultSet.getString("status")
                ));
            }
            
            table_ruangan.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(KasirController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
