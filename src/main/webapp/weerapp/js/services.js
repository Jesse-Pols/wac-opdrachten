function writeDataToInnerHTML(id, data) {
	document.getElementById(id).innerHTML = data;
}

function formatDate(date) {
	return date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}

// https://gist.github.com/felipeskroski/8aec22f01dabdbf8fb6b
var degToCard = function(deg){
  if (deg>11.25 && deg<33.75){
    return "Noord, Noord-Oost";
  }else if (deg>33.75 && deg<56.25){
    return "Oost, Noord-Oost";
  }else if (deg>56.25 && deg<78.75){
    return "Oost";
  }else if (deg>78.75 && deg<101.25){
    return "Oost, Zuid-Oost";
  }else if (deg>101.25 && deg<123.75){
    return "Oost, Zuid-Oost";
  }else if (deg>123.75 && deg<146.25){
    return "Zuid-Oost";
  }else if (deg>146.25 && deg<168.75){
    return "Zuid, Zuid-Oost";
  }else if (deg>168.75 && deg<191.25){
    return "Zuid";
  }else if (deg>191.25 && deg<213.75){
    return "Zuid, Zuid-West";
  }else if (deg>213.75 && deg<236.25){
    return "Zuid-West";
  }else if (deg>236.25 && deg<258.75){
    return "West, Zuid-West";
  }else if (deg>258.75 && deg<281.25){
    return "West";
  }else if (deg>281.25 && deg<303.75){
    return "West, Noord-West";
  }else if (deg>303.75 && deg<326.25){
    return "Noord-West";
  }else if (deg>326.25 && deg<348.75){
    return "Noord, Noord-West";
  }else{
    return "Noord"; 
  }
}

var getRequest = function(url, func) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var data = JSON.parse(this.responseText);
      func(data);
    }
    if (this.status == 400) {
        alert("Er is iets mis gegaan.");
    }
  }
  xhttp.open("GET", url, true);
  xhttp.send();
}

function checkIfStored(name) {
  if (sessionStorage.getItem(name) != null) return true; 
  return false;
}

function getFromStorage(name) {
  let item = sessionStorage.getItem(name);
  let parsed = JSON.parse(item);

  // If the item has expired: return null
  if (hasItemExpired(parsed)) {
    sessionStorage.removeItem(name);
    console.log(name + " has expired, and was removed from the sessionStorage.");
    return null;
  } 

  console.log("Retreived " + name + " from the sessionStorage.");
  return parsed;
}

function saveToStorage(name, x) {
  x.timestamp = new Date(); 
  let item = JSON.stringify(x);
  sessionStorage.setItem(name, item);

  console.log(name + " has been added to the sessionStorage.");
}

function hasItemExpired(item) {
  let date = new Date(item.timestamp);
  let expirationDate = addMinutes(date, 1);

  if (new Date() >= expirationDate) return true;
  return false;
}

function addMinutes(date, minutes) {
    return new Date(date.getTime() + minutes*60000);
}