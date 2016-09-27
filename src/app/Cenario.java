package app;

public class Cenario{

	private int score = 0, recorde1 = 0, recorde2 = 0, recorde3 = 0, nivel = 1, vidas = 3;

	public Cenario(){
		super ();
	}
	
	public Cenario(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getRecorde1() {
		return recorde1;
	}

	public void setRecorde1(int recorde1) {
		this.recorde1 = recorde1;
	}

	public int getRecorde2() {
		return recorde2;
	}

	public void setRecorde2(int recorde2) {
		this.recorde2 = recorde2;
	}

	public int getRecorde3() {
		return recorde3;
	}

	public void setRecorde3(int recorde3) {
		this.recorde3 = recorde3;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
}
