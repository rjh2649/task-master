import Authenticator from "../api/authenticator";
import BindingClass from "../util/bindingClass";
import TMNavbar from "../components/tmNavbar";
import TaskMasterClient from "../api/taskMaster";
import DataStore from "../util/DataStore";

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
        this.getCompletedTasks();
    }

    async getPendingTasks() {
        const errorMessage = document.getElementById('error-message1');
        errorMessage.innerText = ``;
        errorMessage.classList.add('hidden');

        const userId = (await this.auth.getCurrentUserInfo()).email;
        const status = "NOT_STARTED";

        const pendingTasks = await this.client.getTasks(userId, status, (error) => {
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.dataStore.set('pending-tasks', pendingTasks);

        const tableBody = document.getElementById('current-task-table-body');
        pendingTasks.forEach(task => {
            const row = this.generateRow(task);
            tableBody.insertAdjacentHTML('beforeend', row);
        });
    }

    async getCompletedTasks() {
        const errorMessage = document.getElementById('error-message2');
        errorMessage.innerText = ``;
        errorMessage.classList.add('hidden');

        const userId = (await this.auth.getCurrentUserInfo()).email;
        const status = "COMPLETED";

        const completedTasks = await this.client.getTasks(userId, status, (error) => {
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.dataStore.set('completed-tasks', completedTasks);

        const tableBody = document.getElementById('completed-task-table-body');
        completedTasks.forEach(task => {
            const row = this.generateRow(task);
            tableBody.insertAdjacentHTML('beforeend', row);
        });
    }

    generateRow(task) {
        const row = `
        <tr data-task-id="${task.id}">
            <td>${task.desc}</td>
            <td>${task.priority}</td>
            <td>${task.doBy}</td>
            <td>${task.status}</td>
            <td>${task.points}</td>
            <td class="text-end">
                <div class="btn-group dropend">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        Actions
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/updateTask.html?userId=${task.userId}&taskId=${task.id}&desc=${task.desc}&priority=${task.priority}&doBy=${task.doBy}&status=${task.status}&points=${task.points}" data-action="update">Update Task</a></li>
                        <li><a class="dropdown-item" href="#" data-task-id="${task.id}">Delete Task</a></li>
                    </ul>
                </div>
            </td>
        </tr>
    `;
    return row;
    }
}

const main = async () => {
    const index = new Index();
    index.mount();
};

window.addEventListener('DOMContentLoaded', main);