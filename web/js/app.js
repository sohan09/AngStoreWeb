'use strict';

// App Module: the name AngularStore matches the ng-app attribute in the main <html> tag
// the route provides parses the URL and injects the appropriate partial page
var storeApp = angular.module('AngularStore', ['ngResource']).
        config(['$routeProvider', function($routeProvider) {
                $routeProvider.
                        when('/store', {
                            templateUrl: 'partials/store.htm',
                            controller: StoreController
                        }).
                        when('/login', {
                            templateUrl: 'partials/login.html',
                            controller: UserCtrl
                        }).
                        when('/category/:parent/:child', {
                            templateUrl: 'partials/store.htm',
                            controller: ProductByCategoryCtrl
                        }).
                        when('/products/:productSku', {
                            templateUrl: 'partials/product.htm',
                            controller: ProductCtrl
                        }).
                        when('/cart', {
                            templateUrl: 'partials/shoppingCart.htm',
                            controller: CartCtrl
                        }).
                        otherwise({
                            redirectTo: '/store'
                        });
            }]);

// create a data service that provides a store and a shopping cart that
// will be shared by all views (instead of creating fresh ones for each view).
storeApp.factory("DataService", function($resource, $location) {

    // create store
    var myStore = new store();


    // create shopping cart
    var myCart = new shoppingCart("AngularStore");

    // enable PayPal checkout
    // note: the second parameter identifies the merchant; in order to use the 
    // shopping cart with PayPal, you have to create a merchant account with 
    // PayPal. You can do that here:
    // https://www.paypal.com/webapps/mpp/merchant
    myCart.addCheckoutParameters("PayPal", "iraziud@me.com");

    // enable Google Wallet checkout
    // note: the second parameter identifies the merchant; in order to use the 
    // shopping cart with Google Wallet, you have to create a merchant account with 
    // Google. You can do that here:
    // https://developers.google.com/commerce/wallet/digital/training/getting-started/merchant-setup
    myCart.addCheckoutParameters("Google", "500640663394527",
            {
                ship_method_name_1: "UPS Next Day Air",
                ship_method_price_1: "20.00",
                ship_method_currency_1: "USD",
                ship_method_name_2: "UPS Ground",
                ship_method_price_2: "15.00",
                ship_method_currency_2: "USD"
            }
    );

    var storeByCategory = function(child, parent) {
        var rr = $resource('/AngStoreWeb/ProductByCategoryNameAndParent', {}, {
            list: {method: 'GET', params: {child: child, parent: parent}, isArray: true}
        });

        var products = rr.list();
        var mySt = new store();
        mySt.products = products;

        return mySt;
    }

    var categoryTree = function() {
        var rr = $resource('/AngStoreWeb/CategoryTree', {}, {
            list: {method: 'GET', params: {}, isArray: false}
        });

        return rr.list();
    }

    var productBySku = function(sku) {
        var rr = $resource('/AngStoreWeb/ProductBySku', {}, {
            list: {method: 'GET', params: {sku: sku}, isArray: false}
        });

        return rr.list();
    }

    var defaultStore = function() {

        var rr = $resource('/AngStoreWeb/StoreDefault', {}, {
            list: {method: 'GET', params: {}, isArray: true}
        });

        var products = rr.list();
        var mySt = new store();
        mySt.products = products;

        return mySt;
    }

    var login = function(user) {
        var rr = $resource('/AngStoreWeb/Login', {}, {
            list: {method: 'GET', params: {email: user.email, password: user.password}, isArray: false}
        });
        var a_user = rr.list();
        if(a_user == "failed") {
            return false;
        }
        
        localStorage['user'] = a_user;
        $location.path('/store');
    }

    var logout = function() {
        localStorage['user'] = null;

        var rr = $resource('/AngStoreWeb/Logout', {}, {
            list: {method: 'GET', params: {}, isArray: false}
        });
        rr.list();
        
        myCart.clearItems();
        console.log("Logged Out");
        $location.path('/login');
    }
    
    var register = function (user) {
        
        var rr = $resource('/AngStoreWeb/Register', {}, {
            list: {method: 'GET', params: {email: user.email, password: user.password}, isArray: false}
        });
        var ans = rr.list();
        $location.path('/login');
    }

    // return data object with store and cart
    return {
        store: myStore,
        cart: myCart,
        storeByCategory: storeByCategory,
        productBySku: productBySku,
        categoryTree: categoryTree,
        defaultStore: defaultStore,
        login: login,
        logout: logout,
        register: register
    };
});




















