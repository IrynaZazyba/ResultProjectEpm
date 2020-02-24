package by.javatr.library.service.util;

import java.util.*;

public class Request {

    private int command;
    private Map<String, String> parameters = new LinkedHashMap<>();

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
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
        result = result * prime + command;
        result = result * prime + (parameters==null?0:parameters.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj.getClass() == getClass()) return false;
        Request other = (Request) obj;
        if (this.command != other.command) return false;
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
                + "command " + command
                + ", parameters" + sb;
    }
}
