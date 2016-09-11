package app;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	
	public Paddle() {
		super(70,6, Color.YELLOW);
	}
	
	public int getRaioPaddle() {
		return getWidth()/2;
	}
	
	@Override
	public Point getPosition() {
		Point posPaddle = super.getPosition();
		int raio = getRaioPaddle();
		return new Point(posPaddle.x + raio, posPaddle.y + raio);
	}	
	
	public boolean bateu(Ball ball){
	
	Point pos = ball.getPosition();
	int raio = ball.getRaio();
	
	Rect rect = getBounds();
	int top = rect.y;
	int bottom = rect.y + rect.height;
	int left = rect.x;
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
	if(pos.y-raio > bottom) {
		return false;
	}
	
	return true;
	}
	
}