<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE' />
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:useBean id='panNumberErrorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<jsp:useBean id='aadharCardNumberErrorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/EmployeeDeleteForm.js'></script>
<h2>Employee (Delete Module)</h2>
<form method='post' action='/styletwo/DeleteEmployee.jsp' onsubmit='return validateForm(this)'>
<tm:FormID/>
<input type='hidden' id='employeeId' name='employeeId' value='${employeeBean.employeeId}'>
Name : 
<b>${employeeBean.name}</b><br>
Designation : 
<b>${employeeBean.designation}</b><br>
Date of birth : 
<b>${employeeBean.dateOfBirth}</b><br>
Gender : 
<b>${employeeBean.gender}</b><br>
Nationality : 
<tm:If condition='${employeeBean.isIndian}'>
<b>Indian</b><br>
</tm:If>

<tm:If condition='${!employeeBean.isIndian}'>
<b>Non-Indian</b><br>
</tm:If>

Basic salary : 
<b>${employeeBean.basicSalary}</b><br>
PAN number : 
<b>${employeeBean.panNumber}</b><br>
Aadhar card number : 
<b>${employeeBean.aadharCardNumber}</b><br><br>
Are you sure you want to delete this employee ?<br><br>
<button type='submit'>Yes</button>
<button type='Button' onclick='cancelDeletion()'>No</button>
</form>
<form id='cancelDeletionForm' action='/styletwo/Employees.jsp'>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />
