# java2
package pack;
import zen.core.Zen;

public class MysteryGame {
	
	static int y=150;

	static int x = 400; 
	static int x2 = 600;
	static int c1 = Zen.getRandomNumber(100,500);
	static int c2 = Zen.getRandomNumber(100,500); 
	static int gravity=1;

	
	public static void main(String[] args) {
		Zen.create(400,600);
		
	
		
		
		
		while (true){
			draw();
			move ();
			x = x-4;
			x2 = x2-4;
			if (x2< -50) { 
				x2=400;
				c2 = Zen.getRandomNumber(100, 500);
			}
			if( x < -50) { 
				x=400;
				c1 = Zen.getRandomNumber(100,500);
			}
			if (x > 180 && x2< 220){
				if (y < c1-50) { 
					y = 150; 
					gravity = 1; 
					x =400;
					x2= 600; 
				}
				
			}
			// Move the rock. 
			y+=gravity;
			gravity +=2.1; 
			if (Zen.isMouseClicked()) { 
				gravity =- 20;
			}
			if (y>600 || y<0) {
				reset();
			}
			
			
			
			Zen.buffer(33);
			
		}
	}

	private static void reset() {
		y=150;
		gravity +=1;
	}

	private static void move() {
		
		
	}

	private static void draw() {
		Zen.setBackground("light blue");
		Zen.setColor("yellow");
		// draw the rock 
		Zen.fillOval(180,y,20,20);
		Zen.setColor("green");
		Zen.fillRect(x, 0, 50, c1 -50);
		Zen.fillRect(x, c1+50,50, 550-c1);
		Zen.fillRect(x2, 0, 50, c2-50);
		Zen.fillRect(x2, c2 +50,50, 550-c2);
	}

}
