package engine;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import entity.Entity;
import entity.Position;
import asciiPanel.AsciiPanel;
import com.apple.eawt.Application;

/**
 * Ascii Genetics is the program that simulates natural selection
 * 
 * @author Kevin Park, Fisher Darling, Eric Lindau
 *
 */
public class Engine extends Thread {

	public static void main(String[] args) {
		if(System.getProperty("os.name").startsWith("Mac OS X")) {
			Image image = Toolkit.getDefaultToolkit().getImage(Engine.class.getResource("/Ascii.png"));
			Application application = Application.getApplication();
			application.setDockIconImage(image);
		}
		Engine asciiGenetics = new Engine(); // Initialize GUI and display
		asciiGenetics.start(); // Start a thread
	}
	
	private JFrame frame = new JFrame("Ascii Genetics"); //Frame
	private AsciiPanel panel; //Panel that displays ascii characters
	private Environment e = new Environment(); //Enviroment that contains entities
	private Entity[][] enviroChar = new Entity[39][86]; //Entities in the enviroment layout
	private final Color BACKGROUND_COLOR = Color.GRAY; //Default background color
	private final Color FOREGROUND_COLOR = Color.white; //Default character color
	//private final int fontWidth = 9; //Each character width in pixels
	//private final int fontHeight = 16; //Each character height in pixels
	private int highlightX = 0; //cursor column
	private int highlightY = 0; //cursor row
	private static boolean isRunning = true; //The game is running
	private static KeyListener listener; //Keyboard listener

