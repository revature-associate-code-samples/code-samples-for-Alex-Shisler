/**
 * Programmer: Alex Shisler EmployeeLogin.js
 */

/*
 * //time intervals console.log(4); setTimeout (() => console.log(5),500);
 * //syntax below means same as the above line setTimeout (function (){
 * console.log(6); }, 500);
 */

// simple ajax call /request

/*
 * var xhr = new XMLHttpRequest(); xhr.onreadystatechange = function(){
 * console.log("response text: "+ xhr.responseText+ "http status: "+ xhr.status +
 * "http status text: "+ xhr.statusText + "readyState: "+ xhr.readyState ); }
 * xhr.open("GET",
 * "file:///D:/my_git_repos/1810_oct8_jta/Alex_Shisler_Code/ProjectOne/src/main/webapp/HTML/EmployeeLoginPage.html");
 * xhr.send();
 */


/*
 * http status codes 100 - information 200 - success 300 - redirect 400 - client
 * error 500 - server error
 */

var jsonResponse;
var isRedirect = false;

	// window.location.replace("EmployeePage.html");
window.onload = function() {
	console.log("Test");
	let btn = document.getElementById("loggin");
	
		btn.addEventListener("click", login);
}
	//btn.addEventListener("click", ()=> 




function login(){
	event.preventDefault();
	// for every readystate, onreadystatechange will be executed
	var xhr = new XMLHttpRequest();// create instance of xmlhttprequest
									// first step of ajax
	document.getElementById("crP").innerHTML = "";
	xhr.onreadystatechange = function(){
		console.log(xhr.status);
		if((xhr.readyState == 4) && xhr.status == 200){
			request = "";
			console.log("test2");
			 console.log(xhr.responseText);
			 jsonResponse = JSON.parse(xhr.responseText);
				 if(!isRedirect)
					requestRedirect();
				 isRedirect = true;
			 
		}// response received.
		else if(xhr.readyState == 4){
			console.log("invalid credentials")
			document.getElementById("crP").innerHTML = "Invalid Credentials, please try again.";
			document.getElementById("crP").style.color = "red";
		}
			
	
	
	};
	let formData = document.getElementById("logginForm");
	if(formData[0].value != null &&  formData[1].value != null){
		console.log("yay");
	let user = {
		username : formData[0].value,
		password : formData[1].value
			
	};// new user
	user.username = formData[0].value;
	user.password = formData[1].value;
		let  loginfo = JSON.stringify(user);
		// console.log("EMAIL " + user.email);
		 console.log(loginfo);
		// xhr2.open("POST", "http://localhost:6016/ProjectV1/?email=" +
		// user.email + "&password=" + user.password + "&request=" +
		// request, true);
		
		 xhr.open("POST", "api?request=login");
		 xhr.setRequestHeader("Content-Type", "application/json");
		 //console.log("Test" + xhr.responseText);
		xhr.send(loginfo.toString());
	
	}
};

function requestRedirect(){
	console.log("Redirection Test");
	var requestRedirect = new XMLHttpRequest();
	requestRedirect.onreadystatechange = function(){
		//console.log(xhr.status);
		if((requestRedirect.readyState == 4) && requestRedirect.status == 200){
			request = "";
			console.log("Success");
			console.log(requestRedirect.responseText);
			//location.href = requestRedirect.responseText;
			document.write(requestRedirect.responseText);
		}// response received.
	};//response received
		requestRedirect.open("GET", "api?request=redirectLogin");
		 requestRedirect.setRequestHeader("Content-Type", "application/json");
		 //console.log("Test" + xhr.responseText);
		let json = JSON.stringify(jsonResponse);
		document.cookie= "email=" + jsonResponse.email;
		document.cookie= "username=" + jsonResponse.username;
		console.log("Cookies yo " + getCookie("email"));
		console.log("Cookies yo " + getCookie("username"));
		console.log(json.toString());
		requestRedirect.send();
};


//------------------Get Cookie--------------------//
function getCookie(cname){
	 var name = cname + "=";
	    var decodedCookie = decodeURIComponent(document.cookie);
	    var ca = decodedCookie.split(';');
	    for(var i = 0; i <ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0) == ' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) == 0) {
	            return c.substring(name.length, c.length);
	        }
	    }
	    return "";
};

