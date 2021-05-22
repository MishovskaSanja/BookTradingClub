import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Request } from '../model/request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

   headers= new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Access-Control-Allow-Origin', '*');

  url: string;

  constructor(private http: HttpClient){
    this.url = 'http://localhost:8080/api/requests'
  }

  public getAllRequests(): Observable<Request[]>{
    return this.http.get<Request[]>(this.url)
  }

  public getAllTrades(): Observable<Request[]>{
    return this.http.get<Request[]>(this.url+'/accepted')
  }

  public makeRequest(data) : Observable<Request>{
    return this.http.post<Request>(this.url+'/post', data,  {"headers": this.headers})
  }

  public cancelRequest(id){
    return this.http.delete(this.url+"/delete/"+id)
  }

  public acceptRequest(id) : Observable<Request>{
    return this.http.post<Request>(this.url+"/accept/"+id, '')
  }



}
