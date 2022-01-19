import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Pikmin {
	
	private int x, y, xv, yv, flowerTimer, moveTimer, randomTimer, attackTimer, state;
	private Mushroom priority;
	private boolean faceLeft, attacking, occupied;
	private Image img;
	private AffineTransform tx;

	public Pikmin(int x, int y) {
		this.x = x;
		this.y = y;
		this.xv = 0;
		this.yv = 0;
		
		img = getImage("/imgs/redPikminStandRight.png");
		
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void setPriority(Mushroom x) {
		priority = x;
	}
	
	public Mushroom getPriority() {
		return priority;
	}
	
	public void moveRandom() {
		if(occupied == false) {
			moveTimer = (int) (Math.random()*15) + 1;
			xv = (int) (Math.random()*3) - 1;
			yv = (int) (Math.random()*3) - 1;
		}
	}
	
	public void follow(Mushroom x) {
		//something to follow the mushroom
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actually picture location
		update();
		g2.drawImage(img, tx, null);
	}
	
	private void update() {
		x += xv;
		y += yv;
		
		if(randomTimer > 0 && moveTimer == 0) {
			randomTimer --;
		}
		if(randomTimer == 0) {
			moveRandom();
			randomTimer = (int)(Math.random() * 20) + 40;
		}
		
		if(moveTimer > 0) {
			moveTimer --;
		}else {
			xv = 0; 
			yv = 0;
		}
		
		if(xv == 0 && yv == 0) {
			
		}else if(xv > 0) {
			
		}else {
			
		}
		
		tx.setToTranslation(x, y);
		tx.scale(.5	, .5);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(50, 50);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Pikmin.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
