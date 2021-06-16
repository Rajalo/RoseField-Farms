import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Random;

public class Rat {

	public static int x,y;
	public static int mode;
	private int cooldown;
	private double speed;
	private int facing;
	public Rat()
	{
		Random generator = new Random(System.currentTimeMillis());
		x = (generator.nextDouble()<0.5)?2200:-1030;
		y = (int)(100 + 500*generator.nextDouble());
		mode = 0;
		cooldown = 0;
		speed = 5;
		facing = 1;
	}
	public void paint(Graphics g)
	{
		ImageObserver paintingChild = null;
		if (mode == 1)
		{
			g.drawImage(ManagerPanel.images[35+ManagerPanel.universalPhase/3%3], x, y, x+128, y+64 , 64-64*facing, 0, 64+64*facing, 64, paintingChild);
		}
		else
		{
			g.drawImage(ManagerPanel.images[41+ManagerPanel.universalPhase/3%3], x, y, x+128, y+64 , 64-64*facing, 0, 64+64*facing, 64, paintingChild);
		}
	}
	public void step()
	{
		int targetX = (Farmer.roses.size()>0)?Farmer.roses.get(Farmer.roses.size()-1).getX()-32:Farmer.hitX;
		int targetY = (Farmer.roses.size()>0)?Farmer.roses.get(Farmer.roses.size()-1).getY()-32:Farmer.y;
		switch (mode) 
		{
		case 0:
			speed = ((Math.abs(Farmer.x - x)+Math.abs(Farmer.y-y))/300.0+2)%15+Math.sqrt(ManagerPanel.universalPhase/1000);
			double angle = Math.abs(Math.atan((targetY-y)/(targetX-x+.05)));
			x += (int)(((targetX-x<0)?-1*speed:speed)*Math.cos(angle));
			y += (int)(((targetY-y<0)?-1*speed:speed)*Math.sin(angle));
			facing = (targetX-x<0)?1:-1;
			break;
		case 2:
			speed = ((300.0/(Math.abs(Farmer.x - x)+Math.abs(Farmer.y-y)+10.5))+3)%15+Math.sqrt(ManagerPanel.universalPhase/1000);
			double angle1 = Math.abs(Math.atan((targetY-y)/(targetX-x+.05)));
			x -= (int)(((targetX-x<0)?-1*speed:speed)*Math.cos(angle1));
			y -= (int)(((targetY-y<0)?-1*speed:speed)*Math.sin(angle1));
			facing = (targetX-x<0)?1:-1;
			cooldown--;
			facing = (Farmer.hitX-x<0)?1:-1;
			break;
		case 1:
			x += (targetX-x)/2%10;
			y += (targetY-y)/2%10;
			break;
		}
		if (Farmer.state==Farmer.State.SWEEP && Math.abs(Farmer.hitX-x-64)<50&&Math.abs(Farmer.y+120-y-32)<70)
		{
			mode = 2;
			cooldown = 70;
		}
		else if (Math.abs(targetX-x)+Math.abs(targetY-y)<40&&mode==0&&Farmer.roses.size()>0)
		{
			mode = 1;
		}
		else if (cooldown <= 0)
		{
			mode = 0;
			cooldown = 0;
		}
	}
}
