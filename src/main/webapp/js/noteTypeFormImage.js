let fileInput = document.getElementById("file-input");
let fileMessage = document.getElementById("file-message");

fileInput.addEventListener("change", function () {
    if (fileInput.files.length > 0) {
        const fileSize = fileInput.files.item(0).size;
        const fileMb = fileSize / 1024 / 1024;
        if (fileMb >= 1) {
            fileMessage.innerHTML = "Rozmiar pliku jest większy niż 1MB!";
            fileInput.value = null;
        }
    }
});