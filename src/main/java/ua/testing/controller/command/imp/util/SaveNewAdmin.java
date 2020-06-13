package ua.testing.controller.command.imp.util;

import org.mindrot.jbcrypt.BCrypt;
import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.dao.UserDAO;
import ua.testing.model.entity.RoleType;
import ua.testing.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class SaveNewAdmin implements Command {
    @InjectByType
    private UserDAO userDAO;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            userDAO.save(new User(RoleType.ADMIN, "admin", BCrypt.hashpw("root", BCrypt.gensalt())));
        } catch (SQLException e) {
            System.out.println("|---Error while adding admin, username 'admin' already exist---|");
        }
        response.sendRedirect("/app");
    }
}
