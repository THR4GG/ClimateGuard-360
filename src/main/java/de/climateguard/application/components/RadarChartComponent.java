package de.climateguard.application.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class RadarChartComponent extends Composite<VerticalLayout> {
    private final Div chartDiv;
    private final H2 label;

    public RadarChartComponent(double[] initialValues) {
        // Label hinzufügen
        label = new H2("SQL Daten");
        label.getStyle().set("color", "white"); // Setzt die Textfarbe auf Weiß
        label.getStyle().set("text-align", "center");
        label.getStyle().set("margin", "auto");
        getContent().add(label);

        // Div für den Radar-Chart hinzufügen
        chartDiv = new Div();
        chartDiv.setId("radar-chart");
        chartDiv.getStyle().set("width", "35vw");
        chartDiv.getStyle().set("height", "35vw");
        chartDiv.getStyle().set("margin", "auto");
        chartDiv.getStyle().set("padding", "0");
        chartDiv.getStyle().set("display", "flex");
        chartDiv.getStyle().set("justify-content", "center");
        chartDiv.getStyle().set("align-items", "center");
        getContent().add(chartDiv);

        getElement().executeJs(
                "var chartContainer = document.querySelector('#" + chartDiv.getId().orElse("") + "');" +
                        "chartContainer.style.boxShadow = 'none';" +
                        "chartContainer.style.border = 'none';" +
                        "chartContainer.style.padding = '0';" +
                        "chartContainer.style.margin = 'auto';");

        getElement().executeJs(
                "var options = {" +
                        "  series: [{" +
                        "    name: 'Series 1'," +
                        "    data: [" + initialValues[0] + ", " + initialValues[1] + ", " + initialValues[2] + ", "
                        + initialValues[3] + ", " + initialValues[4] + "]" +
                        "  }]," +
                        "  chart: {" +
                        "    height: '100%'," +
                        "    type: 'radar'," +
                        "    toolbar: { show: false }, " +
                        "    animations: { " +
                        "      enabled: true, " +
                        "      easing: 'easeinout', " +
                        "      speed: 800 " +
                        "    }" +
                        "  }," +
                        "  xaxis: {" +
                        "    categories: [" +
                        "      'Temperature (" + initialValues[0] + ")', " +
                        "      'Humidity (" + initialValues[1] + ")', " +
                        "      'Pressure (" + initialValues[2] + ")', " +
                        "      'LightIntensity (" + initialValues[3] + ")', " +
                        "      'AirQuality (" + initialValues[4] + ")'" +
                        "    ]," +
                        "    labels: { " +
                        "      style: { " +
                        "        colors: Array(5).fill('white'), " +
                        "        fontSize: '16px' " +
                        "      }" +
                        "    }" +
                        "  }," +
                        "  yaxis: { " +
                        "    labels: { style: { colors: 'white' } }, " +
                        "    show: false " +
                        "  }, " +
                        "  title: { text: '' }" +
                        "};" +
                        "var chart = new ApexCharts(document.querySelector('#" + chartDiv.getId().orElse("")
                        + "'), options);" +
                        "chart.render();" +
                        "window['" + chartDiv.getId().orElse("") + "'] = chart;");
    }

    public void setValues(double[] newValues) {
        if (newValues.length != 5) {
            throw new IllegalArgumentException("Die Anzahl der Werte muss 5 sein.");
        }

        getElement().executeJs(
                "window['" + chartDiv.getId().orElse("") + "'].updateSeries([{" +
                        "  data: [" + newValues[0] + ", " + newValues[1] + ", " + newValues[2] + ", " + newValues[3]
                        + ", " + newValues[4] + "]" +
                        "}], true);" +
                        "window['" + chartDiv.getId().orElse("") + "'].updateOptions({ " +
                        "  xaxis: { " +
                        "    categories: [" +
                        "      'Temperature (' + " + newValues[0] + " + ')', " +
                        "      'Humidity (' + " + newValues[1] + " + ')', " +
                        "      'Pressure (' + " + newValues[2] + " + ')', " +
                        "      'LightIntensity (' + " + newValues[3] + " + ')', " +
                        "      'AirQuality (' + " + newValues[4] + " + ')'" +
                        "    ]," +
                        "    labels: { " +
                        "      style: { " +
                        "        colors: Array(5).fill('white'), " +
                        "        fontSize: '16px' " +
                        "      }" +
                        "    }" +
                        "  }" +
                        "});");
    }
}
