const formSection = document.querySelector(".search-form");
const form = document.getElementById("tripForm");
const from = document.getElementById("from");
const to = document.getElementById("to");
let fromStopPlaceId;
let toStopPlaceId;

const journeyDuration = document.querySelector(".duration-value");
const tripDetailsTemplate = document.querySelector(".trip-details-template");
const tripMapTemplate = document.querySelector(".trip-kart-template");
const tripSummaryHeaderTemplate = document.querySelector(".summary-header-template");
const tripResultsDisplay = document.querySelector(".trip-results-display");

// laster siden på https
if (location.protocol !== "https:") {
    location.protocol = "https:";
}

let placeInfo = [
    {
        "from": "",
        "fromStopPlaceId": ""
    },
    {
        "to": "",
        "toStopPlaceId": ""
    }
]


let searchTimer;
const fromSelect = document.createElement("select");
fromSelect.id = "from-suggestion-select";
fromSelect.name = "from-field";
fromSelect.size = 4;


const toSelect = document.createElement("select");
toSelect.id = "to-suggestion-select";
toSelect.name = "to-field";
toSelect.size = 4;

function removeSuggestionSelect(selectElement) {
    if(selectElement.parentNode) {
        selectElement.parentNode.removeChild(selectElement);
    }
}

function setUpSuggestionSelect(inputElement, selectElement) {
    selectElement.innerHTML = "";   // Sletter tidligere valgmuligheter
    inputElement.parentNode.insertBefore(selectElement, inputElement.nextSibling); // Legger dropdown elementet nær den input elementet som den hører til

    selectElement.classList.add('suggestion-select');
    selectElement.style.display = "block";
}

function placeInfoFinder() {
    from.addEventListener("input",  (e) => {
        const value = e.target.value.trim();

        if(value.length < 2) {
            removeSuggestionSelect(fromSelect);
            clearTimeout(searchTimer);
            return;
        }

        clearTimeout(searchTimer);
        searchTimer = setTimeout( async () => {
            try {
                const req = await fetch(`https://api.entur.io/geocoder/v1/autocomplete?lang=no&text=${value}`);
                const reqData = await req.json();

                if (reqData.features.length > 0) {
                    setUpSuggestionSelect(from, fromSelect);

                    reqData.features.map((item) => {
                        const option = document.createElement("option");
                        option.value = item.properties.id;
                        option.text = item.properties.label;
                        fromSelect.appendChild(option);
                    });
                }

                else {
                    removeSuggestionSelect(fromSelect);
                }
            }

            catch (e) {
                console.error("Error ved henting av from suggestions: " + e);
                removeSuggestionSelect(fromSelect)
            }

            }, 300)

        })

    fromSelect.addEventListener("change", (e) => {
        const selectedIndex = e.target.selectedIndex;
        const selectedOption = e.target.options[selectedIndex];

        from.value = selectedOption.text;
        placeInfo[0].from = from.value;
        placeInfo[0].fromStopPlaceId = selectedOption.value;
        removeSuggestionSelect(fromSelect);
    })

    to.addEventListener("input", async (e) => {
        const value = e.target.value.trim();
        if (value.length < 2) {
            removeSuggestionSelect(toSelect);
            clearTimeout(searchTimer);
            return;
        }

        clearTimeout(searchTimer);
        searchTimer = setTimeout(async () => {
            try {
                const req = await fetch(`https://api.entur.io/geocoder/v1/autocomplete?lang=no&text=${value}`);
                const reqData = await req.json();

                if (reqData.features.length  > 0) {
                    setUpSuggestionSelect(to, toSelect);
                    reqData.features.map((item) => {
                        const option = document.createElement("option");
                        option.value = item.properties.id;
                        option.text = item.properties.label;
                        toSelect.appendChild(option);
                    });
                } else {
                    removeSuggestionSelect(toSelect);
                }

            } catch (e) {
                console.error("Error ved henting av to suggestions: " + e);
                removeSuggestionSelect(toSelect);
            }
        }, 300);
    });


    toSelect.addEventListener("change", (e) => {
        const selectedIndex = e.target.selectedIndex;
        const selectedOption = e.target.options[selectedIndex];

        to.value = selectedOption.text;
        placeInfo[1].to = to.value;
        placeInfo[1].toStopPlaceId = selectedOption.value;

        removeSuggestionSelect(toSelect);
    });

}


