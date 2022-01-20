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
	private String color, head, action, direction, fileType;
	private Mushroom priority;
	private boolean faceLeft, attacking, occupied;
	private Image img;
	private AffineTransform tx;

	public Pikmin(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.xv = 0;
		this.yv = 0;
		priority = null;
		occupied = false;
		attacking = false;
		flowerTimer = 1000;
		
		this.color = color;
		head = "Leaf";
		action = "Stand";
		fileType = ".png";
		int chance = (int)(Math.random()*2);
		if(chance == 0) {
			direction = "Left";
		}else {
			direction = "Right";
		}
		
		img = getImage("/imgs/" + color + "Pikmin" + head + action + direction + fileType);
		
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void setPriority(Mushroom x) {
		if(occupied == false) {
			if(priority == null) {
				priority = x;
			}else {
				if(Math.abs(priority.getX() + 120 - this.x) + Math.abs(priority.getY() + 130 - y) >
				   Math.abs(x.getX() + 120 - this.x) + Math.abs(x.getY() + 130 - y)) {
					priority = x;
				}
			}
		}
	}
	
	public void moveRandom() {
		if(occupied == false) {
			moveTimer = (int) (Math.random()*15) + 1;
			xv = (int) (Math.random()*3) - 1;
			yv = (int) (Math.random()*3) - 1;
		}
	}
	
	public void follow() {
		if(priority != null && attacking == false) {
			if(priority.getX() + 60 > this.x) {
				xv = 2;
			}else if(priority.getX() + 100 < this.x) {
				xv = -2;
			}
			if(priority.getY() + 100 > this.y) {
				yv = 2;
			}else if(priority.getY() + 99 < this.y) {
				yv = -2;
			}
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
		x += xv;
		y += yv;
		
		if(x < 0) {
			x += 2;
		}
		if(y < 0) {
			y += 2;
		}
		
		//random movement
		if(occupied == false) {
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
		}
		
		//attack timer
		if(attackTimer >= 0 && priority != null && attacking == true) {
			if(attackTimer == 0) {
				attackTimer = 5;
				priority.damage();
			}
			attackTimer --;
		}
		
		if(xv == 0 && yv == 0) {
			if(attacking == false) {
				action = "Stand";
				fileType = ".png";
			}else {
				action = "Attack";
				fileType = ".gif";
			}
		}else if(xv > 0) {
			action = "Walk";
			direction = "Right";
			fileType = ".gif";
		}else {
			action = "Walk";
			direction = "Left";
			fileType = ".gif";
		}
		
		if(priority != null && priority.getHealth() > 0) {
			occupied = true;	
		}else {
			priority = null;
			occupied = false;
			attacking = false;
		}
		if(occupied == true) {
			follow();
		}
		
		if(priority != null) {
			if(priority.getX() + 60 <= this.x && priority.getX() + 90 >= this.x && priority.getY() + 99 <= this.y && priority.getY() + 100 >= this.y) {
				attacking = true;
			}else {
				attacking = false;
			}
		}
		
		if(attacking == true) {
			xv = 0;
			yv = 0;
		}
		
		if(flowerTimer >= 0) {
			flowerTimer --;
			if(flowerTimer == 500){
				head = "Bud";
			}
			if(flowerTimer == 0) {
				head = "Flower";
			}
		}
		
		img = getImage("/imgs/" + color + "Pikmin" + head + action + direction + fileType);
		
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
