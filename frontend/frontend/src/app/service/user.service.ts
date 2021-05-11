import { Injectable } from '@angular/core';
import {  HttpHeaders } from '@angular/common/http';
import {HttpClient} from '@angular/common/http'
import { UserLogin } from '../model/userLogin';
import { UserRegister } from '../model/userRegister'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl: string

  constructor(private http: HttpClient){
    this.baseUrl = '/api/user'
  }



  login(user:UserLogin){
    const headers = { 'content-type': 'application/json'}
    const body = JSON.stringify(user)
    console.log(user)
    this.http.post<UserLogin>(this.baseUrl + '/login', body, {'headers':headers})
  }

  register(user:UserRegister){
    const headers = { 'content-type': 'application/json'}
    const body = JSON.stringify(user)
    console.log(user)
    this.http.post<UserLogin>(this.baseUrl + '/register', body, {'headers':headers})
  }


}
