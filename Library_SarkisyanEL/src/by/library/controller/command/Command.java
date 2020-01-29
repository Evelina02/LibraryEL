package by.library.controller.command;

import by.library.controller.exception.ControllerException;

public interface Command {

	public String execute(String request) throws ControllerException;
}
