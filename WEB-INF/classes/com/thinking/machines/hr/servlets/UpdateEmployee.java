package com.thinking.machines.hr.servlets;
import java.math.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class UpdateEmployee extends HttpServlet
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
EmployeeBean employeeBean=(EmployeeBean)request.getAttribute("employeeBean");
String employeeId=employeeBean.getEmployeeId();
EmployeeDAO employeeDAO=new EmployeeDAO();
EmployeeDTO employee;
try
{
employee=employeeDAO.getByEmployeeId(employeeId);
}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Employees.jsp");
requestDispatcher.forward(request,response);
}
int designationCode=employeeBean.getDesignationCode();
boolean designationCodeExists=new DesignationDAO().designationCodeExists(designationCode);
String panNumber=employeeBean.getPanNumber();
String aadharCardNumber=employeeBean.getAadharCardNumber();
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
try
{
employee=employeeDAO.getByPANNumber(panNumber);
if(employee.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
panNumberExists=true;
}
}catch(DAOException daoException)
{
panNumberExists=false;
}
try
{
employee=employeeDAO.getByAadharCardNumber(aadharCardNumber);
if(employee.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
aadharCardNumberExists=true;
}
}catch(DAOException daoException)
{
aadharCardNumberExists=false;
}

if(designationCodeExists==false || panNumberExists==true || aadharCardNumberExists==true)
{
if(designationCodeExists==false)
{
ErrorBean designationCodeErrorBean=new ErrorBean();
designationCodeErrorBean.setError("Invalid Designation");
request.setAttribute("designationCodeErrorBean",designationCodeErrorBean);
}
if(panNumberExists)
{
ErrorBean panNumberErrorBean=new ErrorBean();
panNumberErrorBean.setError("PAN Number : "+panNumber+" exists");
request.setAttribute("panNumberErrorBean",panNumberErrorBean);
}
if(aadharCardNumberExists)
{
ErrorBean aadharCardNumberErrorBean=new ErrorBean();
aadharCardNumberErrorBean.setError("Aadhar card number : "+aadharCardNumber+" exists");
request.setAttribute("aadharCardNumberErrorBean",aadharCardNumberErrorBean);
}
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeEditForm.jsp");
requestDispatcher.forward(request,response);
return;
}

try
{
employee=new EmployeeDTO();
employee.setEmployeeId(employeeBean.getEmployeeId());
employee.setName(employeeBean.getName());
employee.setDesignationCode(employeeBean.getDesignationCode());
employee.setDesignation(employeeBean.getDesignation());
String dateOfBirthString=employeeBean.getDateOfBirth();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
Date dateOfBirth=null;
try
{
dateOfBirth=simpleDateFormat.parse(dateOfBirthString);
}catch(ParseException parseException)
{

}
employee.setDateOfBirth(dateOfBirth);
employee.setGender(employeeBean.getGender());
employee.setIsIndian(employeeBean.getIsIndian());
BigDecimal basicSalary=new BigDecimal(employeeBean.getBasicSalary());
employee.setBasicSalary(basicSalary);
employee.setPANNumber(employeeBean.getPanNumber());
employee.setAadharCardNumber(employeeBean.getAadharCardNumber());
employeeDAO.update(employee);
MessageBean messageBean=new MessageBean();
messageBean.setHeading("Employee (Updated Module)");
messageBean.setMessage("Employee updated");
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
ErrorBean errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeEditForm.jsp");
requestDispatcher.forward(request,response);
}
}catch(Exception exception)
{
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