package app;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {
	
	
	private Bloco bloco;
	private Paddle paddle;
	private Ball ball;
	private int deltaY = 1;
	private int deltaX = 1;

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		ball.draw(canvas);
		paddle.draw(canvas);
		bloco.draw(canvas);
	}

	@Override
	protected void setup() {
		this.setFramesPerSecond(60);
		this.setResolution(Resolution.MSX);
		
		ball = new Ball();
		ball.setPosition(130,180);
		
		paddle = new Paddle();
		paddle.setPosition(100,185);

		bloco = new Bloco(Color.RED);
		bloco.setPosition(20,20);
		
		bindKeyPressed("LEFT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddle.move(-3, 0);
			}
		});
		bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddle.move(3, 0);
			}
		});
	}

	@Override
	protected void loop() {
		//Testando os limites do eixo X e Y.
		Point pos = ball.getPosition();
		Point posPaddle = paddle.getPosition();
		
		if (testeLimite(pos.y,0,getResolution().height)) {
			deltaY *= -1;
		}
		if (testeLimite(pos.x,0,getResolution().width)) {
			deltaX *= -1;
		}
		if (testePaddle(posPaddle,pos)){
			deltaY *= -1;
		}
			
		ball.move(deltaX, deltaY);
		
		if (bloco.bateu(ball)) {
			Console.println("Bateu no Bloco");
		}
		if (paddle.bateu(ball)){
			Console.println("PADDLE");
		}
			
			
		redraw();	
		
		
	}


	private boolean testePaddle(Point posPaddle, Point pos) {
		if(posPaddle == pos) {
			return true;
		} else {
			return false;
		}
	}

	private boolean testeLimite(double pos, int min, int max) {
		if(pos > max || pos < min) {
			return true;
		} else {
			return false;
		}
	}
	
}
