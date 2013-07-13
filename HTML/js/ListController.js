function ListController($scope, $location, $routeParams, sharedTaskList)
{
	$scope.tasks = sharedTaskList.getList();

  $scope.updateTask = function() {
    sharedTaskList.setList($scope.tasks);
    $location.path('/list');
  };

  // O(n) for now
  function findIndex(id)
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
  $scope.currentTask = $scope.tasks[findIndex($routeParams.taskId)];
}