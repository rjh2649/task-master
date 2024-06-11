import TaskMasterClient from '../api/taskMaster';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class TMNavbar extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addNavbarToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new TaskMasterClient();
    }

    /**
     * Add the header to the page.
     */
    async addNavbarToPage() {
        const currentUser = await this.client.getIdentity();

        // const siteTitle = this.createSiteTitle();
        // const userInfo = this.createUserInfoForHeader(currentUser);

        const navbar = document.getElementById('tm-navbar');
        let html = `<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand">Put some image here</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Home</a>
                    </li>
                    <li class="nav-item" id="loginItem">
                        
                    </li>
                </ul>
            </div>
        </div>
    </nav>`;
        navbar.innerHTML = html;
        
        const userInfo = this.createUserInfoForHeader(currentUser);
        document.getElementById('loginItem').appendChild(userInfo);

        
        // header.appendChild(siteTitle);
        // header.appendChild(userInfo);
    }

    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'Playlists';

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('nav-link');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}
