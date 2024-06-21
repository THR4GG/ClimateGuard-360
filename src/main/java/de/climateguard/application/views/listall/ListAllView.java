package de.climateguard.application.views.listall;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import de.climateguard.application.views.MainLayout;

@PageTitle("List All")
@Route(value = "list-all", layout = MainLayout.class)
public class ListAllView extends Composite<VerticalLayout> {

    public ListAllView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        TextField textField = new TextField();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H1 h12 = new H1();

        // Set up layoutRow
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        h1.setText("ClimateGuard");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        h1.setWidth("max-content");

        // Set up layoutRow2
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");

        // Set up layoutColumn2
        layoutColumn2.getStyle().set("flex-grow", "1");
        textField.setLabel("Topic:");
        textField.setWidth("109%");
        textField.setHelperText("Enter the topic to subscribe to");
        textField.setPlaceholder("# (all Topics)");

        // Set up layoutRow3
        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutColumn2.setWidth("min-content");
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("min-content");

        // Set up buttonPrimary
        buttonPrimary.setText("Set");
        buttonPrimary.getStyle().set("flex-grow", "1");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.setWidth("50%");

        // Set up buttonSecondary
        buttonSecondary.setText("Refresh");
        buttonSecondary.getStyle().set("flex-grow", "1");
        buttonSecondary.setWidth("50%");

        // Set up layoutColumn3
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn3.setAlignItems(Alignment.CENTER);
        h12.setText("GRID");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.CENTER, h12);
        h12.setWidth("max-content");

        // Add components to the layout
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(layoutRow2);
        layoutRow2.add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(layoutRow3);
        layoutRow3.add(buttonPrimary);
        layoutRow3.add(buttonSecondary);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(h12);

        buttonPrimary.addClickListener(e -> {

        });
    }
}
