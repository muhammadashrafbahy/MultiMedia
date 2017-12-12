/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multimedia;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
//import boofcv.alg.color.ColorYuv;

/**
 * FXML Controller class
 *
 * @author MUHAMMAD ASHRAF BAHY
 */
public class RGB_YUV_controllers implements Initializable {
    @FXML
    private ImageView bfr_img;
    @FXML
    private ImageView aftr_img;
    @FXML
    private Button rgb_yuv;
    @FXML
    private Button yuv_rgb;
    @FXML
    private Button subsampling;


//private static     BufferedImage  BFimage = new BufferedImage(bf., height, imageType)
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void rgb_to_yuv(ActionEvent event) throws IOException {
      
        try {
         BufferedImage  BFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);    
        BufferedImage  AFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);
               FileChooser fc = new FileChooser();
        fc.setTitle("CHOOSE YOUR PHOTO");
        fc.setInitialDirectory(new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia"));
    Node node = (Node) event.getSource();
        File img_file = fc.showOpenDialog(node.getScene().getWindow());
 BFimage =ImageIO.read(img_file);

   bfr_img.setImage(new Image(img_file.toURI().toString()));
        
  RGB_YUV_controllers co = new  RGB_YUV_controllers();

        co.output(co.initmatrix_rgb2yuv(BFimage));
    File f  = new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia\\out.PNG");
   
        
    AFimage = ImageIO.read(f);
    aftr_img.setImage(new  Image(f.toURI().toString()));
  
         }catch(Exception e){
        System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }}

    
    

    
    
    
    public Color [][] initmatrix_rgb2yuv_subsampling(BufferedImage img){
    double wr =0.299;
    double wb =0.114;
    double wg =0.587;
    
    double u_max=0.436;
    double v_max=0.615;
    Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
    Color [][] matrix_subsampl = new Color[img.getWidth()][img.getHeight()];
    Color [][] matrix_ssres = new Color[img.getWidth()][img.getHeight()];
    try {
        
//Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
for (int x = 0; x < img.getWidth(); x++)
        {
            for (int y = 0; y < img.getHeight(); y++)
            {
                 int pixel = img.getRGB(x,y);
                 int a = (pixel >> 24 ) & 0x0FF;
                 int r = (pixel >> 16 ) & 0x0FF;
                 int g = (pixel >> 8  ) & 0x0FF;
                 int b = (pixel       ) & 0x0FF;
//                Color mycolor = new Color(img.getRGB(x, y));
//                 int r =mycolor.getRed();
//                 int g = mycolor.getGreen();
//                 int b =mycolor.getBlue();
//                
                int  yy =(int) Math.floor(wr*r+wg*g+wb*b);
//                int  uu =(int) Math.floor(u_max*((b-yy)/(1-wb)));
                int  uu =(int) Math.floor(-0.147*r - 0.289*g + 0.436*b);
                int  vv =(int) Math.floor(0.615*r - 0.515*g - 0.100*b);
//                int  vv =(int) Math.floor(v_max*((r-yy)/(1-wr)));
//                 ColorYuv cv = new ColorYuv(); 
//                 System.out.println(yy);
                         if (yy >255)  {
                        yy=255;
                    }else if (yy<0) {
                         yy=0;
                    }
                         if (uu >255)  {
                        uu=255;
                    }else if (uu<0) {
                         uu=0;
                    }
                         if (vv >255)  {
                        vv=255;
                    }else if (vv<0) {
                         vv=0;
                    }
                 matrix1 [x][y] = new Color(yy ,uu, vv);
                 
            }
                 
                 for (int i = 0; i < matrix1.length; i+=2) {
                 for (int j = 0; j < matrix1[0].length; j+=2) {
                     int y_ss = matrix1[i][j].getRed();
                     int u_ss = matrix1[i][j].getGreen();
                     int v_ss = matrix1[i][j].getBlue();
               matrix_subsampl[i][j]= new Color(y_ss ,u_ss, v_ss);
                     if (i+1<matrix1.length ) {
                         if (j+1< matrix1[0].length) {
                             
                         
                     
                int y_next_ss = matrix1[i+1][j+1].getRed();
               matrix_subsampl[i+1][j+1]= new Color(y_next_ss ,0, 0);
                     }
                }
                 }
                 }
                  BufferedImage  AFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);
   
     
        for (int z = 0; z < matrix_subsampl.length; z++)
        {
            for (int q = 0; q < matrix_subsampl[0].length; q++)
            { 
               
//             
              AFimage.setRGB(z, q, matrix_subsampl[z][q].getRGB());
              
            }
        }
                 matrix_ssres = new RGB_YUV_controllers().initmatrix_yuv2rgb(AFimage);
//                 matrix1 [x][y] = new Color(r, g,b,a);
       
//                 System.out.println("pixel "+ r + g +b);
           }

//                 System.out.println("size int "+ matrix1.length );

    } catch (Exception e) {
           System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }
    return matrix_ssres;

}
    
    public Color [][] initmatrix_rgb2yuv(BufferedImage img){
    double wr =0.299;
    double wb =0.114;
    double wg =0.587;
    
    double u_max=0.436;
    double v_max=0.615;
    Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
    try {
        
//Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
for (int x = 0; x < img.getWidth(); x++)
        {
            for (int y = 0; y < img.getHeight(); y++)
            {
                 int pixel = img.getRGB(x,y);
                 int a = (pixel >> 24 ) & 0x0FF;
                 int r = (pixel >> 16 ) & 0x0FF;
                 int g = (pixel >> 8  ) & 0x0FF;
                 int b = (pixel       ) & 0x0FF;
//                Color mycolor = new Color(img.getRGB(x, y));
//                 int r =mycolor.getRed();
//                 int g = mycolor.getGreen();
//                 int b =mycolor.getBlue();
//                
                int  yy =(int) Math.floor(wr*r+wg*g+wb*b);
//                int  uu =(int) Math.floor(u_max*((b-yy)/(1-wb)));
                int  uu =(int) Math.floor(-0.147*r - 0.289*g + 0.436*b);
                int  vv =(int) Math.floor(0.615*r - 0.515*g - 0.100*b);
//                int  vv =(int) Math.floor(v_max*((r-yy)/(1-wr)));
//                 ColorYuv cv = new ColorYuv(); 
//                 System.out.println(yy);
                         if (yy >255)  {
                        yy=255;
                    }else if (yy<0) {
                         yy=0;
                    }
                         if (uu >255)  {
                        uu=255;
                    }else if (uu<0) {
                         uu=0;
                    }
                         if (vv >255)  {
                        vv=255;
                    }else if (vv<0) {
                         vv=0;
                    }
                 matrix1 [x][y] = new Color(yy ,uu, vv);
//                 matrix1 [x][y] = new Color(r, g,b,a);
       
//                 System.out.println("pixel "+ r + g +b);
           }}

//                 System.out.println("size int "+ matrix1.length );

    } catch (Exception e) {
           System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }
    return matrix1;

}
  
    
    ////////////////////////////////////////////////////////////////////yuv2rgb
        @FXML
    private void yuv_to_rgb(ActionEvent event) {
     try {
         BufferedImage  BFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);    
        BufferedImage  AFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);
               FileChooser fc = new FileChooser();
        fc.setTitle("CHOOSE YOUR PHOTO");
        fc.setInitialDirectory(new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia"));
    Node node = (Node) event.getSource();
        File img_file = fc.showOpenDialog(node.getScene().getWindow());
 BFimage =ImageIO.read(img_file);

   bfr_img.setImage(new Image(img_file.toURI().toString()));
        
  RGB_YUV_controllers co = new  RGB_YUV_controllers();

        co.output(co.initmatrix_yuv2rgb(BFimage));
    File f  = new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia\\out.PNG");
   
        
    AFimage = ImageIO.read(f);
    aftr_img.setImage(new  Image(f.toURI().toString()));
  
         }catch(Exception e){
        System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }
    }
    
    
    
    public Color [][] initmatrix_yuv2rgb(BufferedImage img){
    double wr =0.299;
    double wb =0.114;
    double wg =0.587;
    
    double u_max=0.436;
    double v_max=0.615;
    Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
    try {
        
//Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
for (int x = 0; x < img.getWidth(); x++)
        {
            for (int y = 0; y < img.getHeight(); y++)
            {
                 int pixel = img.getRGB(x,y);
                 int a = (pixel >> 24 ) & 0x0FF;
                 int yy = (pixel >> 16 ) & 0x0FF;
                 int uu = (pixel >> 8  ) & 0x0FF;
                 int vv = (pixel       ) & 0x0FF;
//                Color mycolor = new Color(img.getRGB(x, y));
//                 int r =mycolor.getRed();
//                 int g = mycolor.getGreen();
//                 int b =mycolor.getBlue();
//                
                int  r =(int) Math.floor(yy+(vv*((1-wr)/(v_max))));
//                int  uu =(int) Math.floor(u_max*((b-yy)/(1-wb)));
                int  g =(int) Math.floor((yy/wg)-(uu*((wb*(1-wb))/u_max*wg))-(vv*((wr*(1-wr))/v_max*wg))  );
                int  b =(int) Math.floor(yy+(uu*((1-wb)/(u_max))));
//                int  vv =(int) Math.floor(v_max*((r-yy)/(1-wr)));
//                 ColorYuv cv = new ColorYuv(); 
//                 System.out.println(yy);
                         if (r >255)  {
                        r=255;
                    }else if (r<0) {
                         r=0;
                    }
                         
                         
                         if (g >255)  {
                        g=255;
                    }else if (g<0) {
                         g=0;
                    }
                         
                         
                         if (b >255)  {
                        b=255;
                    }else if (b<0) {
                         b=0;
                    }
                 matrix1 [x][y] = new Color(r ,g, b);
//                 matrix1 [x][y] = new Color(r, g,b,a);
       
//                 System.out.println("pixel "+ r + g +b);
           }}

//                 System.out.println("size int "+ matrix1.length );

    } catch (Exception e) {
           System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }
    return matrix1;

}
    
    
    



