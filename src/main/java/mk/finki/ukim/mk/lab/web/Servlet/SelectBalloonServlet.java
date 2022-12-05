package mk.finki.ukim.mk.lab.web.Servlet;

import mk.finki.ukim.mk.lab.model.Balloon;
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
import java.io.PrintWriter;

@WebServlet(name = "selectBalloon", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;
    private final OrderService orderService;


    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService, OrderService orderService){
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
        this.orderService = orderService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        this.springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String size = req.getParameter("size");
        req.getSession().setAttribute("size", size);
        String ballooncolor = (String) req.getSession().getAttribute("color");

        Order order=(Order) req.getSession().getAttribute("order");
        order.setBalloonSize(size);
        req.getSession().setAttribute("order",order);

        Order order1 = orderService.createOrder(ballooncolor, size);

        resp.sendRedirect("/BalloonOrder");

    }
}
