var MapController = function($scope, $location,$document,xxxService,$http) {

//TODO refactor the jQuery stuff 
$(document).ready(function() {

    // set the wrapper width and height to match the img size
    $('#wrapper').css({
        'width': $('#wrapper img').width(),
        'height': $('#wrapper img').height()
    })

    //tooltip direction
    var tooltipDirection;

    for (i = 0; i < $(".pin").length; i++) {
        // set tooltip direction type - up or down             
        if ($(".pin").eq(i).hasClass('pin-down')) {
            tooltipDirection = 'tooltip-down';
        } else {
            tooltipDirection = 'tooltip-up';
        }

        // append the tooltip
        $("#wrapper").append("<div style='left:" + $(".pin").eq(i).data('xpos') + "px;top:" + $(".pin").eq(i).data('ypos') + "px' class='" + tooltipDirection + "'>\
													<div class='tooltip'>" + $(".pin").eq(i).html() + "</div>\
											</div>");
    }

    // show/hide the tooltip
    $('.tooltip-up, .tooltip-down').mouseenter(function() {
        $(this).children('.tooltip').fadeIn(100);
    }).mouseleave(function() {
        $(this).children('.tooltip').fadeOut(100);
    });
	
	// click the tooltip
	$('.tooltip-up, .tooltip-down').click(function() {
		var city = $(this).find('.cityValue').text();
		var country = $(this).find('.countryValue').text();
		console.log(city);
		console.log(country);
		var sendData = {
			email: "", 
			city:  "",
			country: ""
		};
		sendData.email = xxxService.getEmail();
		sendData.city = city;
		sendData.country = country;
		console.log(sendData);
		var url = "http://bucd464:9081/game/begin";
		console.log(sendData);
		$http.post(url, sendData).then(function(response){
			console.log(response.data);
		});
	});
});
}

app.controller("MapController", MapController);