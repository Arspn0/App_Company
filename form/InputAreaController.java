package form;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class InputAreaController implements Initializable {
    
    @FXML
    private Button btn_close;

    @FXML
    private Button btn_pilihgambar;

    @FXML
    private Button btn_simpan;

    @FXML
    private ComboBox<String> combo_jenisusaha;

    @FXML
    private TextField input_desa;

    @FXML
    private TextField input_kabupaten;

    @FXML
    private TextField input_kecamatan;

    @FXML
    private TextField input_kodepos;

    @FXML
    private TextField input_namajalan;

    @FXML
    private TextField input_namaperusahaan;

    @FXML
    private TextField input_nogedung;

    @FXML
    private TextField input_noijin;

    @FXML
    private TextField input_notelepon;

    @FXML
    private TextField input_pemilik;

    @FXML
    private TextField input_provinsi;

    @FXML
    private TextField input_rt;

    @FXML
    private TextField input_rw;

    @FXML
    private DatePicker input_tanggalberdiri;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ImageView tampilan_gambar;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;
    private Stage stage;

    public void choosePicture(){
        
        FileChooser open = new FileChooser();
        open.setTitle("Buka file gambar");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("File image", "*jpg", "*png", "*jpeg"));
        
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        
        if(file != null){
            getData.path = file.getAbsolutePath();
            
            image = new Image(file.toURI().toString(), 143, 177, false, true);
            tampilan_gambar.setImage(image);
        }
        
    }
    
    public void addData(){
        
        try {
            Alert alert;

            String sql = "INSERT INTO data (nama_perusahaan, no_ijin, pemilik, jenis_usaha, tanggal_berdiri, no_telepon, provinsi, kabupaten, "
                    + "kecamatan, desa, nama_jalan, rt, rw, no_gedung, kode_pos, img)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            String url = getData.path;
            if (url != null) {
                url = url.replace("\\", "\\\\");
            }

            connect = database.Database.getConnection();

            if (input_namaperusahaan.getText().isEmpty() || input_noijin.getText().isEmpty() || input_pemilik.getText().isEmpty() || combo_jenisusaha.getSelectionModel().isEmpty() ||
                    input_tanggalberdiri.getValue() == null || input_notelepon.getText().isEmpty() || input_provinsi.getText().isEmpty() || input_kabupaten.getText().isEmpty() || 
                    input_kecamatan.getText().isEmpty() || input_desa.getText().isEmpty() || input_namajalan.getText().isEmpty() || input_rt.getText().isEmpty() || 
                    input_rw.getText().isEmpty() || input_nogedung.getText().isEmpty() || input_kodepos.getText().isEmpty() || 
                    getData.path == null || getData.path.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error sistem");
                alert.setHeaderText(null);
                alert.setContentText("Harap isi bagian yang kosong!");
                alert.showAndWait();

            } else {

                String cekData = "SELECT no_ijin FROM data WHERE no_ijin = '"
                        + input_noijin.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(cekData);

                if (result.next()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error sistem");
                    alert.setHeaderText(null);
                    alert.setContentText("No ijin sudah terdaftar!");
                    alert.showAndWait();

                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, input_namaperusahaan.getText());
                    prepare.setString(2, input_noijin.getText());
                    prepare.setString(3, input_pemilik.getText());
                    prepare.setString(4, combo_jenisusaha.getValue());
                    prepare.setString(5, String.valueOf(input_tanggalberdiri.getValue()));
                    prepare.setString(6, input_notelepon.getText());
                    prepare.setString(7, input_provinsi.getText());
                    prepare.setString(8, input_kabupaten.getText());
                    prepare.setString(9, input_kecamatan.getText());
                    prepare.setString(10, input_desa.getText());
                    prepare.setString(11, input_namajalan.getText());
                    prepare.setString(12, input_rt.getText());
                    prepare.setString(13, input_rw.getText());
                    prepare.setString(14, input_nogedung.getText());
                    prepare.setString(15, input_kodepos.getText());
                    prepare.setString(16, url);

                    prepare.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
                    
                    DashboardController.getInstance().loadItems();

                    closeWindow();

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_close.setOnAction(e -> closeWindow());
        combo_jenisusaha.getItems().addAll(
        "Teknologi",
        "Layanan",
        "Kesehatan",
        "Pendidikan",
        "Ritel",
        "F&B",
        "Logistik",
        "Properti",
        "Keuangan",
        "Manufaktur"
        );
    }
    
}
