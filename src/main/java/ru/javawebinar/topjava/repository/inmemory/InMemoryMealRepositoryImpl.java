package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));

        save(new Meal(2, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), 2);
        save(new Meal(2, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if(meal.getUserId() == userId){
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.put(meal.getId(), meal);
                return meal;
            }
            // treat case: update, but absent in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public void delete(int id, int userId) {
        if(repository.get(id)!=null) {
            if(repository.get(id).getUserId() == userId)
                repository.remove(id);
        }
    }

    @Override
    public Meal get(int id, int userId) {
        if(repository.get(id).getUserId() == userId)
            return repository.get(id);
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> mealList = new ArrayList<>(repository.values());
        List<Meal> userMealList = new ArrayList<>();
        for(Meal m : mealList){
            if(m.getUserId() == userId)
                userMealList.add(m);
        }
        userMealList.sort(Comparator.comparing(Meal::getDate).reversed());
        return userMealList;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}

