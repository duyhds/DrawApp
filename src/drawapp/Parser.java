package drawapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;

public class Parser
{
  private BufferedReader reader;
  private ParentGroup image;
  private MainWindowFX frame;
  private ArrayList<String> commands = new ArrayList<>();
  private int currentposition = 0;
  private int currentx = 0, currenty = 0, oldx = 0, oldy = 0; 
  
  
  public Parser(Reader reader, ParentGroup image, MainWindowFX frame)
  {
    this.reader = new BufferedReader(reader);
    this.image = image;
    this.frame = frame;
  }
  
  public void addtoArrayList(){
        try {
            String x = reader.readLine();
            while(x != null){
                commands.add(x);
                x = reader.readLine();
            }
            commands.add(x);
        } catch (IOException ex) {}
  }
  
  public void parseStep(){
        try {
            if(currentposition < commands.size() - 1){
                String x = commands.get(currentposition);
                parseLine(x);
                frame.postMessage("Current Command: " + x);
                currentposition = currentposition + 1;  
            }
            else{
                frame.postMessage("Drawing Completed");
            }
         
        } catch (ParseException ex) {
            frame.postMessage("Parse failed.");
        }
  }

  public void parse()
  {
    try
    {
      String line = reader.readLine();
      while (line != null)
      {
        parseLine(line);
        line = reader.readLine();
      }
    }
    catch (IOException e)
    {
      frame.postMessage("Parse failed.");
      return;
    }
    catch (ParseException e)
    {
      frame.postMessage("Parse Exception: " + e.getMessage());
      return;
    }
    frame.postMessage("Drawing completed");
  }

  public void parseLine(String line) throws ParseException
  {
    if (line.length() < 2) return;
    String command = line.substring(0, 2);
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DI")) { drawImage(line.substring(3, line.length())); return;}
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("CS")) { changeSize(line.substring(2, line.length())); return;}
    if (command.equals("CO")) { changeOpacity(line.substring(2,line.length())); return;}
    if (command.equals("TF")) { turtleForward(line.substring(2,line.length())); return;}
    if (command.equals("TB")) { turtleBackward(line.substring(2,line.length())); return;}
    if (command.equals("TL")) { turtleLeft(line.substring(2,line.length())); return;}
    if (command.equals("TR")) { turtleRight(line.substring(2,line.length())); return;}

    throw new ParseException(command + " is an unknown command");
  }
  
  public void turtleForward(String args) throws ParseException{
      int temp = 0, temp2 = 0;
      StringTokenizer tokenizer = new StringTokenizer(args);
      currenty = oldy + getInteger(tokenizer);
      image.drawLine(oldx, oldy, currentx, currenty);
      oldy = currenty;
  }
  
  public void turtleBackward(String args) throws ParseException{
      StringTokenizer tokenizer = new StringTokenizer(args);
      currenty = oldy - getInteger(tokenizer);
      image.drawLine(oldx, oldy, currentx, currenty);
      oldy = currenty;
  }
  
  public void turtleLeft(String args) throws ParseException{
      StringTokenizer tokenizer = new StringTokenizer(args);
      currentx = oldx - getInteger(tokenizer);
      image.drawLine(oldx, oldy, currentx, currenty);
      oldx = currentx;
  }
  
  public void turtleRight(String args) throws ParseException{
      StringTokenizer tokenizer = new StringTokenizer(args);
      currentx = oldx + getInteger(tokenizer);;
      image.drawLine(oldx, oldy, currentx, currenty);
      oldx = currentx;
  }

  public void changeOpacity(String args) throws ParseException{
      StringTokenizer tokenizer = new StringTokenizer(args);
      image.setOpacity(Double.parseDouble(tokenizer.nextToken().toString()));
  }

  public void changeSize(String args) throws ParseException{
     int x1 = 0;
     int y1 = 0;
     StringTokenizer tokenizer = new StringTokenizer(args);
     x1 = getInteger(tokenizer);
     y1 = getInteger(tokenizer);
     frame.changeSize(x1, y1);
  }
  
  private void drawLine(String args) throws ParseException{
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    image.drawLine(x1,y1,x2,y2);
  }

  private void drawRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    image.drawRect(x1, y1, x2, y2);
  }

  private void fillRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    image.fillRect(x1, y1, x2, y2);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int startAngle = 0;
    int arcAngle = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    startAngle = getInteger(tokenizer);
    arcAngle = getInteger(tokenizer);
    image.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int width = 0;
    int height = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    image.drawOval(x1, y1, width, height);
  }

  private void drawString(String args) throws ParseException{
    int x = 0;
    int y = 0 ;
    String s = "";
    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    int position = args.indexOf("@");
    if (position == -1) throw new ParseException("DrawString string is missing");
    s = args.substring(position+1,args.length());
    image.drawString(x,y,s);
  }
  
  private void drawImage(String args) throws ParseException{
      int x = 0;
      int y = 0 ;
      String s = "";
      StringTokenizer tokenizer = new StringTokenizer(args);
      x = getInteger(tokenizer);
      y = getInteger(tokenizer);
      int position = args.indexOf("@");
      if (position == -1) throw new ParseException("DrawString string is missing");
      s = args.substring(position+1,args.length());
      image.drawImage(x,y,s);

  }

  private void setColour(String colourName) throws ParseException
  {
    if (colourName.equals("black")) { image.setColour(Color.BLACK); return;}
    if (colourName.equals("blue")) { image.setColour(Color.BLUE); return;}
    if (colourName.equals("cyan")) { image.setColour(Color.CYAN); return;}
    if (colourName.equals("darkgray")) { image.setColour(Color.DARKGRAY); return;}
    if (colourName.equals("gray")) { image.setColour(Color.GRAY); return;}
    if (colourName.equals("green")) { image.setColour(Color.GREEN); return;}
    if (colourName.equals("lightgray")) { image.setColour(Color.LIGHTGRAY); return;}
    if (colourName.equals("magenta")) { image.setColour(Color.MAGENTA); return;}
    if (colourName.equals("orange")) { image.setColour(Color.ORANGE); return;}
    if (colourName.equals("pink")) { image.setColour(Color.PINK); return;}
    if (colourName.equals("red")) { image.setColour(Color.RED); return;}
    if (colourName.equals("white")) { image.setColour(Color.WHITE); return;}
    if (colourName.equals("yellow")) { image.setColour(Color.YELLOW); return;}
    throw new ParseException(colourName + " is not a valid color");
  }
  
  public BufferedReader getReader(){return this.reader;}

  private int getInteger(StringTokenizer tokenizer) throws ParseException
  {
    if (tokenizer.hasMoreTokens())
      return Integer.parseInt(tokenizer.nextToken());
    else
      throw new ParseException("Missing Integer value");
  }
  
}
