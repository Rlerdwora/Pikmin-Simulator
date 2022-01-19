import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Mushroom {
	private int x, y, health, state, temp;
	private ArrayList<MushroomParticle> m = new ArrayList<MushroomParticle>();
	private Image img; 	
	private AffineTransform tx;

	public Mushroom(int x, int y) {
		
		this.x = x;
		this.y = y;
		state = 1;
		temp = state;
		health = 100;
		
		img = getImage("/imgs/mushroom" + state + ".png");
		
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void damage() {
		health --;;
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		if(health > 0) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
	
			//call update to update the actually picture location
			update();
			g2.drawImage(img, tx, null);
		}
		for(MushroomParticle x : m) {
			x.paint(g);
		}
		g.drawRect(x + 47, y + 60, 151, 140);
	}
	
	
	private void update() {
		
		img = getImage("/imgs/mushroom" + state + ".png");
		
		if(health <= 100 && health >= 75) {
			state = 1;
		}else if(health < 75 && health >= 50) {
			state = 2;
		}else if(health < 50 && health >= 25) {
			state = 3;
		}else {
			state = 4;
		}
		if(temp != state) {
			temp = state;
			for(int i = 0; i < 5; i ++) {
				MushroomParticle mp = new MushroomParticle(x,y);
				m.add(mp);
			}
		}
		
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
