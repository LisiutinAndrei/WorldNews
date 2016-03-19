main = {};
main.actor = {};
main.actor.details = (function () {

    var _map = null;
    var _markersArray = [];

    var init = function (actor) {
        actor = JSON.parse(actor.replace(/&quot;/g, '"'));
        if (!actor.events || actor.events.length == 0) {
            loadMap();
            return;
        }
        var lastEvent = actor.events[0];
        var lastEventCoords = lastEvent.geolocation.centroid;

        loadMap(lastEventCoords.x, lastEventCoords.y);
        var route = [];
        $.each(actor.events, function (index, e) {
            var centroid = e.geolocation.centroid;
            route.push(centroid);
            var url = "/event/details/" + e.eventID;
            addMarker(centroid.x, centroid.y, "<a href=\"" + url + "\">" + e.name + "</a>")
        });

        addPolyline(route);
    };

    var loadMap = function (latitude, longtitude) {
        latitude = latitude || 0;
        longtitude = longtitude || 0;
        var ge = new google.maps.LatLng(latitude, longtitude);
        var mapOptions = {
            minZoom: 4,
            zoom: 5,
            center: ge,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        _map = new google.maps.Map(document.getElementById("map"), mapOptions);
    };

    var addMarker = function (latitude, longtitude, info) {
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(latitude, longtitude),
            map: _map,
            title: info
        });

        var contentString = '<div id="content">' + info + '</div>';
        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.open(_map, marker);
        });
        _markersArray.push(marker);
    };

    var addPolygon = function (coordinates) {
        var latLongCoords = [];
        $.each(coordinates, function (index, e) {
            latLongCoords.push({
                lat: e.x,
                lng: e.y
            });
        });

        var polygon = new google.maps.Polygon({
            paths: latLongCoords,
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35
        });
        polygon.setMap(_map);
    };

    var addPolyline = function (coordinates) {
        $.each(coordinates, function (index, e) {
            if (index == 0) {
                return;
            }
            var latLongCoords = [];
            latLongCoords.push({
                lat: coordinates[index-1].x,
                lng: coordinates[index-1].y
            });
            latLongCoords.push({
                lat: e.x,
                lng: e.y
            });

            var polyline = new google.maps.Polyline({
                path: latLongCoords,
                geodesic: true,
                strokeColor: '#FF0000',
                strokeOpacity: 1.0,
                strokeWeight: 2,
                icons: [{
                    icon: {path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW},
                    offset: '100%'
                }]
            });
            polyline.setMap(_map);
        });


    };

    // Deletes all markers in the array by removing references to them
    function deleteAllMarkers() {
        if (_markersArray) {
            for (i in _markersArray) {
                _markersArray[i].setMap(null);
            }
            _markersArray.length = 0;
        }
    }

    var _startDate = function () {
        return $('#startDate');
    };

    var _endDate = function () {
        return $('#endDate');
    };

    var getStartDate = function () {
        var value = _startDate().val();
        return value ? new Date(value) : null;
    };

    var getEndDate = function () {
        var value = _endDate().val();
        return value ? new Date(value) : null;
    };

    var setStartDate = function (startDate) {
        var endDate = getEndDate();
        if (startDate && endDate) {
            if (startDate > endDate) {
                startDate = endDate;
            }
        }
        var value = startDate ? startDate.toISOString().substring(0, 10) : null;
        _startDate().val(value)
        return value;
    };

    var setEndDate = function (endDate) {
        var startDate = getStartDate();
        if (endDate && startDate) {
            if (startDate > endDate) {
                endDate = startDate;
            }
        }
        var value = endDate ? endDate.toISOString().substring(0, 10) : null;
        _endDate().val(value)
        return value;
    };

    var loadEvents = function () {
        var startDate = getStartDate();
        var endDate = getEndDate();

        var lat0 = _map.getBounds().getNorthEast().lat();
        var lat1 = _map.getBounds().getSouthWest().lat();

        var lng0 = _map.getBounds().getNorthEast().lng();
        var lng1 = _map.getBounds().getSouthWest().lng();

        var data = {
            startDate: startDate,
            endDate: endDate,
            minLatitude: lat1 >= lat0 ? lat0 : lat1,
            maxLatitude: lat1 >= lat0 ? lat1 : lat0,
            minLongtitude: lng1 >= lng0 ? lng0 : lng1,
            maxLongtitude: lng1 >= lng0 ? lng1 : lng0
        };

        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            data: data,
            url: '/map/getEvents'
        }).done(function (json) {
            if (!json || !json.responseCode || json.responseCode != 'OK') {
                console.log('Error: ajax response is empty.');
                return;
            }

            if (!json.responseData) {
                return;
            }

            deleteAllMarkers();
            $.each(json.responseData, function (index, e) {
                var centroid = e.geolocation.centroid;
                var url = "/event/details/" + e.eventID;
                addMarker(centroid.x, centroid.y, "<a href=\"" + url + "\">" + e.name + "</a>");
            });
        }).fail(function () {
            console.log('Error: ajax call failed.');
        });
    };

    return {
        init: init,
        loadMap: loadMap,
        addMarker: addMarker,
        loadEvents: loadEvents
    };
}());