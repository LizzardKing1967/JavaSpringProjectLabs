package com.BuildingStore.buildingstore.materialRepository;
import com.BuildingStore.buildingstore.model.BrickMaterial;
import com.BuildingStore.buildingstore.model.Material;
import com.BuildingStore.buildingstore.model.MetalSheet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class MaterialRepository {

    private final List<Material> materials = new ArrayList<>();

    // Инициализация списка при создании репозитория
    public MaterialRepository() {
        {
            materials.add(new BrickMaterial(1L, "Красный кирпич", "Обычный красный кирпич", 15, 2.5, BrickMaterial.BrickType.RED));
            materials.add(new BrickMaterial(2L, "Белый кирпич", "Белый силикатный кирпич", 18, 2.8, BrickMaterial.BrickType.WHITE));
            materials.add(new BrickMaterial(3L, "Огнеупорный кирпич", "Кирпич для высоких температур", 25, 3.0, BrickMaterial.BrickType.FIREPROOF));
            materials.add(new Material(4L, "Цемент", "Строительный цемент", 12, 50));
            materials.add(new Material(5L, "Песок", "Речной песок для строительных работ", 7, 30));
            materials.add(new MetalSheet(6L, "Лист металла 3мм", "Металлический лист толщиной 3 мм", 100, 50, 3));
            materials.add(new MetalSheet(7L, "Лист металла 5мм", "Металлический лист толщиной 5 мм", 150, 70, 5));
            materials.add(new MetalSheet(8L, "Лист нержавеющей стали", "Нержавеющая сталь", 250, 80, 4));
            materials.add(new Material(9L, "Гравий", "Строительный гравий", 20, 100));
            materials.add(new Material(10L, "Щебень", "Щебень для фундамента", 18, 100));
            materials.add(new Material(11L, "Асфальт", "Готовая смесь для дорожных покрытий", 50, 200));
            materials.add(new MetalSheet(12L, "Медный лист 2мм", "Медный лист для кровли", 300, 40, 2));
            materials.add(new MetalSheet(13L, "Алюминиевый лист", "Алюминиевый лист для облицовки", 200, 60, 3));
            materials.add(new BrickMaterial(14L, "Кирпич облицовочный", "Кирпич для облицовки зданий", 22, 2.7, BrickMaterial.BrickType.RED));
            materials.add(new Material(15L, "Сухая смесь", "Универсальная сухая смесь для строительства", 10, 25));
        }
    }

    // Метод для получения всех материалов
    public List<Material> findAll() {
        return new ArrayList<>(materials);
    }

    public Material findById(Long id) {
        return materials.stream().filter(material -> material.getId().equals(id)).findFirst().orElse(null);
    }
}
