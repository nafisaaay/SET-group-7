const form = document.getElementById("tripForm");
const from = document.getElementById("from");
const to = document.getElementById("to");
let fromPlace;
let toPlace;

from.addEventListener("input", async (e) => {
    e.preventDefault();
    let result = [];

    function setResult(i) {
        result.push(i);
    }
    if (from.value != "") {
        try {
            const response = await fetch(`https://api.entur.io/geocoder/v1/autocomplete?lang=no&text=${from.value}`);
            const data = await response.json();
            console.log(data);
            setResult(data);

        }

        catch (error) {
            console.error(error);
        }
    }

});


form.addEventListener("submit", async (e) => {
    e.preventDefault(); // hindrer at siden lastes på nytt
    console.log("Skjemaet ble sendt!");
    const formData = {
        tripType: document.getElementById("trip").value,
        person: document.getElementById("person").value,
        from: from.value,
        to: to.value,
        date: document.getElementById("date").value,
        time: document.getElementById("time").value
    };

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
