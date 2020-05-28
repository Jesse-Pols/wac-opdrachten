function createDeleteButton(code) {
    let deleteButton = document.createElement('input');
    deleteButton.type = "button";
    deleteButton.value = "Verwijderen";

    deleteButton.onclick = function() {
        fetch(countryApi + "/" + code + "/delete", {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem("sessionToken")
            }
        }).then(function(response) {
            if (response.ok) {
                location.reload();
            } else {
                alert("Er is iets mis gegaan. Het land kon niet worden verwijderd.");
            }
        }).catch(error => {
            alert("Er is iets mis gegaan. Het land kon niet worden verwijderd.")
            console.log(error);
        });
    };

    return deleteButton;
}

function createUpdateButton(land, hoofdstad, regio, oppervlakte, inwoners, element) {
    let updateButton = document.createElement('input');
    updateButton.type = "button";
    updateButton.setAttribute("id", "updateButton" + element.name);
    updateButton.value = "Wijzigen";

    updateButton.onclick = function() {
        land.innerHTML = '<input type="text" value="' + element.name + '", id = "country' + element.name + '">';
        hoofdstad.innerHTML = '<input type="text" value = "' + element.capital + '", id = "capital' + element.name + '">';
        regio.innerHTML = '<input type="text" value = "' + element.region + '", id = "region' + element.name + '">';
        oppervlakte.innerHTML = '<input type="text" value = "' + element.surface + '", id = "surface' + element.name + '">';
        inwoners.innerHTML = '<input type="text" value = "' + element.population + '", id = "population' + element.name + '">';

        let tempUpdateButton = document.getElementById('updateButton' + element.name);
        let tempExecuteButton = document.getElementById('executeButton' + element.name);
        tempUpdateButton.style.display = "none";
        tempExecuteButton.style.display = "block";
    };
    return updateButton;
}

function createExecuteButton(name) {
    let executeButton = document.createElement('input');
    executeButton.type = "button";
    executeButton.setAttribute("id", "executeButton" + name);
    executeButton.style.display = "none";
    executeButton.value = "Doorvoeren";
    executeButton.setAttribute("class", "updateButtons");

    executeButton.onclick = function() {
        let land = document.getElementById('country' + name);
        let hoofdstad = document.getElementById('capital' + name);
        let regio = document.getElementById('region' + name);
        let oppervlakte = document.getElementById('surface' + name);
        let populatie = document.getElementById('population' + name);

        let data = {};
        data.oldname = name;
        data.name = land.value;
        data.capital = hoofdstad.value;
        data.region = regio.value;
        data.surface = oppervlakte.value;
        data.population = populatie.value;
        let json = JSON.stringify(data);

        fetch(countryApi + "/" + json + "/update", {
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem("sessionToken")
            }
        }).then(function(response) {
            if (response.ok) {
                location.reload();
            } else {
                alert("Er is iets mis gegaan. De update kon niet worden uitgevoerd.");
            }
        }).catch(error => {
            alert("Er is iets mis gegaan. Het land kon niet worden opgeslagen.");
            console.log(error);
        });
    }

    return executeButton;
}

function setFlag(country) {
	var flag = document.getElementById("flag");
	if (country == null) {
		flag.style = "display: none;"
	} else {
		flag.style = "display: block;"
		flag.setAttribute("class", "flag-icon flag-icon-" + country.toLowerCase());
	}
}