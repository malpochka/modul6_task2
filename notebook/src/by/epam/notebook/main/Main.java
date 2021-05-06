package by.epam.notebook.main;

import by.epam.notebook.entity.Notebook;
import by.epam.notebook.logic.NotebookImplLogic;
import by.epam.notebook.view.NotebookView;

/*
 * Задание2.Блокнот.Разработать консольное приложение, работающее с Заметками в Блокноте.
 * Каждая Зметка это:Заметка(тема,дата создания, e-mail, сообщение).
 * Общие пояснения к практическому заданию:
 * 1)В начале работы данный должны считываться из файла, в конце работы- сохраняться в файл.
 * 2)У пользователя должна быть возможность найти запись по любому параметру или
 * по группе параметров(группу параметров можно определить самостоятельно), получить треуемые 
 * записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово,
 * а также добавить новую запись.
 * 3)Особое условие:Поиск, сравнение и валидацию вводимой информации осуществлять с использованием
 * регулярных выражений.
 * 4)Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно
 * добавляющий информацию.
 */
public class Main {

	public static void main(String[] args) {
		String str;
		String str1;
		String str2;
		String str3;
		String newTopic;
		String newDate;
		String newMail;
		boolean checkNewNote;

		NotebookImplLogic logic = new NotebookImplLogic();
		NotebookView view = new NotebookView();
		
		str = logic.enterFromConsole("Введите название заметки или число заметки,или e-mail: ");
		
		Notebook foundNote = logic.searchNote(str, logic);
		view.print(foundNote);
		
		Notebook sortNotes = logic.noteSortByDate();
		view.printSort(sortNotes);

		str1 = logic.enterFromConsole("Введите число заметки: ");
		str2 = logic.enterFromConsole("Введите e-mail: ");

		Notebook foundNote2 = logic.searchNoteByGroup(str1, str2, logic);
		view.print(foundNote2);
		
		str3 = logic.enterFromConsole("Введите слово в тексте заметки: ");
		
		Notebook foundNote3 = logic.searchNoteByWord(str3);
		view.print(foundNote3);
		
		System.out.println();
		System.out.println("Добавьте новую запись:");
		newTopic = logic.enterFromConsole("Введите тему заметки:");

		newDate = logic.enterFromConsole("Введите дату заметки:");

		newMail = logic.enterFromConsole("Введите e-mail:");
		checkNewNote = logic.checkNewNote(newTopic, newDate, newMail, logic);
		
		logic.addNewNote(newTopic, newDate, newMail, checkNewNote);
	}
}
