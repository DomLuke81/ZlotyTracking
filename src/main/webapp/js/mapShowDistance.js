const trasaTd = document.querySelectorAll(".trasa");
let map;

function initMap() {
    if (trasaTd.length > 1) {
        const directionsService = new google.maps.DirectionsService();
        const directionsRenderer = new google.maps.DirectionsRenderer();
        map = new google.maps.Map(document.getElementById("map"));
        calculateAndDisplayRoute(directionsService, directionsRenderer);
        directionsRenderer.setMap(map);
    } else {
        map = new google.maps.Map(document.getElementById("map"), {
            zoom: 7,
            mapTypeControl: false,
        });
        geocode({ address: trasaTd[0].innerHTML});
    }

}


function calculateAndDisplayRoute(directionsService, directionsRenderer) {
    let waypts = [];

    const start = trasaTd[0].innerHTML;
    const end = trasaTd[trasaTd.length - 1].innerHTML;
    for (let i = 1; i < trasaTd.length - 1; i++) {
        waypts.push({
            location: trasaTd[i].innerHTML,
            stopover: true,
        });
    }

    directionsService
        .route({
            origin: start,
            destination: end,
            waypoints: waypts,
            optimizeWaypoints: true,
            travelMode: google.maps.TravelMode.BICYCLING,
        })
        .then((response) => {
            directionsRenderer.setDirections(response);
            const route = response.routes[0];
            // For each route, display summary information.
            trasaTd[0].innerHTML = "";
            for (let i = 0; i < route.legs.length; i++) {
                trasaTd[i + 1].innerHTML = route.legs[i].distance.text;
            }
        })
        .catch((e) => window.alert("Directions request failed due to " + status));
}

function geocode(request) {
    const geocoder = new google.maps.Geocoder();
    const marker = new google.maps.Marker({map,});
    geocoder
        .geocode(request)
        .then((result) => {
            const { results } = result;

            map.setCenter(results[0].geometry.location);
            marker.setPosition(results[0].geometry.location);
            marker.setMap(map);
            return results;
        })
        .catch((e) => {
            alert("Geocode was not successful for the following reason: " + e);
        });
}

window.initMap = initMap;
