import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Duck implements MouseListener{
	private int x = (int) (Math.random()*(850-50+1)+50), y = 300;
	private int vx, vy;
	private Image img; // image of the frog
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	Music soundLaugh = new Music("DuckLaugh.wav", false);
	

	public Duck() {
		img = getImage("targetSmall.png"); //load the image for target
		init(x, y); //initialize the location of the image
		vx = (int) ((Math.random()*8)-2);
		vy = (int) ((Math.random()*-3)+1);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		x+=vx;
		y-=vy;
		
		if (x<0||x>800) {
			vx*=-1;
			
		}
		if(y<=-100||y>=500) {
			x = (int) (Math.random()*(850-50+1)+50);
			y = 300;
			vx = (int) ((Math.random()*8)-2);
			vy = (int) ((Math.random()*4)+1);
		}
	
		
		y--;
		tx.setToTranslation(x, y); //must be called any time x and y change
		
		//these are the 2 lines of code needed draw an image on the screen
		g2.drawImage(img, tx, null);   
		
		
		
	}
	public boolean collided(int mX, int mY) {
		System.out.println (mX+":"+mY);
		Rectangle example = new Rectangle(x, y, 150, 150);
		if (example.contains(mX, mY)) {
			System.out.println("ouch");
			
			//what happens to duck if hit
			vx=0;
			vy=-6;
			
		}
		//say haha if it misses by only a little
		else if (example.contains(mX+50, mY+50)||example.contains(mX-50, mY-50)) {
			System.out.println("haha!");
			soundLaugh.play();
		}
		
		
		return false;
		
	}
	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Duck.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("ouch");
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
