package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class EditDesignation extends HttpServlet
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
}catch(Exception exception)
{

}
int code=0;
try
{
code=Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException nfe)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
}
}

DesignationDAO designationDAO=new DesignationDAO();
try
{
DesignationDTO designation=designationDAO.getDesignationByCode(code);
DesignationBean designationBean=new DesignationBean();
System.out.println(designation.getCode());
System.out.println(designation.getTitle());
designationBean.setCode(designation.getCode());
designationBean.setTitle(designation.getTitle());
request.setAttribute("designationBean",designationBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/DesignationEditForm.jsp");
requestDispatcher.forward(request,response);
}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
}
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