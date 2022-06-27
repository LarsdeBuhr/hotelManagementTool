package de.ldebuhr.ui;

import de.ldebuhr.logic.FileHandler;
import de.ldebuhr.model.Guest;
import de.ldebuhr.settings.AppCommands;
import de.ldebuhr.settings.AppTexts;
import de.rhistel.logic.ConsoleReader;

import java.util.List;


/**
 * Hier wird das Navigationsmenue ausgegeben.
 * Der User kann durch seine Eingabe eine passende CRUD Operation auswählen.
 */
public class UiController {
	
	
	//region 1 Attribute
	private List<Guest> guestList;
	private String      password;
	//endregion
	
	
	//region 2 Konstruktor
	
	/**
	 * Standardkonstruktor
	 * Hier wird das User Interface gestartet
	 */
	public UiController() {
		this.guestList = FileHandler.getInstance().readFromCsvFile();
		this.password = FileHandler.getInstance().readPasswordFromTxtFile();
		System.out.println(AppTexts.APPLICATION_NAME);
		login();
	}
	//endregion
	
	
	//region 3 LogIn
	
	/**
	 * Login-Bereich
	 * Sofern kein Passwort hinterlegt ist muss ein neues eingegeben werden.
	 * Sofern ein Passwort hinterlegt ist muss dieses eingegeben werden.
	 * Nach 3 Fehleingaben wird das Programm gesperrt und ein Masterpasswort gesetzt
	 */
	private void login() {
		
		boolean isValidPassword;
		
		//Prueft ob der Account gesperrt ist und ein Passwort Reset durchgefuehrt werden muss
		isValidPassword = resetPassword();
		
		this.password = FileHandler.getInstance().readPasswordFromTxtFile();
		
		//Passwort mit dem in der Datei hinterlegten überprüfen
		if (!isValidPassword) {
			checkPassword();
		}
	}
	//endregion
	
	
	//region 4 Hauptmenue
	
	/**
	 * Das Hauptmenue wird so lange angezeigt bis das Programm beendet wird.
	 */
	private void showMainMenu() {
		
		boolean exitApplication = false;
		
		while (!exitApplication) {
			this.printMainMenu();
			
			switch (this.getUserChoice()) {
				case AppCommands.CMD_SHOW -> show();
				case AppCommands.CMD_SAVE -> save();
				case AppCommands.CMD_EDIT -> edit();
				case AppCommands.CMD_DELETE -> delete();
				case AppCommands.CMD_NEW_PASSWORD -> newPassword();
				case AppCommands.CMD_EXIT -> exitApplication = true;
				default -> System.out.println(AppTexts.USER_MESSAGE_NO_SUCH_DATA);
			}
		}
		
	}
	
	/**
	 * Das Hauptmenue wird in der Konsole ausgegeben
	 */
	private void printMainMenu() {
		
		System.out.println(AppTexts.APPLICATION_NAME);
		
		System.out.println(AppTexts.SHOW);
		System.out.println(AppTexts.SAVE);
		System.out.println(AppTexts.EDIT);
		System.out.println(AppTexts.DELETE);
		System.out.println(AppTexts.NEW_PASSWORD);
		System.out.println(AppTexts.EXIT);
		
	}
	//endregion
	
	//region 5 Anzeigen
	
	/**
	 * Es werden alle Eintraege angezeigt sofern welche vorhanden sind
	 * Auswahl via Index. Fuer eine bessere User Experience wird der Index ab 1 ausgegeben
	 */
	private void show() {
		if (!this.guestList.isEmpty()) {
			System.out.println(AppTexts.GUEST_LIST_HEADER);
			
			for (int i = 0; i < this.guestList.size(); i++) {
				Guest currentGuest = this.guestList.get(i);
				
				
				System.out.println("\n" + AppTexts.MENU_NUMBER_PREFIX + (i + 1) + AppTexts.MENU_NUMBER_SUFFIX);
				System.out.println(AppTexts.GUEST_SINGULAR + AppTexts.COLON + currentGuest.getStrGuestName() + AppTexts.NEW_LINE + AppTexts.GUEST_ROOM + AppTexts.COLON + currentGuest.getstrGuestRoom() + AppTexts.NEW_LINE + AppTexts.GUEST_EXTRAS + AppTexts.COLON + currentGuest.getStrGuestExtras());
			}
			
		} else {
			System.out.println(AppTexts.USER_MESSAGE_NO_SUCH_DATA);
		}
	}
	//endregion
	
