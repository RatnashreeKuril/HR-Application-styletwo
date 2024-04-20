<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<tm:Module name='DESIGNATION' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/DesignationEditForm.js'></script>
<h2>Designation (Edit Module)</h2>
<span class='error'>
<jsp:getProperty name='errorBean' property='error' />
</span>
<form method='post' action='/styletwo/UpdateDesignation.jsp' onsubmit='return validateForm(this)'>
Designation
<input type='hidden' name='code' id='code' value='${designationBean.code}'>
<input type='text' id='title' name='title' maxLength='35' size='36' value='${designationBean.title}'>
<span id='titleErrorSection' class='error'></span><br>
<button type='submit'>Update</button>
<button type='Button' onclick='cancelUpdate()'>Cancel</button>
</form>
<form id='cancelUpdateForm' action='/styletwo/Designations.jsp'>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />
