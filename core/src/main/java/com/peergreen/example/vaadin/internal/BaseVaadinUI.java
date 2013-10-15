package com.peergreen.example.vaadin.internal;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Unbind;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Mohammed Boukada
 */
@org.apache.felix.ipojo.annotations.Component
@Push
@PreserveOnRefresh
public class BaseVaadinUI extends UI {

    private TabSheet tabSheet = new TabSheet();

    public BaseVaadinUI() {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        content.setMargin(true);
        content.setSpacing(true);
        setContent(content);

        getPage().setTitle("Vaadin and OSGi");
        Label title = new Label("Simple Vaadin integration with OSGi");
        content.addComponent(title);

        tabSheet.setSizeFull();
        content.addComponent(tabSheet);
        content.setExpandRatio(tabSheet, 1.5f);
    }

    @Bind(aggregate = true, optional = true)
    public void bindExtension(final Component extension) {
        doAccess(new Runnable() {
            @Override
            public void run() {
                tabSheet.addTab(extension, "Extension");
            }
        });
    }

    @Unbind
    public void unbindExtension(final Component extension) {
        doAccess(new Runnable() {
            @Override
            public void run() {
                tabSheet.removeComponent(extension);
            }
        });
    }

    public void doAccess(Runnable runnable) {
        if (isAttached()) {
            super.access(runnable);
        } else {
            runnable.run();
        }
    }
}
