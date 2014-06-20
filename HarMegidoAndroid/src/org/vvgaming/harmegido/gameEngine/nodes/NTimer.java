package org.vvgaming.harmegido.gameEngine.nodes;

import org.vvgaming.harmegido.gameEngine.GameNode;

import com.github.detentor.codex.function.Function0;

/**
 * {@link GameObject} bem simples que serve para contar tempo para realizar determinadas ações
 * 
 * @author Vinicius Nogueira
 */
public class NTimer extends GameNode
{

	private long counter = 0;
	private long timeLimit = 0;
	private long cycles = 0;
	private final boolean oneTimeOnly;
	private final Function0<Void> callback;

	public NTimer(final long timeLimit, final Function0<Void> callback, final boolean oneTimeOnly)
	{
		super();
		this.oneTimeOnly = oneTimeOnly;
		this.timeLimit = timeLimit;
		this.callback = callback;
	}

	public NTimer(final long timeLimit, final Function0<Void> callback)
	{
		this(timeLimit, callback, false);
	}

	@Override
	public void update(final long delta)
	{
		counter += delta;
		if (counter >= timeLimit)
		{
			callback.apply();
			counter = counter % timeLimit;
			cycles++;
		}
	}

	@Override
	public boolean isDead()
	{
		return oneTimeOnly && cycles > 0;
	}

	@Override
	public boolean isVisible()
	{
		// sempre invisível, não pinta nada na tela
		return false;
	}

	public long getTimeLimit()
	{
		return timeLimit;
	}

	public void setTimeLimit(final long timeLimit)
	{
		this.timeLimit = timeLimit;
	}

	public NTimer setCounting(final long counter)
	{
		this.counter = counter;
		return this;
	}

}
