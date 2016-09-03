package app;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	private int deltaY = 1;
	
	public Paddle() {
		super(200,3, Color.BLACK);
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
			deltaY *= -1;		}
		if(pos.x+raio < left) {
			deltaY *= -1;		}
		if(pos.y+raio < top) {
			deltaY *= -1;		}
		if(pos.y-raio > bottom) {
			deltaY *= -1;		}
		
		return true;
	}
}
