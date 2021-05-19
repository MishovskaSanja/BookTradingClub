import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/book';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.css']
})
export class CreateRequestComponent implements OnInit {


  // bookToGive: Book
  // bookToTake: Book
  // fromUser: User
  // toUser: User

  request: Request


  constructor() { }

  ngOnInit(): void {
  }

  //TODO: create request

}
