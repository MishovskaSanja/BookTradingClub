import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { User } from 'src/app/model/user';
import { BookService } from 'src/app/service/book.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.css']
})
export class MyBooksComponent implements OnInit {

  user: User
  books : Book[]

  constructor(private userService: UserService,
                private bookService: BookService,
                private router : Router) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
    this.getAllUserBooks()
  }

  public getAllUserBooks(){
    this.userService.getAllUserBooks(this.user.username).subscribe(result => {
      this.books = result
    })
  }
  public delete(id: bigint){
    this.bookService.deleteBook(id).subscribe(
      data => {
        console.log('deleted book ', data)
        this.getAllUserBooks()
      }
    )
  }
}
