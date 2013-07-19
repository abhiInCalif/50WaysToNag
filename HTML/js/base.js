var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/userList', {templateUrl: 'partials/userList.html'}).
      when('/familyList', {templateUrl: 'partials/familyList.html'}).
      when('/edit/:taskId', {templateUrl: 'partials/taskDetails.html'}).
      when('/new', {templateUrl: 'partials/newTask.html'}).
		  when("/signin", {templateUrl: 'login.html'});
}]);