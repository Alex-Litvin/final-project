package ua.training.controller;

import ua.training.controller.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

class ControllerHelper {

    private static Map<String, Command> commandMap = new HashMap<>();

    private static ControllerHelper ourInstance = new ControllerHelper();

    static ControllerHelper getInstance() {
        return ourInstance;
    }

    private ControllerHelper() {
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("home", new HomeCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("localization", new LocalizationCommand());
        commandMap.put("showUniversities", new ShowUniversitiesCommand());
    }

    Command getCommand(HttpServletRequest request) {
        String key = request.getParameter("command");
        return commandMap.get(key);
    }
}
