/**
 * Programmer: Alex Shisler EmployeeRequests.js
 */


var isEmployAll = false;
var onlyPRequests = false;
var onlyRRequests = false;
var allRequests = false;
var hasData = false;
var jsonTable;
// ------------------------Vars-----------------//

	let btn = document.getElementById("showEmps");
	btn.addEventListener("click", ()=> {
		console.log(isEmployAll);
		if(!hasData){
			isEmployAll = true;
			onlyPRequests = false;
			onlyRRequests = false;
			allRequests = false;
			getEmps();
		}// retrieveInfo
		else
			showEmployees(jsonTable);
	});// show all employees
	
	let pBtn = document.getElementById("showPending");
	pBtn.addEventListener("click", ()=> {
		if(!hasData){
			isEmployAll = false;
			onlyPRequests = true;
			onlyRRequests = false;
			allRequests = false;
			getEmps();
			
		}// retrieveInfo
		else
			showPending(jsonTable);
	});// showPending
	
	let resBtn = document.getElementById("showResolved");
	resBtn.addEventListener("click", ()=> {
		if(!hasData){
			isEmployAll = false;
			onlyPRequests = false;
			onlyRRequests = true;
			allRequests = false;
			getEmps();
			
		}// retrieveInfo
		else
			showResolved(jsonTable);	
	});// showResolved
	
	let allBtn = document.getElementById("showAll");
	allBtn.addEventListener("click", ()=> {
		if(!hasData){
			isEmployAll = false;
			onlyPRequests = false;
			onlyRRequests = false;
			allRequests = true;
			getEmps();
			
		}// retrieveInfo
		else
			showAll(jsonTable);	
	});// showAll
	
	let approvBtn = document.getElementById("approveBtn");
	let denyBtn = document.getElementById("denyBtn");
	
	approvBtn.addEventListener("click", ()=> {
		var apReq = "Approved";
		if(document.getElementById("reqIDForm").value != null){
				console.log("it's time");
				let reqId = document.getElementById("reqIDForm").value;
				console.log(reqId);
				changeStatus(apReq, reqId);
			}
			else
				console.log("RIP");
		
	});
	
	denyBtn.addEventListener("click", ()=> {
		var apReq = "Denied";
		if(document.getElementById("reqIDForm").value != null){
				console.log("it's time");
				let reqId = document.getElementById("reqIDForm").value;
				console.log(reqId);
				changeStatus(apReq, reqId);
			}
			else
				console.log("RIP");
	});
