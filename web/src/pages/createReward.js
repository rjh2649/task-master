import Authenticator from "../api/authenticator";
import TaskMasterClient from "../api/taskMaster";
import TMNavbar from "../components/tmNavbar";
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

class CreateReward extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'create', 'redirectToHome'], this);
        this.auth = new Authenticator();
        this.datastore = new DataStore();
        this.datastore.addChangeListener(this.redirectToHome);
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

        let userId = (await this.auth.getCurrentUserInfo()).email;
        if (userId == null) {
            errorMessage.innerText = "Error: Please log in."
        }
        let rewardId;
        const desc = document.getElementById('desc').value;
        const pointsNeeded = document.getElementById('points-needed').value;

        if (pointsNeeded) {
            const input = parseInt(pointsNeeded);
            if (input == NaN) {
                errorMessage.innerText = "Error: Input must be a whole number (eg. 100)";
                errorMessage.classList.remove(hidden);
            } 
        }

        const createButton = document.getElementById('create');
        const originalButtonText = createButton.innerText;
        createButton.innerText = 'Loading . . .';
        
        const reward = await this.client.createReward(userId, rewardId, desc, pointsNeeded, (error) => {
            createButton.innerText = originalButtonText;
            errorMessage.innerText = `Error: ${error.message}`;
            errorMessage.classList.remove('hidden');
        });

        this.datastore.set('reward', reward);
    }

    async redirectToHome() {
        const reward = this.datastore.get('reward');
        if (reward != null) {
            window.location.href = "/index.html";
        }
    }
}

const main = () => {
    const createReward = new CreateReward();
    createReward.mount();
}

window.addEventListener('DOMContentLoaded', main);