	/**
	 * Constructor of the Engine that initializes and displays the GUI
	 */
	public Engine() {
		initListener(); //Start keyboard listener
		panel = new AsciiPanel(110, 41); //Ascii Panel 110x41
		//Create a display layout for Ascii Panel
		panel.write(
				"+--------------------------------------------------------------------------------------+|+------Credits------+",
				0, 0, Color.black, BACKGROUND_COLOR);
		byte loop = 1;
		for (int i = 1; i <= 39; i++) {
			panel.write("|", 0, i, Color.black, BACKGROUND_COLOR);
			panel.write("|", 109, i, Color.black, BACKGROUND_COLOR);
			if (i % 10 == 0) {
				loop++;
				if (loop == 2)
					panel.write("||+----A----+----B----+", 87, i, Color.black,
							BACKGROUND_COLOR);
				else if (loop == 3)
					panel.write("||+----C----+----D----+", 87, i, Color.black,
							BACKGROUND_COLOR);
				else if (loop == 4)
					panel.write("||+---------+---------+", 87, i, Color.black,
							BACKGROUND_COLOR);
			} else {
				if (i > 10 && i < 30) {
					panel.write("|", 99, i, Color.black, BACKGROUND_COLOR);
				} else
					panel.write("|                     ", 87, i, Color.black,
							BACKGROUND_COLOR);
			}
			panel.write("|||", 87, i, Color.black, BACKGROUND_COLOR);
		}
		panel.write(
				"+--------------------------------------------------------------------------------------+|+-------------------+",
				0, 40, Color.black, BACKGROUND_COLOR);

		panel.write("Kevin   Park", 92, 4, Color.white, BACKGROUND_COLOR);
		panel.write("Eric    Lindau", 92, 5, Color.white, BACKGROUND_COLOR);
		panel.write("Fisher  Darling", 92, 6, Color.white, BACKGROUND_COLOR);

		panel.write("  |\\_/|  ", 95, 33, Color.white, BACKGROUND_COLOR);
		panel.write(" / @ @ \\ ", 95, 34, Color.white, BACKGROUND_COLOR);
		panel.write("( > V < )", 95, 35, Color.white, BACKGROUND_COLOR);
		panel.write(" `>>x<<\' ", 95, 36, Color.white, BACKGROUND_COLOR);
		panel.write(" /  O  \\ ", 95, 37, Color.white, BACKGROUND_COLOR);
		//End of creating display layout
		
		frame.setBackground(BACKGROUND_COLOR); //Set the background color of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close the program if x button on the window is pressed
		frame.add(panel); //Add AsciiPanel to the frame
		frame.pack(); //set default frame size based on the containers
		frame.addKeyListener(listener); //Add keyboard listener to the frame
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Engine.class.getResource("/Ascii.png")));
		//frame.setSize((fontWidth * 112) - 3, (fontHeight * 43) + 2); //Set custom frame size
		frame.setFocusable(true);
		frame.setVisible(true); //Display the frame

		for (int i = 0; i < e.getSize(); i++) { 
			Entity t = e.getEntity(i); //Get the entities from the Environment
			Position tp = t.getPosition(); //Get position of that entity
			write(tp.getRow(), tp.getCol(), t); //Load the Entity to the array(enviroChar) for display
		}
	}

	/**
	 * Generates new generation of Entities
	 */
	public void newGeneration() {
		enviroChar = new Entity[39][86]; //Clear the environment Array
		e.processGen(); //Generate next generation in the environment
		for (int i = 0; i < e.getSize(); i++) { //Load the entities to the array (enviroChar)
			Entity t = e.getEntity(i);
			Position tp = t.getPosition();
			write(tp.getRow(), tp.getCol(), t);
		}
	}

	/**
	 * Method that creates new Thread which allows gui update in timely manner
	 */
	public void run() {
		int sleepCounter = 0; //Counter for alternating characters so it doesn't switch too fast
		while (isRunning) { //Loop it while this program is running
			for (int i = 0; i < enviroChar.length; i++) { //Go through the entities in the array
				for (int j = 0; j < enviroChar[i].length; j++) {
					if ((i == highlightY || i == highlightY + 1) //If this entity should be highlighted and it isn't null
							&& (j == highlightX || j == highlightX + 1)
							&& enviroChar[i][j] != null)
						panel.write(enviroChar[i][j].getGender(), j + 1, i + 1, //Print the entity with highlight
								enviroChar[i][j].getColor(), Color.yellow);
					else if (enviroChar[i][j] != null) //If the entity exist but not highlighted
						panel.write(enviroChar[i][j].getGender(), j + 1, i + 1,
								enviroChar[i][j].getColor(), BACKGROUND_COLOR);
					else if ((i == highlightY || i == highlightY + 1) //Highlighted but empty
							&& (j == highlightX || j == highlightX + 1))
						panel.write('-', j + 1, i + 1, FOREGROUND_COLOR,
								Color.yellow);
					else //Not highlighted and empty
						panel.write('-', j + 1, i + 1, FOREGROUND_COLOR,
								BACKGROUND_COLOR);
				}
			}
			int width = 90, height = 11; //Location of the AsciiPanel where it displays detailed part of the Entity
			for (int a = 0; a < 4; a++) { //A B C D
				int xinc = 0; //Col increment of the highlightX
				int yinc = 0; //Row increment of the highlightY
				switch (a) { //Increment properly according to position of the highlight
				case 1:
					xinc++;
					break;
				case 2:
					yinc++;
					break;
				case 3:
					xinc++;
					yinc++;
					break;
				}
				if (enviroChar[highlightY + yinc][highlightX + xinc] == null) { //If the entity inside the array is empty
					for (int b = 0; b < 9; b++) {
						for (int c = 0; c < 9; c++) {
							panel.write('-', width + c, height + b, //Display dashes to show emptiness
									FOREGROUND_COLOR, BACKGROUND_COLOR);
						}
					}
				} else { //If the entity is not empty
					if (sleepCounter == 25) { //Change alternating character every 250 milliseconds
						char[][] figure = enviroChar[highlightY + yinc][highlightX //Get detail characters of that entity
								+ xinc].getFigure();
						Color[][] figureColor = enviroChar[highlightY + yinc][highlightX //Get color of detail characters
								+ xinc].getFigureColor();
						for (int b = 0; b < 9; b++) { //Display the detail parts with colors
							for (int c = 0; c < 9; c++) {
								if (figureColor[b][c] == null) //Check for null character
									panel.write(figure[b][c], width + c, height
											+ b, FOREGROUND_COLOR,
											BACKGROUND_COLOR);
								else
									panel.write(figure[b][c], width + c, height
											+ b, figureColor[b][c],
											BACKGROUND_COLOR);
							}
						}
					}
				}
				if (width == 90) { //Calculate the x and y position of asciiPanel
					width = 100;
				} else {
					width = 90;
					height += 10;
				}
			}
			if (sleepCounter == 25) //Count and reset the sleepCounter
				sleepCounter = 0;
			else
				sleepCounter++;
			SwingUtilities.updateComponentTreeUI(frame);
			try {
				Thread.sleep(10); //Refresh the window every 10 milliseconds except detailed part of the window
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null,
						"Character Switcher Crashed.", "InterruptedException",
						JOptionPane.ERROR_MESSAGE);
				System.err.println();
			}
		}
		System.exit(0); //Exit the program if done running
	}

	/**
	 * Sets the eviroChar array in the correct position with the Entity
	 * 
	 * @param y
	 *            Rows
	 * @param x
	 *            Cols
	 * @param character
	 *            Entity
	 */
	public void write(int y, int x, Entity character) {
		enviroChar[y][x] = character; //set Entity in the correct position
	}

	/**
	 * Key listener that checks for keyboard input
	 */
	public void initListener() { //Keyboard listener
		listener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				parseInput(e); //Call this method if any of the keyboard is pressed
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// Not used
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Not used
			}
		};
	}

	/**
	 * Parses through all currently pressed keys and determines the desired
	 * effects
	 */
	public void parseInput(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37: //Left
			if (highlightX != 0)
				highlightX--;
			break;
		case 38: //Up
			if (highlightY != 0)
				highlightY--;
			break;
		case 39: //Right
			if (highlightX != 84)
				highlightX++;
			break;
		case 40: //Down
			if (highlightY != 37)
				highlightY++;
			break;
		case 32: //Space
			newGeneration();
			break;
		}
	}

}