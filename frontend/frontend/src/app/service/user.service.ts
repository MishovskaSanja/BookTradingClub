import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from '../model/user'
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { TokenStorageService } from './token-storage.service';
import { Request } from '../model/request';

const BASE_URL = 'http://localhost:8083/api/user'

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService){}

  public register(data) : Observable<User>{
    return this.http.post<User>(BASE_URL+"/register", data)
  }

  getAllUsers() : Observable<User[]>{
    return this.http.get<User[]>(BASE_URL);
  }

  editInfo(data) : Observable<User>{
    return this.http.put<User>(BASE_URL+'/edit', data)
  }

  getAllUserBooks() : Observable<Book[]>{
    return this.http.get<Book[]>(BASE_URL+"/userBooks")
  }

  getCurrentUser() : Observable<User>{
    return this.http.get<User>(BASE_URL+"/info")
  }

  getIncomingRequests() : Observable<Request[]> {
    return this.http.get<Request[]>(BASE_URL + "/incomingRequests")
  }

  getMyRequests() : Observable<Request[]> {
    return this.http.get<Request[]>(BASE_URL + "/myRequests")
  }

}
