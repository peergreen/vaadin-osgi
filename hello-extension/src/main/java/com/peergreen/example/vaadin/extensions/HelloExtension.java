package com.peergreen.example.vaadin.extensions;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Mohammed Boukada
 */
@Component
@Instantiate
@Provides(specifications = com.vaadin.ui.Component.class)
public class HelloExtension extends VerticalLayout {

    public HelloExtension() {
        setSizeFull();
        addComponent(new Label("Hello world :-)"));
    }
}
