
var createFamilyService = app.factory("createFamilyService", function($http, $templateCache)
{
	console.log('family created!');
	return {
		create: function(callback)
		{
			$http({method: "POST",
					url: "/Bday/family",
					data: "",
					cache: $templateCache}).success(function(data, status, headers, config)
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