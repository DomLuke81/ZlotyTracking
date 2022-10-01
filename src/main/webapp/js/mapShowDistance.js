function initMap() {
    const directionsService = new google.maps.DirectionsService();
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const map = new google.maps.Map(document.getElementById("map"));
    calculateAndDisplayRoute(directionsService, directionsRenderer);
    directionsRenderer.setMap(map);
}


function calculateAndDisplayRoute(directionsService, directionsRenderer) {
    let waypts = [];
    const trasaTd = document.querySelectorAll(".trasa");

    const start = trasaTd[0].innerHTML;
    const end = trasaTd[trasaTd.length - 1].innerHTML;
    for (let i = 1; i < trasaTd.length - 1; i++) {
        waypts.push({
            location: trasaTd[i].innerHTML,
            stopover: true,
        });
    }
    console.log(start);
    console.log(waypts);
    console.log(end);

    directionsService
        .route({
            origin: start,
            destination: end,
            waypoints: waypts,
            optimizeWaypoints: true,
            travelMode: google.maps.TravelMode.DRIVING,
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
            const route = response.routes[0];
            // For each route, display summary information.
            trasaTd[0].innerHTML = "";
            for (let i = 0; i < route.legs.length; i++) {
                trasaTd[i+1].innerHTML = route.legs[i].distance.text;
            }
        })
        .catch((e) => window.alert("Directions request failed due to " + status));
}

window.initMap = initMap;
