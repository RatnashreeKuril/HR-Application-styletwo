package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
public class ConfirmDeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
HttpSession session=request.getSession();
String username=(String)session.getAttribute("username");
if(username==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);
}

int code=0;
try
{
code=Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException nfe)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);
}

DesignationDAO designationDAO=new DesignationDAO();
try
{
DesignationDTO designation=designationDAO.getDesignationByCode(code);
DesignationBean designationBean=new DesignationBean();
designationBean.setCode(designation.getCode());
designationBean.setTitle(designation.getTitle());
request.setAttribute("designationBean",designationBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/ConfirmDeleteDesignation.jsp");
requestDispatcher.forward(request,response);
}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);
}


}catch(Exception exception)
{
System.out.println(exception.getMessage());
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