package com.frontend.CatFrontend.controller;

import com.frontend.CatFrontend.entitiy.Cat;
import com.frontend.CatFrontend.repo.CatRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatControllerTest {

    @Mock
    private CatRepo catRepo;

    @InjectMocks
    private CatController catController;

    @Test
    public void addCatTest() {
        // Same as before
    }

    @Test
    public void getAllCatsTest() {
        List<Cat> cats = Arrays.asList(new Cat("Mr. Mittens", "grey", 5), new Cat("Fluffy", "white", 2));
        when(catRepo.findAll()).thenReturn(cats);

        ResponseEntity<List<Cat>> response = catController.getAllCats();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cats, response.getBody());
        verify(catRepo, times(1)).findAll();
    }

    @Test
    public void getCatByNameTest_Found() {
        Cat cat = new Cat("Snowball", "white", 1);
        when(catRepo.findByName("Snowball")).thenReturn(Optional.of(cat));

        ResponseEntity<Cat> response = catController.getCatByName("Snowball");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cat, response.getBody());
        verify(catRepo, times(2)).findByName("Snowball");
    }


    @Test
    public void getCatByNameTest_NotFound() {
        when(catRepo.findByName("NonexistentCat")).thenReturn(Optional.empty());

        ResponseEntity<Cat> response = catController.getCatByName("NonexistentCat");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(catRepo, times(1)).findByName("NonexistentCat");
    }

    @Test
    public void deleteCatTest() {
        doNothing().when(catRepo).deleteById(1);

        ResponseEntity<String> response = catController.deleteCat(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cat Deleted", response.getBody());
        verify(catRepo, times(1)).deleteById(1);
    }
}