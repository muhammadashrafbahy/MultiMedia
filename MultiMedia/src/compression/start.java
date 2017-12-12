/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compression;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MUHAMMAD ASHRAF BAHY
 */
public class start extends Application {
    
  @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        
    Parent root =FXMLLoader.load(getClass().getResource("compression.fxml"));
    Scene scene = new Scene(root);
//    scene.getStylesheets().add(getClass().getResource("RGB_YUVcss.css").toExternalForm());
    
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setTitle("COMPRESSION");
    primaryStage.show();    
        } catch (Exception e) {
            System.out.println("here-------------------->"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
