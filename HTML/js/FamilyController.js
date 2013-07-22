function FamilyController($scope, familyService)
{
  // TODO: we should add email and name to database as well
  $scope.users = [];

	$scope.getMembers = function()
	{
		familyService.getFamilyMembers(function(data)
		{
			$scope.users = data;
		});
	};
}