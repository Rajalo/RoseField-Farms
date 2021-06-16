import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
/**Manages which JPanel should be shown, such as the game or the beginning animation
 * 
 * @author Reilly
 *
 */
public class ManagerPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private int state = 0;
	public static boolean nextState= false;
	private TitlePanel titlePanel = new TitlePanel();
	private RenderPanel renderPanel;//stores the playable game
	public static int score;//keeps track of the score for use in the endscreen and game
	private CardLayout cardLayout;
	private EndPanel endPanel;
	private AnimationPanel animePanel;
	public static int universalPhase = 0;
	//This down here is magical, keeps all the strings with the file names
	public static String[] imagePaths = {"/background1.png","/background2.png","/background3.png","/bottomfence.png","/cursor1.png","/cursor2.png","/cursor3.png","/endscreen1.png","/endscreen2.png","/endscreen3.png","/endscreen4.png","/FarmerPlant1.png","/FarmerPlant2.png","/FarmerPlant3.png","/FarmerPlant4.png","/FarmerPlant5.png","/FarmerRun1.png","/FarmerRun2.png","/FarmerRun3.png","/FarmerRun4.png","/FarmerRun5.png","/FarmerShoo1.png","/FarmerShoo2.png","/FarmerShoo3.png","/FarmerShoo4.png","/farmland1.png","/farmland2.png","/farmland3.png","/farmland4.png","/PigDie1.png","/PigDie2.png","/PigDie3.png","/PigRun1.png","/PigRun2.png","/PigRun3.png","/RanEat1.png","/RanEat2.png","/RanEat3.png","/RatDie1.png","/RatDie2.png","/RatDie3.png","/RatRun1.png","/RatRun2.png","/RatRun3.png","/RoseDie1.png","/RoseDie2.png","/RoseDie3.png","/RoseRun1.png","/RoseRun2.png","/RoseRun3.png","/RoseRun4.png","/RoseUp1.png","/RoseUp2.png","/RoseUp3.png","/RoseUp4.png","/RoseUp5.png","/RoseUp6.png","/RoseUp7.png","/SnakeDie1.png","/SnakeDie2.png","/SnakeDie3.png","/SnakeEat1.png","/SnakeEat2.png","/SnakeEat3.png","/SnakeRun1.png","/SnakeRun2.png","/SnakeRun3.png","/Sprout0Stage1.png","/Sprout0Stage2.png","/Sprout0Stage3.png","/Sprout1Stage1.png","/Sprout1Stage2.png","/Sprout1Stage3.png","/Sprout2Stage1.png","/Sprout2Stage2.png","/Sprout2Stage3.png","/Sprout3Stage1.png","/Sprout3Stage2.png","/Sprout3Stage3.png","/Sprout4Stage1.png","/Sprout4Stage2.png","/Sprout4Stage3.png","/title1.png","/title2.png","/title3.png","/topfence.png","/tutorial1.png","/tutorial2.png","/tutorial3.png","/tutorial4.png","/tutorial5.png","/zhealthbad1.png","/zhealthbad2.png","/zhealthok1.png","/zhealthok2.png","/zhealthgood1.png","/zhealthgood2.png"};
	public static BufferedImage[] images = new BufferedImage[97];
	public ManagerPanel() throws IOException
	{
		this.setLayout(new CardLayout());
        this.add(titlePanel, "Title");
		cardLayout = (CardLayout) this.getLayout();
		cardLayout.show(this, "Title");
		addMouseListener(this);//sets up all the listeners
		setFocusable(true);
		addKeyListener(this);
		Timer timer = new Timer(1000/30, this);
        timer.start();
        for (int i =0; i < 97; i++)//loads all the images for use (this is what made it super fast)
        {
        	BufferedImage after = null;
    		try {
    			URL url= getClass().getResource(imagePaths[i]);
    			after = ImageIO.read(url);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		images[i] = after;
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (nextState) //switches panel
		{
			state = (state+1)%4;
			switch (state)
			{
				case 0:
					titlePanel = new TitlePanel();
					this.add(titlePanel, "Title");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "Title");
					break;
				case 1:
					animePanel = new AnimationPanel();
					this.add(animePanel, "Anime");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "Anime");
					titlePanel = null;
					System.gc();
					break;
				case 2:
					universalPhase = 0;
					renderPanel = new RenderPanel();
					this.add(renderPanel, "Game");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "Game");
					animePanel = null;
					titlePanel = null;
					endPanel = null;
					System.gc();
					score = 0;
					break;
				case 3:
					endPanel = new EndPanel();
					this.add(endPanel, "End");
					cardLayout = (CardLayout) this.getLayout();
					cardLayout.show(this, "End");
					break;
			}
			nextState = false;
		}
		else {
			switch (state)//steps through current panel
			{
				case 0:
					titlePanel.step();
					break;
				case 1:
					animePanel.step();
					break;
				case 2:
					renderPanel.step();
					break;
				case 3:
					endPanel.step();
					break;
			}
			universalPhase++;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) { //sends MouseEvent to the current screen
		if (state == 2)
		{
			renderPanel.mouseClicked(e);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (state == 2)//state 2 is the game, sends jump info
		{
			renderPanel.keyPressed(e);
		}
		if (state == 0)
		{
			titlePanel.keyPressed(e);
		}
		if (state == 3)
		{
			endPanel.keyPressed(e);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (state == 2)
		{
			renderPanel.keyReleased(e)  ;
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}