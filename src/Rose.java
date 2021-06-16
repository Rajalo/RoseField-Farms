import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Rose {
	
	public int stage;
	private int x,y,growth,up;
	
	public Rose(int x, int y)
	{
		this.x = x;
		this.y = y;
		growth = 0;
	}
	
	public void paint(Graphics g)
	{
		ImageObserver paintingChild = null;
		if (stage<5) {
			g.drawImage(ManagerPanel.images[67+ManagerPanel.universalPhase/2%3+3*stage], x-32, y-128, paintingChild);
			up =0;
		}
		else if (stage == 5)
		{
			g.drawImage(ManagerPanel.images[51+up/2], x-32, y-128, paintingChild);	
			up++;
		}
		else
		{
			g.drawImage(ManagerPanel.images[47+ManagerPanel.universalPhase/2%4], x-32, y-128, paintingChild);
			up = 0;
		}
	}
	public void step()
	{
		double growthrate = Math.abs(Farmer.x-x)/100.0 + Math.abs(Farmer.y-y)/100.0+ Math.sqrt(ManagerPanel.universalPhase/10);
		growth += growthrate;
		if (up >=14)
		{
			stage++;
		}
		if (Math.abs(Snake.y-y)+Math.abs(Snake.x-x)<100||Math.abs(Rat.y-y)+Math.abs(Rat.x+-x)<100||Math.abs(Pig.y-y)+Math.abs(Pig.x+-x)<100)
		{
			if (stage >4)
			{
				stage = 4;
			}
			else
			{
				if (growth>=300)
				{
					stage-=2;
					RenderPanel.status-=5;
				}
			}
		}
		else if (Math.abs(Cursor.targetY-y)<50 && Math.abs(Cursor.targetX-x)<40)
		{
			stage = -1;
			RenderPanel.status += 55;
			ManagerPanel.score++;
		}
		
		if (growth >= 300)
		{
			growth = 0;
			if(stage <5)
			{
				stage++;
			}
		}
		if (stage >5)
		{
			double angle = Math.abs(Math.atan((Cursor.targetY+16-y)/(Cursor.targetX+32-x+.05)));
			x += ((Cursor.targetX+32-x<0)?-9:9)*Math.cos(angle);
			y += ((Cursor.targetY+16-y<0)?-9:9)*Math.sin(angle);
		}
		
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
}
