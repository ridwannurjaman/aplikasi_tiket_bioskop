/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import bioskop.kasirr.RuanganController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class JadwalModelAdmin {
    String nama, ruangan, jamtayang;
    Button edit,delete;
    public JadwalModelAdmin(String nama, String ruangan, String jamtayang,Button delete, Button edit) {
        this.nama = nama;
        this.ruangan = ruangan;
        this.jamtayang = jamtayang;
        this.edit = edit;
        this.delete = delete;
        edit.setOnAction(ev -> {
            
        });
        
        delete.setOnAction(ev -> {
            
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

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
    
}
