import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int frameNum =0;
	public void step() {
		repaint();
		frameNum++; 
		if (frameNum > 120)
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
		
		g.drawImage(ManagerPanel.images[16+(int)ManagerPanel.universalPhase/4%5], 4*frameNum, 230, paintingChild);
		//Farmer
		g.drawImage(ManagerPanel.images[3], 0, 340, paintingChild);
		
		g.drawImage(ManagerPanel.images[86+(int)ManagerPanel.universalPhase/4%5], 500, 150, paintingChild);
		
		
	}

}
