import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from '../model/user'
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { TokenStorageService } from './token-storage.service';
import { Request } from '../model/request';

const BASE_URL = 'http://localhost:8083/api/user'


@Injectable({
  providedIn: 'root'
})
export class UserService {

  headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*');

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  public register(data): Observable<User> {
    return this.http.post<User>(BASE_URL + "/register", data)
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(BASE_URL);
  }

  editInfo(data): Observable<User> {
    return this.http.put<User>(BASE_URL + '/edit', data)
  }

  getAllCurrentUserBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(BASE_URL + "/currentUserBooks")
  }

  getAllUserBooks(username: String): Observable<Book[]> {
    console.log(username)
    return this.http.get<Book[]>(BASE_URL + "/userBooks/" + username, { "headers": this.headers })
  }

  getAllAvailableUserBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(BASE_URL + "/availableUserBooks")
  }

  getAllTakenUserBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(BASE_URL + "/takenUserBooks")
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(BASE_URL + "/info")
  }

  getIncomingRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(BASE_URL + "/incomingRequests")
  }

  getMyRequests(): Observable<Request[]> {
    return this.http.get<Request[]>(BASE_URL + "/myRequests")
  }

}
