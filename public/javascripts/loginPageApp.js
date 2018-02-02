/**
 * Created by admin on 09-06-2017.
 */
var app = angular.module("mainApp",[]);

app.controller('LoginCtrl', function($scope,$http) {

    $scope.login = function(data){
        localStorage.setItem("userName",data.userName);
        console.log("data -> "+data);
        if(data.userName == "" || data.userName == undefined || data.userName == null){
            alert("Enter usernmae !!");
        }else if(data.password == "" || data.password == undefined || data.password == null){
            alert("Enter password !!")
        }else{
            $http({
                url:'/validate',
                method:'POST',
                header:'application/json',
                data:data
            }).success(function (response) {
                console.log("response="+response);
                if(response == "Super User"){
                    window.location.href='/passengerDetails';
                }else if(response == "Operation") {
                    window.location.href='/passengerDetails';
                }else if(response=="account"){
                    window.location.href='/customerDetail';
                }else if(response=="Accounts"){
                    window.location.href='/passengerDetails';
                }else{
                    alert("invalid username and password !!");
                }
                userPersistenceService.setCookieData(response)
            }).error(function (response){
                alert("Please check username and password");
            });
        }

    }
});