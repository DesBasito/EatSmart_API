package kg.manurov.eatsmartapi.services.impl;

import kg.manurov.eatsmartapi.dto.DishDto;
import kg.manurov.eatsmartapi.mappers.DishMapper;
import kg.manurov.eatsmartapi.models.Dish;
import kg.manurov.eatsmartapi.repositories.DishRepository;
import kg.manurov.eatsmartapi.services.interfaces.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository repository;
    private final DishMapper mapper;

    @Autowired
    public DishServiceImpl(DishMapper mapper, DishRepository repository){
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public DishDto create(DishDto dishDto) {
        Dish dish = mapper.toEntity(dishDto);
        repository.save(dish);
        return mapper.toDto(dish);
    }
}
