package by.training.controller;

import by.training.di.ServiceCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {
    private ServiceCreator serviceCreator;

    public ServiceCreator getServiceCreator() {
        return serviceCreator;
    }

    public void setServiceCreator(ServiceCreator serviceCreator) {
        this.serviceCreator = serviceCreator;
    }

    abstract public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
