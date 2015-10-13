/*
	Simple API for communication with the rest services provided by the back-end
	Add,retrieve,update data from the Parse database
	TODO pretty much everything
*/

var parseService = function($http) {

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
		getPicture: getPicture
		//TO be continued
    };
};
app.factory('parse', parseService);