import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

public class Cursor {
	public int x,y;
	public static int targetX,targetY;
	
	public Cursor()
	{
		x = 480;
		y = 10;
		targetX = x;
		targetY = y;
	}
	public void step()
	{
		if (x != targetX)
		{
			x = targetX;
		}
		if (y != targetY)
		{
			y = targetY; 
		}
	}
	public void paint(Graphics g)
	{
		ImageObserver paintingChild = null;
		g.drawImage(ManagerPanel.images[4+(int)ManagerPanel.universalPhase/3%3], x, y, paintingChild);
	}
	public void click(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		int aboveDiagonalPositive = clickY - clickX + 200;
		int aboveDiagonalNegative = clickY - 878 + clickX;
		if (aboveDiagonalPositive > 0 && aboveDiagonalNegative < 0)
		{
			targetX = 10;
			targetY = 290;
		}
		else if (aboveDiagonalPositive < 0 && aboveDiagonalNegative > 0)
		{
			targetX = 950;
			targetY = 290;
		}
		else if (aboveDiagonalPositive > 0 && aboveDiagonalNegative > 0)
		{
			targetX = 480;
			targetY = 530;
		}
		else 
		{
			targetX = 480;
			targetY = 10;
		}
	}
}
