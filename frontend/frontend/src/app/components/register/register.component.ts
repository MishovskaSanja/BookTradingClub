import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User
  error: string
  hasError: Boolean

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void { }

  onSubmit(data) {
    this.register(data)
  }

  register(data) {
    if (data.username != '' && data.password != '' && data.city != '' && data.fullName != '' && data.address != '' && data.state != '') {
      this.userService.register(data).subscribe(result => {
        this.user = result;
        this.router.navigateByUrl("/login");
      }, error => {
        this.hasError = true
        this.error = error.error;
      })
    } else {
      this.hasError = true
      this.error = "Please fill out all the fields"
    }
  }

}
