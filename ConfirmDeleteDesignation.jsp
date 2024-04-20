<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION' />
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/ConfirmDeleteDesignation.js'></script>
<h2>Designation (Delete Module)</h2>
<form method='post' action='/styletwo/DeleteDesignation.jsp'>
<tm:FormID/>
Designation : <b>${designationBean.title}</b><br><br>
<input type='hidden' name='code' id='code' value='${designationBean.code}'>
Are you sure you want to delete ?<br><br>
<button type='submit'>Yes</button>
<button type='Button' onclick='cancelDeletion()'>No</button>
</form>
<form id='cancelDeletionForm' action='/styletwo/Designations.jsp'>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />