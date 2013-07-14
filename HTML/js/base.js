var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/userList', {templateUrl: 'userList.html'}).
      when('/familyList', {templateUrl: 'familyList.html'}).
      when('/edit/:taskId', {templateUrl: 'taskDetails.html'}).
      when('/new', {templateUrl: 'newTask.html'}).
      otherwise({redirectTo: '/'});
}]);