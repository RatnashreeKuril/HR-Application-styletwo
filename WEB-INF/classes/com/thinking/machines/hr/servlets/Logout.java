package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
HttpSession session=request.getSession();
session.removeAttribute("username");
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/ErrorPages.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{

}

}

}
}