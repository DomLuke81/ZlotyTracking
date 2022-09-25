let fileInput = document.getElementById("file-input");
let fileMessage = document.getElementById("file-message");
let image = document.getElementById("image");

fileInput.addEventListener("change", function () {
    if (fileInput.files.length > 0) {
        const fileSize = fileInput.files.item(0).size;
        if (fileSize > (1024 * 1024)) {
            fileMessage.innerHTML = "Rozmiar pliku jest większy niż 1MB!";
            fileInput.value = null;
        }
        image.src = URL.createObjectURL(fileInput.files.item(0));
    }
});