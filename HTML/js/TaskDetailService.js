var tasksService = app.factory('tasksService', function ($http) {
	return {
		getDetails: function(task_id, callback)
		{
			$http({method: "GET",
						url: "/Bday/tasks/" + task_id
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
		},
		
		editDetails: function(task_id, mTask, callback)
		{
			$http({method: "PUT", 
					url: "/Bday/tasks/" + task_id,
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
		}
	};
});
