import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { from } from 'rxjs';
import { UserLogin } from 'src/app/model/userLogin';
import { UserService } from '../../service/user.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: UserLogin
  loginForm = this.formBuilder.group({
    username: ' ',
    password: ' '
  })

  constructor(private userService: UserService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

  }

  login() {

    this.user = new UserLogin(this.loginForm.value['username'], this.loginForm.value['password']);

    console.log(this.user)
    this.userService.login(this.user)
  }

}
