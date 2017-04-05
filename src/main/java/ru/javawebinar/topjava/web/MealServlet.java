package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Mock.MealMock;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * tingaev@gmail.com on 31.03.17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to meals");

        req.setAttribute("meals", MealsUtil
                .getFilteredWithExceeded(
                        MealMock.getMeals(),
                        LocalDateTime.of(2015, 5, 30, 1, 0).toLocalTime(),
                        LocalDateTime.of(2015, 5, 31, 23, 59).toLocalTime(),
                        2000));

        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
