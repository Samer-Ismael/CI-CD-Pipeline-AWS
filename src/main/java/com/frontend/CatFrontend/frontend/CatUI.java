package com.frontend.CatFrontend.frontend;

import com.frontend.CatFrontend.controller.CatController;
import com.frontend.CatFrontend.entitiy.Cat;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Route (value = "")
public class CatUI extends VerticalLayout {

    private final CatController catController;



    private final Grid<Cat> catGrid = new Grid<>(Cat.class);
    private final TextField name = new TextField("Name");
    private final TextField color = new TextField("Color");
    private final TextField age = new TextField("Age");


    @Autowired
    public CatUI(CatController catController ) {
        this.catController = catController;


        catGrid.setColumns("id", "name", "color", "age");

        Image image = new Image(new StreamResource("cat.jpg", () -> getClass().getResourceAsStream("/images/cat.jpg")), "Cat Image");

        image.getElement().getStyle().set("width", "250px");
        image.getElement().getStyle().set("height", "250px");

        HorizontalLayout imageLayout = new HorizontalLayout(image);
        imageLayout.setAlignItems(Alignment.START);

        HorizontalLayout inputLayout = new HorizontalLayout(name, color, age);
        inputLayout.setSpacing(true);

        Button addCatButton = new Button("Add Cat");
        Button updateCatButton = new Button("Update Cat");
        Button deleteCatButton = new Button("Delete Cat");
        Button swaggerButton = new Button("Swagger Documentation");
        HorizontalLayout buttonLayout = new HorizontalLayout(addCatButton, updateCatButton, deleteCatButton, swaggerButton);
        buttonLayout.setSpacing(true);

        addCatButton.addClickListener(e -> addCat());
        updateCatButton.addClickListener(e -> updateCat());
        deleteCatButton.addClickListener(e -> deleteCat());
        swaggerButton.addClickListener(e -> navigateToSwaggerUi());


        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(imageLayout ,inputLayout, buttonLayout, catGrid);

        refreshGrid();
    }

    private void navigateToSwaggerUi() {
        UI.getCurrent().getPage().setLocation("/swagger-ui/index.html");

    }

    private void addCat() {
       try {
           String catName = name.getValue();
           String catColor = color.getValue();
           String catAge = age.getValue();

           Cat cat = new Cat();
           cat.setName(catName);
           cat.setColor(catColor);
           cat.setAge(Integer.parseInt(catAge));

           catController.addCat(cat);
           refreshGrid();
           Notification.show("Cat added successfully");
       } catch (NumberFormatException e) {
           Notification.show("Something went wrong.");
       }
    }


    private void updateCat() {
        Cat selectedCat = catGrid.asSingleSelect().getValue();
        if (selectedCat != null) {
            selectedCat.setName(name.getValue());
            selectedCat.setColor(color.getValue());
            selectedCat.setAge(Integer.parseInt(age.getValue()));
            catController.updateCat(selectedCat.getId(), selectedCat);
            refreshGrid();
            Notification.show("Cat updated successfully");
        } else {
            Notification.show("Select a cat to update");
        }
    }

    private void deleteCat() {
        Cat selectedCat = catGrid.asSingleSelect().getValue();
        if (selectedCat != null) {
            catController.deleteCat(selectedCat.getId());
            refreshGrid();
            Notification.show("Cat deleted successfully");
        } else {
            Notification.show("Select a cat to delete");
        }
    }

    private void refreshGrid() {
        ResponseEntity<List<Cat>> responseEntity = catController.getAllCats();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<Cat> cats = responseEntity.getBody();
            catGrid.setItems(cats);
            catGrid.deselectAll();
        } else {
            Notification.show("Error fetching cat data: " + responseEntity.getStatusCode());
        }
    }
}