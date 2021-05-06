package by.epam.notebook.dao;

import java.util.List;

import by.epam.notebook.entity.Note;

public interface NotebookDao {

	String read();

	List<Note> loadNote(String line);

	void write(String s);

}
