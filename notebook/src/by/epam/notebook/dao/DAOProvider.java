package by.epam.notebook.dao;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();

	private NotebookDao notebookDAO = new NotebookImplDAO();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public NotebookDao getNotebookDAO() {
		return notebookDAO;
	}

	public void setNotebookDAO(NotebookDao notebookDAO) {
		this.notebookDAO = notebookDAO;
	}

}
