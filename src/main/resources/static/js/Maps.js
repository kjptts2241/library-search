/*
 * This demo illustrates the coordinate system used to display map tiles in the
 * API.
 *
 * Tiles in Google Maps are numbered from the same origin as that for
 * pixels. For Google's implementation of the Mercator projection, the origin
 * tile is always at the northwest corner of the map, with x values increasing
 * from west to east and y values increasing from north to south.
 *
 * Try panning and zooming the map to see how the coordinates change.
 */

// 사용자 위치 값 전달
let map, infoWindow;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 37.573404, lng: 126.976832 },
    zoom: 15,
  });
  infoWindow = new google.maps.InfoWindow();

  const locationButton = document.createElement("button");

  locationButton.textContent = "내 주변의 도서관 찾기";
  locationButton.classList.add("custom-map-control-button");
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  locationButton.addEventListener("click", () => {
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };

          infoWindow.setPosition(pos);
          infoWindow.setContent("Location found.");
          infoWindow.open(map);
          map.setCenter(pos);
        },
        () => {
          handleLocationError(true, infoWindow, map.getCenter());
        }
      );
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infoWindow, map.getCenter());
    }
  });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation."
  );
  infoWindow.open(map);
}

window.initMap = initMap;



// 맵의 위도 경도 표시계    
// class CoordMapType {
//     tileSize;
//     alt = null;
//     maxZoom = 25;
//     minZoom = 0;
//     name = null;
//     projection = null;
//     radius = 6378137;
//     constructor(tileSize) {
//       this.tileSize = tileSize;
//     }
//     getTile(coord, zoom, ownerDocument) {
//       const div = ownerDocument.createElement("div");
  
//       div.innerHTML = String(coord);
//       div.style.width = this.tileSize.width + "px";
//       div.style.height = this.tileSize.height + "px";
//       div.style.fontSize = "10";
//       div.style.borderStyle = "solid";
//       div.style.borderWidth = "1px";
//       div.style.borderColor = "#AAAAAA";
//       return div;
//     }
//     releaseTile(tile) {}
//   }

// 맵의 위도 경도 표시계     
//   function initMap() {
//     const map = new google.maps.Map(document.getElementById("map", mapOptions), {
//         zoom: 15,
//         center: { lat: 37.573404, lng: 126.976832 },
//     }); 
//     var directionsService = new google.maps.DirectionsService();
//     var directionsRenderer = new google.maps.DirectionsRenderer();
//     var haight = new google.maps.LatLng(37.7699298, -122.4469157);
//     var oceanBeach = new google.maps.LatLng(37.7683909618184, -122.51089453697205);
//     var mapOptions = {
//       zoom: 15,
//       center: haight
//     };
 
//     directionsRenderer.setMap(map);
//   }

// 사용자의 위치에서 여러 교통수단으로 가는 경로
//   function calcRoute() {
//     var selectedMode = document.getElementById('mode').value;
//     var request = {
//         origin: haight,
//         destination: oceanBeach,
//         // Note that JavaScript allows us to access the constant
//         // using square brackets and a string value as its
//         // "property."
//         travelMode: google.maps.TravelMode[selectedMode]
//     };
//     directionsService.route(request, function(response, status) {
//       if (status == 'OK') {
//         directionsRenderer.setDirections(response);
//       }
//     });
//   }

// 사용자의 위치에서 여러 교통수단으로 가는 경로
// function initMap() {
//     var directionsService = new google.maps.DirectionsService();
//     var directionsRenderer = new google.maps.DirectionsRenderer();
//     var haight = new google.maps.LatLng(37.7699298, -122.4469157);
//     var oceanBeach = new google.maps.LatLng(37.7683909618184, -122.51089453697205);
//     var mapOptions = {
//       zoom: 14,
//       center: haight
//     }
//     var map = new google.maps.Map(document.getElementById('map'), mapOptions);
//     directionsRenderer.setMap(map);
//   }
  
//   function calcRoute() {
//     var selectedMode = document.getElementById('mode').value;
//     var request = {
//         origin: haight,
//         destination: oceanBeach,
//         // Note that JavaScript allows us to access the constant
//         // using square brackets and a string value as its
//         // "property."
//         travelMode: google.maps.TravelMode[selectedMode]
//     };
//     directionsService.route(request, function(response, status) {
//       if (status == 'OK') {
//         directionsRenderer.setDirections(response);
//       }
//     });
//   }