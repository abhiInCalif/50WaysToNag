function AuthenticationController($scope, authService)
{
	$scope.error = false;
	
	$scope.onClickOfRegister = function()
	{
		// check basic frontend validation then issue the service call.
		if ($scope.register.$valid != false)
		{
			// double check that the two passwords are equal
			if($scope.password != $scope.retype) return;
			// proceed to issue service call
			authService.register($scope.username, $scope.password, function(data)
			{
				// Post message then redirect....
				$.mobile.showPageLoadingMsg( "b", "Registration Success!", false);
				setTimeout(function()
				{
					// either store cookie and forward or just forward, needs to be discussed.
					$.mobile.hidePageLoadingMsg();
				}, 1500 );
			});
		}
	};
	
	$scope.onClickOfLogin = function()
	{
		if ($scope.login.$valid != false)
		{
			// proceed to issue service call
			authService.login($scope.username, $scope.password, function(data)
			{
				if(data == -1)
				{
					// // invalid user, display errors
					// 					$.mobile.showPageLoadingMsg( "b", "Error invalid Username / Password", true);
					// 					// hide after delay
					// 					setTimeout( $.mobile.hidePageLoadingMsg, 1500 );
					$scope.error = true;
				}
				else
				{
					// redirect the user, store the session object so that you have the authentication
					// token, the token should be sent back from the server, and needs to be incorporated
					// in every call (ensures an authenticated user....);
					
				}
			});
		}
		return;
	};
}