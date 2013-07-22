
// assignee must be unique user_id
// injection to controller
var listService = app.factory('familyTasksService', function ($http) {
	return {
		// mTask is the TaskModel object
		// must contain (assignee_name (name), details, title, isCompleted, nagStatus)
		post: function(mTask, callback)
		{
			$http({method: "POST", 
					url: "/Bday/family/task",
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
		
		get: function(callback)
		{
			$http({method: "GET",
					url: "/Bday/family/task"
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
		
		// this one requires a family_id in order to know exactly which family the alternative user
		// should be added to, since this user will not have this family to begin with.
		addMember: function(family_id, callback)
		{
			$http({method: "PUT",
					url: "/Bday/family/" + family_id
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
		
		getUserTasks: function(callback)
		{
			$http({method: "GET",
					url: "/Bday/tasks"
					}).success(function(data,status,headers,config)
					{
						callback(data);
					}).error(function(data,status,headers,config)
					{
						// some error handling
						console.log("Network Error occured!");
						console.log(status);
						console.log(headers);
					});
		},
		
		updateTaskDetails: function(task_id, callback)
		{
			$http({method: "PUT",
					url: "/Bday/tasks/" + task_id
					}).success(function(data,status,headers,config)
					{
						callback(data);
					}).error(function(data,status,headers,config)
					{
						// some error handling
						console.log("Network Error occured!");
						console.log(status);
						console.log(headers);
					});
		}
	};
});