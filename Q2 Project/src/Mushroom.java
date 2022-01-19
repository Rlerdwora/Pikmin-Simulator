import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Mushroom {
	private int x, y, xv, yv, flowerTimer, moveTimer, randomTimer, attackTimer, state;
	private boolean faceLeft, attacking, occupied;
	private Image img; 	
	private AffineTransform tx;

	public Mushroom(int x, int y) {
		
		int chance = (int) (Math.random()*2) + 1;
			
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void move() {
		if(occupied == false) {
			moveTimer = (int) (Math.random()*15) + 1;
			xv = (int) (Math.random()*11) - 5;
			xv = (int) (Math.random()*11) - 5;
		}
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actually picture location
		update();
		g2.drawImage(img, tx, null);
	}
	
	
	private void update() {
		
		
		
		tx.setToTranslation(x, y);
		tx.scale(1	, 1);
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
