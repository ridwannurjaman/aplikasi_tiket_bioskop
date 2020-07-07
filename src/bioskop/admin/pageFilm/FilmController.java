/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop.admin.pageFilm;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mochamad
 */
public class FilmController implements Initializable {

    @FXML
    private Label lblJJudul;
    @FXML
    private Label lblKategori;
    @FXML
    private Label lblDeskripsi;
    @FXML
    private TextField tfJudul;
    @FXML
    private TextArea taDeskripsi;
    @FXML
    private Button btnSimpan;
    @FXML
    private TableView<Film> tvFilm;
    @FXML
    private TableColumn<Film, Integer> colId;
    @FXML
    private TableColumn<Film, String> colJudul;
    @FXML
    private TableColumn<Film, String> colKategori;
    @FXML
    private TableColumn<Film, String> colDeskripsi;
    @FXML
    private Label lblJJudul1;
    @FXML
    private TextField tfKategori;
    @FXML
    private TextField tfDurasi;
    @FXML
    private TableColumn<Film, Integer> colDurasi;
    @FXML
    private Label lblID;
    @FXML
    private Button btnHapus;
    @FXML
    private Button btnUbah;
    @FXML
    private TextField tfID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFilms();
    }    

    @FXML
    private void handyButtonAction(ActionEvent event) {
        if (event.getSource() == btnSimpan) {
            insertRecord();
        }
    }
    
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop", "root", "");
            return conn;
        }catch(Exception ex){
            System.out.println("Error : " + ex.getMessage());
         return null;   
        }
         
    }
     
    public ObservableList<Film> getFilmList(){
        ObservableList<Film> filmList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM film";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Film film;
            while(rs.next()){
                film = new Film(rs.getInt("id"), rs.getString("judul"), rs.getString("deskripsi"), rs.getInt("durasi"));
                filmList.add(film);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return filmList;
    }
    
    public void showFilms(){
        ObservableList<Film> list = getFilmList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
        colJudul.setCellValueFactory(new PropertyValueFactory<Film, String>("judul"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<Film, String>("deskripsi"));
        colDurasi.setCellValueFactory(new PropertyValueFactory<Film, Integer>("durasi"));
        
        tvFilm.setItems(list);
    }
    
    public void insertRecord(){
        String query = "INSERT INTO film VALUES ('" + tfID.getText() +"','"+tfJudul.getText()+"','"+taDeskripsi.getText()+"',"
                +tfDurasi.getText()+","+tfKategori.getText()+")";
        executeQuery(query);
        showFilms();
    }
    

    @FXML
    private void handyButtonAction(MouseEvent event) {
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
