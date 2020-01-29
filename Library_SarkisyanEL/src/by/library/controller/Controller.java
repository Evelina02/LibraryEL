package by.library.controller;

import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;

public final class Controller {
	
	private final CommandProvider provider = new CommandProvider();
	private final String paramDelimeter = "--";
	private static Controller instance;
	
	private Controller() {};
	public static Controller getInstance() {
		if(instance == null)
			instance = new Controller();
		return instance;
	}
	
	public String executeTask(String request) throws ControllerException{
		
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
		String commandName;
		Command executionCommand;
		
		commandName = request.substring(0, request.indexOf(paramDelimeter));
		executionCommand = provider.getCommand(commandName);
		
		String response = executionCommand.execute(request);			
		
		return response;
	}
}
