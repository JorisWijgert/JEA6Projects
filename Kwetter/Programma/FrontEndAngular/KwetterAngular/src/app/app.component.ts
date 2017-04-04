import { Component } from '@angular/core';
import { Http } from "@angular/http";
import { User } from "app/user";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Hello, world!';
  users: User[];

  constructor(private http: Http) {
   http.get('http://localhost:8080/JEA6Kwetter/api/mod/users')
    .subscribe((resp) => {this.users = resp.json();});
  }
}
