import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
public class Farmer {
	public static int x;
	public static int y;
	private double speed;
	private double acc;
	private int dirX;
	private int dirY;
	private int facing;
	public static int hitX;
	private int cooldown;
	public static ArrayList<Rose> roses;
	public enum State {
		STAND,
		MOVE,
		PLANT,
		SWEEP,
	};
	public static State state;
	public Farmer()
	{
		x = 480;
		y = 230;
		state = State.STAND;
		speed = 0;
		acc = 0;
		facing = 1;
		cooldown = 5;
		hitX = x+64;
		roses = new ArrayList<Rose>();
	}
	public void paint(Graphics g)
	{
		ImageObserver paintingChild = null;
		switch (state) {
		case STAND:	
			g.drawImage(ManagerPanel.images[19], x, y, x+64, y+128, 32-facing*32, 0, 32+facing*32, 128, paintingChild);
			break;
		case MOVE:
			g.drawImage(ManagerPanel.images[16+ManagerPanel.universalPhase/4%5], x, y, x+64, y+128, 32-facing*32, 0, 32+facing*32, 128, paintingChild);
			break;
		case PLANT:
			g.drawImage(ManagerPanel.images[11+ManagerPanel.universalPhase/4%5], x, y, x+64, y+128,32-facing*32, 0, 32+facing*32, 128, paintingChild);
			break;
		case SWEEP:
			g.drawImage(ManagerPanel.images[21+ManagerPanel.universalPhase/4%4], x, y, x+64, y+128, 32-facing*32, 0, 32+facing*32, 128, paintingChild);
			break;
		}
		for (int i = 0; i < roses.size();i++)
		{
			roses.get(i).paint(g);
		}
	}
	public void step()
	{
		cooldown -= (cooldown>0)?1:0;
		for (int i = 0; i < roses.size();i++)
		{
			roses.get(i).step();
			if (roses.get(i).stage==-1)
			{
				roses.set(i, null);
				System.gc();
				roses.remove(i);
				i--;
				RenderPanel.status-=10;
			}
		}
		switch (state)
		{
		case MOVE:
			if (speed < 7+Math.sqrt(ManagerPanel.universalPhase/1000))
			{
				acc = 1.6;
			}
			if (speed > 7+Math.sqrt(ManagerPanel.universalPhase/1000))
			{
				acc = 0;
				speed = 7+Math.sqrt(ManagerPanel.universalPhase/1000);
			}
			if (dirX == 0 && dirY == 0)
			{
				state = State.STAND;
			}
			break;
		case SWEEP:
			if (speed > 0)
			{
				acc = -1*Math.random()/5;
			}
			if (speed > 7)
			{
				acc = -2;
			}
			if (speed < 0)
			{
				acc = 0;
				speed = 0;
			}
			break;
		default:
			if (speed > 0)
			{
				acc = -1.3;
			}
			if (speed < 0)
			{
				acc = 0;
				speed = 0;
			}
			break;
		}
		speed += acc;
		if (x < 10)
		{
			x = 11;
			dirX *= 0;
		}
		if (x > 980)
		{
			x = 979;
			dirX *= 0;
		}
		if (y < 10)
		{
			y = 11;
			dirY *= 0;
		}
		if (y > 470)
		{
			y = 469;
			dirY *= 0;
		}
		x += (int)speed*dirX;
		y += (int)speed*dirY;
		hitX = (facing==1)?x+60:x;
	}
	public void press(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			state = State.MOVE;
			dirX = 1;
			facing = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			state = State.MOVE;
			dirX = -1;
			facing = -1;
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			state = State.MOVE;
			dirY = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			state = State.MOVE;
			dirY = -1;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			state = State.PLANT;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			state = State.SWEEP;
			speed += 3*Math.random();
		}
	}
	public void release(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			dirX = (dirX == 1)?0:dirX;
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			dirX = (dirX == -1)?0:dirX;
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			dirY = (dirY == 1)?0:dirY;
		}
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
		{
			dirY = (dirY == -1)?0:dirY;
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT&&(x<448||x>576)&&(y>280||y<130)&&cooldown==0)
		{
			Rose rose = new Rose(hitX,y+128);
			roses.add(rose);
			cooldown = 40;
		}
	}
}
