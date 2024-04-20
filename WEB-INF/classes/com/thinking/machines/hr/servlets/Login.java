package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
public class Login extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
try
{
AdministratorBean administratorBean=(AdministratorBean)request.getAttribute("administratorBean");
String username=administratorBean.getUsername();
String password=administratorBean.getPassword();
AdministratorDAO administratorDAO=new AdministratorDAO();
try
{
AdministratorDTO administrator=administratorDAO.getByUsername(username);
if(password.equals(administrator.getPassword())==false)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError("Invalid password");
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}
HttpSession session=request.getSession();
session.setAttribute("username",username);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.jsp");
requestDispatcher.forward(request,response);

}catch(DAOException daoException)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);

}


}catch(Exception exception)
{
System.out.println(exception);
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