import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

const BASE_URL = "http://localhost:8080/api/user/"

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  login(credentials): Observable<any>{
    return this.http.post(BASE_URL + "login", {
      username: credentials.username,
      password: credentials.password
    }, httpOptions)
  }

  register(user): Observable<User> {
    return this.http.post<User>(BASE_URL + 'register', {
        username: user.username,
        password: user.password,
        fullName: user.name,
        city: user.city,
        address: user.address,
        state: user.state
    }, httpOptions)
  }

}
