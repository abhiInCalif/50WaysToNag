var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/login', {templateUrl: 'partials/login.html'}).
      when('/signup', {templateUrl: 'index.html'}).
      when('/settings', {templateUrl: 'partials/settings.html'}).
      when('/settings/invite', {templateUrl: 'partials/invite.html'}).
      when('/userList', {templateUrl: 'partials/userList.html'}).
      when('/familyList', {templateUrl: 'partials/familyList.html'}).
      when('/:taskId/edit', {templateUrl: 'partials/editTask.html'}).
      when('/:taskId/delete', {templateUrl: 'partials/deleteTask.html'}).
      when('/new', {templateUrl: 'partials/newTask.html'});
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
