package de.climateguard.application.views.history;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

import de.climateguard.application.components.RadarChartComponent;
import de.climateguard.application.data.WeatherStation;
import de.climateguard.application.data.repository.WeatherStationRepository;
import de.climateguard.application.data.service.HistoryDataService;
import de.climateguard.application.data.service.HistoryDataService.GridItem;
import de.climateguard.application.views.MainLayout;
import jakarta.annotation.PostConstruct;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@PageTitle("History")
@Route(value = "history", layout = MainLayout.class)
@UIScope
public class HistoryView extends Composite<VerticalLayout> {

    private final WeatherStationRepository weatherStationRepository;
    private final HistoryDataService historyDataService;

    private ComboBox<String> comboBox;
    private ComboBox<String> comboBox2;
    private DatePicker datePicker;
    private Button buttonPrimary;
    private Button buttonSecondary;
    private VerticalLayout layoutColumn4;
    private Notification notification;
    private Grid<GridItem> grid;
    private RadarChartComponent radarChart;

    private boolean lastRequestWasForAllData = false;

    List<HistoryDataService.GridItem> gridItems = new ArrayList<>();

    public HistoryView(WeatherStationRepository weatherStationRepository, HistoryDataService historyDataService) {
        this.weatherStationRepository = weatherStationRepository;
        this.historyDataService = historyDataService;

        comboBox = new ComboBox<>();
        comboBox2 = new ComboBox<>();
        datePicker = new DatePicker();
        buttonPrimary = new Button();
        buttonSecondary = new Button();
        layoutColumn4 = new VerticalLayout();
        grid = new Grid<>(GridItem.class);

        setupUI();
    }

    @PostConstruct
    private void init() {
        setComboBoxStationData(comboBox);
        setComboBoxData(comboBox2);

        radarChart = new RadarChartComponent(new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 });
        layoutColumn4.add(radarChart);
        layoutColumn4.setVisible(false);

        setupGrid();
    }

    private void setupUI() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H1 h12 = new H1();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow.addClassName("gap-medium");
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        h1.setText("ClimateGuard");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        h1.setWidth("max-content");

        layoutRow2.addClassName("gap-medium");
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");

        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("min-content");
        comboBox.setLabel("ClimateGuard Station:");
        comboBox.setWidth("min-content");
        comboBox2.setLabel("Data:");
        comboBox2.setWidth("min-content");
        datePicker.setLabel("Date:");
        datePicker.setWidth("min-content");
        buttonPrimary.setText("Request");
        buttonPrimary.setWidth("100%");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Clear");
        buttonSecondary.setWidth("100%");
        buttonSecondary.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn3.setAlignItems(FlexComponent.Alignment.CENTER);
        h12.setText("GRID");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.CENTER, h12);
        h12.setWidth("max-content");

        layoutColumn4.setWidth("35vw");
        layoutColumn4.getStyle().set("flex-grow", "1");
        layoutColumn4.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(FlexComponent.Alignment.CENTER);

        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(comboBox2);
        layoutColumn2.add(datePicker);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(buttonSecondary);
        layoutRow2.add(layoutColumn3);
        layoutRow2.add(layoutColumn4);
        layoutColumn3.add(grid);

        buttonPrimary.addClickListener(event -> processFormSubmission());
        buttonSecondary.addClickListener(event -> clearGrid());
    }

    private void setupGrid() {
        grid.removeAllColumns();

        grid.addColumn(GridItem::getDate).setHeader("Date").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(GridItem::getData).setHeader("Data").setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(GridItem::getAverage).setHeader("Average").setTextAlign(ColumnTextAlign.CENTER);

        grid.setHeight("80vh");

        grid.setItems(List.of());
    }

    private void clearGrid() {
        List<GridItem> items = historyDataService.clearData();
        grid.setItems(items);
    }

    private void processFormSubmission() {
        if (comboBox.getValue() == null || comboBox2.getValue() == null || datePicker.getValue() == null) {
            notification = new Notification("Please fill out all fields", 3000);
            notification.setPosition(Notification.Position.MIDDLE);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
            return;
        }

        String selectedStation = comboBox.getValue();
        String selectedData = comboBox2.getValue();
        LocalDate selectedDate = datePicker.getValue(); 

        loadGridData(selectedStation, selectedData, selectedDate);
    }

    private void loadGridData(String station, String data, LocalDate date) {
        List<GridItem> items;

        if (data.equals("All")) {
            clearGrid();
            items = historyDataService.getAverageDataForAllTables(date);
            radarChart.setValues(items.stream().map(GridItem::getAverage).mapToDouble(Double::doubleValue).toArray());
            layoutColumn4.setVisible(true);
            lastRequestWasForAllData = true;
        } else {
            if (lastRequestWasForAllData) {
                clearGrid();
                lastRequestWasForAllData = false;
            }
            layoutColumn4.setVisible(false);
            items = historyDataService.getAverageDataForSpecificTable(data, date);
        }

        Collections.reverse(items);
        grid.setItems(items);
    }

    private void setComboBoxStationData(ComboBox<String> comboBox) {
        List<WeatherStation> stations = weatherStationRepository.findAll();
        comboBox.setItems(stations.stream().map(WeatherStation::getStationId).toList());
        comboBox.setItemLabelGenerator(stationId -> stations.stream()
                .filter(station -> station.getStationId().equals(stationId))
                .findFirst()
                .map(WeatherStation::getName)
                .orElse("Unknown"));
    }

    private void setComboBoxData(ComboBox<String> comboBox) {
        List<Item> items = new ArrayList<>();

        items.add(new Item("All", "All", null));

        try {
            List<String> tables = List.of("Temperature", "Humidity", "Pressure", "LightIntensity", "AirQuality");
            for (String tableName : tables) {
                String label = convertTableNameToLabel(tableName);
                items.add(new Item(tableName, label, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        comboBox.setItems(items.stream().map(Item::value).toList());
        comboBox.setItemLabelGenerator(item -> items.stream()
                .filter(sample -> sample.value().equals(item))
                .findFirst()
                .map(Item::label)
                .orElse("Unknown"));
    }

    private String convertTableNameToLabel(String tableName) {
        return switch (tableName) {
            case "Temperature" -> "Temperatures";
            case "Humidity" -> "Humidity";
            case "Pressure" -> "Pressure";
            case "LightIntensity" -> "Light Intensity";
            case "AirQuality" -> "Air Quality";
            default -> tableName;
        };
    }

    record Item(String value, String label, Boolean disabled) {
    }
}
