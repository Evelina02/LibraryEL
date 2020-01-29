package by.library.controller;

import java.util.HashMap;
import java.util.Map;
import by.library.controller.command.Command;
import by.library.controller.command.impl.*;
import by.library.controller.exception.ControllerException;

public final class CommandProvider {

	private final Map <CommandName, Command> repository = new HashMap<>();
	
	CommandProvider(){
		
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.SIGN_OUT, new SignOut());
		repository.put(CommandName.REGISTER, new Registration());
		repository.put(CommandName.ADD_BOOK, new AddBook());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.CLEAR_LIBRARY, new ClearLibrary());
		repository.put(CommandName.DELETE_BOOK, new DeleteBook());
		repository.put(CommandName.SEARCH_BY_TITLE, new SearchByTitle());
		repository.put(CommandName.SEARCH_BY_AUTHOR, new SearchByAuthor());
		repository.put(CommandName.SEARCH_BY_TITLE_AND_AUTHOR, new SearchByTitleAndAuthor());
		repository.put(CommandName.SORT_BY_AUTHOR, new SortByAuthorAndShow());
		repository.put(CommandName.SORT_BY_TITLE, new SortByTitleAndShow());
		repository.put(CommandName.SORT_BY_YEAR, new SortByYearAndShow());
		repository.put(CommandName.SHOW_USERS, new ShowUsers());

	}
	
	public Command getCommand(String name) throws ControllerException
	{
			CommandName commandName = null;
			Command command = null;
			
			try {
				commandName = CommandName.valueOf(name.toUpperCase());
				command = repository.get(commandName);
			} catch(IllegalArgumentException | NullPointerException e) {
				command = repository.get(CommandName.WRONG_REQUEST);
				throw new ControllerException("Error! Incorrect request");
			}
			return command;
	}
}
