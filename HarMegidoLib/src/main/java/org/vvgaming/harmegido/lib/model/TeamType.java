package org.vvgaming.harmegido.lib.model;

/**
 * Esse enum representa os tipos poss�veis de time 
 */
public enum TeamType 
{
	LIGHT, DARK;
	
	public String toString() 
	{
		String toReturn = "";

		switch (this){
			case LIGHT: 
				toReturn = "Light";
				break;
			case DARK: 
				toReturn = "Dark";
				break;
			default:
				throw new IllegalArgumentException("Tipo n�o reconhecido: " + this);	
		}
		return toReturn;
	}
}
