package by.library.view.action.impl;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.view.Output;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class DeleteBook implements Action {

	private Controller controller = Controller.getInstance();

	@Override
	public String doAction() throws ViewException{
		
		String title = Output.getTitleFromConsole();
		String author = Output.getAuthorFromConsole();
		String response = null;
		
		try {
			response = controller.executeTask("delete_book" + "--" + title +
				"--" + author);
		}catch(ControllerException e) {
			throw new ViewException("Error during deleting book", e);
		}
		return response;

	}

}
