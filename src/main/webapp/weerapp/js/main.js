var ipApiUrl = "https://ipapi.co/json/";
//var countryApi = "https://restcountries.eu/rest/v2/all";
var countryApi = "/restservices/countries"
var openWeatherMapUrl = "https://api.openweathermap.org/data/2.5/weather?units=metric&appid=15f8b16ba77633865f4a480cff38a0e3";

function initPage() {
	getRequest(ipApiUrl, function(x) {
		setOwnLocation(x);
		loadCountries();
		showWeather(x.latitude, x.longitude, x.city);
	});
}

function showWeather(latitude, longitude, city) {
	console.log("\nShowing Weather...");

	if (checkIfStored(city)) {
		let x = getFromStorage(city);
		if (x == null) {
			showUnstoredWeather(latitude, longitude, city);	
		} else {
			setCurrentWeather(x, city);	
		}
	} else {
		showUnstoredWeather(latitude, longitude, city);	
	}		
}

function showUnstoredWeather(latitude, longitude, city) {
	getRequest(openWeatherMapUrl + "&lat=" + latitude + "&lon=" + longitude, function(x) {
		saveToStorage(city, x);
		setCurrentWeather(x, city);
	});
}

function showWeatherByCurrentLocation() {
	let latitude = document.getElementById('latitude').innerHTML;
	let longitude = document.getElementById('longitude').innerHTML;
	let city = document.getElementById('stad').innerHTML;

	this.showWeather(latitude, longitude, city);
}

function loadCountries() {
	getRequest(countryApi, function(x){
		setTableData(x);
	});
}

function initModal() {
	let modal = document.getElementById("modal");
	let btn = document.getElementById("openModalAddCountry");
	let span = document.getElementsByClassName("close")[0];

	btn.onclick = function() {
	  modal.style.display = "block";
	}

	span.onclick = function() {
	  modal.style.display = "none";
	}

	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
}

function saveCountry() {
    let code = document.getElementById('newCode');
    let iso = document.getElementById('newIso3');
    let name = document.getElementById('newName');
    let capital = document.getElementById('newCapital');
    let continent = document.getElementById('newContinent');
    let region = document.getElementById('newRegion');
    let surface = document.getElementById('newSurface');
    let population = document.getElementById('newPopulation');
    let government = document.getElementById('newGov');
    let latitude = document.getElementById('newLat');
    let longitude = document.getElementById('newLong');

    if (code.value == "" || iso.value == "" || name.value == "" || capital.value == "" || continent.value == "" || region.value == "" || surface.value == "" || population.value == "" || government.value == "" || latitude.value == "" || longitude == "") {
        alert("Je bent 1 of meerdere waardes vergeten in te vullen.");
        location.reload();
    }

    let data = {};
    data.code = code.value;
    data.iso3 = iso.value;
    data.name = name.value;
    data.capital = capital.value;
    data.continent = continent.value;
    data.region = region.value;
    data.surface = surface.value;
    data.population = population.value;
    data.government = government.value;
    data.latitude = latitude.value;
    data.longitude = longitude.value;
    let json = JSON.stringify(data);

    fetch(countryApi + "/" + json + "/save", {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + sessionStorage.getItem("sessionToken")
        }
    }).then(response => response.json())
    .then(data => {
        if (data[0].success === null || data[0].success === false) {
            alert("Er is iets misgegaan. Het land kon niet worden opgeslagen. Weet u zeker dat deze landcode niet al bestaat?");
        } else {
            location.reload();
        }
    }).catch(error => {
        console.log(error);
        alert("Er is iets mis gegaan. Het land kon niet worden opgeslagen.");
    });
}

function checkLoggedInStatus() {
    let createButton = document.getElementById("openModalAddCountry");
    if (sessionStorage.getItem("sessionToken") === null)
        createButton.style.display = "none";
}

initPage();
initModal();
checkLoggedInStatus();
