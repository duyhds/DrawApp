#include "graphicsFX.h"  

int main(void)
{  
      int x = 0;
	  int i = 0;
	  
	  for( i = 0; i < 20; i++){
			turtleForward(x);
			x = x + 5;
			turtleRight(x);
			x = x + 5; 
			turtleBackward(x);
			x = x + 5;
			turtleLeft(x);
			x = x + 5;
	  }
	  return 0;
}	