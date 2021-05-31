import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { User } from 'src/app/model/user';
import { BookService } from 'src/app/service/book.service';
import { RequestService } from 'src/app/service/request.service';
import { UserService } from 'src/app/service/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.css']
})
export class CreateRequestComponent implements OnInit {

  user: User
  myBooks: Book[]
  booksByOtherUsers: Book[]
  currentUserUsername: string
  wantedBookId: number;
  wantedBook: Book;
  hasError: Boolean;
  error: string;

  constructor(private userService: UserService, private bookService: BookService, private requestService: RequestService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userService.getAllAvailableUserBooks().subscribe(result => {
      this.myBooks = result
      console.log(result)
    });

    this.getUser()

    this.route.queryParams.subscribe(params => {
      this.wantedBookId = parseInt(this.route.snapshot.queryParams['id']);
    })

    this.bookService.getBook(this.wantedBookId).subscribe(
      res => {
        this.wantedBook = res as Book
      });
  }

  getUser() {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  onSubmit(data) {
    if (data.bookToGive == '' || data.wantedBook == '') {
      this.hasError = true
      this.error = 'Please select the books!'
    } else {
      this.requestService.makeRequest(data).subscribe(result => {
        this.router.navigateByUrl("/requests")
      }, error => {
        this.hasError = true
        this.error = error.error
      })
    }
  }

}
