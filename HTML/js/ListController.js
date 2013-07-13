function ListController($scope, $location, $routeParams, sharedTaskList)
{
	$scope.tasks = sharedTaskList.getList();

  $scope.updateTask = function() {
    sharedTaskList.setList($scope.tasks);
    $location.path('/list');
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

  // TODO: working on this now (irvin)
  /*
  $scope.isChecked = function() {
    console.log('hit');
    var index = findEmailIndex(email);
    if (!index)
    {
      console.log('win');
      return "checked";
    }
    else
    {
      return "";
    }
  };*/

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

  // O(n) for now
  function findEmailIndex(email)
  {
    for (var k = 0; k < $scope.tasks.length; k++)
    {
      if ($scope.tasks[k].email == email)
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

  if ($routeParams.taskId)
  {
    $scope.currentTask = $scope.tasks[findTaskIndex($routeParams.taskId)];
  }
}