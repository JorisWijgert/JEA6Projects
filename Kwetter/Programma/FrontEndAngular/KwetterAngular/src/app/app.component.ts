import { Component } from '@angular/core';

import { User } from './_models/index';
import { UserService } from './_services/index';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: 'app',
    templateUrl: 'app.component.html'
})

export class AppComponent {

    currentUser = new User();
    users: User[] = [];

    constructor(private userService: UserService, private route: ActivatedRoute,
        private router: Router) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.loadAllUsers();
    }

    deleteUser(id: number) {
        //this.userService.delete(id).subscribe(() => { this.loadAllUsers() });
    }

    private loadAllUsers() {
        this.userService.getAll().subscribe(users => { this.users = users; });
    }

    openProfile() {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.currentUser.chosenId = this.currentUser.id
        localStorage.removeItem('currentUser');
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.router.navigate(["profile"]);
    }
}