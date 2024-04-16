package plazoleta.adapters.driven.jpa.msql.adapter;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import plazoleta.adapters.driven.jpa.msql.entity.restaurant.RestaurantEntity;
import plazoleta.adapters.driven.jpa.msql.mapper.IRestaurantEntityMapper;
import plazoleta.adapters.driven.jpa.msql.repository.IRestaurantRepositoryJPA;
import plazoleta.domain.model.restaurant.Restaurant;
import plazoleta.domain.spi.IRestaurantPersistencePort;

import java.util.List;
import java.util.Optional;

import static plazoleta.adapters.driven.jpa.msql.utils.DataOrdering.getOrdering;

@Service
@AllArgsConstructor
public class RestaurantAdapter implements IRestaurantPersistencePort {

    private IRestaurantRepositoryJPA restaurantRepositoryJPA;
    private IRestaurantEntityMapper restaurantEntityMapper;
    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantEntityMapper.toRestaurant(restaurantRepositoryJPA
                .save(restaurantEntityMapper.toRestaurantEntity(restaurant)));
    }
    @Override
    public Optional<RestaurantEntity> getRestaurant (int id) {
        return restaurantRepositoryJPA.findById(id);
    }

    @Override
    public List<Restaurant> getAll(int page, int size, boolean sort) {
        Pageable pageable = getOrdering(page, size, sort, "name");
        return restaurantEntityMapper.toRestaurantList(restaurantRepositoryJPA.findAll(pageable));
    }
    @Override
    public Optional<RestaurantEntity> getRestaurantByEmployee (int id) {
        return restaurantRepositoryJPA.findByOwnerId(id);
    }

}
