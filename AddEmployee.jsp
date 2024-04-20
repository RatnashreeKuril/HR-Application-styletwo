<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Guard>
<jsp:forward page='/LoginForm.jsp' />
</tm:Guard>
<tm:Module name='EMPLOYEE' />
<tm:FormResubmitted>
<jsp:forward page='/notifyFormResubmission' />
</tm:FormResubmitted>
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:setProperty name='employeeBean' property='*' />
<jsp:forward page='/addEmployee' />