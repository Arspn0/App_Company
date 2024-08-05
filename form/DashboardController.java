package form;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class DashboardController implements Initializable {

     @FXML
    private Button btn_fdanb;

    @FXML
    private Button btn_kesehatan;

    @FXML
    private Button btn_keuangan;

    @FXML
    private Button btn_layanan;

    @FXML
    private Button btn_logistik;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_manufaktur;

    @FXML
    private Button btn_pendidikan;

    @FXML
    private Button btn_properti;

    @FXML
    private Button btn_ritel;

    @FXML
    private Button btn_semua;

    @FXML
    private Button btn_tambah;

    @FXML
    private Button btn_teknologi;
    
    @FXML
    private Button btn_update;
    
    @FXML
    private Button btn_statistik;

    @FXML
    private Button close;

    @FXML
    private GridPane grid;

    @FXML
    private Button minimize;

    @FXML
    private ScrollPane scroll_pane;
    
    @FXML
    private TextField search_bar;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;
    
    @FXML
    private void handleButtonClick(ActionEvent event) {
        if (event.getSource() == btn_semua) {
            loadItems();
        } else if (event.getSource() == btn_fdanb) {
            loadItemsByType("F&B");
        } else if (event.getSource() == btn_kesehatan) {
            loadItemsByType("Kesehatan");
        } else if (event.getSource() == btn_keuangan) {
            loadItemsByType("Keuangan");
        } else if (event.getSource() == btn_layanan) {
            loadItemsByType("Layanan");
        } else if (event.getSource() == btn_logistik) {
            loadItemsByType("Logistik");
        } else if (event.getSource() == btn_manufaktur) {
            loadItemsByType("Manufaktur");
        } else if (event.getSource() == btn_pendidikan) {
            loadItemsByType("Pendidikan");
        } else if (event.getSource() == btn_properti) {
            loadItemsByType("Properti");
        } else if (event.getSource() == btn_ritel) {
            loadItemsByType("Ritel");
        } else if (event.getSource() == btn_teknologi) {
            loadItemsByType("Teknologi");
        }
    }
    
    @FXML
    private void handleSearchInput() {
        String searchQuery = search_bar.getText();
        if (searchQuery.isEmpty()) {
            loadItems();
        } else {
            loadItemsBySearch(searchQuery);
        }
    }
    
    public void deleteItemFromDB(int id){

        try {
            String sql = "DELETE FROM data WHERE id = ?";
            
            connect = database.Database.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, id);
            prepare.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting item: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
            }
        }
        
    }
    
    private void loadItemsByType(String jenisUsaha) {
        
        try {
            List<CompaniesData> companyList = getDatafromDBByType(jenisUsaha);
            int row = 0;
            int column = 0;

            grid.getChildren().clear();
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();

            // Setup column constraints
            for (int i = 0; i < 3; i++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setPercentWidth(33.33);
                grid.getColumnConstraints().add(colConstraints);
            }

            // Setup row constraints
            int numRows = (companyList.size() + 2) / 3;
            for (int i = 0; i < numRows; i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(Region.USE_COMPUTED_SIZE);
                rowConstraints.setPrefHeight(Region.USE_COMPUTED_SIZE);
                rowConstraints.setMaxHeight(Region.USE_PREF_SIZE);
                grid.getRowConstraints().add(rowConstraints);
            }

            for (CompaniesData company : companyList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                AnchorPane anchorpane = loader.load();

                ItemController itemController = loader.getController();
                itemController.setData(company);

                anchorpane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                anchorpane.setPrefSize(215, 222);
                anchorpane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(29.5));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loadItemsBySearch(String searchQuery) {
        
        try {
            List<CompaniesData> companyList = getDatafromDBBySearch(searchQuery);
            int row = 0;
            int column = 0;

            grid.getChildren().clear();
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();

            // Column
            for (int i = 0; i < 3; i++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setPercentWidth(33.33);
                grid.getColumnConstraints().add(colConstraints);
            }

            // Row
            int numRows = (companyList.size() + 2) / 3;
            for (int i = 0; i < numRows; i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(Region.USE_COMPUTED_SIZE);
                rowConstraints.setPrefHeight(Region.USE_COMPUTED_SIZE);
                rowConstraints.setMaxHeight(Region.USE_PREF_SIZE);
                grid.getRowConstraints().add(rowConstraints);
            }

            for (CompaniesData company : companyList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                AnchorPane anchorpane = loader.load();

                ItemController itemController = loader.getController();
                itemController.setData(company);

                anchorpane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                anchorpane.setPrefSize(215, 222);
                anchorpane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(29.5));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private List<CompaniesData> getDatafromDB() {
        List<CompaniesData> companyList = new ArrayList<>();

        String sql = "SELECT * FROM data";
        
        try {
            connect = database.Database.getConnection();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                CompaniesData company = new CompaniesData(
                    result.getInt("id"),
                    result.getInt("no_ijin"),
                    result.getInt("no_gedung"),
                    result.getInt("rt"),
                    result.getInt("rw"),
                    result.getInt("kode_pos"),
                    result.getInt("no_telepon"),
                    result.getString("nama_perusahaan"),
                    result.getString("pemilik"),
                    result.getString("jenis_usaha"),
                    result.getString("provinsi"),
                    result.getString("kabupaten"),
                    result.getString("kecamatan"),
                    result.getString("desa"),
                    result.getString("nama_jalan"),
                    result.getDate("tanggal_berdiri"),
                    result.getString("img")
                );
                companyList.add(company);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return companyList;
    }
    
    private List<CompaniesData> getDatafromDBByType(String jenisUsaha) {
        List<CompaniesData> companyList = new ArrayList<>();
        String sql = "SELECT * FROM data WHERE jenis_usaha = ?";

        try {
            connect = database.Database.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, jenisUsaha);
            result = prepare.executeQuery();

            while (result.next()) {
                CompaniesData company = new CompaniesData(
                    result.getInt("id"),
                    result.getInt("no_ijin"),
                    result.getInt("no_gedung"),
                    result.getInt("rt"),
                    result.getInt("rw"),
                    result.getInt("kode_pos"),
                    result.getInt("no_telepon"),
                    result.getString("nama_perusahaan"),
                    result.getString("pemilik"),
                    result.getString("jenis_usaha"),
                    result.getString("provinsi"),
                    result.getString("kabupaten"),
                    result.getString("kecamatan"),
                    result.getString("desa"),
                    result.getString("nama_jalan"),
                    result.getDate("tanggal_berdiri"),
                    result.getString("img")
                );
                companyList.add(company);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    return companyList;
}
    
    private List<CompaniesData> getDatafromDBBySearch(String searchQuery) {
        List<CompaniesData> companyList = new ArrayList<>();
        String sql = "SELECT * FROM data WHERE nama_perusahaan LIKE ? OR kabupaten LIKE ?";

        try {
            connect = database.Database.getConnection();
            prepare = connect.prepareStatement(sql);
            String query = "%" + searchQuery + "%";
            prepare.setString(1, query);
            prepare.setString(2, query);
            result = prepare.executeQuery();

            while (result.next()) {
                CompaniesData company = new CompaniesData(
                    result.getInt("id"),
                    result.getInt("no_ijin"),
                    result.getInt("no_gedung"),
                    result.getInt("rt"),
                    result.getInt("rw"),
                    result.getInt("kode_pos"),
                    result.getInt("no_telepon"),
                    result.getString("nama_perusahaan"),
                    result.getString("pemilik"),
                    result.getString("jenis_usaha"),
                    result.getString("provinsi"),
                    result.getString("kabupaten"),
                    result.getString("kecamatan"),
                    result.getString("desa"),
                    result.getString("nama_jalan"),
                    result.getDate("tanggal_berdiri"),
                    result.getString("img")
                );
                companyList.add(company);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return companyList;
    }
    
    public void loadItems() {
        
        try {
            List<CompaniesData> companyList = getDatafromDB();
            int row = 0;
            int column = 0;

            grid.getChildren().clear();
            grid.getColumnConstraints().clear();
            grid.getRowConstraints().clear();

            // Setup column constraints
            for (int i = 0; i < 3; i++) {
                ColumnConstraints colConstraints = new ColumnConstraints();
                colConstraints.setPercentWidth(33.33);
                grid.getColumnConstraints().add(colConstraints);
            }

            // Setup row constraints
            int numRows = (companyList.size() + 2) / 3;
            for (int i = 0; i < numRows; i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(Region.USE_COMPUTED_SIZE);
                rowConstraints.setPrefHeight(Region.USE_COMPUTED_SIZE);
                rowConstraints.setMaxHeight(Region.USE_PREF_SIZE);
                grid.getRowConstraints().add(rowConstraints);
            }

            for (CompaniesData company : companyList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                AnchorPane anchorpane = loader.load();

                ItemController itemController = loader.getController();
                itemController.setData(company);

                anchorpane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
                anchorpane.setPrefSize(215, 222);
                anchorpane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(29.5));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    public void itemClickAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputArea.fxml"));
            Parent root = loader.load();
            InputAreaController controller = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            controller.setStage(stage);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void btnTambahAcion(){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputArea.fxml"));
            Parent root = loader.load();
            InputAreaController controller = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            controller.setStage(stage);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void btnUpdateAcion(){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InputAreaUpdate.fxml"));
            Parent root = loader.load();
            InputAreaControllerUpdate controller = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            controller.setStage(stage);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void btnStatisticAction(){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistic.fxml"));
            Parent root = loader.load();
            StatisticController controller = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            controller.setStage(stage);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    private static DashboardController instance;
    public DashboardController() {
        instance = this;
    }

    public static DashboardController getInstance() {
        return instance;
    }
    
    private double x = 0;
    private double y = 0;
    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Pesan sistem");
            alert.setHeaderText(null);
            alert.setContentText("Yakin mau logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                btn_logout.getScene().getWindow().hide();
                
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void close(){
        System.exit(0);
    }
    
    public void minimize(){
        Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadItems();
        search_bar.setOnKeyReleased(event -> handleSearchInput());
    }    
    
}
