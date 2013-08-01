function ListController($scope, $location, $routeParams, authService, familyTasksService, createFamilyService, tasksService)
{

	$scope.tasks = [];

  $scope.deleteTask = function()
  {
    console.log('task should be deleted');
    // because asynch call
    $scope.$apply(function() {
      // defaults to userList for now until we combine userlist and familylist
      $location.path('/userList');
    });
  };

	$scope.getFamilyTasks = function()
	{
		familyTasksService.get(function(data)
		{
      console.log('family tasks successfully loaded!');
			$scope.tasks = data;
		});
	};

	$scope.logout = function()
	{
		authService.logout(function(data)
		{
			// move location to home
			$location.path("/");
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
		tasksService.getDetails($routeParams.taskId, function(data)
		{
			$scope.currentTask = data;
      console.log('data');
      console.log(data);
		});
	};

  $scope.updateTask = function() {
    // current way of converting current task to meet backend api.
    // your backend get and put requests need to expect the same data -- right now, the get
    // request assignee is different from that of the put request
    $scope.currentTask.assignee = $scope.currentTask.assignee.email;
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

  // TODO: need to know which user is currently logged on
  $scope.newTask = {
      "title": "",
      "details": "",
      "isCompleted": false,
      "nagStatus": 5,
		  "user": 1   // Abhi: what is this for? I don't know.... (abhi....)
  };
}