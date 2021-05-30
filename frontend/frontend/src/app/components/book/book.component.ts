import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/model/book';
import { User } from 'src/app/model/user';
import { BookService } from 'src/app/service/book.service';
import { NavigationExtras, Router } from '@angular/router';


@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books: Book[]
  user: User

  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit() {
    this.bookService.getAllBooks().subscribe(result => {
      this.books = result;
    });

    this.user = JSON.parse(sessionStorage.getItem("user")) as User

  }

  navigateToRequest(id: bigint) {
    let navigationExtras: NavigationExtras = {
      queryParams: {
        'id': id
      }
    };
    this.router.navigate(['/createRequest'], {queryParams : { 'id': id }});
  }
}
