package by.library.view;

import java.util.HashMap;
import java.util.Map;

import by.library.controller.CommandName;
import by.library.controller.exception.ControllerException;
import by.library.view.action.Action;
import by.library.view.action.impl.*;
import by.library.view.exception.ViewException;


public final class UserActionProvider {

	private final Map <Integer, Action> repository = new HashMap<>();
	
	UserActionProvider(){
		
		repository.put(1, new SignOut());
		repository.put(2, new ShowBooks());
		repository.put(3, new SearchBookByTitle());
		repository.put(4, new SearchBookByAuthor());
		repository.put(5, new SortByTitleAndShow());
		repository.put(6, new SortByAuthorAndShow());
		repository.put(7, new SortByYearAndShow());

		repository.put(8, new AddBook());
		repository.put(9, new DeleteBook());
		repository.put(10, new ClearLibrary());
		repository.put(11, new ShowUsers());

	}
	
	public Action getAction(int number) throws ViewException
	{
			Action action = null;
			
			try {
				action = repository.get(number);
			} catch(IllegalArgumentException | NullPointerException e) {
				throw new ViewException("Error! Incorrect request");
			}
			
			return action;
	}
}

