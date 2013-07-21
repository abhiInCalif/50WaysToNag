function ListController($scope, $location, $routeParams, sharedTaskList, familyTasksService, createFamilyService)
{
	//$scope.tasks = sharedTaskList.getList();
	$scope.tasks = [];
	$scope.family_id = -1;

	$scope.getFamilyTasks = function()
	{
		familyTasksService.get(function(data)
		{
      console.log('family tasks successfully loaded!')
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

	$scope.getUserTasks = function()
	{
    console.log('getusertask');
		familyTasksService.getUserTasks(function(data)
		{
      console.log('user tasks successfully loaded!')
			$scope.tasks = data;
		});
	};

  $scope.updateTask = function() {
    sharedTaskList.setList($scope.tasks);
    $location.path('/familyList');
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
      if ($scope.tasks[k].task_id == id)
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
		  "user": 1   // Abhi: what is this for?
  };

  // when navigating to details page
  if ($routeParams.taskId)
  {
    $scope.currentTask = $scope.tasks[getTaskById($routeParams.taskId)];
  }
}