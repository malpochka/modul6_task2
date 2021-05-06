package by.epam.notebook.view;

import by.epam.notebook.entity.Note;
import by.epam.notebook.entity.Notebook;

public class NotebookView {
	
	public void print(Notebook note) {
		System.out.println("Найденная заметка(и):");
		System.out.println(note);
	}

	public void printSort(Notebook notes) {
		System.out.println("Заметки отсортированы по дате:");
		for (Note n : notes.getNotes()) {
			System.out.println(n);
		}

	}
}
