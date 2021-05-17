import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  constructor(private bookService: BookService, private router: Router) { }

  user: User

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  onSubmit(data){
     this.bookService.addBook({
        "name" : data.name,
        "description" : data.description,
        "username" : this.user.username
    }).subscribe(result => {
      const navigationDetails: string[] = ['/user/myBooks'];
      this.router.navigate(navigationDetails);
    })
  }

}
