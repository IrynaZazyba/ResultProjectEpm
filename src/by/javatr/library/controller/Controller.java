package by.javatr.library.controller;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.util.MenuResolver;
import by.javatr.library.service.util.Request;
import by.javatr.library.service.util.Response;
import by.javatr.library.dao.util.Role;
import by.javatr.library.controller.command.CommandName;

public class Controller {


    private final CommandProvider provider = new CommandProvider();
    private String commandName;

    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static ClientService clientService = serviceFactory.getClientService();


    public Response checkParameters(int action) {

        Response response;
        commandName = transformAction(action);

        checkIfCommandNameResolved();

        Command command = provider.getCommand(commandName);
        response = command.checkParameters();

        return response;

    }

    public Response executeTask(Request request) {
        Command executionCommand;
        String command = request.getParameters().get("commandName");

        if (command != null) {
            executionCommand = provider.getCommand(command);
            Response response;
            response = executionCommand.execute(request.getParameters());
            return response;
        }

        executionCommand = provider.getCommand(commandName);

        Response response;
        response = executionCommand.execute(request.getParameters());
        commandName = null;

        return response;
    }

    private String transformAction(int action) {
        CommandName[] commandNames = CommandName.values();
        for (int i = 0; i < commandNames.length; i++) {
            if (i == action) {
                return commandNames[i].toString();
            }
        }
        return CommandName.WRONG_REQUEST.toString();
    }


    public String getMenu() {
        return MenuResolver.getMenu();
    }

    private void checkIfCommandNameResolved() {
        if (clientService.getAuthorizedUser() == null && !commandName.equals(CommandName.SIGN_IN.toString())
                && !commandName.equals(CommandName.REGISTRATION.toString())) {
            commandName = CommandName.WRONG_REQUEST.toString();
        }

        if (clientService.getAuthorizedUser() != null
                && clientService.getAuthorizedUser().getRole() != Role.ADMIN
                && !commandName.equals(CommandName.GET_CATALOG.toString())
                && !commandName.equals(CommandName.SIGN_OUT.toString())
                && !commandName.equals(CommandName.FIND_BOOK.toString())) {
            commandName = CommandName.WRONG_REQUEST.toString();
        }


        if (clientService.getAuthorizedUser() != null &&
                (commandName.equals(CommandName.SIGN_IN.toString())
                        || commandName.equals(CommandName.REGISTRATION.toString()))) {
            commandName = CommandName.WRONG_REQUEST.toString();
        }
    }

}
