package com.thinking.machines.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.dl.*;
public class UpdateDesignation extends HttpServlet
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


DesignationBean designationBean=(DesignationBean)request.getAttribute("designationBean");
int code=designationBean.getCode();
String title=designationBean.getTitle();
DesignationDTO designation=new DesignationDTO();
designation.setCode(code);
designation.setTitle(title);
DesignationDAO designationDAO=new DesignationDAO();
try
{
designationDAO.updateDesignation(designation);
MessageBean messageBean=new MessageBean();
messageBean.setHeading("Designation (Edit Module)");
messageBean.setMessage("Designation updated");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("Ok");
messageBean.setButtonOneAction("Designations.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);
}catch(DAOException daoException)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/DesignationEditForm.jsp");
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