/*
	Simple API for communication with the rest services provided by the back-end
	Add,retrieve,update data from the Parse database
	TODO pretty much everything
*/

var authService = function($http) {

	var postLogin = function(loginData){
		var url = "http://bucd464:9081/user/login";
		console.log(loginData);
		return $http.post(url, loginData).then(function(response){
			console.log(response.data);
			return response.data;
        });
	};
	
	var postRegister = function(registerData){
		var url = "http://bucd464:9081/user/sigin";
		console.log(registerData);
		return $http.post(url, registerData).then(function(response){
			console.log(response.data);
			return response.data;
        });
	};
	
    var getUser = function(username){
		var url = ""; // TODO
        return $http.get(url.concat(username))
                    .then(function(response){
                        return response.data; 
                    });
    };	
	
	var getPicture = function(objectID){
		var url = ""; // TODO
        return $http.get(url.concat(objectID))
                    .then(function(response){
                        return response.data; 
                    });
    };	
	
    return {
        getUser: getUser,
		getPicture: getPicture,
		login: postLogin,
		register: postRegister
		//TO be continued
    };
};
app.factory('authService', authService);