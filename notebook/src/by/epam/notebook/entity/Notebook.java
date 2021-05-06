package by.epam.notebook.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notebook implements Serializable {

	private static final long serialVersionUID = -261142442099462136L;

	private List<Note> notes;
	{
		notes = new ArrayList<Note>();
	}

	public Notebook() {

	}

	public void addAllNotes(List<Note> note) {
		notes.addAll(note);
	}
	public void addNote(Note note) {
		notes.add(note);
	}
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notebook other = (Notebook) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notebook [notes=" + notes + "]";
	}

}
