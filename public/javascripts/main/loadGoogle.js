$(document).ready(function () {
    initializeGoogleMap();
});

function initializeGoogleMap() {
    var haightAshbury = new google.maps.LatLng(51.0532, 31.83);
    var mapOptions = {
        zoom: 4,
        center: haightAshbury,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map"), mapOptions);
}