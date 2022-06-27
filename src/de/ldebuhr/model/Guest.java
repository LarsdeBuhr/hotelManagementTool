package de.ldebuhr.model;

/**
 * Diese Modelklasse repraesentiert einen Gast
 */
public class Guest {
	
	//region 0 Kosntanten
	/**
	 * Standardwerte
	 */
	private static final String DEF_VALUE_STR = ">noValueSetYet<";
	
	private static final String SPLIT_CHAR = ";";
	
	private static final int INDEX_GUEST_NAME   = 0;
	private static final int INDEX_GUEST_ROOM   = 1;
	private static final int INDEX_GUEST_EXTRAS = 2;
	//endregion
	
	//region 1 Decl and Init Attributes
	private String strGuestName;
	private String strGuestRoom;
	private String strGuestExtras;
	//endregion
	
	//region 2 Konstruktoren
	
	/**
	 * Standardkonstruktor mit Default Werten
	 */
	public Guest() {
		this.strGuestName = DEF_VALUE_STR;
		this.strGuestRoom = DEF_VALUE_STR;
		this.strGuestExtras = DEF_VALUE_STR;
	}
	
	/**
	 * Ueberladener Konstruktor zum direkten Setzen der Attribute via Parameter
	 *
	 * @param strGuestName          :{@link String}:Gastname (Vor- und/oder Nachname)
	 * @param strGuestRoom:{@link   String}:Zimmernummer (Suite 1, Einzel 1, Doppel 1,...)
	 * @param strGuestExtras:{@link String}:Gebuchte Extras (zB Sauna, MiniBar,...)
	 */
	public Guest(String strGuestName, String strGuestRoom, String strGuestExtras) {
		this.strGuestName = strGuestName;
		this.strGuestRoom = strGuestRoom;
		this.strGuestExtras = strGuestExtras;
	}
	
	//endregion
	
	//region 3 Getter und Setter
	
	public String getStrGuestName() {
		return strGuestName;
	}
	
	public void setStrGuestName(String strGuestName) {
		this.strGuestName = strGuestName;
	}
	
	public String getstrGuestRoom() {
		return strGuestRoom;
	}
	
	public void setstrGuestRoom(String strGuestRoom) {
		this.strGuestRoom = strGuestRoom;
	}
	
	public String getStrGuestExtras() {
		return strGuestExtras;
	}
	
	public void setStrGuestExtras(String strGuestExtras) {
		this.strGuestExtras = strGuestExtras;
	}
	
	/**
	 * @return getAllAttributesAsCsvString:{@link String}: Alle Attribute mit einem Semikolon getrennt
	 */
	public String getAllAttributesAsCsvString() {
		return this.strGuestName + SPLIT_CHAR + this.strGuestRoom + SPLIT_CHAR + this.strGuestExtras;
	}
	
	/**
	 *
	 * @param strReadCsvLine :{@link String}: Ausgelesener CSV-String aus {@link de.ldebuhr.logic.FileHandler}
	 */
	public void setAllAttributesFromCsvLine(String strReadCsvLine) {
		String[] strAllAttributes = strReadCsvLine.split(SPLIT_CHAR);
		
		this.strGuestName = strAllAttributes[INDEX_GUEST_NAME];
		this.strGuestRoom = strAllAttributes[INDEX_GUEST_ROOM];
		this.strGuestExtras = strAllAttributes[INDEX_GUEST_EXTRAS];
	}
	
	//endregion
	
	
	//region 4 Methoden und Funktionen
	@Override
	public String toString() {
		return "Guest{" +
				"strGuestName='" + strGuestName + '\'' +
				", iGuestRoom=" + strGuestRoom +
				", strGuestExtras='" + strGuestExtras + '\'' +
				'}';
	}
	//endregion
	
	
}
