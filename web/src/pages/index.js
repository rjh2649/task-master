import Authenticator from "../api/authenticator";
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import TMNavbar from "../components/tmNavbar";
import TaskMasterClient from "../api/taskMaster";

class Index extends BindingClass {

    constructor() {
        super();
        this.auth = new Authenticator();
        this.bindClassMethods = (['mount', 'getPendingTasks', 'getCompletedTasks'], this);
        this.dataStore = new DataStore();
        this.navbar = new TMNavbar();
        this.client = new TaskMasterClient();
    }

    mount() {
        this.navbar.addNavbarToPage();
        this.navbar.createLoginButton();
        this.navbar.createLogoutButton(this.client.getIdentity());
        this.getPendingTasks();
    }

    async getPendingTasks() {
        const errorMessage = document.getElementById('error-message1');
        errorMessage.innerText = ``;
        errorMessage.classList.add('hidden');

        const userId = (await this.auth.getCurrentUserInfo()).email;
        const status = "NOT_STARTED";

        var pendingTasks = [];

        pendingTasks = await this.client.getTasks(userId, status, (error) => {
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.dataStore.set('pending-tasks', pendingTasks);

        const tableBody = document.getElementById('current-task-table-body');
        pendingTasks.forEach(task => {
            const row = document.createElement('tr');

            const description = document.createElement('td');
            description.textContent = task.desc;
            row.appendChild(description);

            const priority = document.createElement('td');
            priority.textContent = task.priority;
            row.appendChild(priority);

            const doBy = document.createElement('td');
            doBy.textContent = task.doBy;
            row.appendChild(doBy);

            const status = document.createElement('td');
            status.textContent = task.status;
            row.appendChild(status);

            const points = document.createElement('td');
            points.textContent = task.points;
            row.appendChild(points);

            tableBody.appendChild(row);
        });
    }

    async getCompletedTasks() {
        const errorMessage = document.getElementById('error-message2');
        errorMessage.innerText = ``;
        errorMessage.classList.add('hidden');

        const userId = (await this.auth.getCurrentUserInfo()).email;
        const status = "COMPLETED";

        var completedTasks = [];

        completedTasks = await this.client.getTasks(userId, status, (error) => {
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.dataStore.set('completed-tasks', completedTasks);

        const tableBody = document.getElementById('completed-task-table-body');
        completedTasks.forEach(task => {
            const row = document.createElement('tr');

            const description = document.createElement('td');
            description.textContent = task.desc;
            row.appendChild(description);

            const priority = document.createElement('td');
            priority.textContent = task.priority;
            row.appendChild(priority);

            const doBy = document.createElement('td');
            doBy.textContent = task.doBy;
            row.appendChild(doBy);

            const status = document.createElement('td');
            status.textContent = task.status;
            row.appendChild(status);

            const points = document.createElement('td');
            points.textContent = task.points;
            row.appendChild(points);

            tableBody.appendChild(row);
        })
    }
}

const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);