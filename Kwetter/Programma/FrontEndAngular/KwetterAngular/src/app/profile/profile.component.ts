import { Component, OnInit } from '@angular/core';
import { UserService, KweetService } from '../_services/index';
import { User, Kweet } from '../_models/index';
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: User;
  chosenUser: User = new User;
  following: User[] = [];
  latest: Kweet[] = [];

  constructor(private userService: UserService, private kweetService: KweetService, private route: ActivatedRoute,
        private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.loadChosenUser();
    this.loadFollowing();
    this.loadLatest();
  }

  loadChosenUser() {
    this.userService.getUser(this.currentUser.chosenId).subscribe(chosenUser => { this.chosenUser = chosenUser });
  }

  private loadFollowing() {
    this.userService.getFollowing(this.currentUser.chosenId).subscribe(following => { this.following = following });
  }

  private loadLatest() {
    this.kweetService.getLatest(this.currentUser.chosenId).subscribe(latest =>  {this.latest = latest});
  }

  viewUser(userId: number) {
    this.currentUser.chosenId = userId;

    localStorage.removeItem('currentUser');
    localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
    this.router.navigate(["profile"]);
  }

}
