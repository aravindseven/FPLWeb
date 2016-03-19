'use strict';


var module1=angular.module('app', ['ngImgCrop','angularFileUpload']);


module1.controller('AppController', ['$scope', 'FileUploader', function($scope, FileUploader) {
        var uploader = $scope.uploader = new FileUploader({
            url: 'http://localhost:8080/FPLWeb/custAjaxProfileCreation.do?uploadImage=true'
        });
        
        $scope.myImage= '';
    	$scope.myCroppedImage='';
    	
        $scope.$watch('myCroppedImage', function(newVal) {
            if (newVal) {
              console.log('myCroppedImage', newVal);
            }

          });

        
        var handleFileSelect=function(evt) {
          var file=evt.currentTarget.files[0];
          var reader = new FileReader();
          reader.onload = function (evt) {
            $scope.$apply(function($scope){
            	$scope.myImage=evt.target.result;
            });
          };
          reader.readAsDataURL(file);
        };
        angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);


        // FILTERS

        uploader.filters.push({
            name: 'imageFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        });

        // CALLBACKS

        uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
            console.info('onWhenAddingFileFailed', item, filter, options);
        };
        uploader.onAfterAddingAll = function(addedFileItems) {
            console.info('onAfterAddingAll', addedFileItems);
        };
        uploader.onProgressItem = function(fileItem, progress) {
            console.info('onProgressItem', fileItem, progress);
        };
        uploader.onProgressAll = function(progress) {
            console.info('onProgressAll', progress);
        };
        uploader.onSuccessItem = function(fileItem, response, status, headers) {
            console.info('onSuccessItem', fileItem, response, status, headers);
        };
        uploader.onErrorItem = function(fileItem, response, status, headers) {
            console.info('onErrorItem', fileItem, response, status, headers);
        };
        uploader.onCancelItem = function(fileItem, response, status, headers) {
            console.info('onCancelItem', fileItem, response, status, headers);
        };
        uploader.onCompleteItem = function(fileItem, response, status, headers) {
            console.info('onCompleteItem', fileItem, response, status, headers);
        };
        uploader.onCompleteAll = function() {
            console.info('onCompleteAll');
        };

        console.info('uploader', uploader);
        
        uploader.onAfterAddingFile = function(item) {
        
          
          var reader = new FileReader();
          reader.onload = function(event) {
            $scope.$apply(function(){
            	$scope.myImage = event.target.result;
            });
          };
          reader.readAsDataURL(item._file);
        };

        uploader.onBeforeUploadItem = function(item) {

          var binary = atob($scope.myCroppedImage.split(',')[1]);
          var mimeString = $scope.myCroppedImage.split(',')[0].split(':')[1].split(';')[0];
          var array = [];
          for(var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
          }

          
          item._file = new Blob([new Uint8Array(array)], {type: mimeString});
        };


        
    }]);