package de.ldebuhr.logic;

import de.ldebuhr.model.Guest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse speichert die Daten des Programms in einer CSV-Datei
 * Daten der Gaeste in einer CSV Datei
 * Passwortdaten in einer txt Datei
 *
 * Klasse ist ein Singleton und stellt somit einen threadsicheren Dateizugriff zur Verfuegung
 */
public class FileHandler {
	
	//region 0 Konstanten
	private static final String CSV_FILE_PATH          = "src/de/ldebuhr/resource/guestList.csv";
	private static final String TXT_PASSWORD_FILE_PATH = "src/de/ldebuhr/resource/passwordFile.txt";
	//endregion
	
	//region 1 Decl and Init Attributes
	private static FileHandler onlyInstance;
	//endregion
	
	//region 2 Konstruktor
	
	/**
	 * Privater Kosntruktor, damit von außen keine Instanz dieser Klasse erzeuget werden kann
	 * Der Konstruktor kann nur ueber die Funktion getInstance() aufgerufen werden
	 */
	private FileHandler() {
	}
	//endregion
	
	//region 3 getInstance
	
	/**
	 * Funktion zur Gewährleistung einer synchronisierten, thread- und zugriffssicheren Nutzung der Klasse
	 *
	 * @return: instance:{@link FileHandler}: Einzige Instanz zur Laufzeit
	 */
	public static synchronized FileHandler getInstance() {
		if (onlyInstance == null) {
			onlyInstance = new FileHandler();
		}
		return onlyInstance;
	}
	//endregion
	
	//region 4 Save File
	
	/**
	 * Speichert die uebergebene Liste {@List} von {@Guest} in eine CSV Datei
	 * @param guestListToSave : {@link List} {@link Guest} : die gespeichert werden sollen
	 */
	public void saveToCsvFile(List<Guest> guestListToSave) {
		File               csvFile = new File(CSV_FILE_PATH);
		FileOutputStream   fos     = null;
		OutputStreamWriter osw     = null;
		BufferedWriter     out     = null;
		
		try {
			fos = new FileOutputStream(csvFile);
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			out = new BufferedWriter(osw);
			
			for (Guest guest : guestListToSave) {
				out.write(guest.getAllAttributesAsCsvString() + "\n");
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Liest die Datei {@List CsvFileHandler#CSV_FILE_PATH}
	 *
	 * @return guestListFromFile : {@List} {@Guest} : Gastdaten aus der Datei
	 */
	public List<Guest> readFromCsvFile() {
		List<Guest> guestListFromFile = new ArrayList<>();
		
		File              csvFile = new File(CSV_FILE_PATH);
		FileInputStream   fis     = null;
		InputStreamReader isr     = null;
		BufferedReader    in      = null;
		
		try {
			if (csvFile.exists()) {
				
				fis = new FileInputStream(csvFile);
				isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				in = new BufferedReader(isr);
				
				boolean eof = false;
				
				while (!eof) {
					String strReadCsvLine = in.readLine();
					
					if (strReadCsvLine == null) {
						eof = true;
					} else {
						Guest currentGuest = new Guest();
						currentGuest.setAllAttributesFromCsvLine(strReadCsvLine);
						guestListFromFile.add(currentGuest);
						
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return guestListFromFile;
	}
	
	//endregion
	
	
	//region Password save and read
	/**
	 * Speichert den uebergebenen {@String} in einer txt Datei
	 * @param  : {@String passwordToSave} : das gespeichert werden soll
	 */
	public void savePasswordToTxtFile(String passwordToSave) {
		File               csvFile = new File(TXT_PASSWORD_FILE_PATH);
		FileOutputStream   fos     = null;
		OutputStreamWriter osw     = null;
		BufferedWriter     out     = null;
		
		try {
			fos = new FileOutputStream(csvFile);
			osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			out = new BufferedWriter(osw);
			
			out.write(passwordToSave);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Liest die Datei {@String CsvFileHandler#TXT_FILE_PATH}
	 * @param  : {@String password} : das ausgelesen werden soll
	 */
	public String readPasswordFromTxtFile() {
		
		String password = "";
		
		File              csvFile = new File(TXT_PASSWORD_FILE_PATH);
		FileInputStream   fis     = null;
		InputStreamReader isr     = null;
		BufferedReader    in      = null;
		
		try {
			if (csvFile.exists()) {
				
				fis = new FileInputStream(csvFile);
				isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				in = new BufferedReader(isr);
				
				boolean eof = false;
				
				while (!eof) {
					String strReadTxtLine = in.readLine();
					
					if (strReadTxtLine == null) {
						eof = true;
					} else {
						password = strReadTxtLine;
						
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return password;
	}
	//endregion
}
