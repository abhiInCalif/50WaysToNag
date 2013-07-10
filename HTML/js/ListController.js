function ListController($scope)
{
	// assignee must be unique user_id
	$scope.tasks = [
	{
		"title": "Do the dishes1",
		"details": "detail1 detail1 detail1 detail1",
		"isCompleted": true,
		"nagStatus": 1,
		"assignee": "uniqueuser1"
	},
	{
		"title": "Do the dishes2",
		"details": "detail2 detail2 detail2 detail2",
		"isCompleted": false,
		"nagStatus": 2,
		"assignee": "uniqueuser1"
	},
	{
		"title": "Do the dishes3",
		"details": "detail3 detail3 detail3 detail3",
		"isCompleted": true,
		"nagStatus": 3,
		"assignee": "uniqueuser1"
	},
	{
		"title": "Do the dishes4",
		"details": "detail4 detail4 detail4 detail4",
		"isCompleted": false,
		"nagStatus": 4,
		"assignee": "uniqueuser2"
	},
	{
		"title": "Do the dishes5",
		"details": "detail5 detail5 detail5 detail5",
		"isCompleted": true,
		"nagStatus": 5,
		"assignee": "uniqueuser2"
	}];
}