package by.library.view;

import by.library.controller.Controller;
import by.library.controller.exception.ControllerException;
import by.library.dao.exception.DAOException;
import by.library.service.exception.UserServiceException;
import by.library.view.action.Action;
import by.library.view.action.impl.SignOut;
import by.library.view.exception.ViewException;

public class Runner {

	public static void main(String[] args) throws UserServiceException, DAOException{//Õ≈À‹«ﬂ!!!!!!

		try {
			Controller controller = Controller.getInstance();
			UserActionProvider provider = new UserActionProvider();
		
			String login = null;
			int startCommand = Output.getStartCommand();
			if(startCommand == 1)
			{
				String info = Output.getAuthorizationInfo();
				String responseSignIn = controller.executeTask("sign_in" + "--" + info);
			
				String[] responseInfo = responseSignIn.split("--");
				login = responseInfo[2];
			 
				if(responseInfo[0].equals("Hello"))
				 {
				 	System.out.println("Hello, " + responseInfo[1] + "!!!\n");
				 	
				 }
			}
			else if(startCommand == 2){
				String infoOfUser = Output.getInfoOfUser();
				String responseRegister = controller.executeTask("register" + "--" + infoOfUser);
			 
				String[] responseInfo = responseRegister.split("--");
				login = responseInfo[2];

				if(responseInfo[0].equals("Welcome"))
				{
					System.out.println("You are registered successfully!!"
				 		+ "Welcome, " + responseInfo[1] + "!!!\n");
				}
			}
			else { 
				System.out.println("Bye!");
				System.exit(0);
			}
		 
			int commandNumber = Output.getCommand(login);
		 
			Action executionAction = provider.getAction(commandNumber);
		
			if(executionAction.getClass() == new SignOut().getClass())
			{
			 	SignOut executionAction1 = new SignOut();
			 	executionAction1.setLogin(login);
				String response = executionAction1.doAction();
				System.out.println(response);
			 }
			else {
				String response = executionAction.doAction();
				System.out.println(response);
			}
 
	}catch(ControllerException | ViewException e) {
		System.out.print(e.getMessage());
		e.printStackTrace();
	}
	}
}
