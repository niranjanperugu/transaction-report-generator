var app = angular.module('CSVParseApp', ['ngFileUpload']);


//file upload controller
app.controller('fileUploadCtrl', function($scope, $window, $filter) {
    var sortFlag = false;

    $scope.Upload = function() {
    	try{
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv)$/;
        if (regex.test($scope.SelectedFile.name.toLowerCase())) {
            if (typeof(FileReader) != "undefined") {
                var reader = new FileReader();
                reader.onload = function(e) {
                    var data = parseCSVData(e.target.result);
                    $scope.$apply(function() {
                        $scope.data = data;
                        $scope.IsVisible = true;
                    	$scope.isError = false;
                    });
                }
                reader.readAsText($scope.SelectedFile);
            } else {
                $window.alert("This browser does not support HTML5.");
            }
        } else {
            $window.alert("Please upload a valid CSV file!!!");
            $scope.isError = true;
        }
    	}
    	catch(err){
    		 $window.alert("Error While Parsing the file : " + err.message);
    	      $scope.isError = true;
    	}
    }

$scope.sort = function(sortBy) {
    sortFlag = !sortFlag;
    $scope.data = $filter('orderBy')($scope.data, sortBy, sortFlag);
}

$scope.SelectFile = function(file) {
    $scope.SelectedFile = file;
    $scope.uploadStatus = true;
};

//Parses csv data into an array
function parseCSVData(csvData) {

    var resultArray = [];
    try {
        var lines = csvData.split("\n");
        var headers = lines[0].split(",");
        for (var i = 1; i < lines.length; i++) {
            var obj = {};
            var currentline = lines[i].split(",");
            for (var j = 0; j < headers.length; j++) {
                if (currentline.length > 1) {
                    obj[headers[j].split('"').join('').trim().replace(/\s/g, '_')] = currentline[j].split('"').join('');
                }
            }
            resultArray.push(obj);
        }
    } catch (err) {
        $window.alert("Error While Parsing the file : " + err.message);
        $scope.isError = true;
    }
    return resultArray;
}
});



//file upload controller
app.controller('customerReportCtrl', function($scope, $window) {
    $scope.Upload = function() {
        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.xml)$/;
        if (regex.test($scope.SelectedFile.name.toLowerCase())) {
            if (typeof(FileReader) != "undefined") {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $scope.$apply(function() {
                        $scope.data = data;
                        $scope.IsVisible = true;
                    });
                }
                reader.readAsText($scope.SelectedFile);
            } else {
                $window.alert("This browser does not support HTML5.");
            }
        } else {
        	
            $window.alert("Please upload a valid CSV or XML file!!!");
        }
    }

$scope.SelectFile = function(file) {
    $scope.SelectedFile = file;
    $scope.uploadStatus = true;
};
});