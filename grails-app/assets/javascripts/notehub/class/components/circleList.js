//= wrapped

/*********************************************
 @author Paul Li
 @date Feb, 28, 2017
 @version 2.0 (Stage 2)

 Circles
 ============================================
 declaring a information for the directive
 - controller
 ********************************************/


angular
    .module("notehub.class")
    .component("circleList", {
        templateUrl: '/notehub/class/circleList.html',
        controller: function CircleListController($http,$window){
            var vm = this;
            //used for default ordering of list
            vm.orderProp = 'dateCreated';
            vm.id = getQueryVariable('id');

            // used for ng-repeat num number of times.
            vm.getNumber = function(num) {
                return new Array(num);
            };

            var userData =  null;
            vm.usersCircles = [];
            $http.get('/api/course/?id='+vm.id,{headers: {'Authorization': 'Bearer '+ $window.sessionStorage.token}}).then(function(response) {
                userData = response.data;
                console.log(userData.name);
                console.log(userData.circles);
                console.log(userData.posts);
                console.log("hello");
                //console.log(classData);
                var circleIdList = userData.circles;
                console.log(circleIdList);
                var i;
                for (i = 0; i < circleIdList.length; i++) {
                    console.log(i);
                    $http.get('/api/circle/?id='+circleIdList[i].id,{headers: {'Authorization': 'Bearer '+ $window.sessionStorage.token}}).then(function(response) {
                        console.log(response.data);
                        vm.usersCircles.push(response.data);
                    });
                }
            },function(response){
                console.log(response);
                console.log(response.data);
            });

            vm.title = " "; // should be in the page controller

            //Dummy Data
            /*
             vm.usersCircles =[
             {
             name: "Default Circle",
             desc: "Welcome to notehub!"
             },{
             name: "CS2209 Circle",
             desc: "Logic Logic Logic"
             },{
             name: "CS2208 Circle",
             desc: "1010101000111"
             }
             ]
             */
        },
        controllerAs: "vm",
        transclude: true,
        scope: {},
        bindToController: {}
    });


function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split('&');
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split('=');
        if (decodeURIComponent(pair[0]) == variable) {
            return decodeURIComponent(pair[1]);
        }
    }
    console.log('Query variable %s not found', variable);
}
