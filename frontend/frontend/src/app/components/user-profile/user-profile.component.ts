import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User

  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(result => {
      this.user = result
    })
  }



}
