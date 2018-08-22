package ua.training.model.service.implementation;

import ua.training.model.service.ServiceFactory;
import ua.training.model.service.SpecialityService;
import ua.training.model.service.UniversityService;
import ua.training.model.service.UserService;

public class ServiceFactoryImpl implements ServiceFactory {
    private static ServiceFactoryImpl instance;

    private UserService userService;
    private UniversityService universityService;
    private SpecialityService specialityService;

    private ServiceFactoryImpl() {
        this.userService = new UserServiceImpl();
        this.universityService = new UniversityServiceImpl();
        this.specialityService = new SpecialityServiceImpl();
    }

    public static ServiceFactoryImpl getInstance() {
        if (instance == null) {
            return new ServiceFactoryImpl();
        }
        return instance;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public UniversityService getUniversityService() {
        return universityService;
    }

    @Override
    public SpecialityService getSpecialityService() {
        return specialityService;
    }


}
