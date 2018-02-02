var app = angular.module('mainApp');
app.requires.push('angularUtils.directives.dirPagination');/*'angucomplete', 'summernote','angular-loading-bar',*/
app.config(function ($stateProvider , $urlRouterProvider) {
    $stateProvider
        .state('list', {
            url:"/list",
            templateUrl: '/assets/views/rankMaster/rankMasterList.html',
            controller: 'listController',
            resolve: {
                auth: function ($q, userPersistenceService) {
                    var userInfo = userPersistenceService.getCookieData();
                    if (userInfo.mobileNo) {
                        return $q.when(userInfo.mobileNo);
                    } else {
                        return $q.reject({ authenticated: false });
                    }
                }
            }

        });
    $urlRouterProvider.otherwise('/list');
});
app.run(["$rootScope", "$location", function ($rootScope, $location) {
    $rootScope.$on("$routeChangeSuccess", function (userInfo) {
        console.log(userInfo.mobileNo);
    });
    $rootScope.$on("$routeChangeError", function (event, current, previous, eventObj) {
        if (eventObj.authenticated === false) {
            console.log("false");
            $location.path("/dashboard");
        }
    });
}]);

app.controller('listController', function($scope, $http, $window, $timeout, $filter,$location ){

    $scope.validationOptions = {
        rules: {
            rankName: {
                required: true
            }

        },
        messages: {
            rankName: {
                required: "This filed required"
            }
        }
    }

    $scope.getAllRankList = function(){
        $http({
            url: '/service/rankMasterList',
            method: 'get',
            header: 'application/json'
        }).success(function (response) {
            console.log(response);
            $scope.rankList = response;
        });
    };
    $( document ).ready(function() {
        console.log("document.ready");
        $scope.getAllRankList();
    });

    $scope.addModal = function(){
        $scope.rank = undefined;
        $scope.status = undefined;
    };
    $scope.save = function(rank ,form){


        alert("hey rank master")

        form.validate();
        if(form.validate()) {

            $('#save').prop("disabled", "true");
            $http({
                url: '/service/rankMasterCreate ',
                method: 'post',
                data: rank,
                header: 'application/json'
            }).success(function (response) {
                console.log("response=" + response);
                if (response == "success") {
                    $('.succesmsgfile').show();
                    $scope.status = "Saved.";
                    $scope.getAllRankList();
                    $('#addModal').modal('toggle');
                    $('#save').removeAttr("disabled");
                }
                else {
                    $('.succesmsgfile').show();
                    $scope.status = "Error! Unable to save.";
                    $('#save').removeAttr("disabled");
                }
            });
        }
    };

    $scope.updateModal = function(rank){
        $scope.rank = angular.copy(rank);
        $scope.status = undefined;
    };
    $scope.updateSave = function(rank ,form){

        form.validate();
        if(form.validate()) {
            $('#updateSave').prop("disabled", "true");
            $http({
                url: '/service/rankMasterUpdate',
                method: 'post',
                data: rank,
                header: 'application/json'
            }).success(function (response) {
                console.log("response=" + response);
                if (response == "success") {
                    $('.succesmsgfile').show();
                    $scope.status = "Saved.";
                    $scope.getAllRankList();
                    $('#updateModal').modal('toggle');
                    $('#updateSave').removeAttr("disabled");
                }
                else {
                    $('.succesmsgfile').show();
                    $scope.status = "Error! Unable to update.";
                    $('#updateSave').removeAttr("disabled");
                }
            });
        }
    };

    $scope.deleteModal = function(rank){
        $scope.rank = angular.copy(rank);
        $scope.status = undefined;
    };
    $scope.deleteSave=function(rank){
        $('#deleteSave').prop("disabled", "true");
        var request = $http({
            url: '/service/rankMasterDelete/'+rank.id,
            method: 'get',
            header: 'application/json'
        }).success(function (response) {
            console.log("response="+response);
            if(response=="success"){
                $scope.status ="Deleted.";
                $scope.getAllRankList();
                $('#deleteModal').modal('toggle');
                $('#deleteSave').removeAttr("disabled");
            }
            else{
                $scope.status ="Error! Unable to delete.";
                $('#deleteSave').removeAttr("disabled");
            }
        });
    }

});

app.controller('rankMasterCtrl', function ($scope, $http, $window, $timeout) {});