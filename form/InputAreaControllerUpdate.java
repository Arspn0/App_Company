package form;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class InputAreaControllerUpdate implements Initializable {
    
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
    private ComboBox<String> input_pilihId;

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
    
    private int companyId;
    
    @FXML
    private void handleIdSelection() {
        input_pilihId.setOnAction(event -> {
            String selectedId = (String) input_pilihId.getValue();
            if (selectedId != null) {
                setData(Integer.parseInt(selectedId));
            }
        });
    }


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
    
    public void updateData(){
        try {
            Alert alert;
            
            String sql = "UPDATE data SET "
                       + "nama_perusahaan = ?, "
                       + "no_ijin = ?, "
                       + "pemilik = ?, "
                       + "jenis_usaha = ?, "
                       + "tanggal_berdiri = ?, "
                       + "no_telepon = ?, "
                       + "provinsi = ?, "
                       + "kabupaten = ?, "
                       + "kecamatan = ?, "
                       + "desa = ?, "
                       + "nama_jalan = ?, "
                       + "rt = ?, "
                       + "rw = ?, "
                       + "no_gedung = ?, "
                       + "kode_pos = ?, "
                       + "img = ? "
                       + "WHERE id = ?";

            connect = database.Database.getConnection();
            prepare = connect.prepareStatement(sql);
            
            if(input_pilihId.getSelectionModel().isEmpty()){
                
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error sistem");
                alert.setHeaderText(null);
                alert.setContentText("Harap pilih id yang ingin diupdate!");
                alert.showAndWait();
                
            }else{
                
                prepare.setString(1, input_namaperusahaan.getText());
                prepare.setString(2, input_noijin.getText());
                prepare.setString(3, input_pemilik.getText());
                prepare.setString(4, combo_jenisusaha.getValue());
                prepare.setDate(5, java.sql.Date.valueOf(input_tanggalberdiri.getValue()));
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

                String imagePath = getData.path;
                prepare.setString(16, imagePath);
                prepare.setInt(17, companyId);

                int affectedRows = prepare.executeUpdate();
                if (affectedRows > 0) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Update Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Data berhasil diperbarui!");
                    alert.showAndWait();

                    DashboardController.getInstance().loadItems();

                    closeWindow();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setData(int id) {
        this.companyId = id;
        try {
            String sql = "SELECT * FROM data WHERE id = ?";
            connect = database.Database.getConnection();

            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, id);

            result = prepare.executeQuery();

            if (result.next()) {
                input_namaperusahaan.setText(result.getString("nama_perusahaan"));
                input_noijin.setText(result.getString("no_ijin"));
                input_pemilik.setText(result.getString("pemilik"));
                combo_jenisusaha.setValue(result.getString("jenis_usaha"));
                input_tanggalberdiri.setValue(result.getDate("tanggal_berdiri").toLocalDate());
                input_notelepon.setText(result.getString("no_telepon"));
                input_provinsi.setText(result.getString("provinsi"));
                input_kabupaten.setText(result.getString("kabupaten"));
                input_kecamatan.setText(result.getString("kecamatan"));
                input_desa.setText(result.getString("desa"));
                input_namajalan.setText(result.getString("nama_jalan"));
                input_rt.setText(result.getString("rt"));
                input_rw.setText(result.getString("rw"));
                input_nogedung.setText(result.getString("no_gedung"));
                input_kodepos.setText(result.getString("kode_pos"));

                String imagePath = result.getString("img");
                getData.path = imagePath;
                image = new Image(new File(imagePath).toURI().toString(), 143, 177, false, true);
                tampilan_gambar.setImage(image);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void loadId() {
        try {
            String sql = "SELECT id FROM data";
            connect = database.Database.getConnection();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                input_pilihId.getItems().add(result.getString("id"));
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
        loadId();
        handleIdSelection();
    }
    
}
