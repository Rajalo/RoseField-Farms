import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class EndPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EndPanel()
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
		
		g.drawImage(ManagerPanel.images[7+(int)ManagerPanel.universalPhase/2%4], 300, 100, paintingChild);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.BOLD, 100));
		g.drawString(ManagerPanel.score+"", 450, 300);
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
