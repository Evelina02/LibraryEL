package by.library.view.action.impl;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.view.Output;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class SearchBookByTitle implements Action {

	private Controller controller = Controller.getInstance();

	@Override
	public String doAction()  throws ViewException {
		 
		String title = Output.getTitleFromConsole();
		String response = null;
		try {
			response = controller.executeTask("search_by_title--" + title);
		}catch(ControllerException e) {
			throw new ViewException("Error during searching book by title", e);
		}
			return response;
	}

}
