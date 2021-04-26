package by.epam.notebook.logic;

import by.epam.notebook.entity.Notebook;

public interface NotebookImplLogic {

	public abstract Notebook notes();

	public abstract void searchNoteByGroup();

	public abstract String enterFromConsole(String message);

	public abstract void noteSortByDate();

	public abstract void searchNote();

	public abstract void searchNoteByWord();

	public abstract void addNewNote();
}
