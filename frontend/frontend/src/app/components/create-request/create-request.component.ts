import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { User } from 'src/app/model/user';
import { BookService } from 'src/app/service/book.service';
import { RequestService } from 'src/app/service/request.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.css']
})
export class CreateRequestComponent implements OnInit {


  user: User
  myBooks : Book[]
  booksByOtherUsers: Book[]
  currentUserUsername : string

  constructor(private userService: UserService, private bookService: BookService, private requestService: RequestService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllUserBooks().subscribe(result =>{
      this.myBooks = result
    })

    this.bookService.getAllBooks().subscribe(result => {
      this.booksByOtherUsers = result
    })

    this.user = JSON.parse(sessionStorage.getItem("user")) as User

  }

  onSubmit(data){
    this.requestService.makeRequest(data).subscribe(result => {
      this.router.navigateByUrl("/requests")
    })
  }

}
