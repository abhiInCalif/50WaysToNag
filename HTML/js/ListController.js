function ListController($scope)
{
	// assignee must be unique user_id
	$scope.tasks = [
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
}