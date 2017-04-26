package ru.javawebinar.topjava.Repository;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * tingaev@gmail.com on 31.03.17.
 */
public class InMemoryMealRepository implements MealRepository  {
    private static final Logger LOG = getLogger(InMemoryMealRepository.class);

    private List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    private Map<UUID, Meal> repository = new ConcurrentHashMap<>();

    {
       meals.forEach(meal -> repository.put(meal.getId(), meal));
    }


    public static final int DEFAULT_CALORIES = 2000;


    @Override
    public Meal save(Meal meal) {
        if (repository.containsKey(meal.getId())) {
            repository.remove(meal.getId());
            return repository.put(meal.getId(), meal);
        }
        else return repository.put(meal.getId(), meal);
    }

    @Override
    public void delete(UUID uuid) {
        repository.remove(uuid);
    }

    @Override
    public Meal get(UUID uuid) {
        return repository.get(uuid);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }
}
