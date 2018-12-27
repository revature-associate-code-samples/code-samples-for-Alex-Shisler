/**
 * 
 */
/*
 * Programmer: Alex Shisler
 * Manager.js
 */


	var jsonResponse;
	let welcomeTxt = document.getElementById("Welcome").innerHTML = "Welcome Manager " + getCookie("username");
	// document.getElementById("crP").innerHTML = "Invalid Credentials, please
	// try again.";
	
	
// --------------------------AJAX Info
// Retrieval-------------------------------------//
	var xhr2 = new XMLHttpRequest();
	console.log("Test" + xhr2.responseText);
	xhr2.onreadystatechange = function(){
		if((xhr2.readyState == 4) && xhr2.status >= 200 && xhr2.status < 300){
			request = "";
			jsonResponse = JSON.parse(xhr2.responseText);
			 console.log("JSON " + jsonResponse.email + " " +  jsonResponse.username);

		}// response received.
			
	
	
	};
		// console.log(loginfo);
		 xhr2.open("GET", "api?request=retrieveEmp");
		// xhr2.open("POST", "http://localhost:6016/ProjectV1/?email=" +
		// user.email + "&password=" + user.password + "&request=" +
		// request, true);
		xhr2.setRequestHeader("Content-Type", "application/json");

		xhr2.send();
	
	
// -------------------AJAX Info Retrieval-----------------------------//
	

// --------------------------------Display Info---------------------//
		let btn = document.getElementById("display");
		isShowing = false;
	btn.addEventListener("click", ()=> {
		console.log("Cookies");
		console.log("Cookies yo " + getCookie("email"));
		console.log("Cookies yo " + getCookie("username"));
	
		 displayInfo();
	});
	

	// ---------------------Update Info----------------------------//
	let updateBtn = document.getElementById("update");
	isUpdating = false;
	updateBtn.addEventListener("click", ()=> {
		 updateInfo();
	});
	// ---------------------Update Submit--------------------------//

	
	
	function sendUpdate(){
		let updtReq = new XMLHttpRequest();
		console.log("Adding reimbursements");
		console.log("Cookies: " + getCookie("email"));
		updtReq.onreadystatechange = function() {
			if ((updtReq.readyState == 4) && updtReq.status >= 200
					&& updtReq.status < 300) {	
				jsonResponse.username = document.getElementById("inUser").value;
				jsonResponse.firstName= document.getElementById("inFirst").value;
				jsonResponse.lastName = document.getElementById("inLast").value;
				jsonResponse.desc	 = document.getElementById("inDesc").value;
				displayInfo();
				document.getElementById("Welcome").innerHTML = "Welcome Back " + jsonResponse.username;
				document.cookie = "username="+jsonResponse.username;
			}// response received.
		}// statechange
		formdata = document.getElementById("inFirst");
		console.log(formdata.value);
		let empUpdate = {
			username :document.getElementById("inUser").value,
			firstName : document.getElementById("inFirst").value,
			lastName: document.getElementById("inLast").value,
			desc: document.getElementById("inDesc").value
				
		}
		let upXInfo = JSON.stringify(empUpdate);
		console.log(upXInfo.toString());
		updtReq.open("POST", "api?request=updateInfo");
		updtReq.setRequestHeader("Content-Type", "application/json");
		updtReq.send(upXInfo.toString());
	}
