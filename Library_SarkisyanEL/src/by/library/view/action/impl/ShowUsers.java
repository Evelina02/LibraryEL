package by.library.view.action.impl;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class ShowUsers implements Action {

	private Controller controller = Controller.getInstance();

	@Override
	public String doAction() throws ViewException {
		
		String response = null;

		try {	
			response = controller.executeTask("show_users--");
		}catch(ControllerException e) {
			throw new ViewException("Error during showing users", e);
		}
			return response;
	}

}
