var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/signup', {templateUrl: 'index.html'}).
      when('/userList', {templateUrl: 'partials/userList.html'}).
      when('/familyList', {templateUrl: 'partials/familyList.html'}).
      when('/edit/:taskId', {templateUrl: 'partials/taskDetails.html'}).
      when('/new', {templateUrl: 'partials/newTask.html'}).
			when("/settings", {templateUrl: 'partials/settings.html'}).
		  when("/login", {templateUrl: 'partials/login.html'});
}]);

function BaseController($scope, InviteServices)
{
	$scope.invites = [];
	
	$scope.getInvites = function()
	{
		InviteServices.getAllInvitations(function(data)
		{
			// show all the invites
			$scope.invites = data;
		});
	};
	
	$scope.accept = function(invite_id, family_id)
	{
		InviteServices.accept(invite_id, family_id, function(data)
		{
			// show all the invites
			$scope.invites = data;
		});
	};
	
	$scope.deny = function(invite_id, family_id)
	{
		InviteServices.deny(invite_id, family_id, function(data)
		{
			// show all the invites
			$scope.invites = data;
		});
	};
}