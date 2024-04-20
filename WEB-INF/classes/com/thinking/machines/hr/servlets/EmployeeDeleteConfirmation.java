package com.thinking.machines.hr.servlets;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class EmployeeDeleteConfirmation extends HttpServlet
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
String employeeId=request.getParameter("employeeId");
EmployeeDAO employeeDAO=new EmployeeDAO();
try
{
EmployeeDTO employee=employeeDAO.getByEmployeeId(employeeId);
EmployeeBean employeeBean=new EmployeeBean();
employeeBean.setEmployeeId(employee.getEmployeeId());
employeeBean.setName(employee.getName());
employeeBean.setDesignationCode(employee.getDesignationCode());
employeeBean.setDesignation(employee.getDesignation());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
employeeBean.setDateOfBirth(simpleDateFormat.format(employee.getDateOfBirth()));
employeeBean.setGender(employee.getGender());
employeeBean.setIsIndian(employee.getIsIndian());
employeeBean.setBasicSalary(employee.getBasicSalary().toPlainString());
employeeBean.setPanNumber(employee.getPANNumber());
employeeBean.setAadharCardNumber(employee.getAadharCardNumber());
request.setAttribute("employeeBean",employeeBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeDeleteForm.jsp");
requestDispatcher.forward(request,response);
}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Employees.jsp");
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