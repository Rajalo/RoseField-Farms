import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Snake {
	
	public static int x,y;
	public static int mode;
	private int cooldown = 0;
	private int speed;
	public Snake()
	{
		Random generator = new Random(System.currentTimeMillis());
		x = (generator.nextDouble()<0.5)?-1030:2200;
		y = (int)(100 + 500*generator.nextDouble());
		mode = 0;
		cooldown = 0;
		speed = 0;
	}
	public void paint(Graphics g)
	{
		ImageObserver paintingChild = null;
		if (mode == 1)
		{
			g.drawImage(ManagerPanel.images[61+ManagerPanel.universalPhase/3%3], x, y, paintingChild);
		}
		else
		{
			g.drawImage(ManagerPanel.images[64+ManagerPanel.universalPhase/3%3], x, y, paintingChild);
		}
	}
	
	public void step() 
	{
		speed = (int)(4 + Math.sqrt(ManagerPanel.universalPhase/1000));
		switch (mode) 
		{
		case 0:
			double angle = Math.abs(Math.atan((Cursor.targetY+16-y)/(Cursor.targetX+16-x+.05)));
			x += ((Cursor.targetX+32-x<0)?-1*speed:speed-1)*Math.cos(angle+Math.cos(ManagerPanel.universalPhase/10.0)/2);
			y += ((Cursor.targetY+32-y<0)?-speed:speed)*Math.sin(angle+Math.cos(ManagerPanel.universalPhase/10.0)/2);
			break;
		case 2:
			double angle2 = Math.abs(Math.atan((Farmer.y+128-y)/(Farmer.hitX-x+0.5)));
			x -= ((Farmer.hitX-x<0)?-2*speed:speed)*Math.cos(angle2+Math.cos(ManagerPanel.universalPhase/10.0)/2);
			y -= ((Farmer.y+128-y<0)?-2*speed:speed)*Math.sin(angle2+Math.cos(ManagerPanel.universalPhase/10.0)/2);
			cooldown--;
			break;
		}
		if (Math.abs(Cursor.targetX+16-x)+Math.abs(Cursor.targetY+16-y)<50)
		{
			mode = 1;
		}
		else if (Farmer.state==Farmer.State.SWEEP && Math.abs(Farmer.hitX-x-64)+Math.abs(Farmer.y+128-y-32)<100)
		{
			mode = 2;
			cooldown = 40;
		}
		else if (cooldown <= 0)
		{
			mode = 0;
			cooldown = 0;
		}
	}
	
}
