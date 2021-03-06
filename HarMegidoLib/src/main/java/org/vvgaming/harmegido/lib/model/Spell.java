package org.vvgaming.harmegido.lib.model;

import java.util.Date;

/**
 * Classe base para todas as instâncias "mágicas". <br/>
 * A igualdade/hashCode da classe está definida para o jogador e timestamp que a mágica está vinculada. <br/>
 */
public class Spell
{
	private final Player jogador;
	private final Date timestamp;

	protected Spell(final Player jogador, final Date timestamp)
	{
		super();
		this.jogador = jogador;
		this.timestamp = timestamp;
	}
	
	/**
	 * Cria uma classe de Spell a partir das informações do jogador e timestamp. <br/>
	 * Seu uso normalmente é para servir de identificador para algum encantamento
	 * @param jogador O jogador associado com esse Spell
	 * @param timestamp O instante no tempo que ele foi criado
	 * @return Uma instância de Spell vinculada ao jogador e instante do tempo passados como parâmetro.
	 */
	public static Spell create(final Player jogador, final Date timestamp)
	{
		return new Spell(jogador, timestamp);
	}
	
	public Player getJogador()
	{
		return jogador;
	}

	public Date getTimestamp()
	{
		return new Date(timestamp.getTime());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (jogador == null ? 0 : jogador.hashCode());
		result = prime * result + (timestamp == null ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Spell other = (Spell) obj;
		if (jogador == null)
		{
			if (other.jogador != null)
			{
				return false;
			}
		}
		else if (!jogador.equals(other.jogador))
		{
			return false;
		}
		if (timestamp == null)
		{
			if (other.timestamp != null)
			{
				return false;
			}
		}
		else if (!timestamp.equals(other.timestamp))
		{
			return false;
		}
		return true;
	}

}
