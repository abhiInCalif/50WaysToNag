function FamilyController($scope, familyService)
{
  // TODO: we should add email and name to database as well
  $scope.users = [];
  $scope.invitedUser = "";

	$scope.getMembers = function()
	{
		familyService.getFamilyMembers(function(data)
		{
			$scope.users = data;
		});
	};

  $scope.inviteMember = function()
  {
    console.log($scope.invitedUser);
  };
}