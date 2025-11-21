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
const priceHeaderTemplate = document.querySelector(".price-header-template");

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
    selectElement.innerHTML = " ";   // Sletter tidligere valgmuligheter
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
                console.error("Error ved uthenting av to suggestions: " + e);
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

function showSpinner() {
    document.getElementById("loading-spinner").classList.remove("hidden");
}

function hideSpinner() {
    document.getElementById("loading-spinner").classList.add("hidden");
}

form.addEventListener("submit", async (e) => {
    e.preventDefault(); // hindrer at siden lastes på nytt
    tripResultsDisplay.innerHTML = " ";

    showSpinner();

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
        const response = await fetch("http://localhost:8000/api/trip", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            const text = await response.text();
            console.log("Error: " + text);

            showToast(errorMsg + "\n" + text)
        }

        if (response.ok) {
            console.log("Vellykket request og response");
            const formFieldset = document.querySelector(".form-fieldset");
            const mapWalking = document.getElementById("map");
            const tripResults = document.querySelector(".trip-results")

            formFieldset.style.display = "none"; // fjerner fra skjermen når responsdelen dukker opp
            tripResults.style.display = "block";
            mapWalking.style.display = "block";


        }

        // Lagrer responsen fra backend som json-format slik at det er enklere å jobbe med det
        const data = await response.json();
        console.log(data.customerPrice);
        console.log(data.tripPatterns);

        //console.log(data.tripPatterns[0].legs[1].steps.length);

        if((data.tripPatterns.length) > 0) {
            const tripSummaryHeaderClone = tripSummaryHeaderTemplate.content.cloneNode(true);
            const duration = secondsToHourMin(data.tripPatterns[0].duration);
            tripSummaryHeaderClone.querySelector(".duration-value").textContent = duration;
            tripResultsDisplay.appendChild(tripSummaryHeaderClone);

            const priceHeaderClone = priceHeaderTemplate.content.cloneNode(true);
            // priceHeaderClone.querySelector(".price").textContent = data.customerPrice;
            priceHeaderClone.querySelector(".client").textContent = `${formData.person.charAt(0).toUpperCase() + formData.person.slice(1)}: ${Math.ceil(data.customerPrice)} kr`;

            tripResultsDisplay.appendChild(priceHeaderClone);

            for (let i = 0; i < data.tripPatterns[0].legs.length; i++) {
                const tripDetailsClone = tripDetailsTemplate.content.cloneNode(true);
                const tripStepsMapClone = tripMapTemplate.content.cloneNode(true);
                const departureTime = new Date(data.tripPatterns[0].legs[i].expectedStartTime);
                const arrivalTime = new Date(data.tripPatterns[0].legs[i].expectedEndTime);

                if ((data.tripPatterns[0].legs[i].steps.length) === 0) {
                    tripDetailsClone.querySelector(".fraStedNavn").textContent = data.tripPatterns[0].legs[i].fromPlace.name;
                    //tripDetailsClone.querySelector(".departure-time").textContent = new Date(data.tripPatterns[0].legs[i].expectedStartTime).getHours() + ":" + String(new Date(data.tripPatterns[0].legs[i].expectedStartTime).getMinutes()).padStart(2,0);
                    tripDetailsClone.querySelector(".tilStedNavn").textContent = data.tripPatterns[0].legs[i].toPlace.name;
                    //tripDetailsClone.querySelector(".arrival-time").textContent = new Date(data.tripPatterns[0].legs[i].expectedEndTime).getHours() + ":" + String(new Date(data.tripPatterns[0].legs[i].expectedEndTime).getMinutes()).padStart(2,0);
                    tripDetailsClone.querySelector(".departure-time").textContent =
                        String(departureTime.getHours()).padStart(2, "0") + ":" +
                        String(departureTime.getMinutes()).padStart(2, "0");
                    tripDetailsClone.querySelector(".arrival-time").textContent =
                        String(arrivalTime.getHours()).padStart(2, "0") + ":" +
                        String(arrivalTime.getMinutes()).padStart(2, "0");



                    if ((data.tripPatterns[0].legs[i].line.transportMode) === "rail") {
                        tripDetailsClone.querySelector(".transportmode").textContent = "Tog";
                    }
                    else if ((data.tripPatterns[0].legs[i].line.transportMode) === "bus") {
                        tripDetailsClone.querySelector(".transportmode").textContent = "Buss";}
                    else if ((data.tripPatterns[0].legs[i].line.transportMode) === "tram") {
                        tripDetailsClone.querySelector(".transportmode").textContent = "Trikk";}
                    else if ((data.tripPatterns[0].legs[i].line.transportMode) === "air") {
                        tripDetailsClone.querySelector(".transportmode").textContent = "Fly";
                    } else {
                        tripDetailsClone.querySelector(".transportmode").textContent = data.tripPatterns[0].legs[i].line.transportMode;
                    }

                    tripDetailsClone.querySelector(".line-id").textContent = "(" + data.tripPatterns[0].legs[i].line.id + ")";
                    tripDetailsClone.querySelector(".line-name").textContent = data.tripPatterns[0].legs[i].line.name;
                    tripResultsDisplay.appendChild(tripDetailsClone);
                }

                else {
                    tripStepsMapClone.querySelector(".fraStedNavn").textContent = data.tripPatterns[0].legs[i].fromPlace.name;
                    tripStepsMapClone.querySelector(".tilStedNavn").textContent = data.tripPatterns[0].legs[i].toPlace.name;
                    tripStepsMapClone.querySelector(".avstand").textContent = "Gå i " + Math.floor(data.tripPatterns[0].legs[i].distance) + " meter";
                    tripResultsDisplay.appendChild(tripStepsMapClone);


                    /*
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

                     */
                }

            }

            const startPLat = data.tripPatterns[0].legs[0].fromPlace.latitude;
            const startPlon = data.tripPatterns[0].legs[0].fromPlace.longitude;

            let arrLength = data.tripPatterns[0].legs.length;

            const endPLat = data.tripPatterns[0].legs[arrLength - 1].toPlace.latitude;
            const endPLon = data.tripPatterns[0].legs[arrLength - 1].toPlace.longitude;

            let routeCoordinates = [[startPLat, startPlon], [endPLat, endPLon]];

            const map = L.map('map').setView([startPLat, startPlon], 17);

            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                // Max zoom level available for these tiles
                maxZoom: 19,
                // Attribution is required by most tile providers
                attribution: '© OpenStreetMap contributors'
            }).addTo(map);

            L.marker([startPLat, startPlon])
                .addTo(map).bindPopup('Turens start posisjon')
                .openPopup();
            L.marker([endPLat, endPLon])
                .addTo(map).bindPopup('Turens slutt posisjon')
                .openPopup();

            const polyline = L.polyline(routeCoordinates, {
                color: 'blue',
                weight: 5,
                opacity: 0.7
            });

            polyline.addTo(map);
            map.fitBounds(polyline.getBounds());

            hideSpinner();
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

    return hours + " t  -  " + minutes + " min";
}
document.querySelector(".recommendationButton").addEventListener('click', () => {
    const mainRecommendationsSection = document.querySelector(".main-rec-section");
    mainRecommendationsSection.style.display = "block";
});
/* 
document.querySelector(".recommendationButtonAdventure").addEventListener('click', () => {
    const mainRecommendationsSection = document.querySelector(".main-rec-section");
    mainRecommendationsSection.style.display = "block";
});
*/


 // For toastBox
