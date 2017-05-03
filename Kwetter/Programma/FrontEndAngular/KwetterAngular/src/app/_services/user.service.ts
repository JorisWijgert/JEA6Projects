import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { User } from '../_models/index';

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/mod/users').map((response: Response) => response.json());
    }

    getFollowers(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/users/followers/' + userId).map((response: Response) => response.json());
    }

    getFollowing(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/users/following/' + userId).map((response: Response) => response.json());
    }

    getUser(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/users/'+ userId).map((response: Response) => response.json());
    }

    update(user: any){
        let url = 'http://localhost:8080/JEA6Kwetter/api/users';
        let body = JSON.stringify({ id: user.id, name: user.name, bio: user.bio, location: user.location, photo: user.photo, web: user.web });
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.put(url, body, options)
            .map((response: Response) => {
                let user = response.json();
                if (user != null) {
                    let returnvalue = user;
                    returnvalue.chosenId = user.id;
                    localStorage.setItem('currentUser', JSON.stringify(returnvalue));
                }
            });
    }

    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));

    }
}