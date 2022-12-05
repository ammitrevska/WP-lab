package mk.finki.ukim.mk.lab.web.Servlet;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BalloonOrder", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;




    public BalloonOrderServlet (UserService userService, ShoppingCartService shoppingCartService, SpringTemplateEngine springTemplateEngine, OrderService orderService){
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context =  new WebContext(req, resp, req.getServletContext());
        this.springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        String dateCreated = req.getParameter("dateCreated");

        //TODO: User Service
            User user = userService.getCient(clientName);
            req.getSession().setAttribute("user", user);

        req.getSession().setAttribute("clientAddress", clientAddress);
        Order order = (Order) req.getSession().getAttribute("order");



        //TODO: ShoppingCart
      //  ShoppingCart shoppingCart = ShoppingCartService.createCart(user, dateCreated, order);

        resp.sendRedirect("/ConfirmationInfo");
    }
}
