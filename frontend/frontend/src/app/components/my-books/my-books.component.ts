import { Component, OnInit } from '@angular/core';
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
  takenBooks: Book[]
  availableBooks: Book[]

  constructor(private userService: UserService,
    private bookService: BookService) { }

  ngOnInit(): void {
    this.getUser()
    this.getAllTakenBooks()
    this.getAllAvailableBooks()
  }

  public getUser() {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  public getAllTakenBooks() {
    this.userService.getAllTakenUserBooks().subscribe(result => {
      this.takenBooks = result
    })
  }

  public getAllAvailableBooks() {
    this.userService.getAllAvailableUserBooks().subscribe(result => {
      this.availableBooks = result
    })
  }

  public delete(id: bigint) {
    this.bookService.deleteBook(id).subscribe(result => {
      this.getAllTakenBooks()
      this.getAllAvailableBooks()
    })
  }
}
