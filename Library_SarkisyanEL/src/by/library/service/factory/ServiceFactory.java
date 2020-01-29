package by.library.service.factory;

import by.library.service.ClientService;
import by.library.service.LibraryService;
import by.library.service.impl.ClientServiceImpl;
import by.library.service.impl.LibraryServiceImpl;

public final class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final ClientService clientService = new ClientServiceImpl();
	private final LibraryService libraryService = new LibraryServiceImpl();

	private ServiceFactory() {}

	public static ServiceFactory getInstance()
	{
		return instance;
	}
	
	public ClientService getClientService()
	{
		return clientService;
	}
	
	public LibraryService getLibraryService()
	{
		return libraryService;
	}
}
