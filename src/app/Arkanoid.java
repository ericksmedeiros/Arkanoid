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
	
	private Bloco blocos[] = new Bloco[65];
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
		for (int i = 0; i < blocos.length; i++) {
			blocos[i].draw(canvas);
		}	
		//canvas.putText(30, 40, 40, null);//SCORE

	}

	@Override
	protected void setup() {
		this.setResolution(Resolution.MIDRES);
		this.setFramesPerSecond(20);
		
		try {
			back = new Image("image/fundo.jpeg");
			imagem = new Image("image/fase1.png");
			
			//score = new Image("Score");//SCORE
			
			ball = new Ball();
			ball.setPosition(130,180);
			
			paddle = new Paddle();
			paddle.setPosition(100,240);

			int indice = 0, positionBlocox = 11, positionBlocoy = 14;
			for (int i = 0; i <= 4; i++){
				for (int j = 0; j < 13; j++) {
					blocos[indice] = new Bloco(Color.LIGHTGRAY);
					blocos[indice].setPosition(positionBlocoy,positionBlocox);
					indice++;
					positionBlocoy = positionBlocoy + 19;
				}
				positionBlocoy = 14;
				positionBlocox = positionBlocox + 11;
			}		
		
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		
		teclado();
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
		
		teclado();
		testeLimitePaddle();

		ball.move(deltaX, deltaY);
		
		for (int i = 0; i < blocos.length; i++) {
			if (blocos[i].bateu(ball)) {
				Console.println("Bateu no Bloco");
				deltaY *= -1;
			}
		}
		
		if (paddle.bateu(ball)){
			Console.println("Bateu no Paddle");
			deltaY *= -1;
		}
						
		redraw();	

	}

	private void testeLimitePaddle() {
		Point posPaddle = paddle.getPosition();

		if (testeLimite(posPaddle.x,47,229)){
			bindKeyPressed("RIGHT", new KeyboardAction() {
				@Override
				public void handleEvent() {
					paddle.move(0, 0);
				}		
			});
			bindKeyPressed("LEFT", new KeyboardAction() {	
				@Override
				public void handleEvent() {
					 paddle.move(-3, 0);
				}
			});
		}
		
		if (testeLimite(posPaddle.x,47,239)){
			bindKeyPressed("LEFT", new KeyboardAction() {	
				@Override
				public void handleEvent() {
					 paddle.move(0, 0);
				}
			});
			bindKeyPressed("RIGHT", new KeyboardAction() {
				@Override
				public void handleEvent() {
					paddle.move(3, 0);
				}	
			});
		}
	}
	
	private void teclado() {
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

	private boolean testeLimite(double pos, int min, int max) {
		if(pos > max || pos < min) {
			return true;
		} else {
			return false;
		}
	}
	
}
