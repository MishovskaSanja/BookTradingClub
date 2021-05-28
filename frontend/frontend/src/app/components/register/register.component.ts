import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
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

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {



  }

  onSubmit(data) {
    this.userService.register(data).subscribe(result => {
      this.user = result;
      const navigationDetails: string[] = ['/login'];
      this.router.navigate(navigationDetails);
    })


  }

}
