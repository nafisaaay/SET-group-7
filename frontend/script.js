const formSection = document.querySelector(".search-form");
const form = document.getElementById("tripForm");
const from = document.getElementById("from");
const to = document.getElementById("to");
let fromStopPlaceId;
let toStopPlaceId;

const journeyDuration = document.querySelector(".journey-duration");
const fromPlace = document.querySelector(".fraStedNavn");
const departureTime = document.querySelector(".avreisetid");
const transportId = document.querySelector(".transport-id");
const name = document.querySelector(".name");
const transportMode = document.querySelector(".transportmode");
const toPlace = document.querySelector(".tilStedNavn");
const arrivalTime = document.querySelector(".ankomsttid");

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
    const formFieldset = document.querySelector(".form-fieldset");
    formFieldset.style.marginLeft = "-200px";
    const displaySection = document.querySelector(".display");
    displaySection.style.visibility = "visible";

    try {
        const response = await fetch("http://localhost:5000/api/trip", {
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
        }

        // Lagrer responsen fra backend som json format for å enklere jobbe med det
        const data = await response.json();
        //console.log(data);
        //console.log(data.tripPatterns);
        const duration = secondsToHourMin(data.tripPatterns[0].duration);
        journeyDuration.textContent = "Reisevarigheten: " + duration;
        //console.log(duration);
        fromPlace.textContent = data.tripPatterns[0].legs[0].fromPlace.name;
        toPlace.textContent = data.tripPatterns[0].legs[0].toPlace.name;
        departureTime.textContent = new Date(data.tripPatterns[0].startTime);
        arrivalTime.textContent = new Date(data.tripPatterns[0].endTime);
        transportId.textContent = "Id: " + data.tripPatterns[0].legs[0].line.id;
        name.textContent = "Name: " + data.tripPatterns[0].legs[0].line.name;
        if ((data.tripPatterns[0].legs[0].line.transportMode) === "rail") {
            transportMode.textContent = "Transporttype: Tog"
        }


    } catch (error) {
        console.error("Kunne ikke få data fra backend!");
    }



});


function secondsToHourMin(durationInSeconds) {
    const seconds = Math.floor(Math.max(0, durationInSeconds));
    const hours = Math.floor(seconds / 3600);
    const remainingSecondsAfterHours = (seconds % 3600);
    const minutes = Math.floor(remainingSecondsAfterHours / 60);

    return hours + " time " + minutes + " min";
}


