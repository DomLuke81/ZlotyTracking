let tdEdition = document.getElementById("edition-cell");

let denominationRadios = document.querySelectorAll(".denominationRadios")
denominationRadios.forEach(function (radio) {
    radio.addEventListener("click", function (event) {
        getEditions(event.target.value);
    })
})

function getEditions(denomination) {
    fetch("http://localhost:8080/json/edition/" + denomination, {method: 'GET'})
        .then(function (response) {
            if (response.ok) {
                return response.json()
            }
            throw new Error("Ooops")
        })
        .then(function (data) {
            drawEditionRadios(data);
        })
        .catch(function (err) {
            console.log(err)
        })
}

function drawEditionRadios(noteTypes) {
    tdEdition.innerText = "";
    noteTypes.forEach(function (noteType) {
        const newRadio = document.createElement("input");
        newRadio.setAttribute("type", "radio");
        newRadio.setAttribute("name", "noteTypeDto.id");
        newRadio.setAttribute("id", "noteTypeDto" + noteType.id);
        newRadio.setAttribute("value", noteType.id);
        tdEdition.append(newRadio);

        const newLabel = document.createElement("label");
        newLabel.setAttribute("for", "noteTypeDto" + noteType.id);
        newLabel.innerText = noteType.edition + " ";
        tdEdition.append(newLabel);

        const newImg = document.createElement("img");
        newImg.setAttribute("src", "data:image/jpeg;base64," + noteType.image);
        newImg.setAttribute("alt", noteType.edition);
        newImg.setAttribute("width", "250");
        newLabel.append(newImg);
    })

}