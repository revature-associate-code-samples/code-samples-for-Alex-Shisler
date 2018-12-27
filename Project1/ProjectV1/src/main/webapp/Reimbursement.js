/**
 * Reimbursement.js
 */
var reList
// -------------vars----------------------//
window.onload = function() {
	
	
	 reList = retrieveExistingRows();

	document.getElementById("submitBtn").addEventListener("click", addRows);
}
// ------------------------Update Rows--------------------------//
function updateRows() {
	//let status = document.getElementById("mstatus");
	let amount = document.getElementById("amount");
	for (let i = 0; i < reList.length; i++){
		console.log(reList[i]);
		let row = document.createElement("tr");
		let statcol = document.createElement("td");
		let amountcol = document.createElement("td");
		
		statcol.textContent = reList[i].status;
		amountcol.textContent = '$'
				+ reList[i].amount;
		;
		row.appendChild(statcol);
		row.appendChild(amountcol);
		document.getElementById("myTable").appendChild(row);
	}
}// updateRows()

// ------------------------Add Rows--------------------------//

function addRows() {
	//let status = document.getElementById("mstatus");
	let amount = document.getElementById("amount").value;

	console.log(amount);
	if (amount && amount > 0) {
		amount = parseFloat(Math.round(amount * 100) / 100).toFixed(2);
		document.getElementById("invalidInput").innerHTML = "";

		if (amount.trim != "") {
			let row = document.createElement("tr");
			let statcol = document.createElement("td");
			let amountcol = document.createElement("td");

			statcol.textContent = 'Pending';
			amountcol.textContent = '$' + amount;
			;
			row.appendChild(statcol);
			row.appendChild(amountcol);
			document.getElementById("myTable").appendChild(row);
			addReimbursement(amount);

		} else
			console.log("No inputs.");
	} else {
		document.getElementById("invalidInput").innerHTML = "Amount must be greater than $0.00";
		document.getElementById("invalidInput").style.color = "red";
	}

}// addRows()

// -----------------------AJAX Reimbursements---------------------------//
function addReimbursement(rAmount) {

	let reimbursReq = new XMLHttpRequest();
	console.log("Adding reimbursements");
	console.log("Cookies: " + getCookie("email"));
	reimbursReq.onreadystatechange = function() {
		if ((reimbursReq.readyState == 4) && reimbursReq.status >= 200
				&& reimbursReq.status < 300) {
			request = "";
			console.log(reimbursReq.responseText);
			// console.log("JSON " + jsonResponse.email + " " +
			// jsonResponse.username);
		}// response received.
	}// statechange

	let reimbursement = {
		status : "Pending",
		amount : rAmount
	}
	let remXInfo = JSON.stringify(reimbursement);
	reimbursReq.open("POST", "api?request=requestMoney");
	reimbursReq.setRequestHeader("Content-Type", "application/json");
	reimbursReq.send(remXInfo.toString());

}

// --------------------retrieveExistingRows-----------------------------//
function retrieveExistingRows() {
	var reListReq = new XMLHttpRequest();
	console.log("Adding reimbursements");
	console.log("Cookies: " + getCookie("email"));
	reListReq.onreadystatechange = function() {
		if ((reListReq.readyState == 4) && reListReq.status >= 200
				&& reListReq.status < 300) {
			request = "";
			console.log(reListReq.responseText);
			reList = JSON.parse(reListReq.responseText);
			for (let i = 0; i < reList.length; i++) {
				console.log(reList[i].amount);
				
			}
			updateRows();
			return reList.slice();
		}// response received.
	}// statechange
	reListReq.open("GET", "api?request=retrieveReqs");
	reListReq.send();
}

// ------------------Get Cookie--------------------//
function getCookie(cName) {
	var name = cName + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
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

// ------------------deleteCookies--------------------//


// --------------------------Logout-----------------------------------

let logoutBtn = document.getElementById("Logout");
logoutBtn.addEventListener("click", ()=> {
	var lgoutReq = new XMLHttpRequest();
	console.log("Test logout");
	lgoutReq.onreadystatechange = function(){
		if((lgoutReq.readyState == 4) && lgoutReq.status >= 200 && lgoutReq.status < 300){
			request = "";
			console.log(lgoutReq.responseText);;
			document.write(lgoutReq.responseText);
		}// response received.
	};// readystate
		lgoutReq.open("GET", "api?request=logout");
		lgoutReq.setRequestHeader("Content-Type", "application/json");
		lgoutReq.send();
		
	console.log("Cookies Logout");
	deleteCookie("email");
	deleteCookie("username");
	console.log("Cookies yo " + getCookie("email"));
	console.log("Cookies yo " + getCookie("username"));


});


function deleteCookie(cName) {
    document.cookie = cName + '=; Max-Age=0'
}


