	function showReg(){
		document.getElementById("loginForm").style.display= 'none';
		document.getElementById("regLink").style.display= 'none';
		document.getElementById("regForm").style.display= 'block';
		document.getElementById("loginLink").style.display= 'block';
	}
	
	function showLogin(){
		document.getElementById("regForm").style.display= 'none';
		document.getElementById("loginLink").style.display= 'none';
		document.getElementById("loginForm").style.display= 'block';
		document.getElementById("regLink").style.display= 'block';
	}