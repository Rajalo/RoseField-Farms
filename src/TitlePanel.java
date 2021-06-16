import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class TitlePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TitlePanel()
	{
		setBackground(Color.BLUE);
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
		
		g.drawImage(ManagerPanel.images[3], 0, 340, paintingChild);
		
		g.drawImage(ManagerPanel.images[82+(int)ManagerPanel.universalPhase/2%3], 0, 0, paintingChild);
		
    }
	public void step() {
		repaint();
	}


	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			ManagerPanel.nextState = true;
		}
		
	}

}
