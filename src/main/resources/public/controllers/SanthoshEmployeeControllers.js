app.controller('AddEmployeesController', function($scope , $location, DataTasksFactory, AddTasksFactory, $route ){
	$scope.now = new Date();
	$scope.now.setMinutes($scope.now.getMinutes() + 10);
	this.addTask = function(addNewTaskCtrl){
		AddTasksFactory.save(addNewTaskCtrl);
		DataTasksFactory.addTask(addNewTaskCtrl);
		
		window.setTimeout(function() {
			$location.path('/');
        }, 50); 
		
	};
});

app.controller('EditEmployeesController', function($scope, $location, ListTasksByIdFactory, EditTasksFactory, $route, $routeParams ){
	var selectedTask = ListTasksByIdFactory.query({id:$routeParams.id});
	selectedTask.$promise.then(function(result){
		$scope.editTaskCtrl = $scope.editTaskCtrl || {};
		$scope.editTaskCtrl.empname = result.empname;
		$scope.editTaskCtrl.joindate = new Date(result.joindate);
		$scope.editTaskCtrl.city = result.city;
		$scope.editTaskCtrl.phone = result.phone;
		$scope.editTaskCtrl.isactive = result.isactive;
	});
	
	this.editTask = function(editTaskCtrl){
		EditTasksFactory.update({id:$routeParams.id},editTaskCtrl);
		
		window.setTimeout(function() {
			$location.path('/');
        }, 10); 
		
	};
});
	
app.controller('ListEmployeesController', function($scope, $location,  DataTasksFactory, ListTasksFactory, $route){
	var task = ListTasksFactory.query();
	task.$promise.then(function (result) {
		DataTasksFactory.init(result);
	    $scope.tasks = DataTasksFactory.getTasks();
	});
});

app.controller('TasksPrioritiesListController', function($scope, TasksPrioritiesListFactory) {
	$scope.items = TasksPrioritiesListFactory.query();
});

app.controller('TasksActionsController', function($scope, $location, ListTasksByIdFactory, EditTasksFactory, DeleteTasksFactory, Upload, $timeout) {
    
	 $scope.CarsView= false;
	 $scope.ListView= true;
     $scope.ShowHide = function () {
         //If DIV is visible it will be hidden and vice versa.
    	 if($scope.CarsView){
    		 $scope.CarsView = false;
    		 $scope.ListView = true;
    	 }else{
    		 $scope.CarsView = true;
    		 $scope.ListView = false;
    	 }
         
     }
	
    $scope.checkTask = function(taskid) {
    	var selectedTask = ListTasksByIdFactory.query({id:taskid});
    	selectedTask.$promise.then(function(result){
    		result.isactive = "true";
    		EditTasksFactory.update({id:taskid},result);
    		
    		window.setTimeout(function() {
    			location.reload(true);
            }, 10);
    		
    	});
     };
    $scope.deleteTask = function(taskid) {
        DeleteTasksFactory.delete_task({id:taskid},null);
        
		window.setTimeout(function() {
			location.reload(true);
        }, 10);
		
     };
     
});
     app.controller('MyCtrl',  function ($scope, Upload, $timeout,$location) {
         $scope.uploadFiles = function(file, errFiles) {
             $scope.f = file;
             $scope.errFile = errFiles && errFiles[0];
             if (file) {
                 file.upload = Upload.upload({
                     url: 'http://localhost:8001/employees/api/upload',
                     data: {file: file}
                 });

                 file.upload.then(function (response) {
                     $timeout(function () {
                         file.result = response.data;
                     });
                 }, function (response) {
                     if (response.status > 0)
                         $scope.errorMsg = response.status + ': ' + response.data;
                 }, function (evt) {
                     file.progress = Math.min(100, parseInt(100.0 * 
                                              evt.loaded / evt.total));
                 });
             }  
         	window.setTimeout(function() {
    			location.reload(true);
            }, 10); 
    	
         }
     });
     
     

    