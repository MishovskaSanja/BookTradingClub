import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { User } from '../model/user'
import { Observable } from 'rxjs';

import { RequestOptions } from '@angular/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string

  constructor(private http: HttpClient){
    this.baseUrl = 'http://localhost:8080/api/user'
  }

  login(data) : Observable<string> {
    return this.http.post<string>(this.baseUrl + '/login', data)
  }

  register(data) : Observable<User>{
    const headers = { 'content-type': 'application/json'}
    console.log(data)
    return this.http.post<User>(this.baseUrl + '/register', data, {'headers':headers})
  }

  getAllUsers() : Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl);
  }

  getCurrentUser() : Observable<User>{
    return this.http.get<User>(this.baseUrl+'/current')
  }

}
