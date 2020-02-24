package by.javatr.library.service.util;

import java.util.*;


public class Response {

    private boolean status;
    private String commandName;
    private Map<String, String> parameters = new LinkedHashMap<>();


    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }


    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = result * prime + Boolean.hashCode(status);
        result = result * prime + (commandName == null ? 0 : 1);
        result = result * prime + (parameters==null?0:parameters.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() == getClass()) return false;
        Response other = (Response) obj;
        if (this.status != other.status) return false;
        if (this.commandName.equals(other.commandName)) return false;
        if (this.parameters == null) {
            if (other.parameters != null) return false;
        } else {
            if (!this.parameters.equals(other.parameters)) return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator<Map.Entry<String, String>> itr1 = parameters.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry<String, String> entry = itr1.next();
            sb.append(entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
        }

        return getClass().getName() + '@'
                + "status=" + status
                + ", commandName=" + commandName
                + ", parameters=" + sb;
    }

}
