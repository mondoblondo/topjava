package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Repository.InMemoryMealRepository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * tingaev@gmail.com on 31.03.17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private InMemoryMealRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to meals");

        String action = req.getParameter("action") == null ? "" : req.getParameter("action");

        switch (action) {

            case "delete": {
                repository.delete(getId(req));
                req.setAttribute("meals", MealsUtil
                        .getFilteredWithExceeded(
                                repository.getAll(),
                                LocalTime.MIN,
                                LocalTime.MAX,
                                InMemoryMealRepository.DEFAULT_CALORIES));
                resp.sendRedirect("/meals");
                LOG.debug("deleted meal in case delete");
                break;
            }
            case "create": {
                Meal meal = new Meal(
                        LocalDateTime.now(),
                        "",
                        1000
                );
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
                break;
            }
            default:
                req.setAttribute("meals", MealsUtil
                        .getFilteredWithExceeded(
                                repository.getAll(),
                                LocalTime.MIN,
                                LocalTime.MAX,
                                InMemoryMealRepository.DEFAULT_CALORIES));
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                LOG.debug("default action in doGet");

        }
    }
    private UUID getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return UUID.fromString(paramId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories"))
        );
        repository.save(meal);
        LOG.debug("saved new meal" + meal.getDescription());
        resp.sendRedirect("/meals");
    }
}
