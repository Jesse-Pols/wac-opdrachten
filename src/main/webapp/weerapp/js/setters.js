function setOwnLocation(data) {
	writeDataToInnerHTML("landcode", data.country_code);
	writeDataToInnerHTML("land", data.country_name);
	writeDataToInnerHTML("regio", data.region);
	writeDataToInnerHTML("stad", data.city);
	writeDataToInnerHTML("postcode", data.postal);
	writeDataToInnerHTML("latitude", data.latitude);
	writeDataToInnerHTML("longitude", data.longitude);
	writeDataToInnerHTML("ip", data.ip);
}

function setCurrentWeather(data, city) {
	writeDataToInnerHTML("currentCity", city);
	writeDataToInnerHTML("temperatuur", Math.round((data.main.temp) * 10) / 10);
	writeDataToInnerHTML("luchtvochtigheid", data.main.humidity);
	writeDataToInnerHTML("windsnelheid", data.wind.speed);
	writeDataToInnerHTML("windrichting", degToCard(data.wind.deg));
	writeDataToInnerHTML("zonsopgang", formatDate(new Date(data.sys.sunrise)));
	writeDataToInnerHTML("zonsondergang", formatDate(new Date(data.sys.sunset)));
    setFlag(data.sys.country);
}

function setTableData(data) {
	var table = document.getElementById("countryTable");
	for (let element of data) {
		table = setTableRow(table, element);
	}	
}

function setTableRow(table, element) {
	let row = table.insertRow();
	let land = row.insertCell();
	let hoofdstad = row.insertCell();
	let regio = row.insertCell();
	let oppervlakte = row.insertCell();
	let inwoners = row.insertCell();

	land.innerHTML = element.name;
	hoofdstad.innerHTML = element.capital;
	regio.innerHTML = element.region;
	oppervlakte.innerHTML = element.surface;
	inwoners.innerHTML = element.population;

    if (sessionStorage.getItem("sessionToken") != null) {
    	let deleteCell = row.insertCell();
    	let updateCell = row.insertCell();

        deleteCell.appendChild(createDeleteButton(element.code));
        updateCell.appendChild(createUpdateButton(land, hoofdstad, regio, oppervlakte, inwoners, element));
        updateCell.appendChild(createExecuteButton(element.name));
    }


	row.classList.add('hoverable');
	row.onclick = function() {
		if (element.capital == null || element.capital == "") {
			showWeather(element.lat, element.lng, element.name);
		} else {
			showWeather(element.lat, element.lng, element.capital);
		}					
	}
	return table;
}