import DataStore from "../util/DataStore";
import TaskMasterClient from "../api/taskMaster";
import TMNavbar from "../components/tmNavbar";
import BindingClass from "../util/bindingClass";

class UpdateTask extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'update', 'redirectToHome'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToHome);
        this.navbar = new TMNavbar();
    }

    mount() {
        document.getElementById('update').addEventListener('click', this.update);
        this.navbar.addNavbarToPage();
        this.client = new TaskMasterClient();
    }        


    async update(event) {
        event.preventDefault();

        const errorMessage = document.getElementById('error-message');
        errorMessage.innerText = ``;
        errorMessage.classList.add('hidden');

        const urlParams = new URLSearchParams(window.location.search);
        const userId = urlParams.get('userId');
        const id = urlParams.get('taskId');
        const points = urlParams.get('points');

        
        var desc = document.getElementById('desc').value;
        if (desc == "") {
            desc = urlParams.get('desc');
        }

        var priority = document.getElementById('task-priority').value;
        if (priority == "") {
            priority = urlParams.get('priority');
        }

        var doBy = document.getElementById('task-do-by').value;
        if (doBy == "") {
            doBy = urlParams.get('doBy');
        }

        var status = document.getElementById('task-status').value;
        if (status == "") {
            status = urlParams.get('status');
        }
        const updateButton = document.getElementById('update');
        const originalButtonText = updateButton.innerText;
        updateButton.innerText = 'Loading . . .';

        const updatedTask = await this.client.updateTask(userId, id, desc, priority, doBy, status, points, (error) => {
            updateButton.innerText = originalButtonText;
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.dataStore.set('task', updatedTask);
    }

    async redirectToHome() {
        const task = this.dataStore.get('task');
        if (task != null) {
            window.location.href = `/index.html`;
        }
    }
}

const main = () => {
    const updateTask = new UpdateTask();
    updateTask.mount();
}

window.addEventListener('DOMContentLoaded', main);