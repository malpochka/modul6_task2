package by.epam.notebook.logic;

import by.epam.notebook.entity.Notebook;

public interface NotebookLogic {

	Notebook notes();

	Notebook searchNote(String str, NotebookImplLogic l);

	Notebook searchNoteByGroup(String str1, String str2, NotebookImplLogic l);

	String enterFromConsole(String message);

	Notebook noteSortByDate();

	Notebook searchNoteByWord(String str);

	boolean checkNewNote(String str, String str2, String str3, NotebookImplLogic l);

	void addNewNote(String str, String str1, String str2, boolean checkNewNote);

}
