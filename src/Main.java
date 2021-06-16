import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.net.URL;

import javax.sound.sampled.*;
/**Its the main method 
 * 
 * @author Reilly
 *
 */
public class Main{

	public static JFrame frame = new JFrame("RoseField Farms");
	public static Clip clip;
	
    public static void main(String[] args) throws Throwable, Throwable {
    	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ManagerPanel panel = new ManagerPanel(); //makes the JPanel that runs the game (I couldn't get cards to work with JFrame)
        frame.add(panel, BorderLayout.CENTER);
		
		try { //loops the song
			URL url= Main.class.getResource("combine harvester.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        clip.setLoopPoints(0, -1);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
        frame.setSize(1040, 678);
        frame.setVisible(true);
    }
}//