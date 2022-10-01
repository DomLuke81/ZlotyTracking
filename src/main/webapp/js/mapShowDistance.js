/**
 * @license
 * Copyright 2019 Google LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
function initMap() {
    const directionsService = new google.maps.DirectionsService();
    const directionsRenderer = new google.maps.DirectionsRenderer();
    const map = new google.maps.Map(document.getElementById("map"));
    calculateAndDisplayRoute(directionsService, directionsRenderer);
    directionsRenderer.setMap(map);
}


function calculateAndDisplayRoute(directionsService, directionsRenderer) {
    let waypts = [];
    const placesHiddenPoles = document.querySelectorAll(".hiddenPlace");

    const start = placesHiddenPoles[0].innerHTML;
    const end = placesHiddenPoles[placesHiddenPoles.length - 1].innerHTML;
    for (let i = 1; i < placesHiddenPoles.length - 1; i++) {
        waypts.push({
            location: placesHiddenPoles[i].innerHTML,
            stopover: true,
        });
    }

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
            const summaryPanel = document.getElementById("directions-panel");

            summaryPanel.innerHTML = "";

            // For each route, display summary information.
            for (let i = 0; i < route.legs.length; i++) {
                const routeSegment = i + 1;

                summaryPanel.innerHTML +=
                    "<b>Route Segment: " + routeSegment + "</b><br>";
                summaryPanel.innerHTML += route.legs[i].start_address + " to ";
                summaryPanel.innerHTML += route.legs[i].end_address + "<br>";
                summaryPanel.innerHTML += route.legs[i].distance.text + "<br><br>";
            }
        })
        .catch((e) => window.alert("Directions request failed due to " + status));
}

window.initMap = initMap;
