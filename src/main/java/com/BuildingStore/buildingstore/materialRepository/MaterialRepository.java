package com.BuildingStore.buildingstore.materialRepository;
import com.BuildingStore.buildingstore.model.Material;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class MaterialRepository {

    private final List<Material> materials = new ArrayList<>();

    // Инициализация списка при создании репозитория
    public MaterialRepository() {
        materials.add(new Material("Brick", "Кирпичи",100));
        materials.add(new Material("Cement", "Кирпичи",50));
        materials.add(new Material("Steel", "Кирпичи", 200));
    }

    // Метод для получения всех материалов
    public List<Material> findAll() {
        //System.out.println("Материалы" + materials.toString());
        return new ArrayList<>(materials);

        // Возвращаем копию списка для защиты данных
    }

    // Можно добавить другие методы для добавления, удаления или поиска материалов
}
