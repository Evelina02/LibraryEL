package by.library.view.action.impl;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.view.Output;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class AddBook implements Action {

	private Controller controller = Controller.getInstance();

	@Override
	public String doAction() throws ViewException{
		 
		System.out.println("Please, enter information about book");

		String title = Output.getTitleFromConsole();
		String author = Output.getAuthorFromConsole();
		int year = Output.getNumberOfPagesFromConsole();
		int numberOfPages = Output.getNumberOfPagesFromConsole();
		String info = Output.getInfoFromConsole();
		String response = null;
		
		try {
			response = controller.executeTask("add_book" + "--" + title +
				"--" + author + "--" + year + "--" + numberOfPages + "--" + info);
		}catch(ControllerException e) {
			throw new ViewException("Error during adding book", e);
		}
		return response;
	}

}
