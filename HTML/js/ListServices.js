
// assignee must be unique user_id
// injection to controller
var listService = app.factory('familyTasksService', function ($http, $templateCache) {
	return {
		// mTask is the TaskModel object
		// must contain (assignee_name (name), details, title, isCompleted, nagStatus)
		post: function(family_id, mTask, callback)
		{
			$http({method: "POST", 
					url: "/Bday/family/1",
					cache: $templateCache,
					data: mTask}).success(function(data, status, headers, config)
					{
						callback(data);
					}).error(function(data, status, headers, config)
					{
						// some error handling goes here
						console.log("Network Error occured!");
						console.log(status);
						console.log(headers);
					});
		},
		
		get: function(family_id, callback)
		{
			$http({method: "GET",
					url: "/Bday/family/" + family_id,
					cache: $templateCache}).success(function(data,status,headers,config)
					{
						callback(data);
					}).error(function(data, status, headers, config)
					{
						// some error handling
						console.log("Network Error occured!");
						console.log(status);
						console.log(headers);
					});
		},
		
		addMember: function(family_id, callback)
		{
			$http({method: "PUT",
					url: "/Bday/family/" + family_id,
					cache: $templateCache}).success(function(data,status,headers,config)
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