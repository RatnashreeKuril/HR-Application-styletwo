package com.thinking.machines.hr.servlets;
import java.math.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class DeleteEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
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

String employeeId=request.getParameter("employeeId");
EmployeeDAO employeeDAO=new EmployeeDAO();
try
{
EmployeeDTO employee=employeeDAO.getByEmployeeId(employeeId);
employeeDAO.deleteByEmployeeId(employeeId);
MessageBean messageBean;
messageBean=new MessageBean();
messageBean.setHeading("Employee (Delete Module)");
messageBean.setMessage("Employee deleted");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("Employees.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);

}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Employees.jsp");
requestDispatcher.forward(request,response);
}

}catch(Exception exception)
{
System.out.println(exception);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
// do nothing
}
}
}

}