let toastBox = document.getElementById('toastBox');
let routeSaved = "<i class=\"fa-solid fa-heart\" style=\"color: #74C0FC;\"></i>Ny rute lagret!\nSe dine Favoritter øverst på siden ( ♥ )";
let routeRemoved = "<i class=\"fa-solid fa-heart-crack\" style=\"color: #74C0FC;\"></i>Ruten er fjernet fra dine lagrede ruter.";
let errorMsg = "<i class=\"fa-solid fa-circle-xmark\" style='color: #74C0FC'></i>Noe gikk galt - ";

 function showToast(msg) {
     let toast = document.createElement('div');
     toastBox.style.display = "block";
     toastBox.scrollIntoView( {behavior: "smooth"});
     toastBox.style.right = '1.5rem';


     toast.classList.add('toast');
     toast.innerHTML = msg;
     toastBox.appendChild(toast);


     setTimeout(()=>{
     toastBox.remove();
     },6000)
 }

 document.getElementById("unfilled-heart").addEventListener('click', function() {
     const unfilledHeart = document.getElementById("unfilled-heart");
     const filledHeart = document.getElementById("full-heart");
     unfilledHeart.style.display = "none";
     filledHeart.style.display = "block";

     showToast(routeSaved);
 });
 document.getElementById("full-heart").addEventListener('click', function() {
    const unfilledHeart = document.getElementById("unfilled-heart");
    const filledHeart = document.getElementById("full-heart");
    unfilledHeart.style.display = "block";
    filledHeart.style.display = "none";

    showToast(routeRemoved);
});

