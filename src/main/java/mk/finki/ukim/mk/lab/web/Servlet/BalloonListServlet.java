package mk.finki.ukim.mk.lab.web.Servlet;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloonListServlet", urlPatterns = "")
public class BalloonListServlet extends HttpServlet {

    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;



    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine, OrderService orderService){
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloons", this.balloonService.listAll());

        this.springTemplateEngine.process("listBalloons.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloonColor = req.getParameter("color");
        Order order = orderService.placeOrder(balloonColor);
        req.getSession().setAttribute("color", balloonColor);
        req.getSession().setAttribute("order", order);
        resp.sendRedirect(req.getContextPath() + "/selectBalloon");
    }
}
