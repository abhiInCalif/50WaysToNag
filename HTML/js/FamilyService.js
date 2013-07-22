var familyService = app.factory('familyService', function ($http) {
	return {
		getFamilyMembers: function(callback)
		{
			$http({method: "GET", 
					url: "/Bday/family/members"
					}).success(function(data, status, headers, config)
					{
						callback(data);
					}).error(function(data, status, headers, config)
					{
						// some error handling goes here
						console.log("Network Error occured!");
						console.log(status);
						console.log(headers);
					});
		}
	};
});
