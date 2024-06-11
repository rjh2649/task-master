import Authenticator from "../api/authenticator";
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import TMNavbar from "../components/tmNavbar";
import TaskMasterClient from "../api/taskMaster";

class Index extends BindingClass {

    constructor() {
        super();
        this.auth = new Authenticator();
        this.bindClassMethods = (['mount'], this);
        this.dataStore = new DataStore();
        this.navbar = new TMNavbar();
        this.client = new TaskMasterClient();
    }

    mount() {
        this.navbar.addNavbarToPage();
        this.navbar.createLoginButton();
        this.navbar.createLogoutButton(this.client.getIdentity());
    }
}

const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);