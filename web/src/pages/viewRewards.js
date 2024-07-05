import Authenticator from "../api/authenticator";
import TaskMasterClient from "../api/taskMaster";
import TMNavbar from "../components/tmNavbar";
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class ViewRewards extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'view'], this);
        this.datastore = new DataStore();
        this.navbar = new TMNavbar();
        this.auth = new Authenticator();
        this.client = new TaskMasterClient();
    }

    mount() {
        this.navbar.addNavbarToPage();
        this.view();
    }

    async view() {
        const errorMessage = document.getElementById('error-message');
        errorMessage.innerText = '';
        errorMessage.classList.add('hidden');

        const userId = (await this.auth.getCurrentUserInfo()).email;

        const rewards = await this.client.getRewards(userId, (error) => {
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.datastore.set('rewards', rewards);

        const tableBody = document.getElementById('available-rewards-table-body');
        rewards.forEach(reward => {
            const row = this.generateRow(reward);
            tableBody.insertAdjacentHTML('beforeend', row);
        });
    }

    generateRow(reward) {
        const row = `
            <tr data-reward-id="${reward.rewardId}">
                                        <td>${reward.desc}</td>
                                        <td>${reward.pointsNeeded}</td>
                                        <td class="text-end"></td>
                                    </tr>
        `;

        return row;        
    }
}

const main = async () => {
    const viewRewards = new ViewRewards();
    viewRewards.mount();
}

window.addEventListener('DOMContentLoaded', main);