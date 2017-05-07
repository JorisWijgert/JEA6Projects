import { Component, OnInit } from '@angular/core';

import { User, Kweet } from '../_models/index';
import { UserService, KweetService, AlertService } from '../_services/index';
import { Router, ActivatedRoute } from "@angular/router";

import { $WebSocket } from 'angular2-websocket/angular2-websocket';

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    model: any = {};
    currentUser: User = new User();
    timeline: Kweet[] = [];
    followers: User[] = [];
    following: User[] = [];
    loading = false;
    socket: $WebSocket;

    constructor(
        private userService: UserService,
        private kweetService: KweetService,
        private alertService: AlertService,
        private route: ActivatedRoute, private router: Router) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.socket = new $WebSocket('ws://localhost:8080/JEA6Kwetter/websocket/kweet');
        this.socket.send4Direct(this.currentUser.name, true);

        this.socket.onMessage(
            (msg: MessageEvent) => {
                console.log("onMessage ", msg.data);
                let k: Kweet = JSON.parse(msg.data);
                if (k != null) {
                    this.timeline.splice(0, 0, k);
                }
            },
            { autoApply: false }
        );
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

    addKweet() {
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

    viewUser(userId: number) {
        this.currentUser.chosenId = userId;

        localStorage.removeItem('currentUser');
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.router.navigate(["profile"]);
    }
}