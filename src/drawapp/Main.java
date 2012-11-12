package drawapp;

import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application
{
  @Override public void start(Stage primaryStage) throws Exception {
        final MainWindowFX main = new MainWindowFX(500,300,primaryStage);
        
        Platform.runLater(
            new Runnable(){
                    public void run(){
                        ParentGroup imagePanel = main.getGroup();
                        Reader reader = new InputStreamReader(System.in);
                        Parser parser = new Parser(reader,imagePanel,main);
                        main.setParser(parser);
                    }});
        
        primaryStage.show();
  }
  
  public static void main(String[] args){launch(args);}
}
