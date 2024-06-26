package de.climateguard.application.views.listall;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import de.climateguard.application.views.MainLayout;
import de.climateguard.application.mqtt.MqttMessageDetails;
import de.climateguard.application.mqtt.ListAllMQTTManager;

/**
 * This class represents the view for listing all MQTT messages.
 * It initializes the view components, sets up the MQTT manager, and handles the
 * UI interactions.
 */
@PageTitle("List All")
@Route(value = "list-all", layout = MainLayout.class)
public class ListAllView extends Composite<VerticalLayout> {

    private ListAllMQTTManager mqttManager;
    private Grid<MqttMessageDetails> mqttGrid;
    private List<MqttMessageDetails> messageList = new ArrayList<>();

    private HorizontalLayout layoutRow = new HorizontalLayout();
    private HorizontalLayout layoutRow2 = new HorizontalLayout();
    private VerticalLayout layoutColumn2 = new VerticalLayout();
    private HorizontalLayout layoutRow3 = new HorizontalLayout();
    private VerticalLayout layoutColumn3 = new VerticalLayout();

    /**
     * Constructor for ListAllView.
     * Initializes the view and sets up the MQTT manager.
     */
    public ListAllView() {
        initializeView();
        setupMqttManager();

        H1 h1 = new H1();
        TextField textField = new TextField();
        Button buttonPrimary = new Button();

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        h1.setText("ClimateGuard");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        h1.setWidth("max-content");

        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");

        layoutColumn2.getStyle().set("flex-grow", "1");
        textField.setLabel("Topic:");
        textField.setWidth("100%");
        textField.setHelperText("Enter the topic to subscribe to");
        textField.setPlaceholder("# (all Topics)");

        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutColumn2.setWidth("min-content");
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("min-content");

        buttonPrimary.setText("Set");
        buttonPrimary.getStyle().set("flex-grow", "1");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.setWidth("100%");

        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn3.setAlignItems(FlexComponent.Alignment.CENTER);

        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(layoutRow3);
        layoutRow3.add(buttonPrimary);
        layoutRow2.add(layoutColumn3);

        buttonPrimary.addClickListener(e -> {
            String newTopic = textField.getValue();
            if (newTopic != null && !newTopic.isEmpty()) {
                clearGrid();
                subscribeToTopic(newTopic);
            }
        });
    }

    /**
     * Initializes the view components, including the grid for displaying MQTT
     * messages.
     */
    private void initializeView() {
        mqttGrid = new Grid<>(MqttMessageDetails.class);

        mqttGrid.removeAllColumns();

        mqttGrid.addColumn(MqttMessageDetails::getTopic).setHeader("Topic").setTextAlign(ColumnTextAlign.CENTER);
        mqttGrid.addColumn(MqttMessageDetails::getPayload).setHeader("Payload").setTextAlign(ColumnTextAlign.CENTER);
        mqttGrid.addColumn(MqttMessageDetails::getTimestamp).setHeader("Timestamp")
                .setTextAlign(ColumnTextAlign.CENTER);

        mqttGrid.setHeight("80vh");

        layoutColumn3.add(mqttGrid);
    }

    /**
     * Sets up the MQTT manager and subscribes to the default topic.
     */
    private void setupMqttManager() {
        try {
            this.mqttManager = new ListAllMQTTManager(this);
            if (mqttManager != null) {
                subscribeToTopic("#");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the grid by removing all messages from the message list.
     */
    private void clearGrid() {
        messageList.clear();
        mqttGrid.setItems(messageList);
    }

    /**
     * Subscribes to the specified MQTT topic.
     *
     * @param topic the MQTT topic to subscribe to
     */
    private void subscribeToTopic(String topic) {
        try {
            mqttManager.changeSubscription(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new MQTT message to the grid.
     *
     * @param message the MQTT message to add
     */
    public void addMessageToGrid(MqttMessageDetails message) {
        messageList.add(message);

        getUI().ifPresent(ui -> ui.access(() -> {
            mqttGrid.setItems(new ArrayList<>(messageList));
            mqttGrid.getDataProvider().refreshAll();
        }));
    }
}
