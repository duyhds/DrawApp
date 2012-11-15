#include "graphicsFX.h"  

int main(void)
{  
      int x = 0;
	  int i = 0;
	  
	  for( i = 0; i < 20; i++){
			setColour(blue);
			turtleForward(x);
			x = x + 5;
			setColour(yellow);
			turtleRight(x);
			x = x + 5; 
			setColour(green);
			turtleBackward(x);
			x = x + 5;
			setColour(cyan);
			turtleLeft(x);
			x = x + 5;
	  }
	  return 0;
}	