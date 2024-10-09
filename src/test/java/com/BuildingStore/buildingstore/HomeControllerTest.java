package com.BuildingStore.buildingstore;

import com.BuildingStore.buildingstore.interfaces.HomeController;
import com.BuildingStore.buildingstore.model.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    private HomeController homeController;
    private Model model;

    @BeforeEach
    void setUp() {
        homeController = new HomeController();
        model = mock(Model.class); // Модель может быть замокирована, но в данном случае мы её не используем
    }

    @Test
    void testAddMaterialsIntoModel() {
        // Arrange
        List<Material> mockMaterials = new ArrayList<>();
        mockMaterials.add(new Material("Brick", "Кирпичи", 100));
        mockMaterials.add(new Material("Cement", "Цемент", 50));
        mockMaterials.add(new Material("Steel", "Сталь", 200));

        // Act
        List<Material> materials = homeController.addMaterialsIntoModel();

        // Assert
        assertNotNull(materials, "Список материалов не должен быть null");
        assertEquals(3, materials.size(), "Список должен содержать 3 материала");
        assertEquals("Brick", materials.get(0).getName(), "Первый элемент должен быть 'Brick'");
    }

    @Test
    void testAttributesInModel() {
        // Act
        homeController.showHomePage(model);

        // Assert
        verify(model, times(1)).addAttribute("storeTitle", "Building store");
        verify(model, times(1)).addAttribute("welcomeMessage", "Welcome to our shop!");
    }

    @Test
    void testHomePageViewName() {
        // Act
        String viewName = homeController.showHomePage(model);

        // Assert
        assertEquals("index", viewName, "Контроллер должен возвращать представление 'index'");
    }

    @Test
    void testModelNotNull() {
        // Act
        String viewName = homeController.showHomePage(model);

        // Assert
        assertNotNull(viewName, "Имя представления не должно быть null");
    }

    @Test
    void testControllerInitialization() {
        assertNotNull(homeController, "Контроллер должен быть корректно инициализирован");
    }
}
