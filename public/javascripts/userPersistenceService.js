

/*"ipCookie"*/
var logapp = angular.module('mainApp', ['ui.router','ui.grid', 'ngTouch', 'ui.grid.selection', 'ui.grid.exporter','ui.grid.rowEdit','ui.grid.edit', 'ui.grid.pagination', "ipCookie"]);

logapp.factory("userPersistenceService", [
    "ipCookie", function(ipCookie) {
        var userName = "";

        return {
            setCookieData: function(username) {
                userName = username;
                ipCookie("userName", username);
                console.log( 'set cookie' + userName)
            },
            getCookieData: function() {
                userName = ipCookie("userName");
                return userName;
                console.log( 'get cookie' + userName)
            },
            clearCookieData: function() {
                userName = "";
                ipCookie.remove('userName', { path: '/' });
                console.log( 'remove cookie' + userName);

            }
        }
    }
]);






