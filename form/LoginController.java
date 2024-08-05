package form;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
    
    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnMinimize;

    @FXML
    private PasswordField inputpassword;

    @FXML
    private TextField inputusername;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    
    public void login(){
        
        connect = database.Database.getConnection();
        
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        
        try{
            
            Alert alert;
            
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, inputusername.getText());
            prepare.setString(2, inputpassword.getText());
            
            result =  prepare.executeQuery();
            
            if(inputusername.getText().isEmpty() || inputpassword.getText().isEmpty()){
                
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pesan sistem");
                alert.setHeaderText(null);
                alert.setContentText("Harap isi username atau password");
                alert.showAndWait();
                
            }else{
                if(result.next()){
                    
                    getData.username = inputusername.getText();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pesan sistem");
                    alert.setHeaderText(null);
                    alert.setContentText("Login berhasil!");
                    alert.showAndWait();
                    
                    btnLogin.getScene().getWindow().hide();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) ->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    
                    root.setOnMouseDragged((MouseEvent event) ->{
                        stage.setX(event.getSceneX() - x);
                        stage.setY(event.getSceneY() - y);
                    });
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                }else{
                    
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Pesan sistem");
                    alert.setHeaderText(null);
                    alert.setContentText("Username atau Password salah!");
                    alert.showAndWait();
                    
                }
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void minimize(){
        Stage stage = (Stage)btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void close(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pesan sistem");
        alert.setHeaderText(null);
        alert.setContentText("Yakin mau keluar?");
        Optional<ButtonType> option = alert.showAndWait();
        
        if(option.get().equals(ButtonType.OK)){
            System.exit(0);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}