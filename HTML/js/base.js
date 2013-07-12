var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/list', {templateUrl: 'list.html'}).
      otherwise({redirectTo: '/'});
}]);