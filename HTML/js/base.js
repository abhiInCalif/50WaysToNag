var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/temp', {templateUrl: 'temp.html'}).
      when('/auth', {templateUrl: 'auth.html'}).
      otherwise({redirectTo: '/index.html'});
}]);