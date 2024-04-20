<jsp:useBean id='administratorBean' scope='request' class='com.thinking.machines.hr.beans.AdministratorBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<!DOCTYPE HTML>
<html lang='en'>
<head>
<meta charset='utf-8'>
<title>HR Application</title>
<script src='/styletwo/js/LoginForm.js'></script>
<link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'>
</head>
<body>
<div class='main-container'>
<img src='/styletwo/images/logo.png' class='logo'><div class='brand-name'>Thinking 
Machines</div>
<center>
<h2>Administrator</h2>

<div class='login-panel'>
<form method='post' action='/styletwo/Login.jsp' onsubmit='return validateForm(this)'>
<table>
<tr>
<td colspan='2' align='center'>
<span class='error'>
<jsp:getProperty name='errorBean' property='error' />
</span>
</td>
</tr>
<tr>
<td>Username</td>
<td><input type='text' name='username' id='username' value='${administratorBean.username}'></td>
<td><span class='error' id='usernameErrorSection'></span></td>
</tr>
<tr>
<td>Password</td>
<td><input type='password' name='password' id='password' value='${administratorBean.password}'></td>
<td><span class='error' id='passwordErrorSection'></span></td>
</tr>
<tr>
<td colspan='2' align='center'>
<button>Login</button>
</td>
</tr>
</table>
</form>
</div>

</center>
</div>
</body>
</html>