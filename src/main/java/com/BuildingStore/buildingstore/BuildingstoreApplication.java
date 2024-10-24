package com.BuildingStore.buildingstore;
import com.BuildingStore.buildingstore.materialRepository.MaterialRepository;
import com.BuildingStore.buildingstore.model.BrickMaterial;
import com.BuildingStore.buildingstore.model.LooseMaterial;
import com.BuildingStore.buildingstore.model.MetalSheetMaterial;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EntityScan(basePackages = "com.BuildingStore.buildingstore.model")
public class BuildingstoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BuildingstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(MaterialRepository materialRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				materialRepository.save(new BrickMaterial("1", "Красный кирпич", "Обычный красный кирпич", 15, 2.5, BrickMaterial.BrickType.RED));
				materialRepository.save(new BrickMaterial("2", "Белый кирпич", "Белый силикатный кирпич", 18, 2.8, BrickMaterial.BrickType.WHITE));
				materialRepository.save(new BrickMaterial("3", "Огнеупорный кирпич", "Кирпич для высоких температур", 25, 3.0, BrickMaterial.BrickType.FIREPROOF));
				materialRepository.save(new LooseMaterial("4", "Цемент", "Строительный цемент", 12, 50));
				materialRepository.save(new LooseMaterial("5", "Песок", "Речной песок для строительных работ", 7, 30));
				materialRepository.save(new MetalSheetMaterial("6", "Лист металла 3мм", "Металлический лист толщиной 3 мм", 100, 50, 3));
				materialRepository.save(new MetalSheetMaterial("7", "Лист металла 5мм", "Металлический лист толщиной 5 мм", 150, 70, 5));
				materialRepository.save(new MetalSheetMaterial("8", "Лист нержавеющей стали", "Нержавеющая сталь", 250, 80, 4));
				materialRepository.save(new LooseMaterial("9", "Гравий", "Строительный гравий", 20, 100));
				materialRepository.save(new LooseMaterial("10", "Щебень", "Щебень для фундамента", 18, 100));
				materialRepository.save(new LooseMaterial("11", "Асфальт", "Готовая смесь для дорожных покрытий", 50, 200));
				materialRepository.save(new MetalSheetMaterial("12", "Медный лист 2мм", "Медный лист для кровли", 300, 40, 2));
				materialRepository.save(new MetalSheetMaterial("13", "Алюминиевый лист", "Алюминиевый лист для облицовки", 200, 60, 3));
				materialRepository.save(new BrickMaterial("14", "Кирпич облицовочный", "Кирпич для облицовки зданий", 22, 2.7, BrickMaterial.BrickType.RED));
				materialRepository.save(new LooseMaterial("15", "Сухая смесь", "Универсальная сухая смесь для строительства", 10, 25));


			}
		};
	}
}