	//region 6 Speichern
	
	/**
	 * Ein neuer Gast-Datensatz wird in der CSV Datei gespeichert
	 */
	private void save() {
		Guest newGuest = this.getNewGuestFromUi();
		
		if (newGuest != null) {
			this.guestList.add(newGuest);
			
			FileHandler.getInstance().saveToCsvFile(this.guestList);
			
			this.show();
		} else {
			System.out.println(AppTexts.USER_MESSAGE_INCORRECT_INPUT);
		}
		
	}
	//endregion
	
	//region 7 Edit
	
	/**
	 * Ausgewaehlte Gast-Datensaetze koennen editiert werden
	 * Auswahl via Index. Fuer eine bessere User Experience wird der Index ab 1 ausgegeben
	 */
	private void edit() {
		if (!this.guestList.isEmpty()) {
			
			this.show();
			
			int indexOfGuestToEdit = this.getValidIndex();
			
			Guest guestToEdit = this.getNewGuestFromUi();
			
			if (guestToEdit != null) {
				
				this.guestList.set(indexOfGuestToEdit, guestToEdit);
				
				FileHandler.getInstance().saveToCsvFile(this.guestList);
				
				this.show();
			}
			
			
		} else {
			System.out.println(AppTexts.USER_MESSAGE_NO_SUCH_DATA);
		}
		
	}
	
	
	//endregion
	
	//region 8 Delete
	
	/**
	 * Ausgewaehlter Datensatz wid geloescht
	 * Auswahl via Index. Fuer eine bessere User Experience wird der Index ab 1 ausgegeben
	 */
	private void delete() {
		
		if (!this.guestList.isEmpty()) {
			
			this.show();
			
			int indexOfGuestToDelete = this.getValidIndex();
			
			this.guestList.remove(indexOfGuestToDelete);
			FileHandler.getInstance().saveToCsvFile(this.guestList);
			
			this.show();
			
		} else {
			System.out.println(AppTexts.USER_MESSAGE_NOTHING_TO_DELETE);
		}
	}
	//endregion
	
	//region 9 New Password
	
	/**
	 * Ein neues User Passwort kann erstellt werden
	 */
	private void newPassword() {
		System.out.println(AppTexts.USER_MESSAGE_INPUT_NEW_PASSWORD);
		String newPassword = ConsoleReader.in.readMandatoryString();
		
		FileHandler.getInstance().savePasswordToTxtFile(newPassword);
		
		
	}
	
	/**
	 * Passwort wird geprueft
	 * Wenn Paswort OK, dann Zugang zur App
	 * Ansonsten Account sperren nach 3 Fehlversuchen
	 */
	private void checkPassword() {
		boolean isCorrectPassword = false;
		int     logInTries        = 3;
		while (!isCorrectPassword) {
			
			System.out.println(AppTexts.USER_MESSAGE_ENTER_PASSWORD);
			String userPassword = ConsoleReader.in.readMandatoryString();
			
			
			if (userPassword.equals(password)) {
				System.out.println(AppTexts.USER_MESSAGE_CORRECT_PASSWORD + AppTexts.USER_MESSAGE_WELCOME_MSG);
				isCorrectPassword = true;
				showMainMenu();
			} else if (userPassword != password && logInTries > 0) {
				System.out.println(AppTexts.USER_MESSAGE_INCORRECT_INPUT + AppTexts.USER_MESSAGE_LOGIN_TRIES_LEFT + logInTries);
				logInTries--;
			} else {
				System.out.println(AppTexts.USER_MESSAGE_ACCOUNT_LOCKED);
				System.out.println(AppTexts.USER_MESSAGE_PROGRAM_EXIT);
				FileHandler.getInstance().savePasswordToTxtFile(AppCommands.MASTER_PASSWORD);
				isCorrectPassword = true;
			}
		}
	}
	
