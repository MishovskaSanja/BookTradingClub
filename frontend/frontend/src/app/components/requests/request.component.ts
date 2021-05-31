import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/service/request.service'
import { Request } from 'src/app/model/request'
import { Router } from '@angular/router';
import { User } from '../../model/user';


@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  requests: Request[];
  user: User;

  constructor(public requestService: RequestService,
    private router: Router) { }

  ngOnInit(): void {
    this.getUser()
    this.getRequests()
  }

  getUser() {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  getRequests() {
    this.requestService.getAllRequests().subscribe(result => {
      this.requests = result;
    });
  }

}
