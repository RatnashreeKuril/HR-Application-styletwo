package com.thinking.machines.hr.servlets;
import java.math.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class AddEmployee extends HttpServlet
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
try
{

EmployeeBean employeeBean=(EmployeeBean)request.getAttribute("employeeBean");
System.out.println(employeeBean.getIsIndian());
int code=employeeBean.getDesignationCode();
DesignationDTO designation=new DesignationDAO().getDesignationByCode(code);
employeeBean.setDesignation(designation.getTitle());
String panNumber=employeeBean.getPanNumber();
String aadharCardNumber=employeeBean.getAadharCardNumber();
EmployeeDAO employeeDAO=new EmployeeDAO();
boolean panNumberExists=employeeDAO.panNumberExists(panNumber);
boolean aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
if(panNumberExists || aadharCardNumberExists)
{
if(panNumberExists) 
{
ErrorBean panNumberErrorBean;
panNumberErrorBean=new ErrorBean();
panNumberErrorBean.setError("PAN Number : "+panNumber+" exists");
request.setAttribute("panNumberErrorBean",panNumberErrorBean);
}
if(aadharCardNumberExists)
{
ErrorBean aadharCardNumberErrorBean;
aadharCardNumberErrorBean=new ErrorBean();
aadharCardNumberErrorBean.setError("Aadhar card number : "+aadharCardNumber+" exists");
request.setAttribute("aadharCardNumberErrorBean",aadharCardNumberErrorBean);
}
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeAddForm.jsp");
requestDispatcher.forward(request,response);
}
else
{
EmployeeDTO employee=new EmployeeDTO();
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
System.out.println("Parse exception : "+parseException);
}
employee.setDateOfBirth(dateOfBirth);
employee.setGender(employeeBean.getGender());
employee.setIsIndian(employeeBean.getIsIndian());
BigDecimal basicSalary=new BigDecimal(employeeBean.getBasicSalary());
employee.setBasicSalary(basicSalary);
employee.setPANNumber(employeeBean.getPanNumber());
employee.setAadharCardNumber(employeeBean.getAadharCardNumber());
employeeDAO.add(employee);
employeeBean.setEmployeeId(employee.getEmployeeId());
MessageBean messageBean=new MessageBean();
messageBean.setHeading("Employee (Add Module)");
messageBean.setMessage("Employee added, add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("EmployeeAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Employees.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response);
}
}catch(DAOException daoException)
{
ErrorBean errorBean=new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/EmployeeAddForm.jsp");
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