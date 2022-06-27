package de.ldebuhr.settings;

/**
 * Stellt alle Programmtexte zur Verfuegung
 */
public class AppTexts {
	
	
	//region 0 Allgemein
	public static final String APPLICATION_NAME =   "\n###########################\n" +
													"##\tHotel Seestern\t\t##\n" +
													"##\tGaeste-Management\t##\n" +
													"##########################\n";
	
	public static final String GUEST_SINGULAR = "Gast";
	public static final String GUEST_PLURAL   = "Gaeste";
	public static final String GUEST_NAME   = "Gastname";
	public static final String GUEST_ROOM   = "Zimmer";
	public static final String GUEST_EXTRAS   = "Extras";
	
	
	public static final String MENU_NUMBER_PREFIX = "[ ";
	public static final String MENU_NUMBER_SUFFIX = " ]";
	public static final String SPACE    = " ";
	public static final String NEW_LINE = "\n";
	public static final String TAB = "\t";
	public static final String COLON = ": ";
	//endregion
	
	//region 1 Hauptmenue
	public static final String SHOW = MENU_NUMBER_PREFIX + AppCommands.CMD_SHOW + MENU_NUMBER_SUFFIX + TAB + GUEST_PLURAL + SPACE + "anzeigen";
	public static final String SAVE = MENU_NUMBER_PREFIX + AppCommands.CMD_SAVE + MENU_NUMBER_SUFFIX + TAB + GUEST_SINGULAR + SPACE + "einchecken";
	public static final String EDIT = MENU_NUMBER_PREFIX + AppCommands.CMD_EDIT + MENU_NUMBER_SUFFIX + TAB + GUEST_SINGULAR + SPACE + "editieren";
	public static final String DELETE = MENU_NUMBER_PREFIX + AppCommands.CMD_DELETE + MENU_NUMBER_SUFFIX + TAB + GUEST_SINGULAR + SPACE + "auschecken";
	public static final String NEW_PASSWORD = MENU_NUMBER_PREFIX + AppCommands.CMD_NEW_PASSWORD + MENU_NUMBER_SUFFIX + TAB + "Neues Passwort";
	public static final String EXIT     = MENU_NUMBER_PREFIX + AppCommands.CMD_EXIT + MENU_NUMBER_SUFFIX + TAB + "Programm beenden";
	
	public static final String USER_CHOICE              = NEW_LINE + MENU_NUMBER_PREFIX + SPACE + MENU_NUMBER_SUFFIX + TAB + "Nummer eingeben" + COLON;
	
	public static final String GUEST_LIST_HEADER = "\nGästeliste" + COLON;
	
	public static final String USER_MESSAGE_NO_SUCH_DATA   = "Keine Daten vorhanden!";
	public static final String USER_MESSAGE_ACCOUNT_LOCKED  = "Account ist gesperrt. Bitte an den Systemadministrator wenden.";
	public static final String USER_MESSAGE_INCORRECT_INPUT  = "Die Eingabe war nicht korrekt.";
	public static final String USER_MESSAGE_LOGIN_TRIES_LEFT = NEW_LINE + "Anzahl verbleibender Versuche" + COLON;
	public static final String USER_MESSAGE_WELCOME_MSG      = "Herzlich willkommen.";
	public static final String USER_MESSAGE_CORRECT_PASSWORD = NEW_LINE + "Das Passwort war korrekt.";
	public static final String USER_MESSAGE_ENTER_PASSWORD    = NEW_LINE + "Bitte geben Sie für die Anmeldung das Passwort ein" + COLON;
	public static final String USER_MESSAGE_NOTHING_TO_DELETE = "Aktuell kann nichts gelöscht werden.";
	public static final String USER_MESSAGE_INPUT_GUEST_DATA = "Bitte die Gastdaten eingeben" + COLON;
	public static final String USER_MESSAGE_CHOOSE_DATA       = NEW_LINE + "Bitte einen Eintrag auswählen" + COLON;
	public static final String USER_MESSAGE_BACK_TO_MAIN_MENU  = "Zurueck zum Hauptmenue";
	public static final String USER_MESSAGE_INPUT_NEW_PASSWORD = "Bitte neues Passwort angeben";
	public static final String USER_MESSAGE_ENTER_MASTERPASSWORD = "Masterpasswort eingeben";
	public static final String USER_MESSAGE_WRONG_MASTERPASSWORD = "Falsches Masterpasswort";
	public static final String USER_MESSAGE_PROGRAM_EXIT = "Programm wird beendet";
	public static final String USER_MESSAGE_RESET_PASSWORD = "Passwort wurde zurueck gesetzt";
	//endregion
	
	//region 2 Konstruktor
	private AppTexts() {
	}
	//endregion
	
}
