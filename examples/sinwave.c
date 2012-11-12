#include "graphicsFX.h" 
#include <math.h> 

#define PI 3.14159265

int main(void)
{  
  int width = 1280;
  int height = 720;
  int x;
  for (x = 0 ; x < width*2 ; x++)
  {  
    double y = (height / 2) - sin(x*(PI/180)) * 100;
    drawRect(x,(int)y,1,1);
  }
  return 0;
}