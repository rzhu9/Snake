import java.util.Date;

/**
 * A small class stores the result of each game.
 * @author Jerry
 *
 */
public class Result {
	private int score;
	private String player;
	private Date time;

	public Result(int score, String player, Date time) {
		this.score = score;
		this.player = player;
		this.time = time;
	}

	public int getscore() {
		return score;
	}

	public String getplayer() {
		return player;
	}

	public Date gettime() {
		return time;
	}

	public String toString() {
		return score +"\n"+ "Player: "+player +" Time: "+ time.toString();
	}
}
