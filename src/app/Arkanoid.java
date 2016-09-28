package app;

import java.io.IOException;
import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Arkanoid extends GraphicApplication {
	
	private Bloco blocos[] = new Bloco[65];
	private Paddle paddle;
	private Ball ball;
	private Image imagem, back;
	private Image coracao1, coracao2, coracao3;
	private Cenario score, recorde1, recorde2, recorde3, nivel, vidas;
	private int indiceBlocos = 0;
	private float deltaX = 1, deltaY = 1;
	
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
	 	canvas.putText(270, 10, 20, "Nível: "+nivel.getNivel());
	 	canvas.putText(270, 40, 18, "Pontuação: "+score.getScore());
		canvas.putText(270, 70, 20, "Vidas");		
		canvas.putText(270, 140, 20, "Recordes");		
		canvas.putText(270, 170, 18, "1º Lugar: "+recorde1.getRecorde1());
		canvas.putText(270, 190, 18, "2º Lugar: "+recorde2.getRecorde2());
		canvas.putText(270, 210, 18, "3º Lugar: "+recorde3.getRecorde3());
		
		if (vidas.getVidas()>=1) {canvas.drawImage(coracao1, 270, 100);}
		if (vidas.getVidas()>=2) {canvas.drawImage(coracao2, 300, 100);}
		if (vidas.getVidas()==3) {canvas.drawImage(coracao3, 330, 100);}
	

	}
	
	@Override
	protected void setup() {
		this.setResolution(Resolution.MIDRES);
		this.setFramesPerSecond(60);
		this.setTitle("Arkanoid 5.0");

		try {
				vidas = new Cenario();
				nivel = new Cenario();
				score = new Cenario();
				back = new Image("image/fundo.jpg");
				imagem = new Image("image/fase.png");
				ball = new Ball();
				ball.setPosition(130,230);
				paddle = new Paddle();
				paddle.setPosition(100,240);
				recorde1 = new Cenario();
				recorde2 = new Cenario();
				recorde3 = new Cenario();

				coracao1 = new Image("image/coracao1.jpg");
				coracao2 = new Image("image/coracao2.jpg");
				coracao3 = new Image("image/coracao3.jpg");
				
				inserirBlocos();
					
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		teclado();
	}

	@Override
	protected void loop() {
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
				score.setScore(score.getScore()+5);
			}
		}
		
		if (paddle.bateu(ball)){
			Console.println("Bateu no Paddle");
			deltaY *= -1;
		}
		
		if(score.getScore()==325){
			nivel.setNivel(2);
			inserirBlocos();
		}
		if(score.getScore()==650){
			nivel.setNivel(3);
			inserirBlocos();
			}
		
		if(ball.getMorte() == 2){
			vidas.setVidas(2);
		}
		if(ball.getMorte() == 1){
			vidas.setVidas(1);
		}
		if(ball.getMorte() == 0){
			inserirRecordes();
			System.exit(0);;
		}		
		
		redraw();	

	}
		
	private void inserirRecordes() {

		if (score.getScore() >= recorde3.getRecorde3()){
			recorde3.setRecorde3(score.getScore());
		}
		else if (score.getScore() >= recorde2.getRecorde2()){
			recorde3.setRecorde3(recorde2.getRecorde2());
			recorde2.setRecorde2(score.getScore());
		}
		else if (score.getScore() >= recorde1.getRecorde1()){
			recorde3.setRecorde3(recorde2.getRecorde2());
			recorde2.setRecorde2(recorde1.getRecorde1());
			recorde1.setRecorde1(score.getScore());
		}
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
	
	private void inserirBlocos() {
		int positionBlocox = 11, positionBlocoy = 14;
		for (int i = 0; i <= 4; i++){
			for (int j = 0; j < 13; j++) {
				blocos[indiceBlocos] = new Bloco(coresBlocos());
				blocos[indiceBlocos].setPosition(positionBlocoy,positionBlocox);
				indiceBlocos++;
				positionBlocoy = positionBlocoy + 19;
			}
			positionBlocoy = 14;
			positionBlocox = positionBlocox + 11;
		}	
		
	}

	private Color coresBlocos() {
		if (indiceBlocos <= 12)
			return Color.BLACK;
		if (indiceBlocos <12 || indiceBlocos <=25)
			return Color.DARKGRAY;
		if (indiceBlocos <25 || indiceBlocos <=38)
			return Color.GRAY;
		if (indiceBlocos <36 || indiceBlocos <=51)
			return Color.LIGHTGRAY;
		else return Color.WHITE;
	}

}
