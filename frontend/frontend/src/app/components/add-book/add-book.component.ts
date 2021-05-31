import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  constructor(private bookService: BookService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(data) {
    this.bookService.addBook({
      "name": data.name,
      "description": data.description,
      "imgUrl": data.img
    }).subscribe(result => {
      this.router.navigateByUrl('user/profile');
    })
  }

}
