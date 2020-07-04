/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.control.CheckBox;

/**
 *
 * @author user
 */
public class ruanganModel {
    String kursi,status;
    CheckBox checkbox;
    
    public ruanganModel(CheckBox checkbox,String kursi,String status){
        this.kursi = kursi;
        this.status = status;
        this.checkbox = checkbox;
        
        checkbox.setVisible(true);
        checkbox.setOnAction(e -> {
            
        });
    }

    public String getKursi() {
        return kursi;
    }

    public void setKursi(String kursi) {
        this.kursi = kursi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(CheckBox checkbox) {
        this.checkbox = checkbox;
    }
    
    
}
