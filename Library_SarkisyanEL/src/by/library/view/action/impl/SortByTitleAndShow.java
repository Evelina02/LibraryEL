package by.library.view.action.impl;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class SortByTitleAndShow implements Action {

	private Controller controller = Controller.getInstance();

	@Override
	public String doAction() throws ViewException {
		
		String response = null;
		try {
			response = controller.executeTask("sort_by_title" + "--");
		}catch(ControllerException e) {
			throw new ViewException("Error during sorting by title!", e);
		}
			return response;
	}
}
