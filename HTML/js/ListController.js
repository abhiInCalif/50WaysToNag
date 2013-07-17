function ListController($scope, $location, $routeParams, sharedTaskList, familyTasksService, createFamilyService)
{
	$scope.tasks = sharedTaskList.getList();
	
	$scope.family_id = -1;
	
	$scope.getFamilyTasks = function()
	{
		familyTasksService.get(function(data)
		{
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
		familyTasksService.getUserTasks(function(data)
		{
			$scope.tasks = data;
		});
	};

  $scope.updateTask = function() {
    sharedTaskList.setList($scope.tasks);
    $location.path('/list');
  };

  $scope.addTask = function() {
    $scope.tasks.push($scope.newTask);
    familyTasksService.post($scope.newTask, function(data)
	 {
			$location.url("/list.html");
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

  $scope.isChecked = function(email) {
    return email == $scope.currentTask.assignee;
  };

  // O(n) for now
  function findTaskIndex(id)
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
		"user": 1
  };

  if ($routeParams.taskId)
  {
    $scope.currentTask = $scope.tasks[findTaskIndex($routeParams.taskId)];
  }
}