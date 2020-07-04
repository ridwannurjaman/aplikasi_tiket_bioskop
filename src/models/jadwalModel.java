/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bioskop.kasirr.RuanganController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class jadwalModel {

    String nama, ruangan, jamtayang;
    Button aksi;

    public jadwalModel(String nama, String ruangan, String jamtayang, Button aksi,String idRuangan) {
        this.nama = nama;
        this.ruangan = ruangan;
        this.jamtayang = jamtayang;
        this.aksi = aksi;

        aksi.setOnAction(ev -> {
            try {
                System.out.println(ruangan);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/bioskop/kasirr/ruangan.fxml"));
                loader.load();
                RuanganController rc = loader.getController();
                rc.setTetx(nama, ruangan, jamtayang,idRuangan);
                Parent p = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.showAndWait();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public String getJamtayang() {
        return jamtayang;
    }

    public void setJamtayang(String jamtayang) {
        this.jamtayang = jamtayang;
    }

    public Button getAksi() {
        return aksi;
    }

    public void setAksi(Button aksi) {
        this.aksi = aksi;
    }

}
