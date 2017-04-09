import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions, Response } from "@angular/http";

@Injectable()
export class KweetService {
    constructor(private http: Http) { }

    getTimeline(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/kweet/timeline?user=' + userId).map((response: Response) => response.json());
    }

    getLatest(userId: number) {
        return this.http.get('http://localhost:8080/JEA6Kwetter/api/kweet/latest?user=' + userId + '&amount=10').map((response: Response) => response.json());
    }

    postKweet(message: string, userId: number) {
        let url = 'http://localhost:8080/JEA6Kwetter/api/kweet/create';
        let body = JSON.stringify({ message: message, userId: userId });
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post(url, body, options)
            .map((response: Response) => {
                let kweet = response.json();
            });
    }
}