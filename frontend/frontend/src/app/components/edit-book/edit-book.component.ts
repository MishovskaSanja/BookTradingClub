import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  constructor(private bookService: BookService, private router: Router) { }

  user: User
  book: any




  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
    this.book =  history.state as Book
  }

  onSubmit(data){
    this.bookService.editBook({
      "id": data.id,
      "name": data.name,
      "description": data.description,
      "owner": this.user
    }).subscribe(result => {
      const navigationDetails: string[] = ['user/myBooks'];
    this.router.navigate(navigationDetails);
    })
}
}
