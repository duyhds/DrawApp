package drawapp;
import java.io.File;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.paint.Stop;

public class ParentGroup {
    private Shape shape;
    private Group parent = new Group();
    private Color color = null;
    private double opacity = 1.0;
    private String colorgradientswitch = "Color";
    private LinearGradient gradient;
    
    public void drawLine(int x1, int y1, int x2, int y2){
      Line line = new Line(x1, y1, x2, y2);
      if(color != null){
          line.setStroke(color);
      }
      parent.getChildren().add(line);
    }

    public void drawRect(int x1, int y1, int x2, int y2){
      Rectangle rect = new Rectangle(x1,y1,x2,y2);
      rect.setStroke(Color.BLACK);
      
      if(colorgradientswitch.equals("Color")){
          rect.setFill(color);
      }else if(colorgradientswitch.equals("Gradient")){
          rect.setFill(gradient);
      }
      
      rect.setOpacity(opacity);
      parent.getChildren().add(rect);
    }

    public void fillRect(int x1, int y1, int x2, int y2){
      drawRect(x1,y1,x2,y2);
    }

    public void drawString(int x, int y, String s){
        Text text  = new Text(x,y,s);
        
        if(colorgradientswitch.equals("Color")){
                if(color == null){
                    text.setFill(Color.BLACK);
                 } else {
                    text.setFill(color);
                 } 
        } else if (colorgradientswitch.equals("Gradient")){
            text.setFill(gradient);
        }
  
        parent.getChildren().add(text);
    }

      public void drawImage(int x, int y, String s){
          File file = new File(s);
          Image image = new Image(file.toURI().toString());
          ImageView imageView = new ImageView(image);
          imageView.setX(x);
          imageView.setY(y);  
          imageView.setOpacity(opacity);
          parent.getChildren().add(imageView);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle){
      Arc arc = new Arc(x,y,width,height,startAngle,arcAngle);
      arc.setStroke(Color.BLACK);
      
      if(colorgradientswitch.equals("Color")){
          arc.setFill(color);
      }else if(colorgradientswitch.equals("Gradient")){
          arc.setFill(gradient);
      }
      
      arc.setOpacity(opacity);
      parent.getChildren().add(arc);
    }

    public void drawOval(int x, int y, int width, int height){
      Ellipse ellipse = new Ellipse(x,y,width,height);
      ellipse.setStroke(Color.BLACK);
      ellipse.setFill(color);
      ellipse.setOpacity(opacity);
      parent.getChildren().add(ellipse);
    }
    
    public void setGradient(Color a, Color b){
        colorgradientswitch = "Gradient";
        gradient = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0,a),
                new Stop(1,b)
          });
    }
    
    public Group getGroup(){return parent;}
    public void setColour(Color color){ colorgradientswitch = "Color";this.color = color;}
    public void setOpacity(double opacity){ this.opacity = opacity;}
    
     
}