// ------------------Get Cookie--------------------//
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
	
	// ----------------------Display Info-------------------------//
	function displayInfo(){
		let infoList = document.getElementById("employeeInfoList");
		if(!isShowing){
		let lnBreak = document.createElement("br");
		infoList.appendChild(lnBreak);
		let list = document.createElement("li");
		list.className= "list-group-item";
		let node = document.createTextNode("Email: " + jsonResponse.email);
		list.appendChild(node);
		infoList.appendChild(list);
		list = document.createElement("li");
		list.className= "list-group-item";
		node = document.createTextNode("Username: " + jsonResponse.username);
		list.appendChild(node);
		infoList.appendChild(list);
		list = document.createElement("li");
		list.className= "list-group-item";
		node = document.createTextNode("First Name: " + jsonResponse.firstName);
		list.appendChild(node);
		infoList.appendChild(list);
		list = document.createElement("li");
		list.className= "list-group-item";
		node = document.createTextNode("Last Name: " + jsonResponse.lastName);
		list.appendChild(node);
		infoList.appendChild(list);
		list = document.createElement("li");
		list.className= "list-group-item";
		node = document.createTextNode("Title: " + jsonResponse.title);
		list.appendChild(node);
		infoList.appendChild(list);
		list = document.createElement("li");
		list.className= "list-group-item";
		node = document.createTextNode("Description: " + jsonResponse.desc);
		list.appendChild(node);
		infoList.appendChild(list);
		isShowing = true;
	}// addInfo
		else{
			while (infoList.firstChild) {
			    infoList.removeChild(infoList.firstChild);
			}
			isShowing = false;
		}
	}// displayInfo
	
	
	// --------------------------Update Info-------------------------------//
	
	function updateInfo(){
		let updateList = document.getElementById("formDiv");
		
		if(!isUpdating){
			let lnBreak = document.createElement("br");
			updateList.appendChild(lnBreak);
			// addInput(updateList, "Email", "email");
			addInput(updateList, "Username", "text", "inUser", jsonResponse.username);	
			addInput(updateList, "First Name", "text", "inFirst", jsonResponse.firstName);
			addInput(updateList, "Last Name", "text", "inLast", jsonResponse.lastName);
			addInput(updateList, "Description", "text", "inDesc", jsonResponse.desc);
		let submitInfo = document.createElement("button");
		submitInfo.className = "btn btn-dark";
		submitInfo.innerHTML = "Update";
		// submitInfo.setAttribute("id", "submitUpBtn");
		submitInfo.id = "submitUpBtn";
		updateList.appendChild(submitInfo);
			isUpdating = true;
		}// addInfo
			else{
				while (updateList.firstChild) {
				    updateList.removeChild(updateList.firstChild);
				}
				isUpdating = false;
			}
		
		let submitUpdate = document.getElementById("submitUpBtn");
		if(submitUpdate != null){
			submitUpdate.addEventListener("click", ()=> {
			sendUpdate();
		});// click
	}//if exists
	}
	
	// ------------------------addInput------------------------------------//
	
	function addInput(updateList, value, type, inID, placeHolder){
		let divInput = document.createElement("div");
		let divPend = document.createElement("div");
		divPend.className = "input-group-prepend";
		divInput.className = "input-group mb-3";
		let span = document.createElement("span");
		span.className = "input-group-text";
		span.innerHTML = value;
		let input = document.createElement("input");
		input.className = "form-control";

		input.id = inID;
		input.setAttribute("type", type);
		input.setAttribute("value", placeHolder);
		updateList.appendChild(divInput);
		divInput.appendChild(divPend);
		divPend.appendChild(span);
		divInput.appendChild(input);
		
	}
	
	// --------------------------Logout-----------------------------------//
	
	
	
	let logoutBtn = document.getElementById("Logout");
	logoutBtn.addEventListener("click", ()=> {
		var lgoutReq = new XMLHttpRequest();
		console.log("Test" + lgoutReq.responseText);
		lgoutReq.onreadystatechange = function(){
			if((lgoutReq.readyState == 4) && lgoutReq.status >= 200 && lgoutReq.status < 300){
				request = "";
				console.log(lgoutReq.responseText);
				document.write(lgoutReq.responseText);
			}// response received.
		};
			
			lgoutReq.open("GET", "api?request=logout");
			lgoutReq.setRequestHeader("Content-Type", "application/json");
			lgoutReq.send();
			
			console.log("Cookies Logout");
			deleteCookie("email");
			deleteCookie("username");
			console.log("Cookies yo " + getCookie("email"));
			console.log("Cookies yo " + getCookie("username"));

	});
	
	// ------------------------------deleteCookies--------------------------//
	function deleteCookie(cName) {
	    document.cookie = cName + '=; Max-Age=0'
	}
