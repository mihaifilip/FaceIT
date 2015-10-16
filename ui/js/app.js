var app = angular.module("FaceIT", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/main", {
            templateUrl: "templates/main.html",
            controller: "MainController"
        })
        .when("/game", {
            templateUrl: "templates/game.html",
            controller: "GameController"
        })
        .when("/menu", {
            templateUrl: "templates/menu.html",
            controller: "MenuController"
        })
        .when("/user/:username", {
            templateUrl: "templates/user.html",
            controller: "UserController"
        })
        .when("/user/:username/settings", {
            templateUrl: "templates/settings.html",
            controller: "SettingsController"
        })
        .when("/map", {
            templateUrl: "templates/map.html",
            controller: "MapController"
        });
        //.otherwise({
          //  redirectTo: "/main"
        //});
});

//small hack to pass data between controllers
app.service("xxxService", function () {
var email = {};

	return {
		getEmail: function () {
			return email;
		},
		setEmail: function (value) {
			email = value;
		}
	};

});