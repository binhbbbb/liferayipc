package com.vaadin.addon.ipcforliferay.demo.receiver;

import com.vaadin.addon.ipcforliferay.LiferayIPC;
import com.vaadin.addon.ipcforliferay.demo.data.Person;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEvent;
import com.vaadin.addon.ipcforliferay.event.LiferayIPCEventListener;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;

public class ReceiverView extends CustomComponent {

    @AutoGenerated
    private AbsoluteLayout mainLayout;
    @AutoGenerated
    private LiferayIPC liferayIPC_1;
    @AutoGenerated
    private Table table_1;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    private BeanItemContainer<Person> personContainer;

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

    /**
     * The constructor should first build the main layout, set the composition
     * root and then do any custom initialization.
     * 
     * The constructor will not be automatically regenerated by the visual
     * editor.
     */
    public ReceiverView() {
        buildMainLayout();
        setCompositionRoot(mainLayout);

        // Create initial container
        createContainer();

        // Initialize IPC
        initializeIPC();
    }

    private void initializeIPC() {
        liferayIPC_1.addListener("newPerson", new LiferayIPCEventListener() {

            public void eventReceived(LiferayIPCEvent event) {
                String[] fields = event.getData().split(";");
                if (fields.length != 3) {
                    getWindow().showNotification(
                            "Received event whose data could not be parsed: "
                                    + event.getData());
                    return;
                }

                String firstName = fields[0];
                String lastName = fields[1];
                int age = Integer.parseInt(fields[2]);
                addUser(firstName, lastName, age);
            }
        });

    }

    private void createContainer() {
        personContainer = new BeanItemContainer<Person>(Person.class);
        table_1.setContainerDataSource(personContainer);
        addUser("Artur", "Signell", 30);

    }

    void addUser(String firstName, String lastName, int age) {
        personContainer.addBean(new Person(firstName, lastName, age));
    }

    @AutoGenerated
    private AbsoluteLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new AbsoluteLayout();
        mainLayout.setWidth("100%");
        mainLayout.setHeight("240px");

        // top-level component properties
        setWidth("100.0%");
        setHeight("240px");

        // table_1
        table_1 = new Table();
        table_1.setCaption("Current users");
        table_1.setImmediate(false);
        table_1.setWidth("100.0%");
        table_1.setHeight("100.0%");
        mainLayout.addComponent(table_1,
                "top:16.0px;right:0.0px;bottom:0.0px;left:0.0px;");

        // liferayIPC_1
        liferayIPC_1 = new LiferayIPC();
        liferayIPC_1.setImmediate(false);
        liferayIPC_1.setWidth("-1px");
        liferayIPC_1.setHeight("-1px");
        mainLayout.addComponent(liferayIPC_1, "top:-10.0px;left:340.0px;");

        return mainLayout;
    }

}
