package de.climateguard.application.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.Random;

/**
 * Represents a gauge component for displaying sensor data.
 * This component uses ApexCharts to render the gauge.
 */
public class GaugeComponent extends Composite<VerticalLayout> {
    private final Div chartDiv;
    private final H2 label;
    private final String unit;
    private final int min;
    private final int max;
    private final String startColor;
    private final String colorInBetween;
    private final String endColor;
    private final int colorStop1;
    private final int colorStop2;

    /**
     * Constructs a new GaugeComponent.
     *
     * @param labelText      the label text for the gauge
     * @param unit           the unit of measurement
     * @param initialValue   the initial value to display
     * @param min            the minimum value of the gauge
     * @param max            the maximum value of the gauge
     * @param startColor     the color for values at the start of the range
     * @param colorInBetween the color for values in the middle of the range
     * @param endColor       the color for values at the end of the range
     * @param colorStop1     the first color stop
     * @param colorStop2     the second color stop
     */
    public GaugeComponent(String labelText, String unit, float initialValue, int min, int max, String startColor,
            String colorInBetween, String endColor, int colorStop1, int colorStop2) {
        this.unit = unit;
        this.startColor = startColor;
        this.colorInBetween = colorInBetween;
        this.endColor = endColor;
        this.colorStop1 = colorStop1;
        this.colorStop2 = colorStop2;
        this.min = min;
        this.max = max;

        // Label hinzufügen
        label = new H2(labelText);
        label.getStyle().set("color", "white");
        label.getStyle().set("text-align", "center");
        label.getStyle().set("margin", "auto");
        getContent().add(label);

        // Chart Div hinzufügen
        chartDiv = new Div();
        chartDiv.setId("gauge-" + labelText.replaceAll("\\s", "").toLowerCase());
        chartDiv.getStyle().set("width", "15vw");
        chartDiv.getStyle().set("height", "15vw");
        chartDiv.getStyle().set("margin", "auto");
        chartDiv.getStyle().set("padding", "0");
        chartDiv.getStyle().set("display", "flex");
        chartDiv.getStyle().set("justify-content", "center");
        chartDiv.getStyle().set("align-items", "center");
        getContent().add(chartDiv);

        float mappedInitialValue = mapValue(initialValue, min, max);

        getElement().executeJs(
                "var chartContainer = document.querySelector('#" + chartDiv.getId().orElse("") + "');" +
                        "chartContainer.style.boxShadow = 'none';" +
                        "chartContainer.style.border = 'none';" +
                        "chartContainer.style.padding = '0';" +
                        "chartContainer.style.margin = 'auto';");

        getElement().executeJs(
                "var options = {" +
                        "  chart: { " +
                        "    height: 400, " +
                        "    type: 'radialBar', " +
                        "    animations: { " +
                        "      enabled: true, " +
                        "      easing: 'easeinout', " +
                        "      speed: 800, " +
                        "      animateGradually: { " +
                        "        enabled: true, " +
                        "        delay: 150 " +
                        "      }, " +
                        "      dynamicAnimation: { " +
                        "        enabled: true, " +
                        "        speed: 350 " +
                        "      } " +
                        "    } " +
                        "  }, " +
                        "  series: [" + mappedInitialValue + "]," +
                        "  colors: ['" + getColorForValue(initialValue) + "']," +
                        "  plotOptions: {" +
                        "    radialBar: {" +
                        "      startAngle: -135," +
                        "      endAngle: 135," +
                        "      track: { background: '#333', startAngle: -135, endAngle: 135 }," +
                        "      dataLabels: {" +
                        "        name: { show: false }," +
                        "        value: { fontSize: '30px', show: true, color: 'white', formatter: function (val) { return "
                        + initialValue + " + ' " + unit + "'; } }" +
                        "      }," +
                        "    }" +
                        "  }," +
                        "  fill: {" +
                        "    type: 'solid'," +
                        "    gradient: {" +
                        "      shade: 'dark'," +
                        "      type: 'circles'," +
                        "      gradientToColors: ['" + endColor + "']," +
                        "      stops: [0, 100]" +
                        "    }" +
                        "  }," +
                        "  stroke: { lineCap: 'butt' }," +
                        "  labels: ['" + labelText + "']" +
                        "};" +
                        "var chart = new ApexCharts(document.querySelector('#" + chartDiv.getId().orElse("")
                        + "'), options);" +
                        "chart.render();" +
                        "window['" + chartDiv.getId().orElse("") + "'] = chart;");
    }

    /**
     * Sets the value of the gauge.
     *
     * @param newValue the new value to set
     */
    public void setValue(float newValue) {
        float mappedValue = mapValue(newValue, min, max);
        String newColor = getColorForValue(newValue);
        getElement().executeJs(
                "var actualValue = " + newValue + ";" +
                        "var unit = '" + unit + "';" +
                        "var newColor = '" + newColor + "';" +
                        "window['" + chartDiv.getId().orElse("") + "'].updateSeries([" + mappedValue + "], true);" +
                        "window['" + chartDiv.getId().orElse("") + "'].updateOptions({ " +
                        "  colors: [newColor]," +
                        "  plotOptions: { radialBar: { dataLabels: { value: { formatter: function () { " +
                        "    return actualValue.toFixed(1) + ' ' + unit; } } } } } });");
    }

    /**
     * Updates the gauge value with a random number between the minimum and maximum.
     */
    public void updateValueRandom() {
        Random random = new Random();
        int newValue = random.nextInt(max - min) + min;
        setValue(newValue);
    }

    /**
     * Maps a value from the range [min, max] to the range [0, 100].
     *
     * @param value the value to map
     * @param min   the minimum value of the range
     * @param max   the maximum value of the range
     * @return the mapped value
     */
    private float mapValue(float value, int min, int max) {
        return (int) (((double) (value - min) / (max - min)) * 100);
    }

    /**
     * Returns the color for the given value based on predefined color stops.
     *
     * @param value the value to determine the color for
     * @return the color as a hex string
     */
    private String getColorForValue(float value) {
        if (value < colorStop1) {
            return startColor;
        } else if (value < colorStop2) {
            return colorInBetween;
        } else {
            return endColor;
        }
    }
}
