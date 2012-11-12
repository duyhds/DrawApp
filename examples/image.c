#include "graphicsFX.h"  

int main(void)
{  
  changeOpacity(0.5);
  changeSize(700,700);
  drawImage("Koala.jpg",100,100);
  changeOpacity(1.0);
  setColour(blue);
  drawRect(10,10,40,50);
  return 0;
}	