<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Guard>
<jsp:forward page='/LoginForm.jsp' />
</tm:Guard>
<jsp:useBean id='administratorBean' scope='session' class='com.thinking.machines.hr.beans.AdministratorBean' />
<!DOCTYPE HTML>
<html lang='en'>
<head>
<title>HR Application</title>
<link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'>
</head>
<body>
<!-- Main container starts here -->
<div class='main-container'>
<!-- Header starts here -->
<div class='header'>


<a href='/styletwo/index.jsp'><img src='/styletwo/images/logo.png' class='logo'></a><div class='brand-name'>Thinking Machines</div>

<div align='right' style='border:1px solid white;margin:5px'>
<img src='/styletwo/images/login.png'><div style='float:right;margin-left:5px;margin-top:5px;margin-bottom:5px'>${username}&nbsp;&nbsp;&nbsp;&nbsp;
<a href='/styletwo/logout'>Logout</a>
</div>
</div>


</div>
<!-- Header ends here -->
<!-- Content-Section starts here -->
<div class='content'>
<!-- Left panel starts here -->
<div class='content-left-panel'>



<tm:If condition='${module==DESIGNATION}'>
<b>Designations</b><br>
</tm:If>
<tm:If condition='${module!=DESIGNATION}'>
<a href='/styletwo/Designations.jsp'>Designations</a><br>
</tm:If>

<tm:If condition='${module==EMPLOYEE}'>
<b>Employees</b><br>
</tm:If>
<tm:If condition='${module!=EMPLOYEE}'>
<a href='/styletwo/Employees.jsp'>Employees</a><br>
</tm:If>
<tm:If condition='${module!=HOME}'>
<br>
<a href='/styletwo/index.jsp'>Home</a><br>
</tm:If>







</div>
<!-- Left panel ends here -->
<!-- Right panel starts here -->
<div class='content-right-panel'>