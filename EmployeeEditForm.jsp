<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE' />
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:useBean id='panNumberErrorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<jsp:useBean id='aadharCardNumberErrorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/EmployeeEditForm.js'></script>
<h2>Employee (Edit Module)</h2>
<form method='post' action='/styletwo/UpdateEmployee.jsp' onsubmit='return validateForm(this)'>
<tm:FormID/>
<input type='hidden' id='employeeId' name='employeeId' value='${employeeBean.employeeId}'>
<table>
<tr>
<td>Name</td>
<td><input type='text' id='name' name='name' maxlength='50' size='51' value='${employeeBean.name}'>
<span id='nameErrorSection' class='error'></span></td>
</tr>
<tr>
<td>Designation</td>
${employeeBean.designationCode}
<input type='hidden' name='designation' id='designation' value='${employeeBean.designation}'>
<td><select id='designationCode' name='designationCode'>
<option value='0'>&lt;Select Designation&gt;</option>

<option selected value='${employeeBean.designationCode}'>${employeeBean.designation}</option>
<tm:EntityList populatorClass='com.thinking.machines.hr.bl.DesignationBL' populatorMethod='getAll' name='designationBean'>

<option value='${designationBean.code}'>${designationBean.title}</option>
</tm:EntityList>
</select>


<span id='designationCodeErrorSection' class='error'></span></td>
</tr>
<tr>
<td>Date of birth</td>
<td><input type='date' id='dateOfBirth' name='dateOfBirth' value='${employeeBean.dateOfBirth}'>
<span id='dateOfBirthErrorSection' class='error'></span>
</td>
</tr>

<tr>
<td>Gender</td>
<td>


<tm:If condition='${employeeBean.gender==\'M\'}'>
<input checked type='radio' id='male' name='gender' value='M'> Male
&nbsp;&nbsp;&nbsp;&nbsp
</tm:If>

<tm:If condition='${employeeBean.gender!=\'M\'}'>
<input type='radio' id='male' name='gender' value='M'> Male
&nbsp;&nbsp;&nbsp;&nbsp
</tm:If>


<tm:If condition='${employeeBean.gender==\'F\'}'>
<input checked type='radio' id='female' name='gender' value='F'> Female
</tm:If>

<tm:If condition='${employeeBean.gender!=\'F\'}'>
<input type='radio' id='female' name='gender' value='F'> Female
</tm:If>

<span id='genderErrorSection' class='error'></span>
</td>
</tr>

<tr>
<td>Indian</td>
<td>

<tm:If condition='${employeeBean.isIndian}'>
<input checked type='checkbox' id='isIndian' name='isIndian' value=true>
</tm:If>

<tm:If condition='${!employeeBean.isIndian}'>
<input type='checkbox' id='isIndian' name='isIndian' value=true>
</tm:If>

</td>
</tr>
<tr>
<td>Basic salary</td>
<td>
<input type='text' id='basicSalary' name='basicSalary' style='text-align:right' maxlength='12' size='13' value='${employeeBean.basicSalary}'>
<span id='basicSalaryErrorSection' class='error'></span>
</td>
</tr>
<tr>
<td>PAN Number</td>
<td>
<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='${employeeBean.panNumber}'>
<span id='panNumberErrorSection' class='error'>${panNumberErrorBean.error}</span>
</td>
</tr>

<tr>
<td>Aadhar card Number</td>
<td>
<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='${employeeBean.aadharCardNumber}'>
<span id='aadharCardNumberErrorSection' class='error'>${aadharCardNumberErrorBean.error}</span>
</td>
</tr>

<tr>
<td colspan='2'><button type='submit'>Update</button>
&nbsp;&nbsp;<button type='Button' onclick='cancelUpdate()'>Cancel</button></td>
</tr>
</table>
</form>
<form id='cancelUpdateForm' action='/styletwo/Employees.jsp'>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />
