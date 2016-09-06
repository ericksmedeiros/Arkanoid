package app;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	
	public Paddle() {
		super(200,3, Color.BLACK);
	}

	public boolean bateu(Ball ball){
		
	Point pos = ball.getPosition();
	int raio = ball.getRaio();
	
	Rect rect = getBounds();
	int top = rect.y;
	
	if(pos.y+raio < top) {
		return false;
		}
	return false;
	}
}