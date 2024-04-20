package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
public class DeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
RequestDispatcher requestDispatcher;
try
{
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
// do nothing
}
}


public void doPost(HttpServletRequest request, HttpServletResponse response)
{
try
{
HttpSession session=request.getSession();
String username=(String)session.getAttribute("username");
RequestDispatcher requestDispatcher;
if(username==null)
{
requestDispatcher=request.getRequestDispatcher("/LoginForm.jsp");
requestDispatcher.forward(request,response);

}

DesignationBean designationBean=(DesignationBean)request.getAttribute("designationBean");
if(designationBean==null)
{
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);
return;
}
int code=designationBean.getCode();
DesignationDAO designationDAO=new DesignationDAO();
try
{
designationDAO.deleteByCode(code);
MessageBean messageBean=new MessageBean();
messageBean.setHeading("Designation (Delete Module)");
messageBean.setMessage("Designation deleted");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("Designations.jsp");
request.setAttribute("messageBean",messageBean);

requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);

}catch(DAOException daoException)
{
requestDispatcher=request.getRequestDispatcher("/Designations.jsp");
requestDispatcher.forward(request,response);

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