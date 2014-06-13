package org.vvgaming.harmegido.server;

import java.util.List;

import org.unbiquitous.uos.core.UOS;
import org.unbiquitous.uos.core.adaptabitilyEngine.ServiceCallException;
import org.unbiquitous.uos.core.driverManager.DriverData;
import org.unbiquitous.uos.core.driverManager.UosDriver;
import org.unbiquitous.uos.core.messageEngine.dataType.UpDevice;
import org.unbiquitous.uos.core.messageEngine.messages.Call;
import org.unbiquitous.uos.core.messageEngine.messages.Response;

import com.github.detentor.codex.monads.Either;
import com.github.detentor.codex.product.Tuple2;

/**
 * Classe de fachada para o ServerDriver, de modo a simplificar o seu uso. <br/>
 * Todos os m�todos poss�veis de serem utilizados pelo ServerDriver s�o m�todos
 * desta classe. 
 */
public class ServerDriverFacade 
{
	final UOS uos;
	final ServerDriver serverDriver;

	protected ServerDriverFacade(final UOS uos) {
		super();
		this.uos = uos;
		this.serverDriver = new ServerDriver();
	}

	/**
	 * Cria um facade (fachada) para o driver do servidor a partir do UOS passado como par�metro.
	 * @param uos A inst�ncia do UOS a ser utilizada para a fachada
	 * @return Uma inst�ncia da fachada do servidor
	 */
	public static ServerDriverFacade from(final UOS uos) 
	{
		if (uos == null)
		{
			throw new IllegalArgumentException("uos n�o pode ser nulo");
		}
		if (uos.getGateway() == null)
		{
			throw new IllegalArgumentException("Erro: Gateway do uos n�o pode retornar null");
		}
		return new ServerDriverFacade(uos);
	}
	
	/**
	 * Retorna um dispositivo a partir do nome do driver. <br/>
	 * @param driver O driver a partir do qual o dispositivo ser� encontrado
	 * @return Um dispositivo que corresponde ao driver
	 * @throws RuntimeException Se n�o houver dispositivo corresponde ao driver
	 */
	private UpDevice deviceFromDriver(final UosDriver driver)
	{
		final List<DriverData> listDrivers = uos.getGateway().listDrivers(driver.getDriver().getName());
		
		if (listDrivers == null || listDrivers.isEmpty())
		{
			throw new RuntimeException("Nenhum dispositivo encontrado para o driver passado como par�metro");
		}
		
		return listDrivers.get(0).getDevice();
	}
	
	/**
	 * Chamada gen�rica para um servi�o deste server. 
	 * @param serviceName O nome do servi�o a ser chamado
	 * @param params Os par�metros a serem repassados para o servi�o
	 * @return Uma inst�ncia de {@link Either} que ir� conter a mensagem de retorno ou a exce��o no caso de erro.
	 */
	private Either<ServiceCallException, String> callService(final String serviceName, final Tuple2<String, Object>... params)
	{
		final Call call = new Call(serverDriver.getDriver().getName(), serviceName);
		
		if (params != null)
		{
			for (Tuple2<String, Object> curParam : params)
			{
				call.addParameter(curParam.getVal1(), curParam.getVal2());
			}
		}

		try {
			final Response response = uos.getGateway().callService(deviceFromDriver(serverDriver), call);
			return Either.createRight(response.toString());
		} 
		catch (ServiceCallException e) {
			return Either.createLeft(e);
		}
	}

	/**
	 * Envia uma mensagem para o driver do servidor
	 * @param message A mensagem a ser enviada
	 * @return Uma inst�ncia de {@link Either} que ir� conter a mensagem de retorno ou a exce��o no caso de erro.
	 */
	@SuppressWarnings("unchecked")
	public Either<ServiceCallException, String> sendMessage(final String message) 
	{
		return callService("sendMessage", Tuple2.<String, Object>from("message", message));
	}
}
