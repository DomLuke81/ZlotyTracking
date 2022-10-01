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
    // const checkboxArray = document.getElementById("waypoints");
    // for (let i = 0; i < checkboxArray.length; i++) {
    //     if (checkboxArray.options[i].selected) {
    //         waypts.push({
    //             location: checkboxArray[i].value,
    //             stopover: true,
    //         });
    //     }
    // }
    const placesHiddenPoles = document.querySelectorAll(".hiddenPlace");
    for (let i = 0; i < placesHiddenPoles.length; i++) {
        let loc = placesHiddenPoles[i].innerText;
        console.log(loc);
        waypts.push({
            location: loc,
            stopover: true,
        });
    }
    console.log(waypts);

    directionsService
        .route({
            origin: document.getElementById("start").value,
            destination: document.getElementById("end").value,
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
