function FamilyController($scope)
{
  $scope.family_id = 1;

  // TODO: we should add email and name to database as well
  $scope.users = [
  {
    "name": "Irvin Zhan",
    "email": "izhan@princeton.edu",
    "username": "izhan"
  },
  {
    "name": "Abhinav Khanna",
    "email": "akhanna@princeton.edu",
    "username": "akhanna"
  }];
}