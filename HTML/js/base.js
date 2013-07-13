var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/list', {templateUrl: 'list.html'}).
      when('/edit/:taskId', {templateUrl: 'taskDetails.html'}).
      when('/new', {templateUrl: 'newTask.html'}).
      otherwise({redirectTo: '/'});
}]);