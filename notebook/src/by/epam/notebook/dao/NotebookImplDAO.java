package by.epam.notebook.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.epam.notebook.entity.Note;

public class NotebookImplDAO implements NotebookDao {

	@Override
	public String read() {
		
		String lines = "";
		BufferedReader reader = null;

		try {
			File noteFile = new File("source\\note.txt");
			FileReader fileReader = new FileReader(noteFile);
			reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines += line;
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {

			try {

				if (reader != null)
					reader.close();

			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}
		return lines;
	}

	@Override
	public List<Note> loadNote(String line) {

		String[] result = line.split(";");

		List<Note> notes = new ArrayList<>();

		List<String> topic = new ArrayList<>();
		
		for (int i = 0; i < result.length; i++) {
			topic.add(result[i]);
			i += 3;
		}
		List<String> date = new ArrayList<>();
		
		for (int i = 1; i < result.length; i++) {
			date.add(result[i]);
			i += 3;
		}
		List<String> mail = new ArrayList<>();
		
		for (int i = 2; i < result.length; i++) {
			mail.add(result[i]);
			i += 3;
		}
		List<String> message = new ArrayList<>();
		
		for (int i = 3; i < result.length; i++) {
			message.add(result[i]);
			i += 3;
		}

		for (int i = 0; i < topic.size(); i++) {
			notes.add(new Note(topic.get(i), date.get(i), mail.get(i), message.get(i)));
		}

		return notes;
	}

	@Override
	public void write(String s) {

		File file = new File("source\\note.txt");
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(file, true);
			writer.write(s + ";" + "\r");

		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {

			try {

				if (writer != null)
					writer.close();

			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}
	}

}
