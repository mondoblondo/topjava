package ru.javawebinar.topjava.Repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.UUID;

/**
 * tingaev@gmail.com on 26.04.17.
 */
public interface MealRepository {
    Meal save(Meal meal);
    void delete(UUID uuid);
    Meal get(UUID uuid);
    Collection<Meal> getAll();

}
