import { Component, OnInit } from '@angular/core';
import { Request } from 'src/app/model/request';
import { RequestService } from 'src/app/service/request.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-my-requests',
  templateUrl: './my-requests.component.html',
  styleUrls: ['./my-requests.component.css']
})
export class MyRequestsComponent implements OnInit {

  myRequests: Request[]

  constructor(private userService: UserService, private requestService: RequestService) { }

  ngOnInit(): void {
      this.load()
  }

  load(){
    this.userService.getMyRequests().subscribe(result => {
      this.myRequests = result
    })
  }

  cancelRequest(id){
    this.requestService.cancelRequest(id).subscribe(result => {
      this.load()
    })
  }

}
