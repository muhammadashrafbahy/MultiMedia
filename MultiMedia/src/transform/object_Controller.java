/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package transform;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * FXML Controller class
 *
 * @author MUHAMMAD ASHRAF BAHY
 */
public class object_Controller implements Initializable {
    @FXML
    private Button trans;
    @FXML
    private Button rotat;
    @FXML
    private Button scale;
    @FXML
    private Rectangle rect;
    @FXML
    private Button draw_btn;
    @FXML
    private TextField trans_x;
    @FXML
    private TextField trans_y;
    @FXML
    private TextField scale_x;
    @FXML
    private TextField scale_y;
    @FXML
    private TextField angle;
    @FXML
    private TextField rec_x;
    @FXML
    private TextField rec_y;
    @FXML
    private TextField width;
    @FXML
    private TextField height;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void transl_obj(ActionEvent event) {
//    Translate translate = new Translate();       
//      
//      //Setting the X,Y,Z coordinates to apply the translation 
//      translate.setX(250); 
//      translate.setY(0); 
////      translate.setZ(0); 
//       
//      //Adding all the transformations to the rectangle 
//      rect.getTransforms().addAll(translate); 
        double tra_x  =Double.parseDouble(trans_x.getText());
        double tra_y  =Double.parseDouble(trans_y.getText());
  rect.setX(rect.getX()+tra_x);
  rect.setY(rect.getY()+tra_y);
    
    }

    @FXML
    private void rotat(ActionEvent event) {
    
//     Rotate rotate = new Rotate(); 
//      
//      //Setting the angle for the rotation 
//      rotate.setAngle(20); 
//      
//      //Setting pivot points for the rotation 
////      rotate.setPivotX(150); 
////      rotate.setPivotY(225);
//    
//      rect.getTransforms().addAll(rotate); 
        
       
    double rot_angle =  Double.parseDouble(angle.getText());
    rect.setRotate(rot_angle);
//    int [][] rot_matrix ={{},{}};
//    
//    double new_x = (rect.getX()*Math.cos(rot_angle))-(rect.getY()*Math.sin(rot_angle));
//    double new_y = (rect.getX()*Math.sin(rot_angle))+(rect.getY()*Math.cos(rot_angle));
//    rect.setX(new_x);
//    rect.setY(new_y);
//    rect.setWidth(rect.getWidth()+new_x);
//    rect.setHeight(rect.getHeight()+new_y);
    }
@FXML
    private void scale_btn(ActionEvent event) {
        try {
            
    
//    Scale scale = new Scale(); 
      
//      //Setting the dimensions for the transformation 
//      scale.setX(1.5); 
//      scale.setY(1.5); 
//      
//      //Setting the pivot point for the transformation 
////      scale.setPivotX(300); 
////      scale.setPivotY(135); 
//    rect.getTransforms().addAll(scale);  
        double scal_x  =Double.parseDouble(scale_x.getText());
        double scal_y  =Double.parseDouble(scale_y.getText());
        
            System.out.println("x  =  "+rect.getX()*scal_x);
            System.out.println("y  =  "+rect.getY()*scal_y);
//  rect.setX(rect.getX()*scal_x);
//  rect.setY(rect.getY()*scal_y);
//  rect.setWidth(rect.getWidth()*scal_x);
//  rect.setHeight(rect.getHeight()*scal_y);
            rect.setScaleX(scal_x);
            rect.setScaleY(scal_y);
      } catch (Exception e) {
            System.out.println("here ----> "+e.getMessage());  
      }
    }

    @FXML
    private void draw(ActionEvent event) {
        
        double x = Double.parseDouble( rec_x.getText());
        double y = Double.parseDouble( rec_y.getText());
        double rec_width = Double.parseDouble( width.getText());
        double rec_height = Double.parseDouble( height.getText());
      rect.setX(x);
      rect.setY(y);
      rect.setWidth(rec_width);
      rect.setHeight(rec_height);
      
    }

 
    
}
