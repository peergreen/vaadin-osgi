package com.peergreen.example.vaadin.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.Factory;
import org.apache.felix.ipojo.MissingHandlerException;
import org.apache.felix.ipojo.UnacceptableConfiguration;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

/**
 * @author Mohammed Boukada
 */
@Component
@Instantiate
@Provides(specifications = UIProvider.class)
public class BaseVaadinUIProvider extends UIProvider {

    @Requires(from = "com.peergreen.example.vaadin.internal.BaseVaadinUI")
    private Factory factory;

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent uiClassSelectionEvent) {
        return BaseVaadinUI.class;
    }

    @Override
    public UI createInstance(UICreateEvent event) {
        BaseVaadinUI ui = new BaseVaadinUI();
        Dictionary<String, Object> props = new Hashtable<>();
        props.put("instance.object", ui);
        try {
            factory.createComponentInstance(props);
        } catch (UnacceptableConfiguration | MissingHandlerException | ConfigurationException e) {
            e.printStackTrace();
        }
        return ui;
    }
}
