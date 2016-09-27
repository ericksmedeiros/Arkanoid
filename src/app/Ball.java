package app;

import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Sprite;

public class Ball extends Sprite {

	private Cenario vidas;
	private Cenario nivel;
	static int padrao = 5;
	private int morte = 3;
	
	public Ball() {
		super(padrao,padrao,Color.BLUE);
	}
	
	public void nivel(){
		if (nivel.getNivel()==2){
			padrao = 4;
		}
		if (nivel.getNivel()==3){
			padrao = 3;
		}
	}

	public int getRaio() {
		return getWidth()/2;
	}

	@Override
	public Point getPosition() {
		vidas = new Cenario();
		Point pos = super.getPosition();
		int r = getRaio();

		if (pos.y - r == 255){
			vidas.setScore(vidas.getVidas()-1);
			if (vidas.getVidas() == 0){
				
			}
			setPosition(130,80);
		}
		return new Point(pos.x + r, pos.y + r);
	}
	
	public int getMorte() {
		return morte;
	}
	public void setMorte(int morte) {
		this.morte = morte;
	}
}
