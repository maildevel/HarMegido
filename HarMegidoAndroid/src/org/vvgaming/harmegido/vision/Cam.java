package org.vvgaming.harmegido.vision;

import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 * Wrapper para captura de frames da camera. Essa classe, captura frames da
 * c�mera.
 * 
 * @author Vinicius Nogueira
 */
public class Cam {

	// limite de tempo entre capturas de frames da c�mera
	private static final int TIME_LIMIT = 30;

	// timestamp da �ltima captura para saber se devemos capturar novo frame
	private long lastCapture;

	private VideoCapture camera;

	private NativeCameraFrame lastFrame = NativeCameraFrame.empty;

	private int width;
	private int height;

	/**
	 * Conecta � camera nativa
	 * 
	 * @param width
	 *            largura da resolu��o
	 * @param height
	 *            altura da resolu��o
	 * @param onFrameListener
	 *            listener para ser notificado a cada frame coletado
	 * 
	 * @return <code>true</code> se houve sucesso na conex�o
	 */
	public boolean connectCamera(int width, int height) {

		this.width = width;
		this.height = height;

		if (!initializeCamera())
			return false;

		return true;
	}

	/**
	 * Libera a camera conectada
	 */
	public void disconnectCamera() {
		synchronized (this) {
			if (camera != null) {
				camera.release();
			}
		}
	}

	private boolean initializeCamera() {
		synchronized (this) {

			camera = new VideoCapture(Highgui.CV_CAP_ANDROID);

			if (camera == null)
				return false;

			if (camera.isOpened() == false)
				return false;

			// java.util.List<Size> sizes = mCamera.getSupportedPreviewSizes();
			//
			// Size frameSize = calculateCameraFrameSize(sizes,
			// new OpenCvSizeAccessor(), width, height);
			//
			// mFrameWidth = (int) frameSize.width;
			// mFrameHeight = (int) frameSize.height;
			//
			// if ((getLayoutParams().width == LayoutParams.MATCH_PARENT)
			// && (getLayoutParams().height == LayoutParams.MATCH_PARENT))
			// mScale = Math.min(((float) height) / mFrameHeight,
			// ((float) width) / mFrameWidth);
			// else
			// mScale = 0;
			//
			// mCamera.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, frameSize.width);
			// mCamera.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, frameSize.height);

			camera.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, width);
			camera.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, height);
		}

		return true;
	}

	public NativeCameraFrame getFrame() {
		// se o est� dentro do limite, n�o precisa capturar de novo...
		// ou se der algum problema na captura retorna a antiga tamb�m
		if ((System.currentTimeMillis() - lastCapture) < TIME_LIMIT
				|| !camera.grab()) {
			return lastFrame;
		}

		lastCapture = System.currentTimeMillis();
		return lastFrame = (new NativeCameraFrame(camera));
	}

	public int getHeight() {
		// retorna invertido INTENCIONALMENTE, pois a imagem est� sendo
		// capturada invertida e ap�s rodar temos a invers�o de width e height
		// TODO arrumar isso
		return width;
	}

	public int getWidth() {
		// retorna invertido INTENCIONALMENTE, pois a imagem est� sendo
		// capturada invertida e ap�s rodar temos a invers�o de width e height
		// TODO arrumar isso
		return height;
	}

}
