/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskop;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;
import utils.connection;

/**
 *
 * @author user
 */
public class LoginController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Label lblEror;

    @FXML
    private TextField txtusername;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnlogin;

    @FXML
    private Button btntest;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        System.out.println("SDASD");

        if (event.getSource() == btnlogin) {

            String username = txtusername.getText();
            String password = txtpassword.getText();
            String sql = "SELECT * FROM user where username = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    lblEror.setTextFill(Color.TOMATO);
                    lblEror.setText("Username atau password anda salah");

                } else {
                    if (resultSet.getString("role").equals("Admin")) {
                        lblEror.setTextFill(Color.GREEN);
                        lblEror.setText("Login BErhasil");
                        System.out.println("berhasil admin");
                        try {
                            Node node = (Node) event.getSource();
                            Stage stage = (Stage) node.getScene().getWindow();
                            //stage.setMaximized(true);
                            stage.close();
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("admin/Admin.fxml")));
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                        }
                    } else if (resultSet.getString("role").equals("Kasir")) {
                        lblEror.setTextFill(Color.GREEN);
                        lblEror.setText("Login Berhasil");
                        System.out.println("berhasil");
                        try {
                            Node node = (Node) event.getSource();
                            Stage stage = (Stage) node.getScene().getWindow();
                            //stage.setMaximized(true);
                            stage.close();
                            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("kasirr/kasir.fxml")));
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception e) {
                        }

                    }
                }
            } catch (SQLException ex) {

            }

//            if (logIn().equals("Sukses")) {
//            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (con == null) {
            lblEror.setTextFill(Color.TOMATO);
            lblEror.setText("Server Error : Check");
        } else {
            lblEror.setTextFill(Color.GREEN);
            lblEror.setText("Server is up : Good to go");
        }

    }

    public LoginController() {
        con = ConnectionUtil.conDB();
    }

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

//    private String logIn() {
//        
//        
//        return status;
//    }
    private void notif(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
