package by.epam.notebook.logic;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.notebook.dao.DAOProvider;
import by.epam.notebook.dao.NotebookImpDao;
import by.epam.notebook.entity.Note;
import by.epam.notebook.entity.NoteSort;
import by.epam.notebook.entity.Notebook;

public class NotebookLogic implements NotebookImplLogic {

	private final DAOProvider provider = DAOProvider.getInstance();

	@Override
	public Notebook notes() {
		NotebookImpDao notebookDAO = provider.getNotebookDAO();

		String line = notebookDAO.readFile();
		Notebook notes = new Notebook();
		notes.addAllNotes(notebookDAO.loadNote(line));

		return notes;
	}

	@Override
	public void searchNote() {
		String str;
		boolean check;
		check = true;

		Notebook notes = notes();

		str = enterFromConsole("Введите название заметки или число заметки,или e-mail: ");
		Pattern pattern = Pattern.compile("[^\\d]+");
		Matcher m = pattern.matcher(str);

		Pattern pattern2 = Pattern.compile("[0-9,]+");
		Matcher m2 = pattern2.matcher(str);

		Pattern pattern3 = Pattern.compile("[0-9a-z_@.]+");
		Matcher m3 = pattern3.matcher(str);

		if (check == m.matches()) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if (notes.getNotes().get(i).getTopic().equalsIgnoreCase(str)) {
					System.out.println(notes.getNotes().get(i));
				}
			}
		} else if (check == m2.matches()) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if (notes.getNotes().get(i).getDate().equalsIgnoreCase(str)) {
					System.out.println(notes.getNotes().get(i));
				}
			}
		} else if (check == m3.matches()) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if (notes.getNotes().get(i).getMail().equalsIgnoreCase(str)) {
					System.out.println(notes.getNotes().get(i));
				}
			}
		} else {
			System.out.println("Вы ввели недопустимые символы");
		}
	}

	@Override
	public void searchNoteByGroup() {
		String str1;
		String str2;
		boolean check;
		check = true;

		Notebook notes = notes();

		str1 = enterFromConsole("Введите число заметки: ");
		str2 = enterFromConsole("Введите e-mail: ");

		Pattern pattern = Pattern.compile("[0-9,]+");
		Matcher m = pattern.matcher(str1);

		Pattern pattern2 = Pattern.compile("[0-9a-z_@.]+");
		Matcher m2 = pattern2.matcher(str2);

		if (check == m.matches() & check == m2.matches()) {
			for (int i = 0; i < notes.getNotes().size(); i++) {
				if ((notes.getNotes().get(i).getDate().equalsIgnoreCase(str1))
						& (notes.getNotes().get(i).getMail().equalsIgnoreCase(str2))) {

					System.out.println(notes.getNotes().get(i));
				}
			}
		} else {
			System.out.println("Вы ввели недопустимые символы");
		}
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
	public void noteSortByDate() {
		NotebookImpDao notebookDAO = provider.getNotebookDAO();

		String line = notebookDAO.readFile();
		List<Note> notes = notebookDAO.loadNote(line);

		Collections.sort(notes, NoteSort.noteSort);

		for (Note n : notes) {
			System.out.println(n);
		}
	}

	@Override
	public void searchNoteByWord() {
		String str;
		boolean check;

		check = true;

		Notebook notes = notes();

		str = enterFromConsole("Введите слово в тексте заметки: ");

		Pattern regexp = Pattern.compile(str);
		for (int i = 0; i < notes.getNotes().size(); i++) {
			Matcher m = regexp.matcher(notes.getNotes().get(i).getMessage());
			if (check == m.find()) {
				System.out.print(notes.getNotes().get(i) + "\r");
			}
		}

	}

	@Override
	public void addNewNote() {
		String str;
		String str1;
		String str2;
		String str3;

		boolean check = true;

		NotebookImpDao notebookDAO = provider.getNotebookDAO();

		System.out.println();
		System.out.println("Добавьте новую запись:");

		str = enterFromConsole("Введите тему заметки:");

		Pattern pattern = Pattern.compile("[^\\d]+");
		Matcher m = pattern.matcher(str);

		if (check == m.matches()) {
			System.out.println("Данные приняты");
			;
		} else {
			System.out.println("Вы ввели недопустимые символы");

		}

		str1 = enterFromConsole("Введите дату заметки:");

		Pattern pattern2 = Pattern.compile("[0-9,]+");
		Matcher m2 = pattern2.matcher(str1);

		if (check == m2.matches()) {
			System.out.println("Данные приняты");
		} else {
			System.out.println("Вы ввели недопустимые символы");

		}

		str2 = enterFromConsole("Введите e-mail:");

		Pattern pattern3 = Pattern.compile("[0-9a-z_@.]+");
		Matcher m3 = pattern3.matcher(str2);

		if (check == m3.matches()) {
			System.out.println("Данные приняты");
		} else {
			System.out.println("Вы ввели недопустимые символы");

		}

		if (check == m.matches() & check == m2.matches() & check == m.matches()) {
			str3 = enterFromConsole("Введите текст:");
			notebookDAO.writeFile(str);
			notebookDAO.writeFile(str1);
			notebookDAO.writeFile(str2);
			notebookDAO.writeFile(str3);
		} else {
			System.out.println("Вы ввели недопустимые символы,текст записан не будет");
		}
	}
}
