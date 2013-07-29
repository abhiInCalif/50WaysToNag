function ListController($scope, $location, $routeParams, sharedTaskList, familyTasksService, createFamilyService, tasksService)
{
	//$scope.tasks = sharedTaskList.getList();
	
	$scope.tasks = [];

	$scope.getFamilyTasks = function()
	{
		familyTasksService.get(function(data)
		{
      console.log('family tasks successfully loaded!');
			$scope.tasks = data;
		});
	};

	$scope.createFamily = function()
	{
		createFamilyService.create(function(data)
		{
			$scope.family_id = data;
		});
	};
	
	$scope.nag = function()
	{
		tasksService.nag($routeParams.taskId, function(data)
		{
			// no response, perhaps we take them back?
		});
	};

	$scope.getUserTasks = function()
	{
    console.log('getusertask');
		familyTasksService.getUserTasks(function(data)
		{
      console.log('user tasks successfully loaded!');
			$scope.tasks = data;
		});
	};
	
	$scope.getTaskDetails = function()
	{
		console.log("gettaskdetails");
		tasksService.getDetails($routeParams.taskId, function(data)
		{
			$scope.currentTask = data;
		});
	};

  $scope.updateTask = function() {
		tasksService.editDetails($scope.currentTask.id, $scope.currentTask, function(data)
		{
			$location.path('/familyList');
		});
  };

  $scope.addTask = function() {
    $scope.tasks.push($scope.newTask);
    familyTasksService.post($scope.newTask, function(data)
	  {
			$location.path("/familyList");
	  });
    $scope.updateTask();
  };

  // for debugging purposes for now
  // resets to data pulled from server
  // will be used in production in future
  $scope.resetAll = function() {
    sharedTaskList.setList();
    $scope.tasks = sharedTaskList.getList();
  };

  // O(n) for now
  function getTaskById(id)
  {
    for (var k = 0; k < $scope.tasks.length; k++)
    {
      if ($scope.tasks[k].id == id)
      {
        return k;
      }
    }
    return false;
  }

  // TODO: need to know which user is currently logged on
  $scope.newTask = {
      "title": "",
      "details": "",
      "isCompleted": false,
      "nagStatus": 5,
		  "user": 1   // Abhi: what is this for? I don't know.... (abhi....)
  };
}