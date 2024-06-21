package de.climateguard.application.views.history;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import de.climateguard.application.views.MainLayout;
import java.util.ArrayList;
import java.util.List;

@PageTitle("History")
@Route(value = "history", layout = MainLayout.class)
public class HistoryView extends Composite<VerticalLayout> {

    public HistoryView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        DatePicker datePicker = new DatePicker();
        Button buttonPrimary = new Button();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H1 h12 = new H1();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        h1.setText("ClimateGuard");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        h1.setWidth("max-content");

        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");

        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("min-content");
        comboBox.setLabel("ClimateGuard Station:");
        comboBox.setWidth("min-content");
        setComboBoxStationData(comboBox);
        comboBox2.setLabel("Data:");
        comboBox2.setWidth("min-content");
        setComboBoxData(comboBox2);
        datePicker.setLabel("Date:");
        datePicker.setWidth("min-content");
        buttonPrimary.setText("Request");
        buttonPrimary.setWidth("100%");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn3.setAlignItems(Alignment.CENTER);
        h12.setText("GRID");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.CENTER, h12);
        h12.setWidth("max-content");

        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(comboBox2);
        layoutColumn2.add(datePicker);
        layoutColumn2.add(buttonPrimary);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(h12);
    }

    record Item(String value, String label, Boolean disabled) {
    }

    private void setComboBoxStationData(ComboBox comboBox) {
        List<Item> Items = new ArrayList<>();
        Items.add(new Item("A8:42:E3:A9:55:38", "ClimateGuard", null));
        comboBox.setItems(Items);
        comboBox.setItemLabelGenerator(item -> ((Item) item).label());
    }

    private void setComboBoxData(ComboBox comboBox) {
        List<Item> Items = new ArrayList<>();
        Items.add(new Item("All", "All", null));
        Items.add(new Item("Temperatures", "Temperatures", null));
        Items.add(new Item("Humidity", "Humidity", null));
        Items.add(new Item("Pressure", "Pressure", null));
        Items.add(new Item("LightIntensity", "Light Intensity", null));
        Items.add(new Item("AirQuality", "Air Quality", null));
        // sampleItems.add(new Item("RainSensor", "Rain Sensor", null));
        // sampleItems.add(new Item("UVIntensity", "UV Intensity", null));
        // sampleItems.add(new Item("SoundLevel", "Sound Level", null));
        comboBox.setItems(Items);
        comboBox.setItemLabelGenerator(item -> ((Item) item).label());
    }
}
