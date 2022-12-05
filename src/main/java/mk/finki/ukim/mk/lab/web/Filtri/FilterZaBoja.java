//package mk.finki.ukim.mk.lab.web.Filtri;
//
//import mk.finki.ukim.mk.lab.model.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class FilterZaBoja implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        javax.servlet.Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String pathUri = request.getRequestURI();
//        String color = (String) request.getSession().getAttribute("color");
//
//        String path = request.getServletPath();
//
//        if(color == null){
//            color = request.getParameter("color");
//        }
//        if(!("/balloons".equals(path)) && color == null && !(pathUri.startsWith("/balloons/delete/")
//                &&  !("/balloons/add-form".equals(path)))){
//            response.sendRedirect("/balloons");
//        }else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        javax.servlet.Filter.super.destroy();
//    }
//}