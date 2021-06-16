import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Farmer farmer;
	public Cursor cursor;
	public Snake snake;
	public Rat rat;
	public Pig pig;
	public static int status;
	public RenderPanel()
	{
		farmer = new Farmer();
		cursor = new Cursor();
		snake = new Snake();
		rat = new Rat();
		pig = new Pig();
		status = 300;
	}
	public void step() {
		farmer.step();
		cursor.step();
		snake.step();
		rat.step();
		pig.step();
		repaint();
		if (status > 300)
		{
			status = 300+ (status-300)*10/11;
		}
		status -= (ManagerPanel.universalPhase%5==0)?1:0;
		if (status <=20)
		{
			ManagerPanel.nextState= true;
		}
		
	}
	
	
	public void paintComponent(Graphics g){ //draws screen
        super.paintComponent(g);
  
		ImageObserver paintingChild = null;
		g.drawImage(ManagerPanel.images[(int)ManagerPanel.universalPhase/2%3], 0, 0, paintingChild);
		g.drawImage(ManagerPanel.images[85], 0, 0, paintingChild);
		//Farmland below
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 64, 128, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 192, 128, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 320, 128, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 576, 128, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 704, 128, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 832, 128, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 576, 384, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 832, 384, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 704, 384, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 64, 384, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 192, 384, paintingChild);
		g.drawImage(ManagerPanel.images[25+(int)ManagerPanel.universalPhase/2%4], 320, 384, paintingChild);
		
		snake.paint(g);
		rat.paint(g);
		farmer.paint(g);
		pig.paint(g);
		
		g.drawImage(ManagerPanel.images[3], 0, 340, paintingChild);
		int hp = (status > 199)?2:status/100;
		g.drawImage(ManagerPanel.images[91+(int)ManagerPanel.universalPhase/5%2+hp*2], 0, 500, paintingChild);
		
		cursor.paint(g);
		
		
	}
	public void keyPressed(KeyEvent e) {
		farmer.press(e);
		
	}

	public void keyReleased(KeyEvent e) {
		farmer.release(e);
		
	}

	public void mouseClicked(MouseEvent e) {
		cursor.click(e);
		
	}

}
