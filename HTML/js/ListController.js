function ListController($scope, $location, $routeParams, sharedTaskList, userSession)
{
  $scope.currentUser = userSession.session;
	$scope.tasks = sharedTaskList.getList();

  $scope.updateTask = function() {
    sharedTaskList.setList($scope.tasks);
    $location.path('/familyList');
  };

  $scope.addTask = function() {
    $scope.tasks.push($scope.newTask);
    sharedTaskList.incrementNewTaskId();
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
      "task_id": sharedTaskList.getNewTaskId(),
      "title": "",
      "details": "",
      "isCompleted": false,
      "nagStatus": 5,
      "assignee": "someuser"
  };

  // when navigating to details page
  if ($routeParams.taskId)
  {
    $scope.currentTask = $scope.tasks[getTaskById($routeParams.taskId)];
  }
}