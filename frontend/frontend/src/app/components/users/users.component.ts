import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';
import { NavigationExtras, Router } from '@angular/router';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[]

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers()
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(result => {
      this.users = result
    })
  }

  navigateToUserBooks(username: String) {
    let navigationExtras: NavigationExtras = {
      queryParams: {
        'username': username
      }
    };
    this.router.navigate(['/userBooks'], { queryParams: { 'username': username } });
  }
}
