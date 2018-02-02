/**
 * Created by admin on 09-06-2017.
 */
var app = angular.module("mainApp",[]);
app.requires.push('angularUtils.directives.dirPagination','angucomplete-alt');/*'angucomplete', 'summernote','angular-loading-bar',*/

function printDiv(divID) {
    //Get the HTML of div
    var divElements = document.getElementById(divID).innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;
    //Reset the page's HTML with div's HTML only
    document.body.innerHTML =
        "<html><head><title>hello</title></head><body>" +
        divElements + "</body>";
    //Print Page
    window.print();
    //Restore orignal HTML
    document.body.innerHTML = oldPage;

}



app.controller('customerDetailCtrl', function($scope,$http) {

    $scope.getCodeGeneratorList=function () {
        $http({
            url: '/codeGeneratorList',
            method: 'get',
            header: 'application/json'
        }).success(function (response) {
            console.log(response);
            $scope.codeGeneratorList = response;
        });
    }
    $scope.getCodeGeneratorList();



   /* $scope.singleInvoiceDataList=[];
    $scope.add = function(newCustomerData){

         $scope.newCustomerData.codeGenerator={};
        newCustomerData.codeGenerator=newCustomerData.originalObject.id;
        $scope.singleInvoiceDataList.push(angular.copy(newCustomerData));
        $scope.newCustomerData="";

    }*/



    $scope.singleInvoiceDataList=[];
    $scope.add=function(newCustomerData){
        var newServiceWisePaymentTerms = angular.copy(newCustomerData);
        var customerDetailsList={};
         customerDetailsList.codeGenerator={};
         customerDetailsList.codeGenerator.id=newServiceWisePaymentTerms.originalObject.id;
         customerDetailsList.quantity=newServiceWisePaymentTerms.quantity;
         customerDetailsList.strCode=newServiceWisePaymentTerms.originalObject.strCode;
         customerDetailsList.strItemDescription=newServiceWisePaymentTerms.originalObject.strItemDescription;
         customerDetailsList. strSalingRate=newServiceWisePaymentTerms.originalObject.strSalingRate;
         customerDetailsList. strPurchesRate=newServiceWisePaymentTerms.originalObject.strPurchesRate;
         customerDetailsList.total=customerDetailsList. strSalingRate*customerDetailsList.quantity;
        $scope.singleInvoiceDataList.push(angular.copy(customerDetailsList));
        $scope.newCustomerData="";
    }

    $scope.removeFromList = function(index){
        $scope.singleInvoiceDataList.splice(index,1);
    };




    $scope.invoiceNo={};
    $scope.save=function (singleInvoiceDataList) {
        alert("heyyyyyy"+singleInvoiceDataList);
        $http({
            url:'/customerDetailCreate',
            method:'POST',
            header:'application/json',
            data:singleInvoiceDataList
        }).success(function (response) {
            /*alert(response);
            console.log("response=*******************"+response);*/
            $scope.invoiceNo = response;



             $http({
                url: '/invoiceNoGetAll/'+$scope.invoiceNo,
                method: 'get',
                header: 'application/json'
            }).success(function (response) {
                console.log("response="+response);
                $scope.printInvoiceList=response;
            });


        });

    }




});