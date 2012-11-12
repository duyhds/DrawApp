#include <stdio.h>
#include "graphicsFX.h"

void drawLine(int x1, int x2, int x3, int x4)
{
  printf("DL %i %i %i %i\n", x1, x2, x3, x4);
}

void drawRect(int x1, int x2, int x3, int x4)
{
  printf("DR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawOval(int x, int y, int width, int height)
{
  printf("DO %i %i %i %i\n",x,y,width,height);
}

void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
{
  printf("DA %i %i %i %i %i %i\n",x,y,width,height, startAngle, arcAngle);
}

void fillRect(int x1, int x2, int x3, int x4)
{
  printf("FR %i %i %i %i\n", x1, x2, x3, x4);
}

void drawString(char* s, int x, int y)
{
  printf("DS %i %i @%s\n",x,y,s);
}

// Specity the X co-ordinate, Y co-ordinate, and the filename 
void drawImage(char* s, int x, int y){
	printf("DI %i %i @%s\n",x,y,s);
}

// Enter new X and Y sizes 
void changeSize(int x, int y){
	printf("CS %i %i\n",x,y);
}

// Enter opacity level from 0.0 to 1.0 (higher the number the more opaque and less transparent).
void changeOpacity(double x){
	printf("CO %f\n",x);
}

// Move turtle forward by X 
void turtleForward(int x){
	printf("TF %i\n",x);
}

// Move tutrle right by X
void turtleRight(int x){
	printf("TR %i\n",x);
}

// Move turtle backawrds by X
void turtleBackward(int x){
	printf("TB %i\n",x);
}

// Move tutrtle left by X
void turtleLeft(int x){
	printf("TL %i\n",x);
}

void setColour(colour c)
{
  char* colourName;
  switch(c)
  {
    case black : colourName = "black"; break;
    case blue : colourName = "blue"; break;
    case cyan : colourName = "cyan"; break;
    case darkgray : colourName = "darkgray"; break;
    case gray : colourName = "gray"; break;
    case green : colourName = "green"; break;
    case lightgray : colourName = "lightgray"; break;
    case magenta : colourName = "magenta"; break;
    case orange : colourName = "orange"; break;
    case pink : colourName = "pink"; break;
    case red : colourName = "red"; break;
    case white : colourName = "white"; break;
    case yellow : colourName = "yellow"; break;
  }
  printf("SC %s\n", colourName);
}