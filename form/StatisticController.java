package form;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class StatisticController implements Initializable {
    
    @FXML
    private Button btn_close;

    @FXML
    private PieChart chart_jenisUsaha;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Stage stage;
    
    private void getDataforChart(){
        chart_jenisUsaha.getData().clear();

        String sql = "SELECT jenis_usaha, COUNT(jenis_usaha) AS jumlah FROM data GROUP BY jenis_usaha";
        connect = database.Database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                String jenisUsaha = result.getString("jenis_usaha");
                int jumlah = result.getInt("jumlah"); // Mengambil nilai dari alias "jumlah"

                PieChart.Data slice = new PieChart.Data(jenisUsaha, jumlah);
                chart_jenisUsaha.getData().add(slice);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        getDataforChart();
    }    
    
}
