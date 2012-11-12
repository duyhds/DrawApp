#include "graphicsFX.h"  

int main(void)
{  
  int n;
  for (n = 0 ; n < 300 ; n += 5)
  {  
	//setColour(red);
    drawOval(50, 50, n, n + 40); 
  }
  return 0;
}