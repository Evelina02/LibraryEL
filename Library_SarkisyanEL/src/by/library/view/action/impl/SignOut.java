package by.library.view.action.impl;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class SignOut implements Action {

	private Controller controller = Controller.getInstance();
	private String login;
	
	@Override
	public String doAction() throws ViewException{
		
		String response = null;
		try {
			response = controller.executeTask("sign_out" + "--" + login);
		}catch(ControllerException e) {
			throw new ViewException("Error during signing out!", e);
		}
		return response;

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
}