placeInfoFinder();
console.log(placeInfo);


form.addEventListener("submit", async (e) => {
    e.preventDefault(); // hindrer at siden lastes på nytt
    tripResultsDisplay.innerHTML = "";


    console.log("Skjemaet ble sendt!");
    const formData = {
        tripType: document.getElementById("trip").value,
        person: document.getElementById("person").value,
        from: placeInfo[0].from,
        fromPlace: placeInfo[0].fromStopPlaceId,
        to: placeInfo[1].to,
        toPlace: placeInfo[1].toStopPlaceId,
        date: document.getElementById("date").value,
        time: document.getElementById("time").value
    };
    console.log(formData);


    try {
        const response = await fetch("https://localhost:8443/api/trip", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            const text = await response.text();
            console.log("Error: " + text);
        }

        if (response.ok) {
            console.log("Vellykket request og response");
            const formFieldset = document.querySelector(".form-fieldset");
            formFieldset.style.marginLeft = "-200px";
            tripResultsDisplay.style.visibility = "visible";
        }

        // Lagrer responsen fra backend som json format for å enklere jobbe med det
        const data = await response.json();
        console.log(data);

        //console.log(data.tripPatterns[0].legs[1].steps.length);

        if((data.tripPatterns.length) > 0) {
            const tripSummaryHeaderClone = tripSummaryHeaderTemplate.content.cloneNode(true);
            const duration = secondsToHourMin(data.tripPatterns[0].duration);
            tripSummaryHeaderClone.querySelector(".duration-value").textContent = duration;
            tripResultsDisplay.appendChild(tripSummaryHeaderClone);


            for (let i = 0; i < data.tripPatterns[0].legs.length; i++) {
                const tripDetailsClone = tripDetailsTemplate.content.cloneNode(true);
                const tripStepsMapClone = tripMapTemplate.content.cloneNode(true);


                if ((data.tripPatterns[0].legs[i].steps.length) === 0) {
                    tripDetailsClone.querySelector(".fraStedNavn").textContent = data.tripPatterns[0].legs[i].fromPlace.name;
                    tripDetailsClone.querySelector(".departure-time").textContent = " - " + new Date(data.tripPatterns[0].legs[i].expectedStartTime).getHours() + ":" + String(new Date(data.tripPatterns[0].legs[i].expectedStartTime).getMinutes()).padStart(2,0);
                    tripDetailsClone.querySelector(".tilStedNavn").textContent = data.tripPatterns[0].legs[i].toPlace.name;
                    tripDetailsClone.querySelector(".arrival-time").textContent = " - " + new Date(data.tripPatterns[0].legs[i].expectedEndTime).getHours() + ":" + String(new Date(data.tripPatterns[0].legs[i].expectedEndTime).getMinutes()).padStart(2,0);

                    if ((data.tripPatterns[0].legs[i].line.transportMode) === "rail") {
                        tripDetailsClone.querySelector(".transportmode").textContent = "Transporttype: " + "Tog";
                    }

                    else {
                        tripDetailsClone.querySelector(".transportmode").textContent = "Transporttype: " + data.tripPatterns[0].legs[i].line.transportMode;
                    }

                    tripDetailsClone.querySelector(".line-id").textContent = "Linjeid: " + data.tripPatterns[0].legs[i].line.id;
                    tripDetailsClone.querySelector(".line-name").textContent = "Linjenavn: " + data.tripPatterns[0].legs[i].line.name;
                    tripResultsDisplay.appendChild(tripDetailsClone);
                }

                else {
                    tripStepsMapClone.querySelector(".fraStedNavn").textContent = data.tripPatterns[0].legs[i].fromPlace.name;
                    tripStepsMapClone.querySelector(".tilStedNavn").textContent = data.tripPatterns[0].legs[i].toPlace.name;
                    tripStepsMapClone.querySelector(".avstand").textContent = "Gå i " + Math.floor(data.tripPatterns[0].legs[i].distance) + "m (se på kartet nedenfor)";
                    tripResultsDisplay.appendChild(tripStepsMapClone);

                    if (L.DomUtil.get('map') !== null) {
                        L.DomUtil.get('map')._leaflet_id = null;
                    }

                    const map = L.map('map').setView([data.tripPatterns[0].legs[i].steps[0].latitude, data.tripPatterns[0].legs[i].steps[0].longitude], 17);

                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; OpenStreetMap',
                        maxZoom: 19
                    }).addTo(map);

                    const latlngs = data.tripPatterns[0].legs[i].steps.map(s => [s.latitude, s.longitude]);
                    const routeLine = L.polyline(latlngs, { color: 'blue', weight: 4 }).addTo(map);

                    data.tripPatterns[0].legs[i].steps.forEach((s, index) => {
                        const popupText = `
                                <b>Steg ${index + 1}</b><br>
                                ${s.relativeDirection === "depart" ? "Start" : s.relativeDirection === "right" ? "Ta til høyre" : "Hold til venstre"}<br>
                                ${Math.round(s.distance)} m på ${s.streetName}<br>
                                Retning: ${s.heading}
                              `;
                        L.circleMarker([s.latitude, s.longitude], {
                            radius: 6,
                            fillColor: index === 0 ? "green" : (index === data.tripPatterns[0].legs[i].steps.length - 1 ? "red" : "blue"),
                            color: "#fff",
                            weight: 1,
                            opacity: 1,
                            fillOpacity: 0.9
                        }).addTo(map).bindPopup(popupText);
                    })

                    map.fitBounds(routeLine.getBounds());
                }

            }

        }



    } catch (error) {
        console.error("Kunne ikke få data fra backend!");
        console.error(error);
    }



});


