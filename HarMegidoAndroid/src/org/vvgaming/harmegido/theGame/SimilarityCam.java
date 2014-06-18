package org.vvgaming.harmegido.theGame;

import org.opencv.core.Mat;
import org.vvgaming.harmegido.vision.JavaCameraFrame;

import com.github.detentor.codex.monads.Option;
import com.github.detentor.codex.product.Tuple2;

/**
 * � uma especifica��o de c�mera que tem a capacidade de capturar determinados
 * frames e compar�-los ao atuais que fica comparando os frames com frames
 * anteriores guardados, verificando sua similaridade. <br/>
 * 
 * @author Vinicius Nogueira
 */
public interface SimilarityCam<T> {

	/**
	 * Captura um snapshot retornando a imagem capturada e os dados necess�rios
	 * � compara��o deste frame no futuro
	 * 
	 * @return
	 */
	public abstract T snapshot();

	/**
	 * Inicia observa��o dos dados informados por parametro, isto �, fica
	 * comparando o frame atual com este informado
	 * 
	 * @param obs
	 */
	public abstract void observar(T obs);

	/**
	 * Para a observa��o iniciada por
	 * {@link FeaturesSimilarityCam#observar(Tuple2)}
	 */
	public abstract void stopObservar();

	/**
	 * Compara o frame atual com o frame capturado anteriormente
	 * 
	 * @return retorna o valor da compara��o de acordo com a m�trica definida ou
	 *         option vazia, caso n�o tenha sido um frame capturado
	 *         anteriormente. Capture com
	 *         {@link FeaturesSimilarityCam#snapshot()} e inicie observa��o com
	 *         {@link FeaturesSimilarityCam#observar(Mat)}
	 */
	public abstract Option<Float> compara();

	/**
	 * Conecta � c�mera com a resolu��o desejada
	 * 
	 * @param width
	 * @param height
	 */
	public abstract void connectCamera(int width, int height);

	/**
	 * Libera a c�mera
	 */
	public abstract void disconnectCamera();

	/**
	 * Recupera o �ltimo frame
	 * 
	 * @return
	 */
	public abstract JavaCameraFrame getLastFrame();

	/**
	 * Implementa a regra para ver se o resultado da compara��o � suficiente
	 * para considerar a imagem "bastante" similar
	 * 
	 * @param comparacao
	 * @return
	 */
	public abstract boolean isSimilarEnough(float comparacao);

	public abstract int getHeight();

	public abstract int getWidth();

}