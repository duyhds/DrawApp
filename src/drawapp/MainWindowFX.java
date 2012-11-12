package drawapp;
import javafx.scene.control.*; 
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.SnapshotParameters;

import javax.imageio.ImageIO;


public class MainWindowFX {
  public int DEFAULT_WIDTH = 800, DEFAULT_HEIGHT = 600;
  final ParentGroup parent = new ParentGroup();
  private Stage primaryStage = new Stage();  
  private Parser parse;
  final private Button autorun      = new Button("  Auto-run ");
  final private Button next         = new Button("   Next    ");
  final private Button close        = new Button("   Close   ");
  final private Button snap         = new Button("Take Snapshot");
  final private Label text = new Label("");
  private int numberoftime = 0;
  private String firstbuttonpressed = "unpressed";
  final TextField input = new TextField();
  
  javafx.embed.swing.SwingFXUtils fXUtils;
    BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
    File file = new File("Snapshot.jpg");
    VBox vbox = null;
  
  public MainWindowFX(int width, int height, final Stage primaryStage){
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("DrawApp JavaFX version by Vincent");
      init();
  }
  public final void init(){
      final BorderPane bp = new BorderPane();
      bp.setPadding(new Insets(10,20,10,20));
      
      next.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e){
               if(firstbuttonpressed.equals("unpressed") || firstbuttonpressed.equals("Next")){
                        firstbuttonpressed = "Next";
                        if(numberoftime == 0){
                            parse.addtoArrayList();
                            numberoftime = numberoftime + 1;
                        }
                        parse.parseStep();
                } else{
                      postMessage("You already pressed the " + firstbuttonpressed + " button");
                  }
               }
      });
      
      autorun.setOnAction(new EventHandler<ActionEvent> () { 
          @Override 
          public void handle(ActionEvent e){
              if(firstbuttonpressed.equals("unpressed") || firstbuttonpressed.equals("Auto-run")){
                  firstbuttonpressed = "Auto-run";
                  parse.parse();
              }
              else{
                  postMessage("You already pressed the " + firstbuttonpressed + " button");
              }
      }});

      vbox = new VBox();
      vbox.getChildren().addAll(parent.getGroup());
      // used http://stackoverflow.com/questions/12330720/how-to-convert-node-to-image-in-javafx-2-1
      snap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            // here we make image from vbox and add it to scene, can be repeated :)
           WritableImage snapshot = vbox.snapshot(new SnapshotParameters(), null);
                saveImage(snapshot);
                postMessage("Snapshot taken");
            }
        });
      
      close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
      

      VBox box = new VBox(20);
      box.getChildren().addAll(autorun,next,snap,close,text);
      bp.setCenter(vbox);
      bp.setBottom(box);
     // bp.setLeft(box);
     
      primaryStage.setScene(new Scene(bp,DEFAULT_WIDTH,DEFAULT_HEIGHT));
      primaryStage.setResizable(false);
  }
  
  // used http://stackoverflow.com/questions/12330720/how-to-convert-node-to-image-in-javafx-2-1
  private void saveImage(WritableImage snapshot) {
        BufferedImage image;
        image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, bufferedImage);
        try {
            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.translate(vbox.getWidth(), vbox.getHeight());
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
            Logger.getLogger(MainWindowFX.class.getName()).log(Level.SEVERE, null, ex);
        };
      }

  public void changeSize(int x, int y){this.primaryStage.setWidth(x);this.primaryStage.setHeight(y);}
  public void setParser(Parser parser){this.parse = parser;}
  public void postMessage(String s){text.setText(s);}
  public ParentGroup getGroup(){return parent;}
}
