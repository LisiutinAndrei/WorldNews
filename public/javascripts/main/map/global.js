main = {};
main.map = {};
main.map.global = (function () {

    var _map = null;

    var init = function () {
        loadMap();
    };

    var loadMap = function () {
        var haightAshbury = new google.maps.LatLng(51.0532, 31.83);
        var mapOptions = {
            zoom: 4,
            center: haightAshbury,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        _map = new google.maps.Map(document.getElementById("map"), mapOptions);
        addMarker();
    };

    var addMarker = function () {
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(-34.397, 150.644),
            map: _map,
            title: "Hello World!"
        });

        var contentString = '<div id="content">Тут всё то про что должно быть рассказано</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.open(_map, marker);
        });
    };

    var loadEvents = function () {

    };

    return {
        init: init,
        loadMap: loadMap,
        addMarker: addMarker
    };
}());

$(document).ready(function () {
    main.map.global.init();
});
