import DataStore from "../util/DataStore";
import BindingClass from "../util/bindingClass";
import TaskMasterClient from "../api/taskMaster";
import TMNavbar from "../components/tmNavbar";

class CreateTask extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'create', 'redirectToHome'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToHome);
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

        // const taskData = {
        //     id: this.generateUniqueID(),
        //     desc: document.getElementById('description').value,
        //     priority: document.getElementById('priority').value,
        //     doBy: document.getElementById('do-by').value,
        //     status: 'NOT_STARTED',
        //     points: this.pointsForPriority(document.getElementById('priority').value)
        // }

        let id;
        const desc = document.getElementById('desc').value;
        const priority = document.getElementById('task-priority').value;
        const doBy = document.getElementById('task-do-by').value;
        const status = 'NOT_STARTED';
        let points;
        
        const createButton = document.getElementById('create');
        const originalButtonText = createButton.innerText
        createButton.innerText = 'Loading . . .';

        const task = await this.client.createTask(id, desc, priority, doBy, status, points, (error) => {
            createButton.innerText = originalButtonText;
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        })

        this.dataStore.set('task', task);        
    }

    async redirectToHome() {
        const task = this.dataStore.get('task');
        if (task != null) {
            window.location.href = `/index.html`;
        }
    }
}

const main = () => {
    const createTask = new CreateTask();
    createTask.mount();
}

window.addEventListener('DOMContentLoaded', main);