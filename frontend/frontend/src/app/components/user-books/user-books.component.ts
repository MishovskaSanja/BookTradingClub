import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { User } from '../../model/user'
import { UserService } from 'src/app/service/user.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../model/book';



@Component({
  selector: 'app-user-books',
  templateUrl: './user-books.component.html',
  styleUrls: ['./user-books.component.css']
})
export class UserBooksComponent implements OnInit {

  username: String;
  books: Book[]


  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.username = JSON.stringify(this.route.snapshot.queryParams['username']);

    })

    this.username = this.username.replace('"', '')
    this.username = this.username.replace('"', '')

      this.userService.getAllUserBooks(this.username).subscribe(
        res => {
          this.books = res
          console.log(res)
        });
  }



}
