import DataStore from "../util/DataStore";
import BindingClass from "../util/bindingClass";
import TaskMasterClient from "../api/taskMaster";
import TMNavbar from "../components/tmNavbar";

class CreateTask extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'create'], this);
        this.dataStore = new DataStore();
        this.navbar = new TMNavbar();
    }

    mount() {
        document.getElementById('create').addEventListener('click', this.create);
        
        this.navbar.addNavbarToPage();

        this.client = new TaskMasterClient();
    }

    async create(event) {
        event.preventDefault();

        const errorMessage = document.getElementById('error-message');
        errorMessage.innerText = ``;
        errorMessage.classList.add('hidden');

        const desc = document.getElementById('description');
        const priority = document.getElementById('priority');
        const doBy = document.getElementById('do-by');
        
        const createButton = document.getElementById('create');
        const originalButtonText = createButton.innerText
        createButton.innerText = 'Loading . . .';

        const task = await this.client.createTask(desc, priority, doBy, (error) => {
            createButton.innerText = originalButtonText;
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        })

        this.dataStore.set('task', task);
    }
}

const main = () => {
    const createTask = new CreateTask();
    createTask.mount();
}

window.addEventListener('DOMContentLoaded', main);