/////////////////////////////////////////////////////////////////////////////subsampi//////////////////////

    @FXML
    private void subsampling(ActionEvent event)  throws IOException {
      
        try {
         BufferedImage  BFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);    
        BufferedImage  AFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);
               FileChooser fc = new FileChooser();
        fc.setTitle("CHOOSE YOUR PHOTO");
        fc.setInitialDirectory(new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia"));
    Node node = (Node) event.getSource();
        File img_file = fc.showOpenDialog(node.getScene().getWindow());
 BFimage =ImageIO.read(img_file);

   bfr_img.setImage(new Image(img_file.toURI().toString()));
        
  RGB_YUV_controllers co = new  RGB_YUV_controllers();

        co.output(co.initmatrix_rgb2yuv_subsampling(BFimage));
    File f  = new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia\\out.PNG");
   
        
    AFimage = ImageIO.read(f);
    aftr_img.setImage(new  Image(f.toURI().toString()));
  
         }catch(Exception e){
        System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }}
     
    public Color [][] subsampling(BufferedImage img){
    double wr =0.299;
    double wb =0.114;
    double wg =0.587;
    
    double u_max=0.436;
    double v_max=0.615;
    Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
    try {
        
//Color [][] matrix1 = new Color[img.getWidth()][img.getHeight()];
for (int x = 0; x < img.getWidth(); x++)
        {
            for (int y = 0; y < img.getHeight(); y++)
            {
                 int pixel = img.getRGB(x,y);
                 int a = (pixel >> 24 ) & 0x0FF;
                 int r = (pixel >> 16 ) & 0x0FF;
                 int g = (pixel >> 8  ) & 0x0FF;
                 int b = (pixel       ) & 0x0FF;
//                Color mycolor = new Color(img.getRGB(x, y));
//                 int r =mycolor.getRed();
//                 int g = mycolor.getGreen();
//                 int b =mycolor.getBlue();
//                
                int  yy =(int) Math.floor(wr*r+wg*g+wb*b);
//                int  uu =(int) Math.floor(u_max*((b-yy)/(1-wb)));
                int  uu =(int) Math.floor(-0.147*r - 0.289*g + 0.436*b);
                int  vv =(int) Math.floor(0.615*r - 0.515*g - 0.100*b);
//                int  vv =(int) Math.floor(v_max*((r-yy)/(1-wr)));
//                 ColorYuv cv = new ColorYuv(); 
//                 System.out.println(yy);
                         if (yy >255)  {
                        yy=255;
                    }else if (yy<0) {
                         yy=0;
                    }
                         if (uu >255)  {
                        uu=255;
                    }else if (uu<0) {
                         uu=0;
                    }
                         if (vv >255)  {
                        vv=255;
                    }else if (vv<0) {
                         vv=0;
                    }
                         
                         List<Integer> bfr_ss = new ArrayList<>();
                         
                 matrix1 [x][y] = new Color(yy ,uu, vv);

           }}


    } catch (Exception e) {
           System.out.println("here+++++++>  " +e.getMessage());
        e.printStackTrace();
    }
    return matrix1;

}
//        *//////////////////////////////////////////////////////////////out
public void output(Color[][] matrix ){
 
     BufferedImage  AFimage = new BufferedImage(408, 552, BufferedImage.TYPE_INT_ARGB);
    try {
        File f = new File("C:\\Users\\MUHAMMAD ASHRAF BAHY\\Documents\\NetBeansProjects\\MultiMedia\\src\\multimedia\\out.PNG");
        FileInputStream fs = new FileInputStream(f);
//        System.out.println("SIZE"+matrix.length);
        for (int x = 0; x < matrix.length; x++)
        {
            for (int y = 0; y < matrix[0].length; y++)
            { 
               
//                System.out.println("rgb "  +matrix[x][y].getRGB());  
              AFimage.setRGB(x, y, matrix[x][y].getRGB());
              
            }
        }
      ImageIO.write(AFimage, "PNG", f);
       
    
    } catch (Exception e) {
    System.out.println("here+++++++>  " +e.getMessage());
    e.printStackTrace();
    } 
}
    
    
}
