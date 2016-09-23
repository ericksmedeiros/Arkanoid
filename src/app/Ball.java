package app;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {
	
	private boolean alive = true;

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
	//	Rect rect = getBounds();
	//	int bottom = rect.y + rect.height;
		
		if (pos.y - r == 255){
			alive = false;
			return null;
		}
		else return new Point(pos.x + r, pos.y + r);
	}
	/*
	public boolean ballMorreu(Ball ball) {
		Point pos = super.getPosition();
		int r = getRaio();
		Rect rect = getBounds();
		int bottom = rect.y + rect.height;
		
		if (pos.y - r >= bottom){
			return(0,0);
		}
	}*/
	@Override
	public void draw(Canvas canvas) {
		if (alive = true)
			super.draw(canvas);
	}
}
