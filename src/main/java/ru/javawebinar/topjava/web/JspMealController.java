package ru.javawebinar.topjava.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JspMealController {
    @Autowired
    private UserService service;

    @Autowired
    private MealService mealService;

    @Autowired
    private MealRestController mealRestController;

    @GetMapping("/meals")
    public String users(Model model) {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

//    @PostMapping("/users")
//    public String setUser(HttpServletRequest request) {
//        int userId = Integer.valueOf(request.getParameter("userId"));
//        SecurityUtil.setAuthUserId(userId);
//        return "redirect:meals";
//    }
//
}
