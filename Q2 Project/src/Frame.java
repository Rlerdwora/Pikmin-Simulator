import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	//instantiating objects
	Background b = new Background(0,0);
	Button rButton = new Button(5,10,"redPikmin");
	Button bButton = new Button(80,10,"bluePikmin");
	Button yButton = new Button(155,10,"yellowPikmin");
	Button mButton = new Button(230,10,"mushroom");
	ArrayList<Pikmin> p = new ArrayList<Pikmin>();
	ArrayList<Mushroom> m = new ArrayList<Mushroom>();
	
	//button hitboxes
	int rbhbX = rButton.getX() + 6, rbhbY = rButton.getY() + 6;
	int bbhbX = bButton.getX() + 6, bbhbY = bButton.getY() + 6;
	int ybhbX = yButton.getX() + 6, ybhbY = yButton.getY() + 6;
	int mbhbX = mButton.getX() + 6, mbhbY = mButton.getY() + 6;
	
	//variables
	int itemType = 1;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		rButton.paint(g);
		bButton.paint(g);
		yButton.paint(g);
		mButton.paint(g);
		
		for(Mushroom x : m) {
			x.paint(g);
		}
		
		for(Pikmin x : p) {
			x.paint(g);
			for(Mushroom y : m) {
				x.setPriority(y);
			}
		}
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Pikmin Simulator");
		f.setSize(new Dimension(700, 700));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(arg0.getX() < rbhbX  + 64 && arg0.getX() > rbhbX && arg0.getY() < rbhbY + 64 && arg0.getY() > rbhbY) {
			itemType = 1;
		}else if(arg0.getX() < bbhbX  + 64 && arg0.getX() > bbhbX && arg0.getY() < bbhbY + 64 && arg0.getY() > bbhbY) {
			itemType = 2;
		}else if(arg0.getX() < ybhbX  + 64 && arg0.getX() > ybhbX && arg0.getY() < ybhbY + 64 && arg0.getY() > ybhbY) {
			itemType = 3;
		}else if(arg0.getX() < mbhbX  + 64 && arg0.getX() > mbhbX && arg0.getY() < mbhbY + 64 && arg0.getY() > mbhbY) {
			itemType = 4;
		}else {
			if(itemType == 1) {
				Pikmin x = new Pikmin(arg0.getX() - 35, arg0.getY() - 30, "red");
				p.add(x);
			}else if(itemType == 2) {
				Pikmin x = new Pikmin(arg0.getX() - 35, arg0.getY() - 30, "blue");
				p.add(x);
			}else if(itemType == 3) {
				Pikmin x = new Pikmin(arg0.getX() - 35, arg0.getY() - 30, "yellow");
				p.add(x);
			}else if(itemType == 4) {
				Mushroom x = new Mushroom(arg0.getX() - 135, arg0.getY() - 130);
				m.add(x);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
