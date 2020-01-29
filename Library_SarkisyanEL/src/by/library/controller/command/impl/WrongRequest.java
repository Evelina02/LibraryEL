package by.library.controller.command.impl;

import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;

public class WrongRequest implements Command {

	@Override
	public String execute(String request) throws ControllerException {

		String response = "Error! Request is wrong";

		return response;
	}

}
