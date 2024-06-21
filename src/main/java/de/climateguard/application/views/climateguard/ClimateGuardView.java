package de.climateguard.application.views.climateguard;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import de.climateguard.application.views.MainLayout;
import java.util.ArrayList;
import java.util.List;

@PageTitle("ClimateGuard")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ClimateGuardView extends Composite<VerticalLayout> {

    public ClimateGuardView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        ComboBox comboBox = new ComboBox();
        Button buttonPrimary = new Button();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        TextField textField = new TextField();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        TextField textField2 = new TextField();
        VerticalLayout layoutColumn6 = new VerticalLayout();
        TextField textField3 = new TextField();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        VerticalLayout layoutColumn7 = new VerticalLayout();
        TextField textField4 = new TextField();
        VerticalLayout layoutColumn8 = new VerticalLayout();
        TextField textField5 = new TextField();
        VerticalLayout layoutColumn9 = new VerticalLayout();
        TextField textField6 = new TextField();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.XLARGE);
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
        comboBox.setLabel("ClimateGuard Station:");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, comboBox);
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        buttonPrimary.setText("Auswählen");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
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
        layoutColumn4.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn4.setAlignItems(Alignment.CENTER);
        textField.setLabel("Temperature:");
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        textField.setWidth("min-content");
        layoutColumn5.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth("100%");
        layoutColumn5.getStyle().set("flex-grow", "1");
        layoutColumn5.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn5.setAlignItems(Alignment.CENTER);
        textField2.setLabel("Humidity:");
        textField2.setWidth("min-content");
        layoutColumn6.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn6);
        layoutColumn6.setWidth("100%");
        layoutColumn6.getStyle().set("flex-grow", "1");
        layoutColumn6.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn6.setAlignItems(Alignment.CENTER);
        textField3.setLabel("Pressure:");
        textField3.setWidth("min-content");
        layoutRow4.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        layoutColumn7.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutColumn7);
        layoutColumn7.setWidth("100%");
        layoutColumn7.getStyle().set("flex-grow", "1");
        layoutColumn7.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn7.setAlignItems(Alignment.CENTER);
        textField4.setLabel("AirQuality:");
        textField4.setWidth("min-content");
        layoutColumn8.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutColumn8);
        layoutColumn8.setWidth("100%");
        layoutColumn8.getStyle().set("flex-grow", "1");
        layoutColumn8.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn8.setAlignItems(Alignment.CENTER);
        textField5.setLabel("LightIntensity:");
        textField5.setWidth("min-content");
        layoutColumn9.setHeightFull();
        layoutRow4.setFlexGrow(1.0, layoutColumn9);
        layoutColumn9.setWidth("100%");
        layoutColumn9.getStyle().set("flex-grow", "1");
        layoutColumn9.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn9.setAlignItems(Alignment.CENTER);
        textField6.setLabel("Raining:");
        textField6.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(buttonPrimary);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(layoutRow3);
        layoutRow3.add(layoutColumn4);
        layoutColumn4.add(textField);
        layoutRow3.add(layoutColumn5);
        layoutColumn5.add(textField2);
        layoutRow3.add(layoutColumn6);
        layoutColumn6.add(textField3);
        layoutColumn3.add(layoutRow4);
        layoutRow4.add(layoutColumn7);
        layoutColumn7.add(textField4);
        layoutRow4.add(layoutColumn8);
        layoutColumn8.add(textField5);
        layoutRow4.add(layoutColumn9);
        layoutColumn9.add(textField6);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
