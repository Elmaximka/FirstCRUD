package main.java.servlet;

import main.java.service.UserService;
import com.google.gson.Gson;
import main.java.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    UserService userService = UserService.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson(userService.getAllUsers()));
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.cleanUp();
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("post")) {
            userService.addUser(new User(req.getParameter("name"), req.getParameter("password"), req.getParameter("gender")));
        }
        if (req.getPathInfo().contains("delete")) {
            userService.deleteUser(req.getParameter("name"));
        }
        if (req.getPathInfo().contains("change")) {
            User user = userService.getUserByName(req.getParameter("name"));
            if (!req.getParameter("newName").equals("") && !req.getParameter("password").equals("")) {
                user.setName(req.getParameter("newName"));
                user.setPassword(req.getParameter("password"));
            } else if (!req.getParameter("newName").equals("")) {
                user.setName(req.getParameter("newName"));
            } else if (!req.getParameter("password").equals("")) {
                user.setPassword(req.getParameter("newName"));
            }
            user.setGender(req.getParameter("gender"));
            if (userService.getUserByName(req.getParameter("newName")) == null) {
                userService.deleteUser(req.getParameter("name"));
            } else {
                resp.getWriter().println("Unavailiable name, try another name");
            }
            userService.addUser(user);
        }
        doGet(req, resp);
    }
}
