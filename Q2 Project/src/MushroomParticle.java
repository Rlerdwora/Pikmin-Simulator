import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class MushroomParticle {
	private int x, y, xv, yv, timer;
	private Image img; 	
	private AffineTransform tx;

	public MushroomParticle(int x, int y) {
		
		this.x = (int)(Math.random()*3 - 1) + x;
		this.y = (int)(Math.random()*3 - 1) + y;
		xv = (int)(Math.random()*4) - 2;
		yv = (int)(Math.random()*5) + 1;
		timer = 20;
		img = getImage("/imgs/mushroomParticle.gif");
		
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		if(timer > 0) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
	
			//call update to update the actually picture location
			update();
			g2.drawImage(img, tx, null);
		}
	}
	
	
	private void update() {
		x += xv;
		y += yv;
		yv ++;
		
		timer --;
		
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
