package com.peergreen.example.vaadin.internal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Requires;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

/**
 * @author Mohammed Boukada
 */

// iPOJO annotations
@Component
@Instantiate
// Vaadin Servlet annotations
@WebServlet(asyncSupported = true)
@VaadinServletConfiguration(ui = BaseVaadinUI.class, productionMode = false)
public class BaseVaadinServlet extends VaadinServlet {

    @Requires
    private UIProvider provider;

    public BaseVaadinServlet(@Requires HttpService httpService) throws ServletException, NamespaceException {
        httpService.registerServlet("/vaadin", this, null, null);
    }

    @Override
    protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) throws ServiceException {
        VaadinServletService servletService = super.createServletService(deploymentConfiguration);
        servletService.addSessionInitListener(new SessionInitListener() {
            @Override
            public void sessionInit(SessionInitEvent sessionInitEvent) throws ServiceException {
                sessionInitEvent.getSession().addUIProvider(provider);
            }
        });
        return servletService;
    }
}
