var app, deps;
deps= [ 'angularBootstrapNavTree'] ;

if (angular.version.full.indexOf("1.2") >= 0) {
    deps.push('ngAnimate');
  }


var cartApp = angular.module('cartApp', deps);

cartApp.config( function($locationProvider,  $httpProvider) {
    $locationProvider.html5Mode(true);
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}
);

cartApp.controller('cartController', function($scope, $http){
	
			$scope.refreshCart = function(cartId){
				$http.get('/ShoppingCard/rest/cart/'+$scope.cartId)
				.success(function(data){
					$scope.cart = data;
				});
			};
			
			$scope.clearCart = function() {
				$http.delete('/ShoppingCard/rest/cart/'+$scope.cartId)
				 .success($scope.refreshCart($scope.cartId));
			};
				  
			$scope.initCartId = function(cartId) {
				$scope.cartId=cartId;
				$scope.refreshCart($scope.cartId);
			};
			
			$scope.addToCart = function(productId) {
				 $http.put('/ShoppingCard/rest/cart/add/'+productId)
				 .success(function(data) {
					$scope.refreshCart( $http.get ('/ShoppingCard/rest/cart/cartId'));
					alert("Product Successfully added to the Cart!");
				});
			 };
			 
			 $scope.removeFromCart = function(productId) {
				 $http.put('/ShoppingCard/rest/cart/remove/'+productId)
				 .success(function(data) {
					 $scope.refreshCart( $http.get('/ShoppingCard/rest/cart/cartId'));
				 });
			};
			
	
	});

	cartApp.controller('categoryController', function($scope, $log, productService,$timeout ){
		 $scope.getCategoryProducts = function(cat) {
		     var promise = productService.getCategoryProducts(cat);
		     promise.then(
				    function(payload) {
					 $scope.data = payload.data;
				    },
				    function(errorPayload) {
					      $scope.data = null;
				              $log.error('failure loading data', errorPayload);
				     });
		};
		 $scope.initCategoryTreeData = function() {
		     var promise = productService.getCategoryTreeData();
		     $scope.categoryTreeData = [{}];
		     promise.then(
				    function(payload) {
					 $scope.try_async_load( payload.data.categoryTreeData);
				    },
				    function(errorPayload) {
					 alert("no defined category");
					 $scope.categoryTreeData = null;
				              $log.error('failure loading data', errorPayload);
				     });
		};

		    $scope.my_tree_handler = function(branch) {
			      $scope.getCategoryProducts(branch.id);
			      history.pushState(null,null,'products-' +branch.label);
			    };
			    
			    $scope.my_tree = tree = {};
			    $scope.try_async_load = function(category_data) {
				 $scope.categoryTreeData = [];
			      $scope.doing_async = true;
			      return $timeout(function() {
				  $scope.categoryTreeData  = category_data;
			        $scope.doing_async = false;
			        return tree.expand_all();
			      }, 200);
			    };
			    
	});
	
	cartApp.controller('productController',  function( $scope,$log,productService){
	    var promise = productService.getProducts();
	    promise.then(
		    function(payload) {
		              $scope.data = payload.data;
		          },
		          function(errorPayload) {
		              $scope.data = null;
		              $log.error('failure loading data', errorPayload);
		          });
	    
	}  );
	
	
	 cartApp.factory('productService',  function( $http ) {
	     return {
		 getProducts : function() {
	    		return  $http.get('/ShoppingCard/products/all');
	    		 },
	     	getCategoryProducts : function(cat) {
	    		return $http.get('/ShoppingCard/products/cat/'+cat );
	    		 },
	    	getCategoryTreeData : function (){
	    	    return $http.get('/ShoppingCard/products/getCategories' );
	    	}
	     };
    	
    } );
	 
	   

