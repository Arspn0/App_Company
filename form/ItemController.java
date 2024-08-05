package form;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class ItemController implements Initializable {
    
    @FXML
    private ImageView img_perusahaan;
    
    @FXML
    private AnchorPane item;

    @FXML
    private Label label_jenis;

    @FXML
    private Label label_kabupaten;

    @FXML
    private Label label_namaperusahaan;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Image image;
    private int id; 
    
    private DashboardController dashboardController;
    
    public void setData(CompaniesData company) {
        this.id = company.getId();
        
        try {
            String sql = "SELECT id, nama_perusahaan, jenis_usaha, kabupaten, img FROM data WHERE id = ? ";
            connect = database.Database.getConnection();
            
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, company.getId());
            
            result = prepare.executeQuery();
            
            if (result.next()) {
                label_namaperusahaan.setText(company.getNamaPerusahaan());
                label_kabupaten.setText(company.getKabupaten());
                label_jenis.setText(company.getJenisUsaha());

                getData.path = company.getImage();
        
                String uri = "file:" + company.getImage();

                image = new Image(uri, 143, 177, false, true);
                img_perusahaan.setImage(image);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    private void itemClickAction(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Penghapusan");
        alert.setHeaderText(null);
        alert.setContentText("Apakah yakin ingin menghapus item yang dipilih?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            
            dashboardController.deleteItemFromDB(id);
            dashboardController.loadItems();
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardController = DashboardController.getInstance();
    }    
    
}
