import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Request } from 'src/app/model/request';
import { RequestService } from 'src/app/service/request.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-incoming-requests',
  templateUrl: './incoming-requests.component.html',
  styleUrls: ['./incoming-requests.component.css']
})
export class IncomingRequestsComponent implements OnInit {

  incomingRequests: Request[]

  constructor(private userService: UserService, private requestService: RequestService, private router: Router) { }

  ngOnInit(): void {
    this.load()
  }

  load() {
    this.userService.getIncomingRequests().subscribe(result => {
      this.incomingRequests = result
      console.log(result)
    })
  }

  reject(id) {
    this.requestService.cancelRequest(id).subscribe(result => {
      this.load()
    })
  }

  accept(id) {
    this.requestService.acceptRequest(id).subscribe(result => {
      this.router.navigateByUrl("/trades")
    })
  }

}
