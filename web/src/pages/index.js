import Authenticator from "../api/authenticator";
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import TMNavbar from "../components/tmNavbar";

class Index extends BindingClass {

    constructor() {
        super();
        this.auth = new Authenticator();
        this.bindClassMethods = (['mount'], this);
        this.dataStore = new DataStore();
        this.navbar = new TMNavbar();
    }

    mount() {
        this.navbar.addNavbarToPage();

        this.onStartup();
    }

    

    async onStartup() {
        if (await this.auth.isUserLoggedIn()) {
            document.getElementById('logout').hidden = false;
        } else {
            document.getElementById('login').hidden = false;
        }
    }
}

const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);