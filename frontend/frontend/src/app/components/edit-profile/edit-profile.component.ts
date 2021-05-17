import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  user: User

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  onSubmit(data){
      this.userService.editInfo({
        username: this.user.username,
        fullName: data.fullName,
        city: data.city,
        address: data.address,
        state: data.state
      }).subscribe(result => {
        sessionStorage.removeItem("user")
        sessionStorage.setItem("user", JSON.stringify(result))
        
      })
  }

}
