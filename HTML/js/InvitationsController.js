function InvitationsController($scope, InviteServices)
{
	$scope.invite = function()
	{
		var emails = $scope.invite_emails.split(",");
		//debugger;
		// start the progress dialog
		$.mobile.showPageLoadingMsg( "b", "Inviting User...", false);

		InviteServices.invite(emails, function(data)
		{
			$.mobile.hidePageLoadingMsg();
			// console.log the response
			console.log("users invited successfully!");
			console.log(data);
		});
	};
}