function secondsToHourMin(durationInSeconds) {
    const seconds = Math.floor(Math.max(0, durationInSeconds));
    const hours = Math.floor(seconds / 3600);
    const remainingSecondsAfterHours = (seconds % 3600);
    const minutes = Math.floor(remainingSecondsAfterHours / 60);

    return hours + " time " + minutes + " min";
}

// geolocation - longtitude og latitude                                                 
const x = document.getElementById("locationDisplay");

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(success, error);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

async function success(position) {
    const lat = position.coords.latitude;
    const lon = position.coords.longitude;

    x.innerHTML = `Latitude: ${lat}<br>Longitude: ${lon}<br><br>Henter nærmeste stopp...`;

    const stops = await getNearestStops(lat, lon);
    showStops(stops);
}

function error(err) {
    x.innerHTML = "Kunne ikke hente posisjon: " + err.message;
}

// hent nærmeste stopp fra Entur
async function getNearestStops(lat, lon) {
    const url = `https://api.entur.io/geocoder/v1/reverse?point.lat=${lat}&point.lon=${lon}&boundary.circle.radius=500&size=5&layers=venue`;

    const req = await fetch(url, {
        headers: {
            "ET-Client-Name": "gruppe7-kollektivtransport-app"
        }
    });

    const data = await req.json();
    return data.features;
}
// vis stopp
function showStops(stops) {
    if (!stops || stops.length === 0) {
        x.innerHTML += "<br>Fant ingen stopp i nærheten.";
        return;
    }

    x.innerHTML += "<br><b>Nærmeste stopp:</b><br><br>";

    stops.forEach(stop => {
        x.innerHTML += `
            <b>${stop.properties.name}</b><br>
            Avstand: ${Math.round(stop.properties.distance)} meter<br><br>
        `;
    });
}