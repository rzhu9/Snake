import java.util.*;
import java.awt.*;

/**
 * The class represents a snake updated at one time, and its properties including the 
 * food, each element of the body and all the methods of the snake move.
 * @author Jerry
 *
 */
public class Snake {
	private final Game game;
	private ArrayList<Point> snakelist = new ArrayList<Point>();
	private String[] Dlist = { "EAST", "SOUTH", "WEST", "NORTH" };
	private Color[] Clist = { Color.BLUE, Color.RED, Color.WHITE, Color.GREEN, Color.PINK, Color.YELLOW, Color.ORANGE };
	private String Velocity;/* EAST,WEST,NORTH,SOUTH,ZERO */
	private Point head;
	private Food food;
	private final Color snakeColor;
	private ArrayList<Point> availablePoints;
	int length;

		
		public Snake(Game game) {
		Velocity = Dlist[(int) (Math.random() * 4)];
		availablePoints = new ArrayList<Point>();
		length = 3;
		snakeColor = randomColor();
		this.game = game;
		snakelist.add(new Point(game.getWidth() / 2, game.getHeight() / 2));
		switch (Velocity) {
		case "EAST":
			snakelist.add(new Point(game.getWidth() / 2 - 10, game.getHeight() / 2));
			snakelist.add(new Point(game.getWidth() / 2 - 20, game.getHeight() / 2));
			break;
		case "SOUTH":
			snakelist.add(new Point(game.getWidth() / 2, game.getHeight() / 2 - 10));
			snakelist.add(new Point(game.getWidth() / 2, game.getHeight() / 2 - 20));
			break;
		case "WEST":
			snakelist.add(new Point(game.getWidth() / 2 + 10, game.getHeight() / 2));
			snakelist.add(new Point(game.getWidth() / 2 + 20, game.getHeight() / 2));
			break;
		case "NORTH":
			snakelist.add(new Point(game.getWidth() / 2, game.getHeight() / 2 + 10));
			snakelist.add(new Point(game.getWidth() / 2, game.getHeight() / 2 + 20));
			break;

		default:
			break;
		}
		for (int i = 0; i < game.getHeight() * game.getWidth() / 100; i += 1) {
			if ((i % (game.getWidth() / 10) == game.getWidth() / 10 - 1)
					|| (i / (game.getWidth() / 10) == game.getHeight() - 1)){
				
			}else{
				availablePoints.add(new Point(i % (game.getWidth() / 10) * 10, i / (game.getWidth() / 10) * 10));
			}
		}
		availablePoints.removeAll(snakelist);
		food = new Food();
		head = snakelist.get(0);
		Velocity = "ZERO";
	}

	public void setVelocity(String v) {
		Velocity = v;
	}

	public String getVelocity() {
		return Velocity;
	}

	public void updatepos() {
		if (Velocity == "ZERO") {
			return;
		}
		if (!eatFood()) {

			Point last = snakelist.remove(snakelist.size() - 1);
			availablePoints.add(last);
			append(Velocity);
		} else {
			game.getCore().incScore();
			length++;
			append(Velocity);
			food = new Food();
		}

		if (isDead()) {
			game.getCore().Finished();
		}

	}

	private void append(String v) {

		switch (Velocity) {
		case "EAST":
			head = new Point((int) head.getX() + 10, (int) (head.getY()));
			snakelist.add(0, head);
			availablePoints.remove(head);
			break;
		case "WEST":
			head = new Point((int) (head.getX() - 10), (int) (head.getY()));
			snakelist.add(0, head);
			availablePoints.remove(head);
			break;
		case "SOUTH":
			head = new Point((int) (head.getX()), (int) (head.getY() + 10));
			snakelist.add(0, head);
			availablePoints.remove(head);
			break;
		case "NORTH":
			head = new Point((int) (head.getX()), (int) (head.getY() - 10));
			snakelist.add(0, head);
			availablePoints.remove(head);
			break;

		default:
			break;
		}

	}
	//return if the coordinate of the head of the snake is 
	//equal to the coordinate of the food
	private boolean eatFood() {
		return head.equals(food.getLoc());
	}

	//return true if the snake touch itself or the wall
	private boolean isDead() {
		if (head.getX() < 0 || head.getX() > game.getWidth() - 10 || head.getY() < 0
				|| head.getY() > game.getHeight() - 10) {
			return true;
		}
		for (int i = 1; i < snakelist.size() - 1; i++) {
			Point curr = snakelist.get(i);
			if (head.getX() == curr.getX() && head.getY() == curr.getY()) {
				return true;
			}
		}
		return false;
	}

	//random select a color for the food from the color list
	private Color randomColor() {
		return Clist[(int) (Math.random() * 7)];
	}

	public void drawsnake(Graphics g1) {
		Iterator<Point> itr = snakelist.iterator();
		Point curr = null;
		Graphics2D g = (Graphics2D) g1;
		g.setStroke(new BasicStroke(2.0f));
		g.setPaint(Color.BLACK);

		while (itr.hasNext()) {
			curr = itr.next();
			g.setColor(Color.BLACK);
			g.fillRect((int) curr.getX(), (int) curr.getY(), 10, 10);
			g.setColor(snakeColor);
			g.fillRect((int) (curr.getX() + 1), (int) (curr.getY() + 1), 8, 8);
		}
		g.setColor(Color.WHITE);
		g.drawString("Your Score: " + game.getCore().getScore(), game.getWidth() / 2 - 50, 20);

	}

	public Food getFood() {
		return food;
	}

	class Food {
		private Point foodloc;
		private Color foodcol;

		public Food() {
			foodloc = newLoc();
			foodcol = randomColor();
			while (foodcol == snakeColor) {
				foodcol = randomColor();
			}
		}

		public Point newLoc() {
			int size = availablePoints.size();
			//Point p =availablePoints.get((int) (Math.random() * size));
			//System.out.println(p.getX()+" "+p.getY());
			return availablePoints.get((int) (Math.random() * size));
		}

		public Point getLoc() {
			return foodloc;
		}

		public void drawfood(Graphics g) {
			// TODO Auto-generated method stub
			g.setColor(foodcol);
			g.fillRect((int) foodloc.getX(), (int) foodloc.getY(), 10, 10);

		}

	}

}
