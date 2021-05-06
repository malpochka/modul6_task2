package by.epam.notebook.logic;


import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.notebook.dao.DAOProvider;
import by.epam.notebook.dao.NotebookDao;
import by.epam.notebook.entity.Note;
import by.epam.notebook.entity.NoteSort;
import by.epam.notebook.entity.Notebook;

public class NotebookImplLogic implements NotebookLogic {

	private final DAOProvider provider = DAOProvider.getInstance();

	@Override
	public Notebook notes() {
		NotebookDao notebookDAO = provider.getNotebookDAO();

		String line = notebookDAO.read();
		Notebook notes = new Notebook();
		notes.addAllNotes(notebookDAO.loadNote(line));

		return notes;
	}

	@Override
	public Notebook searchNote(String str, NotebookImplLogic l) {

		boolean check;
		check = true;

		Notebook notes = notes();
		Notebook foundNote = new Notebook();

		if (check == l.checkNoteByTopic(str)) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if (notes.getNotes().get(i).getTopic().equalsIgnoreCase(str)) {
					foundNote.addNote(notes.getNotes().get(i));
				}
			}
		} else if (check == l.checkNoteByDate(str)) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if (notes.getNotes().get(i).getDate().equalsIgnoreCase(str)) {
					foundNote.addNote(notes.getNotes().get(i));
				}
			}
		} else if (check == l.checkNoteByMail(str)) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if (notes.getNotes().get(i).getMail().equalsIgnoreCase(str)) {
					foundNote.addNote(notes.getNotes().get(i));
				}
			}
		} else {
			System.out.println("Вы ввели недопустимые символы");
		}
		return foundNote;
	}

	public boolean checkNoteByTopic(String str) {
		boolean check;

		Pattern pattern = Pattern.compile("[^\\d]+");
		Matcher m = pattern.matcher(str);

		check = m.matches();

		return check;
	}

	public boolean checkNoteByDate(String str) {
		boolean check;
		Pattern pattern = Pattern.compile("[0-9,]+");
		Matcher m = pattern.matcher(str);
		check = m.matches();
		return check;
	}

	public boolean checkNoteByMail(String str) {
		boolean check;

		Pattern pattern = Pattern.compile("[0-9a-z_@.]+");
		Matcher m = pattern.matcher(str);

		check = m.matches();

		return check;
	}

	@Override
	public Notebook searchNoteByGroup(String str1, String str2, NotebookImplLogic l) {

		boolean check;
		check = true;

		Notebook notes = notes();
		Notebook foundNote = new Notebook();

		if (check == l.checkNoteByDate(str1) & check == l.checkNoteByMail(str2)) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if ((notes.getNotes().get(i).getDate().equalsIgnoreCase(str1))
						& (notes.getNotes().get(i).getMail().equalsIgnoreCase(str2))) {

					foundNote.addNote(notes.getNotes().get(i));
				}
			}
		} else {
			System.out.println("Вы ввели недопустимые символы");
		}
		return foundNote;
	}

	@Override
	public String enterFromConsole(String message) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println(message);

		while (!sc.hasNextLine()) {
			sc.next();
			System.out.println(message);
		}

		return sc.nextLine();
	}

	@Override
	public Notebook noteSortByDate() {
		
		NotebookDao notebookDAO = provider.getNotebookDAO();

		String line = notebookDAO.read();
		List<Note> notes = notebookDAO.loadNote(line);

		Collections.sort(notes, NoteSort.noteSort);

		Notebook sortNotes = new Notebook();
		sortNotes.addAllNotes(notes);

		return sortNotes;
	}

	@Override
	public Notebook searchNoteByWord(String str) {

		boolean check;

		check = true;

		Notebook notes = notes();
		Notebook foundNote = new Notebook();

		Pattern regexp = Pattern.compile(str);
		for (int i = 0; i < notes.getNotes().size(); i++) {
			Matcher m = regexp.matcher(notes.getNotes().get(i).getMessage());
			if (check == m.find()) {
				foundNote.addNote(notes.getNotes().get(i));
			} else {
				System.out.println("Вы ввели недопустимые символы");
				break;
			}
		}
		return foundNote;

	}

	@Override
	public boolean checkNewNote(String str, String str2, String str3, NotebookImplLogic l) {
		boolean check = true;

		if (check == l.checkNoteByTopic(str) & check == l.checkNoteByDate(str2) & check == l.checkNoteByMail(str3)) {

			System.out.println("Данные приняты");

		} else {
			System.out.println("Вы ввели недопустимые символы");
			check = false;
		}

		return check;
	}

	@Override
	public void addNewNote(String str, String str1, String str2, boolean checkNewNote) {
		String str3;
		boolean check = true;

		NotebookDao notebookDAO = provider.getNotebookDAO();

		if (check == checkNewNote) {
			str3 = enterFromConsole("Введите текст:");
			notebookDAO.write(str);
			notebookDAO.write(str1);
			notebookDAO.write(str2);
			notebookDAO.write(str3);
		} else {
			System.out.println("Вы ввели недопустимые символы,текст записан не будет");
		}

	}
}
