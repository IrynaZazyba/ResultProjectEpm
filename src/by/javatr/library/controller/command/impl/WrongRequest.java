package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.util.Response;
import java.util.Map;

public class WrongRequest implements Command {


    @Override
    public Response checkParameters() {
        Response response =new Response();
        response.setStatus(false);
        return response;
    }

    @Override
    public Response execute(Map<String, String> parameters) {
        Response response =getUnsuccessfulResponse("Your request doesn't exist.");
        response.setStatus(true);
        return response;
    }
}
