package by.javatr.library.service.util;

import by.javatr.library.bean.User;
import by.javatr.library.dao.util.Role;
import by.javatr.library.service.factory.ServiceFactory;
import by.javatr.library.service.ClientService;
import by.javatr.library.controller.command.CommandName;


public class MenuResolver {

    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static ClientService clientService = serviceFactory.getClientService();

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("~~~~~~~~~~~~~~~~~~~~~~~\n");
        User user = clientService.getAuthorizedUser();

        if (user == null) {
            sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");

            CommandName[] commandNames = CommandName.values();
            for (int i = 0; i < commandNames.length; i++) {
                if (commandNames[i].getTypeOfMenu().equals("Start")) {
                    sb.append("| ").append(commandNames[i].ordinal()).append("   ").append(commandNames[i]).append("\n");
                }
            }

            sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            return sb.toString().trim();
        }

        if (user.getRole() == Role.ADMIN) {
            CommandName[] commandNames = CommandName.values();
            for (int i = 0; i < commandNames.length; i++) {
                if (!commandNames[i].getTypeOfMenu().equals("Start") && !commandNames[i].getTypeOfMenu().equals("System")) {
                    sb.append("| ").append(commandNames[i].ordinal()).append("   ").append(commandNames[i]).append("\n");
                }
            }

        } else {
            CommandName[] commandNames = CommandName.values();
            for (int i = 0; i < commandNames.length; i++) {
                if (commandNames[i].getTypeOfMenu().equals("User")) {
                    sb.append("| ").append(commandNames[i].ordinal()).append("   ").append(commandNames[i]).append("\n");
                }
            }
        }
        sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        return sb.toString().trim();
    }


}






