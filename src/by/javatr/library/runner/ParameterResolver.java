package by.javatr.library.runner;

import by.javatr.library.runner.scanner.DataScanner;
import by.javatr.library.service.util.Request;
import by.javatr.library.service.util.Response;
import java.util.Map;


public class ParameterResolver {


    public static void printResponse(Response response, Request request) {

        if (response.getStatus()) {
            Map<String, String> params=response.getParameters();
            String param;
            for (Map.Entry<String,String> entry :params.entrySet()) {
                System.out.println(entry.getKey() + ": ");
                param = DataScanner.enterStringFromConsole();
                request.addParameter(entry.getKey(), param);
            }
        }
    }

    public static void printExecutiveResponse(Response response) {
        System.out.println(response.getParameters().get("message"));
        response.setStatus(true);
    }
}


