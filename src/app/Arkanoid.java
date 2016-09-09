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
	private Image back;
	//private Image score;
	private int deltaY = 1;
	private int deltaX = 1;

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		canvas.drawImage(back, 0, 0);
		canvas.drawImage(imagem,10,10);
		ball.draw(canvas);
		paddle.draw(canvas);
		bloco.draw(canvas);	
		//canvas.putText(30, 40, 40, null);//SCORE

	}

	@Override
	protected void setup() {
		this.setResolution(Resolution.MIDRES);
		this.setFramesPerSecond(30);
		
		try {
			back = new Image("image/fundo.jpeg");
			imagem = new Image("image/fase1.png");
			
			//score = new Image("Score");//SCORE
			
			ball = new Ball();
			ball.setPosition(130,180);
			
			paddle = new Paddle();
			paddle.setPosition(100,240);

			bloco = new Bloco(Color.GREEN);
			bloco.setPosition(20,20);
		
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

		bindKeyPressed("LEFT", new KeyboardAction() {
			Point posPaddle = paddle.getPosition();
			@Override
			public void handleEvent() {
				if (testeLimite(posPaddle.x,0,260)){
					paddle.move(3, 0);
				}else paddle.move(-3, 0);
			}
		});
		bindKeyPressed("RIGHT", new KeyboardAction() {
			@Override
			public void handleEvent() {
				paddle.move(3, 0);
			}		});
	}

	@Override
	protected void loop() {
		//Testando os limites do eixo X e Y.
		Point pos = ball.getPosition();
		
		if (testeLimite(pos.y,15,260)) {
			deltaY *= -1;
		}
		if (testeLimite(pos.x,15,260)) {
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
