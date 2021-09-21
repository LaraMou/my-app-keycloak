package com.example.application.views.admin;

import com.example.application.services.ClientService;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


@Route("admin")
public class AdminView extends Div {
    public AdminView(@Autowired ClientService clientService){
        String adminPage= clientService.getAdminPage();
        add(new H1(adminPage));
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
