/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compression;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MUHAMMAD ASHRAF BAHY
 */
public class CompressionController implements Initializable {
    @FXML
    private TextField in_str;
    @FXML
    private Button run_length;
    @FXML
    private Button HuffmanCode;
    @FXML
    private Button lzw;
    @FXML
    private Button shannonfano;
    @FXML
    private Button arth;
    @FXML
    private Button runlen_decompress;
    @FXML
    private Label output;
    @FXML
    private Label output2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enter(ActionEvent event) {
        
        
    }

    @FXML
    private void run_length(ActionEvent event) {
        String str = in_str.getText();
    run_length  rl = new run_length();
    String out = rl.compress(str);
    output.setText(out);
    }

    @FXML
    private void HuffmanCode(ActionEvent event) {
        String str = in_str.getText();
        huffman_code hfc = new huffman_code(str);
        output.setText(hfc.character().toString());
        output2.setText(hfc.probability().toString());
    }

    @FXML
    private void lzw(ActionEvent event) {
       String str = in_str.getText();
    LZW  rl = new LZW();
    String out = rl.compress(str).toString();
    output.setText(out);
    }

    @FXML
    private void shannonfano(ActionEvent event) {
                String str = in_str.getText();
        shannon_code hfc = new shannon_code(str);
        output.setText(hfc.character().toString());
        output2.setText(hfc.probability().toString());
    }

    @FXML
    private void arth(ActionEvent event) {
          String str = in_str.getText();
    arth_comp  rl = new arth_comp();
    String out = rl.compression(str);
    output.setText(out);
    }

    @FXML
    private void runlen_decompress(ActionEvent event) {
        String str  =in_str.getText();
        run_length  rl = new run_length();
    String out = rl.decompress(str);
    output.setText(out);
    
    }
    
}
