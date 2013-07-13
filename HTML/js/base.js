var app = angular.module('bday', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/', {templateUrl: 'index.html'}).
      when('/list', {templateUrl: 'list.html', reloadOnSearch: false}).
      when('/edit/:taskId', {templateUrl: 'taskDetails.html', reloadOnSearch: false}).
      otherwise({redirectTo: '/'});
}]);

// assignee must be unique user_id
// injection to controller
app.service('sharedTaskList', function () {
  var taskList = [
    {
      "task_id": 1,
      "title": "AA the dishes1",
      "details": "detail1 detail1 detail1 detail1",
      "isCompleted": true,
      "nagStatus": 1,
      "assignee": "uniqueuser1"
    },
    {
      "task_id": 2,
      "title": "DD the dishes2",
      "details": "detail2 detail2 detail2 detail2",
      "isCompleted": false,
      "nagStatus": 2,
      "assignee": "uniqueuser1"
    },
    {
      "task_id": 3,
      "title": "BB the dishes3",
      "details": "detail3 detail3 detail3 detail3",
      "isCompleted": true,
      "nagStatus": 3,
      "assignee": "uniqueuser1"
    },
    {
      "task_id": 4,
      "title": "CC the dishes4",
      "details": "detail4 detail4 detail4 detail4",
      "isCompleted": false,
      "nagStatus": 4,
      "assignee": "uniqueuser2"
    },
    {
      "task_id": 5,
      "title": "EE the dishes5",
      "details": "detail5 detail5 detail5 detail5",
      "isCompleted": true,
      "nagStatus": 5,
      "assignee": "uniqueuser2"
    }];

  return {
      getList: function () {
          return taskList;
      },
      setList: function(list) {
          taskList = list;
      }
  };
});