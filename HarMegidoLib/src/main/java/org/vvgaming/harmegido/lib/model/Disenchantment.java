package org.vvgaming.harmegido.lib.model;

import java.util.Date;

/**
 * Representa o desencantamento de algum encantamento. Imut�vel.<br/>
 */
public class Disenchantment
{
	private final Player jogador;
	private final Date timestamp;
	private final Enchantment encantamento;

	
	protected Disenchantment(final Player jogador, final Date timestamp, final Enchantment encantamento) {
		super();
		this.jogador = jogador.copy(); //salva o estado do jogador na hora do desencantamento
		this.timestamp = timestamp;
		encantamento.desencantar(this); //faz o efeito colateral pra salvar o estado
		this.encantamento = encantamento.copy(); //salva o estado do encantamento
	}

	/**
	 * Cria um novo desencantamento, para o jogador, timestamp e encantamento passados como par�metro. <br/>
	 * Como efeito colateral, o encantamento passado como par�metro ser� alterado para refletir o desencantamento. <br/>
	 * 
	 * @param jogador O jogador respons�vel pelo desencantamento
	 * @param timestamp A data/hora do desencantamento
	 * @param encantamento O encantamento que est� sendo desencantado
	 * @return Uma inst�ncia de {@link Disenchantment} que representa o desencantamento
	 * @throws IllegalArgumentException Se o jogador que estiver desencantando for do mesmo tipo que
	 * encantou
	 */
	public static Disenchantment from(final Player jogador, final Date timestamp, final Enchantment encantamento)
	{
		if (jogador.getTime().equals(encantamento.getJogador().getTime()))
		{
			throw new IllegalArgumentException("O time que desencanta deve ser diferente do time que encantou");
		}
		return new Disenchantment(jogador, timestamp, encantamento);
	}

	public Player getJogador() {
		return jogador;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Enchantment getEncantamento() {
		return encantamento;
	}
	
	public int calcularPontuacao()
	{
		//TODO: efetivar o c�lculo
		return 0;
	}
}
