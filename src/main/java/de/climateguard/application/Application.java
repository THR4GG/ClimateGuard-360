package de.climateguard.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 */
@Push
@SpringBootApplication(scanBasePackages = { "de.climateguard.application" })
@Theme(value = "climateguard", variant = Lumo.DARK)
public class Application implements AppShellConfigurator {

    /**
     * The main method which serves as the entry point for the Spring Boot
     * application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
