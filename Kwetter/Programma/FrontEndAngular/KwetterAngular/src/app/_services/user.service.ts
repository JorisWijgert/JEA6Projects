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
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/user/getfollowers?user=' + userId).map((response: Response) => response.json());
    }

    getFollowing(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/user/getfollowing?user=' + userId).map((response: Response) => response.json());
    }

    getUser(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/user/user?id='+ userId).map((response: Response) => response.json());
    }

    update(user: any){
        let url = 'http://localhost:8080/JEA6Kwetter/api/user/update';
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

    // getById(id: number) {
    //     return this.http.get('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    // }

    // update(user: User) {
    //     return this.http.put('/api/users/' + user.id, user, this.jwt()).map((response: Response) => response.json());
    // }

    // delete(id: number) {
    //     return this.http.delete('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    // }

    // private helper methods

    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));

    }
}