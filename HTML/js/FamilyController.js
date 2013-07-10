function FamilyController($scope)
{
  $scope.family_id = 1;

  $scope.users = [
  {
    "username": "Irvin Zhan",
    "tasks_assigned": [1, 2, 5]
  },
  {
    "username": "Abhinav Khanna",
    "tasks_assigned": [3, 4]
  }];
}