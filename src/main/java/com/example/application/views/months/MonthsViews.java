package com.example.application.views.months;

import com.example.application.services.ClientService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
@Route("months")

class MonthsView extends VerticalLayout implements RouterLayout {

        public MonthsView(@Autowired ClientService clientService) {
                List<String> months = clientService.getMonths();
                months.forEach(s -> add(new Paragraph(s)));
                if(!SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal().equals("anonymousUser")) {
                        KeycloakPrincipal principal = (KeycloakPrincipal) SecurityContextHolder.getContext()
                                .getAuthentication().getPrincipal();
                        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();

                        String preferredUsername = keycloakSecurityContext.getIdToken().getPreferredUsername();
                        Anchor logout = new Anchor("http://localhost:9991/auth/realms/Demo/protocol/openid-connect/logout?redirect_uri=" +
                                "http://localhost:8080/",
                                "Logout");
                        add(new HorizontalLayout(new Span(preferredUsername), logout));
                }
                else{
                        add(new Span("No Logged in User"));


                }
        }
}
