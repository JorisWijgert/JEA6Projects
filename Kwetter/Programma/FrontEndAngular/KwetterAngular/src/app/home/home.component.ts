import { Component, OnInit } from '@angular/core';

import { User, Kweet } from '../_models/index';
import { UserService, KweetService, AlertService } from '../_services/index';

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    model: any = {};
    currentUser: User;
    timeline: Kweet[] = [];
    followers: User[] = [];
    following: User[] = [];
    loading = false;

    constructor(private userService: UserService, private kweetService: KweetService, private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.loadAllKweets();
        this.loadFollowers();
        this.loadFollowing();
    }

    private loadAllKweets() {
        this.kweetService.getTimeline(this.currentUser.id).subscribe(timeline => { this.timeline = timeline })
    }

    private loadFollowers() {
        this.userService.getFollowers(this.currentUser.id).subscribe(followers => { this.followers = followers })
    }

        private loadFollowing() {
        this.userService.getFollowing(this.currentUser.id).subscribe(following => { this.following = following })
    }

    private addKweet() {
        this.kweetService.postKweet(this.model.kweet, this.currentUser.id).subscribe(
            data => {
                this.alertService.success('Post successful', true);
                this.loadAllKweets();
                this.model.kweet = '';
            },
            error => {
                this.alertService.error(error);
                this.loading = false;
            });
    }
}