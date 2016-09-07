package app;

import java.io.IOException;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {
	

	private Bloco bloco;
	private Paddle paddle;
	private Ball ball;
	private Image imagem;
	private int deltaY = 1;
	private int deltaX = 1;

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		canvas.drawImage(imagem,0,0);
		ball.draw(canvas);
		paddle.draw(canvas);
		bloco.draw(canvas);	

	}

	@Override
	protected void setup() {
		this.setResolution(Resolution.HIGHRES);
		this.setFramesPerSecond(30);
		this.setResolution(Resolution.MSX);
		
		try {
			imagem = new Image("image/fase1.png");
			ball = new Ball();
			ball.setPosition(130,180);
			
			paddle = new Paddle();
			paddle.setPosition(100,185);

			bloco = new Bloco(Color.GREEN);
			bloco.setPosition(20,20);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		
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
		
		if (testeLimite(pos.y,0,getResolution().height)) {
			deltaY *= -1;
		}
		if (testeLimite(pos.x,0,getResolution().width)) {
			deltaX *= -1;
		}

		ball.move(deltaX, deltaY);
		
		if (bloco.bateu(ball)) {
			Console.println("Bateu no Bloco");
		}
		if (paddle.bateu(ball)){
			Console.println("PADDLE");
			deltaY *= -1;
		}
						
		redraw();	

	}

	private boolean testeLimite(double pos, int min, int max) {
		if(pos > max || pos < min) {
			return true;
		} else {
			return false;
		}
	}
	
}
