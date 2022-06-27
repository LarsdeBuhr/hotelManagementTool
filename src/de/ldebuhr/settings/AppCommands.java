package de.ldebuhr.settings;

/**
 * Befehle des Menues als Konstanten
 */
public class AppCommands {
	
	//region 0 Konstanten
	
	public static final int CMD_SHOW = 1;
	public static final int CMD_SAVE = 2;
	public static final int CMD_EDIT = 3;
	public static final int CMD_DELETE = 4;
	public static final int CMD_NEW_PASSWORD = 5;
	public static final int CMD_EXIT = 0;
	
	public static final String MASTER_PASSWORD = "Masterpasswort";
	
	//endregion
	
	//region 2 Privater Konstruktor
	
	/**
	 * Durch privaten Konstruktor keine Möglichkeit eine Instanz dieser Klasse von außerhalb zu erzeugen
	 * Diese Klasse stellt nur Konstanten zur Verfuegung
	 */
	private AppCommands() {
	}
	//endregion
	
	
}
