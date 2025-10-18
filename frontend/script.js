const formSection = document.querySelector(".search-form");
const form = document.getElementById("tripForm");
const from = document.getElementById("from");
const to = document.getElementById("to");
let fromStopPlaceId;
let toStopPlaceId;
let placeInfo = [
    {
        "from": from.value,
        "fromStopPlaceId": fromStopPlaceId
    },
    {
        "to": to.value,
        "toStopPlaceId": toStopPlaceId
    }
]


let searchTimer;
const fromSelect = document.createElement("select");
fromSelect.innerHTML = '';
fromSelect.id = "from-suggestion-select";
fromSelect.name = "from-field";
const toSelect = document.createElement("select");
toSelect.innerHTML = '';
toSelect.id = "to-suggestion-select";
toSelect.name = "to-field";

function placeInfoFinder() {
    from.addEventListener("input",  (e) => {
        // const option = document.createElement("option");
        const ul = document.createElement("ul");
        clearTimeout(searchTimer);
        searchTimer = setTimeout( async () => {
            try {
                const req = await fetch(`https://api.entur.io/geocoder/v1/autocomplete?lang=no&text=${from.value}`);
                const reqData = await req.json();
                formSection.appendChild(fromSelect);
                fromSelect.style.position = "absolute";
                fromSelect.style.top = "220px";
                fromSelect.style.width = "86%";
                fromSelect.style.height = "45px";
                fromSelect.style.marginTop = "15px";

                reqData.features.map((item) => {
                    const option = document.createElement("option");
                    option.value = item.properties.id;
                    option.text = item.properties.label;
                    fromSelect.appendChild(option);

                    fromSelect.addEventListener("change", (e) => {
                        const selectedIndex = e.target.selectedIndex;
                        const selectedOption = e.target.options[selectedIndex];
                        from.value = selectedOption.text;
                        // console.log(selectedOption.value);
                        placeInfo[0].from = from.value;
                        placeInfo[0].fromStopPlaceId = selectedOption.value;

                    })
                })

            }

            catch (e) {
                console.error(e);
            }

            }, 1000)

        })

    to.addEventListener("input",  (e) => {
        const ul = document.createElement("ul");
        clearTimeout(searchTimer);
        searchTimer = setTimeout( async () => {
            try {
                const req = await fetch(`https://api.entur.io/geocoder/v1/autocomplete?lang=no&text=${to.value}`);
                const reqData = await req.json();
                formSection.appendChild(toSelect);
                toSelect.style.position = "absolute";
                toSelect.style.top = "350px";
                toSelect.style.width = "86%";
                toSelect.style.height = "45px";
                toSelect.style.marginTop = "15px";

                reqData.features.map((item) => {
                    const option = document.createElement("option");
                    option.value = item.properties.id;
                    option.text = item.properties.label;
                    toSelect.appendChild(option);

                    toSelect.addEventListener("change", (e) => {
                        const selectedIndex = e.target.selectedIndex;
                        const selectedOption = e.target.options[selectedIndex];
                        to.value = selectedOption.text;
                        // console.log(selectedOption.value);
                        placeInfo[1].to = to.value;
                        placeInfo[1].toStopPlaceId = selectedOption.value;

                    })
                })

            }

            catch (e) {
                console.error(e);
            }

        }, 1000)

    })

}


placeInfoFinder();



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
    /*console.log(placeInfo[0]);*/



    try {
        const response = await fetch("http://localhost:5000/form", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        });
        console.log(await response.text());
    } catch (error) {
        console.error(error);
    }
});
