package by.epam.notebook.dao;

import java.util.List;

import by.epam.notebook.entity.Note;

public interface NotebookImpDao {

	public abstract String readFile();

	public abstract List<Note> loadNote(String line);

	public abstract void writeFile(String s);

}