// -----------------------------Event
// Handling---------------------------------//
	function showEmployees(jsonTable) {
		removeRows();
		for(let i = 0; i < jsonTable.length; i++){
			
				let row = document.createElement("tr");
				let firstCol = document.createElement("td");
				let lastCol = document.createElement("td");
				let userCol = document.createElement("td");
				let emailCol = document.createElement("td");
				let titleCol = document.createElement("td");
				let descCol = document.createElement("td");
				firstCol.textContent = jsonTable[i].firstName;
				lastCol.textContent = jsonTable[i].lastName;
				userCol.textContent = jsonTable[i].username;
				emailCol.textContent = jsonTable[i].email;
				titleCol.textContent = jsonTable[i].title;
				descCol.textContent = jsonTable[i].desc;
				row.appendChild(firstCol);
				row.appendChild(lastCol);
				row.appendChild(userCol);
				row.appendChild(emailCol);
				row.appendChild(titleCol);
				row.appendChild(descCol);
				document.getElementById("myTable").appendChild(row);
		}// all employees
		isEmployAll = true;
}// addRows()
	
	function showPending(jsonTable){
		removeRows();
		for(let i = 0; i < jsonTable.length; i++){
			for(let x = 0; x < jsonTable[i].reimburseList.length; x++){
				if(jsonTable[i].reimburseList[x].status == "Pending"){
					let row = document.createElement("tr");
					let firstCol = document.createElement("td");
					let lastCol = document.createElement("td");
					let userCol = document.createElement("td");
					let emailCol = document.createElement("td");
					let amountCol = document.createElement("td");
					let statusCol = document.createElement("td");
					let rIdCol = document.createElement("td");
					firstCol.textContent = jsonTable[i].firstName;
					lastCol.textContent = jsonTable[i].lastName;
					userCol.textContent = jsonTable[i].username;
					emailCol.textContent = jsonTable[i].email;
					statusCol.textContent = jsonTable[i].reimburseList[x].status;
					amountCol.textContent = "$" + jsonTable[i].reimburseList[x].amount;
					rIdCol.textContent = "RID = " + jsonTable[i].reimburseList[x].id;
					row.appendChild(firstCol);
					row.appendChild(lastCol);
					row.appendChild(userCol);
					row.appendChild(emailCol);
					row.appendChild(statusCol);
					row.appendChild(amountCol);
					row.appendChild(rIdCol);
					document.getElementById("myTable").appendChild(row);
				}// Pending only
			}// reimbursements
		}// all employees
		onlyRequests = true;
	}// showPending
	
	// ---------------------------------ShowPending-----------------------------//
	
	function showResolved(jsonTable){
		removeRows();
		for(let i = 0; i < jsonTable.length; i++){
			for(let x = 0; x < jsonTable[i].reimburseList.length; x++){
				if(jsonTable[i].reimburseList[x].status == "Approved" || jsonTable[i].reimburseList[x].status == "Denied"){
					let row = document.createElement("tr");
					let firstCol = document.createElement("td");
					let lastCol = document.createElement("td");
					let userCol = document.createElement("td");
					let emailCol = document.createElement("td");
					let amountCol = document.createElement("td");
					let statusCol = document.createElement("td");
					let rIdCol = document.createElement("td");
					firstCol.textContent = jsonTable[i].firstName;
					lastCol.textContent = jsonTable[i].lastName;
					userCol.textContent = jsonTable[i].username;
					emailCol.textContent = jsonTable[i].email;
					statusCol.textContent = jsonTable[i].reimburseList[x].status;
					amountCol.textContent = "$" + jsonTable[i].reimburseList[x].amount;
					rIdCol.textContent = "RID: " + jsonTable[i].reimburseList[x].id;
					row.appendChild(firstCol);
					row.appendChild(lastCol);
					row.appendChild(userCol);
					row.appendChild(emailCol);
					row.appendChild(statusCol);
					row.appendChild(amountCol);
					row.appendChild(rIdCol);
					document.getElementById("myTable").appendChild(row);
				}// Pending only
			}// reimbursements
		}// all employees
		onlyRequests = true;
	}// showResoloved
	
	
	function showAll(jsonTable){
		removeRows();
		for(let i = 0; i < jsonTable.length; i++){
			for(let x = 0; x < jsonTable[i].reimburseList.length; x++){
					let row = document.createElement("tr");
					let firstCol = document.createElement("td");
					let lastCol = document.createElement("td");
					let userCol = document.createElement("td");
					let emailCol = document.createElement("td");
					let amountCol = document.createElement("td");
					let statusCol = document.createElement("td");
					let rIdCol = document.createElement("td");
					firstCol.textContent = jsonTable[i].firstName;
					lastCol.textContent = jsonTable[i].lastName;
					userCol.textContent = jsonTable[i].username;
					emailCol.textContent = jsonTable[i].email;
					statusCol.textContent = jsonTable[i].reimburseList[x].status;
					amountCol.textContent = "$" + jsonTable[i].reimburseList[x].amount;
					rIdCol.textContent = "RID: " + jsonTable[i].reimburseList[x].id;
					row.appendChild(firstCol);
					row.appendChild(lastCol);
					row.appendChild(userCol);
					row.appendChild(emailCol);
					row.appendChild(statusCol);
					row.appendChild(amountCol);
					row.appendChild(rIdCol);
					document.getElementById("myTable").appendChild(row);
			}// reimbursements
		}// all employees
		allRequests = true;
	}
	// --------------------------------showResolved-----------------------------//
	
	function getEmps(){
		var allEmpReq = new XMLHttpRequest();
		allEmpReq.onreadystatechange = function(){
			if((allEmpReq.readyState == 4) && allEmpReq.status >= 200 && allEmpReq.status < 300){
				request = "";
				jsonTable = JSON.parse(allEmpReq.responseText);
				console.log(jsonTable);
				if(isEmployAll)
				 	showEmployees(jsonTable);
				 if(onlyPRequests)
				 	showPending(jsonTable);
				 if(onlyRRequests)
				 	showResolved(jsonTable);
				 if(allRequests)
					 showAll(jsonTable);
				hasData = true;
			}// response received.
		};// statechange
		
			// console.log(loginfo);
			 allEmpReq.open("GET", "api?request=empList");
			 allEmpReq.setRequestHeader("Content-Type", "application/json");

			allEmpReq.send();
	}
	
	// ---------------------------AJAX
	// Request--------------------------------------//

	
	
	function removeRows(){
		const table = document.getElementById("myTable");
		while (table.firstChild) {
			table.removeChild(table.firstChild);
		}
	}
	
	// ----------------------------Remove Rows-------------------------------------//
	
	function changeStatus(rStatus, reqId){
		var approvReq = new XMLHttpRequest();
		approvReq.onreadystatechange = function(){
			if((approvReq.readyState == 4) && approvReq.status >= 200 && approvReq.status < 300){
				
				console.log("Success?");
			}// response received.
		};// statechange
			
			let approvject ={
				id : reqId,
				status : rStatus,
				amount : 0.0
			};
			
			let  jsonApprv= JSON.stringify(approvject);
			// console.log(loginfo);
			 approvReq.open("POST", "api?request=approvReq");
			 approvReq.setRequestHeader("Content-Type", "application/json");
			 approvReq.send(jsonApprv.toString());
			 
			 removeRows();
			 getEmps();
	}
	
	// --------------------------AJAX changeStatus--------------------------------//
	

