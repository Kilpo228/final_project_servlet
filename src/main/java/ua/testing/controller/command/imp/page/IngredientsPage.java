package ua.testing.controller.command.imp.page;

import ua.testing.controller.command.Command;
import ua.testing.model.annotation.Controller;
import ua.testing.model.annotation.InjectByType;
import ua.testing.model.service.IngredientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IngredientsPage implements Command {
    @InjectByType
    private IngredientService ingredientService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("ingredients", ingredientService.parseQuery(request));
        request.getRequestDispatcher("/WEB-INF/admin/ingredient.jsp").forward(request, response);
    }
}
