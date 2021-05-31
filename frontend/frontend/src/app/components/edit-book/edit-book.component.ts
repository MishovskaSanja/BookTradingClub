import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  constructor(private bookService: BookService, private router: Router, private route: ActivatedRoute) { }
  book: Book
  bookId: number
  hasError: Boolean
  error: string

  ngOnInit(): void {
    this.hasError = false
    this.route.params.subscribe(params => {
      this.bookId = params['id']
    });

    this.getBook(this.bookId)
  }

  getBook(id) {
    this.bookService.getBook(id).subscribe(result => {
      this.book = result
    })
  }

  onSubmit(data) {
    if (data.name == '' || data.description == '' || data.imgUrl == '') {
      this.hasError = true
      this.error = 'All the fields are mandatory. Please fill out all the input fields.'
    } else {
      this.bookService.editBook({
        "id": this.book.id,
        "name": data.name,
        "description": data.description,
        "imgUrl": data.imgUrl
      }).subscribe(result => {
        this.router.navigateByUrl("/user/profile");
      })
    }
  }

}
