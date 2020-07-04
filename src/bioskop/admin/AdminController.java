/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminController implements Initializable {

    @FXML
    private Button btnjadwal;
    @FXML
    private Button btnruangan;
    @FXML
    private Button btnfilm;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnjadwal(MouseEvent event) {
        loadPage("pageJadwal/jadwal.fxml");
    }

    @FXML
    private void btnruangan(MouseEvent event) {
        loadPage("pageRuangan/ruangan.fxml");
    }

    @FXML
    private void btnfilm(MouseEvent event) {
        loadPage("pageFilm/film.fxml");
    }
    
    private void loadPage(String page){
        Parent root = null;
        
        try { 
            root = FXMLLoader.load(getClass().getResource(page));
        } catch (IOException ex) {
//            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
    }
    
}
