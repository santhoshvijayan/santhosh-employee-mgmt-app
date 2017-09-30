var app = angular.module('SanthoshEmployeeApp', ['ngRoute','ngResource','ui.bootstrap', 'ui.bootstrap.datetimepicker', 'ngJsonExportExcel', 'ngFileUpload' ]);

		app.config(function($routeProvider){
				$routeProvider
				.when('/',
				{
					controller: 'ListEmployeesController',
					templateUrl: '/views/employees.html'
				})
				.when('/addEmployee',
				{
					controller: 'AddEmployeesController',
					templateUrl: '/views/addEmployees.html'
				})
				.when('/editEmployee:id',
				{
					controller: 'EditEmployeesController',
					templateUrl: '/views/editEmployees.html'
				})
				.when('/viewEmployee:id',
				{
					controller: 'EditEmployeesController',
					templateUrl: '/views/viewEmployees.html'
				})
				.otherwise({
					redirectTo: '/#'
				})

		});
		