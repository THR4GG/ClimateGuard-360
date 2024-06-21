package de.climateguard.application.views.climateguard;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import de.climateguard.application.views.MainLayout;
import de.climateguard.application.mqtt.MQTTManager;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.ArrayList;
import java.util.List;

@PageTitle("ClimateGuard")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ClimateGuardView extends Composite<VerticalLayout> {

    private TextField temperatureField;
    private TextField humidityField;
    private TextField pressureField;
    private TextField airQualityField;
    private TextField lightIntensityField;
    private TextField rainSensorField;

    private MQTTManager mqttManager;

    public ClimateGuardView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        ComboBox<String> comboBox = new ComboBox<>();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        ComboBox<String> comboBox2 = new ComboBox<>();

        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        VerticalLayout layoutColumn6 = new VerticalLayout();

        HorizontalLayout layoutRow4 = new HorizontalLayout();
        VerticalLayout layoutColumn7 = new VerticalLayout();
        VerticalLayout layoutColumn8 = new VerticalLayout();
        VerticalLayout layoutColumn9 = new VerticalLayout();

        // Textfelder initialisieren
        temperatureField = createTextField("Temperature:");
        humidityField = createTextField("Humidity:");
        pressureField = createTextField("Pressure:");
        airQualityField = createTextField("AirQuality:");
        lightIntensityField = createTextField("LightIntensity:");
        rainSensorField = createTextField("Raining:");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow.addClassName(Gap.XLARGE);
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
        layoutColumn2.setWidth("min-content");
        comboBox.setLabel("ClimateGuard Station:");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, comboBox);
        comboBox.setWidth("min-content");
        setComboBoxData(comboBox);

        comboBox2.setLabel("Mode:");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, comboBox2);
        comboBox2.setWidth("min-content");
        setComboBoxModeData(comboBox2);

        buttonPrimary.setText("Auswählen");
        buttonPrimary.setWidth("100%");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonSecondary.setText("Change Mode");
        buttonSecondary.setWidth("100%");
        buttonSecondary.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");

        layoutRow3.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");

        layoutColumn4.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        layoutColumn4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn4.add(temperatureField);

        layoutColumn5.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        layoutColumn5.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn5.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn5.add(humidityField);

        layoutColumn6.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn6);
        layoutColumn6.setWidth("100%");
        layoutColumn6.getStyle().set("flex-grow", "1");
        layoutColumn6.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn6.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn6.add(pressureField);

        layoutRow4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");

        layoutColumn7.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutColumn7);
        layoutColumn7.setWidth("100%");
        layoutColumn7.getStyle().set("flex-grow", "1");
        layoutColumn7.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn7.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn7.add(airQualityField);

        layoutColumn8.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutColumn8);
        layoutColumn8.setWidth("100%");
        layoutColumn8.getStyle().set("flex-grow", "1");
        layoutColumn8.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn8.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn8.add(lightIntensityField);

        layoutColumn9.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutColumn9);
        layoutColumn9.setWidth("100%");
        layoutColumn9.getStyle().set("flex-grow", "1");
        layoutColumn9.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn9.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn9.add(rainSensorField);

        getContent().add(layoutRow);
        layoutRow.add(h1);

        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(comboBox2);
        layoutColumn2.add(buttonSecondary);

        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(layoutRow3);
        layoutRow3.add(layoutColumn4);
        layoutRow3.add(layoutColumn5);
        layoutRow3.add(layoutColumn6);

        layoutColumn3.add(layoutRow4);
        layoutRow4.add(layoutColumn7);
        layoutRow4.add(layoutColumn8);
        layoutRow4.add(layoutColumn9);

        try {
            mqttManager = new MQTTManager("tcp://climateguard-360.local:1883", "Vaadin", this);
            buttonPrimary.addClickListener(event -> {
                String selectedValue = comboBox.getValue();
                if (selectedValue != null) {
                    try {
                        mqttManager.subscribeToTopics(selectedValue);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        try {
            buttonSecondary.addClickListener(event -> {
                String selectedValue = comboBox2.getValue();
                if (selectedValue != null) {
                    try {
                        mqttManager.publishMessage("A8:42:E3:A9:55:38/ModeChange", selectedValue);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextField createTextField(String label) {
        TextField textField = new TextField(label);
        textField.setWidth("min-content");
        textField.setReadOnly(true);
        return textField;
    }

    public void updateField(String topic, String value) {
        getUI().ifPresent(ui -> ui.access(() -> {
            if (topic.endsWith("/Temperature")) {
                temperatureField.setValue(value + " °C");
            } else if (topic.endsWith("/Humidity")) {
                humidityField.setValue(value + " %");
            } else if (topic.endsWith("/Pressure")) {
                pressureField.setValue(value + " hPa");
            } else if (topic.endsWith("/AirQuality")) {
                airQualityField.setValue(value + " ppm");
            } else if (topic.endsWith("/LightIntensity")) {
                lightIntensityField.setValue(value + " Lux");
            } else if (topic.endsWith("/RainSensor")) {
                rainSensorField.setValue(Boolean.parseBoolean(value) ? "Es regnet gerade" : "Es regnet nicht");
            }
            // Entfernen von ui.push(), da es ohne diese Zeile auch ohne expliziten Push
            // funktionieren sollte
        }));
    }

    record Item(String value, String label, Boolean disabled) {
    }

    private void setComboBoxData(ComboBox<String> comboBox) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("A8:42:E3:A9:55:38", "ClimateGuard", null));
        comboBox.setItems(items.stream().map(Item::value).toList());
        comboBox.setHelperText("Select a ClimateGuard Station");
        comboBox.setTooltipText("Station");
        comboBox.setItemLabelGenerator(item -> items.stream()
                .filter(sample -> sample.value().equals(item))
                .findFirst()
                .map(Item::label)
                .orElse("Unknown"));
    }

    private void setComboBoxModeData(ComboBox<String> comboBox) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("INDOOR", "Indoor", null));
        items.add(new Item("OUTDOOR", "Outdoor", null));
        items.add(new Item("HYBRID", "Hybrid", null));
        comboBox.setItems(items.stream().map(Item::value).toList());
        comboBox.setHelperText("Select a Mode");
        comboBox.setTooltipText("Mode");
        comboBox.setItemLabelGenerator(item -> items.stream()
                .filter(sample -> sample.value().equals(item))
                .findFirst()
                .map(Item::label)
                .orElse("Unknown"));
    }
}
