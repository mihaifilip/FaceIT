var MainController = function($scope, $http , $location, $document, authService) {
	$scope.showRegister = true;
	$scope.hideLogin = true;
	$scope.isRegister = true;
	$scope.isLogin = false;
	$scope.registerStatus = false;
	$scope.loginStatus = false;
	
	$scope.loginClass = "login fadeInDown";
	$scope.registerClass = "register fadeInDown";
	
	$scope.userLoginData = {
		email: "",
		password: "",
		token: ""
	};
	
	$scope.userRegisterData = {
		username: "",
		email: "",
		password: ""
	};
	
	$scope.onSigninClick = function(){
		$scope.isRegister = false;
		$scope.isLogin = true;
		$scope.hideLogin = false;
		$scope.registerClass = "register fadeOutDown";
	};
	$scope.onRegisterClick = function(){
		authService.register($scope.userRegisterData).then(function(response){
			console.log("......."+response);
			$scope.registerStatus = response == "SUCCESS";
		});	
		$scope.isRegister = false;
		$scope.isLogin = true;
		$scope.hideLogin = false;
		$scope.registerClass = "register fadeOutDown";
	};
	$scope.onLoginClick = function(){
		authService.login($scope.userLoginData).then(function(response){
			console.log("......."+response);
			$scope.loginStatus = response === "SUCCESS";
			console.log(response == "\"SUCCESS\"");
		});	
	};
};
app.controller("MainController", MainController);
