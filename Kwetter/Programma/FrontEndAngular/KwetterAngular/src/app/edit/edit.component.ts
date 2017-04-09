import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/index';
import { User, Kweet } from '../_models/index';
import { FormGroup, FormControl, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService, AuthenticationService } from '../_services/index';

@Component({
  moduleId: module.id,
  selector: 'app-profile',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  currentUser: User;
  editProfileForm: FormGroup;
  loading = false;

  constructor(private userService: UserService, private route: ActivatedRoute,
    private router: Router, private alertService: AlertService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.editProfileForm = new FormGroup({
      name: new FormControl(this.currentUser.name, [Validators.required]),
      bio: new FormControl(this.currentUser.bio),
      web: new FormControl(this.currentUser.web),
      location: new FormControl(this.currentUser.location),
      photo: new FormControl(this.currentUser.photo)
    });
  }

  ngOnInit() {
  }

  editProfile() {
            this.loading = true;
    let user = this.editProfileForm.value;
    user.id = this.currentUser.id;

    this.userService.update(user)
      .subscribe(
      data => {
        this.alertService.success('Updating profile successful', true);
        this.router.navigate(["profile"]);
      },
      error => {
                this.loading = false;
        this.alertService.error(error);
      });
  }

}
