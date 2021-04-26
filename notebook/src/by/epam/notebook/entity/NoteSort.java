package by.epam.notebook.entity;

import java.util.Comparator;

public class NoteSort implements Comparator<Note> {

	public static final NoteSort noteSort = new NoteSort();

	@Override
	public int compare(Note o1, Note o2) {

		return o1.getDate().compareTo(o2.getDate());
	}

	public static NoteSort getNotesort() {
		return noteSort;
	}

}
