package app;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class Paddle extends Sprite {
	
	private static int padrao = 70;
	private Cenario nivel;
	
	public Paddle() {
		super(padrao,6, Color.YELLOW);
	}
	
	public void nivel(){
		if (nivel.getNivel()==2){
			padrao = 60;
		}
		if (nivel.getNivel()==3){
			padrao = 50;
		}
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