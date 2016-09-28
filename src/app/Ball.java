package app;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {

	private int morte = 3;
	
	public Ball() {
		super(5,5,Color.BLUE);
	}
	

	public int getRaio() {
		return getWidth()/2;
	}

	@Override
	public Point getPosition() {
		Point pos = super.getPosition();
		int r = getRaio();

		if (pos.y - r == 255){
			setPosition(130,80);
			morte--;
			}
		
		return new Point(pos.x + r, pos.y + r);
	}


	public int getMorte() {
		return morte;
	}


	public void setMorte(int morte) {
		this.morte = morte;
	}
	
}
