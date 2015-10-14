var UserController = function($scope, imageUpload) {
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/imageUploads";
        imageUpload.uploadFileToUrl(file, uploadUrl);
    };
};
app.controller("UserController", UserController);
