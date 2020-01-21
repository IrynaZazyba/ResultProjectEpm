package by.javatr.library.factory;

import by.javatr.library.service.ClientService;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.impl.ClientServiceImpl;
import by.javatr.library.service.impl.LibraryServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }
}
