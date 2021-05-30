import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  user: User

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(result =>{
      this.user = result
    })
  }

  onSubmit(data){
    console.log(data)
      this.userService.editInfo({
        fullName: data.fullName,
        city: data.city,
        address: data.address,
        state: data.state
      }).subscribe(result => {
        this.router.navigateByUrl("/user/profile")
      })

  }

}
