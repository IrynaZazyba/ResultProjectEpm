package by.javatr.library.runner;

import by.javatr.library.controller.Controller;
import by.javatr.library.runner.scanner.DataScanner;
import by.javatr.library.service.util.Request;
import by.javatr.library.service.util.Response;
import by.javatr.library.controller.command.CommandName;

public class Runner {

    public static void main(String[] args) {

        boolean flag = false;
        Controller controller = new Controller();
        while (!flag) {

            System.out.println(controller.getMenu());
            int action = DataScanner.enterIntFromConsole();

            if(action==CommandName.EXIT.ordinal()){
                flag=true;
            }

            while (!flag) {
                Request request = new Request();
                Response response = controller.checkParameters(action);

                if (action == CommandName.SIGN_OUT.ordinal()
                        && response.getParameters() != null
                        && response.getParameters().get("SIGN_OUT").equals("true")) {
                    flag = true;
                }

                String command = response.getCommandName();
                if (command != null) {
                    Request requestToRemove = new Request();
                    requestToRemove.addParameter("commandName", command);
                    Response responseInner = controller.executeTask(requestToRemove);
                    ParameterResolver.printExecutiveResponse(responseInner);
                }
                ParameterResolver.printResponse(response, request);

                response = controller.executeTask(request);
                ParameterResolver.printExecutiveResponse(response);

                    if (response.getStatus()&&!flag) {
                    System.out.println(controller.getMenu());
                    action = DataScanner.enterIntFromConsole();

                }
            }

        }
    }

}
