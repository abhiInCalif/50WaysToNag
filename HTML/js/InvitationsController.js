function InvitationsController($scope, InviteServices)
{
	$scope.invite = function()
	{
		var emails = $scope.invite_emails.split(",");
		//debugger;

		InviteServices.invite(emails, function(data)
		{
			// console.log the response
			console.log("users invited successfully!");
			console.log(data);
		});
	};
}