import { Injectable } from '@angular/core';
import {  HttpHeaders } from '@angular/common/http';
import {HttpClient} from '@angular/common/http'
import { User } from '../model/user';
import { RequestOptions } from '@angular/http';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string

  constructor(private http: HttpClient){
    this.url = '/api/user'
  }

  // login(data:any){
  //   let url = `/api/login`;
  //   let body = JSON.stringify(data);
  //   let headers = new Headers({'Content-Type':'application/json'});
  //   let reqOptions = new RequestOptions({headers:headers});

  //   return this.http.post(url,body,reqOptions) ????
  //                   .map(res=>res);
  // }


}
