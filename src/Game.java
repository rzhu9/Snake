import javax.swing.*;
import javax.swing.Timer;import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class manipulates the whole game and all its components.
 * @author Jerry
 *
 */
public class Game {
	private final JFrame GameWindow = new JFrame();
	private int Width;
	private int Height;
	private int frequency;
	private String gamestatus;
	/*
	 * The status of the game includes NEW, RUNNING, PAUSED, FINISHED, EXIT
	 */
	private GameMenu gamemenu;
	private GameCore gamecore;

	public Game() {
		frequency = 80;
		setEverything(500,500);
		JOptionPane.showMessageDialog(GameWindow,
				"Welcome to the Greedy Snake game!" + "\n" + "Press any arrow key to start" + "\n" + "Good Luck!",
				"Welcome Window", JOptionPane.INFORMATION_MESSAGE);

	}
	public void setEverything(int Width, int Height){
		gamestatus = "NEW";
		this.Width = Width;
		this.Height = Height;
		gamecore = new GameCore(frequency);
		gamemenu = new GameMenu();
		gamecore.setSize(Width, Height);
		GameWindow.setTitle("Greedy Snake");
		GameWindow.setLayout(new FlowLayout());
		GameWindow.setContentPane(gamecore);
		GameWindow.setJMenuBar(gamemenu);
		GameWindow.setSize(Width, Height);
		GameWindow.setFocusable(true);
		GameWindow.setResizable(true);
		GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameWindow.setVisible(true);
		GameWindow.addKeyListener(gamecore);
	}

	public String getStatus() {
		return gamestatus;
	}

	public GameCore getCore() {
		return gamecore;
	}

