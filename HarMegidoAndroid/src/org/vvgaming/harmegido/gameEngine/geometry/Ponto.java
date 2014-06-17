package org.vvgaming.harmegido.gameEngine.geometry;

/**
 * Classe geom�trica que representa o ponto e suas opera��es
 * 
 * @author Vinicius Nogueira
 */
public class Ponto {

	public final float x, y;

	public Ponto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Ponto translate(float x, float y) {
		return new Ponto(this.x + x, this.y + y);
	}

}
