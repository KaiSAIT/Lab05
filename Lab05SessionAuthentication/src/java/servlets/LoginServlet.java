package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String logout = "";
        logout = request.getParameter("logout");
        
        if ( logout != null ) {
            
            session.invalidate();
            request.setAttribute("logoutMessage", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
            
        }
        
        if ( session.getAttribute("username") != null ) {
            
            response.sendRedirect("home"); 
            return;
            
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String username = "";
        String password = "";
        username = request.getParameter("userName");
        password = request.getParameter("password");

        AccountService as = new AccountService();

        User user = as.login(username, password);

        if ( username == null || username.equals("") || password == null || password.equals("") ) {
            
            user = user.login(username, password);
            
            if ( user != null ) {
                
                session.setAttribute("username", user.getUsername());
                response.sendRedirect("home");
                return;
                
            } 
            
            else {
                
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("invalidMessage", true);
                response.sendRedirect("login");
                return;
                
            }
            
        }
        
    }

}
