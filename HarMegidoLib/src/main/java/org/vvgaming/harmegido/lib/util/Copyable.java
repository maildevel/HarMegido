package org.vvgaming.harmegido.lib.util;

/**
 * Classes que implementam esta interface permitem a opera��o de c�pia
 * 
 */
public interface Copyable 
{
	/**
	 * Retorna uma c�pia profunda desta classe, ou seja, guarda retorna "foto" do estado desta classe.
	 * @return Uma c�pia profunda desta classe.
	 */
	Copyable copy();
}
