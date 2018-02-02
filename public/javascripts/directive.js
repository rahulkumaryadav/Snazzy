/**
 * Created by admin on 11/10/16.
 */
var app = angular.module('mainApp');

app.directive('casetransform', function($filter) {
    return {
        require: 'ngModel',
        link: function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function (inputValue) {
                var transformedInput = inputValue;
                if (attrs.casetransform=='toUpper'){            //html directive -->  casetransform="toUpper"
                    transformedInput= $filter('uppercase')(transformedInput);
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }else if (attrs.casetransform=='toLower'){            //html directive -->  casetransform="toLower"
                    transformedInput= $filter('lowercase')(transformedInput);
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }else if(attrs.casetransform=='camelCase' ){            //html directive -->  casetransform="camelCase"
                    transformedInput= transformedInput.replace(/\w\S*/g, function(transformedInput){return transformedInput.charAt(0).toUpperCase() + transformedInput.substr(1).toLowerCase();});
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }
                return transformedInput;
            });
        }
    }
});


app.directive('numbersOnly', [
    function(){ // limit the input field to numbers only, but retain value as string. Note do not use on type=number input field
        /*<input type="text" numbers-only />*/
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
])