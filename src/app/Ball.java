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
	
	
	@Override
	public Point getPosition() {
		Point pos = super.getPosition();
		int raio = getRaio();
		return new Point(pos.x + raio, pos.y + raio);
	}

	public int getRaio() {
		return getWidth()/2;
	}
		
	public boolean morreu(Ball ball){
		if (!alive)
			return false;

		Point pos = ball.getPosition();
		int raio = ball.getRaio();
		
		Rect rect = getBounds();
		int bottom = rect.x + rect.width;
		int top = rect.y;
		/*		int left = rect.x;
		int right = rect.x + rect.width;
		
		
		if (pos.x-raio > right) {
			return false;
		}
		if(pos.x+raio < left) {
			return false;
		}
		if(pos.y+raio < top) {
			return false;
		}
		if(pos.y > bottom) {
			return false;
		}
		if(pos.y+raio < top) {
			return true;
		}*/
		
		//alive = false;
		return true;
	}

	@Override
	public void draw(Canvas canvas){
		if (alive)
			super.draw(canvas);
	}

	
	
	
	
	
	
	/*	
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
			return new Point(0,0);
		}
		return new Point(pos.x + r, pos.y + r);
	}
	
	public boolean ballMorreu(Ball ball) {
		return alive = false;
	}
	@Override
	public void draw(Canvas canvas) {
		if (alive = true)
			super.draw(canvas);
	}*/
}
