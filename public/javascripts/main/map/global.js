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
        //addMarker(-34.397, 150.644, 'test');
    };

    var addMarker = function (latitude, longtitude, info) {
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(latitude, longtitude),
            map: _map,
            title: "Hello World!"
        });

        var contentString = '<div id="content">' + info + '</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.open(_map, marker);
        });
    };

    var loadEvents = function () {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            url: '/map/getEvents'
        }).done(function (json) {
                debugger;
                if (!json || !json.responseCode || json.responseCode != 'OK') {
                    alert('error!');
                    return;
                }

                if (!json.responseData) {
                    return;
                }

                $.each(json.responseData, function (index, e) {
                    var centroid = e.geolocation.centroid;
                    addMarker(centroid.x, centroid.y, e.provenance.source);
                });
            }).fail(function () {
                alert("error");
            });
    };

    return {
        init: init,
        loadMap: loadMap,
        addMarker: addMarker,
        loadEvents: loadEvents
    };
}());

$(document).ready(function () {
    main.map.global.init();
    main.map.global.loadEvents();
});