	public int getWidth() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}

	private void Exit() {
		gamestatus = "EXIT";
		GameWindow.removeKeyListener(gamecore);
		GameWindow.dispose();
		System.exit(0);
	}

	/**
	 * The list of the menus used in the game.
	 * @author Jerry
	 *
	 */
	class GameMenu extends JMenuBar implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final JMenu M_MENU, M_CUSTOMIZE, M_PLAYER, M_ABOUT;
		private final JMenu M_SIZE, M_DIFFICULTY;
		private final JMenuItem M_NEW, M_ROOKIE, M_NORMAL, M_MASTER, M_EXIT, M_SMALL, M_MEDIUM, M_LARGE, M_AUTHOR,
				M_GAMEHELP, M_HISTORY, M_BESTSCORE;

		public GameMenu() {
			// Main menu
			M_MENU = new JMenu("Menu");
			M_CUSTOMIZE = new JMenu("Customize");
			M_PLAYER = new JMenu("Player");
			M_ABOUT = new JMenu("About");
			// Submenu

			M_SIZE = new JMenu("Window Size");
			M_DIFFICULTY = new JMenu("Difficulty");

			// Menu item
			M_NEW = new JMenuItem("New Game");
			M_ROOKIE = new JMenuItem("Rookie");
			M_NORMAL = new JMenuItem("Normal");
			M_MASTER = new JMenuItem("Master");
			M_EXIT = new JMenuItem("Exit");
			M_SMALL = new JMenuItem("300 * 300");
			M_MEDIUM = new JMenuItem("500 * 500");
			M_LARGE = new JMenuItem("700 * 700");
			M_GAMEHELP = new JMenuItem("Game tutorial");
			M_HISTORY = new JMenuItem("History");
			M_BESTSCORE = new JMenuItem("BestScore");
			M_AUTHOR = new JMenuItem("Author");

			M_NEW.addActionListener(this);
			M_EXIT.addActionListener(this);
			M_ROOKIE.addActionListener(this);
			M_NORMAL.addActionListener(this);
			M_MASTER.addActionListener(this);
			M_SIZE.addActionListener(this);
			M_SMALL.addActionListener(this);
			M_MEDIUM.addActionListener(this);
			M_LARGE.addActionListener(this);
			M_HISTORY.addActionListener(this);
			M_BESTSCORE.addActionListener(this);
			M_AUTHOR.addActionListener(this);
			M_GAMEHELP.addActionListener(this);

			add(M_MENU);
			add(M_CUSTOMIZE);
			add(M_PLAYER);
			add(M_ABOUT);
			M_MENU.add(M_NEW);
			M_MENU.add(M_DIFFICULTY);
			M_MENU.add(M_EXIT);
			M_CUSTOMIZE.add(M_SIZE);
			M_SIZE.add(M_SMALL);
			M_SIZE.add(M_MEDIUM);
			M_SIZE.add(M_LARGE);
			M_DIFFICULTY.add(M_ROOKIE);
			M_DIFFICULTY.add(M_NORMAL);
			M_DIFFICULTY.add(M_MASTER);
			M_PLAYER.add(M_BESTSCORE);
			M_PLAYER.add(M_HISTORY);
			M_ABOUT.add(M_AUTHOR);
			M_ABOUT.add(M_GAMEHELP);
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(M_NEW)) {
				JOptionPane.showMessageDialog(GameWindow,
						"Welcome to the Greedy Snake game!" + "\n" + "Press any arrow key to start" + "\n" + "Good Luck!",
						"Welcome Window", JOptionPane.INFORMATION_MESSAGE);
				gamecore.Restart();
			}
			if (e.getSource().equals(M_EXIT)) {
				Exit();
			}
			if(e.getSource().equals(M_SMALL)){
				if(gamecore!=null){
					GameWindow.removeKeyListener(gamecore);
					GameWindow.dispose();
				}
				setEverything(300,300);
			}
			if(e.getSource().equals(M_MEDIUM)){
				if(gamecore!=null){
					GameWindow.removeKeyListener(gamecore);
					GameWindow.dispose();
				}
				setEverything(400,400);
			}
			if(e.getSource().equals(M_LARGE)){
				if(gamecore!=null){
					GameWindow.removeKeyListener(gamecore);
					GameWindow.dispose();
				}
				setEverything(500,500);
			}

			if (e.getSource().equals(M_ROOKIE)) {
				gamecore.getTimer().setDelay(80);
			}
			if (e.getSource().equals(M_NORMAL)) {
				gamecore.getTimer().setDelay(500);
			}
			if (e.getSource().equals(M_MASTER)) {
				gamecore.getTimer().setDelay(20);
			}
			if (e.getSource().equals(M_HISTORY)) {
				String scores = "";
				ArrayList<Result> scorelist = gamecore.getscoreHistory();
				for (int i = 0; i < Math.min(11, scorelist.size()); i++) {
					scores += (i + 1) + " Score: " + scorelist.get(i).toString() + "\n";
				}
				JOptionPane.showMessageDialog(GameWindow, scores, "Game Histoy", JOptionPane.INFORMATION_MESSAGE);
			}

			if (e.getSource().equals(M_BESTSCORE)) {
				String bestscore = gamecore.getBestScore().toString();
				JOptionPane.showMessageDialog(GameWindow, "The best Score: " + bestscore, "Score Leader",
						JOptionPane.INFORMATION_MESSAGE);
			}

			if (e.getSource().equals(M_AUTHOR)) {
				JOptionPane.showMessageDialog(GameWindow,
						"By Jieru Hu(Jerry)" + "\n" + "Student from University " + "of Wisconsin Madison" + "\n"
								+ "Double major in Mechanical Engineering and Computer Science" + "\n"
								+ "Hometown: Ningbo, China" + "\n" + "Email: jhu76@wisc.edu",
						"Author", JOptionPane.INFORMATION_MESSAGE);
			}

			if (e.getSource().equals(M_GAMEHELP)) {
				JOptionPane.showMessageDialog(GameWindow,
						"In the game, the user uses four arrows keys to control the motion of the snake. \n"
						+ "The aim of the game is to eat as much food as possible. The user could stop the \n"
						+ "game by pressing 'Space' and exit the game by pressing 'escape'.",
						"Game rules", JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	/**
	 * The core part panel of the game where the game is started, stopped, resumed, 
	 * finished and painted.
	 * @author Jerry
	 *
	 */
	class GameCore extends JPanel implements ActionListener, KeyListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Snake s;
		Timer time;
		private int currentScore;
		ArrayList<Result> scoreHistory;
		Result bestScore;

		public GameCore(int freq) {
			s = new Snake(Game.this);
			time = new Timer(frequency, this);
			currentScore = 0;
			scoreHistory = new ArrayList<Result>();
			bestScore = null;
			setSize(this.getWidth(), this.getHeight());
			setBackground(Color.BLACK);
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			time.start();
		}

		public Timer getTimer() {
			return time;
		}

		public int getScore() {
			return currentScore;
		}

		public void incScore() {
			currentScore++;
		}

		private ArrayList<Result> getscoreHistory() {
			return scoreHistory;
		}

		private void addScore(Result result) {
			scoreHistory.add(0, result);
		}

		private void setBestscore(Result newResult) {
			if (bestScore == null) {
				bestScore = newResult;
			}

			if (newResult.getscore() > bestScore.getscore()) {
				bestScore = newResult;
			}
		}

		private Result getBestScore() {
			return bestScore;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (gamestatus == "NEW") {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
						|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gamestatus = "RUNNING";
				}
			}

			if (gamestatus.equals("RUNNING")) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					if (s.getVelocity() == "EAST" || s.getVelocity() == "WEST" || s.getVelocity() == "ZERO") {
						s.setVelocity("NORTH");
					}
					break;

				case KeyEvent.VK_DOWN:
					if (s.getVelocity() == "EAST" || s.getVelocity() == "WEST" || s.getVelocity() == "ZERO") {
						s.setVelocity("SOUTH");
					}
					break;

				case KeyEvent.VK_LEFT:
					if (s.getVelocity() == "NORTH" || s.getVelocity() == "SOUTH" || s.getVelocity() == "ZERO") {
						s.setVelocity("WEST");
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (s.getVelocity() == "NORTH" || s.getVelocity() == "SOUTH" || s.getVelocity() == "ZERO") {
						s.setVelocity("EAST");
					}
					break;
				case KeyEvent.VK_ESCAPE:
					Exit();
					break;
				case KeyEvent.VK_SPACE:
					System.out.println("q");
					Pause();
					break;
				default:
					break;
				}
			} else if (gamestatus.equals("PAUSED") && e.getKeyCode() == KeyEvent.VK_SPACE) {
				Resume();
			} else if (gamestatus.equals("Finished")) {
				Finished();
			}

		}

		public void Finished() {
			gamestatus = "Finished";
			int score = gamecore.getScore();
			String output = "";

			if (gamecore.getBestScore() == null) {

				output = "Congratulations! You 've break the record!" + "\n" + "Your score is " + score;
			} else {
				if (score > gamecore.getBestScore().getscore()) {
					output = "Congratulations! You 've break the record!" + "\n" + "Your score is " + score+" !";
				} else {
					output = "Your score is " + score;
				}
			}
			String username = JOptionPane.showInputDialog(GameWindow,
					"\n" + output + "\n" + "Please enter your nickname:" + "\n", "Game Finished",
					JOptionPane.QUESTION_MESSAGE);
			Date date = new Date();
			Result r = new Result(score, username, date);
			gamecore.addScore(r);
			gamecore.setBestscore(r);
			time.stop();
		}

		public void Pause() {
			gamestatus = "PAUSED";
			time.stop();
		}

		public void Restart() {
			gamestatus = "NEW";
			currentScore = 0;
			s = new Snake(Game.this);
			time.restart();
			repaint();
		}

		private void Resume() {
			gamestatus = "RUNNING";
			time.restart();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		public void paintComponent(Graphics gg) {
			super.paintComponent(gg);
			Graphics2D g = (Graphics2D) gg;
			s.drawsnake(g);
			s.getFood().drawfood(g);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			s.updatepos();
			repaint();
		}

	}

}
