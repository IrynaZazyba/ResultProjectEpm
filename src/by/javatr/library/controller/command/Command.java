package by.javatr.library.controller.command;

import by.javatr.library.service.util.Response;

import java.util.Map;

public interface Command {

    Response checkParameters();

    Response execute(Map<String, String> parameters);

    default Response getUnsuccessfulResponse(String message) {
        Response response = new Response();
        response.addParameter("message", message);
        response.setStatus(false);
        return response;
    }
}