document.querySelector('.contactSection').addEventListener('click', function (){
    const contact = document.getElementById('contact');
    contact.style.display = 'block';
});



const selectSight = document.getElementById("city");

selectSight.addEventListener("change", async (e) => {
    const selectedIndex = e.target.selectedIndex;
    const selectedOption = e.target.options[selectedIndex].textContent;
    // console.log(selectedOption);

    try {
        const response = await fetch("http://localhost:8000/api/sights", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(selectedOption)
        });

        console.log(response);

        if (!response.ok) {
            const res = await response.text();
            console.log("Message: " + res);
        }

        if(response.ok) {
            console.log("Successfull request and response");

            const res = await response.json();
            console.log(res);

            const cityName = document.querySelector(".cityName");
            const sights = document.querySelector(".sights");


            function foodPlaces(option) {

                // Nullstiller verdiene i main-recommendations-info
                for (let i = 0; i < sights.childElementCount; i++) {
                    cityName.textContent = "ingen matsteder ble funnet!";
                    sights.children.item(i).textContent = "";
                }


                if (selectedOption === "Fredrikstad") {
                    for (let i = 0; i < sights.childElementCount; i++) {
                        cityName.textContent = res[0][0].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;

                    }
                }

                else if (selectedOption === "Sarpsborg") {
                    for (let i = 0; i < sights.childElementCount; i++) {
                        cityName.textContent = res[0][0].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;

                    }
                }

                else if (selectedOption === "Moss") {
                    for (let i = 0; i < res[0].length; i++) {
                        cityName.textContent = res[0][0].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;
                    }
                }

                else if (selectedOption === "Halden") {
                    for (let i = 0; i < res[0].length; i++) {
                        cityName.textContent = res[0][0].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;
                    }
                }
                else if (selectedOption === "Oslo") {
                    for (let i = 0; i < res[0].length; i++) {
                        cityName.textContent = res[0][0].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;
                    }
                }
            }

            
            function adventure(option){
                for (let i = 0; i < sights.childElementCount; i++) {
                    cityName.textContent = "ingen matsteder ble funnet!";
                    sights.children.item(i).textContent = "";
                }


                if (selectedOption === "Fredrikstad") {
                    for (let i = 0; i < sights.childElementCount; i++) {
                        cityName.textContent = res[0][1].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;

                    }
                }

                else if (selectedOption === "Sarpsborg") {
                    for (let i = 0; i < sights.childElementCount; i++) {
                        cityName.textContent = res[0][1].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;

                    }
                }

                else if (selectedOption === "Moss") {
                    for (let i = 0; i < res[0].length; i++) {
                        cityName.textContent = res[0][1].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;
                    }
                }

                else if (selectedOption === "Halden") {
                    for (let i = 0; i < res[0].length; i++) {
                        cityName.textContent = res[0][1].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;
                    }
                }
                else if (selectedOption === "Oslo") {
                    for (let i = 0; i < res[0].length; i++) {
                        cityName.textContent = res[0][1].city;
                        sights.children.item(i).textContent = res[0][i].placeName + " - " + res[0][i].placeType;
                    }
                }
                    

            }

            foodPlaces(selectedOption);

            /*function sights() {

            }*/

        }

    }

    catch (e) {
        console.error(e);
    }

})

document.getElementById("buyTicketButton").addEventListener('click', () => {
    const ticketControllerSection = document.getElementById("ticket-controller");
    ticketControllerSection.style.display = "block";
});

document.getElementById("ticketControllerButton").addEventListener('click', () => {
    const ticketControllerSection = document.getElementById("ticket-controller");
    ticketControllerSection.style.display = "none";
});
