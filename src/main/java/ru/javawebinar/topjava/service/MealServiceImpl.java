package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        Meal meal = repository.get(id, userId);
        if(meal != null) return meal;
        else throw new NotFoundException("Not found meal" + id + "user" + userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        if(get(id, userId) == null) throw new NotFoundException("Not found meal" + id + "user" + userId);
        repository.delete(id, userId);
    }

    @Override
    public List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) throws NotFoundException {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Meal> getAll(int userId) throws NotFoundException {
        return repository.getAll(userId);
    }

    @Override
    public void update(Meal meal, int userId) throws NotFoundException {
        if(get(meal.getId(), userId) == null) throw new NotFoundException("Not found meal" + meal.getId() + "user" + userId);
        repository.save(meal, userId);
    }

    @Override
    public Meal create(Meal meal, int userId) throws NotFoundException {
        return repository.save(meal, userId);
    }
}