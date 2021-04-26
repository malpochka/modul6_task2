package by.epam.notebook.dao;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();

	private NotebookImpDao notebookDAO = new NotebookDAO();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public NotebookImpDao getNotebookDAO() {
		return notebookDAO;
	}

	public void setNotebookDAO(NotebookImpDao notebookDAO) {
		this.notebookDAO = notebookDAO;
	}

}
