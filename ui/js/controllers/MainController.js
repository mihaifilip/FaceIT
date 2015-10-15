var MainController = function($scope, $http , $location, $document) {
	$scope.showRegister = true;
	$scope.hideLogin = true;
	$scope.isRegister = true;
	$scope.isLogin = false;
	
	$scope.loginClass = "login fadeInDown";
	$scope.registerClass = "register fadeInDown";
	
	$scope.onSigninClick = function(){
		$scope.isRegister = false;
		$scope.isLogin = true;
		$scope.hideLogin = false;
		$scope.registerClass = "register fadeOutDown";
	};
	$scope.onRegisterClick = function(){
		$scope.isRegister = false;
		$scope.isLogin = true;
		$scope.hideLogin = false;
		$scope.registerClass = "register fadeOutDown";
	};
	$scope.onLoginClick = function(){
		$location.path('.#/menu');
	};
	$scope.onTokenClick = function(){
		
	};
};
app.controller("MainController", MainController);
