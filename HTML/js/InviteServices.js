var inviteServices = app.factory('InviteServices', function ($http) {
	return {
		invite: function(user_emails, callback)
		{
			$http({method: "POST",
						url: "/Bday/invite/",
						data: {"emails": user_emails}
						}).success(function(data,status,headers,config)
						{
							callback(data);
						}).error(function(data, status, headers, config)
						{
							// some error handling
							console.log("Network Error occured!");
							console.log(status);
							console.log(headers);
						});
		}
	};
});