	/**
	 * Passwort reset und neu setzen
	 *
	 * @return {@link boolean} : isResetOk
	 */
	private boolean resetPassword() {
		boolean isResetOk;
		if (password == "") {
			newPassword();
			isResetOk = false;
		} else if (password.equals(AppCommands.MASTER_PASSWORD)) {
			System.out.println(AppTexts.USER_MESSAGE_ACCOUNT_LOCKED);
			System.out.println(AppTexts.USER_MESSAGE_ENTER_MASTERPASSWORD);
			String masterPassword = ConsoleReader.in.readMandatoryString();
			if (masterPassword.equals(AppCommands.MASTER_PASSWORD)) {
				String resetPassword = "";
				FileHandler.getInstance().savePasswordToTxtFile(resetPassword);
				System.out.println(AppTexts.USER_MESSAGE_RESET_PASSWORD);
				newPassword();
				isResetOk = false;
			} else {
				System.out.println(AppTexts.USER_MESSAGE_WRONG_MASTERPASSWORD);
				System.out.println(AppTexts.USER_MESSAGE_PROGRAM_EXIT);
				isResetOk = true;
			}
		} else {
			isResetOk = false;
		}
		return isResetOk;
	}
	//endregion
	
	
	//region 9 Hilfsmethoden und -funktionen
	
	/**
	 * User wird zur Eingabe aufgefordert. Der durch den User eingegebene Wert wird zurueck gegeben
	 *
	 * @return USER_CHOICE : int : Eingelesener Wert >= 0
	 */
	private int getUserChoice() {
		System.out.println(AppTexts.USER_CHOICE);
		return ConsoleReader.in.readPositivInt();
	}
	
	/**
	 * Liest die Eingabewerte vom User aus, prueft diese und liefert diese zurueck
	 *
	 * @return newGuestFromUI : {@link Guest} : gibt ein Guest Objekt zurueck
	 */
	private Guest getNewGuestFromUi() {
		Guest newGuestFromUi = null;
		
		final int indexGuestName       = 0;
		final int indexGuestRoomNumber = 1;
		final int indexGuestExtras     = 2;
		
		String[] strInputData = this.getInputData();
		
		newGuestFromUi = new Guest(strInputData[0], strInputData[1], strInputData[2]);
		
		return newGuestFromUi;
	}
	
	/**
	 * Gibt die Eingabeaufforderungen für die Werte eines neuen Guest Objektes aus und liest diese dann auch sofort ein.
	 *
	 * @return strInputted Data :{@link String}[] : eingelesene Eingabedaten
	 */
	private String[] getInputData() {
		System.out.println(AppTexts.USER_MESSAGE_INPUT_GUEST_DATA);
		
		System.out.print(AppTexts.GUEST_NAME + AppTexts.COLON);
		String strGuestName = ConsoleReader.in.readMandatoryString();
		
		System.out.print(AppTexts.GUEST_ROOM + AppTexts.COLON);
		String strGuestRoomNumber = ConsoleReader.in.readMandatoryString();
		
		System.out.print(AppTexts.GUEST_EXTRAS + AppTexts.COLON);
		String strGuestExtras = ConsoleReader.in.readMandatoryString();
		
		return new String[]{
				strGuestName,
				strGuestRoomNumber,
				strGuestExtras
		};
		
	}
	
	/**
	 * Gibt einen validen Index zurueck
	 *
	 * @return inputIndex : int : valider Index
	 */
	private int getValidIndex() {
		int     inputIndex   = -1;
		boolean isIndexValid = false;
		
		while (!isIndexValid) {
			System.out.println(AppTexts.USER_MESSAGE_CHOOSE_DATA);
			System.out.println(AppTexts.MENU_NUMBER_PREFIX + AppCommands.CMD_EXIT + AppTexts.MENU_NUMBER_SUFFIX + AppTexts.TAB + AppTexts.USER_MESSAGE_BACK_TO_MAIN_MENU);
			inputIndex = ConsoleReader.in.readPositivInt();
			if (inputIndex == 0) {
				isIndexValid = true;
			} else if ((inputIndex >= 0) && (inputIndex <= (this.guestList.size()))) {
				inputIndex--;
				isIndexValid = true;
			} else {
				System.out.println(AppTexts.USER_MESSAGE_INCORRECT_INPUT);
			}
		}
		return inputIndex;
	}
//endregion
}
