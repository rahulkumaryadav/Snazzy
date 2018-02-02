/**
 * Created by admin on 09-06-2017.
 */
var app = angular.module("mainApp",[]);
app.requires.push('angularUtils.directives.dirPagination');/*'angucomplete', 'summernote','angular-loading-bar',*/


// search function to match full text


var printRegistration = function() {
    DocRaptor.createAndDownloadDoc("YOUR_API_KEY_HERE", {
        test: true, // test documents are free, but watermarked
        type: "pdf",
        document_content: document.querySelector('html').innerHTML, // use this page's HTML
        // document_content: "<h1>Hello world!</h1>",               // or supply HTML directly
        // document_url: "http://example.com/your-page",            // or use a URL
        // javascript: true,                                        // enable JavaScript processing
        // prince_options: {
        //   media: "screen",                                       // use screen styles instead of print styles
        // }
    })
}


app.controller('codeGeneratorCtrl', function($scope,$http) {

    $scope.getCodeList = function() {
        $http({
            url: '/codeGeneratorList',
            method: 'get',
            header: 'application/json'
        }).success(function (response) {
            console.log(response);
            $scope.codeGeneratorList = response;
        });
    };

    $scope.getCodeList();


    $scope.save = function(data){
        //alert("helllo"+data.purchesRate);
      /*  console.log("data -> "+data);
        if(data.userName == "" || data.userName == undefined || data.userName == null){
            alert("Enter usernmae !!");
        }else if(data.password == "" || data.password == undefined || data.password == null){
            alert("Enter password !!")
        }else{*/
        $('#save').prop("disabled", "true");
            $http({
                url:'/codeGeneratorCreate',
                method:'POST',
                header:'application/json',
                data:data
            }).success(function (response) {
                $scope.data={}

                $scope.getCodeList();

            });
        }


    $scope.delete=function(codeGenerator){
        alert(codeGenerator.id)
         $http({
            url: '/customerDetailDelete/'+codeGenerator.id,
            method: 'post',
            header: 'application/json'
        }).success(function (response) {
            console.log("response="+response);
             $scope.getCodeList();

         });
    }






    /*}*/
});