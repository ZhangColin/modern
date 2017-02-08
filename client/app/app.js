import 'bootstrap/dist/css/bootstrap.css'
import angular from 'angular'

let app = () => {
    return {
        template: require('./app.html'),
        controller: 'AppController',
        controllerAs: 'app'
    }
}

class AppController {
    constructor() {
        this.name = 'Modern'
    }
}

const MODULE_NAME = 'app'

angular.module(MODULE_NAME, []).directive('app', app).controller('AppController', AppController)

export default MODULE_NAME