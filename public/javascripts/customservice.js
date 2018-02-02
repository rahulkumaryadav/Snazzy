/**
 * Created by admin on 11/23/2016.
 */
var custservices = angular.module('custservices', []);
custservices.service('downloadFile', function() {
    this.myDownloadFunc = function(filePath, hitPath){
        // document.getElementById("myDiv").innerHTML="";
        $('#myDiv').empty();
        var val1=filePath;
        var neval=new Array()
        console.log(neval.length);
        neval=val1.split(";");
        console.info(neval[0]);
        count=1;
        for(i=0;i<neval.length;i++){
            var mydiv = document.getElementById("myDiv");
            var aTag = document.createElement('a');
            var checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.style.height="18px";
            checkbox.style.width="20px";
            console.log("neval[i]"+neval[i]);
            var v=neval[i].substring(neval[i].lastIndexOf('\\')+1,neval[i].length);
            console.info(v);
            // lab.innerHTML=v;
            checkbox.name = neval[i];
            checkbox.id="i"+i;
            checkbox.className = "file";
            mydiv.appendChild(checkbox);
            // mydiv.appendChild(lab);
            mydiv.appendChild(document.createTextNode (" "));
            aTag.setAttribute('href', hitPath +v);
            aTag.innerHTML =v;   //v
            mydiv.appendChild(aTag);
            mydiv.appendChild(document.createElement("br"));
            document.getElementById("remove").style.display='block';
        }
    }
    return true;
});

custservices.factory('fileService', function() {
    var file = {};
    var fileService = {};
    fileService.getFile = function(name) {
        return file[name];
    };
    fileService.setFile = function(newFile, index, name) {
        if (index === 0 && file[name] === undefined)
            file[name] = [];
        file[name][index] = newFile;
    };
    fileService.removeFile =function(name) {
        file[name] = undefined;
    };
    return fileService;
});

custservices.directive('myFileUpload', function(fileService) {
    return function(scope, element, attrs) {
        element.bind('change', function() {
            var index;
            var index_file = 0;
            for (index = 0; index < element[0].files.length; index++) {
                fileService.setFile(element[0].files[index], index_file, attrs.myFileUpload);
                index_file++;
            }
            index_file = 0;
        });
    }
});

app.directive('numbersOnly', [
    function(){ // limit the input field to numbers only, but retain value as string. Note do not use on type=number input field
        return {
            require: 'ngModel',
            link: function(scope, element, attrs, modelCtrl) {
                modelCtrl.$parsers.push(function (inputValue) {
                    var transformedInput = inputValue;
                    if(transformedInput)
                        transformedInput = Number(transformedInput);
                    if (transformedInput!=inputValue) {
                        modelCtrl.$setViewValue(null);
                        modelCtrl.$render();
                    }
                    return transformedInput;
                });
            }
        };
    }
]);