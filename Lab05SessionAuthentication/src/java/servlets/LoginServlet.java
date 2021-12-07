package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
import utilities.CookieUtil;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        Cookie[] cookies = request.getCookies();
        String username = CookieUtil.getCookieValue(cookies, "username");
        request.setAttribute("cookies", cookies);

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = "";
        String password = "";
        username = request.getParameter("userName");
        password = request.getParameter("password");

        Cookie cookie = new Cookie("username", username);
        response.addCookie(cookie);

        AccountService as = new AccountService();

        User user = as.login(username, password);

        request.setAttribute("user", user);

        if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute("invalidEntry", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("home");
        }
    }

}
