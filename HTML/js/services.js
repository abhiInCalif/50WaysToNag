
// assignee must be unique user_id
// injection to controller
app.service('sharedTaskList', function () {
  var counter = 100;

  // data loaded from backend.  should be unfiltered, etc
  // that way, when we change taskList, we don't have to
  // call backend again if we ever need to reset
  var data = [
    {
      "task_id": 1,
      "title": "AA the dishes1",
      "details": "detail1 detail1 detail1 detail1",
      "isCompleted": true,
      "nagStatus": 1,
      "assignee": "izhan@princeton.edu"
    },
    {
      "task_id": 2,
      "title": "DD the dishes2",
      "details": "detail2 detail2 detail2 detail2",
      "isCompleted": false,
      "nagStatus": 2,
      "assignee": "izhan@princeton.edu"
    },
    {
      "task_id": 3,
      "title": "BB the dishes3",
      "details": "detail3 detail3 detail3 detail3",
      "isCompleted": true,
      "nagStatus": 3,
      "assignee": "akhanna@princeton.edu"
    },
    {
      "task_id": 4,
      "title": "CC the dishes4",
      "details": "detail4 detail4 detail4 detail4",
      "isCompleted": false,
      "nagStatus": 4,
      "assignee": "izhan@princeton.edu"
    },
    {
      "task_id": 5,
      "title": "EE the dishes5",
      "details": "detail5 detail5 detail5 detail5",
      "isCompleted": true,
      "nagStatus": 5,
      "assignee": "akhanna@princeton.edu"
    }];

  // deep copy needed
  var taskList = angular.copy(data);

    return {
      getList: function () {
        return taskList;
      },

      setList: function(list) {
        if (list) {
          taskList = list;
        }
        // resets list
        else {
          taskList = angular.copy(data);
        }
      },

      incrementNewTaskId: function() {
        counter++;
      },

      getNewTaskId: function() {
        return counter;
      }
  };
});